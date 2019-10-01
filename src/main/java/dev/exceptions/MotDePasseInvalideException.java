package dev.exceptions;

public class MotDePasseInvalideException extends Exception {

	public MotDePasseInvalideException() {
	}

	public MotDePasseInvalideException(String message) {
		super(message);
	}

	public MotDePasseInvalideException(String message, Throwable cause) {
		super(message, cause);
	}

	public MotDePasseInvalideException(Throwable cause) {
		super(cause);
	}

	public MotDePasseInvalideException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
