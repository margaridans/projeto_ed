/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Enumeracoes.TipoMensagem;
import Database.SqlConnection;
import ArrayList.ArrayUnorderedList;
import Iterator.ArrayIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import GrafoPesado.Network;
import projeto_ed.Projeto_ed;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Menus {

    private SqlConnection sql = Projeto_ed.connection;
    private String utilizador_logado = null;
    private Network<Pessoa> grafoPessoas = new Network<>();

    public Menus(String user_logado) {
        this.utilizador_logado = user_logado;
    }

    public void menuPrincipal(String user_logado) throws IOException {
        this.utilizador_logado = user_logado;
        //System.out.println("Existem numero de vertices: " + this.grafoPessoas.size());
        System.out.println("\n \n");
        System.out.println("* * * * * * * * * * * * Menu * * * * * * * * * * * *");
        System.out.println("*                                                  * ");
        System.out.println("*                                                  * ");
        System.out.println("*              1- Escrever mensagem                * ");
        System.out.println("*              2- Ver utilizadores                 * ");
        System.out.println("*              3- Gestão de pedidos de amizade     * ");
        System.out.println("*              4- Definições de conta              * ");
        System.out.println("*              5- Terminar Sessão                  * ");
        System.out.println("*                                                  * ");
        System.out.println("*                                                  * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * *  * ");
        System.out.print("O que pretende fazer? ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();

        if (null != escolha) {

            switch (escolha) {

                case "1":
                    System.out.println("\n");
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
                    System.out.println("* O QUE QUER PARTILHAR COM OS SEUS AMIGOS?  *");
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
                    String conteudo = escreverMensagem();

                    System.out.println("");
                    System.out.print("A sua mensagem vai ser de que tipo: \n"
                            + "1 - PRIVADA: Apenas os seus amigos podem ver as suas mensagens publicadas\n"
                            + "2 - PÚBLICA: Todos os utilizadores podem ver as suas mensagens publicadas\n\n"
                            + "Como pretende guardar a sua mensagem? ");

                    int lerTipoMensagem = Integer.parseInt(in.readLine());

                    Integer IdTipoMensagem = null;
                    if (1 == lerTipoMensagem) {
                        IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PRIVADA.getDescricao());
                    } else if (2 == lerTipoMensagem) {
                        IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PUBLICA.getDescricao());
                    }

                    System.out.println("\n");

                    System.out.println("Está prestes a partilhar a sua mensagem com os seus amigos...\n\n"
                            + "Tem a certeza que pretende continuar?\n"
                            + "1- Sim\n2- Não ");
                    String lerOpcaoMensagem = in.readLine();

                    if ("1".equals(lerOpcaoMensagem)) {
                        Date d = new Date();

                        Pessoa p = sql.getPessoa(user_logado);
                        Mensagem msg = new Mensagem(conteudo, d, IdTipoMensagem, p);

                        sql.inserirMensagem(msg);
                        System.out.println("Mensagem partilhada. Esteja atento agora aos comentários! ");
                        menuPrincipal(user_logado);
                    } else if ("2".equals(lerOpcaoMensagem)) {
                        System.out.println("A sua mensagem não foi partilhada com os seus amigos!");
                        menuPrincipal(user_logado);
                    }
                    break;

                case "2":
                    ArrayUnorderedList<Pessoa> p = new ArrayUnorderedList<>();
                    p = sql.getAllPessoas(user_logado);
                    if (p != null) {
                        printAllUsers(p);
                        Pessoa pEscolhida = escolherUser(p);
                        menuPessoa(pEscolhida, this.utilizador_logado);
                    } else {
                        System.out.println("É nulo");
                    }
                    break;

                case "3":
                    String escolha_opcaoPedido = MenuPedidosAmizade();

                    if ("2".equals(escolha_opcaoPedido)) {

                    }
                    break;

                case "4":
                    this.menuPrincipal(this.utilizador_logado);
                    break;

                case "5":
                    System.out.println("A sua sessão foi terminada. Até à próxima");
                    user_logado = null;
                    Projeto_ed inicio = new Projeto_ed();
                    break;
                default:
                    break;
            }
        }
    }

    public String escreverMensagem() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("");
        System.out.print("Escreva aqui: ");
        String lerMensagem = in.readLine();
        String mensagem_conteudo = lerMensagem;

        return mensagem_conteudo;
    }

    private void printAllUsers(ArrayUnorderedList<Pessoa> u) {

        int counter = 0;
        ArrayIterator it = (ArrayIterator) u.iterator();
        System.out.println();
        System.out.println("Escolha um utilizador através do seu índice");
        while (it.hasNext()) {
            counter++;
            Pessoa p = (Pessoa) it.next();
            System.out.println(counter + " -> " + p.getUser_email());
        }

    }

    private Pessoa escolherUser(ArrayUnorderedList<Pessoa> u) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();
        ArrayIterator it = (ArrayIterator) u.iterator();
        Integer counter = 0;
        while (it.hasNext()) {
            counter++;
            Pessoa p = (Pessoa) it.next();
            if (escolha.equals(counter.toString())) {
                System.out.println("Escolheu o utilizador " + p.getUser_nome());
                return p;
            }
        }
        return null;
    }

    /**
     * Método responsável por apresentar o menu do utilizador
     *
     * @param menuPessoa
     * @param utiliza
     * @throws IOException
     */
    public void menuPessoa(Pessoa menuPessoa, String utiliza) throws IOException {
        String nomePessoa = menuPessoa.getUser_nome();

        System.out.println("\n \n");
        System.out.println("* * * * * * * * * * Menu * * * * * * * * * * ");
        System.out.println("* Bem vindo ao perfil de " + nomePessoa + "*");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("*      1- Ver informação do utilizador     * ");
        System.out.println("*      2- Ver mensagens Utilizador         * ");
        System.out.println("*      3- Fazer pedido amizade             * ");
        System.out.println("*      4- Sair do perfil do utilizador     * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *  * ");
        System.out.println("O que pretende fazer? ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();

        if (null != escolha) {
            switch (escolha) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    this.menuPrincipal(this.utilizador_logado);
                    break;
                default:
                    break;
            }
        }

    }

    public String MenuPedidosAmizade() throws IOException {
        System.out.println("\n \n");
        System.out.println("* * * * * * * * * * Menu * * * * * * * * * * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("*          1- Fazer pedido patrocionado    * ");
        System.out.println("*          2- Fazer pedido amizade normal  * ");
        System.out.println("*          3- Aceitar/Rejeitar pedidos     * ");
        System.out.println("*          4- Voltar atrás                 * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *  * ");
        System.out.println("O que pretende fazer? ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();

        return escolha;
    }
}
