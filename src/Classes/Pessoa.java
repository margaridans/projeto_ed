package Classes;

import java.util.Objects;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Pessoa implements Comparable<Pessoa> {

    private String user_nome, user_email, password;
    private Integer nr_creditos;

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Pessoa}
     *
     * @param user_nome nome do utilizador
     * @param user_email email do utlizador
     * @param password password do utilizador
     * @param nr_creditos número de créditos que o utilizador tem
     */
    public Pessoa(String user_email, String user_nome, String password, Integer nr_creditos) {
        this.user_nome = user_nome;
        this.user_email = user_email;
        this.password = password;
        this.nr_creditos = nr_creditos;
    }

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link Pessoa}
     */
    public Pessoa() {
    }

    /**
     * Método que retorna o nome do utilizador
     *
     * @return o nome do utilizador
     */
    public String getUser_nome() {
        return user_nome;
    }

    /**
     *
     * Método que define o valor da variavel user_nome
     *
     * @param user_nome valor para o qual a variavel user_nome vai ser alterada
     */
    public void setUser_nome(String user_nome) {
        this.user_nome = user_nome;
    }

    /**
     * Método que retorna o email do utilizador
     *
     * @return o email do utilizador
     */
    public String getUser_email() {
        return user_email;
    }

    /**
     *
     * Método que define o valor da variavel user_email
     *
     * @param user_email valor para o qual a variavel user_email vai ser
     * alterada
     */
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    /**
     * Método que retorna a password do utilizador
     *
     * @return a password do utilizador
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * Método que define o valor da variavel password
     *
     * @param password valor para o qual a variavel password vai ser alterada
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método que retorna o número de créditos do utilizador
     *
     * @return o número de créditos do utilizador
     */
    public Integer getNr_creditos() {
        return nr_creditos;
    }

    /**
     *
     * Método que define o valor da variavel nr_creditos
     *
     * @param nr_creditos valor para o qual a variavel nr_creditos vai ser
     * alterada
     */
    public void setNr_creditos(Integer nr_creditos) {
        this.nr_creditos = nr_creditos;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.user_nome);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
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
        return Objects.equals(this.user_nome, other.user_nome);
        /* if (!Objects.equals(this.user_nome, other.user_nome)) {
            return false;
        }
        return true;*/
    }

    /**
     * Método que serve para fazer a comparação entre pessoas (utilizadores). O
     * que é usado para comparar estes utilizadores é o seu nome
     *
     * @param pessoa pessoa que vai ser comparada
     * @return 0 se os nomes forem iguais, 1 se o nome que lá está for maior
     * (alfabeticamente) que o nome recebido e -1 caso seja o contrário do
     * último caso mencionado
     */
    @Override
    public int compareTo(Pessoa pessoa) {
        if (this.user_nome.equals(pessoa.getUser_nome())) {
            return 0;
        } else if (this.user_nome.compareTo(pessoa.getUser_nome()) == 1) {
            return 1;
        } else {
            return -1;
        }
    }

}
