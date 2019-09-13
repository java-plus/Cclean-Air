package dev.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.controllers.dto.ProfilDtoGet;
import dev.controllers.dto.UtilisateurDtoGet;
import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Statut;
import dev.entities.Utilisateur;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.services.CommuneService;
import dev.services.UtilisateurService;

@Controller
public class UtilisateurController {

	private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);
	private UtilisateurService utilisateurService;
	private CommuneService communeService;

	@Autowired
	public UtilisateurController(UtilisateurService utilisateurService, CommuneService communeService) {
		this.utilisateurService = utilisateurService;
		this.communeService = communeService;
	}

	@PostMapping("/comptes")
	@ResponseBody
	public ResponseEntity<UtilisateurDtoGet> reqAjoutUtilisateur(@Valid @RequestBody UtilisateurDtoPost uDtoP,
			Errors errors) throws UtilisateurInvalideException, CommuneInvalideException {
		LOGGER.info("reqAjoutUtilisateur() lancé");

		if (errors.hasErrors()) {
			throw new UtilisateurInvalideException(
					"ERREUR : au moins un des champs est mal renseigné : \n " + "errors.getAllErrors().");
		}

		if (utilisateurService.isEmailExistant(uDtoP.getEmail())) {
			throw new UtilisateurInvalideException("ERREUR : Un utilisateur utilise déjà cet email.");
		}

		if (!communeService.isCommuneExistante(uDtoP.getNomCommune())) {
			throw new CommuneInvalideException(
					"ERREUR : le nom de cette commune n'a pas été trouvé dans la base de " + "données.");
		}

		if (uDtoP.getStatuts().get(0).equals(Statut.MEMBRE)
				|| uDtoP.getStatuts().get(0).equals(Statut.ADMINISTRATEUR)) {
			Utilisateur utilisateur = utilisateurService.sauvegarderUtilisateur(uDtoP);
			return ResponseEntity.status(201).body(new UtilisateurDtoGet(utilisateur));
		} else {
			throw new UtilisateurInvalideException(
					"ERREUR : Le statut renseigné n'est pas valide (doit être " + "utilisateur ou administrateur)");
		}
	}

	@GetMapping(value = "/profil")
	public ResponseEntity<ProfilDtoGet> visualiserProfil() throws UtilisateurNonConnecteException {

		return new ResponseEntity<>(utilisateurService.visualiserProfil(), HttpStatus.OK);

	}

	@ExceptionHandler(UtilisateurInvalideException.class)
	public ResponseEntity<String> handleException(UtilisateurInvalideException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

}
