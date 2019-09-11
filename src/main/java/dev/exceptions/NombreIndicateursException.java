package dev.exceptions;

/**
 * @author Guillaume Exception déclenché lorsque l'utilisateur dispose d'un trop
 *         grand nombre d'indicateur
 *
 */
public class NombreIndicateursException extends Exception {

	private static final long serialVersionUID = -3647830157292008525L;

	/**
	 * Message d'erreur à afficher
	 */
	private String message;

	public NombreIndicateursException(String message) {
		super();
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
