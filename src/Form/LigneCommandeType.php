<?php

namespace App\Form;

use App\Entity\LigneCommande;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Validator\Constraints\Length;



class LigneCommandeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('Quantite' ,IntegerType::class, [
                'required' => true,
                'constraints' => ['min' => 1,
                'minMessage' => 'Please Do not skip 10 products',
                // max length allowed by Symfony for security reasons
                'max' => 10,],
            ])
            ->add('Price')
            ->add('produit')
            ->add('commande')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => LigneCommande::class,
        ]);
    }
}
