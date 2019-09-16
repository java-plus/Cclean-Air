package dev.exceptions;

/**
 * Exception liée à un polluant introuvable ou dont les données sont incorrectes.
 */
public class PolluantInvalideException extends RuntimeException {
    public PolluantInvalideException() {
        super();
    }

    public PolluantInvalideException(String message) {
        super(message);
    }

    public PolluantInvalideException(String message, Throwable cause) {
        super(message, cause);
    }

    public PolluantInvalideException(Throwable cause) {
        super(cause);
    }

    protected PolluantInvalideException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
