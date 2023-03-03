<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;

class MailerTestController extends AbstractController
{
    #[Route('/mailer/test', name: 'app_mailer_test')]
    public function index(): Response
    {
        return $this->render('mailer_test/index.html.twig', [
            'controller_name' => 'MailerTestController',
        ]);
    }
    private $mailer;

    public function __construct(MailerInterface $mailer)
    {
        $this->mailer = $mailer;
    }
    #[Route('/mailer/test/send', name: 'app_mailer_test_send')]
    public function sendEmail( \Swift_Mailer $mailer)
    {
        $message = (new \Swift_Message('Validation Commande'))
        ->setFrom('noreply_cybersARK@gmail.com')
        ->setTo('louey.boujmil@esprit.tn')
        ->setBody(

    'CYBER-ARK@gmail.com'
        );

    // On envoie le message

    $mailer->send($message);

        return $this->redirectToRoute('home');
    }
}

