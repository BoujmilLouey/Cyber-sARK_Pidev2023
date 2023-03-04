<?php

namespace App\Entity;

use App\Repository\CommentaireRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CommentaireRepository::class)]
class Commentaire
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    //#[ORM\ManyToOne]
    //#[ORM\JoinColumn(nullable: false)]
    //private ?Cours $id_cours = null;

    #[ORM\Column(length: 255)]
    private ?string $contenu = null;

    #[ORM\Column]
    private ?int $note = null;
    //
    // #[ORM\ManyToOne]
    // #[ORM\JoinColumn(nullable: false)]
    // private ?User $id_user = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    // public function getIdCours(): ?Cours
    // {
    //     return $this->id_cours;
    // }

    // public function setIdCours(?Cours $id_cours): self
    // {
    //     $this->id_cours = $id_cours;
    //
    //     return $this;
    // }

    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    public function setContenu(string $contenu): self
    {
        $this->contenu = $contenu;

        return $this;
    }

    public function getNote(): ?int
    {
        return $this->note;
    }

    public function setNote(int $note): self
    {
        $this->note = $note;

        return $this;
    }

    //public function getIdUser(): ?User
    //{
    //    return $this->id_user;
    //}
    //
    //public function setIdUser(?User $id_user): self
    //{
    //    $this->id_user = $id_user;
    //
    //    return $this;
    //}
}
