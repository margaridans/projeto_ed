package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import exceptions.EmptyQueueException;
import java.util.Iterator;


/**
 * @param <T> T element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public interface BinaryTreeADT<T> {

    /**
     * Returns a reference to the root element.
     *
     * @return a reference to the root
     */
    public T getRoot();


    /**
     * Return true if this binary tree is empty and false otherwise.
     *
     * @return true if this binary tree is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this binary tree.
     *
     * @return the integer number of the elements in this tree
     */
    public int size();

    /**
     * Returns true if the binary tree contains an element that matches the specified.
     * element and false otherwise
     *
     * @param targetElement the element being sought in the tree.
     * @return true if the tree contains the target element.
     */
    public boolean contains(T targetElement);

    /**
     * Returns a reference to the specified element if it is found in this binary tree.
     * throws an exception if the specified element is not found.
     *
     * @param targetElement the element being sought in the tree.
     * @return a reference to the specified element.
     * @throws ElementNotFoundException if Element not found
     */
    public T find(T targetElement) throws ElementNotFoundException;

    /**
     * Returns the string representation of the binary tree.
     *
     * @return a string representation of the binary tree.
     */
    public String toString();

    /**
     * Performs an in order traversal on this binary tree by calling an
     * overloaded, recursive in order method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree.
     */
    public Iterator<T> iteratorInOrder();

    /**
     * Performs a pre order traversal on this binary tree by calling an
     * overloaded, recursive pre order method that starts.
     * with the root.
     *
     * @return an iterator over the elements of this binary tree.
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * Performs a post order traversal on this binary tree by
     * calling an overloaded, recursive post order
     * method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    public Iterator<T> iteratorPostOrder();

    /**
     * Performs a level order traversal on the binary tree,
     * using a queue.
     *
     * @return an iterator over the elements of this binary tree
     * @throws EmptyCollectionException if an empty collection exception occurs
     * @throws EmptyQueueException if an empty Queue exception occurs
     */
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException, EmptyQueueException;

}