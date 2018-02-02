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

    private int id_pessoa;
    private String nome, email;

    /*Método construtor*/
    public Pessoa(int id_pessoa, String nome, String email) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.email = email;
    }

    /*Método construtor vazio*/
    public Pessoa() {

    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

}
