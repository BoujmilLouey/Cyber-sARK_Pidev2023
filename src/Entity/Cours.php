<?php

namespace App\Entity;

use App\Repository\CoursRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CoursRepository::class)]
class Cours
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $nom = null;

    #[ORM\Column(length: 255)]
    private ?string $description = null;

    #[ORM\Column]
    private ?int $note = null;

    #[ORM\Column(length: 255)]
    private ?string $video = null;

    #[ORM\Column(length: 255)]
    private ?string $pdf = null;

    #[ORM\OneToMany(mappedBy: 'cours', targetEntity: commentaire::class)]
    private Collection $id_commentaire;

    public function __construct()
    {
        $this->id_commentaire = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

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

    public function getVideo(): ?string
    {
        return $this->video;
    }

    public function setVideo(string $video): self
    {
        $this->video = $video;

        return $this;
    }

    public function getPdf(): ?string
    {
        return $this->pdf;
    }

    public function setPdf(string $pdf): self
    {
        $this->pdf = $pdf;

        return $this;
    }

    /**
     * @return Collection<int, commentaire>
     */
    public function getIdCommentaire(): Collection
    {
        return $this->id_commentaire;
    }

    public function addIdCommentaire(commentaire $idCommentaire): self
    {
        if (!$this->id_commentaire->contains($idCommentaire)) {
            $this->id_commentaire->add($idCommentaire);
            $idCommentaire->setCours($this);
        }

        return $this;
    }

    public function removeIdCommentaire(commentaire $idCommentaire): self
    {
        if ($this->id_commentaire->removeElement($idCommentaire)) {
            // set the owning side to null (unless already changed)
            if ($idCommentaire->getCours() === $this) {
                $idCommentaire->setCours(null);
            }
        }

        return $this;
    }
}
