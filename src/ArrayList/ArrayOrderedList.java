package ArrayList;

import interfaces.OrderedListADT;
import Iterator.ArrayIterator;

/**
 * @param <T> T Element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    private ArrayIterator<T> iterator;

    /**
     * Creates an empty list using default capacity
     */
    public ArrayOrderedList() {
        super();
    }

    /**
     * Creates an empty list using specified capacity
     *
     * @param initialCapacity Initial capacity of array
     */
    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adds the specified Comparable element to the list, keeping the elements
     * in sorted order.
     */
    @Override
    public void add(T element) {
        if (size() == super.list.length)
            expandCapacity();

        Comparable<T> temp = (Comparable<T>) element;

        int count = 0;
        while (count < super.last && temp.compareTo((T) super.list[count]) > 0)
            count++;

        for (int scan2 = super.last; scan2 > count; scan2--)
            super.list[scan2] = super.list[scan2 - 1];

        super.list[count] = element;
        super.last++;
    }

    /**
     * Returns an iterator for the elements in this arrayList.
     *
     * @return an iterator over the elements in this arrayList
     */
    public ArrayIterator<T> getIterator() {
        return this.iterator = new ArrayIterator(super.list, super.size());
    }

}
