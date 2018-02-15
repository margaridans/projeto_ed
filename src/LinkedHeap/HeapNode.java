package LinkedHeap;

/**
 *
 * HeapNode cria um nó de árvore binária com um ponteiro pai para uso em heaps.
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    protected HeapNode<T> parent;

    /**
     * Cria um novo nó de heaps com os dados especificados.
     *
     * @param object os dados que vão pertencer aos novos nós de heap.
     */
    public HeapNode(T object) {
        super(object);
        parent = null;
    }
}
