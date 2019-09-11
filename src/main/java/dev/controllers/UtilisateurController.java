package dev.controllers;

import dev.controllers.dto.UtilisateurDtoGet;
import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Statut;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.services.AdministrateurService;
import dev.services.CommuneService;
import dev.services.MembreService;
import dev.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class UtilisateurController {

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);
    private UtilisateurService utilisateurService;
    private MembreService membreService;
    private AdministrateurService administrateurService;
    private CommuneService communeService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, MembreService membreService,
                                 AdministrateurService administrateurService, CommuneService communeService) {
        this.utilisateurService = utilisateurService;
        this.membreService = membreService;
        this.administrateurService = administrateurService;
        this.communeService = communeService;
    }

    @PostMapping("/comptes")
    @ResponseBody
    public ResponseEntity<UtilisateurDtoGet> reqAjoutUtilisateur(@Valid @RequestBody UtilisateurDtoPost uDtoP,
                                                              Errors errors) throws UtilisateurInvalideException, CommuneInvalideException {
        LOGGER.info("reqAjoutUtilisateur() lancé");

        if(errors.hasErrors()) {
            throw new UtilisateurInvalideException("ERREUR : au moins un des champs est mal renseigné : \n " +
                    "errors.getAllErrors().");
        }

        if(utilisateurService.isEmailExistant(uDtoP.getEmail())) {
            throw new UtilisateurInvalideException("ERREUR : Un utilisateur utilise déjà cet email.");
        }

        if(!communeService.isCommuneExistante(uDtoP.getNomCommune())) {
            throw new CommuneInvalideException("ERREUR : le nom de cette commune n'a pas été trouvé dans la base de " +
                    "données.");
        }

        if(uDtoP.getStatut().equals(Statut.MEMBRE)) {
            Membre membre = membreService.sauvegarderMembre(uDtoP);
            return ResponseEntity.status(201).body(new UtilisateurDtoGet(membre));
        } else if (uDtoP.getStatut().equals(Statut.ADMINISTRATEUR)) {
            Administrateur admin = administrateurService.sauvegarderAdministrateur(uDtoP);
            return ResponseEntity.status(201).body(new UtilisateurDtoGet(admin));
        } else {
            throw new UtilisateurInvalideException("ERREUR : Le statut renseigné n'est pas valide (doit être " +
                    "utilisateur ou administrateur)");
        }
    }

    @ExceptionHandler(UtilisateurInvalideException.class)
    public ResponseEntity<String> handleException(UtilisateurInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
