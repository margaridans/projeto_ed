package interfaces;

import Exceptions.ElementNotFoundException;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to this arrayList at the front of this
     * arrayList.
     *
     * @param element The element to be added to this arrayList
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to this arrayList at the rear of this
     * arrayList.
     *
     * @param element The element to be added to this arrayList
     */
    public void addToRear(T element);

    /**
     * Adds the specified element to this arrayList after...
     *
     * @param element The element to be added to this arrayList.
     * @param target reference to a list element
     * @throws ElementNotFoundException if element not found
     */
    public void addAfter(T element, T target) throws ElementNotFoundException;
}
