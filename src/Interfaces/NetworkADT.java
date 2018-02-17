package interfaces;

import Exceptions.EmptyCollectionException;

/**
 * NetworkADT defines the interface to a network
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 * @param <T> T Element
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     */
    public void addEdge(T vertex1, T vertex2, double weight);

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     * @throws Exceptions.EmptyCollectionException if an empty collection
     * exception occurs
     */
    public double shortestPathWeight(T vertex1, T vertex2)  throws EmptyCollectionException;

}
