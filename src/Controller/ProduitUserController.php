<?php

namespace App\Controller;
use App\Form\RatingType;
use App\Entity\Produit;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\ProduitRepository;
use App\Repository\CategorieProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Entity\CategorieProduit;
use Doctrine\Persistence\ManagerRegistry;
use phpDocumentor\Reflection\PseudoTypes\True_;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Endroid\QrCodeBundle\Response\QrCodeResponse;
use Knp\Component\Pager\PaginatorInterface;


class ProduitUserController extends AbstractController

{




    #[Route('store/', name: 'app_store_index', methods: ['POST','GET'])]
    public function index(ProduitRepository $produitRepository ,Request $request,PaginatorInterface $paginator): Response
    {
        $query = $this->getDoctrine()->getRepository(Produit::class)->createQueryBuilder('u');

        $pagination = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),
            5 // items per page
        );
        $produits = $produitRepository->findAll();
        $triDescendant = false;
        $triAscendant = false;
        if ($request->getMethod() === 'POST') {
            $triDescendant = $request->request->get('tri_descendant');
            $triAscendant = $request->request->get('tri_ascendant');
        }

        // Si le tri descendant est sélectionné
        if ($triDescendant) {
            $produits = $produitRepository->findByPriceDesc();
            
        }
        // Si le tri ascendant est sélectionné
        else if ($triAscendant) {
            $produits = $produitRepository->findByPriceAsc();
        }

       return $this->render('produituser/index.html.twig', [
            'produits' => $produits,
            'tri_descendant' => $triDescendant,
            'tri_ascendant' => $triAscendant,
            'pagination' => $pagination,
        ]);
    }
    
 


    
    #[Route('/listbyCat/{id}', name: 'List_By_Categorie')]
    public function showClassroom(ProduitRepository $produitRepository,$id)
    {
        $categorie = $this->getDoctrine()->getRepository(CategorieProduit::class)->find($id);
        $produit= $produitRepository->findByCategory($categorie->getId());
        return $this->render('produituser/ProduitByCategorieUser.html.twig', [
            "categorie" => $categorie,
            "produit"=>$produit]);
    }




    #[Route('store/storshow/{id}', name: 'app_store_show', methods: ['GET'])]
    public function show(Produit $produit,ProduitRepository $produitRepository , CategorieProduitRepository $categorieProduitRepository,Request $request): Response
    {

        
        return $this->render('produituser/show.html.twig', [
            'produit' => $produit,
            'category' => $categorieProduitRepository->findAll(),
            

        ]);
    }
    



    #[Route('/upload', name: 'upload', methods: ['POST','GET'])]
public function upload(Request $request)
{
    // Handle the file upload
    $file = $request->files->get('file');
    if (!is_null($file)) {
        // Check if the uploaded file is of an allowed mimetype
        $allowedMimetypes = [
            'image/jpeg',
            'image/png',
            'text/plain',
            'application/pdf',
            'application/vnd.oasis.opendocument.text',
            'application/vnd.oasis.opendocument.spreadsheet',
            'application/vnd.oasis.opendocument.presentation',
            'application/vnd.ms-powerpoint',
            'application/msword',
            'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
        ];
        $mimetype = $file->getMimeType();
        if (!in_array($mimetype, $allowedMimetypes)) {
            // Handle the case where the file is of an invalid mimetype
            return $this->render('produituser/image.html.twig', [
                'error' => 'Invalid file type'
            ]);
        }

        // Generate a unique filename
        $filename = uniqid() . '.' . $file->getClientOriginalExtension();
        $file->move($this->getParameter('upload_directory'), $filename);
        $filepath = $this->getParameter('upload_directory') . '/' . $filename;

        return $this->render('produituser/image.html.twig', [
            'filepath' => $filepath
        ]);
    } else {
        // Handle the case where no file was uploaded
        return $this->render('produituser/image.html.twig');
    }
}



}
