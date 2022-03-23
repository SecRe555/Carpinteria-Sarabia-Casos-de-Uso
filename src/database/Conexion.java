package database;

import java.sql.*;

public class Conexion {
    private static String vendor="mysql";
    private static String server="localhost";
    private static String port="3306";    
    private static String db="info_usuarios";
    private static String params="?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_URL = "jdbc:"+vendor+"://"+server+":"+port+"/"+db+params;
    private static final String USER = "root";
    private static final String PASSWORD = "Jerus";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
