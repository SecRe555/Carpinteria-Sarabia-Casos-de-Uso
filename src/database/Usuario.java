package database;

/**
 * Clase usada para almacenar los registros de las bases de datos.
 * @author Daniel
 * @see UsuarioQuerys
 */
public class Usuario {
    
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String email;
    private String nickname;
    private String password;
    
    public Usuario(){
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String nombres, String apellidos, String email, 
            String nickname, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public Usuario(int idUsuario, String nombres, String apellidos, 
            String email, String nickname, String password) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombres=" + nombres 
                + ", apellidos=" + apellidos + ", email=" + email 
                + ", nickname=" + nickname + ", password=" + password + '}';
    }
}
