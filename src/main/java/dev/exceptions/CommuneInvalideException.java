package dev.exceptions;

/**
 * Exception liée à une commune dont les données seraient manquantes ou incorrectes.
 */
public class CommuneInvalideException extends RuntimeException {
    public CommuneInvalideException() {
        super();
    }

    public CommuneInvalideException(String message) {
        super(message);
    }

    public CommuneInvalideException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommuneInvalideException(Throwable cause) {
        super(cause);
    }

    protected CommuneInvalideException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
