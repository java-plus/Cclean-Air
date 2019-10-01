package dev.exceptions;

public class DonneesLocalesSheduledTasksException extends RuntimeException {
    public DonneesLocalesSheduledTasksException() {
        super();
    }

    public DonneesLocalesSheduledTasksException(String message) {
        super(message);
    }

    public DonneesLocalesSheduledTasksException(String message, Throwable cause) {
        super(message, cause);
    }

    public DonneesLocalesSheduledTasksException(Throwable cause) {
        super(cause);
    }

    protected DonneesLocalesSheduledTasksException(String message, Throwable cause, boolean enableSuppression,
                                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
