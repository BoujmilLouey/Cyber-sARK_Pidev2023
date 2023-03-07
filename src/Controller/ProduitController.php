<?php

namespace App\Controller;

use App\Entity\CategorieProduit;
use App\Entity\Produit;
use App\Form\ProduitType;
use App\Form\SearchProduitType;

use App\Repository\ProduitRepository;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


#[Route('/produit')]
class ProduitController extends AbstractController
{
    #[Route('/', name: 'app_produit_index', methods: ['GET'])]
    public function index(ProduitRepository $produitRepository, Request $request ): Response
    {     $produit = new Produit();
        $form = $this->createForm(SearchProduitType::class, $produit);
        $form->handleRequest($request);
        return $this-> renderForm('produit/index.html.twig', [
            'produits' => $produitRepository->findAll(),
            'form' => $form,
        ]);
    }

    #[Route('/new', name: 'app_produit_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ProduitRepository $produitRepository ): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form['image']->getData();
            if ($imageFile) {
            $fileName = md5(uniqid()) . '.' . $imageFile->guessExtension();
            $imageFile->move(
                $this->getParameter('Images'),
                $fileName
            );
            $produit->setImage($fileName);}
            $produitRepository->save($produit, true);

            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form,

        ]);
    }


    #[Route('/listproduitWithSearch', name: 'listproduitWithSearch')]
    public function listproduitWithSearch(Request $request, ProduitRepository $repository)
    {
        //All of Student
        $produit = $repository->findAll();
        //search
        $searchForm = $this->createForm(SearchProduitType::class);
        $searchForm->add("Recherche", SubmitType::class);
        $searchForm->handleRequest($request);
        if ($searchForm->isSubmitted()) {
            $nom = $searchForm['nom']->getData();
            $resultOfSearch = $repository->searchProduit($nom);
            return $this->render('produit/searchProduit.html.twig', array(
                "resultOfSearch" => $resultOfSearch,
                "searchProduit" => $searchForm->createView()));
        }
        return $this->render('produit/listWithSearch.html.twig', array(
            "produits" => $produit,
            "searchProduit" => $searchForm->createView()));
    }

    #[Route('/show/{id}', name: 'app_produit_show', methods: ['GET'])]
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_produit_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form['image']->getData();
            if ($imageFile) {
            $fileName = md5(uniqid()) . '.' . $imageFile->guessExtension();
            $imageFile->move(
                $this->getParameter('Images'),
                $fileName
            );
            $produit->setImage($fileName);}
            $produitRepository->save($produit, true);

            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_produit_delete', methods: ['POST'])]
    public function delete(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getId(), $request->request->get('_token'))) {
            $produitRepository->remove($produit, true);
        }

        return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
    }



    #[Route('/state', name: 'stat', methods: ['GET', 'POST'])]
    public function statistiques(ProduitRepository  $produitRepository){
        // On va chercher toutes les catégories

        $produit = $produitRepository->countByPrix();
        $prix = [];
        $produitCount = [];
        //$categColor = [];
        foreach($produit as $com){
            $prix[] = $com['prix'];
            $produitCount[] = $com['count'];
        }
        return $this->render('produit/stats.html.twig', [
            'prix' => json_encode($prix),
            'produitCount' => json_encode($produitCount),
        ]);
    }
}

