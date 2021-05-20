package dk.sdu.swe.cross_cutting.exceptions;

/**
 * The type User creation exception.
 */
public class UserCreationException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for                later retrieval by the {@link #getMessage()} method.
     */
    public UserCreationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new User creation exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public UserCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
