package com.ashdz.webrevistas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asael
 */
public class Conexion {
    private static Connection conexion = null;
    private final String jdbcURL = "jdbc:mariadb://localhost:3306/WebRevistas";
    private final String jdbcUsername = "userDB";
    private final String jdbcPassword = "123";
    
    private Conexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se pudo conectar");
            System.out.println(e.getMessage());
        }
    }
    
    public static Connection getConexion(){
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }
    
    public static void desconectar(){
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("se desconecto");
            } catch (SQLException ex) {
                System.out.println("Fallo al intentar desconectar");
                System.out.println(ex.getMessage());
            }
        }
    }
}
