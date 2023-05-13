<?php

namespace App\Controller;

use App\Entity\Offres;
use App\Repository\OffresRepository;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("mobile/offre")
 */
class OffresMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(OffresRepository $offreRepository,NormalizerInterface $Normalizer): Response
    {
        $offres = $offreRepository->findAll();

        $jsonContent = $Normalizer->normalize($offres, 'json', ['groups' => 'post:read']);


        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/show", methods={"POST"})
     */
    public function show(Request $request, OffresRepository $offreRepository,NormalizerInterface $Normalizer): Response
    {
        $offre = $offreRepository->find((int)$request->get("id"));

        $jsonContent = $Normalizer->normalize($offre, 'json', ['groups' => 'post:read']);


        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/add")
     */
    public function add(Request $request,EntityManagerInterface $em): Response
    {
        $offre = new Offres();
        $offre->setTitre($request->get('titre'));
        $offre->setDescription($request->get('description'));
        $offre->setPrix($request->get('prix'));
        $em->persist($offre);
        $em->flush();
        return new Response('done');
    }

    /**
     * @Route("/edit", methods={"POST"})
     */
    public function edit(Request $request, OffresRepository $offreRepository,NormalizerInterface $Normalizer): Response
    {
        $offre = $offreRepository->find((int)$request->get("id"));

        $jsonContent = $Normalizer->normalize($offre, 'json', ['groups' => 'post:read']);


        return new Response(json_encode($jsonContent));
    }

    public function manage($offre, $request): JsonResponse
    {
        $file = $request->files->get("file");
        if ($file) {
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();

            try {
                $file->move($this->getParameter('photos_directory'), $fileName);
            } catch (FileException $e) {
                dd($e);
            }
        } else {
            if ($request->get("image")) {
                $fileName = $request->get("image");
            } else {
                $fileName = "null";
            }
        }

        $offre->setUp(
            $fileName,
            $request->get("titre"),
            $request->get("description"),
            (float)$request->get("prix")
        );

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($offre);
        $entityManager->flush();

        return new JsonResponse($offre, 200);
    }

    /**
     * @Route("/delete", methods={"POST"})
     */
    public function delete(Request $request, EntityManagerInterface $entityManager, OffresRepository $offreRepository): Response
    {
        $offre = $offreRepository->find((int)$request->get("id"));

        if (!$offre) {
            return new JsonResponse(null, 200);
        }

        $entityManager->remove($offre);
        $entityManager->flush();

        return new Response('done');
    }

    /**
     * @Route("/deleteAll", methods={"POST"})
     */
    public function deleteAll(EntityManagerInterface $entityManager, OffresRepository $offreRepository): Response
    {
        $offres = $offreRepository->findAll();

        foreach ($offres as $offre) {
            $entityManager->remove($offre);
            $entityManager->flush();
        }

        return new Response('done');
    }

    /**
     * @Route("/image/{image}", methods={"GET"})
     */
    public function getPicture(Request $request): BinaryFileResponse
    {
        return new BinaryFileResponse(
            $this->getParameter('photos_directory') . "/" . $request->get("image")
        );
    }
}
