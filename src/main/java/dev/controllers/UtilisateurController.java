package dev.controllers;


import dev.controllers.dto.*;
import dev.entities.Statut;
import dev.entities.Utilisateur;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.MotDePasseInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.services.CommuneService;
import dev.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
public class UtilisateurController {

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);
    private UtilisateurService utilisateurService;
    private CommuneService communeService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, CommuneService communeService) {
        this.utilisateurService = utilisateurService;
        this.communeService = communeService;
    }

    @PostMapping("/comptes")
    @ResponseBody
    public ResponseEntity<UtilisateurDtoGet> reqAjoutUtilisateur(@Valid @RequestBody UtilisateurDtoPost uDtoP,
                                                                 Errors errors) throws UtilisateurInvalideException, CommuneInvalideException {
        LOGGER.info("reqAjoutUtilisateur() lancé");

        if (errors.hasErrors()) {
            throw new UtilisateurInvalideException(
                    "ERREUR : au moins un des champs est mal renseigné : \n " + "errors.getAllErrors().");
        }

        if (utilisateurService.isEmailExistant(uDtoP.getEmail())) {
            throw new UtilisateurInvalideException("ERREUR : Un utilisateur utilise déjà cet email.");
        }

        if (!communeService.isCommuneExistante(uDtoP.getNomCommune())) {
            throw new CommuneInvalideException(
                    "ERREUR : le nom de cette commune n'a pas été trouvé dans la base de " + "données.");
        }

        if (uDtoP.getStatuts().get(0).equals(Statut.MEMBRE)
                || uDtoP.getStatuts().get(0).equals(Statut.ADMINISTRATEUR)) {
            Utilisateur utilisateur = utilisateurService.sauvegarderUtilisateur(uDtoP);
            return ResponseEntity.status(201).body(new UtilisateurDtoGet(utilisateur));
        } else {
            throw new UtilisateurInvalideException(
                    "ERREUR : Le statut renseigné n'est pas valide (doit être " + "utilisateur ou administrateur)");
        }
    }

    @ExceptionHandler(UtilisateurInvalideException.class)
    public ResponseEntity<String> handleException(UtilisateurInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Méthode qui affiche la liste des utilisateur
     *
     * @return
     */
    @GetMapping("/admin/membres")
    public List<UtilisateurDtoAdmin> afficherListeUtilisateur() {
        return utilisateurService.creerListeUtilisateur();
    }


    /**
     * Méthode qui récupère la suppression de l'utilisateur
     *
     * @param email
     * @return
     */
    @DeleteMapping("/admin/membres/suppression/{email}")
    public ResponseEntity<String> suppressionUtilisateur(@PathVariable String email) {
        if (utilisateurService.isEmailExistant(email)) {
            utilisateurService.supprimerUtilisateur(email);
            return ResponseEntity.status(200).body("L'utilisateur " + email + " est bien supprimé !");
        } else {
            return ResponseEntity.status(400).body("L'email ne correspond à aucun compte");
        }
    }
    
    @DeleteMapping("/profil/suppression")
    public ResponseEntity<String> suppressionComptePerso(@RequestBody EmailDto email, HttpServletRequest req, HttpServletResponse resp) {
    	if (utilisateurService.isEmailExistant(email.getEmail())) {
    		utilisateurService.supprimerComptePerso();   
    		
    		Cookie[] cookies = req.getCookies();
    		if (cookies != null) {
    			for (Cookie cookie : cookies) {
    				cookie.setValue("");
    				cookie.setMaxAge(0);
    				resp.addCookie(cookie);
    			}
    		}
    		return ResponseEntity.status(200).body("Votre compte a bien été supprimé !");
    	} else {
    		return ResponseEntity.status(400).body("L'email ne correspond a aucun compte");
    	}
    }

    @GetMapping(value = "/profil")
    public ResponseEntity<ProfilDtoGet> visualiserProfil() throws UtilisateurNonConnecteException {

        return new ResponseEntity<>(utilisateurService.visualiserProfil(), HttpStatus.OK);

    }

    @PatchMapping("profil/modification")
    public ProfilModifcationGet afficherProfilModifie(@RequestBody ProfilModificationPost profilModificationPost) throws UtilisateurNonConnecteException, MotDePasseInvalideException {

        return utilisateurService.modifierProfil(profilModificationPost);
    }
}

