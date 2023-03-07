<?php

namespace App\Controller;

use App\Entity\Scores;
use App\Form\ScoresType;
use App\Repository\ScoresRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/scores')]
class ScoresController extends AbstractController
{
    #[Route('/', name: 'app_scores_index', methods: ['GET'])]
    public function index(ScoresRepository $scoresRepository): Response
    {
        return $this->render('scores/index.html.twig', [
            'scores' => $scoresRepository->findAll(),
        ]);

        
    }

    #[Route('/new', name: 'app_scores_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ScoresRepository $scoresRepository): Response
    {
        $score = new Scores();
        $form = $this->createForm(ScoresType::class, $score);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $scoresRepository->save($score, true);

            return $this->redirectToRoute('app_scores_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scores/new.html.twig', [
            'score' => $score,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scores_show', methods: ['GET'])]
    public function show(Scores $score): Response
    {
        return $this->render('scores/show.html.twig', [
            'score' => $score,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_scores_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Scores $score, ScoresRepository $scoresRepository): Response
    {
        $form = $this->createForm(ScoresType::class, $score);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $scoresRepository->save($score, true);

            return $this->redirectToRoute('app_scores_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scores/edit.html.twig', [
            'score' => $score,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scores_delete', methods: ['POST'])]
    public function delete(Request $request, Scores $score, ScoresRepository $scoresRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$score->getId(), $request->request->get('_token'))) {
            $scoresRepository->remove($score, true);
        }

        return $this->redirectToRoute('app_scores_index', [], Response::HTTP_SEE_OTHER);
    }
}
