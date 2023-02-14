package com.nashss.se.fivelifts.exceptions;

/**
 * Exception to throw when a rep entered is less than zero.
 */
public class RepsLessThanZeroException extends RuntimeException {
    private static final long serialVersionUID = -6342321508531808722L;

    /**
     * Exception with no message or cause.
     */
    public RepsLessThanZeroException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public RepsLessThanZeroException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public RepsLessThanZeroException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public RepsLessThanZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
