/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author Marisa Machado
 */
public class Comentario {

    private int id_comentario;
    private String comentario;
    private Date data_comentario;
    private Pessoa email_user;
    private Mensagem id_mensagem;

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Comentario}
     *
     * @param id_comentario id_comentario
     * @param comentario conteudo do comentário
     * @param data_comentario data de quando foi feito o comentário
     * @param email_user email de quem escreveu o comentário
     * @param id_mensagem id da mensagem associada ao comentário
     */
    public Comentario(int id_comentario, String comentario, Date data_comentario, Pessoa email_user, Mensagem id_mensagem) {
        this.id_comentario = id_comentario;
        this.comentario = comentario;
        this.data_comentario = data_comentario;
        this.email_user = email_user;
        this.id_mensagem = id_mensagem;
    }

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Comentario}
     *
     * @param comentario conteudo do comentário
     * @param data_comentario data de quando foi feito o comentário
     * @param email_user email de quem escreveu o comentário
     * @param id_mensagem id da mensagem associada ao comentário
     */
    public Comentario(String comentario, Date data_comentario, Pessoa email_user, Mensagem id_mensagem) {
        this.comentario = comentario;
        this.data_comentario = data_comentario;
        this.email_user = email_user;
        this.id_mensagem = id_mensagem;
    }

    /**
     * Método construtor que permite a criação de uma instância de
     * {@link Comentario} vazia
     */
    public Comentario() {
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData_comentario() {
        return data_comentario;
    }

    public void setData_comentario(Date data_comentario) {
        this.data_comentario = data_comentario;
    }

    public Pessoa getEmail_user() {
        return email_user;
    }

    public void setEmail_user(Pessoa email_user) {
        this.email_user = email_user;
    }

    public Mensagem getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(Mensagem id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

}
