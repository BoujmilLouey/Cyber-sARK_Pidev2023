<?php

namespace App\Controller;


use App\Entity\GameRating;
use App\Form\GameRatingType;
use App\Entity\Game;
use App\Entity\Scores;
use App\Form\GameType;
use App\Repository\GameRepository;
use App\Repository\GameCategoryRepository;
use App\Repository\ScoresRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\SerializerInterface;

#[Route('/game')]

class GameController extends AbstractController
{
    /**
     * @Route("/play-game/{id}", name="play_game")
     */
    public function playGame($id, GameRepository $gameRepository, UserRepository $userRepository, ScoresRepository $scoresRepository): Response
    {


        $game = $gameRepository->find($id);
        $user = $userRepository->findAll()[0];


        $score_rand = mt_rand(0, 100);
        // Générer un score aléatoire entre 0 et 100
        // Enregistrer le score de l'utilisateur dans la base de données ou autre logique de traitement

        $score = new Scores();
        $score->setScore($score_rand);
        $score->setGameId($game);
        $score->setUserId($user);
        $scoresRepository->save($score, true);


        //  dd($score);


        return $this->render('game/result.html.twig', [
            'score' => $score_rand,
            'game' => $game


        ]);
    }

    /**
     * @Route("/games/{id}/rate", name="game_rate")
     */
    public function rate(Request $request, Game $game, EntityManagerInterface $entityManager): Response
    {
        $rating = new GameRating();
        $form = $this->createForm(GameRatingType::class, $rating);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $rating->setGame($game);
            $rating->setUser($this->getUser());
            $entityManager = $this->$entityManager()->getManager();
            $entityManager->persist($rating);
            $entityManager->flush();

            $this->addFlash('success', 'Votre note a été enregistrée.');

            return $this->redirectToRoute('game_show', ['id' => $game->getId()]);
        }

        return $this->render('game/rate.html.twig', [
            'game' => $game,
            'form' => $form->createView(),
        ]);
    }

 

    #[Route('/search', name: 'app_game_search', methods: ['POST'])]
    public function searchGame(gameRepository $gameRepository, Request $request, SerializerInterface $serializerInterface): Response
    {

        $data = $request->get('search');
        $game = $gameRepository->searchQB($data);


        $jsonData = json_decode(
            $serializerInterface->serialize(
                $game,
                'json',
                [
                    AbstractNormalizer::IGNORED_ATTRIBUTES => ['rating', 'gameCategory', 'score'],
                    AbstractNormalizer::CIRCULAR_REFERENCE_LIMIT => 1
                ]
            ),
            JSON_OBJECT_AS_ARRAY
        );
        return $this->json(['data' => $jsonData, 'html' => $this->renderView('game/game_items.html.twig', ['games' => $game])]);
    }

    #[Route('/', name: 'app_game_index', methods: ['GET'])]
    public function index(Request $request, GameRepository $gameRepository, GameCategoryRepository $gameCategoryRepository, PaginatorInterface $paginator, ScoresRepository $scoresRepository): Response
    {
        $data = $gameRepository->findAll();
        $scores = $scoresRepository->getHighestScores();
        $pagination = $paginator->paginate(
            $data,
            $request->query->getInt('page', 1),
            6 // limit per page
        );
        return $this->render('game/index.html.twig', [
            'games' => $pagination,
            'game_categories' => $gameCategoryRepository->findAll(),
            'scores' => $scores
        ]);
    }
    #[Route('/showALL', name: 'show_game_index', methods: ['GET'])]
    public function index2(GameRepository $gameRepository, GameCategoryRepository $gameCategoryRepository): Response
    {
        return $this->render('game/index_back.html.twig', [
            'games' => $gameRepository->findAll(),
            'game_categories' => $gameCategoryRepository->findAll(),

        ]);
    }

    # #[Route('/showallgames', name: 'show_gamee_index', methods: ['GET'])]
    # public function gamesInCategoryAction($categorySlug): Response
    # {
    #    $gameCategory = $this->getDoctrine()->getRepository(GameCategory::class)->findOneBy(['slug' => $categorySlug]);

    #     if (!$gameCategory) {
    #        throw $this->createNotFoundException('La catégorie n\'existe pas.');
    #    }

    # $games = $this->getDoctrine()->getRepository(Game::class)->findBy(['game_categories' => $gameCategory]);

    # return $this->render('game/index_back.html.twig', [
    #      'game_categories' => $gameCategory,
    #     'games' => $games,
    # ]);
    #} 



    #[Route('/new', name: 'app_game_new', methods: ['GET', 'POST'])]
    public function new(Request $request, GameRepository $gameRepository): Response
    {
        $game = new Game();
        $form = $this->createForm(GameType::class, $game);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form['image']->getData();
            if ($imageFile) {
                $fileName = md5(uniqid()) . '.' . $imageFile->guessExtension();
                $imageFile->move(
                    $this->getParameter('game_images_directory'),
                    $fileName
                );
                $game->setImage($fileName);
            }
            $gameRepository->save($game, true);

            return $this->redirectToRoute('app_game_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('game/new.html.twig', [
            'game' => $game,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_game_show', methods: ['GET'])]
    public function show(Game $game): Response
    {
        return $this->render('game/show.html.twig', [
            'game' => $game,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_game_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Game $game, GameRepository $gameRepository): Response
    {
        $form = $this->createForm(GameType::class, $game);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form['image']->getData();
            if ($imageFile) {
                $fileName = md5(uniqid()) . '.' . $imageFile->guessExtension();
                $imageFile->move(
                    $this->getParameter('game_images_directory'),
                    $fileName
                );
                $game->setImage($fileName);
            }
            $gameRepository->save($game, true);

            return $this->redirectToRoute('app_game_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('game/edit.html.twig', [
            'game' => $game,
            'form' => $form,
        ]);
    }




    #[Route('/{id}', name: 'app_game_delete', methods: ['POST'])]
    public function delete(Request $request, Game $game, GameRepository $gameRepository): Response
    {
        if ($this->isCsrfTokenValid('delete' . $game->getId(), $request->request->get('_token'))) {
            $gameRepository->remove($game, true);
        }

        return $this->redirectToRoute('app_game_index', [], Response::HTTP_SEE_OTHER);
    }




    public function paginator(Request $request, gameRepository $gameRepository, PaginatorInterface $paginator)
    {
        $data = $gameRepository->findAll();
        $pagination = $paginator->paginate(
            $data,
            $request->query->getInt('page', 1),
            6 // limit per page
        );

        return $this->render('game/index.html.twig', [
            'pagination' => $pagination,
        ]);
    }
}
