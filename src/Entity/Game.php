<?php

namespace App\Entity;

use ApiPlatform\Metadata\ApiResource;
use App\Repository\GameRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: GameRepository::class)]
class Game
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\Length(min: 4, minMessage: "Veuillez entrer un nom de minimum 4 caractères")]
    #[Assert\NotBlank(message: "Le nom est requis")]
    private ?string $name = null;

    #[ORM\Column(length: 255)]
    #[Assert\Length(min: 5, minMessage: "Veuillez entrer un commentaire de minimum 5 caractères")]
    private ?string $description = null;


    #[ORM\Column(length: 500, nullable: true)]
    private ?string $image = null;



    #[ORM\ManyToOne(targetEntity: self::class, inversedBy: 'games')]
    private ?self $gameCategory = null;

    #[ORM\OneToMany(mappedBy: 'gameCategory', targetEntity: self::class)]
    private Collection $games;



    #[ORM\ManyToOne(inversedBy: 'games')]
    private ?GameCategory $gameCategorie = null;


    #[ORM\OneToMany(mappedBy: 'game_id', targetEntity: Scores::class)]
    private Collection $score;

    #[ORM\OneToMany(mappedBy: 'game', targetEntity: GameRating::class)]
    private Collection $rating;



    public function __construct()
    {
        $this->games = new ArrayCollection();
        $this->score = new ArrayCollection();
        $this->rating = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

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

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }


    public function getGameCategorie(): ?GameCategory
    {
        return $this->gameCategorie;
    }

    public function setGameCategorie(?GameCategory $gameCategorie): self
    {
        $this->gameCategorie = $gameCategorie;

        return $this;
    }


    /**
     * @return Collection<int, Scores>
     */
    public function getScore(): Collection
    {
        return $this->score;
    }

    public function addScore(Scores $score): self
    {
        if (!$this->score->contains($score)) {
            $this->score->add($score);
            $score->setGameId($this);
        }

        return $this;
    }

    public function removeScore(Scores $score): self
    {
        if ($this->score->removeElement($score)) {
            // set the owning side to null (unless already changed)
            if ($score->getGameId() === $this) {
                $score->setGameId(null);
            }
        }

        return $this;
    }

    public function averageRating(): float
    {
        $sum = 0;
        $count = 0;

        foreach ($this->getRating() as $rating) {
            $sum += $rating->getRating();
            $count++;
        }

        if ($count > 0) {
            return $sum / $count;
        } else {
            return 0;
        }
    }

    /**
     * @return Collection<int, GameRating>
     */
    public function getRating(): Collection
    {
        return $this->rating;
    }

    public function addRating(GameRating $rating): self
    {
        if (!$this->rating->contains($rating)) {
            $this->rating->add($rating);
            $rating->setGame($this);
        }

        return $this;
    }

    public function removeRating(GameRating $rating): self
    {
        if ($this->rating->removeElement($rating)) {
            // set the owning side to null (unless already changed)
            if ($rating->getGame() === $this) {
                $rating->setGame(null);
            }
        }

        return $this;
    }
}
