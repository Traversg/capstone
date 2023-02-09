package com.nashss.se.fivelifts.exceptions;

/**
 * Exception to throw when a given email is not found
 * in the database.
 */
public class WorkoutNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2362970293434662394L;

    /**
     * Exception with no message or cause.
     */
    public WorkoutNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public WorkoutNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public WorkoutNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public WorkoutNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
