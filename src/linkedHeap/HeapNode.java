package linkedHeap;

/**
 * HeapNode creates a binary tree node with a parent pointer for use in heaps.
 *
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    protected HeapNode<T> parent;

    /**
     * Creates a new heap node with the specified data.
     *
     * @param object the data to be contained within the new heap nodes.
     */
    public HeapNode(T object) {
        super(object);
        parent = null;
    }
}
