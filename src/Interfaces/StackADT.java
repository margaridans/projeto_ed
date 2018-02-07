package interfaces;

import Exceptions.EmptyStackException;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T>
 */
public interface StackADT<T> {

    /**
     * Adds the specified element to the top of this stack
     *
     * @param element generic element to be pushed onto stack
     */
    public void push(T element);

    /**
     * Removes the element at the top of this stack and
     * returns a reference to it
     *
     * @return T element removed from top of stack
     * @throws EmptyStackException if a pop is attempted on empty stack
     */
    public T pop() throws EmptyStackException;

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack
     *
     * @return T element on top of stack
     * @throws EmptyStackException if a peek is attempted on empty stack
     */
    public T peek() throws EmptyStackException;

    /**
     * Verify that the list is empty
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty();

    /**
     * Get the number of elements in the list
     *
     * @return number of elements
     */
    public int size();

    @Override
    public String toString();

}
