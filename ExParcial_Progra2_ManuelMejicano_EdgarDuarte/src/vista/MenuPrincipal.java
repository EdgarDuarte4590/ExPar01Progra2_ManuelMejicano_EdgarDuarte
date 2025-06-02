package vista;

// Clase MenuPrincipal: Ventana principal de la aplicación.
// Atributos: vistaAdmin, vistaOficiales (composición con otras vistas).
// Atributo controlador: Instancia del controlador para manejar eventos.

import controlador.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vista.administrador.LoginAdmin;
import vista.administrador.VistaAdmin;
import vista.oficiales.Login;
import vista.oficiales.VistaOficiales;

public class MenuPrincipal extends JFrame{

    public VistaAdmin vistaAdmin; //Composición 
    public VistaOficiales vistaOficiales; //Composición
    

    Controlador controlador; // Instancia del controlador

    public MenuPrincipal(Controlador controlador) {
        this.controlador = controlador; // Inicializar el controlador
        setTitle("Menu Principal");
        setSize(1366, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(null); 

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        vistaAdmin = new VistaAdmin(this.controlador);
        vistaOficiales = new VistaOficiales(controlador);  
        //vistaEstudiante = new VistaEstudiante(controlador);

        JPanel panelFondo = new JPanel();
        panelFondo.setLayout(null); 
        panelFondo.setBounds(0, 0, 1366, 720);
        panelFondo.setBackground(new Color(0xFF054FBE)); 
        super.add(panelFondo); 

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null); // Establecer el diseño nulo para el panel principal
        panelPrincipal.setBounds(383,150 ,600, 400); // Establecer el tamaño del panel principal
        panelPrincipal.setBackground(Color.WHITE); // Color de fondo transparente
        panelFondo.add(panelPrincipal); // Agregar el panel principal a la ventana

        JLabel label = new JLabel("Bienvenido al Sistema de Control de Ingresos y Salidas del CTP UPALA");
        label.setBounds(25, 50, 550, 30); // Establecer la posición y el tamaño del JLabel
        label.setFont(new Font("Arial", Font.BOLD, 15)); // Establecer la fuente del JLabel
        panelPrincipal.add(label); // Agregar el JLabel al panel principal

        // Cargar imagen como ícono
        ImageIcon icono = new ImageIcon("src/resources/logoCTPU.png"); // Ruta de la imagen
        JLabel labelIcon = new JLabel(icono);

        // Opcional: Escalar la imagen si es necesario
        Image imagenEscalada = icono.getImage().getScaledInstance(100, 130, Image.SCALE_SMOOTH);
        labelIcon.setIcon(new ImageIcon(imagenEscalada));
        labelIcon.setBounds(205, 210, 200, 200); // Establecer la posición y el tamaño del JLabel
        panelPrincipal.add(labelIcon); // Agregar el JLabel con la imagen al panel principal

        JLabel label2 = new JLabel("- Iniciar Sesión -");
        label2.setBounds(260, 100, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelPrincipal.add(label2); // Agregar el JLabel al panel principal

        JButton btnEstudiante = new JButton("Estudiante");
        btnEstudiante.setBounds(230, 150, 150, 30); // Establecer la posición y el tamaño del botón
        btnEstudiante.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente del botón
        //panelPrincipal.add(btnEstudiante); 
        btnEstudiante.addActionListener(e -> {

            //vistaEstudiante.mostrar(); // Mostrar la vista del estudiante al hacer clic en el botón
            //this.dispose(); // Cerrar la ventana actual
        });

        JButton btnGuarda = new JButton("Oficial de Seguridad");
        btnGuarda.setIcon(new ImageIcon("src/resources/icon_oficial.png"));
        btnGuarda.setBounds(205, 150, 200, 30); // Establecer la posición y el tamaño del botón
        btnGuarda.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente del botón
        btnGuarda.setBackground(new Color(0xFF054FBE)); // Establecer el color de fondo del botón
        btnGuarda.setForeground(Color.WHITE);
        panelPrincipal.add(btnGuarda);
        btnGuarda.addActionListener(e -> {
            if (!controlador.isSesionInciadaOficial()) {
                Login login = new Login(controlador); // Crear una nueva instancia de Login
                login.setVisible(true);
                login.setLocationRelativeTo(null); 
               
            } else{
                vistaOficiales.setVisible(true);
                vistaOficiales.generarJComboEstudiantes();
            }
           // vistaOficiales.mostrar(); // Mostrar la vista de los oficiales al hacer clic en el botón
            //this.dispose(); // Cerrar la ventana actual
        });

        JButton btnAdmin = new JButton("Administrador");
        btnAdmin.setIcon(new ImageIcon("src/resources/icon_admin.png"));
        btnAdmin.setBounds(205, 200, 200, 30); // Establecer la posición y el tamaño del botón
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente del botón
        btnAdmin.setBackground(new Color(0xFF054FBE));
        btnAdmin.setForeground(Color.WHITE);
        panelPrincipal.add(btnAdmin);
        btnAdmin.addActionListener(e -> { 
            if (!controlador.isSesionIniciadaAdmin()) {
                LoginAdmin login = new LoginAdmin(controlador);
                login.setVisible(true);
                login.setLocationRelativeTo(null);  
            } else {
                vistaAdmin.setVisible(true);
                vistaAdmin.generarTablaEstudiantes();
                try {
                    vistaAdmin.generarTablaOficiales();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });



        
    }

    
    
}

