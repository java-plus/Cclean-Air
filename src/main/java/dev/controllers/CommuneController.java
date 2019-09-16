package dev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controllers.dto.AffichageResultatCommuneDto;
import dev.controllers.dto.CommuneRechercheDto;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.IndicateurFuturException;
import dev.services.CommuneService;

@RestController
@RequestMapping(value = "/communes")
public class CommuneController {

	@Autowired
	CommuneService communeService;

	@ExceptionHandler(CommuneInvalideException.class)
	public ResponseEntity<String> handleException(CommuneInvalideException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

	/**
	 * Méthode qui affiche les données locales pour l'utilisateur
	 * 
	 * @param codeInsee
	 * @return
	 */
	@GetMapping(value = "/{codeInsee}")
	public DonneesLocalesDto afficherDonneesLocales(@PathVariable String codeInsee) {

		return communeService.creerDonneesLocalesCommune(codeInsee);
	}

	/**
	 * @param commune Paramètres de recherche renseignés par l'utilisateur récupérés
	 *                sous la forme d'un JSON
	 * @return renvoie les données de recherche personnalisée selon la recherche
	 *         utilisateur
	 * @throws IndicateurFuturException Déclenché si l'utilisateur a indiqué une
	 *                                  date dans le futur
	 */
	@PostMapping(value = "/recherche")
	public ResponseEntity<AffichageResultatCommuneDto> afficherResultatRecherche(
			@RequestBody CommuneRechercheDto commune) throws IndicateurFuturException {
		return new ResponseEntity<>(communeService.rechercheCommune(commune), HttpStatus.OK);
	}
}
