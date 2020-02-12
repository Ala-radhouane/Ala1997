/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alarad
 */
public class ConnectionDATAB {

    private static String url = "jdbc:mysql://localhost:3306/xhunter";
    private static String utilisateur = "root";
    private static String motDePasse = "";
    private static Connection cnx;
    private static ConnectionDATAB cbd;
    
    public static Connection getCnx() {
        return cnx;
    }

    private ConnectionDATAB() {

        try {
            cnx = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connection etablie");
        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }

    }
    public static ConnectionDATAB getInstance(){
        if (cbd==null)
            cbd = new ConnectionDATAB();
        return cbd;
    }

}
