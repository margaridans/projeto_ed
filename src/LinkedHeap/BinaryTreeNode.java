package LinkedHeap;

/**
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public class BinaryTreeNode<T> {

    protected T element;
    protected BinaryTreeNode<T> left;
    protected BinaryTreeNode<T> right;

    /**
     * Método construtor que permite criar uma nova instância de
     * {@link BinaryTreeNode} com os dados específicos
     *
     * @param element o elemento que se tornará parte do novo nó da árvore
     */
    public BinaryTreeNode(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    /**
     * Retorna o número de filhos não nulos deste nó.
     *
     * @return o número inteiro de filhos não nulos desse nó
     */
    public int numChildren() {
        int children = 0;
        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }
}
