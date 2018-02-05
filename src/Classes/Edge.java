/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Edge {

    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private final int peso = 1;

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
     * Método construtor que permite a criação de uma instância de {@link Edge}
     * vazia
     */
    public Edge() {

    }

    public int getPeso() {
        return this.peso;
    }

    public Pessoa getPessoa1() {
        return pessoa1;
    }

    public void setPessoa1(Pessoa pessoa1) {
        this.pessoa1 = pessoa1;
    }

    public Pessoa getPessoa2() {
        return pessoa2;
    }

    public void setPessoa2(Pessoa pessoa2) {
        this.pessoa2 = pessoa2;
    }
}
