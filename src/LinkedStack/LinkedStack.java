package LinkedStack;
import Exceptions.EmptyStackException;
import interfaces.StackADT;

/**
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public class LinkedStack<T> implements StackADT <T> {

    private LinearNode top;
    private int size;

    /**
     * Create a empty stack
     */
    public LinkedStack () {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push (T element) {
        LinearNode<T> newNode = new LinearNode<T>(element);
        newNode.setNext(this.top);
        this.top = newNode;
        ++size;
    }

    @Override
    public T pop () throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T result = (T)this.top.getElement();
            this.top = this.top.getNext();
            --size;
            return result;
        }
    }

    @Override
    public T peek () throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return (T) this.top.getElement();
        }
    }

    @Override
    public boolean isEmpty () {
        if (this.size == 0) {
            return true;
        }
        return false;
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
