<?php

namespace App\Controller;

use App\Entity\Avis;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class AvisMobileController extends AbstractController
{
    /**
     * @Route("/avis/mobile", name="app_avis_mobile")
     */
    public function index(): Response
    {
        return $this->render('avis_mobile/index.html.twig', [
            'controller_name' => 'AvisMobileController',
        ]);
    }
    /**
     * @Route("/Allavis", name="Allavis")
     */
    public function Allavis(Request $request,NormalizerInterface $Normalizer )
    {
        //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
        $em = $this->getDoctrine()->getManager();
        $avis = $em->getRepository(Avis::class)->findAll();

        //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
        //en tableau d'objet avis
        $jsonContent = $Normalizer->normalize($avis, 'json', ['groups' => 'post:read']);


        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/addavisJSON/new", name="addavisJSON")
     */
    public function addavisJSON(Request $request, NormalizerInterface $normalizer, EntityManagerInterface $entityManager): Response
    {
        $avis = new Avis();

        $avis->setRating($request->get('rating'));
        $avis->setTitle($request->get('title'));
        $avis->setCategory($request->get('category'));
        $avis->setContent($request->get('content'));

        $entityManager->persist($avis);
        $entityManager->flush();

        $jsonContent=$normalizer->normalize($avis,'json',['groups'=>'post:read']);
        return new Response("Rate Delete".json_encode($jsonContent));
    }

    /**
     * @Route("/deleteAvisJSON", name="deleteAvisJSON")
     */
    public function deleteAvisJSON(Request $request, NormalizerInterface $normalizer, $id): Response
    {
        $id=$request->get("id");
        $em = $this->getDoctrine()->getManager();

        $avis = $em->getRepository(Avis::class)->find($id);
        $em->remove($avis);
        $em->flush();

        $jsonContent=$normalizer->normalize($avis,'json',['groups'=>'post:read']);
        return new Response("Rate Delete".json_encode($jsonContent));
    }

}
