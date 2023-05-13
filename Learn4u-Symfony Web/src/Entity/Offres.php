<?php

namespace App\Entity;

use JsonSerializable;
use App\Repository\OffresRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\EquatableInterface;
use Symfony\Component\Validator\Context\ExecutionContextInterface;

/**
 * @ORM\Entity(repositoryClass=OffresRepository::class)
 */
class Offres implements JsonSerializable
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=5000)
     * @Groups("post:read")
     */
    private $image;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("post:read")
     */
    private $titre;

    /**
     * @ORM\Column(type="string", length=255)
     * @ORM\Column(type="string", length=100)
     * @Assert\Length(
     * * min = 5,
     * * max = 200,
     * * minMessage = "la description de l'offre doit comporter au moins {{ limit }} caractères",
     * * maxMessage = "la descrption de l'offre doit comporter au plus {{ limit }} caractères" * )
     * * @Assert\NotBlank( message="Ne doit pas être vide")
     * @Groups("post:read")
     */
    private $description;

    /**
     * @var integer
     * @ORM\Column(name="prix", type="float", nullable=true)
     * @Assert\GreaterThanOrEqual(
     *     message = "Le prix doit être supérieure à 0 dt.",
     *     value = 0
     * )
     * @ORM\Column(type="float")
     * @Groups("post:read")
     */
    private $prix;

    /**
     * @ORM\OneToMany(targetEntity=Abonnement::class, mappedBy="Offres", cascade={"all"},orphanRemoval=true)
     * @Groups("post:read")
     */
    private $abonnements;

    public function __construct()
    {
        $this->abonnements = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

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

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * @return Collection<int, Abonnement>
     */
    public function getAbonnements(): Collection
    {
        return $this->abonnements;
    }

    public function addAbonnement(Abonnement $abonnement): self
    {
        if (!$this->abonnements->contains($abonnement)) {
            $this->abonnements[] = $abonnement;
            $abonnement->setOffres($this);
        }

        return $this;
    }

    public function removeAbonnement(Abonnement $abonnement): self
    {
        if ($this->abonnements->removeElement($abonnement)) {
            // set the owning side to null (unless already changed)
            if ($abonnement->getOffres() === $this) {
                $abonnement->setOffres(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return $this->titre;
    }

    public function jsonSerialize(): array
    {
        return array(
            'id' => $this->id,
            'image' => $this->image,
            'titre' => $this->titre,
            'description' => $this->description,
            'prix' => $this->prix,
            'abonnements' => $this->abonnements,
        );
    }

    public function setUp($image, $titre, $description, int $prix)
    {
        $this->image = $image;
        $this->titre = $titre;
        $this->description = $description;
        $this->prix = $prix;
    }
}
