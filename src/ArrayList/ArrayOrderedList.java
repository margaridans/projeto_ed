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
     * Cria uma lista vazia usando a capacidade padrão
     */
    public ArrayOrderedList() {
        super();
    }

    /**
     * Cria uma lista vazia usando a capacidade específica
     *
     * @param initialCapacity capacidade inicial do array
     */
    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adiciona um elemento específico comparável mantendo os elementos
     * ordenados
     */
    @Override
    public void add(T element) {
        if (size() == super.list.length) {
            expandCapacity();
        }

        Comparable<T> temp = (Comparable<T>) element;

        int count = 0;
        while (count < super.last && temp.compareTo((T) super.list[count]) > 0) {
            count++;
        }

        for (int count2 = super.last; count2 > count; count2--) {
            super.list[count2] = super.list[count2 - 1];
        }

        super.list[count] = element;
        super.last++;
    }

    /**
     * Retorna um iterador para os elementos deste arrayList
     *
     * @return um iterados sobre os elementos neste arrayList
     */
    public ArrayIterator<T> getIterator() {
        return this.iterator = new ArrayIterator(super.list, super.size());
    }

}
