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
public class Pedido {

    private Integer id_pedidoAmizade;
    private Pessoa user_origem;
    private Pessoa user_destino;
    private Integer id_estado;

    public Pedido(Pessoa user_origem, Pessoa user_destino, Integer id_estado) {
        this.user_origem = user_origem;
        this.user_destino = user_destino;
        this.id_estado = id_estado;
    }

    public Pedido() {
    }

   

    public Integer getId_pedidoAmizade() {
        return id_pedidoAmizade;
    }

    public void setId_pedidoAmizade(Integer id_pedidoAmizade) {
        this.id_pedidoAmizade = id_pedidoAmizade;
    }

    public Pessoa getUser_origem() {
        return user_origem;
    }

    public void setUser_origem(Pessoa user_origem) {
        this.user_origem = user_origem;
    }

    public Pessoa getUser_destino() {
        return user_destino;
    }

    public void setUser_destino(Pessoa user_destino) {
        this.user_destino = user_destino;
    }

}
