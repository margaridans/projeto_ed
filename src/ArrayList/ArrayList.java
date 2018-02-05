package ArrayList;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import interfaces.ListADT;
import Iterator.ArrayIterator;
import java.util.Iterator;

/**
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class ArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int first;
    protected int last;
    private int position;
    protected T[] list;

    /**
     * Creates an empty list using the default capacity
     */
    public ArrayList() {
        this.first = 0;
        this.last = 0;
        this.position = -1;
        this.list = (T[]) new Object[this.DEFAULT_CAPACITY];
    }

    /**
     * Creates an empty list using the specified capacity
     *
     * @param initialCapacity Initial capacity of array
     */
    public ArrayList(int initialCapacity) {
        this.first = 0;
        this.last = 0;
        this.position = -1;
        this.list = (T[]) new Object[initialCapacity];
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        T result;

        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            result = this.list[0];
            --(this.last);
            for (int i = 0; i < this.last; ++i) {
                this.list[this.last] = this.list[i + 1];
            }

            this.list[this.last] = null;
        }
        return result;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        T result;

        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            --(this.last);
            result = this.list[this.last];
            this.list[this.last] = null;
        }
        return result;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        T result;

        if (contains(element)) {
            throw new ElementNotFoundException("Element not Found");
        } else {
            result = this.list[this.position];
            --(this.last);
            //Shift The appropriate elements
            for (int i = 0; i < this.last; ++i) {
                this.list[i] = this.list[i + 1];
            }
        }
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            return this.list[this.first];
        }
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            return this.list[this.last - 1];
        }
    }

    @Override
    public boolean contains(T target) {
        boolean contain = false;
        int i = 0;

        if (!isEmpty()) {
            while (!contain && i < this.last) {
                if (target.equals(this.list[i])) {
                    contain = true;
                    this.position = i;
                } else {
                    ++i;
                }
            }
        }
        return contain;
    }

    @Override
    public boolean isEmpty() {
        return (this.last == 0);
    }

    @Override
    public int size() {
        int count = 0;

        for (int i = 0; i < this.list.length; ++i) {
            if (this.list[i] != null) {
                ++count;
            }
        }
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        ArrayIterator<T> iterator = new ArrayIterator(this.list, this.last);

        return iterator;
    }

    /**
     * Expand capacity if this list is full
     */
    protected void expandCapacity() {
        T[] temporaryArray = (T[]) new Object[this.list.length * 2];

        for (int i = 0; i < this.last; ++i) {
            temporaryArray[i] = this.list[i];
        }
        this.list = temporaryArray;
    }
}
