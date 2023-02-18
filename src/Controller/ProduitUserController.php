<?php

namespace App\Controller;

use App\Entity\Produit;

use App\Repository\ProduitRepository;
use App\Repository\CategorieProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Entity\CategorieProduit;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/store')]
class ProduitUserController extends AbstractController

{
    #[Route('/', name: 'app_store_index', methods: ['GET'])]
    public function index(ProduitRepository $produitRepository): Response
    {
        return $this->render('produituser/index.html.twig', [
            'produits' => $produitRepository->findAll(),
            
        ]);
    }

    #[Route('/{id}', name: 'app_store_show', methods: ['GET'])]
    public function show(Produit $produit,ProduitRepository $produitRepository): Response
    {
        return $this->render('produituser/show.html.twig', [
            'produit' => $produit,
            'produits' => $produitRepository->findAll(),
        ]);
    }
  
}