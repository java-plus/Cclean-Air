package dev.exceptions;

/**
 * Exception liée à un utilisateur dont les données seraient manquantes ou
 * incorrectes.
 */
public class UtilisateurInvalideException extends RuntimeException {

	public UtilisateurInvalideException() {
		super();
	}

	public UtilisateurInvalideException(String message) {
		super(message);
	}

	public UtilisateurInvalideException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilisateurInvalideException(Throwable cause) {
		super(cause);
	}

	protected UtilisateurInvalideException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
