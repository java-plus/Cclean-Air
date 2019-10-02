package dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controllers.dto.CommuneCarteDto;
import dev.services.CommuneService;

/**
 * @author Guillaume Classe gérant l'envoi de données pour l'affichage des
 *         marqueurs de carte et des données de chaque commune
 *
 */
@RestController
public class CarteController {

	private CommuneService service;

	@Autowired
	public CarteController(CommuneService service) {

		this.service = service;
	}

	/**
	 * @return renvoie les informations nécessaire à l'affichage de chaque "pin" sur
	 *         la carte
	 */
	@GetMapping("/donnees_cartes")
	public ResponseEntity<List<CommuneCarteDto>> recupererDonneesCartes() {
		return new ResponseEntity<>(service.recupererDonnesCarte(), HttpStatus.OK);
	}

}
