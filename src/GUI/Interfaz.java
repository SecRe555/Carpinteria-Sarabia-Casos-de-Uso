package GUI;

//Importaciones
import backend.LoginScripts;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.Border;

/***
 * Clase que implementa la interfaz grafica del proyecto.
 * @author Daniel
 * @since 2.0
 */
public class Interfaz extends JFrame {
    //Scripsts usados en el programa
    LoginScripts script = new LoginScripts();
    //Contenedor y pesta\u00F1as
    private JPanel container;
    private JTabbedPaneCloseButton tabbedPane;
    private JPanel loginTab;
    private JPanel emailTab;
    private JPanel missPasswordTab;
    //loginTab
    private JPanel missPassword;
    private JPanel noEmailBody;
    private JPanel emailBody;
    private JLabel emailLabel;

    private String currentCodeKey;
    private String codeKey = "";
    private List<JLabel> emails     = new ArrayList<>();
    private List<JPanel> emailsText = new ArrayList<>();
    private int beforeIndex;
    private JScrollPane emailsSectionScroll;
    private JPanel emailsSectionPanel;

    // private boolean fullscreen;
    private boolean removeUserExampleText     = true;
    private boolean removePasswordExampleText = true;
    private boolean removeEmailExampleText    = true;
    private String currentEmail;
    private boolean validEmail;
    // private int tabCount;
    //Colores y bordes
    private final Color brownCarpentryColor = new Color(194, 102, 10);
    private final Color redErrorColor       = new Color(255, 80, 80);
    private final Border redBorder          = BorderFactory.createLineBorder
                                            (Color.red, 2);
    private final Color greenConfirmColor   = new Color(80, 255, 80);
    private final Color greenButtonColor    = new Color(0, 170, 50);
    private final Border greenBorder        = BorderFactory.createLineBorder
                                            (new Color(0, 255, 0), 2);
    private final Border blackBorder        = BorderFactory.createLineBorder
                                            (Color.black, 1);
    /**
     * Caracteres especiales usados:
     * \u00F3       = ó
     * \u00F1       = ñ
     * \uD83D\uDDD5 = Simbolo de minimizar (Ocultar ventana)
     * \uD83D\uDDD7 = Simbolo de minimizar (Hacer mas pequeña la ventana)
     * \u25A1       = Simbolo de maximizar 
     * \u2715       = Simbolo de salir.
     */

    /**
     * Crea toda la interfaz usada en la aplicacion.
     */
    public Interfaz() {
        initWindow();
        createContainer();
        createActionButtons();
        createJTabbedPane();
    }
    
