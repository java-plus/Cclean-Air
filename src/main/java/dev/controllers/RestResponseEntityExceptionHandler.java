package dev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.exceptions.AucuneDonneeException;
import dev.exceptions.CommuneDejaSuivieException;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.ConnexionInvalideException;
import dev.exceptions.IndicateurFuturException;
import dev.exceptions.MotDePasseInvalideException;
import dev.exceptions.NombreIndicateursException;
import dev.exceptions.UtilisateurNonConnecteException;

/**
 * @author Guillaume Classe de gestion des exceptions au niveau de l'envoie de
 *         réponse
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	/**
	 * @param e
	 * @return retourne un statut 400 en cas d'ajout d'un indicateur où la commune
	 *         existe déjà pour un autre indicateur
	 */
	@ExceptionHandler(value = { CommuneDejaSuivieException.class })
	public ResponseEntity<Object> handleConflict(CommuneDejaSuivieException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param e
	 * @return Renvoie un statut 400 si une commune invalide a été renseignée
	 */
	@ExceptionHandler(value = { CommuneInvalideException.class })
	public ResponseEntity<Object> handleConflict(CommuneInvalideException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param e
	 * @return Exception déclenchée qua don tente d'ajouter un nouvel indicateur
	 *         alors que la limite autorisée est atteinte
	 */
	@ExceptionHandler(value = { NombreIndicateursException.class })
	public ResponseEntity<Object> handleConflict(NombreIndicateursException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param e
	 * @return Exception déclenché lorsqu'aucun utilisateur n'est authentifié mais
	 *         que l'opération nécéssite une récupération de données d'un
	 *         utilisateur connecté
	 */
	@ExceptionHandler(value = { UtilisateurNonConnecteException.class })
	public ResponseEntity<Object> handleConflict(UtilisateurNonConnecteException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param e
	 * @return Exception déclenché lorsqu'aucun utilisateur tente de consulter les
	 *         données d'une commune à une daté situé dans le futur
	 */
	@ExceptionHandler(value = { IndicateurFuturException.class })
	public ResponseEntity<Object> handleConflict(IndicateurFuturException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param e
	 * @return Exception déclenché lorsqu'aucun utilisateur tente de consulter les
	 *         données d'une commune à une daté situé dans le futur
	 */
	@ExceptionHandler(value = { MotDePasseInvalideException.class })
	public ResponseEntity<Object> handleConflict(MotDePasseInvalideException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Gestionnaire de l'exception de connexion invalide.
	 * 
	 * @param e : ConnexionInvalideException l'exception lancée
	 * @return : ResponseEntity<Object>
	 */
	@ExceptionHandler(value = { ConnexionInvalideException.class })
	public ResponseEntity<Object> handleConflict(ConnexionInvalideException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Gestionnaire de l'exceptin d'absence de données
	 * 
	 * @param e l'exception déclenchée
	 * @return retourne une réponse avec un statut 404 et le corps de la stack trace
	 */
	@ExceptionHandler(AucuneDonneeException.class)
	public ResponseEntity<String> handleException(AucuneDonneeException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

}
