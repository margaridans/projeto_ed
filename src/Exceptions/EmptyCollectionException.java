package Exceptions;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class EmptyCollectionException extends Exception {

    /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link EmptyCollectionException} sem mensagem detalhada
     */
    public EmptyCollectionException() {
    }

    /**
     * Método construtor que permite criar uma nova instância de
     * {@link ArrayUnorderedList} com uma mensagem espec]ifica detalhada
     *
     * @param msg mensagem detalhada
     */
    public EmptyCollectionException(String msg) {
        super(msg);
    }
}
