package Exceptions;

/**
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class EmptyStackException extends Exception {

    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String message) {
        super(message);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyStackException(Throwable cause) {
        super(cause);
    }

    protected EmptyStackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
