package GrafoPesado;

import ArrayList.ArrayUnorderedList;
import Exceptions.EmptyCollectionException;
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
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 20;
    protected int numVertices; // Número de vértices num grafo 
    protected boolean[][] adjMatrix; // matriz de adjacências
    protected T[] vertices; // valores dos vértices

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link Graph}
     */
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     *
     * Adiciona um vértice a este grafo, associando o objeto ao vértice.
     *
     * @param vertex1 - primeiro vértice a ser adicionado
     * @param vertex2 - segundo vértice a ser adicionado
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Insere uma ligação entre dois vértices do grafo.
     *
     * @param index1 primeiro indice
     * @param index2 segundo indice
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Retorna verdadeiro se o índice recebido for válido
     *
     * @param index índice específico
     * @return true se for verdadeiro, false não for verdadeiro
     */
    protected boolean indexIsValid(int index) {
        return ((index < this.numVertices) && (index >= 0));
    }

    /**
     * Retorna o valor do índice da primeira ocorrência do vértice.
     *
     * @param vertex - vértice do grafo
     * @return -1 se a chave não for encontrada
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
     *
     * Adiciona um vértice ao grafo, expandindo a capacidade do mesmo, se
     * necessário. Também associa um objeto ao vértice.
     *
     * @param vertex vértice a ser adicionado ao grafo
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
        numVertices++;
    }

    /**
     * Cria novos arrays para armazenar o conteúdo do grafo com duas vezes mais
     * a capacidade do anterior.
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

    /**
     *
     * Remove um único vértice com o valor fornecido a partir deste grafo
     *
     * @param vertex - vértice a ser removido do grafo
     */
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
     *
     * Remove um vértice no índice dado do grafo.
     *
     * @param index - index do vértice a ser removido
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

    /**
     * Remove uma ligação entre dois vértices do grafo
     *
     * @param vertex1 - primeiro vértice
     * @param vertex2 - segundo vértice
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Remove uma ligação entre dois vértices do grafo
     *
     * @param index1 - primeiro indice
     * @param index2 - segundo indice
     */
    private void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = false;
            this.adjMatrix[index2][index1] = false;
        }
    }

    /**
     * Retorna um primeiro iterador de largura começando com o vértice dado.
     *
     * @param startVertex - vértice inicial
     * @return um primeiro iterador de profundidade começando no vértice dado
     * @throws EmptyCollectionException if an empty collection
     * exception occurs
     */
    @Override
    public Iterator iteratorBFS(T startVertex) throws EmptyCollectionException {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * BFS Iterator
     *
     * @param startIndex - índice de inicio
     * @return iterador com lista de itens visitados
     * @throws EmptyQueueException é lançada quando a queue está vazia
     */
    private Iterator<T> iteratorBFS(int startIndex) throws EmptyCollectionException {
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
            /* Encontra todos os vértices adjacentes a x que não tenham sido 
            visitados*/
            for (int i = 0; i < this.numVertices; ++i) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Retorna um primeiro iterador de profundidade começando com o vértice
     * dado.
     *
     * @param startVertex - vértice inicial
     * @return um primeiro iterador de profundidade começando no vértice dado
     * @throws EmptyCollectionException if an empty collection
     * exception occurs
     */
    @Override
    public Iterator iteratorDFS(T startVertex) throws EmptyCollectionException {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Obter um iterador DFS com a pesquisa em profundidade a partir de um
     * índice
     *
     * @param startIndex índice onde a pesquisa começará
     * @return um iterador.
     * @throws EmptyStackException é lançada se a stack estiver vazia
     */
    private Iterator<T> iteratorDFS(int startIndex) throws EmptyCollectionException {
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
            /* Encontre um vértice adjacente a x que não tenha sido visitado e 
            pressione-o na stack*/
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

    /**
     *
     * Retorna um iterador que contém o caminho mais curto entre os dois
     * vértices.
     *
     * @param startVertex - vértice inicial
     * @param targetVertex - vértice final
     * @return um iterador que contém o caminho mais curto entre os dois
     * vértices
     * @throws EmptyCollectionException if an empty collection
     * exception occurs
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) throws EmptyCollectionException {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Obter um iterador com a lista de locais entre dois vertices
     *
     * @param startIndex - índice de início
     * @param targetIndex - índice de paragem
     * @return um iterador
     */
    private Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyCollectionException {
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
     *
     * Retorna um iterador que contém os índices dos vértices que estão no
     * caminho mais curto entre os dois vértices dados.
     *
     * @param startIndex - índice de ínicio
     * @param targetIndex - índice de paragem
     * @return um iterador
     */
    private Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyCollectionException {
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

            /* Atualize o comprimento do caminho para cada vértice não visitado 
            adjacente ao vértice no índice atual */
            for (int i = 0; i < this.numVertices; ++i) {
                if (this.adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        if (index != targetIndex) //Impossível
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

    /**
     * Método que retorna true se o grafo estiver vazio e falso caso contrário
     *
     * @return true se o grafo estiver vazio
     */
    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    /**
     * Método que retorna true se o grafo for conexo e falso caso contrário
     *
     * @return true se o grafo for conexo
     * @throws EmptyCollectionException if an empty collection
     * exception occurs
     */
    @Override
    public boolean isConnected() throws EmptyCollectionException {
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

    /**
     * Retorna o número de vértices de um grafo
     *
     * @return um número inteiro de vértices do grafo
     */
    @Override
    public int size() {
        return this.numVertices;
    }

}
