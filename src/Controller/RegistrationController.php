<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\RegistrationFormType;
use App\Security\EmailVerifier;
use App\Security\UserAuthenticator;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Address;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Guard\GuardAuthenticatorHandler;
use SymfonyCasts\Bundle\VerifyEmail\Exception\VerifyEmailExceptionInterface;

class RegistrationController extends AbstractController
{
    private EmailVerifier $emailVerifier;

    public function __construct(EmailVerifier $emailVerifier)
    {
        $this->emailVerifier = $emailVerifier;
    }
    #[Route('/verify/email2', name: 'app_verify_email2')]
    public function verifyUserEmail(Request $request): Response
    {
        $this->denyAccessUnlessGranted('IS_AUTHENTICATED_FULLY');

        // validate email confirmation link, sets User::isVerified=true and persists
        try {
            $this->emailVerifier->handleEmailConfirmation($request, $this->getUser());
        } catch (VerifyEmailExceptionInterface $exception) {
            $this->addFlash('verify_email_error', $exception->getReason());

            return $this->redirectToRoute('register');
        }

        // @TODO Change the redirect on success and handle or remove the flash message in your templates
        $this->addFlash('success', 'Your email address has been verified.');

        return $this->redirectToRoute('home');
    }
    
    #[Route('/register', name: 'register')]
    public function register(Request $request, UserPasswordEncoderInterface $userPasswordEncoder, GuardAuthenticatorHandler $guardHandler, UserAuthenticator $authenticator, EntityManagerInterface $entityManager, EmailVerifier $emailVerifier): Response
    {
        $user = new User();
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // encode the plain password
            $user->setPassword(
            $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );

            $entityManager->persist($user);
            $entityManager->flush();

            $this->emailVerifier->sendEmailConfirmation('app_verify_email2', $user,
            (new TemplatedEmail())
                ->from(new Address('noreply.cyberark@gmail.com', 'CyberARK mail Bot'))
                ->to($user->getEmail())
                ->subject('Please Confirm your Email')
                ->htmlTemplate('registration/confirmation_email.html.twig')
        );


            return $guardHandler->authenticateUserAndHandleSuccess(
                $user,
                $request,
                $authenticator,
                'main' // firewall name in security.yaml
            );
        }

        return $this->render('registration/register.html.twig', [
            'registrationForm' => $form->createView(),
        ]);
    }
    #[Route('/signupjson', name: 'signupjson')]
    public function signup(Request $request, NormalizerInterface $Normalizer,UserPasswordEncoderInterface $userPasswordEncoder )
    {
        //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données

        //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
        //en tableau d'objet Students
        $em=$this->getDoctrine()->getManager();
        $user=new User();
        $user->setPassword( $userPasswordEncoder->encodePassword(
            $user,
            $request->get('plainPassword')->getData()
        )
        );

        $em->persist($user);
        $em->flush();
        $jsonContent=$Normalizer->normalize($user,'json',['groups'=>'user']);

        return new Response(json_encode($jsonContent));

    }
    #[Route('/registerjson', name: 'registerjson')]
    public function registerJSON(Request $request, UserPasswordEncoderInterface $userPasswordEncoder, NormalizerInterface $normalizer, EntityManagerInterface $entityManager): Response
    {
        $user = new User();



            /** @var UploadedFile $imageFile */
            $imageFile = $request->get('Photo');

            if($imageFile!=null) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();
                try {
                    $imageFile->move(
                        $this->getParameter('images_directory'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                $user->setPhoto($newFilename);
            }
            $user->setEmail($request->get('email'));
            $user->setFullname($request->get('fullname'));
            $user->setUsername($request->get('username'));
            $birthday=new \DateTime($request->get('birthday'));
            $user->setNaissance($birthday);
            // encode the plain password
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $request->get('plainPassword')
                )
            );

            $entityManager->persist($user);
            $entityManager->flush();

            // generate a signed url and email it to the user
            #MAILER

            $this->emailVerifier->sendEmailConfirmation('app_verify_email2', $user,
                (new TemplatedEmail())
                    ->from(new Address('noreply.learn4u@gmail.com', 'learn4u mail Bot'))
                    ->to($user->getEmail())
                    ->subject('Please Confirm your Email')
                    ->htmlTemplate('registration/confirmation_email.html.twig')
            );

       //$jsonContent=$normalizer->normalize($user,'json',['groups'=>'post:read']);
        return new JsonResponse('Account Created',200);
    }


    #[Route('/loginjson', name: 'loginjson')]
    public function loginJSON(Request $request,NormalizerInterface $serializer)
    {
        $email=$request->get('email');
        $password=$request->get('password');

        $em=$this->getDoctrine()->getManager();
        $user=$em->getRepository(User::class)->findOneBy(['email'=>$email]);
        if($user){
            if(password_verify($password,$user->getPassword())){
                //$serializer=new Serializer([new ObjectNormalizer()]);
                $formatted=$serializer->normalize($user);
                return new JsonResponse($formatted);
            }
            else{
                return new Response("password not found");
            }
        }else{
            return new Response("failed");
        }

    }
}
