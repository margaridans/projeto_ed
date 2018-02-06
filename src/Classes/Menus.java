/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ArrayList.ArrayOrderedList;
import Classes.Enumeracoes.TipoMensagem;
import Database.SqlConnection;
import ArrayList.ArrayUnorderedList;
import Iterator.ArrayIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import GrafoPesado.Network;
import InterfacesGraficas.Login;
import java.sql.SQLException;
import java.text.ParseException;
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

    /**
     * Método responsável por imprimir o menu principal e por fazer a gestão das
     * escolhas que o utilizador vai fazer
     *
     * @param user_logado - email do utilizador logado
     * @throws IOException
     * @throws ParseException
     * @throws SQLException
     */
    public void menuPrincipal(String user_logado) throws IOException, ParseException, SQLException {
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
                        System.out.println("");
                        System.out.println("**********");
                        System.out.println("*  BOA!! *");
                        System.out.println("**********");
                        System.out.print("Mensagem partilhada. Esteja atento agora aos comentários! ");
                        menuPrincipal(user_logado);
                    } else if ("2".equals(lerOpcaoMensagem)) {
                        System.out.println("");
                        System.out.println("*************");
                        System.out.println("*  OH NÃO!! *");
                        System.out.println("*************");
                        System.out.print("A sua mensagem não foi partilhada com os seus amigos!");
                        menuPrincipal(user_logado);
                    }
                    break;

                case "2":
                    ArrayUnorderedList<Pessoa> pessoa = new ArrayUnorderedList<>();
                    pessoa = sql.getAllPessoas(user_logado);
                    if (pessoa != null) {
                        printAllUsers(pessoa);
                        Pessoa pEscolhida = escolherUser(pessoa);
                        System.out.println("");
                        menuPessoa(pEscolhida, this.utilizador_logado);
                    } else if (pessoa == null) {
                        System.out.println("Não existem utilizadores");
                    }
                    break;

                case "3":
                    String escolha_opcaoPedido = MenuPedidosAmizade();

                    if ("2".equals(escolha_opcaoPedido)) {

                    }
                    break;

                case "4":
                    System.out.println("");
                    System.out.println("*********************************");
                    System.out.println("*       DEFINIÇÕES DE CONTA     *");
                    System.out.println("*********************************");
                    System.out.println("");
                    System.out.println("Nome: " + sql.getPessoa(user_logado).getUser_nome());
                    System.out.println("Email: " + user_logado);
                    System.out.println("Número de créditos: " + sql.getPessoa(user_logado).getNr_creditos());

                    break;

                case "5":
                    System.out.println("A sua sessão foi terminada. Até à próxima");
                    user_logado = null;
                    Login voltar_inicio = new Login();

                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Método responsável por imprimir o menu do utilizador que foi escolhido
     * para o seu perfil ser visualizado e por fazer a gestão das escolhas que o
     * utilizador vai fazer perante o menu
     *
     * @param pessoaEscolhida - pessoa escolhida para o seu perfil ser
     * visualizado
     * @param utilizador - utilizador logado
     * @throws IOException
     * @throws java.text.ParseException
     */
    public void menuPessoa(Pessoa pessoaEscolhida, String utilizador) throws IOException, ParseException, SQLException {
        String nomePessoa = pessoaEscolhida.getUser_nome();

        System.out.println("\n \n");
        System.out.println("* * * * * * * * * * * * Menu * * * * * * * * * * * * * *");
        System.out.println("*            Bem vindo ao perfil de " + nomePessoa + "          *");
        System.out.println("*                                                      *");
        System.out.println("*                                                      *");
        System.out.println("*           1- Ver informação do utilizador            *");
        System.out.println("*           2- Ver mensagens Utilizador                *");
        System.out.println("*           3- Fazer pedido amizade                    *");
        System.out.println("*           4- Sair do perfil do utilizador            *");
        System.out.println("*                                                      *");
        System.out.println("*                                                      *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *  * * * * * * *");
        System.out.println("O que pretende fazer? ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();

        if (null != escolha) {
            switch (escolha) {
                case "1":
                    System.out.println("");
                    System.out.println("************************************************************");
                    System.out.println("*              INFORMAÇÃO DO USER " + nomePessoa.toUpperCase() + "                *");
                    System.out.println("************************************************************");
                    System.out.println("");
                    System.out.println("Nome: " + nomePessoa);
                    System.out.println("Email: " + sql.getPessoaByName(nomePessoa).getUser_email());
                    System.out.println("Número de créditos: " + sql.getPessoaByName(nomePessoa).getNr_creditos());
                    menuPessoa(pessoaEscolhida, utilizador);
                    break;
                case "2":
                    String email = sql.getPessoaByName(nomePessoa).getUser_email();
                    ArrayOrderedList<Mensagem> msg = new ArrayOrderedList<>();

                    //SE NÃO FOREM AMIGOS -> PUBLICAS
                    System.out.println("Uma vez que não é amigo do utilizador " + nomePessoa + " só pode ver as suas mensagens públicas");

                    System.out.println("");
                    System.out.println("***************************************");
                    System.out.println("*              MENSAGENS              *");
                    System.out.println("***************************************");

                    msg = sql.getMensagensPublicas(email);
                    if (msg != null) {

                        printMsgPublicas(msg);
                        System.out.println("");

                    } else {
                        System.out.println("Não existem mensagens deste utilizadores");
                    }

                    //SE FOR AMIGOS -> MOSTRA TODAS
                    /* System.out.println("");
                    System.out.println("***************************************");
                    System.out.println("*              MENSAGENS              *");
                    System.out.println("***************************************");

                    msg = sql.getAllMensagens(email);
                    if (msg != null) {

                        printMsgPublicas(msg);
                        System.out.println("");

                    } else {
                        System.out.println("Não existem mensagens deste utilizadores");
                    }*/
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

    /**
     * Método responsável apenas por mostrar ao utilizador o menu dos pedidos de
     * amizade e ler do teclado a sua opção
     *
     * @return a opção escolhida pelo utilizador
     * @throws IOException
     */
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

    /**
     * Este método é responsável pela seleção do utilizador ao qual pretendemos
     * ver o perfil, através do BufferedReader lê a opção que o utilizador
     * escolheu e através do iterador vai percorrendo os utilizadores mostrados
     * no ecrã fazendo o count, depois vai ver se a opção que o utilizador
     * escolheu é igual ao count se foi retorna o nome da pessoa que foi, se não
     * continua a percorrer até ser escolhida
     *
     * @param pessoa - coleção de pessoas que podem ser escolhidas, isto é, que
     * foram mostradas ao utilizador para ele escolher a pessoa que queria
     * visualizar o perfil
     * @return pessoa escolhida
     * @throws IOException
     */
    private Pessoa escolherUser(ArrayUnorderedList<Pessoa> pessoa) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();

        ArrayIterator it = (ArrayIterator) pessoa.iterator();
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
     * Método responsável pela escrita da mensagem, o utilizador vai escrever a
     * mensagem e através do BufferedReader vamos ler do teclado o que
     * utilizador escreveu e armazenamos numa variavel (mensagem_conteudo)
     *
     * @return o conteúdo da mensagem do utilizador
     * @throws IOException
     */
    public String escreverMensagem() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("");
        System.out.print("Escreva aqui: ");
        String lerMensagem = in.readLine();
        String mensagem_conteudo = lerMensagem;

        return mensagem_conteudo;
    }

    /**
     * Método que vai imprimir todos as mensagens públicas, para isso
     * percorremos a nossa OrderedList de mensagens através do iterador
     *
     * @param msg coleção de mensagens que vamos percorrer e consequentemente
     * imprimir
     */
    private void printMsgPublicas(ArrayOrderedList<Mensagem> msg) {

        ArrayIterator it = (ArrayIterator) msg.iterator();
        System.out.println();
        while (it.hasNext()) {
            Mensagem mens = (Mensagem) it.next();
            System.out.println("Publicada em: " + mens.getData_publicacao().toString());
            System.out.println("Mensagem: " + mens.getConteudo_msg());
            System.out.println("");

        }

    }

    /**
     * Método que vai imprimir todos os utilizadores, para isso percorremos a
     * nossa UnorderedList de pessoas através do iterador
     *
     * @param pessoa coleção de pessoas que vamos percorrer e consequentemente
     * imprimir
     */
    private void printAllUsers(ArrayUnorderedList<Pessoa> pessoa) {

        int counter = 0;
        ArrayIterator it = (ArrayIterator) pessoa.iterator();
        System.out.println();
        System.out.println("Escolha um utilizador através do seu índice: ");
        while (it.hasNext()) {
            counter++;
            Pessoa p = (Pessoa) it.next();
            System.out.println(counter + " -> " + p.getUser_email());
            System.out.println("");
            System.out.println("Qual o utilizador que pretende escolher? ");
        }

    }
}
