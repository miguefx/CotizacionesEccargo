/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Conexion;

/**
 *
 * @author matc_
 */
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String driverDB;
    private static String urlDB;
    private static Connection cn;
    private static String driver;
    private static String URLBD;

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://145.239.3.207;databaseName=EC_CONTAINERS;user=SA;password=6xTrduTC");
            System.out.println("Sirve Conexion BD");
        }
        catch (Exception exception) {
            // empty catch block
        }
        return cn;
    }

    
}