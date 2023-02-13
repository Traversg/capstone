package com.nashss.se.fivelifts.exceptions;

/**
 * Exception to throw when a body weight is less than zero.
 */
public class BodyWeightLessThanZeroException extends RuntimeException {
    private static final long serialVersionUID = 6392413315905251750L;

    /**
     * Exception with no message or cause.
     */
    public BodyWeightLessThanZeroException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public BodyWeightLessThanZeroException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public BodyWeightLessThanZeroException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public BodyWeightLessThanZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
