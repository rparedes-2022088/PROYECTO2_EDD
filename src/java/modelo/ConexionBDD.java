/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ruben
 */
public class ConexionBDD {
    private final String Driver = "oracle.jdbc.OracleDriver";
//    private final String Driver = "oracle.jdbc.driver.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String User = "PROYECTO2_EDD";
    private final String Password = "admin";
    
    private Connection cadena;

    public ConexionBDD() {
        this.cadena = null;
    }

    public Connection conectar(){
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.cadena = DriverManager.getConnection(URL, User, Password);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
    
    public void desconectar(){
        try {
            this.cadena.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}