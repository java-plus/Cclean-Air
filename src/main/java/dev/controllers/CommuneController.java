package dev.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import dev.controllers.dto.CommuneDtoGetLight;
import dev.controllers.dto.DonneesLocalesHistorique;
import dev.controllers.dto.DonneesLocalesRecherchees;
import dev.controllers.dto.recherche.CommuneCarteDto;
import dev.controllers.dto.recherche.CommuneRechercheDto;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.exceptions.AucuneDonneeException;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.DonneesLocalesException;
import dev.exceptions.IndicateurFuturException;
import dev.services.CommuneService;
import dev.utils.CalculUtils;

@RestController
@RequestMapping(value = "/communes")
public class CommuneController {

	private Logger LOGGER = LoggerFactory.getLogger(CommuneController.class);

	private CommuneService communeService;

	private CalculUtils calcul;

	@Autowired
	public CommuneController(CommuneService communeService, CalculUtils calcul) {
		this.communeService = communeService;
		this.calcul = calcul;
	}

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
	 * @throws AucuneDonneeException
	 */
	@PostMapping(value = "/recherche")
	public ResponseEntity<AffichageResultatCommuneDto> afficherResultatRecherche(
			@RequestBody CommuneRechercheDto commune) throws IndicateurFuturException, AucuneDonneeException {
		return new ResponseEntity<>(communeService.rechercheCommune(commune), HttpStatus.OK);
	}

	/**
	 * Affcihe l'historique pour le polluant et la période saisie par l'utilisateur
	 *
	 * @param codeInsee
	 * @param donneesLocalesRecherchees
	 * @return
	 */
	@PostMapping("/historiques/{codeInsee}")
	public List<DonneesLocalesHistorique> afficherHistorique(@PathVariable String codeInsee,
			@RequestBody DonneesLocalesRecherchees donneesLocalesRecherchees) {

		LOGGER.info("Je passe dans le controller et je récupère le json : " + donneesLocalesRecherchees);

		return communeService.creerHistorique(donneesLocalesRecherchees, codeInsee);

	}

	/**
	 * @param coordonnees Prend en paramètre un tableau de coordonnées [longitude,
	 *                    latitude]
	 * @return Renvoie la commune la plus proche sous la forme d'une instance de
	 *         CommuneCarteDto
	 */
	@PostMapping("/plus_proche")
	public ResponseEntity<CommuneCarteDto> recupererCommuneLaPlusProche(@RequestBody Double[] coordonnees) {
		return new ResponseEntity<>(calcul.calculerCommuneLaPluProche(coordonnees), HttpStatus.OK);
	}

	/**
	 * Contrôleur permettant la transmission de toutes les communes.
	 * 
	 * @return : List<CommuneDtoGetLight> la liste des communes insérées en base de
	 *         données
	 */
	@GetMapping
	public List<CommuneDtoGetLight> recupererCommunesReq() {
		return communeService.recupererToutesLesCommunesDeLaBaseInfosEssentielles();
	}

	@ExceptionHandler(DonneesLocalesException.class)
	public ResponseEntity<String> handleException(DonneesLocalesException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

}
