<?php

namespace App\Entity;

use App\Repository\ClassementRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ClassementRepository::class)]
class Classement
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?float $score = null;

    #[ORM\OneToOne(cascade: ['persist', 'remove'])]
    private ?user $id_user = null;

    #[ORM\OneToOne(cascade: ['persist', 'remove'])]
    private ?jeux $id_jeux = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getScore(): ?float
    {
        return $this->score;
    }

    public function setScore(float $score): self
    {
        $this->score = $score;

        return $this;
    }

    public function getIdUser(): ?user
    {
        return $this->id_user;
    }

    public function setIdUser(?user $id_user): self
    {
        $this->id_user = $id_user;

        return $this;
    }

    public function getIdJeux(): ?jeux
    {
        return $this->id_jeux;
    }

    public function setIdJeux(?jeux $id_jeux): self
    {
        $this->id_jeux = $id_jeux;

        return $this;
    }
}
