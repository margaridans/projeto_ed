/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Enumeracoes;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public enum EstadoPedido {

    PENDENTE, RECUSADO, ACEITE;

    public String getDescricao() {
        switch (this) {
            case PENDENTE:
                return "Pendente";
            case RECUSADO:
                return "Recusado";
            case ACEITE:
                return "Aceite";
            default:
                return "";
        }

    }
}
