<?php

namespace App\Repository;

use App\Entity\User;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<User>
 *
 * @method User|null find($id, $lockMode = null, $lockVersion = null)
 * @method User|null findOneBy(array $criteria, array $orderBy = null)
 * @method User[]    findAll()
 * @method User[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class UserRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, User::class);
    }

    public function save(User $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(User $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

//    /**
//     * @return User[] Returns an array of User objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('u')
//            ->andWhere('u.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('u.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?User
//    {
//        return $this->createQueryBuilder('u')
//            ->andWhere('u.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
public function orderByMail()
{
    return $this->createQueryBuilder('s')
        ->orderBy('s.email', 'ASC')
        ->getQuery()->getResult();
}
public function orderByUsername()
{
    return $this->createQueryBuilder('s')
        ->orderBy('s.username', 'ASC')
        ->getQuery()->getResult();
}
public function findVerifiedUser(){

    $qb= $this->createQueryBuilder('s');
    $qb ->where('s.isVerified=:isVerified');
    $qb->setParameter('isVerified',true);
    return $qb->getQuery()->getResult();
}
public function findBannedUser(){

    $qb= $this->createQueryBuilder('s');
    $qb ->where('s.isBanned=:isBanned');
    $qb->setParameter('isBanned',true);
    return $qb->getQuery()->getResult();
}
}
