<?php

namespace App\Form;

use App\Entity\Game;
use App\Entity\GameCategory;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Validator\Constraints\NotBlank;

class GameType extends AbstractType
{


    

    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('name',null,['attr'=>[
                'class'=>'classExample'
            ]], [
                'constraints' => [
                    new NotBlank(['message' => 'Le nom du joueurs est obligatoire.']),
                    
                ]
            ])
            
            
            ->add('image',FileType::class,

            ['label'=>'Image',
             'mapped'=>false,
             'required'=>true,
             'constraints' => [
                    new File([
                        'maxSize' => '1024k',
                        'mimeTypes' => [
                            'image/*',
                        ],
                        'mimeTypesMessage' => 'Please upload a valid Image',
                    ])
                ],
            ])
            ->add('description')

            ->add('gameCategorie',EntityType::class,[
                'class'=>GameCategory::class,
                'choice_label'=>'name'
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Game::class,
            'validation_groups' => ['Default', 'my_validation_group'],
        ]);
        
    }
}
