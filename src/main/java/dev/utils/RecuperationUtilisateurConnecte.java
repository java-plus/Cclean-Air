package dev.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import dev.entities.Utilisateur;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.repositories.IUtilisateurRepository;

/**
 * @author Guillaume Classe utilitaire qui récupère l'utilisateur connecté
 *
 */
@Component
public class RecuperationUtilisateurConnecte {

	private static final Logger log = LoggerFactory.getLogger(RecuperationUtilisateurConnecte.class);

	private IUtilisateurRepository repository;

	@Autowired
	public RecuperationUtilisateurConnecte(IUtilisateurRepository repository) {

		this.repository = repository;
	}

	/**
	 * @return renvoie l'utilisateur connecté via son email
	 * @throws UtilisateurNonConnecteException si aucun utilisateur n'est connecté,
	 *                                         renvoie une exception
	 */
	public Utilisateur recupererUtilisateurViaEmail() throws UtilisateurNonConnecteException {
		log.info("Récupération de l'utilisateur enregistré...");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = "";
		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}
		log.info("Identifiant récupéré : {0}", email);
		Optional<Utilisateur> utilisateur = repository.findByEmailIgnoreCase(email);
		if (utilisateur.isPresent()) {
			return utilisateur.get();
		} else {
			throw new UtilisateurNonConnecteException("Aucun utilisateur n'a pu être identifié.");
		}
	}

}
