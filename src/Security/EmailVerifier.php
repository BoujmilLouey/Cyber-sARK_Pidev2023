<?php

namespace App\Security;

use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Security\Core\User\UserInterface;
use SymfonyCasts\Bundle\VerifyEmail\Exception\VerifyEmailExceptionInterface;
use SymfonyCasts\Bundle\VerifyEmail\VerifyEmailHelperInterface;
use Symfony\Component\Mailer\Transport\Smtp\SmtpTransport;

use Symfony\Component\Mime\Email;
use Symfony\Component\Mime\Address;

class EmailVerifier
{
    private $mailer;
    private $verifyEmailHelper;
    private $emailSender;

    public function __construct(MailerInterface $mailer, VerifyEmailHelperInterface $helper)
    {
        $this->mailer = $mailer;
        $this->verifyEmailHelper = $helper;
        
    }

    public function sendEmailConfirmation(string $verifyEmailRouteName, UserInterface $user, Email $email): void
    {
        $signatureComponents = $this->verifyEmailHelper->generateSignature(
            $verifyEmailRouteName,
            $user->getId(),
            $user->getEmail()
        );

        $context = $email->getContext();
        $context['signedUrl'] = $signatureComponents->getSignedUrl();
        $context['expiresAtMessageKey'] = $signatureComponents->getExpirationMessageKey();
        $context['expiresAtMessageData'] = $signatureComponents->getExpirationMessageData();

        $email->context($context);
        $email->from(new Address('louey.boujmil@esprit.tn'))
              ->to(new Address($user->getEmail()));

        $transport = new SmtpTransport('smtp.gmail.com', 587);
        $transport->setUsername('louey.boujmil');
        $transport->setPassword('Loulou_b_2100');

        $this->mailer = new Mailer($transport);

        $this->mailer->send($email);
    }

    /**
     * @throws VerifyEmailExceptionInterface
     */
    public function handleEmailConfirmation(Request $request, UserInterface $user): void
    {
        $this->verifyEmailHelper->validateEmailConfirmation($request->getUri(), $user->getId(), $user->getEmail());

        $user->setIsVerified(true);

        $this->entityManager->persist($user);
        $this->entityManager->flush();
    }
}
