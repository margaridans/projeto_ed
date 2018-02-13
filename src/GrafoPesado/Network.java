package GrafoPesado;

import ArrayList.ArrayOrderedList;
import Classes.Edge;
import Classes.Pessoa;
import Database.SqlConnection;
import ArrayList.ArrayUnorderedList;
import Classes.Amizade;
import Exceptions.EmptyCollectionException;
import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import interfaces.NetworkADT;
import java.util.Iterator;
import LinkedHeap.LinkedHeap;
import LinkedQueue.LinkedQueue;
import LinkedStack.LinkedStack;

/**
 * @param <T> T Element
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    private double[][] adjMatrix;
    private Edge[][] edgeMatrix;
    //private final double COST = 1.5;

    /**
     * Creates an empty Network
     */
    public Network() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.edgeMatrix = new Edge[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        vertices = (T[]) new Object[DEFAULT_CAPACITY];
        addPessoasToVertex();
        addAmizadeToEdge();

    }

    /**
     * Método para adicionar pessoas a cada vértice, ou seja, o número de
     * pessoas na base dados vai ser igual ao número de vértices
     */
    private void addPessoasToVertex() {
        SqlConnection con = projeto_ed.Projeto_ed.connection;
        ArrayUnorderedList<Pessoa> p = new ArrayUnorderedList<>();
        p = con.getAllPessoas("");
        Iterator it = p.iterator();
        while (it.hasNext()) {
            addVertex((T) it.next());
        }
        Integer i = this.numVertices;
    }

    private void addAmizadeToEdge() {
        SqlConnection con = projeto_ed.Projeto_ed.connection;
        ArrayUnorderedList<Amizade> a = new ArrayUnorderedList<>();
        a = con.getAllAmizades();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            Amizade tmpAm = (Amizade) it.next();
            Edge tmpEdge = new Edge(tmpAm.getUser1(), tmpAm.getUser2());
            addEdge((T) tmpAm.getUser1(), (T) tmpAm.getUser2(), tmpEdge);
        }

    }

    /**
     * Inserir uma ligação entre dois vertice
     *
     * @param vertex1 primeiro vertex
     * @param vertex2 segundo vertex
     * @param weight peso atribuído na ligação
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    public Edge testEdge(Pessoa logada, Pessoa perfil) {
        int vertex1 = getIndex((T) logada);
        int vertex2 = getIndex((T) perfil);
        Edge testEdge = this.edgeMatrix[vertex1][vertex2];

        return testEdge;
    }

    public ArrayOrderedList<Pessoa> getAmigos(Pessoa logada) {
        ArrayOrderedList<Pessoa> listamigos = new ArrayOrderedList<>();
        for (int i = 0; i < this.numVertices; i++) {
            if (this.edgeMatrix[getIndex((T) logada)][i] != null) {
                Pessoa p = this.edgeMatrix[getIndex((T) logada)][i].getPessoa2();
                if (p.equals(logada)) {
                    listamigos.add(this.edgeMatrix[getIndex((T) logada)][i].getPessoa1());
                } else {
                    listamigos.add(p);
                }

            }
        }

        return listamigos;
    }

    public ArrayOrderedList<Pessoa> getNaoAmigos(Pessoa logada) {
        ArrayOrderedList<Pessoa> listNamigos = new ArrayOrderedList<>();
        for (int i = 0; i < this.numVertices; i++) {
            if (this.edgeMatrix[getIndex((T) logada)][i] == null) {
                Pessoa p = this.edgeMatrix[getIndex((T) logada)][i].getPessoa2();
                if (p.equals(logada)) {
                    listNamigos.add(this.edgeMatrix[getIndex((T) logada)][i].getPessoa1());
                } else {
                    listNamigos.add(p);
                }

            }
        }

        return listNamigos;
    }

    public boolean ifAmigos(Pessoa logada) {

        ArrayOrderedList<Pessoa> listamigos = new ArrayOrderedList<>();
        for (int i = 0; i < this.numVertices; i++) {
            if (this.edgeMatrix[getIndex((T) logada)][i] != null) {
                Pessoa p = this.edgeMatrix[getIndex((T) logada)][i].getPessoa2();
                return p.equals(logada);

            }
        }

        return true;
    }

    public Boolean verificarAmigoDeAmigo(Pessoa perfil1, Pessoa perfil2) {
        Integer myIndexPessoa = getIndex((T) perfil1);
        Integer myIndexPessoa2 = getIndex((T) perfil2);
        Edge edge = this.edgeMatrix[myIndexPessoa][myIndexPessoa2];
        if (edge != null) {
            return true;
        } else {
            return false;
        }
        //return edge != null;
    }

    public Boolean verificarTipoAmizadePossivel(Pessoa logada, Pessoa perfil) {
        Integer myIndexPessoa = getIndex((T) logada);
        Boolean existe = false;
        for (int i = 0; i < this.numVertices; i++) {
            if (this.edgeMatrix[myIndexPessoa][i] != null) {
                Edge edge = edgeMatrix[myIndexPessoa][i];
                existe = verificarAmigoDeAmigo(edge.getPessoa2(), perfil);
                if (existe) {
                    break;
                }
            }
        }
        return existe;
    }

    /**
     * Adicionar um Edge à matriz de Edges O Edge contém a informação necessária
     * ao cálculo do caminho mímino entre 2 pontos: 1. Tempo. 2. Distância.
     * 3.Custo.
     *
     * @param index1 índice do vertice de origem
     * @param index2 índice do vertice de destino
     * @param edge ligação entre vertex1 e vertex2
     */
    private void addEdge(int index1, int index2, Edge edge) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.edgeMatrix[index1][index2] = edge;
            this.edgeMatrix[index2][index1] = edge;
        }
    }

    /**
     * Adicionar um Edge à matriz de Edges O Edge contém a informação necessária
     * ao cálculo do caminho mímino entre 2 pontos: 1. Tempo. 2. Distância.
     * 3.Custo.
     *
     * @param vertex1 vertex do ponto de origem
     * @param vertex2 vertex do ponto de destino
     * @param edge ligação entre vertex1 e vertex2
     */
    public void addEdge(T vertex1, T vertex2, Edge edge) {
        addEdge(getIndex(vertex1), getIndex(vertex2), edge);
        addEdge(vertex1, vertex2, edge.getPeso());
    }

    /**
     * Inserir um Edge entre dois pontos da network
     *
     * @param index1 index do primeiro vertice
     * @param index2 index do segundo vertice
     * @param weight valor imposto à ligação
     */
    private void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = weight;
            this.adjMatrix[index2][index1] = weight;
        }
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
    public double shortestPathWeight(T vertex1, T vertex2) throws EmptyQueueException, EmptyStackException, EmptyCollectionException {
        double result = 0;

        if (!indexIsValid(getIndex(vertex1)) || !indexIsValid(getIndex(vertex2))) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;

        Iterator<Integer> iterator = iteratorShortestPath(getIndex(vertex1), getIndex(vertex2));

        if (iterator.hasNext()) {
            index1 = iterator.next().intValue();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (iterator.hasNext()) {
            index2 = (iterator.next()).intValue();
            result += this.adjMatrix[index1][index2];
            index1 = index2;
        }
        return result;
    }

    /**
     * Método que permite definir numa lista todos os vertices visitados a FALSE
     *
     * @return lista de boolean com todos os vertices a false
     */
    private boolean[] setVerticesFalse() {
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        return visited;
    }

    /**
     * Método que permite definir numa lista todos os vertices a infinito
     *
     * @return lista de double com valor POSITIVE_INFINITY
     */
    private double[] setVerticesInfinty() {
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }
        return pathWeight;
    }

    /**
     * Obter um iterador com a lista de Vertex que definem o caminho mínimo
     * entre dois pontos
     *
     * @param startIndex ponto de origem
     * @param targetIndex ponto de destino
     * @return Iterador que permite identificar o perurso entre dois locais
     * @throws EmptyCollectionException exceção lançada no caso da coleção estar
     * vazia
     * @throws EmptyStackException exceção lançada no caso da Queue estar vazia
     */
    public Iterator<Integer> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyCollectionException, EmptyStackException {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();
        double[] pathWeight = setVerticesInfinty();
        boolean visited[] = setVerticesFalse();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;

        /**
         * Atualizar o peso da ligação para cada vertice, excepto o vertice
         * inicial. Todos os vertices não adjacentes terão um peso =
         * Double.POSITIVE_INFINITY pelo menos para já
         */
        for (int i = 0; i < numVertices; ++i) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            weight = traversalMinHeap.removeMin();
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // Caminho impossível
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertex(visited, pathWeight, weight);
                visited[index] = true;
            }
            /**
             * Atualizar o peso para cada vertice que não tenha sido visitado e
             * é adjacente ao último vertice que foi visitado. Adicionar cada
             * vertice à heap
             */
            for (int i = 0; i < numVertices; ++i) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(new Double(pathWeight[i]));
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(new Integer(index));
        do {
            index = predecessor[index];
            stack.push(new Integer(index));
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * Obter o indice dos elementos visitados para obter o valor da ligação
     *
     * @param visited lista valores boleanos que representa a se o vertice foi
     * visitado ou não
     * @param pathWeight lista de valores que representa o peso da ligação
     * @param weight peso da ligação
     * @return o índice
     */
    protected int getIndexOfAdjVertex(boolean[] visited, double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int k = 0; k < numVertices; k++) {
                    if ((adjMatrix[i][k] < Double.POSITIVE_INFINITY) && visited[k]) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Adicionar um vertex e expandir a capacidade se necessário
     *
     * @param vertex Vertex a adicionar à network
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;

        for (int i = 0; i < numVertices; ++i) {
            this.adjMatrix[numVertices][i] = Integer.MAX_VALUE;
            this.adjMatrix[i][numVertices] = Integer.MAX_VALUE;
        }
        ++numVertices;
    }

    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; ++i) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                break;
            }
        }
    }

    /**
     * Remover um vertex
     *
     * @param index índice a remover
     */
    private void removeVertex(int index) {
        if (indexIsValid(index)) {
            --numVertices;

            for (int i = index; i < numVertices; ++i) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; ++i) {
                for (int k = 0; k < numVertices; ++k) {
                    this.adjMatrix[i][k] = this.adjMatrix[i + 1][k];
                    this.edgeMatrix[i][k] = this.edgeMatrix[i + 1][k];
                }
            }

            for (int i = index; i < numVertices; ++i) {
                for (int k = 0; k < numVertices; ++k) {
                    this.adjMatrix[k][i] = this.adjMatrix[k][i + 1];
                    this.edgeMatrix[k][i] = this.edgeMatrix[k][i + 1];
                }
            }
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        if (indexIsValid(getIndex(vertex1)) && indexIsValid(getIndex(vertex2))) {
            this.adjMatrix[getIndex(vertex1)][getIndex(vertex2)] = Integer.MAX_VALUE;
            this.adjMatrix[getIndex(vertex2)][getIndex(vertex1)] = Integer.MAX_VALUE;

        }
    }

    @Override
    public Iterator iteratorBFS(T startVertex) throws EmptyQueueException {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Obter um iterador BFS com a pesquisa em largura a partir de um índice
     *
     * @param startIndex índice onde a pesquisa começará
     * @return an iterator.
     * @throws EmptyStackException se a stack estiver vazia
     */
    private Iterator<T> iteratorBFS(int startIndex) throws EmptyQueueException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

        // Verificar se o startIndex é válido
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        // Criar lista de boleanos, para marcar todos os vertices a false;
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; ++i) {
            visited[i] = false;
        }

        //atribuir à queue o valor do startIndex - Ponto de partida do iterador.
        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true; // startIndex  - Visited = true;

        // Executar o ciclo equanto o número de elementos na queue for > 0
        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue(); // Obter o valor da Queue
            resultList.addToRear(vertices[x.intValue()]); //Adicionar a UnorderedList

            //Encontrar todos os vertices adjacentes a x que ainda não foram visitados e adicioná-los à Queue
            for (int i = 0; i < numVertices; ++i) {
                if ((adjMatrix[x.intValue()][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
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
     * Obter um iterador DFS com a pesquisa em profundidade a partir de um
     * índice
     *
     * @param startIndex índice onde a pesquisa começará
     * @return an iterator.
     * @throws EmptyStackException se a stack estiver vazia
     */
    private Iterator<T> iteratorDFS(int startIndex) throws EmptyStackException {
        Integer x;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        boolean found;

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; ++i) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startIndex));
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < numVertices) && !found; ++i) {
                if ((this.adjMatrix[x.intValue()][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
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
     * Obter a minimum spanning tree
     *
     * @return uma network
     * @throws EmptyQueueException se a Queue estiver vazia
     * @throws EmptyCollectionException se não existir elementos na coleção
     */
    /* public Network mstNetwork() throws EmptyQueueException, EmptyCollectionException {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        LinkedHeap<Double> minHeap = new LinkedHeap();
        Network<T> resultGraph = new Network();

        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }

        resultGraph.adjMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        resultGraph.vertices = (T[]) (new Object[numVertices]);

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;

        /**
         * Adicionar todas as arestas, que são adjacentes ao vertice inicial à
         * heap
     */
 /*
        for (int i = 0; i < numVertices; i++) {
            minHeap.addElement(new Double(adjMatrix[0][i]));
        }

        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            //Obter o edge com o min weight
            do {
                weight = (minHeap.removeMin()).doubleValue();
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }
            //Adicionar uma nova ligação a resultGraph
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;

            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];

            /**
             * Adicionar todas as ligações, que são adjacentes ao vertice à heap
     */
 /*
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[i][index]
                        < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(new Double(adjMatrix[index][i]));
                }
            }
        }
        return resultGraph;
    }
     */
    /**
     * Obter o índice do Edge que tem associado o peso na ligação especificada
     *
     * @param weight peso aplicado na ligação
     * @param visited lista de nós visitados
     * @return o indíce do elemento na matriz, -1 se não encontrar
     */
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];

        for (int i = 0; i < numVertices; ++i) {
            for (int k = 0; k < numVertices; ++k) /**
             * O símbolo ^ significa Bit-aBit XOU OR
             */
            {
                if ((adjMatrix[i][k] == weight) && (visited[i] ^ visited[k])) {
                    edge[0] = i;
                    edge[1] = k;
                    return edge;
                }
            }
        }
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    /**
     * Expandir a capcidade da matriz que armazena os valores da ligação
     */
    @Override
    protected void expandCapacity() {
        T[] largerVertices = (T[]) new Object[vertices.length * 2];
        double[][] largerAdjMatrix = new double[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < this.numVertices; ++i) {
            for (int k = 0; k < this.numVertices; ++k) {
                largerAdjMatrix[i][k] = this.adjMatrix[i][k];
            }
            largerVertices[i] = this.vertices[i];
        }
        this.vertices = largerVertices;
        this.adjMatrix = largerAdjMatrix;
        expandEdgeCapacity();
    }

    /**
     * Expandir a capacidade da matriz que armazena os Edges (Ligações entre
     * vertices)
     */
    protected void expandEdgeCapacity() {
        Edge[][] largerAdjMatrix = new Edge[vertices.length][vertices.length];

        for (int i = 0; i < this.numVertices; ++i) {
            for (int k = 0; k < this.numVertices; ++k) {
                largerAdjMatrix[i][k] = this.edgeMatrix[i][k];
            }
        }
        this.edgeMatrix = largerAdjMatrix;
    }
}
