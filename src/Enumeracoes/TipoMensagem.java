
package Enumeracoes;

/**
 * Esta enumeração enumera os tipos de mensagens, este tipo pode ser: Pública
 * (onde as mensagens ficam visiveis para todos os utilizadores) e Privada (onde
 * as mensagens ficam apenas visiveis para os amigos do utilizador que a
 * publica)
 *
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public enum TipoMensagem {

    PUBLICA, PRIVADA;

    public String getDescricao() {
        switch (this) {
            case PUBLICA:
                return "Pública";
            case PRIVADA:
                return "Privada";
            default:
                return "";
        }

    }
}
