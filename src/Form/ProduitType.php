<?php

namespace App\Form;
<<<<<<< Updated upstream

=======
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                
            ])
            ->add('reference',null,[
                'label' => 'Reference'
            ])

            ->add('image', FileType::class, [
                'label' => 'Image (JPG, PNG or GIF file)',
=======
                'required' => true,
                
                'constraints' => [
                    
                    new Length([
                        'min' => 2,
                        'max' => 50,
                    ]),
                ],
                'empty_data' => 'Default name',
            ])
            ->add('reference',TextType::class, [
                'label' => 'Product Reference',
                'required' => true,
                
                'constraints' => [
                    
                    new Length([
                        'min' => 3,
                        'max' => 50,
                    ]),
                ],
                'empty_data' => 'Default reference',
            ])
            ->add('image',FileType::class, [
                'label' => 'Image',
                'mapped' => false,
                'required' => false,
>>>>>>> Stashed changes
               
            ])
            ->add('prix',NumberType::class, [
                'label' => 'Product Price',
<<<<<<< Updated upstream
                
            ])
            ->add('couleur',TextType::class, [
                'label' => 'Product Color',
                
                
               
            ])
              ->add('poids',NumberType::class, [
                'label' => 'Product Weight',

            ])
            ->add('description',TextType::class, [
                'label' => 'Product Description',
               
=======
                'required' => false,
                'scale' => 2,
            ])
            ->add('couleur',TextType::class, [
                'label' => 'Product Color',
                'required' => true,
                
                'constraints' => [
                    
                    new Length([
                        'min' => 3,
                        'max' => 50,
                    ]),
                ],
                'empty_data' => 'Default Color',
            ])
            ->add('poids',NumberType::class, [
                'label' => 'Product Weight',
                'required' => false,
                'scale' => 2,
            ])
            ->add('description',TextType::class, [
                'label' => 'Product Color',
                'required' => true,
                
                'constraints' => [
                    
                    new Length([
                        'min' => 3,
                        'max' => 50,
                    ]),
                ],
                'empty_data' => 'Default Decription',
>>>>>>> Stashed changes
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
