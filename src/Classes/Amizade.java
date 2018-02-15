package Classes;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Amizade {

    private Integer id_amizade;
    private Pessoa user1; //utilizador 1 da amizade
    private Pessoa user2; //utilizador 2 da amizade

    /**
     * Método construtor que permite a criação de uma nova instância de
     * {@link Amizade}
     *
     * @param user1 utilizador 1 que vai fazer parte da amizade
     * @param user2 utilizador 2 que vai fazer parte da amizade
     */
    public Amizade(Pessoa user1, Pessoa user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link Amizade}
     */
    public Amizade() {

    }

    /**
     * Método que retorna o utilizador 1
     *
     * @return o utilizador 1
     */
    public Pessoa getUser1() {
        return user1;
    }

    /**
     * Método que define o valor da variavel user1
     *
     * @param user1 valor para o qual a variavel user1 vai ser
     * alterada
     */
    public void setUser1(Pessoa user1) {
        this.user1 = user1;
    }

    /**
     * Método que retorna o utilizador 2
     *
     * @return o utilizador 2
     */
    public Pessoa getUser2() {
        return user2;
    }

       /**
     * Método que define o valor da variavel user2
     *
     * @param user2 valor para o qual a variavel user1 vai ser
     * alterada
     */
    public void setUser2(Pessoa user2) {
        this.user2 = user2;
    }

}
