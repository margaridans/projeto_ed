package ArrayList;

import Exceptions.ElementNotFoundException;
import interfaces.UnorderedListADT;

/**
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Creates an empty list using the default capacity
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Creates an empty list using the default capacity
     *
     * @param initialCapacity Capacity of array list
     */
    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void addToFront(T element) {
        if (size() == list.length) {
            expandCapacity();
        }

        //Shift the elements
        for (int i = last; i > 0; --i) {
            list[i] = list[i - 1];
        }

        list[0] = element;
        ++last;
    }

    @Override
    public void addToRear(T element) {
        if (size() == list.length) {
            expandCapacity();
        }

        list[last] = element;
        ++last;
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        if (size() == list.length) {
            expandCapacity();
        }

        int i = 0;

        while (i < last && !target.equals(list[i])) {
            ++i;
        }

        if (i == last) {
            throw new ElementNotFoundException("Element Not Found - UnorderedList");
        }
        ++i;
        for (int k = last; k > i; --k) {
            list[k] = list[k - 1];
        }

        list[i] = element;
        ++last;
    }
}