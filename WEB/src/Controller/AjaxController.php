<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\User;
use Symfony\Component\HttpFoundation\JsonResponse;

class AjaxController extends AbstractController
{
    #[Route('/ajax/sort-users-by-roles', name: 'ajax_sort_users_by_roles')]
    public function sortUsersByRoles()
    {
        $userRepository = $this->getDoctrine()->getRepository(User::class);
        $users = $userRepository->findAll();

        // Sort the users by roles
        usort($users, function ($a, $b) {
            return strcmp(implode(',', $a->getRoles()), implode(',', $b->getRoles()));
        });

        // Convert the users to an array of data
        $usersData = [];
        foreach ($users as $user) {
            $usersData[] = [
                'id' => $user->getId(),
                'username' => $user->getUsername(),
                'email' => $user->getEmail(),
                'roles' => $user->getRoles(),
            ];
        }

        // Return the sorted users as a JSON response
        return new JsonResponse($usersData);
    }
}
