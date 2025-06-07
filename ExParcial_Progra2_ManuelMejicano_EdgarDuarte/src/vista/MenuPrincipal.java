package vista;

// Clase MenuPrincipal: Ventana principal de la aplicación.
// Atributos: vistaAdmin, vistaOficiales (composición con otras vistas).
// Atributo controlador: Instancia del controlador para manejar eventos.

import controlador.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class MenuPrincipal extends JFrame {

    public VistaAdmin vistaAdmin; // Composición
    public VistaOficiales vistaOficiales; // Composición
    public Controlador controlador; // Instancia del controlador

    public MenuPrincipal(Controlador controlador) throws SQLException {
        this.controlador = controlador; // Inicializar el controlador
        setTitle("Menu Principal");
        setSize(1366, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        inicializarComponentes();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (controlador.connection != null && !controlador.connection.isClosed()) {
                        controlador.connection.close(); // Cierra la conexión si está abierta
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                System.exit(0); // Cierra la aplicación
            }
        });
    }

    private void inicializarComponentes() throws SQLException {
        vistaAdmin = new VistaAdmin(this.controlador);
        vistaOficiales = new VistaOficiales(controlador);

        JPanel panelFondo = new JPanel();
        panelFondo.setLayout(null);
        panelFondo.setBounds(0, 0, 1366, 720);
        panelFondo.setBackground(new Color(0xFF054FBE));
        super.add(panelFondo);

        // Use the custom RoundedPanel instead of JPanel
        RoundedPanel panelPrincipal = new RoundedPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBounds(283, 150, 800, 500);
        panelPrincipal.setBackground(Color.WHITE);
        panelFondo.add(panelPrincipal);

        JLabel label = new JLabel("Bienvenido al Sistema de Control de Ingresos y Salidas del CTP UPALA");
        label.setBounds(130, 50, 550, 30);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        panelPrincipal.add(label);

        ImageIcon icono = new ImageIcon("src/resources/logoCTPU.png");
        JLabel labelIcon = new JLabel(icono);
        Image imagenEscalada = icono.getImage().getScaledInstance(100, 130, Image.SCALE_SMOOTH);
        labelIcon.setIcon(new ImageIcon(imagenEscalada));
        labelIcon.setBounds(300, 310, 200, 200);
        panelPrincipal.add(labelIcon);

        JLabel label2 = new JLabel("MENU PRINCIPAL");
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        label2.setForeground(new Color(0xFF054FBE));
        label2.setBounds(325, 100, 200, 30);
        panelPrincipal.add(label2);

        JButton btnGuarda = new JButton("Oficial de Seguridad");
        btnGuarda.setIcon(new ImageIcon("src/resources/icon_oficial.png"));
        btnGuarda.setBounds(300, 150, 200, 30);
        btnGuarda.setFont(new Font("Arial", Font.BOLD, 12));
        btnGuarda.setBackground(new Color(0xFF054FBE));
        btnGuarda.setForeground(Color.WHITE);
        panelPrincipal.add(btnGuarda);
        btnGuarda.addActionListener(e -> {
            if (!controlador.isSesionInciadaOficial()) {
                Login login = new Login(controlador);
                login.setVisible(true);
                login.setLocationRelativeTo(null);
            } else {
                vistaOficiales.setVisible(true);
                
                    vistaOficiales.generarJComboEstudiantes2();
             
            }
        });

        JButton btnAdmin = new JButton("Administrador");
        btnAdmin.setIcon(new ImageIcon("src/resources/icon_admin.png"));
        btnAdmin.setBounds(300, 200, 200, 30);
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 12));
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
                    e1.printStackTrace();
                }
            }
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.setIcon(new ImageIcon("src/resources/icon_salir.png"));
        btnSalir.setBounds(300, 250, 200, 30);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalir.setBackground(new Color(0xFF054FBE));
        btnSalir.setForeground(Color.WHITE);
        panelPrincipal.add(btnSalir);
        btnSalir.addActionListener(e -> {
            int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea salir?", "Confirmar Salida", javax.swing.JOptionPane.YES_NO_OPTION);
            if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    if (controlador.connection != null && !controlador.connection.isClosed()) {
                        controlador.connection.close(); // Cierra la conexión si está abierta
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                System.exit(0); // Cierra la aplicación
            }
        });

        JButton btnSporte = new JButton("Derechos de Autor");
        btnSporte.setIcon(new ImageIcon("src/resources/icon_derechos.png"));
        btnSporte.setBounds(300, 300, 200, 30);
        btnSporte.setFont(new Font("Arial", Font.BOLD, 12));
        btnSporte.setBackground(new Color(0xFF054FBE));
        btnSporte.setForeground(Color.WHITE);
        panelPrincipal.add(btnSporte);
        btnSporte.addActionListener(e -> {
           SoporteFrame soporteFrame = new SoporteFrame();
            soporteFrame.setVisible(true);
            soporteFrame.setLocationRelativeTo(null);
        });
    }

    
    class RoundedPanel extends JPanel {
        int cornerRadius = 25; 

        public RoundedPanel() {
            setOpaque(false); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }

    
    }
}