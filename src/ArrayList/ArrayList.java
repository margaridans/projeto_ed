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
     * Método construtor vazio que permite criar uma nova instância de
     * {@link ArrayList} usando a capacidade do padrão
     *
     */
    public ArrayList() {
        this.first = 0;
        this.last = 0;
        this.counter = 0;
        this.list = (T[]) new Object[this.DEFAULT_CAPACITY];
    }

    /**
     * Método construtor que permite criar uma nova instância de
     * {@link ArrayList} usando a capacidade específica
     *
     * @param initialCapacity capacidade inicial do array
     */
    public ArrayList(int initialCapacity) {
        this.first = 0;
        this.last = 0;
        this.counter = -1;
        this.list = (T[]) new Object[initialCapacity];
    }

    /**
     * Remove o primeiro elemeno do arrayList
     *
     * @return o primeiro elemento do arrayList
     * @throws EmptyCollectionException é lançada se o arrayList estiver vazio.
     */
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

    /**
     * Remove o último elemento deste ArrayList
     *
     * @return o último elemento do arrayList
     * @throws EmptyCollectionException é lançada se o arrayList estiver vazio.
     */
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

    /**
     * Remove um elemento específico do arrayList
     *
     * @param element específico a ser removido desse arrayList
     * @return o elemento removido
     * @throws EmptyCollectionException é lançada se o arrayList estiver vazio.
     * @throws ElementNotFoundException é lançada se o elemento não for
     * encontrado.
     */
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

    /**
     * Método que retorna uma referência para o primeiro elemento do arrayList
     *
     * @return uma referência para o primeiro elemento do arrayList
     * @throws EmptyCollectionException é lançada se o arrayList estiver vazio.
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia");
        } else {
            return this.list[this.first];
        }
    }

    /**
     * Método que retorna uma referência para o último elemento do arrayList
     *
     * @return uma referência para o último elemento do arrayList
     * @throws EmptyCollectionException é lançada se o arrayList estiver vazio.
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia");
        } else {
            return this.list[this.last - 1];
        }
    }

    /**
     * Método que vê se o arrayList contêm o elemento especificado
     *
     * @param target alvo/elemento que está a ser procurado no arrayList
     * @return true se o arrayList contem esse elemento, false se não contem
     */
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

    /**
     * Método que vê se o arrayList está vazio ou não
     *
     * @return true se estiver vazio e falso caso não esteja vazio
     */
    @Override
    public boolean isEmpty() {
        return (this.last == 0);
    }

    /**
     * Método que retorna o número de elementos do arrayList
     * @return número de elementos do arrayList
     */
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

    /**
     * Método que retorna um iterador para os elementos do arrayList
     * @return um iterador para os elementos do arrayList
     */
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
