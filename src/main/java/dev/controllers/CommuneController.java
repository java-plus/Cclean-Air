package dev.controllers;

import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.exceptions.CommuneInvalideException;
import dev.services.CommuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/communes")
public class CommuneController {

    private Logger LOGGER = LoggerFactory.getLogger(CommuneController.class);

    @Autowired
    private CommuneService communeService;

    @ExceptionHandler(CommuneInvalideException.class)
    public ResponseEntity<String> handleException(CommuneInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Méthode qui affiche les données locales pour l'utilisateur
     * @param codeInsee
     * @return
     */
    @GetMapping(value = "/{codeInsee}" )
    public DonneesLocalesDto afficherDonneesLocales(@PathVariable String codeInsee) {

        return communeService.creerDonneesLocalesCommune(codeInsee);
    }
}
