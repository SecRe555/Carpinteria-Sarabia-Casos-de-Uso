package database;

/** Importaciones */
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase encargada de ejecutar las consultas.
 * @author Daniel
 */
public class UsuarioQuerys {
    private Conexion conexion            = new Conexion();
    private final String SQL_SELECT      = "SELECT id_usuario, nombres, "
            + "apellidos, email, nickname, password_usuario FROM usuarios";
    private final String SQL_INSERT      = "INSERT INTO usuarios(nombres, "
            + "apellidos, email, nickname, password_usuario) VALUES"
            + "(?, ?, ?, ?, ?)";
    private final String SQL_UPDATE      = "UPDATE usuarios SET nombres = ?, "
            + "apellidos = ?, email = ?, nickname = ?, password_usuario = ?"
            + "WHERE id_usuario = ?";
    private final String UPDATE_PASSWORD = "UPDATE usuarios SET "
            + "password_usuario = ? WHERE id_usuario = ?";
    
    /**
     * Ejecuta una sentencia de SELECT.
     * @return los registros recuperados
     */
    public List<Usuario> seleccionar() {
        Connection conn        = null;
        PreparedStatement stmt = null;
        ResultSet rs           = null;
        Usuario usuario        = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs   = stmt.executeQuery();
            while (rs.next()) {
                int idUsuario   = rs.getInt("id_usuario");
                String nombre   = rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                String email    = rs.getString("email");
                String nickname = rs.getString("nickname");
                String password = rs.getString("password_usuario");

                usuario = new Usuario(idUsuario, nombre, apellido, email,
                        nickname, password);

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                conexion.close(rs);
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return usuarios;
    }
    
    /**
     * Ejecuta una sentencia de INSERT
     * @param usuario el registro a insertar
     */
    public int insertar(Usuario usuario){
        Connection conn        = null;
        PreparedStatement stmt = null;
        int registros          = 0;
        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombres());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getNickname());
            stmt.setString(5, usuario.getPassword());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    /**
     * Ejecuta una sentencia de UPDATE.
     * @param usuario los datos del registro a actualizar
     */
    public int actualizar(Usuario usuario){
        Connection conn        = null;
        PreparedStatement stmt = null;
        int registros          = 0;
        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getNombres());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getNickname());
            stmt.setString(6, usuario.getPassword());
            stmt.setInt(6, usuario.getIdUsuario());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    /**
     * Ejecuta una sentencia de UPDATE pero solo del campo password_usuario
     * @param usuario el registro a actualizar
     */
    public int actualizarPassword(Usuario usuario) {
    	Connection conn        = null;
        PreparedStatement stmt = null;
        int registros          = 0;
        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(UPDATE_PASSWORD);
            stmt.setString(1, usuario.getPassword());
            stmt.setInt(2, usuario.getIdUsuario());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
