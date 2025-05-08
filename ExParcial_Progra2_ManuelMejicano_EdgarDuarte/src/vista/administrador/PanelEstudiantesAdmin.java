package vista.administrador;

import java.awt.Color;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import modelo.Estudiante;

public class PanelEstudiantesAdmin extends JPanel {
    VistaAdmin vistaAdministrador;

    public PanelEstudiantesAdmin(VistaAdmin vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
        initComponents();
    }

    public JPanel initComponents() {
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
        jDateChooser.setDateFormatString("dd/MM/yyyy");
        jDateChooser.setBounds(250, 200, 200, 30);
        panelEstudiantes.add(jDateChooser);

        JLabel jLabel5 = new JLabel("Carnet estudiantil: ");
        jLabel5.setBounds(40, 250, 200, 30);
        panelEstudiantes.add(jLabel5);

        JTextField textFieldCarnet = new JTextField();
        textFieldCarnet.setBounds(250, 250, 200, 30);
        panelEstudiantes.add(textFieldCarnet);

        JLabel label6 = new JLabel("Nacionalidad:");
        label6.setBounds(40, 300, 200, 30);
        panelEstudiantes.add(label6);

        JComboBox jComboBoxEstudiante = new JComboBox<>(new String[] { "Costarricense", "Nicaraguense", "Panameño",
                "Estadounidense", "Mexicano", "Hondureño", "Salvadoreño" });
        jComboBoxEstudiante.setBounds(250, 300, 200, 30);
        panelEstudiantes.add(jComboBoxEstudiante);

        JLabel label7 = new JLabel("Dirección:");
        label7.setBounds(40, 350, 200, 30);
        panelEstudiantes.add(label7);

        JComboBox jComboBoxDireccion = new JComboBox<>(new String[] { "Upala", "Aguas Claras", "San José de Upala", "Bijagua", "Delicias", "Dos Ríos", "Yolillal", "Canalete"});
        jComboBoxDireccion.setBounds(250, 350, 200, 30);
        panelEstudiantes.add(jComboBoxDireccion);

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
                    textFieldNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }
            LocalDate fechaN = jDateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            vistaAdministrador.controlador.getEstudiantes().add(new Estudiante(
                    textFieldNombre.getText(),
                    Integer.parseInt(textFieldID.getText()),
                    textFieldCarnet.getText(),
                    jComboBoxDireccion.getSelectedItem().toString(),
                    fechaN,
                    jComboBoxEstudiante.getSelectedItem().toString()));

            vistaAdministrador.generarTablaEstudiantes();
            textFieldNombre.setText(""); // Limpiar el campo de texto
            textFieldCarnet.setText("");
            textFieldID.setText("");
            jDateChooser.setDate(null);
            jComboBoxEstudiante.setSelectedIndex(-1);

        });

        panelEstudiantes.add(btnAgregar);

        // Creacion de tabla de estudiantes
        vistaAdministrador.tablaEstudiantes = new JTable();
        vistaAdministrador.tablaEstudiantes.setBounds(0, 0, 800, 400);
        JScrollPane scrollPane = new JScrollPane(vistaAdministrador.tablaEstudiantes);
        scrollPane.setBounds(500, 100, 800, 400);
        panelEstudiantes.add(scrollPane);
        vistaAdministrador.modeloTablaEstudiantes.setColumnIdentifiers(new String[] { "Nombre completo",
                "Identificación", "Fecha de nacimiento", "Nacionalidad", "Carnet estudiantil", "Dirección (Distrito)" });
        vistaAdministrador.tablaEstudiantes.setModel(vistaAdministrador.modeloTablaEstudiantes);
        JButton btnEliminar = new JButton("Eliminar Estudiante");
        btnEliminar.setBounds(1150, 550, 150, 30);
        btnEliminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnEliminar.setBackground(new Color(0xFFE0133C));
        btnEliminar.setForeground(Color.WHITE);
        panelEstudiantes.add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = vistaAdministrador.tablaEstudiantes.getSelectedRow();
            if (filaSeleccionada != -1) {
                vistaAdministrador.controlador.getEstudiantes().remove(filaSeleccionada);
                vistaAdministrador.modeloTablaEstudiantes.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente.");
                vistaAdministrador.generarTablaEstudiantes();
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila para eliminar");
            }

        });

        return panelEstudiantes;
    }
}
