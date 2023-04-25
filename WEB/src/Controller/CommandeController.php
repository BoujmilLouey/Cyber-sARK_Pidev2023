<?php

namespace App\Controller;

use App\Entity\Commande;
use Dompdf\Dompdf;
use Dompdf\Options;
use App\Entity\LigneCommande;
use App\Entity\User;
use App\Form\CommandeType;
use App\Repository\CommandeRepository;
use App\Repository\UserRepository;
use App\Repository\LigneCommandeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Repository\ProduitRepository;
use PHPUnit\TextUI\Command;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\VarDumper\Cloner\Data;
use Symfony\Component\Notifier\Message\SmsMessage;
use Twilio\Rest\Client;

use Symfony\Component\Notifier\TexterInterface;

#[Route('/commande')]
class CommandeController extends AbstractController
{
    #[Route('/affiche', name: 'affichecommande', methods: ['GET'])]
    public function index(LigneCommandeRepository $repository ): Response
    {
        return $this->render('commande/index.html.twig', [
            'commandes' => $repository->findAll(),
            
        ]);
    }

    #[Route('/', name: 'commande', methods: ['GET', 'POST'])]
    public function ValidCom(UserRepository $usrRep,SessionInterface $session, ProduitRepository $productRepository, AuthenticationUtils $authenticationUtils,TexterInterface $texter): Response
    {
        $panier = $session->get("panier", []);

        // On "fabrique" les données
        $dataPanier = [];
        $total = 0;

        foreach($panier as $id => $quantite){
            $product = $productRepository->find($id);
            $dataPanier[] = [
                "produit" => $product,
                "quantite" => $quantite
            ];
            $total += $product->getPrix() * $quantite;
        }
        $order=new Commande();
        $user=$this->getUser()->getUserIdentifier();
        $currentuser=$usrRep->findOneBy(array('id'=>$user));
        $order->setUser($currentuser);
        $order->setDateCommande(new \DateTime());
        $order->setMontantCommande($total);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($order);
        $entityManager->flush();
        foreach ($dataPanier as $item) {
            $productOrder=new LigneCommande();
            $productOrder->setCommande($order);
            $productOrder->setQuantite($item['quantite']);
            $productOrder->setPrice($productRepository->find($item['produit']->getId())->getPrix()*$item['quantite']);
            $productOrder->setProduit($productRepository->find($item['produit']->getId()));
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($productOrder);
            $entityManager->flush();

        }
        $sid='ACb8752af82006d3e7939e58bf557787d0';
        $token='af153920a2e0c01427b209ef05c4465a';
        $from = '+13155993597';
        $twilio = new Client($sid, $token); 
 
        $message = $twilio->messages 
                          ->create("+21699027754", // to 
                                   array(  
                                       "messagingServiceSid" => "MGcbf9acd4611dfe8faf4a1265c0c05112",      
                                       "body" => "Votre Commande est validé" 
                                   ) 
                          ); 
        $session->remove("panier");
        $this->addFlash('success', 'Your commande a ete valider');

        return $this->redirectToRoute('home');
    }
    #[Route('/state', name: 'stat', methods: ['GET', 'POST'])]
    public function statistiques(CommandeRepository  $commandeRepository){
        // On va chercher toutes les catégories

        $commande = $commandeRepository->countByDate();
        $dates = [];
        $commandeCount = [];
        //$categColor = [];
        foreach($commande as $com){
            $dates[] = $com['dateCommande'];
            $commandeCount[] = $com['count'];
        }
        return $this->render('commande/stats.html.twig', [
            'dates' => json_encode($dates),
            'commandeCount' => json_encode($commandeCount),
        ]);


    }
    // #[Route('/detail/{id}', name: 'detail', methods: ['GET', 'POST'])]   
    // public function show($id , Commande $commande,) {
    //     $commande = $this->getDoctrine()->getRepository(Commande::class)->find($id);
  
    //     return $this->render('commande/detail.html.twig', array('commande' => $commande));
    // }
  
  
  
    #[Route('/detail/{id}', name: 'detail', methods: ['GET'])]
    public function show(LigneCommande $commande): Response
    {
        return $this->render('commande/show.html.twig', [
            'commande' => $commande,
        ]);
    }


    //   #[Route('/pdf', name: 'print-pdf', methods: ['GET'])]
    //   public function printPdf(Request $request ,CommandeRepository  $commandeRepository)
    //   {
    //       // Get the HTML content to be converted to PDF
    //       $html = $this->renderView('commande/pdf.html.twig', [
          
    //         'commandes' => $commandeRepository->findAll(),
            
    //     ]);

    //       // Create a new Dompdf instance
    //       $dompdf = new Dompdf();
    //       $options = $dompdf->getOptions();
    //       $options->setDefaultFont('Courier');
    //       $dompdf->setOptions($options);
    //       // Load the HTML content into Dompdf
    //       $dompdf->loadHtml($html);
      
    //       // Set the paper size and orientation
    //       $dompdf->setPaper('A4', 'portrait');
      
    //       // Render the PDF content
    //       $dompdf->render();
    //   // Output the generated PDF to Browser
    //       $dompdf->stream();
    //       // Generate the response object with the PDF file
    //       $pdfContent = $dompdf->output();
    //       $response = new Response($pdfContent);
      
    //       // Set the headers for the response
    //       $response->headers->set('Content-Type', 'application/pdf');
    //       $response->headers->set('Content-Disposition', 'attachment;filename="filename.pdf"');
      
    //       return $response;
          
    //   }
    #[Route('/delete/{id}', name: 'delete')]
    public function delete(Request $request, Commande $commande, CommandeRepository $commandeRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$commande->getId(), $request->request->get('_token'))) {
            $commandeRepository->remove($commande, true);
        }

        return $this->redirectToRoute('affichecommande', [], Response::HTTP_SEE_OTHER);
    }
      
    #[Route('/pdf', name: 'print-pdf', methods: ['GET'])]
    public function generatePdf(LigneCommandeRepository $repository )
    {
        $data = $this->getDoctrine()->getRepository(Commande::class)->findAll();
    
        $html = $this->renderView('commande/pdf.html.twig', [
            'data' => $repository->findAll(),
        ]);
    
        $dompdf = new Dompdf();
        $dompdf->loadHtml($html);
        $dompdf->render();
    
        $pdfOutput = $dompdf->output();
    
        return new Response($pdfOutput, 200, [
            'Content-Type' => 'application/pdf'
        ]);
    }
      


}
