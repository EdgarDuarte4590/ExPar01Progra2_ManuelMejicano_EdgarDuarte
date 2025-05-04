package vista;

import controlador.Controlador;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vista.administrador.VistaAdmin;
import vista.estudiantes.VistaEstudiante;
import vista.oficiales.Login;
import vista.oficiales.VistaOficiales;

public class MenuPrincipal extends JFrame{

    public VistaAdmin vistaAdmin;
    public VistaOficiales vistaOficiales;
    public VistaEstudiante vistaEstudiante;

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
        vistaOficiales = new VistaOficiales(this.controlador);  
        vistaEstudiante = new VistaEstudiante();

        JPanel panelFondo = new JPanel();
        panelFondo.setLayout(null); 
        panelFondo.setBounds(0, 0, 1366, 720);
        panelFondo.setBackground(new Color(0xFF0A1419)); 
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

        JLabel label2 = new JLabel("Seleccione su rol:");
        label2.setBounds(255, 100, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelPrincipal.add(label2); // Agregar el JLabel al panel principal

        JButton btnEstudiante = new JButton("Estudiante");
        btnEstudiante.setBounds(230, 150, 150, 30); // Establecer la posición y el tamaño del botón
        btnEstudiante.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente del botón
        panelPrincipal.add(btnEstudiante); 
        btnEstudiante.addActionListener(e -> {

            vistaEstudiante.mostrar(); // Mostrar la vista del estudiante al hacer clic en el botón
            //this.dispose(); // Cerrar la ventana actual
        });

        JButton btnGuarda = new JButton("Oficial de Seguridad");
        btnGuarda.setBounds(230, 200, 150, 30); // Establecer la posición y el tamaño del botón
        btnGuarda.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente del botón
        panelPrincipal.add(btnGuarda);
        btnGuarda.addActionListener(e -> {
            if (!controlador.isSesionIniciada()) {
                Login login = new Login(controlador); // Crear una nueva instancia de Login
                login.setVisible(true);
                login.setLocationRelativeTo(null); 
            }
           // vistaOficiales.mostrar(); // Mostrar la vista de los oficiales al hacer clic en el botón
            //this.dispose(); // Cerrar la ventana actual
        });

        JButton btnAdmin = new JButton("Administrador");
        btnAdmin.setBounds(230, 250, 150, 30); // Establecer la posición y el tamaño del botón
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente del botón
        panelPrincipal.add(btnAdmin);
        btnAdmin.addActionListener(e -> { 
            vistaAdmin.mostrar(); // Mostrar la vista del administrador al hacer clic en el botón
            //this.dispose(); // Cerrar la ventana actual
        });



        
    }

    
    
}

