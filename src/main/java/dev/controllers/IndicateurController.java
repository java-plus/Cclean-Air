package dev.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.controllers.dto.CommuneIndicateurDto;
import dev.controllers.dto.IndicateurAffichageDto;
import dev.controllers.dto.IndicateurDto;
import dev.exceptions.AucuneDonneeException;
import dev.exceptions.CommuneDejaSuivieException;
import dev.exceptions.NombreIndicateursException;
import dev.exceptions.UtilisateurNonConnecteException;
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
	public ResponseEntity<List<IndicateurAffichageDto>> getIndicateursUtilisateur() {
		log.info("Récupération de l'utilisateur enregistré...");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = "";
		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}

		List<IndicateurAffichageDto> response = service.recupererLesIndicateurs(email);
		log.info("Indicateurs récupérés : {0}", response);
		return new ResponseEntity<>(service.recupererLesIndicateurs(email), HttpStatus.OK);

	}

	/**
	 * @param indicateur : nom de la commune concernée par l'indicateur, récupéré
	 *                   depuis une requete HTTP
	 * @return retourne le critère ajouté et un code http 201, en cas d'erreur,
	 *         renvoie un code http 400
	 * @throws UtilisateurNonConnecteException
	 * @throws CommuneDejaSuivieException
	 * @throws NombreIndicateursException
	 */
	@PostMapping(value = "/indicateurs")
	public ResponseEntity<IndicateurDto> ajoutIndicateur(@RequestBody CommuneIndicateurDto indicateur)
			throws NombreIndicateursException, UtilisateurNonConnecteException, CommuneDejaSuivieException {

		return new ResponseEntity<>(service.sauvegarderNouvelIndicateur(indicateur), HttpStatus.CREATED);

	}

	/**
	 * @param indicateur : L'indicateur à supprimer, identifié par le nom de la
	 *                   commune et les informations de l'utilisateur connecté
	 * @return renvoie un code 204 en cas de suppression réussie
	 * @throws UtilisateurNonConnecteException
	 */
	@DeleteMapping(value = "/indicateurs/{nomCommune}")
	public ResponseEntity<IndicateurDto> supprimerIndicateur(@PathVariable String nomCommune)
			throws UtilisateurNonConnecteException {
		return new ResponseEntity<>(service.supprimerUnIndicateur(nomCommune), HttpStatus.NO_CONTENT);
	}

	/**
	 * @param nouvelIndicateur L'indicateur nouvellement créé
	 * @param ancienIndicateur L'indicateur qui va être remplacé
	 * @return renvoie le nom de la commune du nouvelle indicateur
	 * @throws CommuneDejaSuivieException
	 * @throws UtilisateurNonConnecteException
	 * @throws AucuneDonneeException
	 */
	@PatchMapping(value = "/indicateurs/{nomCommune}")
	public ResponseEntity<CommuneIndicateurDto> modifierIndicateur(@RequestBody CommuneIndicateurDto indicateurs,
			@PathVariable String nomCommune)
			throws UtilisateurNonConnecteException, CommuneDejaSuivieException, AucuneDonneeException {

		return new ResponseEntity<>(service.modifierIndicateur(indicateurs, nomCommune), HttpStatus.OK);

	}

}
