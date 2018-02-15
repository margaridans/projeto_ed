package Exceptions;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class EmptyCollectionException extends Exception {

   public EmptyCollectionException() {
    }

    public EmptyCollectionException(String msg) {
        super(msg);
    }
}
