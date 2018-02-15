package interfaces;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public interface ListADT<T> extends Iterable<T> {

    /**
     * Removes and returns the first element from this arrayList.
     *
     * @return the first element from the arrayList
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public T removeFirst() throws EmptyCollectionException;

    /***
     * Removes and returns the last element from this arrayList.
     *
     * @return the last element from this arrayList
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the element from this arrayList.
     *
     * @param element The element to be removed from the arrayList
     * @return The element removed from this arrayList
     * @throws ElementNotFoundException if element not found
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException;


    /**
     * Returns a reference to the first element in this arrayList.
     *
     * @return a reference to the first element in this arrayList.
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns a reference to the last element in this arrayList.
     *
     * @return a reference to the last element in this arrayList.
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public T last() throws EmptyCollectionException;

    /**
     * Returns true if this arrayList contains the specified target element.
     *
     * @param target the target that is being sought in the arrayList.
     * @return true if the arrayList contains this element
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public boolean contains(T target) throws EmptyCollectionException;

    /**
     * Return true if the arrayList contains no elements.
     *
     * @return true if this arrayList contains no elements.
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this arrayList
     *
     * @return the integer representation of number of elements in this arrayList
     */
    public int size();

    /**
     * Returns an iterator for the elements in this arrayList.
     *
     * @return an iterator over the elements in this arrayList
     */
    @Override
    public Iterator<T> iterator();

    /**
     * Returns a string representation of this arrayList.
     *
     * @return a string representation of this arrayList
     */
    @Override
    public String toString();

}
