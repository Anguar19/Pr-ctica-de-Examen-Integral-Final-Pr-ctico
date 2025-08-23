/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

/**
 *
 * @author Frank
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Datos de conexión (ajusta según tu configuración)
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PURAVIDAMIPYME";
    private static final String USUARIO = "sa";
    private static final String PASSWORD = "1234";

    // Conexión única y estática
    private static Connection conexion = null;

    /**
     * Devuelve la conexión activa a SQL Server.
     * Si no existe, la crea.
     */
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexión establecida correctamente.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver JDBC de SQL Server: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    /**
     * Cierra la conexión si está abierta.
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Prueba de conexión
    public static void main(String[] args) {
        Connection conn = Conexion.getConexion();
        Conexion.cerrarConexion();
    }
}
