package interfaces;

import Exceptions.EmptyCollectionException;
import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;

/**
 * NetworkADT defines the interface to a network
 *
 * @param <T> T Element
 * @author Bernardino Silva - 8140277
 * @author Rui Bessa - 8140210
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     */
    public void addEdge(T vertex1, T vertex2, int weight);

    /**
     * Returns the weight of the shortest path in this network
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     * @throws EmptyQueueException if Queue is empty
     * @throws EmptyStackException if a peek is attempted on empty stack
     * @throws EmptyCollectionException is an empty collection exception occurs
     */
    public double shortestPathWeight(T vertex1, T vertex2) throws EmptyQueueException, EmptyStackException, EmptyCollectionException;

}
