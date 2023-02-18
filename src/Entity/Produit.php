<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\ProduitRepository;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\CategorieProduit;
#[ORM\Entity(repositoryClass: ProduitRepository::class)]
class Produit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank (message:"Please enter product name")]
   
    #[Assert\Length (min:2 , max:30, minMessage:"Your product name must be at least 2 caracteres", maxMessage:"Your product name characters max is 30")]
    private ?string $nom = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank (message:"Please enter product Reference")]
    #[Assert\Length (min:2 , max:30, minMessage:"Your product reference must be at least 2 caracteres", maxMessage:"Your product reference characters max is 30")]
    private ?string $reference = null;

    #[ORM\Column]
    private ?float $prix = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank (message:"Please enter product Color")]
   
    #[Assert\Length (min:2 , max:30, minMessage:"Your product color must be at least 2 caracteres", maxMessage:"Your product color characters max is 30")]
    private ?string $couleur = null;

    #[ORM\Column]
    #[Assert\NotBlank (message:"Please enter product weight")]
    
    private ?float $poids = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank (message:"Please enter product description")]
    private ?string $description = null;

    #[ORM\ManyToOne(inversedBy: 'produits')]
    private ?categorieproduit $id_categorie_produit = null;

    #[ORM\Column(length: 255)]
    private ?string $image = null;

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

    public function getReference(): ?string
    {
        return $this->reference;
    }

    public function setReference(string $reference): self
    {
        $this->reference = $reference;

        return $this;
    }
    
    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getCouleur(): ?string
    {
        return $this->couleur;
    }

    public function setCouleur(string $couleur): self
    {
        $this->couleur = $couleur;

        return $this;
    }

    public function getPoids(): ?float
    {
        return $this->poids;
    }

    public function setPoids(float $poids): self
    {
        $this->poids = $poids;

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
    
    public function getIdCategorieProduit(): ?categorieproduit
    {
        return $this->id_categorie_produit;
    }

    public function setIdCategorieProduit(?CategorieProduit $id_categorie_produit): self
    {
        $this->id_categorie_produit = $id_categorie_produit;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }
}
