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

    /*Método construtor*/
    public Comentario(int id_comentario, String comentario, Date data_comentario) {
        this.id_comentario = id_comentario;
        this.comentario = comentario;
        this.data_comentario = data_comentario;
    }

    /*Método construtor vazio*/
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

}
