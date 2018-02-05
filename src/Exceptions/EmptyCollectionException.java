package Exceptions;

/**
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class EmptyCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmptyCollectionException() {
        super();
    }

    public EmptyCollectionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public EmptyCollectionException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public EmptyCollectionException(String arg0) {
        super(arg0);
    }

    public EmptyCollectionException(Throwable arg0) {
        super(arg0);
    }
}
