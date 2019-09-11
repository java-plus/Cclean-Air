package dev.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controllers.dto.CommuneIndicateurDto;
import dev.services.IndicateurService;

/**
 * @author Guillaume Classe interagissant avec le front pour les requêtes HTTP
 *         liées aux indicateurs
 *
 */
@RestController
public class IndicateurController {

	private static final Logger log = LoggerFactory.getLogger(IndicateurController.class);

	/**
	 * Injection de la classe de service
	 */
	private IndicateurService service;

	@Autowired
	public IndicateurController(IndicateurService service) {

		this.service = service;
	}

	/**
	 * @return Renvoie la liste des indicateurs d'un utilisateur spécifique
	 */
	@GetMapping(value = "/indicateurs")
	public ResponseEntity<List<CommuneIndicateurDto>> getIndicateursUtilisateur() {
		log.info("Récupération de l'utilisateur enregistré...");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = "";
		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}
		log.info("Identifiant récupéré : {0}", email);
		List<CommuneIndicateurDto> response = service.recupererLesIndicateurs(email);
		log.info("Indicateurs récupérés : {0}", response);
		return new ResponseEntity<>(service.recupererLesIndicateurs(email), HttpStatus.OK);

	}

}
