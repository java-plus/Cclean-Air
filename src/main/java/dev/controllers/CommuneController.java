package dev.controllers;

import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.entities.DonneesLocales;
import dev.exceptions.CommuneInvalideException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController (value = "/communes")
public class CommuneController {

    @ExceptionHandler(CommuneInvalideException.class)
    public ResponseEntity<String> handleException(CommuneInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @GetMapping(value = "/{codeInsee}" )
    public DonneesLocalesDto afficherDonneesLocales(@PathVariable String codeInsee, ZonedDateTime date){
        // TODO: 11/09/2019
        //créer la méthode service creerAfficherDonnesLocalesDTO

        return null;
    }
}
