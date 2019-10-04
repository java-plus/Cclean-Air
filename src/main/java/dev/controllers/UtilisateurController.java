package dev.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.controllers.dto.EmailDto;
import dev.controllers.dto.ProfilDtoGet;
import dev.controllers.dto.ProfilModifcationGet;
import dev.controllers.dto.ProfilModificationPost;
import dev.controllers.dto.UtilisateurDtoAdmin;
import dev.controllers.dto.UtilisateurDtoGet;
import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Statut;
import dev.entities.Utilisateur;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.EmailInvalideException;
import dev.exceptions.MotDePasseInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.services.CommuneService;
import dev.services.UtilisateurService;

/**
 * Controleur gérant les requêtes liées au CRUD des comptes utilisateurs.
 */
@RestController
public class UtilisateurController {

	/**
	 * LOGGER de la classe.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

	/**
	 * Services de l'utilisateur.
	 */
	private UtilisateurService utilisateurService;

	/**
	 * Services de la commune.
	 */
	private CommuneService communeService;

	/**
	 * Constructeur faisant les injections des services.
	 *
	 * @param utilisateurService : UtilisateurService services de l'utilisateur.
	 * @param communeService     : CommuneService services de la commune
	 */
	@Autowired
	public UtilisateurController(UtilisateurService utilisateurService, CommuneService communeService) {
		this.utilisateurService = utilisateurService;
		this.communeService = communeService;
	}

	/**
	 * Contrôleur gérant la requête POST de création d'un compte.
	 *
	 * @param uDtoP  : UtilisateurDtoPost correspondant au JSON de la requête
	 * @param errors : Errors objet en cas d'erreur
	 * @return : ResponseEntity<UtilisateurDtoGet> Un body JSON contenant les
	 *         données du compte créé.
	 * @throws UtilisateurInvalideException : Exception lancée si les données
	 *                                      entrées sont incorrectes.
	 * @throws CommuneInvalideException     : Exception lancée si la commune ne
	 *                                      correspond pas à une commune en base.
	 */
	@PostMapping("/comptes")
	@ResponseBody
	public ResponseEntity<UtilisateurDtoGet> reqAjoutUtilisateur(@Valid @RequestBody UtilisateurDtoPost uDtoP,
			Errors errors) throws UtilisateurInvalideException, CommuneInvalideException {

		LOGGER.info("reqAjoutUtilisateur() lancé");

		if (errors.hasErrors()) {
			throw new UtilisateurInvalideException(
					"ERREUR : au moins un des champs est mal renseigné : \n " + errors.getAllErrors());
		}

		if (utilisateurService.isEmailExistant(uDtoP.getEmail())) {
			throw new UtilisateurInvalideException("ERREUR : un utilisateur utilise déjà cet email.");
		}

		if (!communeService.isCommuneExistante(uDtoP.getNomCommune())) {
			throw new CommuneInvalideException(
					"ERREUR : le nom de cette commune n'a pas été trouvé dans la base de données.");
		}

		if (uDtoP.getStatuts().get(0).equals(Statut.MEMBRE)
				|| uDtoP.getStatuts().get(0).equals(Statut.ADMINISTRATEUR)) {
			Utilisateur utilisateur = utilisateurService.sauvegarderUtilisateur(uDtoP);
			return ResponseEntity.status(201).body(new UtilisateurDtoGet(utilisateur));
		} else {
			throw new UtilisateurInvalideException(
					"ERREUR : le statut renseigné n'est pas valide (il doit être utilisateur ou administrateur)");
		}
	}

	/**
	 * Gestionnaire d'exception en cas d'émission d'une exception de type
	 * UtilisateurInvalideException
	 *
	 * @param e : UtilisateurInvalideException l'exception
	 * @return : ResponseEntity
	 */
	@ExceptionHandler(UtilisateurInvalideException.class)
	public ResponseEntity<String> handleException(UtilisateurInvalideException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

	/**
	 * Contrôleur gérant la requête GET permettant de répondre avec la liste des
	 * utilisateurs.
	 *
	 * @return : List<UtilisateurDtoAdmin> la liste des utilisateurs
	 */
	@GetMapping("/admin/membres")
	public List<UtilisateurDtoAdmin> afficherListeUtilisateur() {
		return utilisateurService.creerListeUtilisateur();
	}

	/**
	 * Méthode qui récupère la suppression de l'utilisateur
	 *
	 * @param email : string
	 * @return : ResponseEntity<String>
	 */
	@DeleteMapping("/admin/membres/suppression/{email}")
	public ResponseEntity<String> suppressionUtilisateur(@PathVariable String email) {
		if (utilisateurService.isEmailExistant(email)) {
			utilisateurService.supprimerUtilisateur(email);
			return ResponseEntity.status(200).body("L'utilisateur " + email + " est bien supprimé !");
		} else {
			return ResponseEntity.status(400).body("L'email ne correspond à aucun compte");
		}
	}

	/**
	 * Contrôleur gérant la suppression de l'utilisateur (par requête DELETE)
	 *
	 * @param email : string
	 * @param req
	 * @param resp
	 * @return : ResponseEntity<String>
	 */
	@DeleteMapping("/profil/suppression/{email}")
	public ResponseEntity<String> suppressionComptePerso(@PathVariable String email, HttpServletRequest req,
			HttpServletResponse resp) {
		if (utilisateurService.isEmailExistant(email)) {
			utilisateurService.supprimerComptePerso();

			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
			}
			//return ResponseEntity.status(200).body("Votre compte a bien été supprimé !");
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(400).body("L'email ne correspond a aucun compte");
		}
	}

	/**
	 * Contrôleur gérant la requête permettant de visualiser les données d'un
	 * profil.
	 *
	 * @return : ResponseEntity<ProfilDtoGet>
	 * @throws UtilisateurNonConnecteException
	 */
	@GetMapping(value = "/profil")
	public ResponseEntity<ProfilDtoGet> visualiserProfil() throws UtilisateurNonConnecteException {

		return new ResponseEntity<>(utilisateurService.visualiserProfil(), HttpStatus.OK);

	}

	/**
	 * Méthode qui permet à l'utilisateur de modifier son profil
	 *
	 * @param profilModificationPost : ProfilModificationPost
	 * @return : ProfilModifcationGet
	 * @throws EmailInvalideException
	 * @throws UtilisateurNonConnecteException
	 * @throws MotDePasseInvalideException
	 */
	@PatchMapping("profil/modification")
	public ProfilModifcationGet afficherProfilModifie(@RequestBody ProfilModificationPost profilModificationPost)
			throws UtilisateurNonConnecteException, MotDePasseInvalideException, EmailInvalideException {

		return utilisateurService.modifierProfil(profilModificationPost);
	}

	/**
	 * Méthode qui permet de savoir si on et connecté en mode admin ou non. Elle
	 * retourne un code 200 si on est connecté en admin, et un code 400 si on n'est
	 * pas connecté en admin.
	 * 
	 * @return
	 * @throws UtilisateurNonConnecteException
	 */
	@GetMapping("/profils/statut")
	public ResponseEntity<String> validationStatut() throws UtilisateurNonConnecteException {

		var retour = utilisateurService.validationAdmin();

		if (retour) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
