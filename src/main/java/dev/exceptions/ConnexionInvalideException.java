package dev.exceptions;

/**
 * Classe d'exception lancée lors d'une erreur de connexion.
 */
public class ConnexionInvalideException extends RuntimeException {
    public ConnexionInvalideException() {
        super();
    }

    public ConnexionInvalideException(String message) {
        super(message);
    }

    public ConnexionInvalideException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnexionInvalideException(Throwable cause) {
        super(cause);
    }

    protected ConnexionInvalideException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
