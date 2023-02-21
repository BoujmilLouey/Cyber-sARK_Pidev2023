<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Form\CoursType;



use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CoursRepository;


class HomeController extends AbstractController
{
    #[Route('/', name: 'app_home')]
    public function index(coursRepository $coursRepository): Response
    {
        return $this->render('base.html.twig', [
            'cour' => $coursRepository->findAll(),
        ]);
    }
}
