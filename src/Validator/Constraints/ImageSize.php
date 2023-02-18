<?php

namespace App\Validator\Constraints;

use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 */
class ImageSize extends Constraint
{
    public $message = 'The image dimensions must be between {{ min_width }}x{{ min_height }} and {{ max_width }}x{{ max_height }}.';
    public $minWidth;
    public $minHeight;
    public $maxWidth;
    public $maxHeight;



    public function getTargets()
    {
        return self::CLASS_CONSTRAINT;
    }
}
