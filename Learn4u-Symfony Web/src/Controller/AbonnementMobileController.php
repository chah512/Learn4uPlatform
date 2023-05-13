<?php

namespace App\Controller;

use App\Entity\Abonnement;
use App\Repository\AbonnementRepository;
use App\Repository\OffresRepository;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use SebastianBergmann\CodeCoverage\Report\Text;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Address;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/mobile/abonnement")
 */
class AbonnementMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(AbonnementRepository $abonnementRepository,NormalizerInterface $Normalizer ): Response
    {
        $abonnements = $abonnementRepository->findAll();

        $jsonContent = $Normalizer->normalize($abonnements, 'json', ['groups' => 'post:read']);


        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/show", methods={"POST"})
     */
    public function show(Request $request, AbonnementRepository $abonnementRepository): Response
    {
        $abonnement = $abonnementRepository->find((int)$request->get("id"));

        if ($abonnement) {
            return new JsonResponse($abonnement, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }

    /**
     * @Route("/add")
     */
    public function add(Request $request,EntityManagerInterface $em, OffresRepository $rep, MailerInterface $mailer): Response
    {
        $abonnement = new Abonnement();
        $abonnement->setDuree('duree');
        $offre=$rep->find($request->get('id'));
        $abonnement->setOffres($offre);
        $email = (new TemplatedEmail())
            ->from(new Address('noreply.learn4u@gmail.com', 'learn4u mail Bot'))
            ->to($request->get('email'))
            ->subject('Verification reservation')
            ->htmlTemplate('notification_reclamation/verificationreservmail.html.twig');
        $mailer->send($email);
        return new Response('done');
    }

    /**
     * @Route("/edit", methods={"POST"})
     */
    public function edit(Request $request, AbonnementRepository $abonnementRepository, OffresRepository $offreRepository): Response
    {
        $abonnement = $abonnementRepository->find((int)$request->get("id"));

        if (!$abonnement) {
            return new JsonResponse(null, 404);
        }

        return $this->manage($abonnement, $offreRepository, $request);
    }

    public function manage($abonnement, $offreRepository, $request): JsonResponse
    {
        $offre = $offreRepository->find((int)$request->get("offre"));
        if (!$offre) {
            return new JsonResponse("offre with id " . (int)$request->get("offre") . " does not exist", 203);
        }

        $abonnement->setUp(
            $offre,
            $request->get("duree")
        );

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($abonnement);
        $entityManager->flush();

        return new JsonResponse($abonnement, 200);
    }

    /**
     * @Route("/delete", methods={"POST"})
     */
    public function delete(Request $request, EntityManagerInterface $entityManager, AbonnementRepository $abonnementRepository): JsonResponse
    {
        $abonnement = $abonnementRepository->find((int)$request->get("id"));

        if (!$abonnement) {
            return new JsonResponse(null, 200);
        }

        $entityManager->remove($abonnement);
        $entityManager->flush();

        return new JsonResponse([], 200);
    }

    /**
     * @Route("/deleteAll", methods={"POST"})
     */
    public function deleteAll(EntityManagerInterface $entityManager, AbonnementRepository $abonnementRepository): Response
    {
        $abonnements = $abonnementRepository->findAll();

        foreach ($abonnements as $abonnement) {
            $entityManager->remove($abonnement);
            $entityManager->flush();
        }

        return new JsonResponse([], 200);
    }


}
