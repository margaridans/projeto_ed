/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ed;

import Database.SqlConnection;
import Interfaces.Login;
import Interfaces.Registo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author margarida
 */
public class Projeto_ed {

    public static SqlConnection connection;

    public Projeto_ed() {
        new Registo(this);

        connection = new SqlConnection();

    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Projeto_ed();
    }

}
