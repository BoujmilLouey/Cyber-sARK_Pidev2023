<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AcceesDeniedController extends AbstractController
{
    #[Route('/access_denied', name: 'app_accees_denied')]
    public function index(): Response
    {
        return $this->render('access_denied.html.twig', [
            'controller_name' => 'AcceesDeniedController',
        ]);
    }
}
