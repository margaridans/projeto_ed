/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ed;

import Database.SqlConnection;
import InterfacesGraficas.Login;


/**
 * @author Margarida Sousa - 8140092
 * @author Marisa Machado - 8140186
 */
public class Projeto_ed {

    public static SqlConnection connection;

    public Projeto_ed() {
        connection = new SqlConnection();
        Login login = new Login();
    }

  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Projeto_ed projeto_ed = new Projeto_ed();
    }

}
