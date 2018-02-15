package Database;

import ArrayList.ArrayOrderedList;
import Classes.Mensagem;
import Classes.Pessoa;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.sqlite.SQLiteConfig;
import ArrayList.ArrayUnorderedList;
import Classes.Amizade;
import Classes.Comentario;
import Classes.PedidoAmizade;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public final class SqlConnection {

    private final String DRIVER = "org.sqlite.JDBC";
    private final String TABELA_PESSOA = "Pessoa";
    private final String TABELA_COMENTARIO = "Comentario";
    private final String TABELA_MENSAGEM = "Mensagem";
    private final String TABELA_TIPO_MENSAGEM = "TipoMensagem";
    private final String TABELA_AMIZADE = "Amizade";
    private final String TABELA_PEDIDO_AMIZADE = "PedidoAmizade";
    private final String TABELA_ESTADO_PEDIDO_AMIZADE = "EstadoPedidoAmizade";
    private final String SQL_LITE = "jdbc:sqlite:";
    private String DB_NAME = "basedadosed.db";
    private Connection connection = null;
    private boolean tabl = false;

    /**
     * Método construtor que permite a criação de uma nova instância de {link
     * SqlConnection}
     */
    public SqlConnection() {
        try {
            Class.forName(DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(SQL_LITE + DB_NAME, config.toProperties());
            DatabaseMetaData dmd = this.connection.getMetaData();
            ResultSet tables = dmd.getTables(null, null, "Pessoa", null);
            if (!tables.next()) {
                criarTabelaPessoa();
                criarTabelaMensagem();
                criarTabelaComentario();
                criarTabelaTipoMensagem();
                criarTabelaAmizade();
                criarTabelaPedidoAmizade();
                criarTabelaEstadoPedido();
                inserirTipoMensagem();
                inserirEstadoPedido();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //-------------------------------------------------------------CRIAR TABELAS-----------------------------------------------------------
    /**
     * Criação da tabela que vai permitir armazenar pessoas
     */
    public void criarTabelaPessoa() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            String sqlTable = "CREATE TABLE " + TABELA_PESSOA
                    + " (USER_EMAIL VARCHAR(50) PRIMARY KEY NOT NULL,"
                    + "USER_NOME VARCHAR(50) NOT NULL,"
                    + "NR_CREDITOS INTEGER NOT NULL, "
                    + "PASSWORD VARCHAR(50) NOT NULL)";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela Pessoa já existe!");
        }
    }

    /**
     * Criação da tabela que vai permitir armazenar mensagens
     */
    public void criarTabelaMensagem() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            stm.execute("PRAGMA foreign_keys = ON");
            String sqlTable = "CREATE TABLE " + TABELA_MENSAGEM
                    + " (ID_MENSAGEM INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "CONTEUDO_MSG VARCHAR(200) NOT NULL,"
                    + "DATA_PUBLICACAO TEXT NOT NULL,"
                    + "USER_EMAIL VARCHAR(50) NOT NULL,"
                    + "ID_TIPO_MENSAGEM INTEGER NOT NULL,"
                    + "FOREIGN KEY(ID_TIPO_MENSAGEM) REFERENCES TipoMensagem(ID_TIPO_MENSAGEM) ON DELETE CASCADE, "
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Pessoa(USER_EMAIL) ON DELETE CASCADE )";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Mensagem' já existe!");
        }
    }

    /**
     * Criação da tabela que vai permitir armazenar comentários
     */
    public void criarTabelaComentario() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            stm.execute("PRAGMA foreign_keys = ON");
            String sqlTable = "CREATE TABLE " + TABELA_COMENTARIO
                    + " (ID_COMENTARIO INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "CONTEUDO_COMENT VARCHAR(150) NOT NULL,"
                    + "DATA_COMENT TEXT NOT NULL,"
                    + "USER_EMAIL VARCHAR(50) NOT NULL,"
                    + "ID_MENSAGEM INTEGER NOT NULL,"
                    + "FOREIGN KEY(ID_MENSAGEM) REFERENCES Mensagem(ID_MENSAGEM) ON DELETE CASCADE ,"
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Pessoa(USER_EMAIL) ON DELETE CASCADE)";
            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Comentario' já existe!");
        }
    }

    /**
     * Criação da tabela que vai permitir armazenar os tipos de mensagens
     */
    public void criarTabelaTipoMensagem() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            stm.execute("PRAGMA foreign_keys = ON");
            String sqlTable = "CREATE TABLE " + TABELA_TIPO_MENSAGEM
                    + " (ID_TIPO_MENSAGEM INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "TIPO_MENSAGEM VARCHAR(50) NOT NULL)";
            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Tipo Mensagem' já existe!");
        }
    }

    /**
     * Criação da tabela que vai permitir armazenar as amizades
     */
    public void criarTabelaAmizade() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            stm.execute("PRAGMA foreign_keys = ON");
            String sqlTable = "CREATE TABLE " + TABELA_AMIZADE
                    + " (ID_AMIZADE INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "USER_EMAIL1 VARCHAR(50) NOT NULL,"
                    + "USER_EMAIL2 VARCHAR(50) NOT NULL,"
                    + "FOREIGN KEY(USER_EMAIL1) REFERENCES Pessoa(USER_EMAIL) ON DELETE CASCADE ,"
                    + "FOREIGN KEY(USER_EMAIL2) REFERENCES Pessoa(USER_EMAIL) ON DELETE CASCADE )";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Amizade' já existe!");
        }
    }

    /**
     * Criação da tabela que vai permitir armazenar os pedidos de amizade
     */
    public void criarTabelaPedidoAmizade() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            String sqlTable = "CREATE TABLE " + TABELA_PEDIDO_AMIZADE
                    + " (ID_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "USER_ORIGEM VARCHAR(50) NOT NULL,"
                    + "USER_DESTINO VARCHAR(50) NOT NULL,"
                    + "ID_ESTADO INTEGER NOT NULL,"
                    + "FOREIGN KEY(USER_ORIGEM) REFERENCES Pessoa(USER_EMAIL) ON DELETE CASCADE ,"
                    + "FOREIGN KEY(USER_DESTINO) REFERENCES Pessoa(USER_EMAIL) ON DELETE CASCADE ,"
                    + "FOREIGN KEY(ID_ESTADO) REFERENCES EstadoPedidoAmizade(ID_ESTADO) ON DELETE CASCADE )";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Pedido Amizade' já existe!");
        }
    }

    /**
     * Criação da tabela que vai permitir armazenar os estados dos pedidos de
     * amizade
     */
    public void criarTabelaEstadoPedido() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            String sqlTable = "CREATE TABLE " + TABELA_ESTADO_PEDIDO_AMIZADE
                    + " (ID_ESTADO INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "  ESTADO_DESC VARCHAR(50) NOT NULL)";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Estado Pedido Amizade' já existe!");
        }
    }

    //-------------------------------------------------INSERIR DADOS NAS TABELAS-----------------------------------------------------------
    /**
     * Inserir utilizadores na tabela Pessoas
     *
     * @param user - pessoa do tipo Pessoa que vai ser adicionada na base dados
     */
    public void inserirPessoa(Pessoa user) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String insert = "INSERT INTO " + TABELA_PESSOA
                    + "(USER_EMAIL, USER_NOME, NR_CREDITOS,PASSWORD) " + "VALUES ( '" + user.getUser_email() + "'" + ",'"
                    + user.getUser_nome() + "'" + ",'"
                    + user.getNr_creditos() + "'" + ",'"
                    + user.getPassword() + "');";

            statement.executeUpdate(insert);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Inserir mensagens na tabela Mensagens
     *
     * @param msg - mensagem do tipo Mensagem que vai ser adicionada na base
     * dados
     */
    public void inserirMensagem(Mensagem msg) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
            String insert = "INSERT INTO " + TABELA_MENSAGEM
                    + "(CONTEUDO_MSG, DATA_PUBLICACAO, USER_EMAIL, ID_TIPO_MENSAGEM) " + "VALUES ( '" + msg.getConteudo_msg() + "'" + ",'"
                    + ft.format(msg.getData_publicacao()) + "'" + ",'"
                    + msg.getEmail_user().getUser_email() + "'" + ",'"
                    + msg.getTipoMensagem() + "');";

            statement.executeUpdate(insert);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Inserir tipos de mensagens na tabela Mensagens
     */
    public void inserirTipoMensagem() {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String insert = "INSERT INTO " + TABELA_TIPO_MENSAGEM
                    + "(TIPO_MENSAGEM) " + "VALUES ('Pública')";
            String insert1 = "INSERT INTO " + TABELA_TIPO_MENSAGEM
                    + "(TIPO_MENSAGEM) " + "VALUES ('Privada')";
            statement.executeUpdate(insert);
            statement.executeUpdate(insert1);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Inserir os estados dos pedidos de amizade na tabela PedidoAmizade
     */
    public void inserirEstadoPedido() {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String insert = "INSERT INTO " + TABELA_ESTADO_PEDIDO_AMIZADE
                    + "(ESTADO_DESC) " + "VALUES ('Pendente')";
            String insert1 = "INSERT INTO " + TABELA_ESTADO_PEDIDO_AMIZADE
                    + "(ESTADO_DESC) " + "VALUES ('Aceite')";
            String insert2 = "INSERT INTO " + TABELA_ESTADO_PEDIDO_AMIZADE
                    + "(ESTADO_DESC) " + "VALUES ('Recusado')";
            statement.executeUpdate(insert);
            statement.executeUpdate(insert1);
            statement.executeUpdate(insert2);
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Método responsável por inserir comentários na base dados
     *
     * @param coment comentário a ser inserido
     */
    public void inserirComent(Comentario coment) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
            String insert = "INSERT INTO " + TABELA_COMENTARIO
                    + "(CONTEUDO_COMENT, DATA_COMENT, USER_EMAIL, ID_MENSAGEM) " + "VALUES ( '" + coment.getComentario() + "'" + ",'"
                    + ft.format(coment.getData_comentario()) + "'" + ",'"
                    + coment.getEmail_user().getUser_email() + "'" + ",'"
                    + coment.getId_mensagem() + "');";

            statement.executeUpdate(insert);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    //----------------------------------------------------------------UTILIZADOR-------------------------------------------------------
    /**
     * Método responsável por eliminar uma pessoa da base dados
     *
     * @param email_user email da pessoa que vai ser eliminada
     */
    public void apagarPessoa(String email_user) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String delete = "DELETE FROM Pessoa WHERE USER_EMAIL  = '" + email_user + "'";
            statement.executeUpdate(delete);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Método responsável por ir buscar uma pessoa específica
     *
     * @param email_user pessoa a ser pesquisada na base dados
     * @return valor - pessoa encontrada na base dados com aquele email
     * especifico
     */
    public Pessoa getPessoa(String email_user) {
        Statement statement = null;
        Pessoa valor = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM Pessoa WHERE USER_EMAIL  = '" + email_user + "'";

            ResultSet r = statement.executeQuery(SQL);

            Pessoa tmp = new Pessoa(email_user, r.getString("USER_NOME"), r.getString("PASSWORD"), r.getInt("NR_CREDITOS"));
            valor = tmp;
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;
    }

    /**
     * Método responsável por ir buscar uma pessoa específica
     *
     * @param nome - pessoa a ser pesquisada na base dados
     * @return valor - pessoa encontrada na base dados com aquele email
     * especifico
     */
    public Pessoa getPessoaByName(String nome) {
        Statement statement = null;
        Pessoa valor = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM Pessoa WHERE USER_NOME  = '" + nome + "'";

            ResultSet r = statement.executeQuery(SQL);
            Pessoa tmp = new Pessoa(r.getString("USER_EMAIL"), nome, r.getString("PASSWORD"), r.getInt("NR_CREDITOS"));
            valor = tmp;
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;
    }

    /**
     * Método responsável por ir buscar à base dados todas as pessoas que
     * existem com registo excepto o utilizador que é enviado na string, ou
     * seja, excepto o utilizador que se encontra logado
     *
     * @param myEmail email da pessoa logada
     * @return numa Unordered List todas as pessoas presentes na base dados
     * excepto o utilizador que é enviado na string
     */
    public ArrayUnorderedList<Pessoa> getAllPessoas(String myEmail) {
        Statement statement = null;
        ArrayUnorderedList<Pessoa> valor = new ArrayUnorderedList<>();
        try {
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String SQL = "SELECT * FROM Pessoa WHERE USER_EMAIL  <> '" + myEmail + "'";

            ResultSet r = statement.executeQuery(SQL);

            //Boolean hasPessoa = false;
            while (r.next()) {
                //   hasPessoa = true;
                Pessoa tmpPessoa = new Pessoa(r.getString("USER_EMAIL"), r.getString("USER_NOME"), null, r.getInt("NR_CREDITOS"));
                valor.addToRear(tmpPessoa);

            }

            /*if (!hasPessoa) {
                System.out.println("Não existem pessoas");
                valor = null;
            }*/
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;
    }

    /**
     * Método responsável por atualizar os créditos do utilizador
     *
     * @param email email do utilizador ao qual os créditos vão ser alterados
     * @param nrCreditos número de créditos para o qual o valor de créditos vai
     * ser alterado
     */
    public void updateCreditosUser(String email, Integer nrCreditos) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String update = "UPDATE Pessoa SET NR_CREDITOS = '" + nrCreditos + "'" + "WHERE USER_EMAIL = '" + email + "'";

            statement.executeUpdate(update);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    //-----------------------------------------------------------------MENSAGENS--------------------------------------------------------
    /**
     * Método responsável por apagar uma mensagem da base dados
     *
     * @param id_mensagem - id da mensagem que vai ser eliminada
     */
    public void apagarMensagem(Integer id_mensagem) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String delete = "DELETE FROM Mensagem WHERE ID_MENSAGEM  = '" + id_mensagem + "'";

            statement.executeUpdate(delete);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Método responsável por ir buscar à base dados todas as mensagens que
     * existem daquele utilizador
     *
     * @param email_user email do utilizador que queremos ver as mensagens
     * @return numa Unordered List todas as mensagens presentes na base dados
     * daquele utilizador
     * @throws ParseException - é lançada quando encontra erros de análise,
     * nesta caso na transição da data de String para Date
     */
    public ArrayOrderedList<Mensagem> getAllMensagens(String email_user) throws ParseException {
        Statement statement = null;
        ArrayOrderedList<Mensagem> valor = new ArrayOrderedList<>();
        try {
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String SQL = "SELECT * FROM Mensagem WHERE USER_EMAIL = '" + email_user + "'";

            ResultSet r = statement.executeQuery(SQL);

            Boolean hasMensagem = false;

            while (r.next()) {
                String data = r.getString("DATA_PUBLICACAO");
                Date data_pub = new SimpleDateFormat("dd/MM/yyyy").parse(data);

                hasMensagem = true;
                Mensagem tmpMensagem = new Mensagem(r.getString("CONTEUDO_MSG"), data_pub, r.getInt("ID_TIPO_MENSAGEM"), null);

                valor.add(tmpMensagem);

            }

            /*if (!hasMensagem) {
                System.out.println("Não existem mensagens");
                valor = null;
            }*/
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;
    }

    /**
     * Método responsável por ir buscar à base dados todas as mensagens do tipo
     * públicas que existem daquele utilizador
     *
     * @param email_user email do utilizador que queremos ver as mensagens
     * @return numa Ordered List todas as mensagens presentes na base dados
     * daquele utilizador
     * @throws ParseException - é lançada quando encontra erros de análise,
     * nesta caso na transição da data de String para Date
     */
    public ArrayOrderedList<Mensagem> getMensagensPublicas(String email_user) throws ParseException {
        Statement statement = null;
        ArrayOrderedList<Mensagem> valor = new ArrayOrderedList<>();
        try {
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String SQL = "SELECT * FROM Mensagem WHERE USER_EMAIL = '" + email_user + "'" + "AND ID_TIPO_MENSAGEM = 1";

            ResultSet r = statement.executeQuery(SQL);

            Boolean hasMensagem = false;

            while (r.next()) {
                String data = r.getString("DATA_PUBLICACAO");
                Date data_pub = new SimpleDateFormat("dd/MM/yyyy").parse(data);

                Pessoa tempPessoa = getPessoa(r.getString("USER_EMAIL"));
                hasMensagem = true;
                Mensagem tmpMensagem = new Mensagem(r.getString("CONTEUDO_MSG"), data_pub, r.getInt("ID_TIPO_MENSAGEM"), tempPessoa);
                valor.add(tmpMensagem);

            }

            /*if (!hasMensagem) {
                System.out.println("Não existem mensagens");
                valor = null;
            }*/
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;
    }

    /**
     * Método responsável por fazer uma pesquisa à base dados para encontrar o
     * id do tipo de mensagem que corresponde ao tipo de mensagem inserida pelo
     * utilizador
     *
     * @param tipoMensagem - tipo de mensagem a ser encontrada
     * @return valor - o id do tipo de mensagem
     */
    public Integer verTipoMensagem(String tipoMensagem) {
        Statement statement = null;
        Integer valor = 0;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String SQL = "SELECT ID_TIPO_MENSAGEM FROM TipoMensagem WHERE TIPO_MENSAGEM  = '" + tipoMensagem + "'";
            ResultSet r = statement.executeQuery(SQL);
            valor = Integer.parseInt(r.getString("ID_TIPO_MENSAGEM"));
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;

    }

    /**
     * Método responsável por fazer uma pesquisa à base dados para encontrar o
     * id da mensagem que corresponde ao conteudo da mensagem inserida pelo
     * utilizador
     *
     * @param conteudoMsg - conteudo da mensagem a ser encontrada
     * @return valor - o id da mensagem
     */
    public Integer verIdMensagem(String conteudoMsg) {
        Statement statement = null;
        Integer valor = 0;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String SQL = "SELECT ID_MENSAGEM FROM Mensagem WHERE CONTEUDO_MSG  = '" + conteudoMsg + "'";
            ResultSet r = statement.executeQuery(SQL);
            valor = Integer.parseInt(r.getString("ID_MENSAGEM"));
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;

    }

    //---------------------------------------------------------------COMENTÁRIOS-------------------------------------------------------
    /**
     * Este método é responsável por ir buscar os comentários correspondentes a
     * uma mensagem
     *
     * @param id_mensagem id da mensagem ao qualpretende ir buscar os
     * comentários
     * @return Ordered List de todos os comentários presentes na base dados
     * associados aquela mensagem
     * @throws ParseException
     */
    public ArrayOrderedList<Comentario> getComentarioById(Integer id_mensagem) throws ParseException {
        Statement statement = null;
        ArrayOrderedList<Comentario> valor = new ArrayOrderedList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM Comentario WHERE ID_MENSAGEM  = '" + id_mensagem + "'";

            ResultSet r = statement.executeQuery(SQL);
            Boolean hasComentario = false;

            while (r.next()) {
                String data = r.getString("DATA_COMENT");
                Date data_coment = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                hasComentario = true;
                Comentario tmp = new Comentario(r.getString("CONTEUDO_COMENT"), data_coment, null, id_mensagem);

                valor.add(tmp);

            }
            connection.commit();
            statement.close();
        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;
    }

    /**
     * Método responsável para ver se existem comentários associados a uma
     * mensagem
     *
     * @param id_mensagem id da mensagem que se pretente ver se há comentários
     * @return true se houver comentários para aquela mensagem ou false caso não
     * haja
     * @throws SQLException
     */
    public boolean ifExisteComentariosMensagem(Integer id_mensagem) throws SQLException {
        connection.setAutoCommit(false);
        Comentario coment = null;
        boolean result = false;

        Statement stmt = null;
        String SQL = "SELECT * FROM Comentario WHERE ID_MENSAGEM  = '" + id_mensagem + "'";
        connection.commit();

        /*Executa o sql*/
        stmt = connection.createStatement();
        ResultSet resultado = stmt.executeQuery(SQL);

        if (resultado.next()) {
            result = true;
        }
        stmt.close();
        return result;
    }

//----------------------------------------------------------PEDIDOS DE AMIZADE & AMIZADE-----------------------------------------------------------
    /**
     * Método responsável por ir buscar à base dados todas as amizade que
     * existem
     *
     * @return numa Unordered List todas as amizades presentes na base dados
     */
    public ArrayUnorderedList<Amizade> getAllAmizades() {
        Statement statement = null;
        ArrayUnorderedList<Amizade> valor = new ArrayUnorderedList<>();
        try {
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String SQL = "SELECT * FROM Amizade";

            ResultSet r = statement.executeQuery(SQL);

            while (r.next()) {
                Pessoa a = getPessoa(r.getString("USER_EMAIL1"));
                Pessoa b = getPessoa(r.getString("USER_EMAIL2"));
                Amizade amizade = new Amizade(a, b);
                valor.addToRear(amizade);

            }
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;

    }

    /**
     * Método responsável por fazer o pedido de amizade, ou seja, por inserir na
     * base dados na tabela Pedido de amizade um pedido de amizade realizado,
     * com estado =1 pois o pedido aquando de ser feito fica pendente até ser
     * aceite/rejeitado
     *
     * @param emailOrigem pessoa que enviou o pedido de amizade
     * @param emailDestino pessoa que recebeu o pedido de amizade
     */
    public void fazerPedidoAmizade(Pessoa emailOrigem, Pessoa emailDestino) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String insert = "INSERT INTO " + TABELA_PEDIDO_AMIZADE
                    + "(USER_ORIGEM, USER_DESTINO, ID_ESTADO) " + "VALUES ( '" + emailOrigem.getUser_email() + "'" + ",'"
                    + emailDestino.getUser_email() + "'" + ",'"
                    + "1" + "');";

            statement.executeUpdate(insert);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Método responsável por verificar na base dados se existe amziades
     * pendentes para um determinado utilizador
     *
     * @param emailLogado email do utilizador ao qual vamos ver se há ou não
     * pedidos pendentes
     * @return true caso existam pedidos pendentes, false caso não existam
     * @throws SQLException
     */
    public boolean ifExisteAmizadesPendentes(String emailLogado) throws SQLException {
        connection.setAutoCommit(false);
        Pessoa user = null;
        boolean result = false;

        String SQL = "SELECT * FROM PedidoAmizade WHERE USER_DESTINO  = '" + emailLogado + "'" + "AND ID_ESTADO = 1";
        connection.commit();
        Statement stmt = null;

        stmt = connection.createStatement();
        ResultSet resultado = stmt.executeQuery(SQL);

        if (resultado.next()) {
            result = true;
        }
        stmt.close();
        return result;
    }

    /**
     * Método responsável por verificar se já existe ou não o pedido de amizade
     *
     * @param emailOrigem email da pessoa que fez o pedido de amizade
     * @param emailDestino email da pessoa que recebeu o pedido de amizade
     * @return true se já existe o pedido, false caso não exista o pedido
     * @throws SQLException
     */
    public boolean ifExisteJaPedido(String emailOrigem, String emailDestino) throws SQLException {
        connection.setAutoCommit(false);
        Pessoa user = null;
        boolean result = false;

        String SQL = "SELECT * FROM PedidoAmizade WHERE USER_ORIGEM  = '" + emailOrigem + "'" + "AND USER_DESTINO  = '" + emailDestino + "'" + "AND ID_ESTADO = 1";
        connection.commit();
        Statement stmt = null;

        /*Executa o sql*/
        stmt = connection.createStatement();
        ResultSet resultado = stmt.executeQuery(SQL);

        if (resultado.next()) {
            result = true;
        }
        stmt.close();
        return result;
    }

    /**
     * Método responsável por aceitar o pedido de amizade e inserir a amizade na
     * base dados, ou seja, responsável por atualizar o estado para 2 na base
     * dados na tabela Pedido de amizade e inserir a amizade na tabela amizade
     *
     * @param emailOrigem email da pessoa que fez o pedido de amizade
     * @param emailDestino email da pessoa que recebeu o pedido de amizade
     * @throws SQLException
     */
    public void aceitarPedido(String emailOrigem, String emailDestino) throws SQLException {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String update = "UPDATE PedidoAmizade SET ID_ESTADO = 2 WHERE USER_ORIGEM  = '" + emailOrigem + "'" + "AND USER_DESTINO  = '" + emailDestino + "'";
            String insert = "INSERT INTO Amizade (USER_EMAIL1, USER_EMAIL2) VALUES ('" + emailOrigem + "'" + ",'" + emailDestino + "')";

            statement.executeUpdate(update);
            statement.executeUpdate(insert);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }

    }

    /**
     * Método responsável por rejeitar o pedido de amizade, ou seja, de
     * atualizar o estado para 3 na base dados na tabela Pedido de amizade
     *
     * @param emailOrigem email da pessoa que fez o pedido de amizade
     * @param emailDestino email da pessoa que recebeu o pedido de amizade
     * @throws SQLException
     */
    public void rejeitarPedido(String emailOrigem, String emailDestino) throws SQLException {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String update = "UPDATE PedidoAmizade SET ID_ESTADO = 3 WHERE USER_ORIGEM  = '" + emailOrigem + "'" + "AND USER_DESTINO  = '" + emailDestino + "'";

            statement.executeUpdate(update);

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }

    }

    /**
     * Método responsável por ir buscar à base dados todos os pedidos pendentes
     * de um determinado utilizador
     *
     * @param emailLogado utilizador ao qual vamos ver os pedidos pendentes
     * @return numa Unordered List todas os pedidos pendentes na base dados de
     * um determinado utilizador
     * @throws SQLException
     */
    public ArrayUnorderedList<PedidoAmizade> getPedidosPendentes(String emailLogado) throws SQLException {

        Statement statement = null;
        ArrayUnorderedList<PedidoAmizade> valor = new ArrayUnorderedList<>();
        try {
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String SQL = "SELECT * FROM PedidoAmizade WHERE USER_DESTINO  = '" + emailLogado + "'" + "AND ID_ESTADO = 1";

            ResultSet r = statement.executeQuery(SQL);

            while (r.next()) {
                Pessoa a = getPessoa(r.getString("USER_ORIGEM"));
                Pessoa b = getPessoa(r.getString("USER_DESTINO"));
                PedidoAmizade tmpPedido = new PedidoAmizade(a, b, r.getInt("ID_ESTADO"));
                valor.addToRear(tmpPedido);

            }

            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;

    }

    //--------------------------------------------------------------ACERCA LOGIN-----------------------------------------------------
    /**
     * Método responsável pela verificação do utilizador na base dados perante o
     * email inserido
     *
     * @param email - email a ser pesquisado na base dados
     * @return true se existir esse email na base dados ou false caso esse email
     * não exista
     * @throws SQLException exceção lançado aquando de erros de acesso à base
     * dados
     */
    public boolean ifExiste(String email) throws SQLException {
        connection.setAutoCommit(false);
        Pessoa user = null;
        boolean result = false;
        Statement stmt = null;

        String SQL = "SELECT * FROM Pessoa WHERE User_email  = '" + email + "'";
        connection.commit();

        /*Executa o sql*/
        stmt = connection.createStatement();
        ResultSet resultado = stmt.executeQuery(SQL);

        if (resultado.next()) {
            result = true;
        }
        stmt.close();
        return result;
    }

    /**
     * Método que verifica na base dados se existe o email e a password
     * especificada pelo utilizador
     *
     * @param email - que vai ser pesquisado na base dados
     * @param password - que vai ser pesquisado na base dados
     * @return true caso exista ou falso caso não exista
     * @throws SQLException exceção lançado aquando de erros de acesso à base
     * dados
     */
    public boolean ifExisteLogin(String email, String password) throws SQLException {
        connection.setAutoCommit(false);
        Pessoa user = null;
        boolean result = false;
        Statement stmt = null;

        String SQL = "SELECT * FROM Pessoa WHERE User_email  = '" + email + "'" + "AND Password = '" + password + "'";
        connection.commit();
        stmt = connection.createStatement();
        ResultSet resultado = stmt.executeQuery(SQL);

        if (resultado.next()) {
            result = true;
        }
        stmt.close();
        return result;
    }

    /**
     * Fecha a connexão à base de dados
     */
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

}
