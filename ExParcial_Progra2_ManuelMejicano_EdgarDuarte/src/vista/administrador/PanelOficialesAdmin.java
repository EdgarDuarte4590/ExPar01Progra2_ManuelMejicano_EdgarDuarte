package vista.administrador;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelOficialesAdmin  extends JPanel{
    VistaAdmin vistaAdministrador;
    
    public PanelOficialesAdmin(VistaAdmin vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
        initComponents();
    }
    //inicializacion del panel de ofiioales  donde se registran los oficiaes 
    public JPanel initComponents() {
        String[] nombreColumnas = { "Nombre", "ID", "Teléfono", "ID Acceso", "Contraseña" };
        vistaAdministrador.modeloTablaOficiales = new DefaultTableModel();
        vistaAdministrador.modeloTablaOficiales.setColumnIdentifiers(nombreColumnas);
        vistaAdministrador.tablaOficiales = new JTable();
        vistaAdministrador.tablaOficiales.setModel(vistaAdministrador.modeloTablaOficiales);

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
            //agregar al oficial al arraylist correspondiente
            try {
                vistaAdministrador.agregarOficial(textFieldNombre.getText(), textFieldIDAcceso.getText(), textFieldContrasena.getText(),
                        textFieldTelefono.getText(), textFieldID.getText());
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
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
            try {
                vistaAdministrador.agregarOficial(textFieldNombre.getText(), textFieldIDAcceso.getText(), textFieldContrasena.getText(),
                        textFieldTelefono.getText(), textFieldID.getText());
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textFieldNombre.setText(""); // Limpiar el campo de texto
            textFieldID.setText(""); // Limpiar el campo de texto
            textFieldTelefono.setText(""); // Limpiar el campo de texto
            textFieldIDAcceso.setText(""); // Limpiar el campo de texto
            textFieldContrasena.setText(""); // Limpiar el campo de texto
        });

        JScrollPane scrollPane = new JScrollPane(vistaAdministrador.tablaOficiales);
        scrollPane.setBounds(500, 100, 800, 400);
        vistaAdministrador.tablaOficiales.setBounds(0, 0, 800, 400);
        panelOficiales.add(scrollPane);

        JButton btnEliminar = new JButton("Eliminar Oficial");
        btnEliminar.setBounds(1150, 550, 150, 30);
        btnEliminar.setBackground(new Color(0xFFE0133C));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panelOficiales.add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = vistaAdministrador.tablaOficiales.getSelectedRow(); // Obtener la fila seleccionada
            if (filaSeleccionada != -1) { // Verificar si hay una fila seleccionada
                vistaAdministrador.controlador.eliminarOficial(filaSeleccionada);
                System.out.println("fila seleccionada: " + filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila para eliminar");
                System.out.println("fila seleccionada: " + filaSeleccionada);
            }
            try {
                vistaAdministrador.generarTablaOficiales();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        return panelOficiales;
    }
}
