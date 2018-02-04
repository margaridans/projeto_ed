package Database;

import Classes.Pessoa;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;

/**
 * @author Marisa Machado - 8140
 */
public class SqlConnection {

    private final String DRIVER = "org.sqlite.JDBC";
    private final String TABELA_PESSOA = "Pessoa";
    private final String TABELA_COMENTARIO = "Comentario";
    private final String TABELA_MENSAGEM = "Mensagem";
    private final String TABELA_TIPO_MENSAGEM = "TipoMensagem";
    private final String TABELA_AMIZADE = "Amizade";
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
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /*Criação da tabela que armazena pessoas*/
    public void criarTabelaPessoa() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            String sqlTable = "CREATE TABLE " + TABELA_PESSOA
                    + " (USER_EMAIL VARCHAR(50) PRIMARY KEY NOT NULL,"
                    + "USER_NOME VARCHAR(50) NOT NULL,"
                    + "PASSWORD VARCHAR(50) NOT NULL)";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Pessoa' já existe!");
        }
    }

    /*Criação da tabela que armazena mensagens*/
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

    /*Criação da tabela que armazena comentário*/
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

    /*Criação da tabela que armazena os tipos de mensagens*/
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
     * Inserir utilizadores na tabela Pessoas
     *
     * @param user - nó num grafo que representa uma pessoa
     */
    public void inserirPessoa(Pessoa user) {
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String insert = "INSERT INTO " + TABELA_PESSOA
                    + "(USER_EMAIL, USER_NOME, PASSWORD) " + "VALUES ( " + user.getUser_email() + ","
                    + user.getUser_nome()+ ","
                    + user.getPassword()+  ");";

            statement.executeUpdate(insert);
            connection.commit();
            statement.close();

        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Fechar a connexão à base de dados
     */
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
        }
    }

}
