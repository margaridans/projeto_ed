package Exceptions;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class ElementNotFoundException extends Exception {

  /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link ElementNotFoundException} sem mensagem detalhada
     */
    public ElementNotFoundException() {
    }

    /**
     * Método construtor que permite criar uma nova instância de
     * {@link ElementNotFoundException} com uma mensagem espec]ifica detalhada
     *
     * @param msg mensagem detalhada
     */
    public ElementNotFoundException(String msg) {
        super(msg);
    }

}
