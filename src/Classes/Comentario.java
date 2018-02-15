package Classes;

import java.util.Date;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Comentario implements Comparable<Comentario> {

    private int id_comentario;
    private String comentario;
    private Date data_comentario;
    private Pessoa email_user;
    private Integer id_mensagem;

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
    public Comentario(int id_comentario, String comentario, Date data_comentario, Pessoa email_user, Integer id_mensagem) {
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
    public Comentario(String comentario, Date data_comentario, Pessoa email_user, Integer id_mensagem) {
        this.comentario = comentario;
        this.data_comentario = data_comentario;
        this.email_user = email_user;
        this.id_mensagem = id_mensagem;
    }

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link Comentario}
     */
    public Comentario() {
    }

    /**
     * Método que retorna o comentário
     *
     * @return o comentário
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Método que define o valor da variavel comentario
     *
     * @param comentario valor para o qual a variavel comentario vai ser
     * alterada
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Método que retorna a data do comentário, ou seja, de quando ele foi feito
     *
     * @return a data do comentário
     */
    public Date getData_comentario() {
        return data_comentario;
    }

    /**
     * Método que define o valor da variavel data_comentario
     *
     * @param data_comentario valor para o qual a variavel data_comentario vai
     * ser alterada
     */
    public void setData_comentario(Date data_comentario) {
        this.data_comentario = data_comentario;
    }

    /**
     * Método que retorna o email do utilizador que fez o comentário
     *
     * @return o email do utilizador que fez o comentário
     */
    public Pessoa getEmail_user() {
        return email_user;
    }

    /**
     * Método que define o valor da variavel email_user
     *
     * @param email_user valor para o qual a variavel email_user vai ser
     * alterada
     */
    public void setEmail_user(Pessoa email_user) {
        this.email_user = email_user;
    }

    /**
     * Método que retorna o id da mensagem à qual foi feita um comentário
     *
     * @return o id da mensagem à qual foi feita um comentário
     */
    public Integer getId_mensagem() {
        return id_mensagem;
    }

    /**
     * Método que define o valor da variavel id_mensagem
     *
     * @param id_mensagem valor para o qual a variavel id_mensagem vai ser
     * alterada
     */
    public void setId_mensagem(Integer id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

    /**
     * Método que serve para fazer a comparação entre comentários. O que é usado
     * para comparar os comentários é a sua data
     *
     * @param coment comentario que vai ser comparado
     * @return 0 se forem iguais, 1 se a data que lá está for menor que a data
     * recebida e -1 caso seja o contrário do último caso mencionado
     */
    @Override
    public int compareTo(Comentario coment) {
        long myDateMiliSec = this.data_comentario.getTime();
        long otherDateMiliSec = coment.getData_comentario().getTime();

        if (myDateMiliSec > otherDateMiliSec) {
            return -1;
        } else if (myDateMiliSec < otherDateMiliSec) {
            return 1;

        } else {
            return 0;
        }
    }

}
