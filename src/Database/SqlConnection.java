package Database;

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
import arrayList.ArrayUnorderedList;

/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class SqlConnection {

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
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

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
                    + "NR_CREDITOS INTEGER NOT NULL"
                    + "PASSWORD VARCHAR(50) NOT NULL)";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Pessoa' já existe!");
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
                    + "FOREIGN KEY(ID_TIPO_MENSAGEM) REFERENCES TipoMensagem(ID_TIPO_MENSAGEM),"
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Pessoa(USER_EMAIL))";

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
                    + "FOREIGN KEY(ID_MENSAGEM) REFERENCES Mensagem(ID_MENSAGEM),"
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Pessoa(USER_EMAIL))";

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
            System.out.println(ex + " : A tabela 'Tipo Mensagem' já existe!");
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
                    + "FOREIGN KEY(USER_EMAIL1) REFERENCES Pessoa(USER_EMAIL),"
                    + "FOREIGN KEY(USER_EMAIL2) REFERENCES Pessoa(USER_EMAIL))";

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
                    + "FOREIGN KEY(USER_ORIGEM) REFERENCES Pessoa(USER_EMAIL),"
                    + "FOREIGN KEY(USER_DESTINO) REFERENCES Pessoa(USER_EMAIL),"
                    + "FOREIGN KEY(ID_ESTADO) REFERENCES EstadoPedidoAmizade(ID_ESTADO))";

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
                    + "(USER_EMAIL, USER_NOME, PASSWORD) " + "VALUES ( '" + user.getUser_email() + "'" + ",'"
                    + user.getUser_nome() + "'" + ",'"
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
            SimpleDateFormat ft
                    = new SimpleDateFormat("dd/MM/yyyy");
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

        String SQL = "SELECT * FROM Pessoa WHERE User_email  = '" + email + "'";
        connection.commit();

        /*Executa o sql*/
        Statement stmt = connection.createStatement();
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
        String SQL = "SELECT * FROM Pessoa WHERE User_email  = '" + email + "'" + "AND Password = '" + password + "'";
        connection.commit();
        // executa o SQL.
        Statement stmt = connection.createStatement();
        ResultSet resultado = stmt.executeQuery(SQL);

        if (resultado.next()) {
            result = true;
        }
        stmt.close();
        return result;
    }

    /**
     * Método responsável por ir buscar à base dados todas as pessoas que
     * existem com registo menos a pessoa que se encontra logada
     *
     * @param myEmail email da pessoa logada
     * @return Lista desordenada das pessoas que encontrou na base dados
     */
    public ArrayUnorderedList<Pessoa> getAllPessoas(String myEmail) {
        Statement statement = null;
        ArrayUnorderedList<Pessoa> valor = new ArrayUnorderedList<>();
        try {
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String SQL = "SELECT * FROM Pessoa WHERE USER_EMAIL  <> '" + myEmail + "'";

            ResultSet r = statement.executeQuery(SQL);

            Boolean hasPessoa = false;
            while (r.next()) {
                hasPessoa = true;
                Pessoa tmpP = new Pessoa(r.getString("USER_EMAIL"), r.getString("USER_NOME"), null, r.getInt("NR_CREDITOS"));
                valor.addToRear(tmpP);

            }

            if (!hasPessoa) {
                System.out.println("Não existe Pessoas");
                valor = null;
            }
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
        return valor;
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
