<?php

namespace App\Controller;

use App\Entity\Coursss;
use App\Entity\User;
use App\Form\CoursssType;
use App\Repository\CoursssRepository;
use App\Repository\FormmattionRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;



/**
 * @Route("/coursss")
 */
class CoursssController extends AbstractController
{


    /**
     * @Route("/delettt", name="delettt")
     *@Method("DELETE")
     */

    public function delJSON(Request $request) {
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $agence = $em->getRepository(Coursss::class)->find($id);
        if($agence!=null ) {
            $em->remove($agence);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Agence a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }else{
            return new JsonResponse("id agence invalide.");}


    }
    /**
     * @Route("/editer", name="editer")
     *
     */

    public function updateJSON(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $agence = $this->getDoctrine()->getManager()
            ->getRepository(Coursss::class)
            ->find($request->get("id"));
        $agence->setFormations($request->get("Formations"));
        $agence->setNom($request->get("nom"));
        $agence->setDescription($request->get("description"));

        // $agence->setVideo($request->get("Video"));

        $em->persist($agence);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($agence);
        return new JsonResponse("Agence a ete modifiee avec success.");

    }
    /**
     * @Route("/admin/agence/newJSON", name="agence_newJSON")
     * @Method("POST")
     */

    public function newJSON(Request $request,FormmattionRepository $repository)
    {
        $agence = new Coursss();
        $date = new \DateTime('now');
        //$formations = $request->query->get("Formations");
        $ag= $repository->find($request->get('formations'));
        $nom = $request->query->get("nom");
        $description = $request->query->get("description");
        $em = $this->getDoctrine()->getManager();


        //$agence->setFormations($formations);
        $agence->setDateajoutt($date);
        $agence->setFormations( $ag);
        $agence->setNom($nom);
        $agence->setDescription($description);

        $em->persist($agence);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($agence);
        return new JsonResponse($formatted);

    }




    /**
     * @Route("/admin/utilisateur/search", name="utilsearch")
     */
    public function searchPlanajax(Request $request)
    {
        $repository = $this->getDoctrine()->getRepository(Coursss::class);
        $requestString = $request->get('searchValue');
        $plan = $repository->findPlanBySujet($requestString);
        return $this->render('coursss/utilajax.html.twig', [
            'Coursss' => $plan,
        ]);
    }






    /**
     * @Route("/stat", name="stat_app")
     */
    public function stat(CoursssRepository $repo): Response
    {
        $categorys=$repo->findAll();

        $label=[];
        $count=[];
        foreach($categorys as $category ){
            $label[]=$category->getNom();
            $count[]=count((array)$category->getFormations());

        }
        return $this->render('coursss/stat.html.twig', [
            'label'=>json_encode($label),
            'count'=>json_encode($count),
        ]);
    }






    /**
     * @Route("/affichage_cours_frontt", name="affichage_cours_front", methods={"GET"})
     */
    public function affichage_cours_frontt(CoursssRepository $coursssRepository): Response
    {
        return $this->render('coursss/affichecours.html.twig', [
            'courssses' => $coursssRepository->findAll(),
        ]);
    }

    /**
     * @Route("/affichage_cours_front", name="affichage_cours_front", methods={"GET"})
     */
    public function affichage_cours_front(CoursssRepository $coursssRepository): Response
    {
        return $this->render('coursss/affichecours.html.twig', [
            'courssses' => $coursssRepository->findAll(),
        ]);
    }
/*
    public function AllCategorie(NormalizerInterface $Normalizer )
    {
        //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
        $repository =$this->getDoctrine()->getRepository(Coursss::class);
        $Formations=$repository->FindAll();
        //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
        //en tableau d'objet Students
        $jsonContent=$Normalizer->normalize( $Formations,'json',['groups'=>'post:read']);





        return new Response(json_encode($jsonContent));

    }
**/



    /**
     * @Route("/", name="coursss_index", methods={"GET"})
     */
    public function index(CoursssRepository $coursssRepository): Response
    {
        return $this->render('coursss/index.html.twig', [
            'courssses' => $coursssRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="coursss_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $coursss = new Coursss();
        $form = $this->createForm(CoursssType::class, $coursss);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($coursss);
            $entityManager->flush();

            return $this->redirectToRoute('coursss_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('coursss/new.html.twig', [
            'coursss' => $coursss,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="coursss_show", methods={"GET"})
     */
    public function show(Coursss $coursss): Response
    {
        return $this->render('coursss/show.html.twig', [
            'coursss' => $coursss,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="coursss_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Coursss $coursss, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CoursssType::class, $coursss);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('coursss_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('coursss/edit.html.twig', [
            'coursss' => $coursss,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="coursss_delete", methods={"POST"})
     */
    public function delete(Request $request, Coursss $coursss, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$coursss->getId(), $request->request->get('_token'))) {
            $entityManager->remove($coursss);
            $entityManager->flush();
        }

        return $this->redirectToRoute('coursss_index', [], Response::HTTP_SEE_OTHER);
    }
}
