package dev.exceptions;

/**
 * Exception liée à un polluant introuvable ou dont les données sont incorrectes.
 */
public class DonneesLocalesException extends RuntimeException {
    public DonneesLocalesException() {
        super();
    }

    public DonneesLocalesException(String message) {
        super(message);
    }

    public DonneesLocalesException(String message, Throwable cause) {
        super(message, cause);
    }

    public DonneesLocalesException(Throwable cause) {
        super(cause);
    }

    protected DonneesLocalesException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
