package LinkedHeap;

/**
 * BinaryTreenode representa um nó numa árvore binária com um filho à esquerda e
 * à direita.
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
     *
     * Cria um novo nó da árvore com os dados especificados
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
