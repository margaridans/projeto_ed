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
     * Cria uma lista vazia usando a capacidade padrão
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Cria uma lista vazia usando a capacidade específica
     *
     * @param initialCapacity capacidade inicial do array
     */
    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

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
