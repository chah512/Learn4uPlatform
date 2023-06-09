<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/user")
 */
class UserController extends AbstractController
{
    /**
     * @Route("/", name="user_index", methods={"GET"})
     */
    public function index(Request $request,PaginatorInterface $paginator,UserRepository $userRepository): Response
    {
        $data=$userRepository->findAll();
        $article=$paginator->paginate(
            $data,
            $request->query->getInt('page',1),
            4
        );
        return $this->render('user/index.html.twig', [
            'users' => $article,
        ]);
    }
    /**
     * @Route("/bannedusr", name="user_banned", methods={"GET"})
     */
    public function Bannedindex(Request $request,PaginatorInterface $paginator,UserRepository $userRepository): Response
    {

        $data=$userRepository->findBy(array('IsBanned'=>true),null,null,0);
        $article=$paginator->paginate(
            $data,
            $request->query->getInt('page',1),
            4
        );
        return $this->render('user/index.html.twig', [
            'users' => $article,
        ]);
    }

    /**
     * @Route("/adminusr", name="user_admin", methods={"GET"})
     */
    public function adminindex(Request $request,PaginatorInterface $paginator,UserRepository $userRepository): Response
    {

        $data=$userRepository->findBy(array('roles'=>["ROLE_USER"]),null,null,0);
        $article=$paginator->paginate(
            $data,
            $request->query->getInt('page',1),
            4
        );
        return $this->render('user/index.html.twig', [
            'users' => $article,
        ]);
    }

    /**
     * @Route("/admin/utilisateur/searchuser", name="utilsearchuser")
     */
    public function searchPlan(Request $request)
    {
        $repository = $this->getDoctrine()->getRepository(User::class);
        $requestString = $request->get('searchValue');
        $plan = $repository->findPlanBySujet($requestString);
        return $this->render('user/utilajax.html.twig', [
            'users' => $plan,
        ]);
    }

    /**
     * @Route("/new", name="user_new", methods={"GET", "POST"})
     */
    public function new(Request $request,UserPasswordEncoderInterface $userPasswordEncoder, EntityManagerInterface $entityManager): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);
        dump($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );

            $entityManager->persist($user);
            $entityManager->flush();

            return $this->redirectToRoute('user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/new.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="user_show", methods={"GET"})
     */
    public function show(User $user): Response
    {
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="user_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request,UserPasswordEncoderInterface $userPasswordEncoder, User $user, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );

            $entityManager->flush();

            return $this->redirectToRoute('user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/{id}/ban", name="user_ban", methods={"GET", "POST"})
     */
    public function ban(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('ban'.$user->getId(), $request->request->get('_token'))) {

            $user->setIsBanned(true);


            $entityManager->flush();


        }
        return $this->redirectToRoute('user_index', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/{id}/unban", name="user_unban", methods={"GET", "POST"})
     */
    public function unban(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('unban'.$user->getId(), $request->request->get('_token'))) {

            $user->setIsBanned(false);


            $entityManager->flush();


        }
        return $this->redirectToRoute('user_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/{id}", name="user_delete", methods={"POST"})
     */
    public function delete(Request $request,PaginatorInterface $paginator, User $user, EntityManagerInterface $entityManager,UserRepository $userRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getId(), $request->request->get('_token'))) {
            $entityManager->remove($user);
            $entityManager->flush();
        }
        $data=$userRepository->findAll();
        $article=$paginator->paginate(
            $data,
            $request->query->getInt('page',1),
            4
        );
        return $this->render('user/index.html.twig', [
            'users' => $article,
        ]);
    }
/*
    /**
     * @Route("/Userjson/{id}", name="Userjson/{id}")
     * @param Request $request
     * @param $id
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    /*public function Userid(Request $request,$id,NormalizerInterface $Normalizer )
    {
        //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->find($id);

        //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
        //en tableau d'objet user
        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'user']);


        return new Response(json_encode($jsonContent));

    }*/








}
