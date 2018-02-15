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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinearNode<T> getFirst() {
        return first;
    }

    public void setFirst(LinearNode<T> first) {
        this.first = first;
    }

    public LinearNode<T> getRear() {
        return rear;
    }

    public void setRear(LinearNode<T> rear) {
        this.rear = rear;
    }

    
    
    /**
     * Creates an empty LinkedQueue
     */
    public LinkedQueue() {
        this.size = 0;
        this.first = null;
        this.rear = null;
    }

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

    @Override
    public T dequeue() throws EmptyCollectionException  {
        if (isEmpty()) {
            throw new EmptyCollectionException ("The QUEUE is Empty");
        } else {
            T result = this.first.getElement();
            this.first = this.first.getNext();
            --(this.size);
            return result;
        }
    }

    @Override
    public T first() throws EmptyCollectionException  {
        if (isEmpty()) {
            throw new EmptyCollectionException ("The QUEUE is Empty");
        } else {
            return this.first.getElement();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
        /*if (this.size == 0) {
            return true;
        }
        return false;*/
    }

    @Override
    public int size() {
        return this.size;
    }

}
