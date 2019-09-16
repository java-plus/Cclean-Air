package dev.controllers;

import dev.controllers.dto.DonneesLocalesRecherchees;
import dev.controllers.dto.DonneesLocalesHistorique;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.exceptions.CommuneInvalideException;
import dev.services.CommuneService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/communes")
public class CommuneController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommuneController.class);
	
   
    private CommuneService communeService;
    
    

    /** Constructeur
	 * @param communeService
	 */
    @Autowired
	public CommuneController(CommuneService communeService) {
		super();
		this.communeService = communeService;
	}

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

    /**
     * Affcihe l'historique pour le polluant et la période saisie par l'utilisateur
     * @param codeInsee
     * @param donneesLocalesRecherchees
     * @return
     */
    @PostMapping("/historiques/{codeInsee}")
    public List<DonneesLocalesHistorique> afficherHistorique(@PathVariable String codeInsee, @RequestBody DonneesLocalesRecherchees donneesLocalesRecherchees){

    
    	LOGGER.info("Je passe dans le controller et je récupère le json : " + donneesLocalesRecherchees);
    	
        return communeService.creerHistorique(donneesLocalesRecherchees, codeInsee);


    }
}
