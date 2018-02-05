package LinkedStack;

/**
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class LinearNode<T> {

    private LinearNode<T> next;
    private T element;

    /**
     * Create a stored empty node
     */
    public LinearNode() {
        this.next = null;
        this.element = null;
    }

    /**
     * Create a node storing the specified element
     *
     * @param element element to be stored
     */
    public LinearNode(T element) {
        this.next = null;
        this.element = element;
    }

    /**
     * Returns the node that follows this one
     *
     * @return LinearNode reference to next node
     */
    public LinearNode<T> getNext() {
        return this.next;
    }

    /**
     * Sts the node that follows this one
     *
     * @param node node to follow this one
     */
    public void setNext(LinearNode<T> node) {
        this.next = node;
    }

    /**
     * Returns the element stored in this node
     *
     * @return T elemnt stored at this node
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Sets the element stored in this node
     *
     * @param element T element stored at this node
     */
    public void setElement(T element) {
        this.element = element;
    }

}
