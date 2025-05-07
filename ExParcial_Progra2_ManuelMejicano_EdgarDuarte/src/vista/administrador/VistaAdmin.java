package vista.administrador;

import java.awt.Color;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;
import modelo.Estudiante;
import modelo.Guarda;

public class VistaAdmin extends JFrame {
    Controlador controlador; // Instancia del controlador

    JTabbedPane tabbedPane = new JTabbedPane();
    JTable tablaOficiales = new JTable();
    JTable tablaEstudiantes = new JTable();

    DefaultTableModel modeloTablaOficiales = new DefaultTableModel();
    DefaultTableModel modeloTablaEstudiantes = new DefaultTableModel();

    PanelOficialesAdmin panelOficiales = new PanelOficialesAdmin(this);
  public  PanelEstudiantesAdmin panelEstudiantes = new PanelEstudiantesAdmin(this);

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
        //tabbedPane.add("Menu Administrativo", panelAdministradores());
        tabbedPane.add("Oficiales", panelOficiales.initComponents());
        tabbedPane.add("Estudiantes", panelEstudiantes.initComponents());

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

  

    public void agregarOficial(String nombre, String idAcceso, String contrasena, String telefono, int id) {
        // Lógica para agregar el oficial

        if (nombre.isEmpty() || idAcceso.isEmpty() || contrasena.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        Guarda nuevoOficial = new Guarda(nombre, id, idAcceso, contrasena, telefono);
        controlador.agregarOficial(nuevoOficial);
        generarTablaOficiales();
    }

    

    public void generarTablaEstudiantes() {
        modeloTablaEstudiantes.setRowCount(0);
        for (Estudiante estudiante : controlador.getEstudiantes()) {
            modeloTablaEstudiantes.addRow(new Object[] {
                    estudiante.getNombre(),
                    estudiante.getId(),
                    estudiante.getFechaNacimiento(),
                    estudiante.getNacionalidad(),
                    estudiante.getCarnet(),
                    estudiante.getDireccion()
            });

        }
    }

    public void generarTablaOficiales() {
        modeloTablaOficiales.setRowCount(0);
        for (Guarda guarda : controlador.getOficiales()) {
            modeloTablaOficiales.addRow(new Object[] {
                    guarda.getNombre(),
                    guarda.getId(),
                    guarda.getNumeroTelefono(),
                    guarda.getIDAcceso(),
                    guarda.getContrasena()
            });
        }
    }
}
