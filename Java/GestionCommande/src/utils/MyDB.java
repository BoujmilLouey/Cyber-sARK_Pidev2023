/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Skand
 */
public class MyDB {

    private String url = "jdbc:mysql://localhost:3306/bdpidev";
    private String user = "root";
    private String password = "";
    
    private Connection cnx;
    
    private static MyDB instance;

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
}
