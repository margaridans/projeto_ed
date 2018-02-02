/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Enumeracoes;

/**
 *
 * @author Marisa Machado
 */
public class TipoMensagem {

    enum tipoMens {
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
}
