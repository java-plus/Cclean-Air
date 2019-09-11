package dev.exceptions;

/**
 * @author Guillaume Exception renvoyée quand aucun utilisateur connecté n'a pu
 *         être détecté
 *
 */
public class UtilisateurNonConnecteException extends Exception {

	private static final long serialVersionUID = -7263238836009082336L;

	private String message;

	public UtilisateurNonConnecteException(String message) {

		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
