package Classes;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class PedidoAmizade {

    private Integer id_pedidoAmizade;
    private Pessoa user_origem; //pessoa que fez o pedido de amizade
    private Pessoa user_destino; //pessoa que recebeu o pedido de amizade
    private Integer id_estado; //estado do pedido de amizade (1-Pendente/2-Aceite/3-Recusado)

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link PedidoAmizade}
     *
     * @param user_origem utilizador que manda o pedido de amizade
     * @param user_destino utilizador que recebe o pedido de amizade
     * @param id_estado estado do pedido de amizade (PENDENTE/ACEITE/REJEITADO)
     */
    public PedidoAmizade(Pessoa user_origem, Pessoa user_destino, Integer id_estado) {
        this.user_origem = user_origem;
        this.user_destino = user_destino;
        this.id_estado = id_estado;
    }

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link PedidoAmizade}
     *
     */
    public PedidoAmizade() {
    }

    /**
     *
     * Método que define o valor da variavel id_pedidoAmizade
     *
     * @param id_pedidoAmizade valor para o qual a variavel id_pedidoAmizade vai
     * ser alterada
     */
    public void setId_pedidoAmizade(Integer id_pedidoAmizade) {
        this.id_pedidoAmizade = id_pedidoAmizade;
    }

    /**
     * Método que retorna o utilizador origem, isto é, o utilizador que manda o
     * pedido de amizade
     *
     * @return retorna o utilizador origem
     */
    public Pessoa getUser_origem() {
        return user_origem;
    }

    /**
     *
     * Método que define o valor da variavel user_origem
     *
     * @param user_origem valor para o qual a variavel user_origem vai ser
     * alterada
     */
    public void setUser_origem(Pessoa user_origem) {
        this.user_origem = user_origem;
    }

    /**
     * Método que retorna o utilizador destino, isto é, o utilizador que recebe
     * o pedido de amizade
     *
     * @return retorna o utilizador destino
     */
    public Pessoa getUser_destino() {
        return user_destino;
    }

    /**
     *
     * Método que define o valor da variavel user_destino
     *
     * @param user_destino valor para o qual a variavel user_destino vai ser
     * alterada
     */
    public void setUser_destino(Pessoa user_destino) {
        this.user_destino = user_destino;
    }

}
