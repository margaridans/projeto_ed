package LinkedStack;


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
     * Método construtor vazio que permite criar uma nova instância de
     * {@link LinearNode}
     */
    public LinearNode() {
        this.next = null;
        this.element = null;
    }

    /**
     *
     * Método construtor vazio que permite criar uma nova instância de
     * {@link LinearNode} com um elemento específico
     *
     * @param element elemento a ser armazenado
     */
    public LinearNode(T element) {
        this.next = null;
        this.element = element;
    }

    /**
     * Método que retorna uma referência do nó que se segue a este
     *
     * @return uma referência do nó que se segue a este
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
     * Método que retorna uma referência do elemento armazenado neste nó
     *
     * @return uma referência do elemento armazenado neste nó
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
