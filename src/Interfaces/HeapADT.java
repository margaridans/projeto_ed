package interfaces;

import exceptions.EmptyCollectionException;

/**
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public interface HeapADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified object to this heap.
     *
     * @param object the element to added to this heap.
     */
    public void addElement(T object);

    /**
     * Removes element with the lowest value from this heap.
     *
     * @return the element with the lowest value from this heap.
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public T removeMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the element with the lowest value in this heap.
     *
     * @return a reference to the element with the lowest value in this heap
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public T findMin() throws EmptyCollectionException;
}
