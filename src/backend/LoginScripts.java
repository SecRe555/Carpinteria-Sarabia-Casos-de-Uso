package backend;

import database.Usuario;
import database.UsuarioQuerys;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class LoginScripts {
    UsuarioQuerys querys = new UsuarioQuerys();
    List<Usuario> users = querys.seleccionar();
    private boolean isLogin;
    private boolean invalidPassword = false;
    private boolean isEmailCorrect = false;
    private String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",};
    Random random = new Random();
    
    public void validateUser(String nickname, String password){
    	isLogin = false;
    	
        users.forEach(user -> {
            if (user.getNickname().equals(nickname) && user.getPassword().equals(password)){
                JOptionPane.showMessageDialog(null, "El usuario ha ingresado con exito\n"
                        + "\nNombre Completo: " + user.getNombres() + " " + user.getApellidos()
                        + "\nEmail: " + user.getEmail(), 
                        "Bienvenido " + user.getNickname(), -1);
                isLogin = true;
            } else if (user.getNickname().equals(nickname)){
                invalidPassword = true;
            }
        });
        if (!isLogin){
            if(invalidPassword){
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta."
                        + "\nIntente nuevamente por favor.", "Error al iniciar sesion", 0);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario inexistente."
                        + "\nIntente nuevamente por favor.", "Error al iniciar sesion", 0);
            }
        }
    }
    
    public boolean validateEmail(String email) {
    	users.forEach(user ->{
	    		if (user.getEmail().equals(email)){
	    			isEmailCorrect = true;
	    		}
    		}
    	);
    	return isEmailCorrect;
    }
    
    public String generateCodeKey(){
    	int randomNum;
    	String codeKey = "";
    	for (int i = 0; i < 5; i++) {
    		randomNum = random.nextInt(10);
    		codeKey += numbers[randomNum];
    	}
    	return codeKey;
    }
    
    public void updatePassword(String email, String newPassword) {
    	users.forEach(user ->{
    		if (user.getEmail().equals(email)) {
    			user.setPassword(newPassword);
    			querys.actualizarPassword(user);
    		}
    	});
    }
}
