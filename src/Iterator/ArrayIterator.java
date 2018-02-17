package Iterator;

import java.util.Iterator;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T> T Element
 */
public class ArrayIterator<T> implements Iterator<T> {

    private int size; //tamanho máximo do array
    private int current; //posição atual
    private T[] elements; //guarda referência para o arrayList

    /**
     *
     * Método construtor que permite criar uma nova instância de
     * {@link ArrayIterator}
     *
     * @param elements arrayList
     * @param size número de elementos de«o arrayList
     */
    public ArrayIterator(T[] elements, int size) {
        this.elements = elements;
        this.size = size;
        this.current = 0;
    }

    /**
     * Método que retorna true se houver mais elementos
     *
     * @return true se houver mais elementos, false caso não haja mais elementos
     */
    @Override
    public boolean hasNext() {
        return (current < size);
    }

    /**
     * Método que retorna o próximo elemento da iteração
     *
     * @return o próximo elemento da iteração
     */
    @Override
    public T next() {
        return elements[current++];
    }
}
