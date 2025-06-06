package vista.administrador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;



// panel que se le muestra al administrador para registrar estudiantes
public class PanelEstudiantesAdmin extends JPanel {
    VistaAdmin vistaAdministrador;
    boolean editando=false;

    public PanelEstudiantesAdmin(VistaAdmin vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
        initComponents();
    }

    // inicializacion del panel
    public JPanel initComponents() {
        JPanel panelEstudiantes = new JPanel();
        panelEstudiantes.setLayout(null); // Establecer el diseño nulo para el panel de estudiantes
        panelEstudiantes.setBounds(0, 0, 1366, 720);

        JLabel label = new JLabel("Gestión de Estudiantes");
        label.setBounds(40, 50, 500, 30); // Establecer la posición y el tamaño del JLabel
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Establecer la fuente del JLabel
        panelEstudiantes.add(label);

        JLabel label2 = new JLabel("Primer Nombre:");
        label2.setBounds(40, 100, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label2);

        JTextField textFieldNombre1 = new JTextField();
        textFieldNombre1.setBounds(250, 100, 200, 30); // Establecer la posición y el tamaño del JTextField
        textFieldNombre1.setToolTipText("Ejemplo: Juan");
        panelEstudiantes.add(textFieldNombre1);

        JLabel label8 = new JLabel("Segundo Nombre:");
        label8.setBounds(40, 150, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label8);

        JTextField textFieldNombre2 = new JTextField();
        textFieldNombre2.setBounds(250, 150, 200, 30); // Establecer la posición y el tamaño del JTextField
        textFieldNombre2.setToolTipText("Ejemplo: Carlos");
        panelEstudiantes.add(textFieldNombre2);

        JLabel label9 = new JLabel("Primer Apellido:");
        label9.setBounds(40, 200, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label9);

        JTextField textFieldApellido1 = new JTextField();
        textFieldApellido1.setBounds(250, 200, 200, 30); // Establecer la posición y el tamaño del JTextField
        textFieldApellido1.setToolTipText("Ejemplo: Pérez");
        panelEstudiantes.add(textFieldApellido1);

        JLabel label10 = new JLabel("Segundo Apellido:");
        label10.setBounds(40, 250, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label10);

        JTextField textFieldApellido2 = new JTextField();
        textFieldApellido2.setBounds(250, 250, 200, 30); // Establecer la posición y el tamaño del JTextField
        textFieldApellido2.setToolTipText("Ejemplo: González");
        panelEstudiantes.add(textFieldApellido2);

        JLabel label3 = new JLabel("Número de identificación:");
        label3.setBounds(40, 300, 500, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label3);

        JTextField textFieldID = new JTextField();
        textFieldID.setBounds(250, 300, 200, 30); // Establecer la posición y el tamaño del JTextField
        textFieldID.setToolTipText("Ejemplo: 101110111");
        panelEstudiantes.add(textFieldID);

        JLabel label4 = new JLabel("Fecha de nacimiento:");
        label4.setBounds(40, 350, 200, 30); // Establecer la posición y el tamaño del JLabel
        panelEstudiantes.add(label4);

        JDateChooser jDateChooser = new JDateChooser();
        jDateChooser.setDateFormatString("dia/mes/año");
        jDateChooser.setBounds(250, 350, 200, 30);
        panelEstudiantes.add(jDateChooser);

        JLabel jLabel5 = new JLabel("Carnet estudiantil: ");
        jLabel5.setBounds(40, 400, 200, 30);
        panelEstudiantes.add(jLabel5);

        JTextField textFieldCarnet = new JTextField();
        textFieldCarnet.setBounds(250, 400, 200, 30);
        textFieldCarnet.setToolTipText("Ejemplo: E123456");
        panelEstudiantes.add(textFieldCarnet);

        JLabel label6 = new JLabel("Nacionalidad:");
        label6.setBounds(40, 450, 200, 30);
        panelEstudiantes.add(label6);
        // lista desplegable de la nacionalidad
        JComboBox jComboBoxEstudiante = new JComboBox<>(new String[] { "Costarricense", "Nicaraguense", "Panameño",
                "Estadounidense", "Mexicano", "Hondureño", "Salvadoreño" });
        jComboBoxEstudiante.setBounds(250, 450, 200, 30);
        panelEstudiantes.add(jComboBoxEstudiante);

        JLabel label7 = new JLabel("Dirección:");
        label7.setBounds(40, 500, 200, 30);
        panelEstudiantes.add(label7);

        JComboBox jComboBoxDireccion = new JComboBox<>(new String[] { "Upala", "Aguas Claras", "San José de Upala",
                "Bijagua", "Delicias", "Dos Ríos", "Yolillal", "Canalete" });
        jComboBoxDireccion.setBounds(250, 500, 200, 30);
        panelEstudiantes.add(jComboBoxDireccion);
        // boton para registrar al estudiante
// Botón “Guardar estudiante” (antes “Agregar estudiante”)
JButton btnAgregar = new JButton("Guardar");
btnAgregar.setToolTipText("Guardar o actualizar estudiante");
btnAgregar.setIcon(new ImageIcon("src/resources/icon_guardar.png")); 
btnAgregar.setBounds(290, 550, 160, 40);
btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
btnAgregar.setBackground(new Color(0xFF054FBE));
btnAgregar.setForeground(Color.WHITE);
btnAgregar.setBorderPainted(false);
btnAgregar.addActionListener(e -> {
    // Validación de campos obligatorios
    if (textFieldID == null || textFieldID.getText().isEmpty() ||
        jDateChooser.getDate() == null || textFieldCarnet.getText().isEmpty() ||
        textFieldNombre1.getText().isEmpty() || textFieldApellido1.getText().isEmpty() ||
        textFieldApellido2.getText().isEmpty()) {

        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        return;
    }

    // Convertir la fecha seleccionada a LocalDate
    LocalDate fechaN = jDateChooser.getDate()
        .toInstant()
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalDate();

    if (editando) {
        // Si estamos en modo edición, obtenemos la fila seleccionada
        int filaSeleccionada = vistaAdministrador.tablaEstudiantes.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Tomamos el carnet antiguo de esa fila para identificar el registro en la BD
            String carnetViejo = (String) vistaAdministrador.tablaEstudiantes.getValueAt(filaSeleccionada, 5);

            // Llamada al método de edición en el controlador
            vistaAdministrador.controlador.editarEstudiante(textFieldNombre1.getText(),
                textFieldNombre2.getText(),
                textFieldApellido1.getText(),
                textFieldApellido2.getText(),
                textFieldID.getText(),
                fechaN,
                textFieldCarnet.getText(),
                jComboBoxEstudiante.getSelectedItem().toString(),
                jComboBoxDireccion.getSelectedItem().toString(),
                carnetViejo
            );

            // Salimos de modo edición
            editando = false;
            btnAgregar.setText("Guardar");
            btnAgregar.setIcon(new ImageIcon("src/resources/icon_guardar.png")); 
            vistaAdministrador.tablaEstudiantes.setEnabled(true);
        }
    } else {
        // Modo “agregar nuevo estudiante”
        vistaAdministrador.agregarEstudiante(
            textFieldNombre1.getText(),
            textFieldNombre2.getText(),
            textFieldApellido1.getText(),
            textFieldApellido2.getText(),
            textFieldID.getText(),
            fechaN,
            textFieldCarnet.getText(),
            jComboBoxEstudiante.getSelectedItem().toString(),
            jComboBoxDireccion.getSelectedItem().toString()
        );
    }

    // Limpiar todos los campos
    textFieldNombre1.setText("");
    textFieldNombre2.setText("");
    textFieldApellido1.setText("");
    textFieldApellido2.setText("");
    textFieldCarnet.setText("");
    textFieldID.setText("");
    jDateChooser.setDate(null);
    jComboBoxEstudiante.setSelectedIndex(-1);
    jComboBoxDireccion.setSelectedIndex(-1);

    // Refrescar la tabla
    vistaAdministrador.generarTablaEstudiantes();
});

panelEstudiantes.add(btnAgregar);


// Botón “Editar Estudiante”
JButton btnEditar = new JButton("Editar");
btnEditar.setIcon(new ImageIcon("src/resources/icon_editar.png"));
btnEditar.setToolTipText("Editar estudiante seleccionado");
btnEditar.setBounds(900, 550, 125, 40);
btnEditar.setBackground(new Color(0xFF054FBE));
btnEditar.setForeground(Color.WHITE);
btnEditar.setBorderPainted(false);
btnEditar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
panelEstudiantes.add(btnEditar);

btnEditar.addActionListener(e -> {
    int filaSeleccionada = vistaAdministrador.tablaEstudiantes.getSelectedRow();
    if (filaSeleccionada != -1) {
        editando = true;

        // Obtenemos el carnet del estudiante seleccionado en la tabla
        String carnet = (String) vistaAdministrador.tablaEstudiantes.getValueAt(filaSeleccionada, 5);

        try (ResultSet rs = vistaAdministrador.controlador.statement.executeQuery(
                "SELECT * FROM estudiantes WHERE carnet = '" + carnet + "'")) {
            if (rs.next()) {
                String nombre1 = rs.getString("nombre1");
                String nombre2 = rs.getString("nombre2");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String cedula = rs.getString("cedula");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                String nacionalidad = rs.getString("nacionalidad");
                String direccion = rs.getString("direccion");


                // Rellenamos los campos de texto con los datos recuperados
                textFieldNombre1.setText(nombre1);
                textFieldNombre2.setText(nombre2);
                textFieldApellido1.setText(apellido1);
                textFieldApellido2.setText(apellido2);
                textFieldID.setText(cedula);
                jDateChooser.setDate(java.sql.Date.valueOf(fechaNacimiento));
                textFieldCarnet.setText(carnet);
                jComboBoxEstudiante.setSelectedItem(nacionalidad);
                jComboBoxDireccion.setSelectedItem(direccion);
                // Cambiamos el texto del botón guardar y deshabilitamos la tabla
                btnAgregar.setText("Actualizar");
                btnAgregar.setIcon(new ImageIcon("src/resources/icon_editar.png")); 
                vistaAdministrador.tablaEstudiantes.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el estudiante seleccionado.");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.");
    }
});


    JLabel labelBuscar=new JLabel("Buscar Estudiante:");
    labelBuscar.setBounds(500, 50, 200, 30);
    labelBuscar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
    panelEstudiantes.add(labelBuscar);


        JTextField textFieldBuscar = new JTextField();
        textFieldBuscar.setBounds(650, 50, 200, 30);
        textFieldBuscar.setToolTipText("Buscar por nombre, apellido, identificación o carnet");
       panelEstudiantes.add(textFieldBuscar);
               textFieldBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String busqueda = textFieldBuscar.getText();
                if (!busqueda.isEmpty()) {
                    consultarEstudiante(busqueda);
                } else {
                    try {
                        vistaAdministrador.generarTablaOficiales();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });


        // Creacion de tabla de estudiantes
        vistaAdministrador.tablaEstudiantes = new JTable();
        vistaAdministrador.tablaEstudiantes.setBounds(0, 0, 800, 400);
        JScrollPane scrollPane = new JScrollPane(vistaAdministrador.tablaEstudiantes);
        scrollPane.setBounds(500, 100, 800, 400);
        panelEstudiantes.add(scrollPane);
        vistaAdministrador.modeloTablaEstudiantes.setColumnIdentifiers(new String[] { "Nombre completo",
                "Identificación", "Fecha de nacimiento", "Edad", "Nacionalidad", "Carnet estudiantil",
                "Dirección (Distrito)" });
        vistaAdministrador.tablaEstudiantes.setModel(vistaAdministrador.modeloTablaEstudiantes);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setIcon(new ImageIcon("src/resources/icon_eliminar.png"));
        btnEliminar.setToolTipText("Eliminar estudiante seleccionado");
        btnEliminar.setBounds(1100, 550, 125, 40);
        btnEliminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnEliminar.setBackground(new Color(0xFFE0133C));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBorderPainted(false);
        panelEstudiantes.add(btnEliminar);
btnEliminar.addActionListener(e -> {
    int filaSeleccionada = vistaAdministrador.tablaEstudiantes.getSelectedRow();
    if (filaSeleccionada != -1) {
        String carnet = (String) vistaAdministrador.tablaEstudiantes.getValueAt(filaSeleccionada, 5);
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Estás seguro de que deseas eliminar al estudiante con carnet " + carnet + "?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION
        );
        if (confirmacion == JOptionPane.YES_OPTION) {
            vistaAdministrador.controlador.eliminarEstudiante(carnet);
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila para eliminar");
        System.out.println("fila seleccionada: " + filaSeleccionada);
    }
    vistaAdministrador.generarTablaEstudiantes();
});


        return panelEstudiantes;
    }

