<?php

namespace App\Controller;

use App\Entity\LigneCommande;
use App\Form\LigneCommandeType;
use App\Repository\LigneCommandeRepository;
use App\Entity\Produit;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;


#[Route('/cart', name: 'cart_')]
class CartController extends AbstractController
{
    #[Route('/', name: 'index', methods: ['GET'])]
    public function index(SessionInterface $session, ProduitRepository $productsRepository)
    {
        $panier = $session->get("panier", []);

        // On "fabrique" les données
        $dataPanier = [];
        $total = 0;

        foreach($panier as $id => $quantite){
            $product = $productsRepository->find($id);
            $dataPanier[] = [
                "produit" => $product,
                "quantite" => $quantite
            ];
            $total += $product->getPrix() * $quantite;
        }

        return $this->render('cart/index.html.twig', compact("dataPanier", "total"));
    }

    #[Route('/add/{id}', name: 'add')]
    public function add(Produit $product, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $product->getId();

        if(!empty($panier[$id])){
            if(($panier[$id] < 10)) {
            $panier[$id]++;
            
            $this->addFlash('success', 'Votre produit a été ajouté au panier');
            }
            else
            {
                $this->addFlash('warning', 'Vous avez atteint la limite de 10 produits pour ce produit');
            }
        }else{
            $panier[$id] = 1;
            $this->addFlash('success', 'Votre produit a été ajouté au panier');
        }

        
        $session->set("panier", $panier);
        return $this->redirectToRoute("cart_index");
    }
    #[Route('/remove/{id}', name: 'remove', methods: ['GET'])]
    public function remove(Produit $product, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $product->getId();

        if(!empty($panier[$id])){
            if($panier[$id] > 1){
                $panier[$id]--;
            }else{
                unset($panier[$id]);
            }
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);
        $this->addFlash('success', 'Votre produit a ete retier du panier');

        return $this->redirectToRoute("cart_index");
    }

    #[Route('/delete/{id}', name: 'delete', methods: ['GET', 'POST'])]
    public function delete(Produit $product, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $product->getId();

        if(!empty($panier[$id])){
            unset($panier[$id]);
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);
        $this->addFlash('success', 'Votre produit a ete retier du panier');

        return $this->redirectToRoute("cart_index");
    }

    #[Route('/delete', name: 'delete_all')]
    public function deleteAll(SessionInterface $session)
    {  
       
    
        $session->remove("panier");
        $this->addFlash('success', 'Votre panier a ete vider');
        return $this->redirectToRoute("cart_index");
        
         
    }











    
    // #[Route('/detail/{id}', name: 'detail', methods: ['GET'])]
    // public function show(LigneCommande $commande): Response
    // {

    //     return $this->render('cart/show.html.twig', [
    //         'commande' => $commande,
    //     ]);
    // }
}
