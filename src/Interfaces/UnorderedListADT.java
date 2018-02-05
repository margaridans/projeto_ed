package interfaces;

import exceptions.ElementNotFoundException;

/**
 * @param <T> T element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
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
