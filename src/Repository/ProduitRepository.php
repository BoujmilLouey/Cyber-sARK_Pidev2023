<?php

namespace App\Repository;

use App\Entity\CategorieProduit;
use App\Entity\Produit;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Produit>
 *
 * @method Produit|null find($id, $lockMode = null, $lockVersion = null)
 * @method Produit|null findOneBy(array $criteria, array $orderBy = null)
 * @method Produit[]    findAll()
 * @method Produit[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProduitRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Produit::class);
    }

    public function save(Produit $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Produit $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

//    /**
//     * @return Produit[] Returns an array of Produit objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('p.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Produit
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
public function searchProduit($nom)
{
    return $this->createQueryBuilder('s')
        ->andWhere('s.nom LIKE :nom')
        ->setParameter('nom', '%'.$nom.'%')
        ->getQuery()
        ->execute();    
}



            public function findByCategory($id)
            {
                return $this->createQueryBuilder('p')
                    ->join('p.categorie', 'c')
                    ->addSelect('c')
                    ->where('c.id=:id')
                    ->setParameter('id',$id)
                    ->getQuery()
                    ->getResult();
            }





            public function countByPrix(){
                        
                $query = $this->getEntityManager()->createQuery("
                    SELECT SUBSTRING(a.prix, 1, 10) as prix, COUNT(a) as count FROM App\Entity\Produit a GROUP BY prix
                ");
                
                
                return $query->getResult();
            }


            public function findByPriceAsc()
{
    return $this->createQueryBuilder('p')
        ->orderBy('p.prix', 'ASC')
        ->getQuery()
        ->getResult();
}

public function findByPriceDesc()
{
    return $this->createQueryBuilder('p')
        ->orderBy('p.prix', 'DESC')
        ->getQuery()
        ->getResult();
}




                }
            