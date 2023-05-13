<?php

namespace App\Repository;

use App\Entity\Offres;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;



/**
 * @method Offres|null find($id, $lockMode = null, $lockVersion = null)
 * @method Offres|null findOneBy(array $criteria, array $orderBy = null)
 * @method Offres[]    findAll()
 * @method Offres[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class OffresRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Offres::class);
    }

    // /**
    //  * @return Offres[] Returns an array of Offres objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('o.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Offres
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function findOffrebytitle($title)
    {
        return $this->createQueryBuilder('e')
        ->where('e.titre LIKE :titre')
            ->setParameter('titre', '%'.$title.'%')
            ->getQuery()
            ->getResult();
    }



    public function findByPrix2()
    {
        return $this->createQueryBuilder('Offre')
            ->orderBy('Offre.prix','ASC')
            ->getQuery()
            ->getResult()
            ;
    }

    public function findByPrix()
    {
        return $this->createQueryBuilder('Offre')
            ->orderBy('Offre.prix','DESC')
            ->getQuery()
            ->getResult()
            ;
    }

    public function findByTitre2()
    {
        return $this->createQueryBuilder('Offre')
            ->orderBy('Offre.titre','ASC')
            ->getQuery()
            ->getResult()
            ;
    }

    public function findByTitre()
    {
        return $this->createQueryBuilder('Offre')
            ->orderBy('Offre.titre','DESC')
            ->getQuery()
            ->getResult()
            ;
    }


}
