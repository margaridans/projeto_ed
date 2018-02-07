package interfaces;

import Exceptions.EmptyCollectionException;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
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
