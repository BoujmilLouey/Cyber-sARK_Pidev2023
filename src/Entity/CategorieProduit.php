<?php

namespace App\Entity;
use App\Entity\Produit;
use App\Repository\CategorieProduitRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
#[ORM\Entity(repositoryClass: CategorieProduitRepository::class)]
class CategorieProduit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank (message:"Please enter product Type")]
    #[Assert\Length (min:2 , max:30, minMessage:"Your product Type must be at least 2 caracteres", maxMessage:"Your product Type characters max is 30")]
    private ?string $type = null;

    #[ORM\Column(length: 255)] 
    #[Assert\NotBlank (message:"Please enter product Reference")]
    #[Assert\Length (min:2 , max:30, minMessage:"Your product reference must be at least 2 caracteres", maxMessage:"Your product reference characters max is 30")]
    private ?string $reference = null;

    #[ORM\OneToMany(mappedBy: 'id_categorie_produit', targetEntity: Produit::class)]
    private Collection $produit;

    #[ORM\OneToMany(mappedBy: 'categorie', targetEntity: Produit::class)]
    private Collection $produits;

    public function __construct()
    {
        $this->produits = new ArrayCollection();
        $this->produit = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

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

    /**
     * @return Collection<int, Produit>
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    
    
    public function __toString()
{
    return $this->type;
}

   

    public function addProduits(Produit $produits): self
    {
        if (!$this->produits->contains($produits)) {
            $this->produits->add($produits);
            $produits->setCategorie($this);
        }

        return $this;
    }

    public function removeProduits(Produit $produits): self
    {
        if ($this->produits->removeElement($produits)) {
            // set the owning side to null (unless already changed)
            if ($produits->getCategorie() === $this) {
                $produits->setCategorie(null);
            }
        }

        return $this;
    }
}
