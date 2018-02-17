package LinkedHeap;

/**
 *
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T> T Element
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    protected HeapNode<T> parent;

    /**
     * Método construtor que permite criar uma nova instância de
     * {@link HeapNode} com os dados específicados
     *
     * @param object os dados que vão pertencer aos novos nós de heap.
     */
    public HeapNode(T object) {
        super(object);
        parent = null;
    }  
}
