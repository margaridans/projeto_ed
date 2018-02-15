package LinkedHeap;

import Exceptions.EmptyCollectionException;
import interfaces.HeapADT;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {

    public HeapNode<T> lastNode;

    /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link LinkedHeap}
     */
    public LinkedHeap() {
        super();
    }

    /**
     *
     * Adiciona o elemento especificado a esta heap na posição apropriada de
     * acordo com seu valor-chave.
     *
     * @param object O elemento a ser adicionado a esta heap.
     */
    @Override
    public void addElement(T object) {
        HeapNode<T> node = new HeapNode<>(object);

        if (root == null) {
            root = node;
        } else {
            HeapNode<T> next_parent = getNextParentAdd();
            if (next_parent.left == null) {
                next_parent.left = node;
            } else {
                next_parent.right = node;
            }

            node.parent = next_parent;
        }
        lastNode = node;
        ++count;

        if (count > 1) {
            heapifyAdd();
        }
    }

    /**
     * Remova o elemento com o valor mais baixo neste heap e retorna uma
     * referência para ele.
     *
     * @return O elemento com o valor mais baixo neste heap
     * @throws EmptyCollectionException é lançada se a heap estiver vazia.
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Heap Vazia");
        }

        T minElement = root.element;

        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.parent.left == lastNode) {
                lastNode.parent.left = null;
            } else {
                lastNode.parent.right = null;
            }

            root.element = lastNode.element;
            lastNode = next_last;
            heapifyRemove();
        }
        --count;

        return minElement;
    }


    /**
     * Método que retorna o nó que será o pai deste novo nó
     *
     * @return o nó que será o pai deste novo nó
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.parent.left != result)) {
            result = result.parent;
        }

        if (result != root) {
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = (HeapNode<T>) result.parent.right;
                while (result.left != null) {
                    result = (HeapNode<T>) result.left;
                }
            }
        } else {
            while (result.left != null) {
                result = (HeapNode<T>) result.left;
            }
        }

        return result;
    }

    /**
     * Reordena a heap após a inserção
     */
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;

        temp = next.element;

        while ((next != root) && (((Comparable) temp).compareTo(next.parent.element) < 0)) {
            next.element = next.parent.element;
            next = next.parent;
        }

        next.element = temp;
    }

    /**
     * Reordena a heap após a remoção
     */
    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.left;
        HeapNode<T> right = (HeapNode<T>) node.right;
        HeapNode<T> next;

        if ((left == null) && (right == null)) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.element).compareTo(right.element) < 0) {
            next = left;
        } else {
            next = right;
        }

        temp = node.element;
        while ((next != null) && (((Comparable) next.element).compareTo(temp) < 0)) {
            node.element = next.element;
            node = next;
            left = (HeapNode<T>) node.left;
            right = (HeapNode<T>) node.right;

            if ((left == null) && (right == null)) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.element).compareTo(right.element) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
        node.element = temp;
    }

    /**
     *
     * Retorna o nó que será o último nó após uma remoção. Retorna uma
     * referência para o novo último nó na heap
     *
     * @return o nó que será o último após uma remoção.
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.parent.left == result)) {
            result = result.parent;
        }

        if (result != root) {
            result = (HeapNode<T>) result.parent.left;
        }

        while (result.right != null) {
            result = (HeapNode<T>) result.right;
        }

        return result;
    }

    /**
     * Remove todos os elementos da heap
     *
     * @throws EmptyCollectionException é lançada se a heap estiver vazia.
     */
    public void removeAllElements() throws EmptyCollectionException {
        while (!isEmpty()) {
            this.removeMin();
        }
    }

    @Override
    public T findMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
