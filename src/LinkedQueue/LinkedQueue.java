package LinkedQueue;

import Exceptions.EmptyCollectionException;
import interfaces.QueueADT;

/**
 * @param <T> T element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private int size;
    private LinearNode<T> first;
    private LinearNode<T> rear;

    /**
     * Método construtor vazio que permite criar uma nova instância de
     * {@link LinkedQueue}
     */
    public LinkedQueue() {
        this.size = 0;
        this.first = null;
        this.rear = null;
    }

    /**
     * Método que retorna o número de elementos da queue
     *
     * @return o número de elementos da queue
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
     * Método que retorna uma referência para o primeiro elemento
     *
     * @return referência para o primeiro elemento
     */
    public LinearNode<T> getFirst() {
        return first;
    }

     /**
     *
     * Define o primeiro elemento
     *
     * @param first primeiro elemento
     */
    public void setFirst(LinearNode<T> first) {
        this.first = first;
    }

    /**
     * Método que retorna uma referência para o último elemento
     *
     * @return referência para o último elemento
     */
    public LinearNode<T> getRear() {
        return rear;
    }

    /**
     *
     * Define o último elemento
     *
     * @param rear último elemento
     */
    public void setRear(LinearNode<T> rear) {
        this.rear = rear;
    }

    /**
     * Adiciona um elemento específico à cauda da queue
     *
     * @param element elemento a ser adicionado à cauda
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        if (isEmpty()) {
            this.first = newNode;
        } else {
            this.rear.setNext(newNode);
        }

        this.rear = newNode;
        ++(this.size);
    }

    /**
     * Remove um elemento específico à cabeça da queue
     *
     * @return o elemento removido da cabeça
     * @throws EmptyCollectionException é lançada se a queue estiver vazia
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A queue está vazia");
        } else {
            T result = this.first.getElement();
            this.first = this.first.getNext();
            --(this.size);
            return result;
        }
    }

    /**
     * Método que retorna o primeiro elemento da queue
     *
     * @return o primeiro elemento da queue
     * @throws EmptyCollectionException é lançada se a queue estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A queue está vazia");
        } else {
            return this.first.getElement();
        }
    }

    /**
     * Método que vê se a queue está ou não vazia
     *
     * @return true se estiver vazia, false caso não esteja
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
        /*if (this.size == 0) {
            return true;
        }
        return false;*/
    }

    /**
     * Método que retorna o número de elementos da queue
     *
     * @return o número de elementos da queue
     */
    @Override
    public int size() {
        return this.size;
    }

}
