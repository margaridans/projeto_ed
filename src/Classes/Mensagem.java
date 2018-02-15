package Classes;

import ArrayList.ArrayOrderedList;
import java.util.Date;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Mensagem implements Comparable<Mensagem> {

    private int id_mensagem;
    private String conteudo_msg;
    private Date data_publicacao;
    private Integer IdtipoMensagem;
    private Pessoa email_user;
    private ArrayOrderedList<Comentario> comentarios;

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
    public Mensagem(int id_mensagem, String conteudo_msg, Date data_publicacao, Integer tipoMensagem, Pessoa email_user) {
        this.id_mensagem = id_mensagem;
        this.conteudo_msg = conteudo_msg;
        this.data_publicacao = data_publicacao;
        this.IdtipoMensagem = tipoMensagem;
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
    public Mensagem(String conteudo_msg, Date data_publicacao, Integer tipoMensagem, Pessoa email_user) {
        this.conteudo_msg = conteudo_msg;
        this.data_publicacao = data_publicacao;
        this.IdtipoMensagem = tipoMensagem;
        this.email_user = email_user;
    }

    /**
     * Método construtor vazio que permite a criação de uma instância de
     * {@link Mensagem}
     */
    public Mensagem() {
    }

    /**
     * Método que retorna o conteúdo da mensagem
     *
     * @return o conteúdo da mensagem
     */
    public String getConteudo_msg() {
        return conteudo_msg;
    }

    /**
     *
     * Método que define o valor da variavel conteudo_msg
     *
     * @param conteudo_msg valor para o qual a variavel conteudo_msg vai ser
     * alterada
     */
    public void setConteudo_msg(String conteudo_msg) {
        this.conteudo_msg = conteudo_msg;
    }

    /**
     * Método que retorna a data da publicação da mensagem
     *
     * @return a data da publicação da mensagem
     */
    public Date getData_publicacao() {
        return data_publicacao;
    }

    /**
     *
     * Método que define o valor da variavel data_publicacao
     *
     * @param data_publicacao valor para o qual a variavel data_publicacao vai
     * ser alterada
     */
    public void setData_publicacao(Date data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    /**
     * Método que retorna o tipo da mensagem (Privada/Pública)
     *
     * @return o tipo da mensagem
     */
    public Integer getTipoMensagem() {
        return IdtipoMensagem;
    }

    /**
     *
     * Método que define o valor da variavel tipoMensagem
     *
     * @param tipoMensagem valor para o qual a variavel tipoMensagem vai ser
     * alterada
     */
    public void setTipoMensagem(Integer tipoMensagem) {
        this.IdtipoMensagem = tipoMensagem;
    }

    /**
     * Método que retorna o email do utilizador que escreveu a mensagem
     *
     * @return o email do utilizador
     */
    public Pessoa getEmail_user() {
        return email_user;
    }

    /**
     *
     * Método que define o valor da variavel email_user
     *
     * @param email_user valor para o qual a variavel email_user vai ser
     * alterada
     */
    public void setEmail_user(Pessoa email_user) {
        this.email_user = email_user;
    }

    /**
     * Método que serve para fazer a comparação entre mensagens. O que é usado
     * para comparar as mensagens é a sua data
     *
     * @param msg mensagem que vai ser comparada
     * @return 0 se forem iguais, 1 se a data que lá está for menor que a data
     * recebida e -1 caso seja o contrário do último caso mencionado
     */
    @Override
    public int compareTo(Mensagem msg) {
        long myDateMiliSec = this.data_publicacao.getTime();
        long otherDateMiliSec = msg.getData_publicacao().getTime();

        if (myDateMiliSec > otherDateMiliSec) {
            return -1;
        } else if (myDateMiliSec < otherDateMiliSec) {
            return 1;

        } else {
            return 0;
        }
    }
}
