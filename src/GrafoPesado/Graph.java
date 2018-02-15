package GrafoPesado;

import ArrayList.ArrayUnorderedList;
import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import interfaces.GraphADT;
import java.util.Iterator;
import LinkedQueue.LinkedQueue;
import LinkedStack.LinkedStack;

/**
 * @param <T> T element
 * 
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Graph <T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; // Número de vértices num grafo 
    protected boolean[][] adjMatrix; // matriz de adjacências
    protected T[] vertices; // valores dos vértices

     /**
     * Método construtor que permite a criação de uma instância de
     * {@link Graph} vazia
     */
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[200][200];
        this.vertices = (T[]) new Object[200];
    }
    
    
  

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Returns true if given index is valid.
     *
     * @param index the indes specified
     * @return true or false
     */
    protected boolean indexIsValid(int index) {
        return ((index < this.numVertices) && (index >= 0));
    }

    /**
     * Returns the index value of the first occurrence of the vertex.
     *
     * @param vertex Vertex of the graph
     * @return -1 if the key is not found
     */
    protected int getIndex(T vertex) {
        for (int i = 0; i < this.numVertices; ++i) {
            if (this.vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;

        for (int i = 0; i <= numVertices; ++i) {
            this.adjMatrix[numVertices][i] = false;
            this.adjMatrix[i][numVertices] = false;
        }
        ++(this.numVertices);
    }

    /**
     * Creates new arrays to store the contents of the graph with twice
     * capacity.
     */
    protected void expandCapacity() {
        T[] largerVertices = (T[]) new Object[vertices.length * 2];
        boolean[][] largerAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < this.numVertices; ++i) {
            for (int k = 0; k < this.numVertices; ++k) {
                largerAdjMatrix[i][k] = this.adjMatrix[i][k];
            }
            largerVertices[i] = this.vertices[i];
        }
        this.vertices = largerVertices;
        this.adjMatrix = largerAdjMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < this.numVertices; ++i) {
            if (vertex.equals(this.vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * Removes a vertex at the given index from the graph. Note that this mays
     * affect the values of other vertices.
     *
     * @param index
     */
    private void removeVertex(int index) {
        if (indexIsValid(index)) {
            --(this.numVertices);

            for (int i = index; i < this.numVertices; ++i) {
                this.vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < this.numVertices; ++i) {
                for (int k = 0; k <= this.numVertices; ++k) {
                    this.adjMatrix[i][k] = this.adjMatrix[i + 1][k];
                }
            }

            for (int i = index; i < this.numVertices; ++i) {
                for (int k = 0; k < this.numVertices; ++k) {
                    this.adjMatrix[k][i] = this.adjMatrix[k][i + 1];
                }
            }
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Removes an Edge between two vertices of graph
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    private void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = false;
            this.adjMatrix[index2][index1] = false;
        }
    }

    @Override
    public Iterator iteratorBFS(T startVertex) throws EmptyQueueException {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * BFS Iterator
     *
     * @param startIndex start index
     * @return iterator with list of visited items
     * @throws EmptyQueueException if an Empty Queue occurs
     */
    private Iterator<T> iteratorBFS(int startIndex) throws EmptyQueueException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);

            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up.
             */
            for (int i = 0; i < this.numVertices; ++i) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorDFS(T startVertex) throws EmptyStackException {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * DFS Iterator
     *
     * @param startIndex start index
     * @return iterator with list of visited items
     */
    private Iterator<T> iteratorDFS(int startIndex) throws EmptyStackException {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; ++i) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < this.numVertices) && !found; ++i) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(this.vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) throws EmptyQueueException, EmptyStackException {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Obter um iterador com a lista de locais entre dois vertices
     *
     * @param startIndex start index
     * @param targetIndex stop index
     * @return an iterator
     */
    private Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyQueueException, EmptyStackException {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return resultList.iterator();
        }

        Iterator<Integer> iterator = iteratorShortestPathIndices(startIndex, targetIndex);

        while (iterator.hasNext()) {
            resultList.addToRear(this.vertices[(iterator.next())]);
        }

        return resultList.iterator();
    }

    /**
     * Returns an iterator that contains the indices of the vertices that are in
     * the shortest path between the two given vertices.
     *
     * @param startIndex start index
     * @param targetIndex stop index
     * @return an Iterator
     */
    private Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyQueueException, EmptyStackException {
        int index = startIndex;
        int[] pathLength = new int[this.numVertices];
        int[] predecessor = new int[this.numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; ++i) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex)) {
            index = (traversalQueue.dequeue());

            /**
             * Update the pathLength for each unvisited vertex adjacent to the
             * vertex at the current index
             */
            for (int i = 0; i < this.numVertices; ++i) {
                if (this.adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        if (index != targetIndex) // Impossible
        {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<>();
        index = targetIndex;
        stack.push(index);

        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    @Override
    public boolean isConnected() throws EmptyQueueException {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> iterator = iteratorBFS(0);
        int count = 0;

        while (iterator.hasNext()) {
            iterator.next();
            ++count;
        }
        return (count == this.numVertices);
    }

    @Override
    public int size() {
        return this.numVertices;
    }

   
}
