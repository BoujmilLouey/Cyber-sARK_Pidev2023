<?php

namespace App\Entity;

use App\Repository\GameRatingRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: GameRatingRepository::class)]
class GameRating
{
     /**
     * @ORM\ManyToOne(targetEntity=Game::class)
     * @ORM\JoinColumn(nullable=false)
     */

     private $game;

     public function getGame(): ?Game
     {
         return $this->game;
     }
 
     public function setGame(?Game $game): self
     {
         $this->game = $game;
 
         return $this;
     }


      /**
     * @ORM\ManyToOne(targetEntity=User::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $user;

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?int $rating = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getRating(): ?int
    {
        return $this->rating;
    }

    public function setRating(int $rating): self
    {
        $this->rating = $rating;    

        return $this;
    }
}
