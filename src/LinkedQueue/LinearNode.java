package LinkedQueue;

/**
 * @param <T> T element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class LinearNode<T> {

    private LinearNode<T> next; //referência ao próximo nó na lista
    private T element;// elemento armazenado no nó

    /**
     * Cria um nó vazio
     */
    public LinearNode() {
        this.next = null;
        this.element = null;
    }

    /**
     * Crie um nó armazenando o elemento especificado
     *
     * @param element elemento a ser armazenado
     */
    public LinearNode(T element) {
        this.next = null;
        this.element = element;
    }

    /**
     *
     * Retorna o nó que se segue a este
     *
     * @return LinearNode referência para o próximo nó
     */
    public LinearNode<T> getNext() {
        return this.next;
    }

    /**
     * Define o nó que se segue a este
     *
     * @param node nó que se segue
     */
    public void setNext(LinearNode<T> node) {
        this.next = node;
    }

    /**
     * Retorna o elemento armazenado neste nó
     *
     * @return T elemento armazenado no nó
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Define o elemento armazenado neste nó
     *
     * @param element elemento T armazenado neste nó
     */
    public void setElement(T element) {
        this.element = element;
    }
}
