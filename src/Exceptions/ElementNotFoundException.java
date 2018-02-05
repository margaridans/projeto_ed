package Exceptions;

/**
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class ElementNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

}
