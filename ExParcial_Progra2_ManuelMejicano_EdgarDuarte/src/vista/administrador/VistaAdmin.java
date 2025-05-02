package vista.administrador;

import java.security.Guard;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.Guarda;

public class VistaAdmin extends JFrame{
    Controlador controlador; // Instancia del controlador

    JTabbedPane tabbedPane = new JTabbedPane();
    
        public VistaAdmin(Controlador controlador) {
            this.controlador = controlador; 
            setTitle("Vista Administrador");
            setSize(1366, 720);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null); 
            setLayout(null); 
            
            inicializarComponentes();
        }

        public void mostrar() {
            setVisible(true);
            
        }
    
        private void inicializarComponentes() {
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setBounds(0, 0, 1366, 720); // Establecer el tamaño del JTabbedPane
            super.add(tabbedPane); // Agregar el JTabbedPane a la ventana
           tabbedPane.add("Menu Administrativo",panelAdministradores()) ;
              tabbedPane.add("Oficiales", panelOficiales()); // Agregar el panel de oficiales al JTabbedPane

        }
        
        public JPanel panelAdministradores() {
            JPanel panelAdministradores = new JPanel();
            panelAdministradores.setLayout(null); // Establecer el diseño nulo para el panel de administradores
            panelAdministradores.setBounds(0, 0, 1366, 720); // Establecer el tamaño del panel de administradores
            
            JLabel label = new JLabel("Bienvenido Admin");
            label.setBounds(40, 50, 500, 30); // Establecer la posición y el tamaño del JLabel
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Establecer la fuente del JLabel
            panelAdministradores.add(label); 


            return panelAdministradores;
        }

        public JPanel panelOficiales() {
            JPanel panelOficiales = new JPanel();
            panelOficiales.setLayout(null); // Establecer el diseño nulo para el panel de oficiales
            panelOficiales.setBounds(0, 0, 1366, 720); // Establecer el tamaño del panel de oficiales
            
            JLabel label = new JLabel("Agrega y visualiza los oficiales");
            label.setBounds(40, 50, 500, 30); // Establecer la posición y el tamaño del JLabel
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Establecer la fuente del JLabel
            panelOficiales.add(label); 

            JLabel label2 = new JLabel("Nombre completo de Oficial:");
            label2.setBounds(40, 100, 500, 30); 
            panelOficiales.add(label2); // Agregar el JLabel al panel de oficiales

            JTextField textFieldNombre = new JTextField();
            textFieldNombre.setBounds(250, 100, 200, 30); // Establecer la posición y el tamaño del JTextField
            panelOficiales.add(textFieldNombre); // Agregar el JTextField al panel de oficiales

            JLabel label3 = new JLabel("Número de identificación:");
            label3.setBounds(40, 150, 500, 30);
            panelOficiales.add(label3); // Agregar el JLabel al panel de oficiales

            JTextField textFieldID = new JTextField();
            textFieldID.setBounds(250, 150, 200, 30); // Establecer la posición y el tamaño del JTextField
            panelOficiales.add(textFieldID); // Agregar el JTextField al panel de oficiales

            JTextField textFieldTelefono = new JTextField();
            textFieldTelefono.setBounds(250, 200, 200, 30); // Establecer la posición y el tamaño del JTextField
            panelOficiales.add(textFieldTelefono); // Agregar el JTextField al panel de oficiales

            JLabel label4 = new JLabel("Número de teléfono:");
            label4.setBounds(40, 200, 500, 30); // Establecer la posición y el tamaño del JLabel
            panelOficiales.add(label4); // Agregar el JLabel al panel de oficiales

            JLabel label5 = new JLabel("ID Acceso:");
            label5.setBounds(40, 250, 500, 30); // Establecer la posición y el tamaño del JLabel
            panelOficiales.add(label5); // Agregar el JLabel al panel de oficiales

            JTextField textFieldIDAcceso = new JTextField();
            textFieldIDAcceso.setBounds(250, 250, 200, 30); // Establecer la posición y el tamaño del JTextField
            panelOficiales.add(textFieldIDAcceso); // Agregar el JTextField al panel de oficiales


            JLabel label6 = new JLabel("Contraseña:");
            label6.setBounds(40, 300, 500, 30); // Establecer la posición y el tamaño del JLabel
            panelOficiales.add(label6); // Agregar el JLabel al panel de oficiales

            JTextField textFieldContrasena = new JTextField();
            textFieldContrasena.setBounds(250, 300, 200, 30); // Establecer la posición y el tamaño del JTextField
            panelOficiales.add(textFieldContrasena); // Agregar el JTextField al panel de oficiales

            JButton btnAgregar = new JButton("Agregar Oficial");
            btnAgregar.setBounds(250, 350, 150, 30); // Establecer la posición y el tamaño del botón
            btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12)); // Establecer la fuente del botón
            panelOficiales.add(btnAgregar); // Agregar el botón al panel de oficiales
            btnAgregar.addActionListener(e -> {
                // Lógica para agregar el oficial
                String nombre = textFieldNombre.getText();
                int id = Integer.parseInt(textFieldID.getText());
                int telefono = Integer.parseInt(textFieldTelefono.getText());
                int idAcceso = Integer.parseInt(textFieldIDAcceso.getText());
                String contrasena = textFieldContrasena.getText();

                // Aquí puedes llamar al controlador para agregar el oficial
                Guarda nuevoOficial = new Guarda(nombre, id, idAcceso, contrasena, telefono);
                controlador.agregarOficial(nuevoOficial);
                
            });
            return panelOficiales;
        }
}
