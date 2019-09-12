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

    private final Logger LOGGER = LoggerFactory.getLogger(CommuneController.class);

    @Autowired
    CommuneService communeService;

    @ExceptionHandler(CommuneInvalideException.class)
    public ResponseEntity<String> handleException(CommuneInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @GetMapping(value = "communes/{codeInsee}" )
    public DonneesLocalesDto afficherDonneesLocales(@PathVariable String codeInsee) {

        ZonedDateTime date = ZonedDateTime.now();
        LOGGER.info("Je suis passé dans le controller communes donnees locales");
        return communeService.creerDonneesLocalesCommune(codeInsee, date);


    }
}
