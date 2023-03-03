<?php

namespace App\Form;

use App\Entity\Commentaire;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;

class CommentaireType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('contenu', TextType::class, [
                'label' => 'Texte',
                'constraints' => [
                    new Assert\Length([
                        'min' => 10,
                        'max' => 1000,
                        'minMessage' => 'Le texte doit contenir au moins {{ limit }} caractères.',
                        'maxMessage' => 'Le texte ne peut pas contenir plus de {{ limit }} caractères.',
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
            #->add('id_cours')
            #->add('id_user')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Commentaire::class,
        ]);
    }
}
