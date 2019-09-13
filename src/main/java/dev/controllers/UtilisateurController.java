package dev.controllers;

import javax.validation.Valid;

import dev.controllers.dto.UtilisateurDtoAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import dev.controllers.dto.UtilisateurDtoGet;
import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Statut;
import dev.entities.Utilisateur;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.services.CommuneService;
import dev.services.UtilisateurService;

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

    @GetMapping("/admin/membres")
    public List<UtilisateurDtoAdmin> afficherListeUtilisateur() {
        return utilisateurService.creerListeUtilisateur();
    }


    @DeleteMapping("/admin/membres/suppression/{email}")
    public ResponseEntity<String> suppressionUtilisateur(@PathVariable String email)  {
        if (utilisateurService.isEmailExistant(email)) {
            utilisateurService.supprimerUtilisateur(email);
            return ResponseEntity.status(200).body("L'utilisateur " + email + " est bien supprimé !");
        } else {
        	return  ResponseEntity.status(400).body("L'email ne correspond à aucun compte");
		}


    }

}
