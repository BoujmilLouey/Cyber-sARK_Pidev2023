<?php

namespace App\Entity;

use App\Repository\RatingRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: RatingRepository::class)]
class Rating
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\OneToOne(cascade: ['persist', 'remove'])]
    private ?game $score = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getScore(): ?game
    {
        return $this->score;
    }

    public function setScore(?game $score): self
    {
        $this->score = $score;

        return $this;
    }
}
