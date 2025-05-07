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
        tabbedPane.add("Oficiales", panelOficiales());
        tabbedPane.add("Estudiantes", panelEstudiantes());

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
        String[] nombreColumnas = { "Nombre", "ID", "Teléfono", "ID Acceso", "Contraseña" };
        modeloTablaOficiales = new DefaultTableModel();
        modeloTablaOficiales.setColumnIdentifiers(nombreColumnas);
        tablaOficiales = new JTable();
        tablaOficiales.setModel(modeloTablaOficiales);

        JPanel panelOficiales = new JPanel();

        panelOficiales.setLayout(null); // Establecer el diseño nulo para el panel de oficiales
        panelOficiales.setBounds(0, 0, 1366, 720); // Establecer el tamaño del panel de oficiales

        JLabel label = new JLabel("Gestión de Oficiales");
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
        panelOficiales.add(textFieldContrasena); // Agregar el JTextField al panel de oficiales}
        textFieldContrasena.addActionListener(e -> {
            // Lógica para agregar el oficial
            if (textFieldID == null || textFieldID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;

            }
            agregarOficial(textFieldNombre.getText(), textFieldIDAcceso.getText(), textFieldContrasena.getText(),
                    textFieldTelefono.getText(), Integer.parseInt(textFieldID.getText()));
            textFieldNombre.setText(""); // Limpiar el campo de texto
            textFieldID.setText(""); // Limpiar el campo de texto
            textFieldTelefono.setText(""); // Limpiar el campo de texto
            textFieldIDAcceso.setText(""); // Limpiar el campo de texto
            textFieldContrasena.setText(""); // Limpiar el campo de texto
        });
        ;

        JButton btnAgregar = new JButton("Agregar Oficial");
        btnAgregar.setBounds(300, 350, 150, 30); // Establecer la posición y el tamaño del botón
        btnAgregar.setBackground(new Color(0xFF054FBE));
        btnAgregar.setForeground(Color.WHITE); // Establecer el color del texto del botón
        btnAgregar.setBorderPainted(false); // Quitar el borde del botón
        btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12)); // Establecer la fuente del botón
        panelOficiales.add(btnAgregar); // Agregar el botón al panel de oficiales
        btnAgregar.addActionListener(e -> {
            // Lógica para agregar el oficial
            if (textFieldID == null || textFieldID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;

            }
            agregarOficial(textFieldNombre.getText(), textFieldIDAcceso.getText(), textFieldContrasena.getText(),
                    textFieldTelefono.getText(), Integer.parseInt(textFieldID.getText()));
            textFieldNombre.setText(""); // Limpiar el campo de texto
            textFieldID.setText(""); // Limpiar el campo de texto
            textFieldTelefono.setText(""); // Limpiar el campo de texto
            textFieldIDAcceso.setText(""); // Limpiar el campo de texto
            textFieldContrasena.setText(""); // Limpiar el campo de texto
        });

        JScrollPane scrollPane = new JScrollPane(tablaOficiales);
        scrollPane.setBounds(500, 100, 800, 400);
        tablaOficiales.setBounds(0, 0, 800, 400);
        panelOficiales.add(scrollPane);

        JButton btnEliminar = new JButton("Eliminar Oficial");
        btnEliminar.setBounds(1150, 550, 150, 30);
        btnEliminar.setBackground(new Color(0xFFE0133C));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panelOficiales.add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaOficiales.getSelectedRow(); // Obtener la fila seleccionada
            if (filaSeleccionada != -1) { // Verificar si hay una fila seleccionada
                controlador.eliminarOficial(filaSeleccionada);
                System.out.println("fila seleccionada: " + filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila para eliminar");
                System.out.println("fila seleccionada: " + filaSeleccionada);
            }
            generarTablaOficiales();
        });

        return panelOficiales;
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

    public JPanel panelEstudiantes() {
        JPanel panelEstudiantes = new JPanel();
        panelEstudiantes.setLayout(null); // Establecer el diseño nulo para el panel de estudiantes
        panelEstudiantes.setBounds(0, 0, 1366, 720);

        JLabel label = new JLabel("Gestión de Estudiantes");
        label.setBounds(40, 50, 500, 30); // Establecer la posición y el tamaño del JLabel
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Establecer la fuente del JLabel
        panelEstudiantes.add(label);

        JLabel label2 = new JLabel("Nombre completo del estudiante:");
        label2.setBounds(40, 100, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label2);

        JTextField textFieldNombre = new JTextField();
        textFieldNombre.setBounds(250, 100, 200, 30); // Establecer la posición y el tamaño del JTextField
        panelEstudiantes.add(textFieldNombre);

        JLabel label3 = new JLabel("Número de identificación:");
        label3.setBounds(40, 150, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label3);

        JTextField textFieldID = new JTextField();
        textFieldID.setBounds(250, 150, 200, 30); // Establecer la posición y el tamaño del JTextField
        panelEstudiantes.add(textFieldID);

        JLabel label4 = new JLabel("Fecha de nacimiento:");
        label4.setBounds(40, 200, 200, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label4);

        JDateChooser jDateChooser = new JDateChooser();
        jDateChooser.setDateFormatString("dd/mm/yyyy");
        jDateChooser.setBounds(250, 200, 200, 30);
        panelEstudiantes.add(jDateChooser);

        JLabel jLabel5 = new JLabel("Carnet estudiantil: ");
        jLabel5.setBounds(40, 250, 200, 30  );
        panelEstudiantes.add(jLabel5);

        JTextField textFieldCarnet = new JTextField();
        textFieldCarnet.setBounds(250,250, 200,30);
        panelEstudiantes.add(textFieldCarnet);

        JLabel label6 = new JLabel("Nacionalidad:");
        label6.setBounds(40, 300, 200, 30);
        panelEstudiantes.add(label6);

        JComboBox jComboBoxEstudiante = new JComboBox<>(new String[]{"Costarricense", "Nicaraguense", "Panameño", "Estadounidense", "Mexicano", "Hondureño", "Salvadoreño"});
        jComboBoxEstudiante.setBounds(250, 300, 200, 30);
        panelEstudiantes.add(jComboBoxEstudiante);

        JLabel label7 = new JLabel("ID Acceso:");
        label7.setBounds(40, 350, 200, 30);
        panelEstudiantes.add(label7);

        JTextField textFieldIDAcceso = new JTextField();
        textFieldIDAcceso.setBounds(250,350, 200,30);
        panelEstudiantes.add(textFieldIDAcceso);

        JButton btnAgregar = new JButton("Guardar estudiante");
        btnAgregar.setBounds(300, 400, 150, 30);
        btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnAgregar.setBackground(new Color(0xFF054FBE));
        btnAgregar.setForeground(Color.WHITE); 
        btnAgregar.setBorderPainted(false);
        btnAgregar.addActionListener(e -> {
            // Lógica para agregar el estudiante
            if (textFieldID == null || textFieldID.getText().isEmpty() ||
                jDateChooser.getDate() == null || textFieldCarnet.getText().isEmpty() ||
                 textFieldIDAcceso.getText().isEmpty() || textFieldNombre.getText().isEmpty() ) { 
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }
            LocalDate fechaN = jDateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            controlador.getEstudiantes().add(new Estudiante(
                textFieldNombre.getText(),
                Integer.parseInt(textFieldID.getText()),
                textFieldCarnet.getText(),
                textFieldIDAcceso.getText(),
                fechaN,
                jComboBoxEstudiante.getSelectedItem().toString()
                ));

                generarTablaEstudiantes();
                textFieldNombre.setText(""); // Limpiar el campo de texto
                textFieldCarnet.setText("");
                textFieldIDAcceso.setText("");
                textFieldID.setText("");
                jDateChooser.setDate(null);
                jComboBoxEstudiante.setSelectedIndex(-1);

        });

        panelEstudiantes.add(btnAgregar);

        //Creacion de tabla de estudiantes
        tablaEstudiantes = new JTable();
        tablaEstudiantes.setBounds(0, 0, 800, 400);
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        scrollPane.setBounds(500, 100, 800, 400);
        panelEstudiantes.add(scrollPane); 
        modeloTablaEstudiantes.setColumnIdentifiers(new String[]{"Nombre completo", "Identificación","Fecha de nacimiento", "Nacionalidad", "Carnet estudiantil", "ID Acceso" });
        tablaEstudiantes.setModel(modeloTablaEstudiantes);
        JButton btnEliminar = new JButton("Eliminar Estudiante");
        btnEliminar.setBounds(1150, 550, 150, 30);
        panelEstudiantes.add(btnEliminar);
        btnEliminar.addActionListener(e->{
            int filaSeleccionada = tablaEstudiantes.getSelectedRow();
            if (filaSeleccionada!= -1) {
                controlador.getEstudiantes().remove(filaSeleccionada);
                modeloTablaEstudiantes.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente.");
                generarTablaEstudiantes();
            }else{
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila para eliminar");
            }
                
        });
    

        return panelEstudiantes;

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
                    estudiante.getCodigoAcceso()
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
