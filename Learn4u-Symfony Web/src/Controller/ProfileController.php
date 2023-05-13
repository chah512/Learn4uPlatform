<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\ProfileType;
use App\Form\UserType;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Address;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ProfileController extends AbstractController
{
    /**
     * @Route("/profile", name="app_profile")
     */
    public function index(UserRepository $usrRep): Response
    {
        $user=$this->getUser()->getUsername();
        $currentuser=$usrRep->findOneBy(array('Username'=>$user));
        return $this->render('profile/index.html.twig', [
            'user' => $currentuser,
        ]);
    }
    /**
     * @Route("/Userjson", name="Userjson")
     */
    public function Allusers(Request $request,NormalizerInterface $Normalizer )
    {
        //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->findAll();

        //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
        //en tableau d'objet user
        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'user']);


        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/banjson", name="banjson")
     */
    public function banjson(Request $request): Response
    {
        //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->find($request->get('id'));

        $user->setIsBanned(true);
        $em->flush();


        return new Response("banned");

    }
    /**
     * @Route("/unbanjson", name="unbanjson")
     */
    public function unbanjson(Request $request): Response
    {
        //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->find($request->get('id'));

        $user->setIsBanned(false);
        $em->flush();


        return new Response("Unbanned");

    }

    /**
     * @Route("/editJSON", name="app_editJSON")
     */
    public function editJSON(Request $request,UserRepository $rep, UserPasswordEncoderInterface $userPasswordEncoder, NormalizerInterface $normalizer, EntityManagerInterface $entityManager): Response
    {
        $user = $rep->find($request->get('id'));
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
        $roles[]=$request->get('role');
        $user->setRoles($roles);
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


        $entityManager->flush();

        // generate a signed url and email it to the user
        #MAILER



        //$jsonContent=$normalizer->normalize($user,'json',['groups'=>'post:read']);
        return new JsonResponse('Account Created',200);
    }

    /**
     * @Route("/addJSON", name="app_addJSON")
     */
    public function addJSON(Request $request,UserRepository $rep, UserPasswordEncoderInterface $userPasswordEncoder, NormalizerInterface $normalizer, EntityManagerInterface $entityManager): Response
    {
        $user = new User();
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
        $roles[]=$request->get('role');
        $user->setRoles($roles);
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



        //$jsonContent=$normalizer->normalize($user,'json',['groups'=>'post:read']);
        return new JsonResponse('Account Created',200);
    }




    /**
     * @Route("/{id}/editaccount", name="user_editaccount", methods={"GET", "POST"})
     */
    public function editaccount(Request $request,UserPasswordEncoderInterface $userPasswordEncoder, User $user, EntityManagerInterface $entityManager): Response
    {   $usersave=$user;
        $form = $this->createForm(ProfileType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $imageFile = $form->get('Photo')->getData();

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
                else {
                    $user->setPhoto($usersave->getPhoto());
                }
            $user->setRoles($usersave->getRoles());
            $user->setEmail($usersave->getEmail());
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );

            $entityManager->flush();

            return $this->redirectToRoute('app_profile', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('profile/editaccount.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/editpassword", name="user_editpassword")
     */
    public function editpassword(Request $request,UserPasswordEncoderInterface $userPasswordEncoder, UserRepository $repository, EntityManagerInterface $entityManager): Response
    {


        $user = $repository->find($request->get('id'));

        $user->setPassword(
            $userPasswordEncoder->encodePassword(
                $user,
                $request->get('Password')
            )
        );

        $entityManager->flush();

        return new Response("pwd changed");
    }

    /**
     * @Route("/editfullname", name="user_editfullname")
     */
    public function editfullname(Request $request,UserRepository $repository, EntityManagerInterface $entityManager): Response
    {


        $user = $repository->find($request->get('id'));

        $user->setFullname($request->get('Fullname'));

        $entityManager->flush();

        return new Response("pwd changed");
    }

    /**
     * @Route("/editphoto", name="user_editphoto")
     */
    public function editphoto(Request $request,UserRepository $repository, EntityManagerInterface $entityManager): Response
    {
        $user = $repository->find($request->get('id'));

        $imageFile =$request->get('Photo');

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

        $entityManager->flush();

        return new Response("photo changed");
    }


}
