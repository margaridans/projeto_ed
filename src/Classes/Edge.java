package Classes;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Edge {

    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private final double peso = 1;

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Edge}
     *
     * @param pessoa1 pessoa 1 que vai fazer parte da ligação (aresta/edge)
     * @param pessoa2 pessoa 1 que vai fazer parte da ligação (aresta/edge)
     */
    public Edge(Pessoa pessoa1, Pessoa pessoa2) {
        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
    }

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link Edge}
     *
     */
    public Edge() {

    }

    /**
     * Método que retorna o peso do edge
     *
     * @return peso do edge
     */
    public double getPeso() {
        return this.peso;
    }

    /**
     * Método que retorna a pessoa 1
     *
     * @return a pessoa 1
     */
    public Pessoa getPessoa1() {
        return pessoa1;
    }

    /**
     *
     * Método que define o valor da variavel pessoa1
     *
     * @param pessoa1 valor para o qual a variavel pessoa1 vai ser alterada
     */
    public void setPessoa1(Pessoa pessoa1) {
        this.pessoa1 = pessoa1;
    }

    /**
     * Método que retorna a pessoa 2
     *
     * @return a pessoa 2
     */
    public Pessoa getPessoa2() {
        return pessoa2;
    }

    /**
     *
     * Método que define o valor da variavel pessoa2
     *
     * @param pessoa2 valor para o qual a variavel pessoa2 vai ser alterada
     */
    public void setPessoa2(Pessoa pessoa2) {
        this.pessoa2 = pessoa2;
    }
}
