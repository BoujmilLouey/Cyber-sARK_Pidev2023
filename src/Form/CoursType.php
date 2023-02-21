<?php

namespace App\Form;

use App\Entity\Cours;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Validator\Constraints as Assert;



class CoursType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom', TextType::class, [
                'label' => 'nom',
                'constraints' => [
                    new Assert\Length([
                        'min' => 1,
                        'max' => 50,
                        'minMessage' => 'Le texte doit contenir au moins {{ 1 }} caractères.',
                        'maxMessage' => 'Le texte ne peut pas contenir plus de {{ 50 }} caractères.',
                    ]),
                    new Assert\Regex([
                        'pattern' => '/^(?=.*[a-z])(?=.*[A-Z])/',
                        'message' => 'Le texte doit contenir des lettres majuscules et minuscules.',
                    ]),
                ],
            ])
            ->add('description', TextType::class, [
                'label' => 'description',
                'constraints' => [
                    new Assert\Length([
                        'min' => 10,
                        'max' => 255,
                        'minMessage' => 'Le texte doit contenir au moins {{ 10 }} caractères.',
                        'maxMessage' => 'Le texte ne peut pas contenir plus de {{ 255 }} caractères.',
                    ]),
                    new Assert\Regex([
                        'pattern' => '/^(?=.*[a-z])(?=.*[A-Z])/',
                        'message' => 'Le texte doit contenir des lettres majuscules et minuscules.',
                    ]),
                ],
            ])
            ->add('note', IntegerType::class, [
                'label' => 'note',
                'constraints' => [
                    new Assert\Range([
                        'min' => 1,
                        'max' => 10,
                        'minMessage' => 'Le nombre doit être supérieur ou égal à {{ 1 }}.',
                        'maxMessage' => 'Le nombre doit être inférieur ou égal à {{ 10 }}.',
                    ]),
                ],
            ])

            ->add('video', FileType::class, [
                /* 'label' => 'cours (video file)',

                // unmapped means that this field is not associated to any entity property
                'mapped' => false,

                // make it optional so you don't have to re-upload the PDF file
                // every time you edit the Product details
                'required' => false,

                // unmapped fields can't define their validation using annotations
                // in the associated entity, so you can use the PHP constraint classes
                'constraints' => [
                    new File([
                        //'maxSize' => '1024k',
                        //'mimeTypes' => [
                        //  'video/quicktime',
                        //'video/x-ms-wmv',
                        //],
                        'mimeTypesMessage' => 'Please upload a valid video document',
                    ])
                ],*/])




            ->add('pdf', FileType::class, [
                'label' => 'cours (PDF file)',
                /*
                // unmapped means that this field is not associated to any entity property
                'mapped' => false,

                // make it optional so you don't have to re-upload the PDF file
                // every time you edit the Product details
                'required' => false,

                // unmapped fields can't define their validation using annotations
                // in the associated entity, so you can use the PHP constraint classes
                'constraints' => [
                    new File([
                        //'maxSize' => '1024k',
                        'mimeTypes' => [
                            'application/pdf',
                            'application/x-pdf',
                        ],
                        'mimeTypesMessage' => 'Please upload a valid PDF document',
                        
                    ])
                ],*/
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cours::class,
        ]);
    }
}
