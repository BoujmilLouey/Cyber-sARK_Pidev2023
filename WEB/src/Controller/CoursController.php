<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Entity\commentaire;
use App\Form\CoursType;
use App\Repository\CoursRepository;
use App\Repository\CalendarRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\HttpFoundation\FileBag;
use Doctrine\ORM\EntityManagerInterface;


#[Route('/cours')]
class CoursController extends AbstractController
{
    #[Route('/', name: 'app_cours_index', methods: ['GET'])]
    public function index(coursRepository $coursRepository, CalendarRepository $calendarRepository): Response
    {
        return $this->render('cours/index.html.twig', [
            'cours' => $coursRepository->findAll(),
            //'calendars' => $calendarRepository->findAll(),
        ]);
    }



    #[Route('/front', name: 'app_cours1_index', methods: ['GET'])]
    public function index_front(CoursRepository $coursRepository, CalendarRepository $calendarRepository): Response
    {

        return $this->render('cours/index_front.html.twig', [
            'cours' => $coursRepository->findAll(),

        ]);
    }



    #[Route('/cal', name: 'app_cal_index', methods: ['GET'])]
    public function index_cal(CoursRepository $coursRepository, CalendarRepository $calendarRepository): Response
    {
        $events = $calendarRepository->findAll();
        $rdvs = [];

        foreach ($events as $event) {
            $rdvs[] = [
                'id' => $event->getId(),
                'start' => $event->getStart()->format('Y-m-d H:i:s'),
                'end' => $event->getEnd()->format('Y-m-d H:i:s'),
                'title' => $event->getTitle(),
                'allDay' => $event->isAllDay(),
            ];
        }
        $data = json_encode($rdvs);

        return $this->render('cours/cal.html.twig', [
            'cours' => $coursRepository->findAll(),
            'data' => $data,
        ]);
    }




    // 'cours' => $coursRepository->findAll(),

    //pdf 

    #[Route('/pdf', name: 'app_cours_pdf', methods: ['GET'])]

    public function generatePdf(CoursRepository $coursRepository)
    {
        $data = $this->getDoctrine()->getRepository(cours::class)->findAll();

        $html = $this->renderView('cours/pdf.html.twig', [
            'data' => $coursRepository->findAll(),
        ]);

        $dompdf = new Dompdf();
        $dompdf->loadHtml($html);
        $dompdf->render();

        $pdfOutput = $dompdf->output();

        return new Response($pdfOutput, 200, [
            'Content-Type' => 'application/pdf'
        ]);
    }









    #[Route('/new', name: 'app_cours_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CoursRepository $coursRepository, SluggerInterface $slugger): Response
    {
        $cour = new Cours();
        $form = $this->createForm(CoursType::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $coursRepository->save($cour, true);




            return $this->redirectToRoute('app_cours_index', [], Response::HTTP_SEE_OTHER);
        }
        return $this->renderForm('cours/new.html.twig', [
            'cour' => $cour,
            'form' => $form,
        ]);
    }


    #[Route('/{id}', name: 'app_cours_show', methods: ['GET'])]
    public function show(Cours $cour): Response
    {
        return $this->render('cours/show.html.twig', [
            'cour' => $cour,
        ]);
    }
    //route vers la page du cours
    #[Route('/{id}/detail', name: 'detail', methods: ['GET'])]
    public function show_detail(Cours $cour): Response
    {
        return $this->render('blog-article.html.twig', [
            'cour' => $cour,
        ]);
    }



    #[Route('/{id}/edit', name: 'app_cours_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Cours $cour, CoursRepository $coursRepository): Response
    {
        $form = $this->createForm(CoursType::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $coursRepository->save($cour, true);

            return $this->redirectToRoute('app_cours_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('cours/edit.html.twig', [
            'cour' => $cour,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_cours_delete', methods: ['POST'])]
    public function delete(Request $request, Cours $cour, CoursRepository $coursRepository): Response
    {
        if ($this->isCsrfTokenValid('delete' . $cour->getId(), $request->request->get('_token'))) {
            $coursRepository->remove($cour, true);
        }

        return $this->redirectToRoute('app_cours_index', [], Response::HTTP_SEE_OTHER);
    }
}
