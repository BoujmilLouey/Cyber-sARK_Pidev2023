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
    #[Assert\Length(min:4,minMessage:"Veuillez entrer un nom de minimum 4 caractères")]
    #[Assert\NotBlank(message:"Le nom est requis")]
    private ?string $name = null;   

    #[ORM\Column(length: 255)]
    #[Assert\Length(min:5,minMessage:"Veuillez entrer un commentaire de minimum 5 caractères")]
    private ?string $description = null;


    #[ORM\Column(length: 500, nullable: true)]
    private ?string $image = null;

   

    #[ORM\ManyToOne(targetEntity: self::class, inversedBy: 'games')]
    private ?self $gameCategory = null;

    #[ORM\OneToMany(mappedBy: 'gameCategory', targetEntity: self::class)]
    private Collection $games;


    
    #[ORM\ManyToOne(inversedBy: 'games')]
    private ?GameCategory $gameCategorie = null;





    public function __construct()
    {
        $this->games = new ArrayCollection();
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
    
   
}
