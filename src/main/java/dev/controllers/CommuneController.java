package dev.controllers;

import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.entities.DonneesLocales;
import dev.exceptions.CommuneInvalideException;
import dev.services.CommuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController 
public class CommuneController {

    @Autowired
    CommuneService communeService;

    @ExceptionHandler(CommuneInvalideException.class)
    public ResponseEntity<String> handleException(CommuneInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Méthode qui affiche les données locales pour l'utilisateur
     * @param codeInsee
     * @return
     */
    @GetMapping(value = "communes/{codeInsee}" )
    public DonneesLocalesDto afficherDonneesLocales(@PathVariable String codeInsee) {

        return communeService.creerDonneesLocalesCommune(codeInsee);
    }
}
