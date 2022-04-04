package database;

/** Importaciones */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

/**
 * Clase que se encarga de la conexion con la base de datos.
 * @author Daniel
 * @see UsuarioQuerys
 */
public class Conexion {
    
    private static Properties properties = null;
    private static final String JDBC_URL       = "url";
    private static final String USER           = "user";
    private static final String PASSWORD       = "password";
    
    /**
     * Establece la conexion con la base de datos.
     * @return La conexion establecida.
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        try {
            properties = new Properties();
            InputStream file = new FileInputStream("C:\\Users\\Admin\\Desktop\\"
                    + "GitHub_Construccion\\casos_de_uso\\src\\database\\"
                    + "dbConfig.properties");
            properties.load(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            System.exit(0);
        }
        return DriverManager.getConnection(properties.getProperty(JDBC_URL), 
                properties.getProperty(USER), properties.getProperty(PASSWORD));
    }
    
    /**
     * Cierra un ResultSet
     * @param rs el ResultSet a cerrar
     * @throws SQLException 
     */
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    /**
     * Cierra un Statement
     * @param smtm el Statement a cerrar
     * @throws SQLException 
     */
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    
    /**
     * Cierra un PreparedStatement
     * @param smtm el PreparedStatement a cerrar
     * @throws SQLException 
     */
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    
    /**
     * Cierra la conexion
     * @param conn la conexion a cerrar
     * @throws SQLException 
     */
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
