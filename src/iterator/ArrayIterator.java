package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class ArrayIterator<T> implements Iterator<T> {

    private int size;
    private int current;
    private T[] elements;

    /**
     * Constructor method that allows you to create a new {@link ArrayIterator}
     * instance
     *
     * @param collection item list
     * @param size number of the elements in this list
     */
    public ArrayIterator(T[] collection, int size) {
        this.elements = collection;
        this.size = size;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return (current < size);
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        current++;
        return this.elements[current - 1];

    }
}
