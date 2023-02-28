<?php
namespace App\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;

class SearchType extends AbstractType
{
public function buildForm(FormBuilderInterface $builder, array $options)
{
    $builder
        ->add('query', TextType::class)
        ->add('category', ChoiceType::class, [
            'choices' => [
                'Category 1' => 'category1',
                'Category 2' => 'category2',
                'Category 3' => 'category3',
            ],
            'placeholder' => 'Select a category',
            'required' => false,
        ]);
}}
