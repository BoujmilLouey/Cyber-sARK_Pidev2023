<?php
namespace App\Controller;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;

class searchController extends AbstractController
{
/**
 * @Route("/search", name="search")
 */
public function search(Request $request,EntityManagerInterface $entityManager):Response
{
    // récupérer les paramètres de recherche
    $query = $request->query->get('q');
    $category = $request->query->get('category');

    // effectuer la recherche en utilisant Doctrine ou tout  autre moyen
    $results = $this->$entityManager()->getRepository(Product::class)->search($query, $category);

    // renvoyer les résultats en format JSON
    return $this->json($results);
}}
