/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Marisa Machado
 */
public class Pessoa {

    private String user_nome, user_email, password;

    /*Método construtor*/
    public Pessoa(String user_nome, String user_email, String password) {
        this.user_nome = user_nome;
        this.user_email = user_email;
        this.password = password;
    }

    /*Método construtor vazio*/
    public Pessoa() {
    }

    public String getUser_nome() {
        return user_nome;
    }

    public void setUser_nome(String user_nome) {
        this.user_nome = user_nome;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
