/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Enumeracoes.TipoMensagem;
import java.util.Date;

/**
 *
 * @author Marisa Machado
 */
public class Mensagem {
    private int id_mensagem; 
    private String mensagem; // Descrição da mensagem
    private Date data_publicacao; // Data de quando a mensagem foi publicada
    private TipoMensagem tipoMensagem; // Ou será pública ou privada
    private Pessoa id_pessoa; // Pessoa que escreveu a mensagem
    private Comentario id_comentario; // Comentário associado à mensagem

   
}
