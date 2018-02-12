/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Objects;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Pessoa implements Comparable<Pessoa>{

    private String user_nome, user_email, password;
    private Integer nr_creditos;

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Pessoa}
     *
     * @param user_nome nome do utilizador
     * @param user_email email do utlizador
     * @param password password do utilizador
     * @param nr_creditos
     */
    public Pessoa(String user_email, String user_nome, String password, Integer nr_creditos) {
        this.user_nome = user_nome;
        this.user_email = user_email;
        this.password = password;
        this.nr_creditos= nr_creditos;
    }
    
    /**
     * Método construtor que permite a criação de uma instância de
     * {@link Pessoa} vazia
     */
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

    public Integer getNr_creditos() {
        return nr_creditos;
    }

    public void setNr_creditos(Integer nr_creditos) {
        this.nr_creditos = nr_creditos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.user_nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.user_nome, other.user_nome)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Pessoa o) {
        if(this.user_nome.equals(o.getUser_nome())){
            return 0;
        }else if (this.user_nome.compareTo(o.getUser_nome()) == 1){
            return 1;
        }else{
            return -1;
        }
    }

   

}
