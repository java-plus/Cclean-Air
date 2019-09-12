package dev.exceptions;

/**
 * @author Guillaume Eception utilisée lorsqu'un utilisateur tente d'enregistrer
 *         plusieurs fois la même commune en tant qu'indicateur
 *
 */
public class CommuneDejaSuivieException extends Exception {

	private String message;

	public CommuneDejaSuivieException(String message) {

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
