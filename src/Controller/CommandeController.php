<?php

namespace App\Controller;

use App\Entity\Commande;
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
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;

#[Route('/commande')]
class CommandeController extends AbstractController
{
    #[Route('/affiche', name: 'affichecommande', methods: ['GET'])]
    public function index(LigneCommandeRepository $repository): Response
    {
        return $this->render('commande/index.html.twig', [
            'commandes' => $repository->findAll(),
        ]);
    }

    #[Route('/', name: 'commande', methods: ['GET', 'POST'])]
    public function ValidCom(UserRepository $usrRep,SessionInterface $session, ProduitRepository $productRepository, AuthenticationUtils $authenticationUtils): Response
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
        $session->remove("panier");
        $this->addFlash('success', 'Your commande a ete valider');

        return $this->redirectToRoute('home');
    }

    
}
