package ArrayList;

import Exceptions.ElementNotFoundException;
import interfaces.UnorderedListADT;

/**
 * @param <T> T Element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link ArrayUnorderedList} usando a capacidade padrão
     *
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * permite criar uma nova instância de {@link ArrayUnorderedList} usando a
     * capacidade específica
     *
     * @param initialCapacity capacidade inicial do array
     */
    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     *
     * Adiciona o elemento especificado a este arrayList na parte da frente
     * deste arrayList.
     *
     * @param element elemento a ser adicionado a este arrayList
     */
    @Override
    public void addToFront(T element) {
        if (size() == list.length) {
            expandCapacity();
        }

        //Desloca os elementos
        for (int i = last; i > 0; --i) {
            list[i] = list[i - 1];
        }

        list[0] = element;
        ++last;
    }

    /**
     * Adiciona o elemento especificado a este arrayList na parte traseira deste
     * arrayList.
     *
     * @param element elemento a ser adicionado a este arrayList
     */
    @Override
    public void addToRear(T element) {
        if (size() == list.length) {
            expandCapacity();
        }

        list[last] = element;
        ++last;
    }

    /**
     * Adiciona um elemento específico depois de qualquer coisa a este arrayList
     *
     * @param element elemento a ser adicionado a este arrayList
     * @param target referência a um elemento de lista
     * @throws ElementNotFoundException se o elemento não for encontrado
     */
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
            throw new ElementNotFoundException("Elemento não encontrado");
        }
        ++i;

        for (int k = last; k > i; --k) {
            list[k] = list[k - 1];
        }

        list[i] = element;
        ++last;
    }
}
