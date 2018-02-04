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

    private int id_mensagem;
    private String conteudo_msg; 
    private Date data_publicacao; 
    private TipoMensagem tipoMensagem;
    private Pessoa email_user;

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Mensagem}
     *
     * @param id_mensagem id_mensagem
     * @param conteudo_msg conteudo da mensagem
     * @param data_publicacao data de quando a mensagem foi publicada
     * @param tipoMensagem tipo de mensagem (publica/privada)
     * @param email_user email da pessoa que escreveu a mensagem
     */
    public Mensagem(int id_mensagem, String conteudo_msg, Date data_publicacao, TipoMensagem tipoMensagem, Pessoa email_user) {
        this.id_mensagem = id_mensagem;
        this.conteudo_msg = conteudo_msg;
        this.data_publicacao = data_publicacao;
        this.tipoMensagem = tipoMensagem;
        this.email_user = email_user;
    }

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Mensagem}
     *
     * @param conteudo_msg conteudo da mensagem
     * @param data_publicacao data de quando a mensagem foi publicada
     * @param tipoMensagem tipo de mensagem (publica/privada)
     * @param email_user email da pessoa que escreveu a mensagem
     */
    public Mensagem(String conteudo_msg, Date data_publicacao, TipoMensagem tipoMensagem, Pessoa email_user) {
        this.conteudo_msg = conteudo_msg;
        this.data_publicacao = data_publicacao;
        this.tipoMensagem = tipoMensagem;
        this.email_user = email_user;
    }

    /**
     * Método construtor que permite a criação de uma instância de
     * {@link Mensagem} vazia
     */
    public Mensagem() {
    }

    public int getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(int id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

    public String getConteudo_msg() {
        return conteudo_msg;
    }

    public void setConteudo_msg(String conteudo_msg) {
        this.conteudo_msg = conteudo_msg;
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

    public Pessoa getEmail_user() {
        return email_user;
    }

    public void setEmail_user(Pessoa email_user) {
        this.email_user = email_user;
    }

}
