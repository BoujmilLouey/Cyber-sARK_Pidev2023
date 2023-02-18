<?php

namespace App\Form;

use App\Entity\Produit;

use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class ProduitType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom',TextType::class, [
                'label' => 'Product Name',
                
            ])
            ->add('reference',null,[
                'label' => 'Reference'
            ])

            ->add('image', FileType::class, [
                'label' => 'Image (JPG, PNG or GIF file)',
               
            ])
            ->add('prix',NumberType::class, [
                'label' => 'Product Price',
                
            ])
            ->add('couleur',TextType::class, [
                'label' => 'Product Color',
                
                
               
            ])
              ->add('poids',NumberType::class, [
                'label' => 'Product Weight',

            ])
            ->add('description',TextType::class, [
                'label' => 'Product Description',
               
            ])
            ->add('id_categorie_produit',null,[
                'label' => 'Categorie'])
        ;


        
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit::class,
        ]);
    }
}
