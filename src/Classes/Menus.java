package Classes;

import ArrayList.ArrayOrderedList;
import Enumeracoes.TipoMensagem;
import Database.SqlConnection;
import ArrayList.ArrayUnorderedList;
import Exceptions.EmptyCollectionException;
import Iterator.ArrayIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import GrafoPesado.Network;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import projeto_ed.Projeto_ed;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Menus {

    private Integer id_Mensagem = 0;
    private final SqlConnection sql = Projeto_ed.connection;
    private String utilizador_logado = null;
    private Network<Pessoa> grafoPessoas;

    public Menus(String user_logado) {
        this.utilizador_logado = user_logado;
        this.grafoPessoas = new Network<>();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    ArrayIterator it;
    Boolean terminarSwitch = false;

    //---------------------------------------------------------------------MENUS---------------------------------------------------
    /**
     * Método responsável por imprimir o menu principal e por fazer a gestão das
     * escolhas que o utilizador vai fazer
     *
     * @param user_logado - email do utilizador logado
     * @throws IOException
     * @throws ParseException
     * @throws SQLException
     * @throws Exceptions.EmptyCollectionException if an empty collection
     * exception occurs
     */
    public void menuPrincipal(String user_logado) throws IOException, ParseException, SQLException, EmptyCollectionException {
        this.utilizador_logado = user_logado;
        //System.out.println("Existem numero de vertices: " + this.grafoPessoas.size());
        Pessoa pes_logada = sql.getPessoa(utilizador_logado);

        System.out.println("\n");
        System.out.println("* * * * * * * * * * * * Menu * * * * * * * * * * * *");
        System.out.println("*                                                  * ");
        System.out.println("*                                                  * ");
        System.out.println("*              1- Escrever mensagem                * ");
        System.out.println("*              2- Gestão minhas mensagens          * ");
        System.out.println("*              3- Ver utilizadores                 * ");
        System.out.println("*              4- Ver meus amigos                  * ");
        System.out.println("*              5- Gestão de pedidos de amizade     * ");
        System.out.println("*              6- Definições de conta              * ");
        System.out.println("*              7- Terminar Sessão                  * ");
        System.out.println("*                                                  * ");
        System.out.println("*                                                  * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * *  * ");

        System.out.print("O que pretende fazer? ");
        String escolha = in.readLine();

        if (null != escolha) {
            terminarSwitch = false;

            while (terminarSwitch == false) {
                switch (escolha) {
                    //ESCREVER MENSAGEM

                    case "1":
                        System.out.println("\n");
                        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
                        System.out.println("* O QUE QUER PARTILHAR COM OS SEUS AMIGOS?  *");
                        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
                        System.out.println("Se quiser sair clique no 0");
                        //Onde o utilizador vai escrever a mensagem
                        String conteudo = escreverMensagem();

                        System.out.println("");
                        System.out.print("A sua mensagem vai ser de que tipo: \n"
                                + "1 - PÚBLICA: Todos os utilizadores podem ver as suas mensagens publicadas\n"
                                + "2 - PRIVADA: Apenas os seus amigos podem ver as suas mensagens publicadas\n\n"
                                + "Como pretende guardar a sua mensagem? ");
                        int lerTipoMensagem = Integer.parseInt(in.readLine());

                        if (lerTipoMensagem == 0) {
                            System.out.println(".... vai para o menu principal ....");
                            menuPrincipal(utilizador_logado);
                        }
                        Integer IdTipoMensagem = null;

                        terminarSwitch = false;
                        while (terminarSwitch == false) {
                            switch (lerTipoMensagem) {
                                case 1:
                                    IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PUBLICA.getDescricao());
                                    terminarSwitch = true;
                                    break;
                                case 2:
                                    IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PRIVADA.getDescricao());
                                    terminarSwitch = true;
                                    break;
                                default:
                                    System.err.println("A sua opção não foi válida, escolha apenas 1 - Privada   |     2 - Pública");
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
                                    Mensagem msg = new Mensagem(conteudo, data_pub, IdTipoMensagem, pes_logada);
                                    sql.inserirMensagem(msg); //Insere a mensagem
                                    System.out.println("");
                                    System.out.println("**********");
                                    System.out.println("*  BOA!! *");
                                    System.out.println("**********");
                                    System.out.print("Mensagem partilhada. Esteja atento agora aos comentários! ");

                                    if (IdTipoMensagem == 2) {
                                        System.out.println("\n");
                                        System.out.println("Uma vez que a sua mensagem é do tipo privada apenas vai estar vísivel para os seus amigos");
                                        System.out.println("----- Teste de alcance -----");

                                        Iterator iterator = this.grafoPessoas.getAmigos(pes_logada).iterator();
                                        System.out.println("As pessoas que vão poder ver esta mensagem: Os teus amigos\n");
                                        while (iterator.hasNext()) {
                                            Pessoa p = (Pessoa) iterator.next();
                                            System.out.println("- " + p.getUser_email());
                                        }

                                        menuPrincipal(utilizador_logado); //Volta ao menu principal
                                        break;
                                    } else {
                                        if (IdTipoMensagem == 1) {
                                            System.out.println("\n4"
                                                    + "");
                                            System.out.println("Uma vez que a sua mensagem é do tipo pública vai estar vísivel para toda a gente que se encontrar registada");
                                            System.out.println("----- Teste de alcance -----");
                                            System.out.println("As pessoas que vão poder ver esta mensagem: Toda a gente\n");

                                            this.grafoPessoas.printVertex(pes_logada);
                                            menuPrincipal(utilizador_logado);
                                        }
                                    }
                                    break;

                                case "2": //Se não pretende continuar
                                    System.out.println("");
                                    System.out.println("*************");
                                    System.out.println("*  OH NÃO!! *");
                                    System.out.println("*************");
                                    System.out.print("A sua mensagem não foi partilhada com os seus amigos!");
                                    menuPrincipal(utilizador_logado); //Volta ao menu principal
                                    terminarSwitch = true;
                                    break;
                                default:
                                    System.err.println("Essa opção é inválida. Selecione apenas 1- Sim   |   2- Não");
                                    continuarMensagem = in.readLine();
                                    terminarSwitch = false;
                                    break;
                            }
                        }
                        terminarSwitch = true;
                        break;

                    //GERIR MINHAS MENSAGENS
                    case "2":
                        ArrayOrderedList<Mensagem> msg = new ArrayOrderedList<>();

                        System.out.println("\n");
                        System.out.println("************** AS MINHAS MENSAGENS**************");
                        System.out.println("");

                        msg = sql.getAllMensagens(utilizador_logado); //vai buscar todas as minhas mensagens

                        if (msg.size() == 0) {
                            System.out.println("Não tem mensagens");
                            menuPrincipal(utilizador_logado);
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
                                        menuPrincipal(utilizador_logado); //Volta ao menu principal
                                        terminarSwitch = true;
                                        break;

                                    case "2": //Se não desejar eliminar
                                        System.out.println("");
                                        System.out.println("Ainda bem que não quis eliminar as suas mensagens!");
                                        menuPrincipal(utilizador_logado); //Volta ao menu principal
                                        terminarSwitch = true;
                                        break;
                                    default:
                                        System.err.println("Essa opção é inválida. Selecione apenas 1- Sim   |   2- Não");
                                        desejaEliminar = in.readLine();
                                        terminarSwitch = false;
                                        break;
                                }
                            }
                        }
                        terminarSwitch = true;
                        break;

                    //VER UTILIZADORES    
                    case "3":
                        ArrayOrderedList<Pessoa> pessoa = new ArrayOrderedList<>();
                        pessoa = sql.getAllPessoas(utilizador_logado); //Vai buscar as pessoas todas

                        //Se houver pessoas 
                        if (pessoa.size() != 0) {
                            // printAllUsers(pessoa); //mostra todos os utilizadores
                            grafoPessoas.printUsers(pes_logada);

                            Pessoa pEscolhida = escolherUser(pessoa); //guarda a pessoa que foi escolhida

                            System.out.println("");
                            menuPessoa(pEscolhida, this.utilizador_logado); //Vai para o menu da pessoa
                            //Se não houver pessoas 
                        } else {
                            System.out.println("");
                            System.out.println("Não existem utilizadores");
                            menuPrincipal(utilizador_logado); //Volta ao menu principal
                        }
                        terminarSwitch = true;
                        break;

                    case "4":
                        System.out.println("");
                        System.out.println("*********** OS MEUS AMIGOS **********");

                        Iterator iterator = this.grafoPessoas.getAmigos(pes_logada).iterator();
                        while (iterator.hasNext()) {
                            Pessoa p = (Pessoa) iterator.next();
                            System.out.println("- " + p.getUser_email());
                        }
                        System.out.println("");
                        System.out.print("Para sair clique no 0  --> ");
                        String opcaoSair = in.readLine();

                        terminarSwitch = false;
                        while (terminarSwitch == false) {
                            if ("0".equals(opcaoSair)) {
                                System.out.println(".... vai para o menu principal ....");
                                System.out.println("");
                                menuPrincipal(utilizador_logado);
                                terminarSwitch = true;
                            } else {
                                System.err.println("Opção inválida. Insira 0");
                                opcaoSair = in.readLine();
                                terminarSwitch = false;
                            }
                        }
                        terminarSwitch = true;
                        break;

                    //PEDIDOS DE AMIZADE
                    case "5":

                        String escolha_opcaoPedido = MenuPedidosAmizade();
                        terminarSwitch = false;
                        while (terminarSwitch == false) {
                            switch (escolha_opcaoPedido) {
                                case "1":
                                    Integer creditos = pes_logada.getNr_creditos();
                                    System.out.println("Se quiser sair clique no 0");
                                    System.out.println("\n");
                                    this.grafoPessoas.printVertex(pes_logada);
                                    System.out.println("");

                                    System.out.println("Indique aqui o EMAIL da pessoa ao qual pretende fazer pedido de amizade: ");
                                    String lerPessoaPedido = in.readLine();
                                    if (lerPessoaPedido.equals("0")) {
                                        System.out.println(".... vai para o menu principal ....");
                                        menuPrincipal(utilizador_logado);
                                    }
                                    Pessoa pessoaEscolhida = sql.getPessoa(lerPessoaPedido);
                                    String pessoa_origem = utilizador_logado;

                                    String pessoa_destino = lerPessoaPedido;

                                    if (!grafoPessoas.personExists(lerPessoaPedido)) {
                                        System.out.println("");
                                        System.out.println("Essa pessoa não existe");
                                        System.out.println("");
                                        menuPrincipal(utilizador_logado);

                                    } else {
                                        fazerPedido(pessoaEscolhida, utilizador_logado);
                                    }

                                    break;

                                case "2":

                                    Boolean hasPedido = sql.ifExisteAmizadesPendentes(utilizador_logado);
                                    Integer counter = 0;
                                    if (hasPedido == true) {
                                        ArrayUnorderedList<PedidoAmizade> pedidoAmizade = new ArrayUnorderedList<>();

                                        pedidoAmizade = sql.getPedidosPendentes(utilizador_logado);

                                        it = (ArrayIterator) pedidoAmizade.iterator();
                                        PedidoAmizade pedido = null;
                                        System.out.println("Você tem " + pedidoAmizade.size() + " pedido(s) de amizade pendente(s) ");

                                        while (it.hasNext()) {
                                            pedido = (PedidoAmizade) it.next();
                                            counter++;
                                            System.out.println("");
                                            System.out.print("Pedido nº " + counter + "- " + "Pedido amizade de " + pedido.getUser_origem().getUser_email());
                                            System.out.println("");
                                            System.out.println("1 - Aceitar      |      2 - Rejeitar       |    3 - Ignorar");

                                            String rspostaPedido = in.readLine();

                                            switch (rspostaPedido) {
                                                case "1":
                                                    sql.aceitarPedido(pedido.getUser_origem().getUser_email(), utilizador_logado);
                                                    System.out.println("Você aceitou o pedido de amizade de " + pedido.getUser_origem().getUser_email() + ".\nAgora já são amigos, já pode ver e partilhar as mensagens privadas");

                                                    break;
                                                case "2":
                                                    sql.rejeitarPedido(pedido.getUser_origem().getUser_email(), utilizador_logado);
                                                    System.out.println("Você rejeitou o pedido de amizade de " + pedido.getUser_origem().getUser_email());
                                                    break;
                                                case "3":
                                                    System.out.println("Este pedido foi ignorado mas vai-se manter na lista de espera");
                                                    break;
                                                default:
                                                    System.err.println("Opção inválida!");
                                                    menuPrincipal(utilizador_logado);
                                                    break;
                                            }
                                        }

                                        menuPrincipal(utilizador_logado);
                                    } else {
                                        System.out.println("Você não tem pedidos de amizade");
                                        menuPrincipal(utilizador_logado);
                                        terminarSwitch = false;
                                    }
                                    terminarSwitch = true;
                                    break;

                                case "3": //Voltar atrás
                                    menuPrincipal(utilizador_logado);
                                    terminarSwitch = true;
                                    break;
                                default: //Caso não selecione nenhuma opção
                                    System.err.println("Opção inválida selecione apenas um número válido: ");
                                    escolha_opcaoPedido = MenuPedidosAmizade();
                                    terminarSwitch = false;
                                    break;
                            }
                        }
                        terminarSwitch = true;
                        break;

                    //DEFINIÇÕES DE CONTA
                    case "6":
                        String nome_pessoaLogada = pes_logada.getUser_nome(); //nome da pessoa logada
                        Integer creditos = pes_logada.getNr_creditos();

                        System.out.println("");
                        System.out.println("*********************************");
                        System.out.println("*       DEFINIÇÕES DE CONTA     *");
                        System.out.println("*********************************");
                        System.out.println("");

                        System.out.println("Nome: " + nome_pessoaLogada);
                        System.out.println("Email: " + utilizador_logado);
                        System.out.println("Número de créditos: " + creditos);
                        System.out.println("");
                        System.out.println("1- Quer carregar os seus créditos?\n2- Quer cancelar sua conta?\n3- Sair");
                        String opcaoFeita = in.readLine();

                        terminarSwitch = false;

                        while (terminarSwitch == false) {
                            switch (opcaoFeita) {
                                case "1":
                                    System.out.println("Se quiser sair clique no 0");
                                    System.out.println("Quantos créditos quer carregar?");
                                    String nrCreditosInseridos = in.readLine();

                                    if (nrCreditosInseridos.equals("0")) {
                                        System.out.println(".... vai para o menu principal ....");
                                        menuPrincipal(utilizador_logado);
                                    }
                                    int nrCredInseridos = Integer.parseInt(nrCreditosInseridos);

                                    Integer meusCreditos = creditos + nrCredInseridos;

                                    if (nrCredInseridos >= 1 && nrCredInseridos <= 20) {
                                        sql.updateCreditosUser(utilizador_logado, meusCreditos);
                                        System.out.println("Os seus créditos foram adicionados com sucesso.\nCréditos atuais: " + meusCreditos);
                                        menuPrincipal(utilizador_logado);
                                    } else {
                                        System.out.println("Só pode adicionar no minimo 1 crédito e no máximo 20");
                                    }
                                    terminarSwitch = true;
                                    break;

                                case "2":
                                    System.out.println("Tem a certeza que pretende apagar a sua conta?\n1- Sim\n2- Não");
                                    String apagarConta = in.readLine();

                                    while (terminarSwitch == false) {
                                        switch (apagarConta) {
                                            case "1":
                                                sql.apagarPessoa(utilizador_logado); //apaga a conta
                                                System.out.println("Que pena. Vai fazer falta. Até um dia");
                                                System.exit(0); //Encerra o programa
                                                terminarSwitch = true;
                                                break;

                                            case "2":
                                                menuPrincipal(utilizador_logado);
                                                terminarSwitch = true;
                                                break;

                                            default:
                                                System.out.println("");
                                                System.err.println("Escolha uma opção válida: 1 - Sim   |    2 - Não");
                                                apagarConta = in.readLine();
                                                terminarSwitch = false;
                                                break;
                                        }
                                    }

                                case "3":
                                    menuPrincipal(utilizador_logado);
                                    terminarSwitch = true;
                                    break;

                                default:
                                    System.out.println("");
                                    System.err.println("Escolha uma opção válida: 1 - Carregar créditos   |    2 - Apagar conta   |    3 - Sair");
                                    opcaoFeita = in.readLine();
                                    terminarSwitch = false;
                                    break;
                            }
                        }
                        terminarSwitch = true;
                        break;

                    //TEMINAR SESSÃO
                    case "7":
                        System.out.println("A sua sessão foi terminada. Até à próxima");
                        System.exit(0); //Encerra o programa

                        terminarSwitch = true;
                        break;
                    default: //Se não escolher nenhuma opção do menu principal
                        System.err.println("Opção inválida selecione apenas um número válido: ");
                        menuPrincipal(utilizador_logado); //Volta para o menu principal
                        escolha = in.readLine();
                        terminarSwitch = false;
                        break;
                }
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
     * @throws java.sql.SQLException fornece informações sobre um erro de acesso
     * à base dados ou outros erros
     * @throws Exceptions.EmptyCollectionException if an empty collection
     * exception occurs
     */
    public void menuPessoa(Pessoa pessoaEscolhida, String utilizador) throws IOException, ParseException, SQLException, EmptyCollectionException {
        String nomePessoa = pessoaEscolhida.getUser_nome();
        this.utilizador_logado = utilizador;
        Pessoa pes_logada = sql.getPessoa(utilizador_logado);

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

                //se não forem amigos
                if (!grafoPessoas.verificaAmizade(pes_logada, pessoaEscolhida)) {
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
                                    Mensagem msg_comentar = escolherMsg(msg);

                                    id_Mensagem = sql.verIdMensagem(msg_comentar.getConteudo_msg());
                                    System.out.println("\n");
                                    System.out.println("***************COMENTÁRIOS****************");

                                    System.out.println("");
                                    System.out.print("Comente aqui: ");
                                    String conteudo_coment = in.readLine();

                                    Comentario comentario = new Comentario(conteudo_coment, data_pub, pes_logada, id_Mensagem);
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
                                    System.err.println("Escolha uma opção válida: 1 - Sim   |    2 - Não");
                                    pertendeComentar = in.readLine();
                                    terminarSwitch = false;

                            }
                        }

                    } else {
                        System.out.println("Não existem mensagens deste utilizadores");
                        menuPessoa(pessoaEscolhida, utilizador);
                    }
                } else {
                    System.out.println("Uma vez que é amigo do utilizador " + nomePessoa + " pode ver todas as suas mensagens");

                    System.out.println("");
                    System.out.println("***************************************");
                    System.out.println("*              MENSAGENS              *");
                    System.out.println("***************************************");
                    System.out.println("");
                    msg = sql.getAllMensagens(email); //vai buscar todas as mensagens

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
                                    Mensagem msg_comentar = escolherMsg(msg);

                                    id_Mensagem = sql.verIdMensagem(msg_comentar.getConteudo_msg());
                                    System.out.println("\n");
                                    System.out.println("***************COMENTÁRIOS****************");

                                    System.out.println("");
                                    System.out.print("Comente aqui: ");
                                    String conteudo_coment = in.readLine();

                                    Comentario comentario = new Comentario(conteudo_coment, data_pub, pes_logada, id_Mensagem);
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
                                    System.err.println("Escolha uma opção válida: 1 - Sim   |    2 - Não");
                                    pertendeComentar = in.readLine();
                                    terminarSwitch = false;

                            }
                        }

                    } else {
                        System.out.println("Não existem mensagens deste utilizadores");
                        menuPessoa(pessoaEscolhida, utilizador);
                    }
                }

                break;

            //FAZER PEDIDO DE AMIZADE
            case "3":
                Integer creditos = pes_logada.getNr_creditos();

                System.out.println("");
                System.out.println("Ao fazer o pedido de amizade e este for aceite vai poder ver todas as mensagens do utilizador " + nomePessoa);
                System.out.println("");
                System.out.println("Está prestes a fazer o pedido de amizade a " + nomePessoa + ".\nTem a certeza que pretende continuar?\n1-Sim\n2-Não");
                String fazerPedido = in.readLine();

                terminarSwitch = false;
                while (terminarSwitch == false) {
                    switch (fazerPedido) {
                        case "1":
                            fazerPedido(pessoaEscolhida, utilizador);
                            terminarSwitch = true;
                            break;

                        case "2":
                            System.out.println("Ainda assim pode continuar a ver as mensagens públicas de " + nomePessoa);
                            menuPessoa(pessoaEscolhida, utilizador);
                            terminarSwitch = true;
                            break;
                        default:
                            System.err.println("Opção inválida. Selecione apenas 1-Sim    |    2-Não");
                            fazerPedido = in.readLine();
                            terminarSwitch = false;
                            break;
                    }
                }
                break;

            //SAIR DO PERFIL
            case "4":
                menuPrincipal(this.utilizador_logado);
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
        System.out.println("*          1- Fazer pedido amizade         * ");
        System.out.println("*          2- Aceitar/Rejeitar pedidos     * ");
        System.out.println("*          3- Voltar atrás                 * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *  * ");
        System.out.print("O que pretende fazer? ");

        String escolhaMenuAmizade = in.readLine();

        return escolhaMenuAmizade;
    }

    //------------------------------------------------------ACERCA DO UTILIZADOR------------------------------------------------------
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
    private Pessoa escolherUser(ArrayOrderedList<Pessoa> pessoa) throws IOException, ParseException, SQLException, EmptyCollectionException {
        it = (ArrayIterator) pessoa.iterator();
        Integer counter = 0;
        String escolhaUser = in.readLine();
        Pessoa p = null;

        while (it.hasNext() && !counter.toString().equals(escolhaUser)) {
            counter++;
            p = (Pessoa) it.next();
        }

        if (escolhaUser.equals("0")) {
            System.out.println(".... vai para o menu principal ....");
            menuPrincipal(utilizador_logado);
        }

        if (escolhaUser.equals(counter.toString())) {
            System.out.println("Escolheu o utilizador " + p.getUser_nome());
            return p;
        }
        if (!escolhaUser.equals(counter.toString())) {
            System.err.println("Escolha novamente, esse utilizador não existe.");
            menuPrincipal(utilizador_logado);
        }

        return null;

    }

    /**
     * Método que vai imprimir todos os utilizadores, para isso percorremos a
     * nossa UnorderedList de pessoas através do iterador
     *
     * @param pessoa coleção de pessoas que vamos percorrer e consequentemente
     * imprimir
     */
    private void printAllUsers(ArrayOrderedList<Pessoa> pessoa) {

        Integer counter = 0;
        it = (ArrayIterator) pessoa.iterator();

        System.out.println();
        System.out.println("Se pretender sair escreva 0");
        System.out.println("Escolha um utilizador através do seu índice: ");

        while (it.hasNext()) {
            counter++;
            Pessoa p = (Pessoa) it.next();
            System.out.println(counter + " -> " + p.getUser_email());
            System.out.println("");
            System.out.println("Qual o utilizador que pretende escolher? ");
        }

    }

    //------------------------------------------------------ACERCA DAS MENSAGENS------------------------------------------------------
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
            } else {
                System.err.println("Essa opção não é válida , selecione apenas um índice de uma mensagem");
                escolhaMensagem = in.readLine();
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

            terminarSwitch = false;

            while (terminarSwitch == false) {
                if (escolhaMsgEliminada.equals(counter.toString())) {
                    System.out.println("Escolheu a mensagem " + "'" + mensa.getConteudo_msg() + "'" + " para ser eliminada");
                    terminarSwitch = true;
                    return mensa;
                } else {
                    System.err.print("Esse índice não corresponde a nenhuma mensagem. Escreva novamente:");
                    escolhaMsgEliminada = in.readLine();
                    terminarSwitch = false;
                }
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
     * @throws java.text.ParseException
     * @throws java.sql.SQLException fornece informações sobre um erro de acesso
     * à base dados ou outros erros
     * @throws Exceptions.EmptyCollectionException if an empty collection
     * exception occurs
     */
    public String escreverMensagem() throws IOException, ParseException, SQLException, EmptyCollectionException {

        System.out.println("");
        System.out.print("Escreva aqui: ");
        String lerMensagem = in.readLine();

        if (lerMensagem.equals("0")) {
            System.out.println(".... vai para o menu principal ....");
            menuPrincipal(utilizador_logado);
        }
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
            System.out.println("Tipo Mensagem: " + sql.verTipoMensagemByIdTipo(mens.getTipoMensagem()));
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

    //----------------------------------------------------ACERCA DOS COMENTÁRIOS-----------------------------------------------------
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

    //---------------------------------------------ACERCA DOS PEDIDOS DE AMIZADE-----------------------------------------------------
    /**
     * Método responsável por escolher uma amizade para responder
     * (aceitar/recusar/ignorar)
     *
     * @param pedido coleção de pedidos que vai ser percorrida para ver qual o
     * utilizador vai escolher
     * @return o pedido de amizade escolhido
     * @throws IOException
     */
    private PedidoAmizade escolherPedidoAmizade(ArrayUnorderedList<PedidoAmizade> pedido) throws IOException {
        String indicePedido = in.readLine();

        it = (ArrayIterator) pedido.iterator();
        Integer counter = 0;

        while (it.hasNext()) {
            counter++;
            PedidoAmizade pedAmizade = (PedidoAmizade) it.next();
            if (indicePedido.equals(counter.toString())) {
                System.out.println("Escolheu o pedido nº  " + "'" + counter + "'" + " para ser respondido");
                return pedAmizade;
            } else {
                System.err.println("Essa opção não é válida , selecione apenas um índice de um pedido");
                indicePedido = in.readLine();
            }
        }
        return null;

    }

    /**
     * Método responsável por fazer um pedido de amizade de uma pessoa para
     * outra fazendo as devidas verificações: se já existe o pedido, se já
     * existe amizade, se vai ser uma amizade normal ou patrocionado
     *
     * @param pessoaEscolhida - pessoa escolhida para fazer o pedido
     * @param utilizador - utilizador logado
     * @throws SQLException fornece informações sobre um erro de acesso à base
     * dados ou outros erros
     * @throws IOException
     * @throws ParseException
     * @throws EmptyCollectionException if an empty collection exception occurs
     */
    public void fazerPedido(Pessoa pessoaEscolhida, String utilizador) throws SQLException, IOException, ParseException, EmptyCollectionException {
        this.utilizador_logado = utilizador;
        Pessoa pes_logada = sql.getPessoa(utilizador_logado);

        Integer creditos = pes_logada.getNr_creditos();
        String pessoa_origem = utilizador;
        String pessoa_destino = pessoaEscolhida.getUser_email();

        if (sql.ifExisteJaPedido(pessoa_origem, pessoa_destino) == true) {
            System.out.println("Já existe um pedido pendente entre você e a pessoa em questão. Verifique a gestão de pedidos.");
            menuPessoa(pessoaEscolhida, utilizador_logado);
        } else {
            Edge tmpE = this.grafoPessoas.testEdge(pes_logada, pessoaEscolhida);
            if (tmpE != null) {
                System.out.println("Já tem amizade com este utilizador");
                menuPessoa(pessoaEscolhida, utilizador_logado);
            } else {
                Boolean existe = this.grafoPessoas.verificarTipoAmizadePossivel(pes_logada, pessoaEscolhida);
                Boolean existe2 = this.grafoPessoas.verificarTipoAmizadePossivel(pessoaEscolhida,pes_logada);
                if (existe==true || existe2 ==true) {
                    System.out.println("Têm um amigo em comum --> assim sendo será um pedido normal");
                    sql.fazerPedidoAmizade(pes_logada, pessoaEscolhida);
                    System.out.println("O seu pedido foi efetuado com sucesso. Aguarde pela resposta");
                    menuPrincipal(utilizador_logado);
                } else {
                    double nrCreditos = this.grafoPessoas.shortestPathWeight(pes_logada, pessoaEscolhida);
                    if (nrCreditos == 2.147483647E9) {
                        System.out.println("\n");
                        System.out.println("Uma vez que é um utilizador novo e ainda não têm amizades/ligações com ninguém apenas lhe vai ser cobrado 1 crédito pela amizade.");
                        nrCreditos = 1.00;
                    } else {
                        System.out.println("");
                        System.out.println("Não têm um amigo em comum --> assim sendo será um pedido patrocionado.\nIrá pagar " + nrCreditos);
                    }
                    System.out.println("Tem a certeza que pretende continuar?\n1- Sim\n2- Não");
                    String continuarPedidoPago = in.readLine();

                    while (terminarSwitch == false) {
                        switch (continuarPedidoPago) {
                            case "1":
                                int nrCredRetirados = (int) nrCreditos;

                                // Caminho mais curto
                                if (nrCredRetirados > creditos) {
                                    System.out.println("Infelizmente não tem saldo suficiente. Tente recarregar primeiro");
                                    menuPessoa(pessoaEscolhida, utilizador_logado);
                                } else {
                                    sql.fazerPedidoAmizade(pes_logada, pessoaEscolhida);
                                    Integer meusCreditos = creditos - nrCredRetirados;
                                    sql.updateCreditosUser(utilizador_logado, meusCreditos);
                                    System.out.println("O pedido de amizade foi feito com sucesso, espere que " + pessoaEscolhida.getUser_nome() + " responda ao seu pedido");
                                    menuPessoa(pessoaEscolhida, utilizador_logado);
                                }

                                terminarSwitch = true;
                                break;

                            case "2":
                                menuPessoa(pessoaEscolhida, utilizador_logado);
                                terminarSwitch = true;
                                break;
                            default:
                                System.err.println("Opção inválida. Apenas pode escolher 1- Sim     |     2- Não");
                                continuarPedidoPago = in.readLine();
                                terminarSwitch = false;
                                break;
                        }
                    }
                }
            }
        }
    }

}
