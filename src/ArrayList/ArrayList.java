package ArrayList;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import interfaces.ListADT;
import Iterator.ArrayIterator;
import java.util.Iterator;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public class ArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 100; //tamanho especifico do array
    private int first; //cabeça
    protected int last; //cauda
    private int counter; 
    protected T[] list; //array do tipo T

    /**
     *
     * Cria uma lista vazia usando a capacidade padrão
     */
    public ArrayList() {
        this.first = 0;
        this.last = 0;
        this.counter = 0;
        this.list = (T[]) new Object[this.DEFAULT_CAPACITY];
    }

    /**
     * Cria uma lista vazia usando a capacidade específica
     *
     * @param initialCapacity capacidade inicial do array
     */
    public ArrayList(int initialCapacity) {
        this.first = 0;
        this.last = 0;
        this.counter = -1;
        this.list = (T[]) new Object[initialCapacity];
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        T result;

        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia");
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
            throw new EmptyCollectionException("Lista vazia");
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
            throw new ElementNotFoundException("Elemento não encontrado");
        } else {
            result = this.list[this.counter];
            --(this.last);
            //Desloca os elementos apropriados
            for (int i = 0; i < this.last; ++i) {
                this.list[i] = this.list[i + 1];
            }
        }
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia");
        } else {
            return this.list[this.first];
        }
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia");
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
                    this.counter = i;
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
     * Expande a capacidade se a lista estiver cheia
     */
    protected void expandCapacity() {
        T[] temporaryArray = (T[]) new Object[this.list.length * 2];

        for (int i = 0; i < this.last; ++i) {
            temporaryArray[i] = this.list[i];
        }
        this.list = temporaryArray;
    }
}
