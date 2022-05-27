package backend;

/** Importaciones */
import database.Usuario;
import database.UsuarioQuerys;

import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Clase que interactua con las consultas de la base de datos y añade
 * funcionalidades a la clase de Interfaz.
 * @author Daniel
 * @see Interfaz
 * @see UsuarioQuerys
 */
public class Scripts {

    UsuarioQuerys querys = new UsuarioQuerys();
    List<Usuario> users = querys.seleccionar();
    private int returnValue;
    private boolean validateLogin;
    private boolean isEmailCorrect;
    private String emailValided;
    String returnName;
    private final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8",
        "9", "0",};
    Random random = new Random();
    
    /**
     * Verifica que el login sea correcto y devuelve valores dependiendo lo
     * encontrado.
     * @param nickname el nickname ingresado por el usuario
     * @param password la contraseña ingresada por el usuario
     * @return un entero con el resultado obtenido:<br>
     *         {@code 0} si el usuario ingreso correctamente,<br>
     *         {@code 1} si el nickname es correcto pero la contraseña no,<br>
     *         {@code -1} si ambos parametros son incorrectos,
     */
    public int validateLogin(String nickname, String password) {
        validateLogin = false;
        users.forEach(user -> {
            if (user.getNickname().equals(nickname) 
                    && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null,
                        "El usuario ha ingresado con exito\n"
                        + "\nNombre Completo: " + user.getNombres() + " " 
                        + user.getApellidos() + "\nEmail: " + user.getEmail(),
                        "Bienvenido " + user.getNickname(), 
                        JOptionPane.PLAIN_MESSAGE);
                returnValue = 0;
                validateLogin = true;
                return;
            } else if (user.getNickname().equals(nickname)) {
                returnValue = 1;
                validateLogin = true;
            }
        });
        if (!validateLogin) {
            returnValue = -1;
        }
        return returnValue;
    }
    
    /**
     * Verifica que el email recibido se encuentre dentro de la base de datos.
     * @param email la direccion de email dada por el usuario
     * @return {@code true} si el email se encuentra en la base de datos, 
     *         {@code false} de lo contrario
     */
    public boolean validateEmail(String email) {
        isEmailCorrect = false;
        users.forEach(user -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                isEmailCorrect = true;
                emailValided = user.getEmail();
                return;
            }
        }
        );
        return isEmailCorrect;
    }
    
    /**
     * Retorna el email de la persona que cambiara de contraseña.
     * @return la direccion de email validada en validateEmail en minusculas.
     * @see validateEmail
     */
    public String returnEmail() {
        return emailValided.toLowerCase();
    }
    /**
     * Devuelve el nombre de una persona a partir de su correo.
     * @param email la direccion de correo del usuario
     * @return el nombre del usuario encontrado
     */
    public String selectName(String email) {
        returnName = "";
        users.forEach(user -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                returnName = user.getNombres() + " " + user.getApellidos();
                return;
            }
        });
        return returnName;
    }
    
    /**
     * Genera un codigo de confirmacion de 5 digitos.
     * @return el codigo generado
     */
    public String generateCodeKey() {
        int randomNum;
        String codeKey = "";
        for (int i = 0; i < 5; i++) {
            randomNum = random.nextInt(10);
            codeKey += numbers[randomNum];
        }
        return codeKey;
    }
    
    /**
     * Actualiza la contraseña del usuario.
     * @param email la direccion de correo del usuario que quiere cambiar su 
     *        contraseña.
     * @param newPassword la nueva contraseña 
     */
    public void updatePassword(String email, String newPassword) {
        users.forEach(user -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                user.setPassword(newPassword);
                querys.actualizarPassword(user);
                return;
            }
        });
    }
}
