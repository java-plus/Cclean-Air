package dev.exceptions;

public class EmailInvalideException extends Exception {

	public EmailInvalideException() {
	}

	public EmailInvalideException(String message) {
		super(message);
	}

	public EmailInvalideException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailInvalideException(Throwable cause) {
		super(cause);
	}

	public EmailInvalideException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
