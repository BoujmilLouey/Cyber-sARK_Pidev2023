<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\IsTrue;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use App\Validator\Constraints\ImageSize;

class ProfileType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('fullname')
            ->add('email')
            ->add('username')
            ->add('image', FileType::class, [
                'label' => 'Image (JPG, PNG or GIF file)',
                'mapped' => false,
                'required' => true,
                'constraints' => [
                    new ImageSize([
                        'minWidth' => 100,
                        'minHeight' => 100,
                        'maxWidth' => 800,
                        'maxHeight' => 800,
                    ]),
                ],
            ])
            ->add('naissance',DateType::class, [
                'widget' => 'choice',
                'format' => 'dd-MM-yyyy',
                'years' => range(date('Y')-72, date('Y')-13),
                'months' => range(1, 12),
                'days' => range(1, 31),
            ]);
            
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
