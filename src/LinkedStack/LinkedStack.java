package LinkedStack;

import Exceptions.EmptyCollectionException;
import interfaces.StackADT;

/**
 * @param <T> T Element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class LinkedStack<T> implements StackADT<T> {

    private LinearNode top;
    private int size;

    /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link LinkedStack}
     */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Método que retorna uma referência para o elemento do topo
     *
     * @return referência para o elemento do topo
     */
    public LinearNode getTop() {
        return top;
    }

     /**
     *
     * Define o elemento do topo
     *
     * @param top elemento do topo
     */
    public void setTop(LinearNode top) {
        this.top = top;
    }

    /**
     * Método que retorna o número de elementos da stack
     *
     * @return o número de elementos da stack
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * Define o tamanho da queue
     *
     * @param size tamanho da queue
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Adiciona um elemento específico ao topo da stack
     *
     * @param element elemento a ser adicionado
     */
    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        newNode.setNext(this.top);
        this.top = newNode;
        ++size;
    }

    /**
     * Remove um elemento do topo da stack
     *
     * @return o elemento removido do top da stack
     * @throws EmptyCollectionException é lançada se a stack estiver vazia
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            T result = (T) this.top.getElement();
            this.top = this.top.getNext();
            --size;
            return result;
        }
    }

    /**
     * Método que retorna a referência do elemento que está no topo da stack
     *
     * @return a referência do elemento que está no topo da stack
     * @throws EmptyCollectionException é lançada se a stack estiver vazia
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            return (T) this.top.getElement();
        }
    }

    /**
     * Método que vê se a stack está ou não vazia
     *
     * @return true se estiver vazia, false caso não esteja
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
        /* if (this.size == 0) {
            return true;
        }
        return false;*/
    }

    /**
     * Método que retorna o número de elementos da stack
     *
     * @return o número de elementos da stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Método que retorna uma representação da stack
     *
     * @return uma representação da stack
     */
    @Override
    public String toString() {
        String result = "";
        LinearNode<T> current = this.top;

        while (current != null) {
            result = result + current.getElement().toString() + "\n";
            current = current.getNext();
        }
        return result;
    }

}
