package dk.sdu.swe.cross_cutting.exceptions;

public class InvalidNameException extends UserCreationException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidNameException(String message) {
        super(message);
    }
}
