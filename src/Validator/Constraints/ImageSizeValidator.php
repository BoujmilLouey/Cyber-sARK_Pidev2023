<?php


namespace App\Validator\Constraints;

use Symfony\Component\Validator\Constraint;
use Symfony\Component\Validator\ConstraintValidator;

class ImageSizeValidator extends ConstraintValidator
{
    public function validate($value, Constraint $constraint)
    {
        if (!$value) {
            return;
        }

        $width = 0;
        $height = 0;

        if (is_string($value)) {
            // If the value is a string, it represents the path to the uploaded file.
            list($width, $height) = getimagesize($value);
        } elseif ($value instanceof \SplFileInfo) {
            // If the value is a SplFileInfo object, it represents the uploaded file.
            list($width, $height) = getimagesize($value->getPathname());
        }

        if ($width < $constraint->minWidth || $height < $constraint->minHeight ||
            $width > $constraint->maxWidth || $height > $constraint->maxHeight) {
            $this->context->buildViolation($constraint->message)
                ->setParameter('{{ min_width }}', $constraint->minWidth)
                ->setParameter('{{ min_height }}', $constraint->minHeight)
                ->setParameter('{{ max_width }}', $constraint->maxWidth)
                ->setParameter('{{ max_height }}', $constraint->maxHeight)
                ->addViolation();
        }
    }
}
