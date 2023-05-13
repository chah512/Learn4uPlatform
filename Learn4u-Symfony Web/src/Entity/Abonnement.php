<?php

namespace App\Entity;

use JsonSerializable;
use App\Repository\AbonnementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=AbonnementRepository::class)
 */
class Abonnement implements JsonSerializable
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=Offres::class, inversedBy="abonnements")
     * @ORM\JoinColumn(nullable=false)
     */
    private $Offres;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $duree;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getOffres(): ?Offres
    {
        return $this->Offres;
    }

    public function setOffres(?Offres $Offres): self
    {
        $this->Offres = $Offres;

        return $this;
    }

    public function getDuree(): ?string
    {
        return $this->duree;
    }

    public function setDuree(string $duree): self
    {
        $this->duree = $duree;

        return $this;
    }

    public function jsonSerialize(): array
    {
        return array(
            'id' => $this->id,
            'offre' => $this->Offres,
            'duree' => $this->duree
        );
    }

    public function setUp($Offres, $duree)
    {
        $this->Offres = $Offres;
        $this->duree = $duree;
    }
}
