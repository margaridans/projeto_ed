package Database;

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
    private final String TABELA_MENSAGEM = "Messagem";
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
                    + " (EMAIL VARCHAR(50) PRIMARY KEY NOT NULL,"
                    + "NOME_USER VARCHAR(50) NOT NULL,"
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
                    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "CONTEUDO VARCHAR(100) NOT NULL,"
                    + "DATA TEXT NOT NULL,"
                    + "USER_EMAIL VARCHAR(50) NOT NULL,"
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Person(EMAIL))";

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
                    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "CONTEUDO VARCHAR(100) NOT NULL,"
                    + "DATA TEXT NOT NULL,"
                    + "USER_EMAIL VARCHAR(50) NOT NULL,"
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Person(EMAIL))";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Comentário' já existe!");
        }
    }

    /*Criação da tabela que armazena os tipos de mensagens*/
    public void criarTabelaTipoMensagem() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            stm.execute("PRAGMA foreign_keys = ON");
            String sqlTable = "CREATE TABLE " + TABELA_TIPO_MENSAGEM
                    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "CONTEUDO VARCHAR(100) NOT NULL,"
                    + "DATA TEXT NOT NULL,"
                    + "USER_EMAIL VARCHAR(50) NOT NULL,"
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Person(EMAIL))";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Tipo Mensagem' já existe!");
        }
    }

    public void criarTabelaAmizade() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            stm.execute("PRAGMA foreign_keys = ON");
            String sqlTable = "CREATE TABLE " + TABELA_AMIZADE
                    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "CONTEUDO VARCHAR(100) NOT NULL,"
                    + "DATA TEXT NOT NULL,"
                    + "USER_EMAIL VARCHAR(50) NOT NULL,"
                    + "FOREIGN KEY(USER_EMAIL) REFERENCES Person(EMAIL))";

            stm.executeUpdate(sqlTable);
        } catch (SQLException ex) {
            System.out.println("A tabela 'Amizade' já existe!");
        }
    }

//
//    /**
//     * Inserir edges na tabela de Edges
//     *
//     * @param edge ligação entre dois pontos na Network
//     * @param id número de identificação d Edge na base de dados
//     */
//    public void insertToEdgeTable(Edge edge, int id) {
//        Statement statement = null;
//
//        try {
//            connection.setAutoCommit(false);
//            statement = connection.createStatement();
//            String insert = "INSERT INTO " + TABLE_NAME_EDGES
//                    + "(ID,ORIGIN,DESTINATION,TIME,DISTANCE) " + "VALUES (" + id + ","
//                    + edge.getOrigin() + ","
//                    + edge.getDestination() + ","
//                    + edge.getTime() + ","
//                    + edge.getDistance() + ");";
//
//            statement.executeUpdate(insert);
//            connection.commit();
//            statement.close();
//
//        } catch (SQLException ex) {
//            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
//        }
//    }
//
//    /**
//     * Executar uma consulta à base de dados obter uma lista de Edges a partir
//     * de um ficheiro de base de dados
//     *
//     * @param whereCondition filtro de consulta para distância inferiores a um
//     * valor
//     * @return LinkedUnorderedList com referências a uma lista de edges
//     */
//    public LinkedUnorderedList<Edge> selectFromEdges(String whereCondition) {
//        boolean tableExists = false;
//        Edge edge;
//        Statement statement;
//        LinkedUnorderedList<Edge> edgeList = new LinkedUnorderedList();
//        String select = "SELECT ORIGIN,DESTINATION,TIME,DISTANCE FROM " + TABLE_NAME_EDGES + whereCondition;
//        try {
//            statement = connection.createStatement();
//            try (ResultSet result = statement.executeQuery(select)) {
//                while (result.next()) {
//                    tableExists = true;
//                    edge = new Edge();
//                    edge.setOrigin(result.getString("ORIGIN"));
//                    edge.setDestination(result.getString("DESTINATION"));
//                    edge.setTime(result.getDouble("TIME"));
//                    edge.setDistance(result.getDouble("DISTANCE"));
//                    edgeList.addToRear(edge);
//
//                }
//            }
//            if (!tableExists) {
//                return null;
//            }
//            statement.close();
//        } catch (SQLException ex) {
//            return null;
//        }
//        return edgeList;
//    }
//
//    /**
//     * Executar uma consulta à base de dados e obter um Edge de acordo com o seu
//     * ponto de origem e seu ponto de destino
//     *
//     * @param origin ponto de origem do Edge
//     * @param destination ponto de destino do Edge
//     * @return Edge caso exista na base de dados, null se não existir
//     */
//    public Edge selectEdgeFromEdges(String origin, String destination) {
//        String whereCondition = " WHERE '" + origin + "' = ORIGIN " + "AND '" + destination + "' = DESTINATION;";
//        boolean tableExists = false;
//        Edge edge = null;
//        Statement statement;
//        String select = "SELECT ORIGIN,DESTINATION,TIME,DISTANCE FROM " + TABLE_NAME_EDGES + whereCondition;
//        try {
//            statement = connection.createStatement();
//            try (ResultSet result = statement.executeQuery(select)) {
//                tableExists = true;
//                edge = new Edge();
//                edge.setOrigin(result.getString("ORIGIN"));
//                edge.setDestination(result.getString("DESTINATION"));
//                edge.setTime(result.getDouble("TIME"));
//                edge.setDistance(result.getDouble("DISTANCE"));
//            }
//            if (!tableExists) {
//                return null;
//            }
//            statement.close();
//        } catch (SQLException ex) {
//            return null;
//        }
//        return edge;
//    }
//
//    /**
//     * Executar uma query à base de dados para consultar o número de ligações
//     * armazenadas na base de dados
//     *
//     * @return número de Edges armazenados na base de dados
//     */
//    public int selectNumRows() {
//        Statement statement = null;
//        String select = "SELECT COUNT(ID) AS i FROM " + TABLE_NAME_EDGES;
//        ResultSet result;
//        int num = 0;
//        try {
//            statement = connection.createStatement();
//            result = statement.executeQuery(select);
//            num = result.getInt("i");
//            statement.close();
//        } catch (SQLException ex) {
//            System.err.print(SqlConnection.class.getName() + ": " + ex.getMessage());
//        }
//        return num;
//    }
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
