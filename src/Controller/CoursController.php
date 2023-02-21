<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Form\CoursType;
use App\Repository\CoursRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\public\uploads;

use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\HttpFoundation\FileBag;
use Doctrine\ORM\EntityManagerInterface;


#[Route('/cours')]
class CoursController extends AbstractController
{
    #[Route('/', name: 'app_cours_index', methods: ['GET'])]
    public function index(coursRepository $coursRepository): Response
    {
        return $this->render('cours/index.html.twig', [
            'cours' => $coursRepository->findAll(),
        ]);
    }


    #[Route('/front', name: 'app_cours1_index', methods: ['GET'])]
    public function index_front(CoursRepository $coursRepository): Response
    {
        return $this->render('cours/index_front.html.twig', [
            'cours' => $coursRepository->findAll(),
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
            //pdf start
            /*
            $pdf = $form->get('pdf')->getData();

            // this condition is needed because the 'brochure' field is not required
            // so the PDF file must be processed only when a file is uploaded
            if ($pdf) {
                $originalFilename = pathinfo($pdf->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = $slugger->slug($originalFilename);
                $newFilename = $safeFilename . '-' . uniqid() . '.' . $pdf->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $pdf->move(
                        $this->getParameter('/uploads/pdf'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                // updates the 'pdfname' property to store the PDF file name
                // instead of its contents
                $cour->setPdf($newFilename);
            }

            //pdf end


            //video start

            $video = $form->get('video')->getData();

            // this condition is needed because the 'brochure' field is not required
            // so the video file must be processed only when a file is uploaded
            if ($video) {
                $originalFilename = pathinfo($video->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = $slugger->slug($originalFilename);
                $newFilename = $safeFilename . '-' . uniqid() . '.' . $video->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $video->move(
                        $this->getParameter('/uploads/video'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                // updates the 'videoname' property to store the video file name
                // instead of its contents
                $cour->setvideo($newFilename);
            }

            //video end
*/



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
        return $this->render('cours/blog-article.html.twig', [
            'cour' => $cour,
        ]);
    }
    //public function show_detail(EntityManagerInterface $em): Response
    //  {
    //      $coursRepository = $em->getRepository(Cours::class);
    //      $cour = $coursRepository->findOneBy([], ['id' => 'ASC']); // fetch the first Cours object from the database
    //
    //      return $this->render(
    //          'cours/blog-article.html.twig',
    //          [
    //              'cour' => $cour,
    //          ]
    //      );
    //}
    //fin de route 


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
