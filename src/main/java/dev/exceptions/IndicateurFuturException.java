package dev.exceptions;

/**
 * @author Guillaume Exception déclenchée lorsque l'utilisateur saisit une date
 *         située dans le futur lorsqu'il souhaite consulter un indicateur
 *
 */
public class IndicateurFuturException extends Exception {

	private static final long serialVersionUID = 1407514833640057617L;
	private String message;

	public IndicateurFuturException(String message) {
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
