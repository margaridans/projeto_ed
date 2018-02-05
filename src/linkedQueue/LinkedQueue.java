package linkedQueue;

import exceptions.EmptyQueueException;
import interfaces.QueueADT;

/**
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private int size;
    private LinearNode<T> first;
    private LinearNode<T> rear;

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
        LinearNode<T> newNode = new LinearNode<T>(element);

        if (isEmpty()) {
            this.first = newNode;
        } else {
            this.rear.setNext(newNode);
        }

        this.rear = newNode;
        ++(this.size);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("The QUEUE is Empty");
        } else {
            T result = this.first.getElement();
            this.first = this.first.getNext();
            --(this.size);
            return result;
        }
    }

    @Override
    public T first() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("The QUEUE is Empty");
        } else {
            return this.first.getElement();
        }
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

}
