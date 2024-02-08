package cloud.voiture.controller;

import cloud.voiture.service.AnnonceFavorisService;
import cloud.voiture.service.AnnonceService;
import cloud.voiture.model.Annonce;
import cloud.voiture.model.AnnonceFavoris;
import cloud.voiture.model.Carburant;
import cloud.voiture.model.Categorie;
import cloud.voiture.model.Marque;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.model.request.FiltreAnnonceReq;
import cloud.voiture.repository.CarburantRepository;
import cloud.voiture.repository.CategorieRepository;
import cloud.voiture.repository.MarqueRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/annonce")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnoncePublicController {

    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private MarqueRepository marqueRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    CarburantRepository carburantRepository;
    
    @GetMapping("carburant")
    public ResponseEntity<ResponseWrap<List<Carburant>>> getAllCarburant() {
        return new ResponseEntity<>(ResponseWrap.success(carburantRepository.findAll()),HttpStatus.OK);
    }

    @GetMapping("marque")
    public ResponseWrap<List<Marque>> getAllMarque() {
        return ResponseWrap.success(marqueRepository.findByEtat(1));
    }

    @GetMapping("categorie")
    public ResponseWrap<List<Categorie>> getAllCategorie() {
        return ResponseWrap.success(categorieRepository.findByEtat(1));
    }

    // mahita annonce rehetra na tsy connecter aza
    @GetMapping("accueil")
    public ResponseEntity<ResponseWrap> getAccueil() {
        return ResponseEntity.ok(ResponseWrap.success(annonceService.getAnnoncesValidee()));
    }

    @GetMapping("/{id}")
    public ResponseWrap<Annonce> getAnnonceById(@PathVariable String id) {
        Optional<Annonce> rep = annonceService.getAnnonceById(id);
        if (rep.isPresent()) {
            return ResponseWrap.success(rep.get());
        }
        return ResponseWrap.error("id not found");
    }

    @ResponseBody
    @RequestMapping(value = "/filtre", method = RequestMethod.POST)
    public List<Annonce> filtreAnnonce(@RequestBody FiltreAnnonceReq filtreAnnonceReq) {
        System.out.println(filtreAnnonceReq.toString());
        return annonceService.filtrerAnnonces(filtreAnnonceReq);
    }
}
