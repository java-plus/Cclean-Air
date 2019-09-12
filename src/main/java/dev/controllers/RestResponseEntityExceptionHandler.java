package dev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.exceptions.CommuneDejaSuivieException;
import dev.exceptions.CommuneInvalideException;
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

}