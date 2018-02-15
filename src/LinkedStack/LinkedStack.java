
package LinkedStack;
import Exceptions.EmptyCollectionException;
import interfaces.StackADT;

/**
 * @param <T> T Element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class LinkedStack<T> implements StackADT <T> {

    private LinearNode top;
    private int size;

    /**
     * Cria uma LinkedStack vazia
     */
    public LinkedStack () {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push (T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        newNode.setNext(this.top);
        this.top = newNode;
        ++size;
    }

    @Override
    public T pop () throws EmptyCollectionException  {
        if (isEmpty()) {
            throw new EmptyCollectionException ();
        } else {
            T result = (T)this.top.getElement();
            this.top = this.top.getNext();
            --size;
            return result;
        }
    }

    @Override
    public T peek () throws EmptyCollectionException  {
        if (isEmpty()) {
            throw new EmptyCollectionException ();
        } else {
            return (T) this.top.getElement();
        }
    }

    @Override
    public boolean isEmpty () {
        return this.size == 0;
        /* if (this.size == 0) {
            return true;
        }
        return false;*/
    }

    @Override
    public int size () {
        return this.size;
    }

    @Override
    public String toString () {
        String result = "";
        LinearNode<T> current = this.top;

        while (current != null) {
            result = result + current.getElement().toString() + "\n";
            current = current.getNext();
        }
        return result;
    }

}
