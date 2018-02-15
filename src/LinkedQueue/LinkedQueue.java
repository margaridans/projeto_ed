package LinkedQueue;

import Exceptions.EmptyCollectionException;
import interfaces.QueueADT;

/**
 * @param <T> T element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private int size;
    private LinearNode<T> first;
    private LinearNode<T> rear;

    /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link LinkedQueue}
     */
    public LinkedQueue() {
        this.size = 0;
        this.first = null;
        this.rear = null;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     *
     * @return
     */
    public LinearNode<T> getFirst() {
        return first;
    }

    /**
     *
     * @param first
     */
    public void setFirst(LinearNode<T> first) {
        this.first = first;
    }

    /**
     *
     * @return
     */
    public LinearNode<T> getRear() {
        return rear;
    }

    /**
     *
     * @param rear
     */
    public void setRear(LinearNode<T> rear) {
        this.rear = rear;
    }

    /**
     *
     * @param element
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        if (isEmpty()) {
            this.first = newNode;
        } else {
            this.rear.setNext(newNode);
        }

        this.rear = newNode;
        ++(this.size);
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A queue está vazia");
        } else {
            T result = this.first.getElement();
            this.first = this.first.getNext();
            --(this.size);
            return result;
        }
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A queue está vazia");
        } else {
            return this.first.getElement();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
        /*if (this.size == 0) {
            return true;
        }
        return false;*/
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

}
