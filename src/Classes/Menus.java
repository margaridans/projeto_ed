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
import java.sql.SQLException;
import java.text.ParseException;
import projeto_ed.Projeto_ed;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Menus {

    private Integer id_Mensagem = 0;
    private SqlConnection sql = Projeto_ed.connection;
    private String utilizador_logado = null;
    private Network<Pessoa> grafoPessoas = new Network<>();

    public Menus(String user_logado) {
        this.utilizador_logado = user_logado;
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    ArrayIterator it;
    Boolean terminarSwitch = false;

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
        System.out.println("*              2- Gestão minhas mensagens          * ");
        System.out.println("*              3- Ver utilizadores                 * ");
        System.out.println("*              4- Gestão de pedidos de amizade     * ");
        System.out.println("*              5- Definições de conta              * ");
        System.out.println("*              6- Terminar Sessão                  * ");
        System.out.println("*                                                  * ");
        System.out.println("*                                                  * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * *  * ");

        System.out.print("O que pretende fazer? ");
        String escolha = in.readLine();

        if (null != escolha) {

            switch (escolha) {
                //ESCREVER MENSAGEM
                case "1":
                    System.out.println("\n");
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
                    System.out.println("* O QUE QUER PARTILHAR COM OS SEUS AMIGOS?  *");
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

                    //Onde o utilizador vai escrever a mensagem
                    String conteudo = escreverMensagem();

                    System.out.println("");
                    System.out.print("A sua mensagem vai ser de que tipo: \n"
                            + "1 - PRIVADA: Apenas os seus amigos podem ver as suas mensagens publicadas\n"
                            + "2 - PÚBLICA: Todos os utilizadores podem ver as suas mensagens publicadas\n\n"
                            + "Como pretende guardar a sua mensagem? ");
                    int lerTipoMensagem = Integer.parseInt(in.readLine());

                    Integer IdTipoMensagem = null;

                    terminarSwitch = false;
                    while (terminarSwitch == false) {
                        switch (lerTipoMensagem) {
                            case 1:
                                IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PRIVADA.getDescricao());
                                terminarSwitch = true;
                                break;
                            case 2:
                                IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PUBLICA.getDescricao());
                                terminarSwitch = true;
                                break;
                            default:
                                System.out.println("A sua opção não foi válida, escolha apenas 1 - Privada   |     2 - Pública");
                                lerTipoMensagem = Integer.parseInt(in.readLine());
                                terminarSwitch = false;
                                break;
                        }
                    }

                    System.out.println("\n");
                    System.out.println("Está prestes a partilhar a sua mensagem com os seus amigos...\n\n"
                            + "Tem a certeza que pretende continuar?\n"
                            + "1- Sim\n2- Não ");
                    String continuarMensagem = in.readLine();

                    terminarSwitch = false;
                    while (terminarSwitch == false) {
                        switch (continuarMensagem) {
                            case "1": //Se sim pretende continuar
                                Date data_pub = new Date();
                                Pessoa pessoa_logada = sql.getPessoa(user_logado); //Vai buscar a pessoa logada
                                Mensagem msg = new Mensagem(conteudo, data_pub, IdTipoMensagem, pessoa_logada);
                                sql.inserirMensagem(msg); //Insere a mensagem
                                System.out.println("");
                                System.out.println("**********");
                                System.out.println("*  BOA!! *");
                                System.out.println("**********");
                                System.out.print("Mensagem partilhada. Esteja atento agora aos comentários! ");
                                menuPrincipal(user_logado); //Volta ao menu principal
                                terminarSwitch = true;
                                break;

                            case "2": //Se não pretende continuar
                                System.out.println("");
                                System.out.println("*************");
                                System.out.println("*  OH NÃO!! *");
                                System.out.println("*************");
                                System.out.print("A sua mensagem não foi partilhada com os seus amigos!");
                                menuPrincipal(user_logado); //Volta ao menu principal
                                terminarSwitch = true;
                                break;
                            default:
                                System.out.println("Essa opção é inválida. Selecione apenas 1- Sim   |   2- Não");
                                continuarMensagem = in.readLine();
                                terminarSwitch = false;
                                break;
                        }
                    }
                    break;

                //GERIR MINHAS MENSAGENS
                case "2":
                    ArrayOrderedList<Mensagem> msg = new ArrayOrderedList<>();

                    System.out.println("\n");
                    System.out.println("************** AS MINHAS MENSAGENS**************");
                    System.out.println("");

                    msg = sql.getAllMensagens(user_logado); //vai buscar todas as minhas mensagens

                    if (msg.size() == 0) {
                        System.out.println("Não tem mensagens");
                    } else {
                        printMsg(msg);  //imprime todas as minhas mensagens

                        System.out.println("");
                        System.out.println("Deseja eliminar alguma mensagem?\n1-Sim\n2-Não");
                        String desejaEliminar = in.readLine();

                        terminarSwitch = false;
                        while (terminarSwitch == false) {
                            switch (desejaEliminar) {
                                case "1": //Se desejar eliminar
                                    System.out.print("Qual a mensagem que pretende eliminar? Indique o seu índice: ");
                                    Mensagem msg_eliminada = escolherMsgEliminada(msg); //Escolha da mensagem que vai ser eliminada
                                    Integer idMensagem = sql.verIdMensagem(msg_eliminada.getConteudo_msg());
                                    sql.apagarMensagem(idMensagem); //Apaga a mensagem
                                    System.out.println("");
                                    System.out.println("Com pena nossa, a sua mensagem foi eliminada.");
                                    menuPrincipal(user_logado); //Volta ao menu principal
                                    terminarSwitch = true;
                                    break;

                                case "2": //Se não desejar eliminar
                                    System.out.println("");
                                    System.out.println("Ainda bem que não quis eliminar as suas mensagens!");
                                    menuPrincipal(user_logado); //Volta ao menu principal
                                    terminarSwitch = true;
                                    break;
                                default:
                                    System.out.println("Essa opção é inválida. Selecione apenas 1- Sim   |   2- Não");
                                    desejaEliminar = in.readLine();
                                    terminarSwitch = false;
                                    break;
                            }
                        }
                    }
                    break;

                //VER UTILIZADORES    
                case "3":
                    ArrayUnorderedList<Pessoa> pessoa = new ArrayUnorderedList<>();
                    pessoa = sql.getAllPessoas(user_logado); //Vai buscar as pessoas todas

                    //Se houver pessoas 
                    if (pessoa.size() != 0) {
                        printAllUsers(pessoa); //mostra todos os utilizadores
                        Pessoa pEscolhida = escolherUser(pessoa); //guarda a pessoa que foi escolhida
                        System.out.println("");
                        menuPessoa(pEscolhida, this.utilizador_logado); //Vai para o menu da pessoa
                        //Se não houver pessoas 
                    } else {
                        System.out.println("");
                        System.out.println("Não existem utilizadores");
                        menuPrincipal(user_logado); //Volta ao menu principal
                    }
                    break;

                //PEDIDOS DE AMIZADE
                case "4":
                    String escolha_opcaoPedido = MenuPedidosAmizade();

                //DEFINIÇÕES DE CONTA
                case "5":
                    String nome_pessoaLogada = sql.getPessoa(user_logado).getUser_nome(); //nome da pessoa logada

                    System.out.println("");
                    System.out.println("*********************************");
                    System.out.println("*       DEFINIÇÕES DE CONTA     *");
                    System.out.println("*********************************");
                    System.out.println("");

                    System.out.println("Nome: " + nome_pessoaLogada);
                    System.out.println("Email: " + user_logado);
                    System.out.println("Número de créditos: " + sql.getPessoa(user_logado).getNr_creditos());
                    System.out.println("");

                    System.out.println("Deseja apagar a sua conta?\n1-Sim\n2-Não");
                    String apagarConta = in.readLine();

                    terminarSwitch = false;
                    while (terminarSwitch == false) {
                        switch (apagarConta) {
                            case "1":  //Se quiser apagar conta
                                sql.apagarPessoa(user_logado); //apaga a conta
                                System.out.println("Que pena. Vai fazer falta. Até um dia");
                                System.exit(0); //Encerra o programa
                                terminarSwitch = true;
                                break;

                            case "2": //Se não quiser apagar conta
                                System.out.println("");
                                System.out.println("Ainda bem que não apagou a sua conta. Iria fazer falta!");
                                menuPrincipal(user_logado);
                                terminarSwitch = true;
                                break;

                            default: //Se não escolher nenhuma destas opções
                                System.out.println("");
                                System.out.println("Escolha uma opção válida: 1 - Sim   |    2 - Não");
                                apagarConta = in.readLine();
                                terminarSwitch = false;
                                break;
                        }
                    }
                    break;

                //TEMINAR SESSÃO
                case "6":
                    System.out.println("A sua sessão foi terminada. Até à próxima");
                    user_logado = null;
                    System.exit(0); //Encerra o programa
                    break;
                default: //Se não escolher nenhuma opção do menu principal
                    menuPrincipal(user_logado); //Volta para o menu principal
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

        String escolha = in.readLine();

        switch (escolha) {
            //VER INFORMAÇÕES AMIGO/NÃO AMIGO
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

            //VER MENSAGENS DO AMIGO/NÃO AMIGO
            case "2":
                String email = sql.getPessoaByName(nomePessoa).getUser_email();
                ArrayOrderedList<Mensagem> msg = new ArrayOrderedList<>();

                //SE NÃO FOREM AMIGOS -> PUBLICAS
                System.out.println("Uma vez que não é amigo do utilizador " + nomePessoa + " só pode ver as suas mensagens públicas");

                System.out.println("");
                System.out.println("***************************************");
                System.out.println("*              MENSAGENS              *");
                System.out.println("***************************************");
                System.out.println("");
                msg = sql.getMensagensPublicas(email); //vai buscar as mensagens públicas

                if (msg.size() != 0) {
                    printMsg(msg); //imprime as mensagens públicas
                    System.out.println("");
                    System.out.println("Pretende comentar alguma mensagem?\n1- Sim\n2- Não, pretendo sair");
                    String pertendeComentar = in.readLine();

                    terminarSwitch = false;
                    while (terminarSwitch == false) {
                        switch (pertendeComentar) {
                            case "1": //Caso queira comentar
                                Date data_pub = new Date();
                                System.out.println("");
                                System.out.print("Qual a mensagem que pretende comentar? Indique o seu índice: ");
                                System.out.println("\n");
                                System.out.println("***************COMENTÁRIOS****************");
                                Mensagem msg_comentar = escolherMsg(msg);

                                id_Mensagem = sql.verIdMensagem(msg_comentar.getConteudo_msg());

                                System.out.println("");
                                System.out.print("Comente aqui: ");
                                String conteudo_coment = in.readLine();
                                Pessoa pessoa_logada = sql.getPessoa(utilizador_logado);

                                Comentario comentario = new Comentario(conteudo_coment, data_pub, pessoa_logada, id_Mensagem);
                                sql.inserirComent(comentario);
                                System.out.println("O seu comentário foi feito com sucesso");
                                menuPessoa(pessoaEscolhida, utilizador);
                                terminarSwitch = true;
                                break;

                            case "2": //Caso não queira comentar
                                menuPessoa(pessoaEscolhida, utilizador);
                                terminarSwitch = true;
                                break;

                            default://Caso escolha uma opção inválida
                                System.out.println("");
                                System.out.println("Escolha uma opção válida: 1 - Sim   |    2 - Não");
                                pertendeComentar = in.readLine();
                                terminarSwitch = false;

                        }
                    }

                } else {
                    System.out.println("Não existem mensagens deste utilizadores");
                }

                //SE FOR AMIGOS -> MOSTRA TODAS -> FALTA CÓDIGO DEPOIS DOS PEDIDOS DE AMIZADE
                break;

            //FAZER PEDIDO DE AMIZADE
            case "3":
                break;

            //SAIR DO PERFIL
            case "4":
                this.menuPrincipal(this.utilizador_logado);
                break;
            default: //Se não escolher nenhuma opção do menu principal
                menuPessoa(pessoaEscolhida, utilizador); //Volta para o menu da pessoa
                break;
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

        String escolhaMenuAmizade = in.readLine();

        return escolhaMenuAmizade;
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
        it = (ArrayIterator) pessoa.iterator();
        Integer counter = 0;

        while (it.hasNext()) {
            String escolhaUser = in.readLine(); //Lê a escolha do user

            counter++;
            Pessoa p = (Pessoa) it.next();
            terminarSwitch = false;
            while (terminarSwitch == false) {
                if (escolhaUser.equals(counter.toString())) {
                    System.out.println("Escolheu o utilizador " + p.getUser_nome());
                    terminarSwitch = true;
                    return p;

                } else {
                    System.out.println("Escolha novamente, esse utilizador não existe.");
                    escolhaUser = in.readLine();
                    terminarSwitch = false;
                }
            }

        }
        return null;

    }

    /**
     * Este método é responsável pela seleção da mensagem que pretendemos,
     * através do BufferedReader lê a opção que o utilizador escolheu e através
     * do iterador vai percorrendo as mensasagens mostradas no ecrã fazendo o
     * count, depois vai ver se a opção que o utilizador escolheu é igual ao
     * count se foi retorna a mensagem que foi, se não continua a percorrer até
     * ser escolhida
     *
     * @param msg coleção de mensagens que podem ser escolhidas, isto é, que
     * foram mostradas ao utilizador para ele escolher
     * @return mensagem escolhida
     * @throws IOException
     */
    private Mensagem escolherMsg(ArrayOrderedList<Mensagem> msg) throws IOException {
        String escolhaMensagem = in.readLine();

        it = (ArrayIterator) msg.iterator();
        Integer counter = 0;
        while (it.hasNext()) {
            counter++;
            Mensagem mensa = (Mensagem) it.next();
            if (escolhaMensagem.equals(counter.toString())) {
                System.out.println("Escolheu a mensagem " + "'" + mensa.getConteudo_msg() + "'" + " para ser comentada");
                return mensa;
            }
        }
        return null;

    }

    /**
     * Este método é responsável pelo retorno da mensagem que o utilizador quer
     * eliminar
     *
     * @param msg coleção de mensagens que vai ser percorrida para ver qual o
     * utilizador vai escolher
     * @return mensagem que vai ser eliminada
     * @throws IOException
     */
    private Mensagem escolherMsgEliminada(ArrayOrderedList<Mensagem> msg) throws IOException {
        String escolhaMsgEliminada = in.readLine();

        it = (ArrayIterator) msg.iterator();
        Integer counter = 0;
        while (it.hasNext()) {
            counter++;
            Mensagem mensa = (Mensagem) it.next();
            if (escolhaMsgEliminada.equals(counter.toString())) {
                System.out.println("Escolheu a mensagem " + "'" + mensa.getConteudo_msg() + "'" + " para ser eliminada");
                return mensa;
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
    private void printMsg(ArrayOrderedList<Mensagem> msg) throws ParseException, SQLException {
        Integer counter = 0;
        it = (ArrayIterator) msg.iterator();
        ArrayOrderedList<Comentario> comentario_mensagem;

        while (it.hasNext()) {
            counter++;
            Mensagem mens = (Mensagem) it.next();
            System.out.println("Mensagem " + counter + ": " + mens.getConteudo_msg());
            System.out.println("Publicada em: " + mens.getData_publicacao().toLocaleString());
            System.out.println("");

            Integer idMensagem = sql.verIdMensagem(mens.getConteudo_msg());
            //Se exister comentários para aquela mensagem
            if (sql.ifExisteComentariosMensagem(idMensagem) == true) {
                Comentario comentario = new Comentario();

                comentario_mensagem = sql.getComentarioById(idMensagem); //ele vai buscar os comentário
                printComentario(comentario_mensagem); //e vai imprimir

            } else {
                System.out.println("Não há comentários");
                System.out.println("______________________________________________________");
                System.out.println("");
            }

        }

    }

    /**
     * Método resposável por imprimir os comentários
     *
     * @param coment coleção de comentários que vão ser mostrados
     * @throws ParseException
     */
    private void printComentario(ArrayOrderedList<Comentario> coment) throws ParseException {
        Integer counter = 0;
        it = (ArrayIterator) coment.iterator();

        System.out.println();
        while (it.hasNext()) {
            counter++;
            Comentario comentario = (Comentario) it.next();
            System.out.println("Comentário " + counter + ": " + comentario.getComentario());
            System.out.println("Publicado em: " + comentario.getData_comentario().toLocaleString());
            System.out.println("");
        }
        System.out.println("______________________________________________________");

    }

    /**
     * Método que vai imprimir todos os utilizadores, para isso percorremos a
     * nossa UnorderedList de pessoas através do iterador
     *
     * @param pessoa coleção de pessoas que vamos percorrer e consequentemente
     * imprimir
     */
    private void printAllUsers(ArrayUnorderedList<Pessoa> pessoa) {

        Integer counter = 0;
        it = (ArrayIterator) pessoa.iterator();

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
