package dev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.exceptions.AucuneDonneeException;
import dev.services.PolluantService;

/**
 * @author Guillaume Classe controlleur utilisée pour récupérer des données
 *         concernant uniquement les polluants
 *
 */
@RestController(value = "/polluants")
public class PolluantController {

	private PolluantService service;

	public PolluantController(PolluantService service) {
		super();
		this.service = service;
	}

	/**
	 * @return renvoie la liste de tous les noms de polluants situés en base en
	 *         supprimant les doublons
	 * @throws AucuneDonneeException déclenche une exception si aucune donnée n'a
	 *                               été trouvé
	 */
	@GetMapping(value = "/noms")
	public ResponseEntity<Object> recupererNomsPolluants() throws AucuneDonneeException {
		return new ResponseEntity<>(service.recupererNomsPolluantsDeBase(), HttpStatus.OK);
	}

}
