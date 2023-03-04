<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Twilio\Rest\Client;


class SMSController extends AbstractController
{
    #[Route('/sms', name: 'sms')]
    public function index(): Response
    { 
        $sid='ACb8752af82006d3e7939e58bf557787d0';
        $token='af153920a2e0c01427b209ef05c4465a';
        $from = '+13155993597';
        $twilio = new Client($sid, $token); 
 
        $message = $twilio->messages 
                          ->create("+21699027754", // to 
                                   array(  
                                       "messagingServiceSid" => "MGcbf9acd4611dfe8faf4a1265c0c05112",      
                                       "body" => "Your message" 
                                   ) 
                          ); 
         
        print($message->sid);

        return $this->render('sms/index.html.twig', [
            'controller_name' => 'SMSController',
        ]);
    }
}
