<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Form\Commande1Type;
use App\Repository\CommandeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

#[Route('/mobile')]
class CoMobileController extends AbstractController
{
    #[Route('/allcommande', name: 'allcommande', methods: ['GET'])]
    public function allcommande(CommandeRepository $commandeRepository,SerializerInterface $serializer): Response
    {
            $commande = $commandeRepository->findAll();
            $json = $serializer->serialize($commande, 'json', ['groups' => "commande"]);
            return new Response($json);
        
    }

    #[Route('/new', name: 'app_co_mobile_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CommandeRepository $commandeRepository): Response
    {
        $commande = new Commande();
        $form = $this->createForm(Commande1Type::class, $commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $commandeRepository->save($commande, true);

            return $this->redirectToRoute('app_co_mobile_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('co_mobile/new.html.twig', [
            'commande' => $commande,
            'form' => $form,
        ]);
    }

    #[Route('/commandebyid/{id}', name: 'commandebyid', methods: ['GET'])]
    public function commandebyid(CommandeRepository $commandeRepository,$id, NormalizerInterface $normalizer): Response
    {
        $commande = $commandeRepository->find($id);
         $commandeNormalises = $normalizer->normalize($commande, 'json', ['groups' => "commande"]);
         return new Response(json_encode($commandeNormalises));}
    

    #[Route('/updatecommande/{id}', name: 'updatecommande', methods: ['GET', 'POST'])]
    public function updatecommande(Request $req , $id, NormalizerInterface $normalizer): Response
    {
        {   $em = $this->getDoctrine()->getManager();
            $commande = $em->getRepository(Commande::class)->find($id);
            $commande->setid($req->get('id '));
            $commande->setDateCommande($req->get('dateCommande'));
            $commande->setMontantCOmmande($req->get('montantCOmmande'));
           
            
            $em->flush();
            $jsonContent = $normalizer->normalize($commande, 'json', ['groups' => 'commande']);
            return new Response("Demande Carte updated successfully " . json_encode($jsonContent));
        }
    }
 
    #[Route("/addcommande/JSON", name: "addcommande")]
    public function addcommande(Request $req,   NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $commande = new Commande();
        $commande->setid($req->get('id'));
        $commande->setDateCommande($req->get('dateCommande'));
        $commande->setMontantCOmmande($req->get('montantCOmmande'));
        $em->persist($commande);
        $em->flush();
        $jsonContent = $Normalizer->normalize($commande, 'json', ['groups' => 'commande']);
        return new Response(json_encode($jsonContent));}



    #[Route('/deletecommande/{id}', name: 'deletecommande', methods: ['POST'])]
    public function deletecommande(Request $req,$id, NormalizerInterface $Normalizer,Commande $commande, CommandeRepository $commandeRepository): Response
    {
         $em = $this->getDoctrine()->getManager();
         $commande = $em->getRepository(Commande::class)->find($id);
         $em->remove($commande);
         $em->flush();
         $jsonContent = $Normalizer->normalize($commande, 'json', ['groups' => 'commande']);
    return new Response("Demande Carte deleted successfully " . json_encode($jsonContent));
    }

}