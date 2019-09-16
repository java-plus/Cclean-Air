package dev.exceptions;

/**
 * @author Guillaume
 *
 */
public class AucuneDonneeException extends Exception {

	private String message;

	public AucuneDonneeException(String message) {
		super();
		this.message = message;
	}

	public AucuneDonneeException() {
		super();
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
