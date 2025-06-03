package vista.administrador;

import com.sun.source.tree.WhileLoopTree;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public PanelEstudiantesAdmin panelEstudiantes = new PanelEstudiantesAdmin(this);

    public VistaAdmin(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Vista Administrador");
        setSize(1366, 720);
        setLocationRelativeTo(null);
        setLayout(null);

        inicializarComponentes();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mostrarDialogoCerrar();
            }
        });
    }

    public void mostrar() {
        setVisible(true);

    }

    // inicializacion de el tabbedpane que contendra los paneles de estudiantes y
    // oficilaes
    private void inicializarComponentes() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1366, 720); // Establecer el tamaño del JTabbedPane
        super.add(tabbedPane); // Agregar el JTabbedPane a la ventana
        // tabbedPane.add("Menu Administrativo", panelAdministradores());
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

    private void mostrarDialogoCerrar() {
        String[] opciones = { "Cerrar sesión", "Salir", "Cancelar" };
        int opcion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cerrar sesión?",
                "Confirmación de salida",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        switch (opcion) {
            case 0: // Cerrar sesión
                controlador.setSesionIniciadaAdmin(false);
                ;
                this.dispose();
                break;
            case 1: // Salir
                this.dispose(); // Cerrar la aplicación
                break;
            default: // Cancelar o cerrar diálogo
                // No hace nada, permanece en la aplicación
                break;
        }
    }

    public int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return 0;
        }
        LocalDate fechaActual = LocalDate.now();
        int edad = fechaActual.getYear() - fechaNacimiento.getYear();
        if (fechaActual.getDayOfYear() < fechaNacimiento.getDayOfYear()) {
            edad--;
        }
        if (edad < 0) {
            return 0; 
            
        }
        return edad;

    }

    public void agregarEstudiante(String nombre1, String nombre2, String apellido1, String apellido2,
            String cedula, LocalDate fechaNacimiento, String carnet, String nacionalidad, String direccion) {
        // Lógica para agregar el estudiante

        if (cedula.isEmpty() || carnet.isEmpty() || nombre1.isEmpty() || apellido1.isEmpty()
                || apellido2.isEmpty() || fechaNacimiento == null || nacionalidad.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        java.sql.Date sqlFechaNacimiento = java.sql.Date.valueOf(fechaNacimiento);
        int edad = calcularEdad(fechaNacimiento);
        if (edad < 0) {
            JOptionPane.showMessageDialog(null, "Fecha de nacimiento no válida.");
            return;
        }

        String SQL = "INSERT INTO estudiantes (nombre1, nombre2, apellido1, apellido2, cedula, fechaNacimiento, carnet, nacionalidad, direccion, edad) VALUES ('"
                + nombre1 + "', '" + nombre2 + "', '" + apellido1 + "', '" + apellido2 + "', '" + cedula + "', '"
                + sqlFechaNacimiento + "', '" + carnet + "', '" + nacionalidad + "', '" + direccion + "', '" + edad + "' )";

        try {
            controlador.statement.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Estudiante agregado correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar estudiante: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al agregar estudiante: " + e.getMessage());
        }

        generarTablaEstudiantes();
    }

    public void agregarOficial(String nombre1,String nombre2,String apellido1,String apellido2, String nombreUsuario, String contrasena, String telefono, String id)
            throws SQLException {
        // Lógica para agregar el oficial
<<<<<<< HEAD
        if (nombre1.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty() || nombreUsuario.isEmpty()
                || contrasena.isEmpty() || telefono.isEmpty() || id.isEmpty()) {
=======

        if (id.isEmpty() || nombre1.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty()
                || nombreUsuario.isEmpty() || contrasena.isEmpty() || telefono.isEmpty()) {
>>>>>>> e80ab151dfb0d79fc5014ec66df70f487f24b2ce
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }
        
        controlador.editarOficial(nombre1, nombre2, apellido1, apellido2, telefono, nombreUsuario, contrasena,telefono, id);

<<<<<<< HEAD
=======
        controlador.agregarOficial(nombre1, nombre2,apellido1, apellido2, nombreUsuario, contrasena, telefono, id);
>>>>>>> e80ab151dfb0d79fc5014ec66df70f487f24b2ce

        generarTablaOficiales();
    }

    // carga la tabla de estudiantes cada que se agrega o elimina
    public void generarTablaEstudiantes() {

        modeloTablaEstudiantes.setRowCount(0);
        String SQL = "SELECT * FROM estudiantes";
        try {
            ResultSet rs = controlador.statement.executeQuery(SQL);

            while (rs.next()) {
                String nombre1 = rs.getString("nombre1");
                String nombre2 = rs.getString("nombre2");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String cedula = rs.getString("cedula");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                String carnet = rs.getString("carnet");
                String nacionalidad = rs.getString("nacionalidad");
                String direccion = rs.getString("direccion");

                // Concatenar nombres y apellidos
                String nombreCompleto;
                if (nombre2 != null && !nombre2.isEmpty()) {
                    nombreCompleto = nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
                } else {
                    nombreCompleto = nombre1 + " " + apellido1 + " " + apellido2;
                }

                modeloTablaEstudiantes.addRow(new Object[] {
                        nombreCompleto, cedula, fechaNacimiento,calcularEdad(fechaNacimiento), nacionalidad, carnet, direccion
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los estudiantes: " + e.getMessage());
            return;
        }
        // for (Estudiante estudiante : controlador.getEstudiantes()) {
        //     modeloTablaEstudiantes.addRow(new Object[] {
        //             estudiante.getNombre(),
        //             estudiante.getId(),
        //             estudiante.getFechaNacimiento(),
        //             estudiante.getNacionalidad(),
        //             estudiante.getCarnet(),
        //             estudiante.getDireccion()
        //     });

        // }
    }

    // carga la tabla de oficiales cada que se agrega o elimina
    public void generarTablaOficiales() throws SQLException {
        modeloTablaOficiales.setRowCount(0);
        ResultSet rs = controlador.statement.executeQuery("SELECT * FROM usuarios WHERE tipoUsuario='Guarda'");
        while (rs.next()) {
            String nc = null;
            String nombre1 = rs.getString("nombre1");
            String nombre2 = rs.getString("nombre2");
            String apellido1 = rs.getString("apellido1");
            String apellido2 = rs.getString("apellido2");
            String telefono = rs.getString("numeroTelefono");
            String nombreUsuario = rs.getString("nombreUsuario");
            String contrasena = rs.getString("contraseña");
            String id = rs.getString("cedula");

            if (nombre2 != null && !nombre2.isEmpty()) {
                nc = nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
            } else {
                nc = nombre1 + " " + apellido1 + " " + apellido2;
            }
            modeloTablaOficiales.addRow(new Object[] {
                    nc, id, telefono, nombreUsuario, contrasena
            });
        }

    }
}
