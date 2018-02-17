package GrafoPesado;

import ArrayList.ArrayOrderedList;
import Classes.Edge;
import Classes.Pessoa;
import Database.SqlConnection;
import ArrayList.ArrayUnorderedList;
import Classes.Amizade;
import Exceptions.EmptyCollectionException;
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

    private double[][] adjcMatrix;
    private Edge[][] edgeMatrix;

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link Network}
     */
    public Network() {
        numVertices = 0;
        this.adjcMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.edgeMatrix = new Edge[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        vertices = (T[]) new Object[DEFAULT_CAPACITY];
        addPessoasToVertex();
        addAmizadeToEdge();

    }

    /**
     * Método responsável por imprimir os vértices/pessoas do grafo excepto a
     * pessoa logada
     *
     * @param logada pessoa logada
     */
    public void printVertex(Pessoa logada) {

        for (int i = 0; i < size(); i++) {
            Pessoa pessoa = (Pessoa) vertices[i];
            if (!pessoa.equals(logada)) {
                System.out.println("- " + pessoa.getUser_nome() + " »»»»»» " + pessoa.getUser_email());
            }
        }
    }

    /**
     * Método responsável por imprimir os vértices/pessoas do grafo excepto a
     * pessoa logada
     *
     * @param logada pessoa logada
     */
    public void printUsers(Pessoa logada) {
        Integer counter = 0;

        System.out.println();
        System.out.println("Se pretender sair escreva 0");
        System.out.println("Escolha um utilizador através do seu índice: ");
        for (int i = 0; i < size(); i++) {
            Pessoa pessoa = (Pessoa) vertices[i];

            if (!pessoa.equals(logada)) {
                counter++;
                System.out.println(counter + "- " + pessoa.getUser_email());
            }
        }
        System.out.print("Qual o utilizador que pretende escolher? ");

    }

    /**
     * Método para adicionar pessoas a cada vértice, ou seja, o número de
     * pessoas na base dados vai ser igual ao número de vértices
     */
    private void addPessoasToVertex() {
        SqlConnection con = projeto_ed.Projeto_ed.connection;
        ArrayOrderedList<Pessoa> p = new ArrayOrderedList<>();
        p = con.getAllPessoas("");
        Iterator it = p.iterator();
        while (it.hasNext()) {
            addVertex((T) it.next());
        }
        Integer i = this.numVertices;
    }

    /**
     * Método responsável por carregar as ligações da base de dados, isto é,
     * todas as amizades que existem na base dados ele adiciona como edge
     */
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
     * Insere uma ligação entre dois vértices
     *
     * @param vertex1 - primeiro vértice
     * @param vertex2 - segundo vértice
     * @param weight peso atribuído na ligação
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * Método responsável por verificar se existe amizade entre duas pessoas
     *
     * @param perfil1 - primeira pessoa
     * @param perfil2 - segunda pessoa
     * @return a ligação caso exista
     */
    public Edge testEdge(Pessoa perfil1, Pessoa perfil2) {
        int vertex1 = getIndex((T) perfil1);
        int vertex2 = getIndex((T) perfil2);
        Edge testEdge = this.edgeMatrix[vertex1][vertex2];
        return testEdge;
    }

    /**
     * Método responsável por verificar se uma pessoa existe ou não no grafo
     *
     * @param email pessoa que queremos verificar se existe ou não
     * @return true se existe, false caso contrário
     */
    public Boolean personExists(String email) {

        for (int i = 0; i < size(); i++) {
            Pessoa currentVertice = (Pessoa) vertices[i];

            if (currentVertice.getUser_email().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável por ir buscar os amigos da pessoa logada
     *
     * @param logada - pessoa que se encontra logada
     * @return uma OrderedList com todos os amigos da pessoa logada
     */
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

    /**
     * Método responsável por verificar se existe amizade entre 2 pessoas
     *
     * @param perfil1 - primeira pessoa
     * @param perfil2 - segunda pessoa
     * @return true se existir amizade, false caso contrário
     */
    public Boolean verificaAmizade(Pessoa perfil1, Pessoa perfil2) {
        Integer myIndexPessoa = getIndex((T) perfil1);
        Integer myIndexPessoa2 = getIndex((T) perfil2);
        Edge edge = this.edgeMatrix[myIndexPessoa][myIndexPessoa2];
        /*if (edge != null) {
            return true;
        } else {
            return false;
        }*/
        return edge != null;
    }

    /**
     *
     * Verifica se é possivel uma amizade normal (com um amigo em comum) entre
     * duas pessoas
     *
     * @param perfil1 - primeira pessoa
     * @param perfil2 - segunda pessoa
     * @return true se existe uma amizade normal, retorna false caso não exista
     */
    public Boolean verificarTipoAmizadePossivel(Pessoa perfil1, Pessoa perfil2) {
        Integer myIndexPessoa = getIndex((T) perfil1);
        Boolean existe = false;
        for (int i = 0; i < this.numVertices; i++) {
            if (this.edgeMatrix[myIndexPessoa][i] != null) {
                Edge edge = edgeMatrix[myIndexPessoa][i];
                existe = verificaAmizade(edge.getPessoa1(), perfil2);
                  
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
            this.adjcMatrix[index1][index2] = weight;
            this.adjcMatrix[index2][index1] = weight;
        }
    }

    /**
     * Método que retorna true se o grafo for conexo e falso caso contrário
     *
     * @return true se o grafo for conexo
     * @throws EmptyCollectionException if an empty collection exception occurs
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
     *
     * Retorna o peso do caminho mais curto nesta rede.
     *
     * @param vertex1 - primeiro vértice
     * @param vertex2 - segundo vértice
     * @return O peso do caminho mais curto nesta rede
     * @throws EmptyCollectionException if an empty collection exception occurs
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException {
        double result = 0;

        if (!indexIsValid(getIndex(vertex1)) || !indexIsValid(getIndex(vertex2))) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;

        Iterator<Integer> iterator = iteratorShortestPath(getIndex(vertex1), getIndex(vertex2));

        if (iterator.hasNext()) {
            index1 = iterator.next();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (iterator.hasNext()) {
            index2 = (iterator.next());
            result += this.adjcMatrix[index1][index2];
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
     */
    public Iterator<Integer> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyCollectionException {
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
                pathWeight[i] = pathWeight[startIndex] + adjcMatrix[startIndex][i];
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
                    if ((adjcMatrix[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjcMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjcMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

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
                    if ((adjcMatrix[i][k] < Double.POSITIVE_INFINITY) && visited[k]) {
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
            this.adjcMatrix[numVertices][i] = Integer.MAX_VALUE;
            this.adjcMatrix[i][numVertices] = Integer.MAX_VALUE;
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
     * Remove um vertex
     *
     * @param index índice do vértice a remover
     */
    private void removeVertex(int index) {
        if (indexIsValid(index)) {
            --numVertices;

            for (int i = index; i < numVertices; ++i) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; ++i) {
                for (int k = 0; k < numVertices; ++k) {
                    this.adjcMatrix[i][k] = this.adjcMatrix[i + 1][k];
                    this.edgeMatrix[i][k] = this.edgeMatrix[i + 1][k];
                }
            }

            for (int i = index; i < numVertices; ++i) {
                for (int k = 0; k < numVertices; ++k) {
                    this.adjcMatrix[k][i] = this.adjcMatrix[k][i + 1];
                    this.edgeMatrix[k][i] = this.edgeMatrix[k][i + 1];
                }
            }
        }
    }

    /**
     *
     * Remove uma ligação entre dois vértices deste grafo
     *
     * @param vertex1 - primeiro vértice
     * @param vertex2 - segundo vértice
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        if (indexIsValid(getIndex(vertex1)) && indexIsValid(getIndex(vertex2))) {
            this.adjcMatrix[getIndex(vertex1)][getIndex(vertex2)] = Integer.MAX_VALUE;
            this.adjcMatrix[getIndex(vertex2)][getIndex(vertex1)] = Integer.MAX_VALUE;

        }
    }

    /**
     *
     * Retorna um primeiro iterador de largura começando com o vértice dado
     *
     * @param startVertex - vértice inicial
     * @return um primeiro iterador de largura começando no vértice dado
     * @throws EmptyCollectionException if an empty collection exception occurs
     */
    @Override
    public Iterator iteratorBFS(T startVertex) throws EmptyCollectionException {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Obter um iterador BFS com a pesquisa em largura a partir de um índice
     *
     * @param startIndex índice onde a pesquisa começará
     * @return an iterator.
     * @throws EmptyStackException se a stack estiver vazia
     */
    private Iterator<T> iteratorBFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

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
        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true; // startIndex  - Visited = true;

        // Executar o ciclo equanto o número de elementos na queue for > 0
        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue(); // Obter o valor da Queue
            resultList.addToRear(vertices[x]); //Adicionar a UnorderedList

            //Encontrar todos os vertices adjacentes a x que ainda não foram visitados e adicioná-los à Queue
            for (int i = 0; i < numVertices; ++i) {
                if ((adjcMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     *
     * Retorna um primeiro iterador de profundidade começando com o vértice
     * dado.
     *
     * @param startVertex - vértice inicial
     * @return um primeiro iterador de profundidade começando no vértice dado
     * @throws EmptyCollectionException if an empty collection exception occurs
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
     * @return an iterator.
     * @throws EmptyStackException se a stack estiver vazia
     */
    private Iterator<T> iteratorDFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];
        boolean found;

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; ++i) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < numVertices) && !found; ++i) {
                if ((this.adjcMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalStack.push(i);
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
                if ((adjcMatrix[i][k] == weight) && (visited[i] ^ visited[k])) {
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
                largerAdjMatrix[i][k] = this.adjcMatrix[i][k];
            }
            largerVertices[i] = this.vertices[i];
        }
        this.vertices = largerVertices;
        this.adjcMatrix = largerAdjMatrix;
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
