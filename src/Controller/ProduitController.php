<?php

namespace App\Controller;

<<<<<<< Updated upstream
use App\Entity\CategorieProduit;
=======
>>>>>>> Stashed changes
use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/produit')]
class ProduitController extends AbstractController
{
    #[Route('/', name: 'app_produit_index', methods: ['GET'])]
<<<<<<< Updated upstream
    public function index(ProduitRepository $produitRepository): Response
    {
        return $this-> render('produit/index.html.twig', [
            'produits' => $produitRepository->findAll(),
            
=======
    public function index(ProduitRepository $produitRepository,Request $request): Response
    {
        $filters = $request->query->all();

        $products = $produitRepository->findByFilter($filters);

        return $this->render('produit/index.html.twig', [
            'produits' => $produitRepository->findAll(),
            'produits' => $products,
>>>>>>> Stashed changes
        ]);
    }

    #[Route('/new', name: 'app_produit_new', methods: ['GET', 'POST'])]
<<<<<<< Updated upstream
    public function new(Request $request, ProduitRepository $produitRepository ): Response
=======
    public function new(Request $request, ProduitRepository $produitRepository): Response
>>>>>>> Stashed changes
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
<<<<<<< Updated upstream
            $imageFile = $form['image']->getData();
            if ($imageFile) {
            $fileName = md5(uniqid()) . '.' . $imageFile->guessExtension();
            $imageFile->move(
                $this->getParameter('Images'),
                $fileName
            );
            $produit->setImage($fileName);}
            $produitRepository->save($produit, true);
=======
            $produitRepository->save($produit, true);
            $image = $form->get('image')->getData();

            if ($image) {
                $filename = uniqid() . '.' . $image->guessExtension();

                $image->move(
                    $this->getParameter('images_directory'),
                    $filename
                );

                $produit->setImage($filename);
            }
>>>>>>> Stashed changes

            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form,
<<<<<<< Updated upstream

=======
            
>>>>>>> Stashed changes
        ]);
    }

    #[Route('/{id}', name: 'app_produit_show', methods: ['GET'])]
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
}
