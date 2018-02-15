package interfaces;

import Exceptions.EmptyCollectionException;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
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
     * @throws Exceptions.EmptyCollectionException
     */
    public T dequeue() throws EmptyCollectionException ;

    /**
     * Returns without removing the element at the front of this queue the first
     * element in this queue
     *
     * @return the element at the front of this queue
     * @throws Exceptions.EmptyCollectionException
     */
    public T first() throws EmptyCollectionException ;

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