    public void consultarEstudiante(String busqueda) {
        try {
            ResultSet rs = vistaAdministrador.controlador.statement.executeQuery(
                "SELECT * FROM estudiantes WHERE nombre1 LIKE '%" + busqueda + "%' OR "
                + "nombre2 LIKE '%" + busqueda + "%' OR "
                + "apellido1 LIKE '%" + busqueda + "%' OR "
                + "apellido2 LIKE '%" + busqueda + "%' OR "
                + "cedula LIKE '%" + busqueda + "%' OR "
                + "carnet LIKE '%" + busqueda + "%'"
            );
            vistaAdministrador.modeloTablaEstudiantes.setRowCount(0);
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre1") + " " +
                                        rs.getString("nombre2") + " " +
                                        rs.getString("apellido1") + " " +
                                        rs.getString("apellido2");
                String cedula = rs.getString("cedula");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                int edad = LocalDate.now().getYear() - fechaNacimiento.getYear();
                String nacionalidad = rs.getString("nacionalidad");
                String carnet = rs.getString("carnet");
                String direccion = rs.getString("direccion");

                vistaAdministrador.modeloTablaEstudiantes.addRow(new Object[] {
                    nombreCompleto, cedula, fechaNacimiento, edad, nacionalidad, carnet, direccion
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
}
    }
}