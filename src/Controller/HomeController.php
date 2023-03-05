<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Entity\Calendar;
use App\Form\CoursType;



use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CoursRepository;
use App\Repository\CalendarRepository;


class HomeController extends AbstractController
{
    #[Route('/cours/front', name: 'app_home')]
    public function index(CalendarRepository $calendar): Response
    {



        $events = $calendar->findAll();

        $rdvs = [];

        foreach ($events as $event) {
            $rdvs[] = [
                'id' => $event->getId(),
                'start' => $event->getStart()->format('Y-m-d H:i:s'),
                'end' => $event->getEnd()->format('Y-m-d H:i:s'),
                'titre' => $event->getTitle(),
                'allDay' => $event->isAllDay(),
            ];
        }
        $data = json_encode($rdvs);
        return $this->render(
            'cours/cal.html.twig',
            compact('data')

        );
    }
}
