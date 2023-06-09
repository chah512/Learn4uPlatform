<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BaseController extends AbstractController
{
    /**
     * @Route("/", name="base")
     */
    public function index(): Response
    {
        return $this->render('base_FRONT.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }
    /**
     * @Route("/admin", name="admin")
     */
    public function index3(): Response
    {
        return $this->render('base_test.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }
    /**
     * @Route("/resetpwd", name="app_resetpwd")
     */
    public function index2(): Response
    {
        return $this->render('reset_password/rstpwd.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }
    /**
     * @Route("/admin", name="admin")
     */
    public function index4(): Response
    {
        return $this->render('base_BACK.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }
}
