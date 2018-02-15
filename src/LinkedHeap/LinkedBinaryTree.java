package LinkedHeap;

import ArrayList.ArrayUnorderedList;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import interfaces.BinaryTreeADT;
import LinkedQueue.LinkedQueue;

import java.util.Iterator;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count; //Contagem de elementos na árvore
    protected BinaryTreeNode<T> root; //Referência para o root

    /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link LinkedBinaryTree}
     *
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     *
     * Método construtor que permite criar uma nova instância de
     * {@link ArrayIterator} usando o elemento especificado para ser a sua raíz
     *
     * @param element o elemento que se tornará a raiz da árvore
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<>(element);
    }

    /**
     * Método que retorna uma referência para o elemento da raíz
     *
     * @return uma referência para a raíz
     */
    @Override
    public T getRoot() {
        return (T) this.root;
    }

    /**
     * Método que vê se a árvore binária está vazia ou não
     *
     * @return true se estiver vazia, false se não estiver vazia
     */
    @Override
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * Método que retorna o número de elementos da árvore binária
     *
     * @return o número de elementos da árvore binária
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Método que vê se existe um elemento específico na árvore binária
     *
     * @param targetElement elemento específico a ser procurado na árvore
     * @return true esse elemento existir na árvore, false se não existir
     */
    @Override
    public boolean contains(T targetElement) {
        boolean found = false;
        try {
            T temp = find(targetElement);
            found = true;
        } catch (ElementNotFoundException ElementNotFound) {
            found = false;
        }
        return found;
    }

    /**
     * Método que tenta encontrar um elemento específico na árvore binária.
     *
     * @param targetElement elemento a ser encontrado na árvore binária
     * @return uma referência para esse elemento específico
     * @throws ElementNotFoundException é lançada se não se encontrar esse
     * elemento
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null) {
            throw new ElementNotFoundException("Árvore binária");
        }
        return (current.element);
    }

    /**
     *
     * Executa uma travessia inorder nesta árvore binária ao chamar um método
     * inorder sobrecarregado e recursivo que começa com a raiz.
     *
     * @return um iterador de ordem sobre esta árvore binária
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(root, tempList);
        return tempList.iterator();
    }

    /**
     * É uma travessia em-ordem que é realizada ao visitar o filho esquerdo do
     * nó, o nó, e só depois visitar o filho direito
     *
     * @param node nó a ser usado como a raiz para esta travessia
     * @param tempList lista temporária para uso nesta travessia
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    /**
     *
     * Executa um percurso de pré-ordem nessa árvore binária ao chamar um método
     * de pré-ordem recursivo que começa com a raiz.
     *
     * @return um iterador sobre os elementos desta árvore binária
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preOrder(this.root, tempList);
        return tempList.iterator();
    }

    /**
     * É uma travessia pré-ordem que é realizada ao visitar cada nó e só depois
     * visita os seus filhos: esquerdo/direito
     *
     * @param node nó a ser usado como a raiz para esta travessia
     * @param tempList lista temporária para uso nesta travessia
     */
    protected void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preOrder(node.left, tempList);
            preOrder(node.right, tempList);
        }
    }

    /**
     * Executa um percurso de pós-ordem nessa árvore binária ao chamar um método
     * de pós-ordem recursivo que começa com a raiz.
     *
     * @return um iterador sobre os elementos desta árvore binária
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postOrder(root, tempList);
        return tempList.iterator();
    }

    /**
     * É uma travessia pós-ordem que é realizada ao visitar primeiros os filhos:
     * esquerdo/direito e só depois visita o nó
     *
     *
     * @param node nó a ser usado como a raiz para esta travessia
     * @param tempList lista temporária para uso nesta travessia
     */
    protected void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postOrder(node.left, tempList);
            postOrder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    /**
     *
     * Executa uma passagem de nível na árvore binária, usando uma fila
     *
     * @return um iterador sobre os elementos desta árvore binária
     * @throws EmptyCollectionException
     */
    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        LinkedQueue<T> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        BinaryTreeNode<T> current;

        nodes.enqueue((T) root);

        while (!nodes.isEmpty()) {
            current = (BinaryTreeNode<T>) nodes.dequeue();

            if (current != null) {
                templist.addToRear(current.element);
                nodes.enqueue((T) current.left);
                nodes.enqueue((T) current.right);
            }
        }
        return templist.iterator();
    }

    /**
     *
     * Retorna uma referência ao elemento-alvo especificado se for encontrada
     * nessa árvore binária.
     *
     * @param targetElement elemento que está a ser procurado na árvore
     * @param next o elemento para começar a procurar.
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }
        if (next.element.equals(targetElement)) {
            return next;
        }
        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }
        return temp;
    }
}
