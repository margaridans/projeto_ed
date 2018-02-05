/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Enumeracoes;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140092
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
