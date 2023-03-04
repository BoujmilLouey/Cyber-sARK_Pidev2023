<?php

namespace App\Form;

use App\Entity\Cours;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\File;

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
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cours::class,
        ]);
    }
}