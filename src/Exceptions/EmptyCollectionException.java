package Exceptions;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
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