    /**
     * Inicializa los parametros usados en el jFrame.
     */
    private void initWindow() {
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setTitle("Navegador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().
                getResource("/files/brave-icon.png")));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // fullscreen = true; //Variable no utilizada por el momento
        setUndecorated(true);
    }

    /**
     * Crea el panel usado para no sobreponer todo en el jFrame.
     */
    private void createContainer() {
        container = new JPanel();
        container.setLayout(null);
        container.setBackground(Color.black);
        this.getContentPane().add(container);
    }
    
    /**
     * Crea el panel con las pestañas.
     */
    private void createJTabbedPane() {
        UIManager.put("TabbedPane.selected", new Color(100, 100, 100));
        tabbedPane = new JTabbedPaneCloseButton();
        tabbedPane.setBounds(0, 0, 1366, 768);
        tabbedPane.setBackground(Color.black);
        tabbedPane.setForeground(Color.white);
        createLoginTab();
        createEmailTab();
        tabbedPane.addTab("Carpinteria Sarabia", new ImageIcon(getClass().
                getResource("/files/carpentry-icon.jpg")),loginTab, 
                "www.carpinteria-sarabia.com/login");
        tabbedPane.addTab("Email", new ImageIcon(getClass().
                getResource("/files/mail-icon.jpg")),emailTab, "www.email.com");
        container.add(tabbedPane);
    }
    
    /**
     * Crea la pestaña de login.
     */
    private void createLoginTab() {
        loginTab = new JPanel();
        loginTab.setBackground(new Color(255, 255, 255));
        loginTab.setLayout(null);

        JLabel imgLogin = new JLabel();
        imgLogin.setBounds(0, 0, 850, 748);
        imgLogin.setIcon(new ImageIcon(getClass().
                getResource("/files/img-login.jpg")));
        loginTab.add(imgLogin);

        JPanel panelTitleLogin = new JPanel();
        panelTitleLogin.setBounds(850, 0, 516, 200);
        panelTitleLogin.setBackground(brownCarpentryColor);
        panelTitleLogin.setLayout(null);
        loginTab.add(panelTitleLogin);

        JLabel titleLabel = new JLabel(
                "<html>Inicio de sesi\u00F3n<br>de empleados.</html>");
        titleLabel.setBounds(80, 40, 316, 85);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(null);
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setFont(titleLabel.getFont().deriveFont((float) 40));
        panelTitleLogin.add(titleLabel);

        JLabel aviso = new JLabel();
        aviso.setBounds(80, 150, 350, 40);
        aviso.setOpaque(true);
        aviso.setText("<html>* Este servicio esta reservado exclusivamente a"
                + "<br>aquellos empleados registrados</html>");
        aviso.setBackground(null);
        aviso.setForeground(new Color(255, 255, 255));
        aviso.setFont(aviso.getFont().deriveFont((float) 15));
        panelTitleLogin.add(aviso);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(950, 250, 300, 50);
        userLabel.setOpaque(true);
        userLabel.setBackground(null);
        userLabel.setForeground(new Color(100, 100, 100));
        userLabel.setFont(userLabel.getFont().deriveFont((float) 36));
        loginTab.add(userLabel);

        JTextField userTextField = new JTextField("example");
        userTextField.setBounds(950, 310, 300, 50);
        userTextField.setOpaque(true);
        userTextField.setBackground(Color.white);
        userTextField.setForeground(new Color(100, 100, 100, 200));
        userTextField.setFont(userLabel.getFont().deriveFont((float) 24));
        loginTab.add(userTextField);
        
        JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
        passwordLabel.setBounds(950, 380, 300, 50);
        passwordLabel.setOpaque(true);
        passwordLabel.setBackground(null);
        passwordLabel.setForeground(new Color(100, 100, 100));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont((float) 36));
        loginTab.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField("example");
        passwordField.setBounds(950, 440, 251, 50);
        passwordField.setOpaque(true);
        passwordField.setBackground(Color.white);
        passwordField.setForeground(new Color(100, 100, 100, 200));
        passwordField.setFont(passwordLabel.getFont().deriveFont((float) 24));
        loginTab.add(passwordField);

        JToggleButton viewPassword = new JToggleButton();
        viewPassword.setBounds(1200, 440, 50, 49);
        viewPassword.setIcon(new ImageIcon(getClass().
                getResource("/files/closed eye.png")));
        viewPassword.setBackground(Color.white);
        viewPassword.setForeground(new Color(100, 100, 100));
        viewPassword.setFont(viewPassword.getFont().deriveFont((float) 15));
        viewPassword.setFocusPainted(false);
        loginTab.add(viewPassword);

        JButton loginButton = new JButton();
        loginButton.setBounds(950, 550, 300, 50);
        loginButton.setText("Iniciar Sesi\u00F3n");
        loginButton.setBackground(brownCarpentryColor);
        loginButton.setForeground(Color.white);
        loginButton.setFont(loginButton.getFont().deriveFont((float) 24));
        loginButton.setFocusPainted(false);
        loginTab.add(loginButton);

        JButton passMissedButton = new JButton();
        passMissedButton.setBounds(985, 605, 225, 20);
        passMissedButton.setText("¿Olvidaste tu contrase\u00F1a?");
        passMissedButton.setBackground(null);
        passMissedButton.setForeground(brownCarpentryColor);
        passMissedButton.setFont(passMissedButton.getFont().
                deriveFont((float) 15));
        passMissedButton.setBorderPainted(false);
        passMissedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginTab.add(passMissedButton);

        JLabel underLineMissed = new JLabel(" ");
        underLineMissed.setBounds(1000, 625, 200, 3);
        underLineMissed.setOpaque(true);
        underLineMissed.setBackground(new Color(50, 50, 255));
        underLineMissed.setVisible(false);
        loginTab.add(underLineMissed);

        JLabel message = new JLabel();
        message.setBounds(950, 250, 290, 40);
        message.setOpaque(true);
        message.setVisible(false);
        message.setForeground(Color.white);
        message.setFont(message.getFont().deriveFont((float) 14));
        message.setHorizontalAlignment(JLabel.CENTER);
        loginTab.add(message);

        userTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (removeUserExampleText) {
                    userTextField.setText("");
                    userTextField.setForeground(Color.black);
                    removeUserExampleText = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userTextField.getText().equals("")) {
                    userTextField.setText("example");
                    userTextField.setForeground(new Color(100, 100, 100, 200));
                    removeUserExampleText = true;
                }
            }
        });
        userTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char charTyped = evt.getKeyChar();
                if (charTyped == '\n') {
                    loginButton.doClick();
                }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (removePasswordExampleText) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                    removePasswordExampleText = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().equals("") 
                        && !(viewPassword.isSelected())) {
                    passwordField.setText("example");
                    passwordField.setForeground(new Color(100, 100, 100, 200));
                    removePasswordExampleText = true;
                }
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char charTyped = evt.getKeyChar();
                if (charTyped == '\n') {
                    loginButton.doClick();
                }
            }
        });

        char passwordChar = passwordField.getEchoChar();
        viewPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (viewPassword.isSelected()) {
                    viewPassword.setIcon(new ImageIcon(getClass().
                            getResource("/files/open eye.png")));
                    if (removePasswordExampleText) {
                        passwordField.setText("");
                        removePasswordExampleText = false;
                    }
                    passwordField.setEchoChar((char) 0);
                    passwordField.setForeground(Color.black);
                } else {
                    viewPassword.setIcon(new ImageIcon(getClass().
                            getResource("/files/closed eye.png")));
                    passwordField.setEchoChar(passwordChar);
                    if (passwordField.getText().equals("")) {
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
                loginTab.revalidate();
                loginTab.repaint();
                if (removeUserExampleText || removePasswordExampleText) {
                    message.setBackground(redErrorColor);
                    message.setBorder(redBorder);
                    message.setText("Rellene todos los campos.");
                    message.setVisible(true);
                } else {
                    String user = userTextField.getText();
                    char[] passwordArray = passwordField.getPassword();
                    String password = "";
                    for (int i = 0; i < passwordArray.length; i++) {
                        password += passwordArray[i];
                    }
                    int isLogin;
                    isLogin = script.validateUser(user, password);
                    switch (isLogin) {
                        case 0:
                            message.setText("Login exitoso.");
                            message.setBackground(greenConfirmColor);
                            message.setBorder(greenBorder);
                            break;
                        case 1:
                            message.setBackground(redErrorColor);
                            message.setBorder(redBorder);
                            message.setText("Contrase\u00F1a incorrecta.");
                            break;
                        case -1:
                            message.setBackground(redErrorColor);
                            message.setBorder(redBorder);
                            message.setText("Usuario inexistente");
                            break;
                    }
                    message.setVisible(true);
                }
            }
        });

        passMissedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                passMissedButton.setForeground(new Color(50, 50, 255));
                underLineMissed.setVisible(true);
            }

            public void mouseExited(MouseEvent evt) {
                passMissedButton.setForeground(brownCarpentryColor);
                underLineMissed.setVisible(false);
            }
        });
        passMissedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                missPassword = createMissPasswordTab();
                tabbedPane.addTab("Contrase\u00F1a olvidada",
                        new ImageIcon(getClass().
                            getResource("/files/carpentry-icon.jpg")),
                            missPassword, 
                            "www.carpinteria-sarabia.com/password_reset");
                validEmail = false;
            }
        });
    }
    
    /**
     * Crear la pestaña para cambiar la contraseña.
     * @return el panel para poder inicializar la pestaña usando el constructor
     * @see {@code createLoginTab()}
     */
    private JPanel createMissPasswordTab() {
        missPasswordTab = new JPanel();
        missPasswordTab.setBackground(Color.white);
        missPasswordTab.setLayout(null);

        JLabel carpentryLogo = new JLabel();
        carpentryLogo.setBounds(658, 50, 50, 50);
        carpentryLogo.setIcon(new ImageIcon(getClass().
                getResource("/files/carpentry-icon1.jpg")));
        missPasswordTab.add(carpentryLogo);

        JLabel passwordMissedTitle = new JLabel("Cambiar contrase\u00F1a");
        passwordMissedTitle.setBounds(583, 125, 200, 50);
        passwordMissedTitle.setBackground(Color.white);
        passwordMissedTitle.setForeground(Color.black);
        passwordMissedTitle.setFont(passwordMissedTitle.getFont().
                deriveFont((float) 20));
        missPasswordTab.add(passwordMissedTitle);

        JPanel sendEmailPanel = createSendEmailPanel();
        missPasswordTab.add(sendEmailPanel);

        return missPasswordTab;
    }
    
    /**
     * Crea el formulario para introducir el correo a enviar el codigo de 
     * confirmación.
     * @return el panel que sera colocado en la pestaña de contraseña olvidada
     * @see {@code createMissPasswordTab()}
     */
    private JPanel createSendEmailPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(509, 200, 350, 450);
        panel.setLayout(null);
        panel.setBackground(new Color(225, 225, 225));
        missPasswordTab.add(panel);

        JLabel setEmail = new JLabel();
        setEmail.setBounds(30, 40, 290, 30);
        setEmail.setText(
            "<html>Introduzca su direccion de correo<br>electronico</html>");
        setEmail.setBackground(new Color(255, 255, 255, 0));
        setEmail.setForeground(Color.black);
        setEmail.setFont(setEmail.getFont().deriveFont((float) 14));
        panel.add(setEmail);

        JTextField emailTextField = new JTextField("example@mail.com");
        emailTextField.setBounds(30, 110, 290, 40);
        emailTextField.setBackground(Color.white);
        emailTextField.setForeground(new Color(100, 100, 100, 200));
        emailTextField.setFont(emailTextField.getFont().deriveFont((float) 16));
        emailTextField.setFocusable(false);
        panel.add(emailTextField);

        JButton sendEmail = new JButton("Enviar email de confirmacion");
        sendEmail.setBounds(30, 175, 290, 40);
        sendEmail.setBackground(greenButtonColor);
        sendEmail.setForeground(Color.white);
        sendEmail.setFont(sendEmail.getFont().deriveFont((float) 14));
        sendEmail.setFocusPainted(false);
        panel.add(sendEmail);

        JLabel message = new JLabel();
        message.setBounds(30, 40, 290, 40);
        message.setOpaque(true);
        message.setBackground(redErrorColor);
        message.setForeground(Color.white);
        message.setFont(message.getFont().deriveFont((float) 14));
        message.setBorder(redBorder);

        emailTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (evt.getKeyChar() == '\n') {
                    sendEmail.doClick();
                }
            }
        });
        emailTextField.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
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
            public void focusGained(FocusEvent e) {
                if (removeEmailExampleText) {
                    emailTextField.setText("");
                    emailTextField.setForeground(Color.black);
                    removeEmailExampleText = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailTextField.getText().equals("")) {
                    emailTextField.setText("example@mail.com");
                    emailTextField.setForeground(new Color(100, 100, 100, 200));
                    removeEmailExampleText = true;
                }
            }
        });

        sendEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validEmail = script.validateEmail(emailTextField.getText());
                if (validEmail) {
                    validEmail = false;
                    currentEmail = emailTextField.getText();
                    if (emails.isEmpty()) {
                        emailLabel = new JLabel(putEmailInfo());
                        emailLabel.setBounds(0, 0, 500, 100);
                        emailLabel.setOpaque(true);
                        emailLabel.setBackground(Color.white);
                        emailLabel.setFont(emailLabel.getFont().
                            deriveFont((float) 14));
                        emailLabel.setBorder(blackBorder);
                    } else {
                        int emailsSize = emails.size();
                        for (int i = 0; i < emails.size(); i++) {
                            JLabel anteriorLabel = emails.get(i);
                            anteriorLabel.setLocation(0, 
                                (int)(100 * emailsSize));
                            emailsSize--;
                        }
                        emailLabel = new JLabel(putEmailInfo());
                        emailLabel.setBounds(0, 0, 500, 100);
                        emailLabel.setOpaque(true);
                        emailLabel.setBackground(Color.white);
                        emailLabel.setFont(emailLabel.getFont().
                            deriveFont((float) 14));
                        emailLabel.setBorder(blackBorder);
                    }
                    createEmailBodyPanel();
                    emailLabel.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent evt) {
                            for (int i = 0; i < emails.size(); i++) {
                                if (emails.get(i).equals(evt.getComponent())) {
                                    if (noEmailBody.isDisplayable()) {
                                        emailTab.remove(noEmailBody);
                                    } else {
                                        emailTab.remove(emailsText.
                                            get(beforeIndex));
                                    }
                                    emailTab.add(emailsText.get(i));
                                    emailTab.revalidate();
                                    emailTab.repaint();
                                    beforeIndex = i;
                                }
                            }
                        }
                    });

                    emailsSectionPanel.add(emailLabel);
                    emails.add(emailLabel);

                    emailsSectionPanel.setSize(500, (int) 100 * emails.size() + 30);
                    emailsSectionPanel.setPreferredSize(new Dimension
                        (500, (int) 100 * emails.size() + 30));
                    emailsSectionPanel.revalidate();
                    emailsSectionPanel.repaint();
                    emailsSectionScroll.revalidate();

                    missPasswordTab.remove(panel);
                    JPanel restorePassword = createEnterKeyCodePanel();
                    missPasswordTab.add(restorePassword);
                    missPasswordTab.revalidate();
                    missPasswordTab.repaint();
                } else {
                    setEmail.setLocation(30, 110);
                    emailTextField.setLocation(30, 175);
                    sendEmail.setLocation(30, 245);
                    if (message.isDisplayable()) {
                        panel.remove(message);
                    }
                    if (removeEmailExampleText || emailTextField.getText().equals("")) {
                        message.setText(
                            "<html>Ingrese un email para continuar.</html>");
                    } else {
                        message.setText(
                            "<html>Email incorrecto.<br>"
                            + "Intente nuevamente</html>");
                    }
                    panel.add(message);
                    panel.revalidate();
                    panel.repaint();

                }

            }
        });

        return panel;
    }
    
    /**
     * Crear el panel que contendra el formulario para introducir el codigo de 
     * confirmacion.
     * @return el panel que reemplazara el formulario para introducir el email
     * donde se enviara el codigo de confirmacion
     * @see {@code createMissPasswordTab()}
     * @see {@code createSendEmailPanel()}
     */ 
    private JPanel createEnterKeyCodePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(509, 200, 350, 450);
        panel.setLayout(null);
        panel.setBackground(new Color(225, 225, 225));

        JLabel message = new JLabel();
        message.setBounds(30, 40, 290, 40);
        message.setOpaque(true);
        message.setText(
            "<html>Se ha enviado el codigo de confirmacion.</html>");
        message.setBackground(greenConfirmColor);
        message.setForeground(Color.white);
        message.setFont(message.getFont().deriveFont((float) 14));
        message.setBorder(greenBorder);
        panel.add(message);

        JLabel setCode = new JLabel();
        setCode.setBounds(30, 100, 290, 40);
        setCode.setOpaque(true);
        setCode.setText(
            "<html>Introduzca el codigo enviado a su correo.</html>");
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
        field1.setBorder(blackBorder);
        panel.add(field1);

        JTextField field2 = new JTextField();
        field2.setBounds(90, 170, 50, 50);
        field2.setOpaque(true);
        field2.setBackground(Color.white);
        field2.setForeground(Color.black);
        field2.setFont(field2.getFont().deriveFont((float) 24));
        field2.setHorizontalAlignment(JTextField.CENTER);
        field2.setBorder(blackBorder);
        panel.add(field2);

        JTextField field3 = new JTextField();
        field3.setBounds(150, 170, 50, 50);
        field3.setOpaque(true);
        field3.setBackground(Color.white);
        field3.setForeground(Color.black);
        field3.setFont(field3.getFont().deriveFont((float) 24));
        field3.setHorizontalAlignment(JTextField.CENTER);
        field3.setBorder(blackBorder);
        panel.add(field3);

        JTextField field4 = new JTextField();
        field4.setBounds(210, 170, 50, 50);
        field4.setOpaque(true);
        field4.setBackground(Color.white);
        field4.setForeground(Color.black);
        field4.setFont(field4.getFont().deriveFont((float) 24));
        field4.setHorizontalAlignment(JTextField.CENTER);
        field4.setBorder(blackBorder);
        panel.add(field4);

        JTextField field5 = new JTextField();
        field5.setBounds(270, 170, 50, 50);
        field5.setOpaque(true);
        field5.setBackground(Color.white);
        field5.setForeground(Color.black);
        field5.setFont(field5.getFont().deriveFont((float) 24));
        field5.setHorizontalAlignment(JTextField.CENTER);
        field5.setBorder(blackBorder);
        panel.add(field5);

        JButton confirmKeyCode = new JButton();
        confirmKeyCode.setBounds(30, 240, 290, 40);
        confirmKeyCode.setText("Confirmar codigo de confirmacion");
        confirmKeyCode.setBackground(greenButtonColor);
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
                } else if (field1.getText().length() >= 1) {
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
                } else if (field2.getText().length() >= 1) {
                    evt.consume();
                } else {
                    field3.requestFocus();
                }
            }

            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_DELETE 
                        || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        && field2.getText().equals("")) {
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
                        && field3.getText().equals("")) {
                    field2.requestFocus();
                } else if (!(Character.isDigit(charTyped))) {
                    Toolkit.getDefaultToolkit().beep();
                    evt.consume();
                } else if (field3.getText().length() >= 1) {
                    evt.consume();
                } else {
                    field4.requestFocus();
                }
            }

            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_DELETE 
                        || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        && field3.getText().equals("")) {
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
                        && field4.getText().equals("")) {
                    field3.requestFocus();
                } else if (!(Character.isDigit(charTyped))) {
                    Toolkit.getDefaultToolkit().beep();
                    evt.consume();
                } else if (field4.getText().length() >= 1) {
                    evt.consume();
                } else {
                    field5.requestFocus();
                }
            }

            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_DELETE 
                        || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        && field4.getText().equals("")) {
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
                        && field5.getText().equals("")) {
                    field4.requestFocus();
                } else if (!(Character.isDigit(charTyped))) {
                    Toolkit.getDefaultToolkit().beep();
                    evt.consume();
                } else if (field5.getText().length() >= 1) {
                    evt.consume();
                } else {
                    confirmKeyCode.requestFocus();
                }
            }

            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_DELETE 
                        || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        && field5.getText().equals("")) {
                    field4.setText("");
                    field4.requestFocus();
                }
            }
        });

        confirmKeyCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field1.getText().equals("") || field2.getText().equals("") 
                        || field3.getText().equals("")
                        || field4.getText().equals("") 
                        || field5.getText().equals("")) {
                    message.setText("<html>Rellene todos los campos.</html>");
                    message.setBackground(redErrorColor);
                    message.setForeground(Color.white);
                    message.setBorder(redBorder);
                    message.setHorizontalAlignment(JTextField.CENTER);
                } else {
                    codeKey = "";
                    codeKey += field1.getText() + field2.getText() 
                            + field3.getText() + field4.getText() 
                            + field5.getText();
                    if (codeKey.equals(currentCodeKey)) {
                        missPasswordTab.remove(panel);
                        missPasswordTab.revalidate();
                        missPasswordTab.repaint();
                        JPanel changePassword = createChangePasswordPanel();
                        missPasswordTab.add(changePassword);
                    } else {
                        message.setText(
                            "<html>Codigo de confirmacion incorrecto.</html>");
                        message.setBackground(redErrorColor);
                        message.setForeground(Color.white);
                        message.setBorder(redBorder);
                    }
                }
            }
        });
        confirmKeyCode.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (evt.getKeyChar() == '\n') {
                    confirmKeyCode.doClick();
                } else if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
                    field5.setText("");
                    field5.requestFocus();
                }
            }

            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DELETE 
                        || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    field5.setText("");
                    field5.requestFocus();
                }
            }
        });

        return panel;
    }
    
    /**
     * Crear el panel que contendra el formulario para actualizar la contraseña.
     * @return el panel que reemplazara el formulario para introducir el codigo 
     * de confirmacion
     * @see {@code createMissPasswordTab()}
     * @see {@code createEnterKeyCodePanel()}
     */
    private JPanel createChangePasswordPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(509, 200, 350, 450);
        panel.setLayout(null);
        panel.setBackground(new Color(225, 225, 225));

        JLabel textSetPassword = new JLabel();
        textSetPassword.setBounds(30, 40, 290, 40);
        textSetPassword.setOpaque(true);
        textSetPassword.setText(
            "<html>Ingrese la nueva contrase\u00F1a</html>");
        textSetPassword.setBackground(new Color(225, 225, 225));
        textSetPassword.setForeground(Color.black);
        textSetPassword.setFont(textSetPassword.getFont().
            deriveFont((float) 14));
        panel.add(textSetPassword);

        JTextField newPassword = new JTextField();
        newPassword.setBounds(30, 100, 290, 40);
        newPassword.setOpaque(true);
        newPassword.setBackground(Color.white);
        newPassword.setForeground(Color.black);
        newPassword.setFont(newPassword.getFont().deriveFont((float) 14));
        panel.add(newPassword);

        JLabel textConfirmPassword = new JLabel();
        textConfirmPassword.setBounds(30, 160, 290, 40);
        textConfirmPassword.setOpaque(true);
        textConfirmPassword.setText(
                "<html>"
                    + "Ingrese de nuevo la contrase\u00F1a para confirmar"
                + "</html>");
        textConfirmPassword.setBackground(new Color(225, 225, 225));
        textConfirmPassword.setForeground(Color.black);
        textConfirmPassword.setFont(textConfirmPassword.getFont().
            deriveFont((float) 14));
        panel.add(textConfirmPassword);

        JTextField confirmPassword = new JTextField();
        confirmPassword.setBounds(30, 220, 290, 40);
        confirmPassword.setOpaque(true);
        confirmPassword.setBackground(Color.white);
        confirmPassword.setForeground(Color.black);
        confirmPassword.setFont(confirmPassword.
            getFont().deriveFont((float) 14));
        panel.add(confirmPassword);

        JButton confirmChanges = new JButton("Confirmar cambios");
        confirmChanges.setBounds(30, 280, 290, 40);
        confirmChanges.setOpaque(true);
        confirmChanges.setBackground(greenButtonColor);
        confirmChanges.setForeground(Color.white);
        confirmChanges.setFont(confirmChanges.getFont().deriveFont((float) 14));
        panel.add(confirmChanges);

        JLabel message = new JLabel();
        message.setBounds(30, 40, 290, 40);
        message.setOpaque(true);
        message.setBackground(redErrorColor);
        message.setForeground(Color.white);
        message.setFont(message.getFont().deriveFont((float) 14));
        message.setBorder(redBorder);
        message.setHorizontalAlignment(JLabel.CENTER);

        newPassword.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (evt.getKeyChar() == '\n') {
                    confirmChanges.doClick();
                }
            }
        });

        confirmPassword.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (evt.getKeyChar() == '\n') {
                    confirmChanges.doClick();
                }
            }
        });

        confirmChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean validPasswords = (newPassword.getText().
                        equals(confirmPassword.getText())
                        && !newPassword.getText().equals("")) 
                        && (newPassword.getText().length() >= 8
                        && confirmPassword.getText().length() >= 8);
                if (validPasswords) {
                    script.updatePassword(currentEmail, newPassword.getText());
                    missPasswordTab.remove(panel);
                    missPasswordTab.revalidate();
                    missPasswordTab.repaint();
                    JPanel passwordChangedPanel = createPasswordChangedPanel();
                    missPasswordTab.add(passwordChangedPanel);
                } else {
                    textSetPassword.setLocation(30, 100);
                    newPassword.setLocation(30, 160);
                    textConfirmPassword.setLocation(30, 220);
                    confirmPassword.setLocation(30, 280);
                    confirmChanges.setLocation(30, 340);
                    if (newPassword.getText().equals("") 
                        || confirmPassword.getText().equals("")) {
                            message.setText(
                                    "<html>"
                                        + "Rellene todos los campos."
                                    + "</html>");
                    } else if (newPassword.getText().length() < 8
                            && confirmPassword.getText().length() < 8) {
                        message.setText(
                                "<html>"
                                    + "Las contrase\u00F1as deben ser de mas de "
                                    + "8 caracteres."
                                + "</html>");
                    } else {
                        message.setText(
                                "<html>"
                                    + "Las contrase\u00F1as no coinciden."
                                + "</html>");
                    }
                    if (message.isDisplayable()) {
                        panel.remove(message);
                    }
                    panel.add(message);
                    panel.revalidate();
                    panel.repaint();
                }
            }
        });

        return panel;
    }
    
    /**
     * Crear el panel que muestra la confirmacion de la contraseña actualizada.
     * @return el panel que reemplazara el formulario para actualizar la
     * contraseña
     * @see {@code createMissPasswordTab()}
     * @see {@code createChangePasswordPanel()}
     */
    private JPanel createPasswordChangedPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(509, 200, 350, 450);
        panel.setLayout(null);
        panel.setBackground(new Color(225, 225, 225));

        JLabel okGreenLabel = new JLabel();
        okGreenLabel.setBounds(105, 40, 135, 135);
        ImageIcon okGreenIcon = new ImageIcon(getClass().
            getResource("/files/green-ok-icon.png"));
        okGreenLabel.setIcon(new ImageIcon(okGreenIcon.getImage().
            getScaledInstance(135, 135, Image.SCALE_SMOOTH)));
        okGreenLabel.setBorder(greenBorder);
        panel.add(okGreenLabel);

        JLabel message = new JLabel(
                "<html>"
                    + "Su contrase\u00F1a ha sido cambiada con exito.<br>" 
                    + "Ya puede cerrar esta pesta\u00F1a."
                + "</html>");
        message.setBounds(30, 200, 290, 70);
        message.setOpaque(true);
        message.setBackground(greenConfirmColor);
        message.setForeground(Color.white);
        message.setFont(message.getFont().deriveFont((float) 14));
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBorder(greenBorder);
        panel.add(message);

        return panel;
    }
    
    /**
     * Crea la pestaña que simulará el servicio de email.
     */
    private void createEmailTab() {
        emailTab = new JPanel();
        emailTab.setBackground(Color.white);
        emailTab.setLayout(null);

        JLabel emailLogo = new JLabel();
        emailLogo.setBounds(0, 0, 50, 50);
        emailLogo.setOpaque(true);
        emailLogo.setIcon(new ImageIcon(getClass().
            getResource("/files/icon-email-50x50.jpg")));
        emailLogo.setBackground(Color.white);
        emailTab.add(emailLogo);

        JLabel emailTitle = new JLabel("email.com");
        emailTitle.setBounds(50, 0, 450, 50);
        emailTitle.setOpaque(true);
        emailTitle.setBackground(new Color(51, 153, 255));
        emailTitle.setForeground(Color.black);
        emailTitle.setFont(emailTitle.getFont().deriveFont((float) 18));
        emailTitle.setHorizontalAlignment(JLabel.CENTER);
        emailTab.add(emailTitle);

        JLabel emailUpperBar = new JLabel(" ");
        emailUpperBar.setBounds(500, 0, 866, 50);
        emailUpperBar.setOpaque(true);
        emailUpperBar.setBackground(new Color(51, 153, 255));
        emailUpperBar.setForeground(Color.black);
        emailUpperBar.setFont(emailTitle.getFont().deriveFont((float) 18));
        emailTab.add(emailUpperBar);

        emailsSectionScroll = new JScrollPane();
        emailsSectionScroll.setBounds(0, 50, 500, 718);
        emailsSectionScroll.setPreferredSize(new Dimension(500, 3000));
        emailsSectionScroll.setMinimumSize(new Dimension(500, 3000));
        emailsSectionScroll.setMaximumSize(new Dimension(500, 3000));
        emailsSectionScroll.setVerticalScrollBarPolicy(JScrollPane.
            VERTICAL_SCROLLBAR_AS_NEEDED);

        emailsSectionPanel = new JPanel();
        emailsSectionPanel.setBounds(0, 0, 500, 0);
        emailsSectionPanel.setPreferredSize(new Dimension(500, 0));
        emailsSectionPanel.setLayout(null);
        emailsSectionPanel.setBackground(Color.white);
        
        emailsSectionScroll.setViewportView(emailsSectionPanel);
        emailTab.add(emailsSectionScroll);

        noEmailBody = new JPanel();
        noEmailBody.setBounds(500, 0, 866, 768);
        noEmailBody.setLayout(null);
        noEmailBody.setBackground(new Color(50, 50, 50, 50));
        emailTab.add(noEmailBody);

        JLabel noEmailSelected = new JLabel();
        noEmailSelected.setBounds(233, 325, 400, 50);
        noEmailSelected.setOpaque(true);
        noEmailSelected.setText(
                "<html>"
                        + "Seleccione un elemento para leerlo"
                        + "         No hay nada seleccionado."
                + "</html>");
        noEmailSelected.setBackground(new Color(255, 255, 255));
        noEmailSelected.setForeground(Color.black);
        noEmailSelected.setFont(noEmailSelected.getFont().
            deriveFont((float) 14));
        noEmailSelected.setHorizontalAlignment(JLabel.CENTER);
        noEmailBody.add(noEmailSelected);
    }
    
    /**
     * Crea el contenedor del cuerpo del email.
     */
    private void createEmailBodyPanel() {
        emailBody = new JPanel();
        emailBody.setBounds(500, 50, 866, 718);
        emailBody.setLayout(null);
        emailBody.setBackground(Color.white);
        emailBody.setBorder(blackBorder);

        JLabel subject = new JLabel();
        subject.setBounds(0, 0, 866, 100);
        subject.setOpaque(true);
        subject.setText("Recuperacion de contrase\u00F1a.");
        subject.setBackground(new Color(0, 204, 102));
        subject.setForeground(Color.black);
        subject.setFont(subject.getFont().deriveFont((float) 24));
        subject.setBorder(greenBorder);
        emailBody.add(subject);

        JLabel sender = new JLabel();
        sender.setBounds(0, 100, 866, 25);
        sender.setOpaque(true);
        sender.setText("Equipo de soporte Carpinteria Sarabia");
        sender.setBackground(new Color(51, 255, 153));
        sender.setForeground(Color.black);
        sender.setFont(sender.getFont().deriveFont((float) 18));
        emailBody.add(sender);

        JLabel emailSender = new JLabel();
        emailSender.setBounds(0, 125, 866, 25);
        emailSender.setOpaque(true);
        emailSender.setText("support@sarabia.com.mx");
        emailSender.setBackground(new Color(51, 255, 153));
        emailSender.setForeground(Color.black);
        emailSender.setFont(emailSender.getFont().deriveFont((float) 18));
        emailBody.add(emailSender);

        String[] dateTime = getDateTime();
        JLabel date = new JLabel();
        date.setBounds(0, 150, 866, 25);
        date.setOpaque(true);
        date.setText(dateTime[0] + "     " + dateTime[1]);
        date.setBackground(new Color(51, 255, 153));
        date.setForeground(Color.black);
        date.setFont(date.getFont().deriveFont((float) 18));
        emailBody.add(date);

        JPanel borderEmailText = new JPanel();
        borderEmailText.setBounds(140, 225, 600, 400);
        borderEmailText.setLayout(null);
        borderEmailText.setBackground(Color.white);
        borderEmailText.setBorder(blackBorder);
        emailBody.add(borderEmailText);

        JLabel textEmail = new JLabel();
        textEmail.setBounds(50, 50, 500, 200);
        textEmail.setOpaque(true);
        String infoTextEmail = String.format("""
            <html>
                <p align="justify">Hola %s.<br>
                    Alguien esta intentando cambiar su contrase\u00F1a.
                    En caso de no ser usted ignore este correo.
                    Si desea cambiar su contrase\u00F1a introduzca
                    el siguiente codigo de confirmacion:
            </html>
            """, script.selectName(currentEmail));
        textEmail.setText(infoTextEmail);
        textEmail.setBackground(Color.white);
        textEmail.setForeground(Color.black);
        textEmail.setFont(textEmail.getFont().deriveFont((float) 24));
        textEmail.setVerticalAlignment(JLabel.TOP);
        borderEmailText.add(textEmail);

        JLabel codeKeyLabel = new JLabel();
        codeKeyLabel.setBounds(250, 255, 100, 50);
        codeKeyLabel.setOpaque(true);
        currentCodeKey = script.generateCodeKey();
        codeKeyLabel.setText(currentCodeKey);
        codeKeyLabel.setBackground(Color.white);
        codeKeyLabel.setForeground(Color.black);
        codeKeyLabel.setFont(codeKeyLabel.getFont().deriveFont((float) 24));
        codeKeyLabel.setHorizontalAlignment(JLabel.CENTER);
        codeKeyLabel.setBorder(blackBorder);
        borderEmailText.add(codeKeyLabel);

        emailsText.add(emailBody);
    }
    
    /**
     * Crear los botones minimizar, maximizar y cerrar.
     */
    private void createActionButtons() {
        JButton minimizeButton = new JButton();
        minimizeButton.setBounds(1216, 0, 50, 40);
        minimizeButton.setText("\uD83D\uDDD5");
        minimizeButton.setToolTipText("Minimizar");
        minimizeButton.setBackground(Color.black);
        minimizeButton.setForeground(Color.white);
        minimizeButton.setFont(minimizeButton.getFont().deriveFont((float) 14));
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBorderPainted(false);
        container.add(minimizeButton);

        JButton maximizeButton = new JButton();
        maximizeButton.setBounds(1266, 0, 50, 40);
        maximizeButton.setText("\uD83D\uDDD7");
        maximizeButton.setToolTipText("Maximizar");
        maximizeButton.setBackground(Color.black);
        maximizeButton.setForeground(Color.white);
        maximizeButton.setFont(maximizeButton.getFont().deriveFont((float) 14));
        maximizeButton.setFocusPainted(false);
        maximizeButton.setBorderPainted(false);
        maximizeButton.setEnabled(false);
        try {
            maximizeButton.setCursor(Cursor.
                getSystemCustomCursor("Invalid.32x32"));
        } catch (HeadlessException | AWTException e) {
            e.printStackTrace(System.out);
        }
        container.add(maximizeButton);

        JButton exitButton = new JButton();
        exitButton.setBounds(1316, 0, 50, 40);
        exitButton.setText("\u2715");
        exitButton.setToolTipText("Salir");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(exitButton.getFont().deriveFont((float) 14));
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        container.add(exitButton);

        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setState(JFrame.ICONIFIED);
            }
        });
        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                minimizeButton.setBackground(new Color(55, 55, 55));
            }

            public void mouseExited(MouseEvent evt) {
                minimizeButton.setBackground(Color.black);
            }
        });
        // No usado por el momento.
        //      maximizeButton.addActionListener(new ActionListener(){
        //		@Override
        //          public void actionPerformed(ActionEvent evt){
        //              if ( fullscreen ){
        //                  maximizeButton.setText("\u25A1");
        //                  fullscreen = false;
        //              } else {
        //                  maximizeButton.setText("\uD83D\uDDD7");
        //                  fullscreen = true;
        //              }
        //          }
        //      });
        //      maximizeButton.addMouseListener(new MouseAdapter() {
        //          @Override
        //          public void mouseEntered (MouseEvent evt){
        //              maximizeButton.setBackground(new Color(55,55,55));
        //          }
        //          public void mouseExited (MouseEvent evt){
        //              maximizeButton.setBackground(Color.black);
        //          }
        //      });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                exitButton.setBackground(Color.red);
            }

            public void mouseExited(MouseEvent evt) {
                exitButton.setBackground(Color.black);
            }
        });
    }
    
    /**
     * Obtiene la fecha y la hora actual al momento de llamar a la funcion.
     * @return un arreglo cuyo primer parametro es la fecha y el segundo es la
     * hora
     */
    private String[] getDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String day;
        if (localDateTime.getDayOfMonth() < 10) {
            day = "0" + localDateTime.getDayOfMonth();
        } else {
            day = "" + localDateTime.getDayOfMonth();
        }
        String month;
        if (localDateTime.getMonthValue() < 10) {
            month = "0" + localDateTime.getMonthValue();
        } else {
            month = "" + localDateTime.getDayOfMonth();
        }
        String year = "" + localDateTime.getYear();
        String date = day + "/" + month + "/" + year;

        String hour;
        if (localDateTime.getHour() < 10) {
            hour = "0" + localDateTime.getHour();
        } else {
            hour = "" + localDateTime.getHour();
        }
        String minutes;
        if (localDateTime.getMinute() < 10) {
            minutes = "0" + localDateTime.getMinute();
        } else {
            minutes = "" + localDateTime.getMinute();
        }
        String time = hour + ":" + minutes;

        String[] dateTime = {date, time};
        return dateTime;
    }
    
    /**
     * Genera el texto que tendra la informacion del email.
     * @return una cadena con el asunto, fecha, hora y el email del destinatario
     * @see {@code createSendEmailPanel()}
     * @see {@code getDateTime()}
     */
    private String putEmailInfo() {
        String[] dateTime = getDateTime();

        String emailInfo = String.format("""
            <html>
		<table width = 480>
                    <tbody>
			<tr>
                            <td>Equipo de soporte</td>
                            <td>&#9;</td>
                            <td>&#9;</td>
                            <td>%s</td>
			</tr>
			<tr>
                            <td>Cambio de contrase\u00F1a</td>
                            <td>&#9;</td>
                            <td>&#9;</td>
                            <td>%s</td>
			</tr>
			<tr>
                            <td>%s</td>
			</tr>
                    </tbody>
		</table>
            </html>
            """, dateTime[0], dateTime[1], currentEmail);
        return emailInfo;
    }
}
