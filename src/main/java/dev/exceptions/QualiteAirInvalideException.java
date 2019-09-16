package dev.exceptions;

/**
 * Exception liée à une qualité d'air introuvable ou dont les données sont incorrectes.
 */
public class QualiteAirInvalideException extends RuntimeException {
    public QualiteAirInvalideException() {
        super();
    }

    public QualiteAirInvalideException(String message) {
        super(message);
    }

    public QualiteAirInvalideException(String message, Throwable cause) {
        super(message, cause);
    }

    public QualiteAirInvalideException(Throwable cause) {
        super(cause);
    }

    protected QualiteAirInvalideException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
