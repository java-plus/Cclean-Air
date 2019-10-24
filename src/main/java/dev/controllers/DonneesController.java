package dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.controllers.dto.recherche.CommuneCarteDto;
import dev.controllers.dto.recherche.CommuneRechercheDto;
import dev.controllers.dto.recherche.resultat.CommuneDto;
import dev.exceptions.AucuneDonneeException;
import dev.services.CommuneService;

/**
 * @author Guillaume Classe gérant l'envoi de données pour l'affichage des
 *         marqueurs de carte et des données de chaque commune
 *
 */
@RestController
public class DonneesController {

	private CommuneService service;

	@Autowired
	public DonneesController(CommuneService service) {

		this.service = service;
	}

	/**
	 * @return renvoie les informations nécessaire à l'affichage de chaque "pin" sur
	 *         la carte
	 */
	@GetMapping("/donnees_carte")
	public ResponseEntity<List<CommuneCarteDto>> recupererDonneesCartes() {
		return new ResponseEntity<>(service.recupererDonnesCarte(), HttpStatus.OK);
	}

	@PostMapping("/details_commune")
	public ResponseEntity<CommuneDto> recupererDetailsCommune(@RequestBody CommuneRechercheDto commune)
			throws AucuneDonneeException {
		return new ResponseEntity<>(service.rechercherDetailsCommune(commune), HttpStatus.OK);
	}

}
