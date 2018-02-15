package Exceptions;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class ElementNotFoundException extends Exception {

    /**
     *
     * Cria uma nova instância de ElementNotFoundException sem mensagem
     * detalhada.
     */
    public ElementNotFoundException() {
    }

    /**
     * Constrói uma instância de ElementNotFoundException com a mensagem
     * detalhada especificada
     *
     * @param msg mensagem detalhada
     */
    public ElementNotFoundException(String msg) {
        super(msg);
    }

}
