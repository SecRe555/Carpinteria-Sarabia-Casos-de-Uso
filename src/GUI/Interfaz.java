package GUI;

import backend.LoginScripts;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

public class Interfaz extends JFrame{
    LoginScripts script = new LoginScripts();
    JPanel container;
    JTabbedPaneCloseButton tabbedPane;
    JPanel carpentryTab;
    JPanel emailTab;
    JPanel missPassword;
    //private boolean fullscreen;
    private JPanel mainPanel;
    private boolean removeUserExampleText = true;
    private boolean removePasswordExampleText = true;
    private boolean removeEmailExampleText = true;
    private String currentEmail;
    private boolean validEmail;
    //private int tabCount;
    private String currentCodeKey;
    private String codeKey = "";
    
    public Interfaz(){
        initWindow();
        createContainer();
        createActionButtons();
        createJTabbedPane();
    }
    
    private void initWindow(){
        setSize(1366,768);
        setLocationRelativeTo(null);
        setTitle("Navegador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/files/brave-icon.png")));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //fullscreen = true;
        setUndecorated(true);
    }
    
    private void createContainer(){
        container = new JPanel();
        container.setLayout(null);
        container.setBackground(Color.black);
        this.getContentPane().add(container);
    }
    
    private void createJTabbedPane(){
        UIManager.put("TabbedPane.selected", new Color(100,100,100));
        tabbedPane = new JTabbedPaneCloseButton();
        tabbedPane.setBounds(0,0,1366,768);
        tabbedPane.setBackground(Color.black);
        tabbedPane.setForeground(Color.white);
        createCarpentryTab();
        //createEmailTab(); --Esto sirve
        tabbedPane.addTab("Carpinteria Sarabia",
                new ImageIcon(getClass().getResource("/files/carpentry-icon.jpg")),
                carpentryTab, "www.carpinteria-sarabia.com/login");
        //tabbedPane.addTab("Email", new ImageIcon(getClass().getResource("/files/mail-icon.jpg")), 
                //emailTab, "www.email.com"); --Esto sirve
        container.add(tabbedPane);
    }
    
    private void createCarpentryTab(){
        carpentryTab = new JPanel();
        carpentryTab.setBackground(new Color(255, 255, 255));
        carpentryTab.setLayout(null);
        
        JLabel imgLogin = new JLabel();
        imgLogin.setBounds(0, 0, 850, 748);
        imgLogin.setIcon(new ImageIcon(getClass().getResource
            ("/files/img-login.jpg")));
        carpentryTab.add(imgLogin);
        
        JPanel panelTitleLogin = new JPanel();
        panelTitleLogin.setBounds(850, 0, 516, 200);
        panelTitleLogin.setBackground(new Color(194, 102, 10));
        panelTitleLogin.setLayout(null);
        carpentryTab.add(panelTitleLogin);
        
        JLabel titleLabel = new JLabel("<html>Inicio de sesión<br>de empleados.</html>");
        titleLabel.setOpaque(true);
        titleLabel.setBounds(80, 40, 316, 85);
        titleLabel.setBackground(null);
        titleLabel.setForeground(new Color (255,255,255));
        titleLabel.setFont(titleLabel.getFont().deriveFont((float)40));
        panelTitleLogin.add(titleLabel);
        
        JLabel aviso = new JLabel();
        aviso.setOpaque(true);
        aviso.setBounds(80, 150, 350, 40);
        aviso.setText("<html>* Este servicio esta reservado exclusivamente a<br>aquellos empleados registrados</html>");
        aviso.setBackground(null);
        aviso.setForeground(new Color (255,255,255));
        aviso.setFont(aviso.getFont().deriveFont((float)15));
        panelTitleLogin.add(aviso);
        
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setOpaque(true);
        userLabel.setBounds(950, 250, 300, 50);
        userLabel.setBackground(null);
        userLabel.setForeground(new Color(100, 100, 100));
        userLabel.setFont(userLabel.getFont().deriveFont((float)36));
        carpentryTab.add(userLabel);
        
        JTextField userTextField = new JTextField("example");
        userTextField.setOpaque(true);
        userTextField.setBounds(950, 310, 300, 50);
        userTextField.setBackground(Color.white);
        userTextField.setForeground(new Color(100, 100, 100, 200));
        userTextField.setFont(userLabel.getFont().deriveFont((float)24));
        carpentryTab.add(userTextField);
        
        JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
        passwordLabel.setOpaque(true);
        passwordLabel.setBounds(950, 380, 300, 50);
        passwordLabel.setBackground(null);
        passwordLabel.setForeground(new Color(100, 100, 100));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont((float)36));
        carpentryTab.add(passwordLabel);
        
        JPasswordField passwordField = new JPasswordField("example");
        passwordField.setOpaque(true);
        passwordField.setBounds(950, 440, 251, 50);
        passwordField.setBackground(Color.white);
        passwordField.setForeground(new Color(100, 100, 100, 200));
        passwordField.setFont(passwordLabel.getFont().deriveFont((float)24));
        passwordField.setEditable(true);
        carpentryTab.add(passwordField);
        
        JToggleButton viewPassword = new JToggleButton();
        viewPassword.setBounds(1200, 440, 50, 49);
        viewPassword.setIcon(new ImageIcon(getClass().getResource("/files/closed eye.png")));
        viewPassword.setBackground(Color.white);
        viewPassword.setForeground(new Color(100, 100, 100));
        viewPassword.setFont(viewPassword.getFont().deriveFont((float)15));
        viewPassword.setFocusPainted(false);
        carpentryTab.add(viewPassword);
        
        JButton loginButton = new JButton();
        loginButton.setBounds(950, 550, 300, 50);
        loginButton.setText("Iniciar Sesi\u00F3n");
        loginButton.setBackground(new Color(194,102,10));
        loginButton.setForeground(Color.white);
        loginButton.setFont(loginButton.getFont().deriveFont((float)24));
        loginButton.setFocusPainted(false);
        carpentryTab.add(loginButton);
        
        JButton passMissedButton = new JButton();
        passMissedButton.setBounds(985, 605, 225, 20);
        passMissedButton.setText("¿Olvidaste tu contrase\u00F1a?");
        passMissedButton.setBackground(null);
        passMissedButton.setForeground(new Color(194,102,10));
        passMissedButton.setFont(passMissedButton.getFont().deriveFont((float)15));
        passMissedButton.setBorderPainted(false);
        passMissedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        carpentryTab.add(passMissedButton);
        
        JLabel underLineMissed = new JLabel(" ");
        underLineMissed.setOpaque(true);
        underLineMissed.setBounds(1000, 625, 200, 3);
        underLineMissed.setBackground(new Color(50, 50, 255));
        underLineMissed.setVisible(false);
        carpentryTab.add(underLineMissed);
        
        JLabel message = new JLabel();
        message.setBounds(950, 250, 290, 40);
        message.setOpaque(true);
        message.setVisible(false);
        message.setForeground(Color.white);
        message.setFont(message.getFont().deriveFont((float) 14));
        message.setHorizontalAlignment(JLabel.CENTER);
        carpentryTab.add(message);
        
        userTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if (removeUserExampleText){
                    userTextField.setText("");
                    userTextField.setForeground(Color.black);
                    removeUserExampleText = false;
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (userTextField.getText().equals("")){
                    userTextField.setText("example");
                    userTextField.setForeground(new Color(100, 100, 100, 200));
                    removeUserExampleText = true;
                }
            }
        });
        userTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char charTyped = evt.getKeyChar();
                if (charTyped == '\n'){
                    loginButton.doClick();
                }
            }
        });
        
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (removePasswordExampleText){
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                    removePasswordExampleText = false;
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().equals("") && !(viewPassword.isSelected())){
                    passwordField.setText("example");
                    passwordField.setForeground(new Color(100, 100, 100, 200));
                    removePasswordExampleText = true;
                }
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char charTyped = evt.getKeyChar();
                if (charTyped == '\n'){
                    loginButton.doClick();
                }
            }
        });
        
        char passwordChar = passwordField.getEchoChar();
        viewPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (viewPassword.isSelected()){
                	viewPassword.setIcon(new ImageIcon(getClass().getResource("/files/open eye.png")));
                    if (removePasswordExampleText){
                        passwordField.setText("");
                        removePasswordExampleText = false;
                    }
                    passwordField.setEchoChar((char) 0);
                    passwordField.setForeground(Color.black);
                } else {
                	viewPassword.setIcon(new ImageIcon(getClass().getResource("/files/closed eye.png")));
                    passwordField.setEchoChar(passwordChar);
                    if (passwordField.getText().equals("")){
                        passwordField.setText("example");
                        removePasswordExampleText = true;
                        passwordField.setForeground(new Color(100, 100, 100));
                    }
                }
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	userLabel.setLocation(950, 310);
        		userTextField.setLocation(950, 370);
        		passwordLabel.setLocation(950, 440);
        		passwordField.setLocation(950, 500);
        		viewPassword.setLocation(1200, 500);
        		loginButton.setLocation(950, 610);
        		passMissedButton.setLocation(985, 665);
        		underLineMissed.setLocation(1000, 685);
        		carpentryTab.revalidate();
        		carpentryTab.repaint();
            	if (removeUserExampleText || removePasswordExampleText) {
            		message.setBackground(new Color(255, 80, 80));
                    message.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            		message.setText("Rellene todos los campos.");
            		message.setVisible(true);
            	} else {
            		String user = userTextField.getText();
                    char[] passwordArray = passwordField.getPassword();
                    String password = "";
                    for (int i = 0; i < passwordArray.length; i++){
                        password += passwordArray[i];
                    }
                    int isLogin;
                    isLogin = script.validateUser(user, password);
                    switch (isLogin) {
                    	case 0: 
                    		message.setText("Login exitoso.");
                    		message.setBackground(new Color(0, 255, 50));
                    		message.setBorder(BorderFactory.createLineBorder(Color.green, 1));
                    		break;
                    	case 1:
                    		message.setBackground(new Color(255, 80, 80));
                            message.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    		message.setText("Contraseña incorrecta.");
                    		break;
                    	case -1:
                    		message.setBackground(new Color(255, 80, 80));
                            message.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    		message.setText("Usuario inexistente");
                    		break;
                    }
                    message.setVisible(true);
            	}
            }
        });
        
        passMissedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt){
                passMissedButton.setForeground(new Color(50, 50, 255));
                underLineMissed.setVisible(true);
            }
            public void mouseExited(MouseEvent evt){
                passMissedButton.setForeground(new Color(194,102,10));
                underLineMissed.setVisible(false);
            }
        });
        passMissedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                missPassword = createMissPasswordPanel();
                tabbedPane.addTab("Contrase\u00F1a olvidada",
                    new ImageIcon(getClass().getResource("/files/carpentry-icon.jpg")),
                    missPassword, "www.carpinteria-sarabia.com/password_reset");
                validEmail = false;
            }
        });
    }
    
    private JPanel createMissPasswordPanel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(null);
		
		JLabel carpentryLogo = new JLabel();
		carpentryLogo.setBounds(658, 50, 50, 50);
		carpentryLogo.setIcon(new ImageIcon(getClass().getResource("/files/carpentry-icon1.jpg")));
		mainPanel.add(carpentryLogo);
		
		JLabel passwordMissedTitle = new JLabel("Cambiar contraseña");
		passwordMissedTitle.setBounds(583, 125, 200, 50);
		passwordMissedTitle.setBackground(Color.white);
		passwordMissedTitle.setForeground(Color.black);
		passwordMissedTitle.setFont(passwordMissedTitle.getFont().deriveFont((float) 20));
		mainPanel.add(passwordMissedTitle);
		
		JPanel secondaryPanel = new JPanel();
		secondaryPanel.setBounds(509, 200, 350, 450);
		secondaryPanel.setBackground(new Color(225, 225, 225));
		secondaryPanel.setLayout(null);
		mainPanel.add(secondaryPanel);
		
		JLabel setEmail = new JLabel("<html>Introduzca su direccion de correo<br>electronico</html>");
		setEmail.setBounds(30, 40, 290, 30);
		setEmail.setBackground(new Color(255,255,255,0));
		setEmail.setForeground(Color.black);
		setEmail.setFont(setEmail.getFont().deriveFont((float) 14));
		secondaryPanel.add(setEmail);
		
		JTextField emailTextField = new JTextField("example@mail.com");
		emailTextField.setBounds(30, 110, 290, 40);
		emailTextField.setBackground(Color.white);
		emailTextField.setForeground(new Color(100, 100, 100, 200));
		emailTextField.setFont(emailTextField.getFont().deriveFont((float) 16));
		emailTextField.setFocusable(false);
		secondaryPanel.add(emailTextField);
		emailTextField.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt){
				emailTextField.setFocusable(true);
			}
			
			public void mousePressed(MouseEvent evt) {
				emailTextField.setText("");
                emailTextField.setForeground(Color.black);
                removeEmailExampleText = false;
			}
		});
		
		emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if (removeEmailExampleText){
                    emailTextField.setText("");
                    emailTextField.setForeground(Color.black);
                    removeEmailExampleText = false;
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (emailTextField.getText().equals("")){
                    emailTextField.setText("example@mail.com");
                    emailTextField.setForeground(new Color(100, 100, 100, 200));
                    removeEmailExampleText = true;
                }
            }
        });
		
		JButton sendEmail = new JButton("Enviar email de confirmacion");
		sendEmail.setBounds(30, 175, 290, 40);
		sendEmail.setBackground(new Color(0, 170, 50));
		sendEmail.setForeground(Color.white);
		sendEmail.setFont(sendEmail.getFont().deriveFont((float) 14));
		sendEmail.setFocusPainted(false);
		secondaryPanel.add(sendEmail);
		sendEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validEmail = script.validateEmail(emailTextField.getText());
				setEmail.setLocation(30, 110);
				emailTextField.setLocation(30, 175);
				sendEmail.setLocation(30, 245);
				JLabel incorrectEmail = new JLabel();
				if ( validEmail ) {
					validEmail = false;
					currentEmail = emailTextField.getText();
					mainPanel.remove(secondaryPanel);
					mainPanel.revalidate();
					mainPanel.repaint();
					JPanel restorePassword = createEnterKeyCodePanel();
					mainPanel.add(restorePassword);
					currentCodeKey = script.generateCodeKey();
					JOptionPane.showMessageDialog(null, currentCodeKey);
				} else {
					incorrectEmail.setBounds(30, 40, 290, 40);
					incorrectEmail.setText("<html>Email incorrecto.<br>Intente nuevamente</html>");
					incorrectEmail.setOpaque(true);
					incorrectEmail.setBackground(new Color(255, 80, 80));
					incorrectEmail.setForeground(Color.white);
					incorrectEmail.setFont(incorrectEmail.getFont().deriveFont((float) 14));
					incorrectEmail.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					secondaryPanel.add(incorrectEmail);
					
					secondaryPanel.revalidate();
					secondaryPanel.repaint();
				}
			}
		});
		
		emailTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(evt.getKeyChar() == '\n') {
					sendEmail.doClick();
				}
			}
		});

		
		return mainPanel;
	}
    
    private JPanel createEnterKeyCodePanel(){
    	JPanel panel = new JPanel();
    	panel.setBounds(509, 200, 350, 450);
		panel.setBackground(new Color(225, 225, 225));
		panel.setLayout(null);
		
		JLabel message = new JLabel();
    	message.setBounds(30, 40, 290, 40);
    	message.setOpaque(true);
		message.setText("<html>Se ha enviado el codigo de confirmacion.</html>");
		message.setBackground(new Color(0, 200, 50));
		message.setForeground(Color.white);
		message.setFont(message.getFont().deriveFont((float) 14));
		message.setBorder(BorderFactory.createLineBorder(Color.green, 1));
		panel.add(message);
		
		JLabel setCode = new JLabel();
		setCode.setBounds(30, 100, 290, 40);
		setCode.setOpaque(true);
		setCode.setText("<html>Introduzca el codigo enviado a su correo.</html>");
		setCode.setBackground(new Color(225, 225, 225));
		setCode.setForeground(Color.black);
		setCode.setFont(setCode.getFont().deriveFont((float) 14));
		panel.add(setCode);
		
		JTextField field1 = new JTextField();
		field1.setBounds(30, 170, 50, 50);
		field1.setOpaque(true);
		field1.setBackground(Color.white);
		field1.setForeground(Color.black);
		field1.setFont(field1.getFont().deriveFont((float) 24));
		field1.setHorizontalAlignment(JTextField.CENTER);
		field1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel.add(field1);
		
		JTextField field2 = new JTextField();
		field2.setBounds(90, 170, 50, 50);
		field2.setOpaque(true);
		field2.setBackground(Color.white);
		field2.setForeground(Color.black);
		field2.setFont(field2.getFont().deriveFont((float) 24));
		field2.setHorizontalAlignment(JTextField.CENTER);
		field2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel.add(field2);
		
		JTextField field3 = new JTextField();
		field3.setBounds(150, 170, 50, 50);
		field3.setOpaque(true);
		field3.setBackground(Color.white);
		field3.setForeground(Color.black);
		field3.setFont(field3.getFont().deriveFont((float) 24));
		field3.setHorizontalAlignment(JTextField.CENTER);
		field3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel.add(field3);
		
		JTextField field4 = new JTextField();
		field4.setBounds(210, 170, 50, 50);
		field4.setOpaque(true);
		field4.setBackground(Color.white);
		field4.setForeground(Color.black);
		field4.setFont(field4.getFont().deriveFont((float) 24));
		field4.setHorizontalAlignment(JTextField.CENTER);
		field4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel.add(field4);
		
		JTextField field5 = new JTextField();
		field5.setBounds(270, 170, 50, 50);
		field5.setOpaque(true);
		field5.setBackground(Color.white);
		field5.setForeground(Color.black);
		field5.setFont(field5.getFont().deriveFont((float) 24));
		field5.setHorizontalAlignment(JTextField.CENTER);
		field5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel.add(field5);

		JButton confirmKeyCode = new JButton("Confirmar codigo de confirmación");
		confirmKeyCode.setBounds(30, 240, 290, 40);
		confirmKeyCode.setBackground(new Color(0, 170, 50));
		confirmKeyCode.setForeground(Color.white);
		confirmKeyCode.setFont(confirmKeyCode.getFont().deriveFont((float) 14));
		confirmKeyCode.setFocusPainted(false);
		panel.add(confirmKeyCode);
		
		field1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				char charTyped = evt.getKeyChar();
				if (charTyped == '\n') {
					confirmKeyCode.doClick();
				} else if (!(Character.isDigit(charTyped))) {
					Toolkit.getDefaultToolkit().beep();
					evt.consume();
				} else if (field1.getText().length()>=1){
					evt.consume();
				} else {
					field2.requestFocus();
				}
			}
		});
		field2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				char charTyped = evt.getKeyChar();
				if (charTyped == '\n') {
					confirmKeyCode.doClick();
				} else if (!(Character.isDigit(charTyped))) {
					Toolkit.getDefaultToolkit().beep();
					evt.consume();
				} else if (field2.getText().length()>=1){
					evt.consume();
				} else {
					field3.requestFocus();
				}
			}
			
			public void keyPressed(KeyEvent evt) {
				if ((evt.getKeyCode() == KeyEvent.VK_DELETE
						|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
						&& field2.getText().equals("")){
					field1.setText("");
					field1.requestFocus();
				}
			}
		});
		field3.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				char charTyped = evt.getKeyChar();
				if (charTyped == '\n') {
					confirmKeyCode.doClick();
				} else if (charTyped == KeyEvent.VK_DELETE
						&& field3.getText().equals("")){
					field2.requestFocus();
				} else if (!(Character.isDigit(charTyped))) {
					Toolkit.getDefaultToolkit().beep();
					evt.consume();
				} else if (field3.getText().length()>=1){
					evt.consume();
				} else {
					field4.requestFocus();
				}
			}
			
			public void keyPressed(KeyEvent evt) {
				if ((evt.getKeyCode() == KeyEvent.VK_DELETE
						|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
						&& field3.getText().equals("")){
					field2.setText("");
					field2.requestFocus();
				}
			}
		});
		field4.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				char charTyped = evt.getKeyChar();
				if (charTyped == '\n') {
					confirmKeyCode.doClick();
				} else if (charTyped == KeyEvent.VK_DELETE
						&& field4.getText().equals("")){
					field3.requestFocus();
				} else if (!(Character.isDigit(charTyped))) {
					Toolkit.getDefaultToolkit().beep();
					evt.consume();
				} else if (field4.getText().length()>=1){
					evt.consume();
				} else {
					field5.requestFocus();
				}
			}
			
			public void keyPressed(KeyEvent evt) {
				if ((evt.getKeyCode() == KeyEvent.VK_DELETE
						|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
						&& field4.getText().equals("")){
					field3.setText("");
					field3.requestFocus();
				}
			}
		});
		field5.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				char charTyped = evt.getKeyChar();
				if (charTyped == '\n') {
					confirmKeyCode.doClick();
				} else if (charTyped == KeyEvent.VK_DELETE
					&& field5.getText().equals("")){
					field4.requestFocus();
				} else if (!(Character.isDigit(charTyped))) {
					Toolkit.getDefaultToolkit().beep();
					evt.consume();
				} else if (field5.getText().length()>=1){
					evt.consume();
				} else {
					confirmKeyCode.requestFocus();
				}
			}
			
			public void keyPressed(KeyEvent evt) {
				if ((evt.getKeyCode() == KeyEvent.VK_DELETE
						|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
						&& field5.getText().equals("")){
					field4.setText("");
					field4.requestFocus();
				}
			}
		});
		confirmKeyCode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (field1.getText().equals("") || 
					field2.getText().equals("") || 
					field3.getText().equals("") || 
					field4.getText().equals("") || 
					field5.getText().equals("")) {
					message.setText("<html>Rellene todos los campos.</html>");
					message.setBackground(new Color(255, 80, 80));
					message.setForeground(Color.white);
					message.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					message.setHorizontalAlignment(JTextField.CENTER);
				} else {
					codeKey = "";
					codeKey += field1.getText() +  field2.getText() + 
							field3.getText() + field4.getText() + field5.getText();
					if (codeKey.equals(currentCodeKey)) {
						mainPanel.remove(panel);
						mainPanel.revalidate();
						mainPanel.repaint();
						JPanel changePassword = createChangePassword();
						mainPanel.add(changePassword);
					} else {
						message.setText("<html>Codigo de confirmacion incorrecto.</html>");
						message.setBackground(new Color(255, 80, 80));
						message.setForeground(Color.white);
						message.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					}
				}
			}
		});
		confirmKeyCode.addKeyListener(new KeyAdapter() {
			public void keyTyped (KeyEvent evt) {
				if (evt.getKeyChar() == '\n') {
					confirmKeyCode.doClick();
				} else if (evt.getKeyChar() == KeyEvent.VK_DELETE){
					field5.setText("");
					field5.requestFocus();
				} 
			}
			
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_DELETE
						|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					field5.setText("");
					field5.requestFocus();
				}
			}
		});
		
		return panel;
    }
    
    private JPanel createChangePassword() {
    	JPanel panel = new JPanel();
    	panel.setBounds(509, 200, 350, 450);
		panel.setBackground(new Color(225, 225, 225));
		panel.setLayout(null);
		
		JLabel textSetPassword = new JLabel("<html>Ingrese la nueva contraseña</html>");
		textSetPassword.setBounds(30, 40, 290, 40);
		textSetPassword.setOpaque(true);
		textSetPassword.setBackground(new Color(225, 225, 225));
		textSetPassword.setForeground(Color.black);
		textSetPassword.setFont(textSetPassword.getFont().deriveFont((float) 14));
		panel.add(textSetPassword);
		
		JTextField newPassword = new JTextField();
		newPassword.setBounds(30, 100, 290, 40);
		newPassword.setOpaque(true);
		newPassword.setBackground(Color.white);
		newPassword.setForeground(Color.black);
		newPassword.setFont(newPassword.getFont().deriveFont((float) 14));
		panel.add(newPassword);
		
		JLabel textConfirmPassword = new JLabel("<html>Ingrese de nuevo la contraseña para confirmar</html>");
		textConfirmPassword.setBounds(30, 160, 290, 40);
		textConfirmPassword.setOpaque(true);
		textConfirmPassword.setBackground(new Color(225, 225, 225));
		textConfirmPassword.setForeground(Color.black);
		textConfirmPassword.setFont(textConfirmPassword.getFont().deriveFont((float) 14));
		panel.add(textConfirmPassword);
		
		JTextField confirmPassword = new JTextField();
		confirmPassword.setBounds(30, 220, 290, 40);
		confirmPassword.setOpaque(true);
		confirmPassword.setBackground(Color.white);
		confirmPassword.setForeground(Color.black);
		confirmPassword.setFont(confirmPassword.getFont().deriveFont((float) 14));
		panel.add(confirmPassword);
		
		JButton confirmChanges = new JButton("Confirmar cambios");
		confirmChanges.setBounds(30, 280, 290, 40);
		confirmChanges.setOpaque(true);
		confirmChanges.setBackground(new Color(0, 200, 50));
		confirmChanges.setForeground(Color.white);
		confirmChanges.setFont(confirmChanges.getFont().deriveFont((float) 14));
		panel.add(confirmChanges);
		confirmChanges.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if ((newPassword.getText().equals(confirmPassword.getText()) && !newPassword.getText().equals(""))) {
					script.updatePassword(currentEmail, newPassword.getText());
					mainPanel.remove(panel);
					mainPanel.revalidate();
					mainPanel.repaint();
					JPanel passwordChangedPanel = createPasswordChangedPanel();
					mainPanel.add(passwordChangedPanel);					
				} else {
					textSetPassword.setLocation(30, 100);
					newPassword.setLocation(30, 160);
					textConfirmPassword.setLocation(30, 220);
					confirmPassword.setLocation(30, 280);
					confirmChanges.setLocation(30, 340);
					
					JLabel message = new JLabel();
					message.setBounds(30, 40, 290, 40);
					message.setOpaque(true);
					message.setBackground(new Color(255, 80, 80));
					message.setForeground(Color.white);
					message.setFont(message.getFont().deriveFont((float) 14));
					message.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					message.setHorizontalAlignment(JLabel.CENTER);
					panel.add(message);
					panel.revalidate();
					panel.repaint();
					
					if (newPassword.getText().equals("")
							|| confirmPassword.getText().equals("")) {
						message.setText("<html>Rellene todos los campos.</html>");
					} else {
						message.setText("<html>Las contraseñas no coinciden.</html>");
					}
				}
			}
		});
		
		newPassword.addKeyListener(new KeyAdapter() {
			public void keyTyped (KeyEvent evt) {
				if (evt.getKeyChar() == '\n') {
					confirmChanges.doClick();
				}
			}
		});
		confirmPassword.addKeyListener(new KeyAdapter() {
			public void keyTyped (KeyEvent evt) {
				if (evt.getKeyChar() == '\n') {
					confirmChanges.doClick();
				}
			}
		});
		
    	return panel;
    }
    
    private JPanel createPasswordChangedPanel() {
    	JPanel panel = new JPanel();
    	panel.setBounds(509, 200, 350, 450);
		panel.setBackground(new Color(225, 225, 225));
		panel.setLayout(null);
    	
		JLabel okGreenLabel = new JLabel();
		ImageIcon okGreenIcon = new ImageIcon(getClass().getResource("/files/green-ok-icon.png"));
		okGreenLabel.setBounds(105, 40, 135, 135);
		okGreenLabel.setIcon(new ImageIcon(okGreenIcon.getImage().getScaledInstance(135, 135, Image.SCALE_SMOOTH)));
		okGreenLabel.setBorder(BorderFactory.createLineBorder(Color.green, 1, true));
		panel.add(okGreenLabel);
		
		JLabel message = new JLabel("<html>Su contraseña ha sido cambiada con exito.<br>"
				+ "Ya puede cerrar esta pestaña.</html>");
		message.setBounds(30, 200, 290, 70);
		message.setOpaque(true);
		message.setBackground(new Color(0, 200, 50));
		message.setForeground(Color.white);
		message.setFont(message.getFont().deriveFont((float) 14));
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setBorder(BorderFactory.createLineBorder(Color.green, 1));
		panel.add(message);
		
    	return panel;
    }
    
    /*private void createEmailTab(){
        emailTab = new JPanel();
        emailTab.setBackground(Color.white);
        emailTab.setLayout(null);
        
        //JLabel emailLogo = new JLabel();
    }*/
    
    private void createActionButtons() {
        JButton minimizeButton = new JButton();
        minimizeButton.setBounds(1216, 0, 50, 40);
        minimizeButton.setText("\uD83D\uDDD5");
        minimizeButton.setToolTipText("Minimizar");
        minimizeButton.setBackground(Color.black);
        minimizeButton.setForeground(Color.white);
        minimizeButton.setFont(minimizeButton.getFont().deriveFont((float)14));
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                setState(JFrame.ICONIFIED);
            }
        });
        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent evt){
                minimizeButton.setBackground(new Color(55,55,55));
            }
            public void mouseExited (MouseEvent evt){
                minimizeButton.setBackground(Color.black);
            }
        });
        container.add(minimizeButton);
        
        JButton maximizeButton = new JButton();
        maximizeButton.setBounds(1266, 0, 50, 40);
        maximizeButton.setText("\uD83D\uDDD7");
        maximizeButton.setToolTipText("Maximizar");
        maximizeButton.setBackground(Color.black);
        maximizeButton.setForeground(Color.white);
        maximizeButton.setFont(maximizeButton.getFont().deriveFont((float)14));
        maximizeButton.setFocusPainted(false);
        maximizeButton.setBorderPainted(false);
        maximizeButton.setEnabled(false);
        try {
			maximizeButton.setCursor(Cursor.getSystemCustomCursor("Invalid.32x32"));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
//        maximizeButton.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent evt){
//                if ( fullscreen ){
//                    maximizeButton.setText("\u25A1");
//                    fullscreen = false;
//                } else {
//                    maximizeButton.setText("\uD83D\uDDD7");
//                    fullscreen = true;
//                }
//            }
//        });
//        maximizeButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered (MouseEvent evt){
//                maximizeButton.setBackground(new Color(55,55,55));
//            }
//            public void mouseExited (MouseEvent evt){
//                maximizeButton.setBackground(Color.black);
//            }
//        });
        container.add(maximizeButton);
        
        JButton exitButton = new JButton();
        exitButton.setBounds(1316, 0, 50, 40);
        exitButton.setText("\u2715");
        exitButton.setToolTipText("Salir");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(exitButton.getFont().deriveFont((float)14));
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                System.exit(0);
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent evt){
                exitButton.setBackground(Color.red);
            }
            public void mouseExited (MouseEvent evt){
                exitButton.setBackground(Color.black);
            }
        });
        container.add(exitButton);        
    }

}