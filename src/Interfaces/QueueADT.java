package interfaces;

import exceptions.EmptyQueueException;

/**
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public interface QueueADT<T> {

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    public void enqueue(T element);

    /**
     * Removes and returns the element at the front of this queue
     *
     * @return the element at the front of this queue
     * @throws EmptyQueueException if Queue is empty
     */
    public T dequeue() throws EmptyQueueException;

    /**
     * Returns without removing the element at the front of this queue the first
     * element in this queue
     *
     * @return the element at the front of this queue
     * @throws EmptyQueueException if an empty Queue exception occurs
     */
    public T first() throws EmptyQueueException;

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this queue
     *
     * @return the integer representation of this size of queue
     */
    public int size();

    /**
     * Returns a string representation of this queue
     *
     * @return the string representation of this queue
     */
    @Override
    public String toString();
}
