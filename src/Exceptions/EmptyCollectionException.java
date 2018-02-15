package Exceptions;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class EmptyCollectionException extends Exception {

    /**
     *
     * Cria uma nova instância de EmptyCollectionException sem mensagem
     * detalhada.
     */
    public EmptyCollectionException() {
    }

    /**
     * Constrói uma instância de EmptyCollectionException com a mensagem
     * detalhada especificada
     *
     * @param msg mensagem detalhada
     */
    public EmptyCollectionException(String msg) {
        super(msg);
    }
}
