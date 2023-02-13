package com.nashss.se.fivelifts.exceptions;

public class TooManyRepsException extends RuntimeException {
    private static final long serialVersionUID = 7673232630757112432L;

    /**
     * Exception with no message or cause.
     */
    public TooManyRepsException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public TooManyRepsException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public TooManyRepsException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public TooManyRepsException(String message, Throwable cause) {
        super(message, cause);
    }
}
