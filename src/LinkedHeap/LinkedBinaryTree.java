
package LinkedHeap;

import ArrayList.ArrayUnorderedList;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.EmptyQueueException;
import interfaces.BinaryTreeADT;
import LinkedQueue.LinkedQueue;

import java.util.Iterator;

/**
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    
    
    /**
     * Creates an empty binary tree
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary tree
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<>(element);
    }

    @Override
    public T getRoot() {
        return (T) this.root;
    }

    @Override
    public boolean isEmpty() {
        return (this.count == 0);
    }

    @Override
    public int size() {
        return this.count;
    }

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

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null)
            throw new ElementNotFoundException("binary tree");
        return (current.element);
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive in order traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preOrder(this.root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive pre order traversal
     *
     * @param node     node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preOrder(node.left, tempList);
            preOrder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postOrder(root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive postOrder traversal
     *
     * @param node     to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postOrder(node.left, tempList);
            postOrder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException, EmptyQueueException {
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
     * Returns a reference to the specified target element if it is found in
     * this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next          the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null)
            return null;
        if (next.element.equals(targetElement))
            return next;
        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
        if (temp == null)
            temp = findAgain(targetElement, next.right);
        return temp;
    }
}
