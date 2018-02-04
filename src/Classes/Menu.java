/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Enumeracoes.TipoMensagem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import projeto_ed.Projeto_ed;

/**
 *
 * @author Bernardino
 */
public class Menu {

    public void Menu(String user_logado) throws IOException {
        System.out.println("Bem vindo " + user_logado);

        System.out.println("\n \n");
        System.out.println("* * * * * * * * * * Menu * * * * * * * * * * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("*             1- Escrever mensagem         * ");
        System.out.println("*                                          * ");
        System.out.println("*             3- Terminar Sessão           * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *  * ");
        System.out.println("O que pretende fazer? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();

        if ("1".equals(escolha)) {
            System.out.println("\n \n");
            System.out.println("* * * * * * * * * * Menu Mensagem * * * * * * * * * * ");
            System.out.println("\n");
            System.out.println("Escreva aqui a sua mensagem: ");
            System.out.println(" /!\\ Esta sua mensagem irá aparecer na sua página inicial");

            String lerMensagem = in.readLine();
            String mensagem_conteudo = lerMensagem;

            System.out.println("\n \n");
            System.out.println("A sua mensagem vai ser de que tipo: (1 - Privada/ 2 - Pública) ");
           
            String lerTipoMensagem = in.readLine();
            if ("1".equals(lerTipoMensagem)) {

            } else if ("2".equals(lerTipoMensagem)) {
                System.out.println("O tipo da sua mensagem apenas pode ser pública ou privada");

            }
            if (" ".equals(mensagem_conteudo)) {
                System.out.println("A sua mensagem está em branco, pretende guardar na mesma? S/N");
                String lerOpcaoMensagem = in.readLine();

            } else {
                System.out.println("Pretende guardar a mensagem? S/N");
                String lerOpcaoMensagem = in.readLine();

            }
            String lerOpcaoMensagem = in.readLine();
            if ("S".equals(lerOpcaoMensagem)) {

            } else if ("N".equals(lerOpcaoMensagem)) {
                System.out.println("A sua mensagem não foi guardada");

            }
        } else if ("3".equals(escolha)) {
            System.out.println("A sua sessão foi terminada. Até à próxima");
            user_logado = null;
            Projeto_ed inicio = new Projeto_ed();
        }
    }
}
