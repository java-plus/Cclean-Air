package dev.exceptions;

public class ConditionMeteoException extends RuntimeException {
    public ConditionMeteoException() {
        super();
    }

    public ConditionMeteoException(String message) {
        super(message);
    }

    public ConditionMeteoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConditionMeteoException(Throwable cause) {
        super(cause);
    }

    protected ConditionMeteoException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
