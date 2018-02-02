/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Enumeracoes.TipoMensagem;
import java.util.Date;

/**
 *
 * @author Marisa Machado
 */
public class Mensagem {
    private int id_mensagem, id_comentario, id_pessoa;
    private String mensagem;
    private Date data_publicacao;
    private TipoMensagem tipoMensagem;

    /*Método construtor*/
    public Mensagem(int id_mensagem, int id_comentario, int id_pessoa, String mensagem, Date data_publicacao, TipoMensagem tipoMensagem) {
        this.id_mensagem = id_mensagem;
        this.id_comentario = id_comentario;
        this.id_pessoa = id_pessoa;
        this.mensagem = mensagem;
        this.data_publicacao = data_publicacao;
        this.tipoMensagem = tipoMensagem;
    }

    /*Método construtor vazio*/
    public Mensagem() {
    }

   
    public int getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(int id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(Date data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public TipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagem tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }
    
    
    
}
