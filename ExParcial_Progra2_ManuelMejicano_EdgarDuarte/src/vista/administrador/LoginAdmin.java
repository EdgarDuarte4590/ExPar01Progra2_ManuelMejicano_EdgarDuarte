package vista.administrador;

import controlador.Controlador;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginAdmin extends JFrame {
    Controlador controlador; // Instancia del controlador

    Container container;
    //ventana para loguearse
    public LoginAdmin(Controlador controlador) {
        this.controlador = controlador; // Inicializar el controlador
        setTitle("Inicio de sesión - Administrador");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        inicializarComponentes();
        this.setContentPane(container);

    }
    // se inicializa la ventana
    public void inicializarComponentes() {
        container = new Container();
        container.setLayout(null);
        container.setBounds(0, 0, 500, 400);

        JLabel label = new JLabel("Inicio de Sesión de Administrador");
        label.setBounds(125, 50, 250, 30);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        container.add(label);

        JLabel label2 = new JLabel("Escriba su nombre de Usuario:");
        label2.setBounds(170, 100, 200, 30);
        container.add(label2);

        JTextField username = new JTextField();
        username.setBounds(150, 130, 200, 30);
        container.add(username);
        username.setToolTipText("Escriba su nombre de Usuario");

        JLabel label3 = new JLabel("Escriba la Contraseña:");
        label3.setBounds(180, 170, 200, 30);
        container.add(label3);

        JPasswordField contrasena = new JPasswordField();
        contrasena.setBounds(150, 200, 200, 30);
        container.add(contrasena);
        contrasena.setToolTipText("Escriba la Contraseña");

        JCheckBox mostrarContrasena = new JCheckBox("Mostrar contraseña");
        mostrarContrasena.setBounds(175, 230, 200, 20);
        mostrarContrasena.addActionListener(e -> {
            if (mostrarContrasena.isSelected()) {
                contrasena.setEchoChar((char) 0);
            } else {
                contrasena.setEchoChar('\u2022');
            }
        });
        container.add(mostrarContrasena);
        contrasena.setEchoChar('\u2022');

        JButton btnIniciar = new JButton("Iniciar Sesión");
        btnIniciar.setIcon( new ImageIcon("src/resources/loginUser.png"));
        btnIniciar.setBounds(180, 275, 150, 30);
        btnIniciar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnIniciar.setBackground(new Color(0xFF020123));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBorderPainted(false);
        btnIniciar.addActionListener(e -> {
            String nombreUsuario = username.getText();
            String contra = new String(contrasena.getPassword());

            try {
                controlador.loginAdmin(nombreUsuario, contra);
                if (controlador.isSesionIniciadaAdmin()) {
                    System.out.println("Sesión iniciada correctamente.");
                    this.dispose();
                    controlador.menuPrincipal.vistaAdmin.setVisible(true);
                    controlador.menuPrincipal.vistaAdmin.generarTablaOficiales();
                    controlador.menuPrincipal.vistaAdmin.generarTablaEstudiantes();
                    controlador.menuPrincipal.vistaAdmin.setLocationRelativeTo(null);
                } else {
                    System.out.println("Error al iniciar sesión. Verifique sus credenciales.");
                }
            } catch (Exception x) {
                System.out.println("Error al intentar iniciar sesión: " + x.getMessage());
            }
        });
        container.add(btnIniciar);
    }

}
