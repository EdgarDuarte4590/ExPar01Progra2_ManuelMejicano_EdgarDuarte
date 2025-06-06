package vista.administrador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelOficialesAdmin extends JPanel {

    VistaAdmin vistaAdministrador;
    boolean editando = false;

    public PanelOficialesAdmin(VistaAdmin vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
        initComponents();
    }

    // inicializacion del panel de ofiioales donde se registran los oficiaes
    /**
     * @return
     */

     public void guardar_editar(){
        
     }
    public JPanel initComponents() {
        String[] nombreColumnas = { "Nombre", "ID", "Teléfono", "Nombre Usuario", "Contraseña" };
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

        JLabel label2 = new JLabel("Primer nombre: *");
        label2.setBounds(40, 100, 500, 30);
        panelOficiales.add(label2); // Agregar el JLabel al panel de oficiales

        JTextField textFieldNombre1 = new JTextField();
        textFieldNombre1.setBounds(250, 100, 200, 30); // Establecer la posición y el tamaño del JTextField
        textFieldNombre1.setToolTipText("Ejemplo: Juan");
        panelOficiales.add(textFieldNombre1); // Agregar el JTextField al panel de oficiales

        JLabel label7 = new JLabel("Segundo nombre:");
        label7.setBounds(40, 150, 500, 30);
        panelOficiales.add(label7); // Agregar el JLabel al panel de oficiales

        JTextField textFieldNombre2 = new JTextField();
        textFieldNombre2.setBounds(250, 150, 200, 30);
        textFieldNombre2.setToolTipText("Ejemplo: Carlos");
        panelOficiales.add(textFieldNombre2);

        JLabel label8 = new JLabel("Primer apellido: *");
        label8.setBounds(40, 200, 500, 30);
        panelOficiales.add(label8); // Agregar el JLabel al panel de oficiales

        JTextField textFieldApellido1 = new JTextField();
        textFieldApellido1.setBounds(250, 200, 200, 30);
        textFieldApellido1.setToolTipText("Ejemplo: Pérez");
        panelOficiales.add(textFieldApellido1);

        JLabel label9 = new JLabel("Segundo apellido: *");
        label9.setBounds(40, 250, 500, 30);
        panelOficiales.add(label9); // Agregar el JLabel al panel de oficiales

        JTextField textFieldApellido2 = new JTextField();
        textFieldApellido2.setBounds(250, 250, 200, 30);
        textFieldApellido2.setToolTipText("Ejemplo: García");
        panelOficiales.add(textFieldApellido2);

        JLabel label3 = new JLabel("Número de identificación: *");
        label3.setBounds(40, 300, 500, 30);
        panelOficiales.add(label3);

        JTextField textFieldID = new JTextField();
        textFieldID.setBounds(250, 300, 200, 30);
        textFieldID.setToolTipText("Ejemplo: 12345678");
        panelOficiales.add(textFieldID);

        JTextField textFieldTelefono = new JTextField();
        textFieldTelefono.setBounds(250, 350, 200, 30);
        textFieldTelefono.setToolTipText("Ejemplo: 87654321");
        panelOficiales.add(textFieldTelefono);

        JLabel label4 = new JLabel("Número de teléfono:");
        label4.setBounds(40, 350, 500, 30);
        panelOficiales.add(label4);
        JLabel label5 = new JLabel("Nombre de Usuario: *");
        label5.setBounds(40, 400, 500, 30);
        panelOficiales.add(label5);

        JTextField textFieldNombreUsuario = new JTextField();
        textFieldNombreUsuario.setBounds(250, 400, 200, 30);
        textFieldNombreUsuario.setToolTipText("Ejemplo: juan.perez");
        panelOficiales.add(textFieldNombreUsuario);

        JLabel label6 = new JLabel("Contraseña: *");
        label6.setBounds(40, 450, 500, 30);
        panelOficiales.add(label6);
        
        JButton btnEditar = new JButton("Editar Oficial");
        JButton btnAgregar = new JButton("Guardar");

        JTextField textFieldContrasena = new JTextField();
        textFieldContrasena.setBounds(250, 450, 200, 30);
        textFieldContrasena.setToolTipText("Ejemplo: Pass@123");
        panelOficiales.add(textFieldContrasena);
        textFieldContrasena.addActionListener(e -> {
                 if (textFieldID.getText().isEmpty() || textFieldNombre1.getText().isEmpty() ||
                    textFieldNombre2.getText().isEmpty() || textFieldApellido1.getText().isEmpty()
                    || textFieldApellido2.getText().isEmpty() || textFieldTelefono.getText().isEmpty()
                    || textFieldNombreUsuario.getText().isEmpty() || textFieldContrasena.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }

            if (editando) {
                int filaSeleccionada = vistaAdministrador.tablaOficiales.getSelectedRow();

                if (filaSeleccionada != -1) {
                    String userName = vistaAdministrador.tablaOficiales.getValueAt(filaSeleccionada, 3).toString();
                    vistaAdministrador.controlador.editarOficial(
                            textFieldNombre1.getText(), textFieldNombre2.getText(),
                            textFieldApellido1.getText(), textFieldApellido2.getText(), textFieldTelefono.getText(),
                            textFieldNombreUsuario.getText(), textFieldContrasena.getText(),
                            textFieldID.getText(), userName);

                    try {

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al editar oficial: " + ex.getMessage());
                    }
                    editando = false;
                    btnAgregar.setText("Guardar");
                    btnAgregar.setIcon(new ImageIcon("src/resources/icon_guardar.png"));
                    btnEditar.setText("Editar Oficial");
                    btnEditar.setEnabled(true);
                    vistaAdministrador.tablaOficiales.setEnabled(true);
                }
            } else {
                try {
                    vistaAdministrador.controlador.agregarOficial(
                            textFieldNombre1.getText(), textFieldNombre2.getText(),
                            textFieldApellido1.getText(), textFieldApellido2.getText(),
                            textFieldNombreUsuario.getText(), textFieldContrasena.getText(),
                            textFieldTelefono.getText(), textFieldID.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar oficial: " + ex.getMessage());
                }
            }
            textFieldNombre1.setText("");
            textFieldNombre2.setText("");
            textFieldApellido1.setText("");
            textFieldApellido2.setText("");
            textFieldID.setText("");
            textFieldTelefono.setText("");
            textFieldNombreUsuario.setText("");
            textFieldContrasena.setText("");
            try {
                vistaAdministrador.generarTablaOficiales();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        
        });

      
        btnAgregar.setBounds(300, 500, 150, 40);
        btnAgregar.setToolTipText("Haga clic para agregar o editar un oficial");
        btnAgregar.setIcon(new ImageIcon("src/resources/icon_guardar.png")); 
        btnAgregar.setBackground(new Color(0xFF054FBE));
        btnAgregar.setForeground(Color.WHITE); // Establecer el color del texto del botón
        btnAgregar.setBorderPainted(false); // Quitar el borde del botón
        btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12)); // Establecer la fuente del botón
        panelOficiales.add(btnAgregar); // Agregar el botón al panel de oficiales
        btnAgregar.addActionListener(e -> {
            if (textFieldID.getText().isEmpty() || textFieldNombre1.getText().isEmpty()
             || textFieldApellido1.getText().isEmpty()
                    || textFieldApellido2.getText().isEmpty() 
                    || textFieldNombreUsuario.getText().isEmpty() || textFieldContrasena.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }

            if (editando) {
                int filaSeleccionada = vistaAdministrador.tablaOficiales.getSelectedRow();

                if (filaSeleccionada != -1) {
                    String userName = vistaAdministrador.tablaOficiales.getValueAt(filaSeleccionada, 3).toString();
                    vistaAdministrador.controlador.editarOficial(
                            textFieldNombre1.getText(), textFieldNombre2.getText(),
                            textFieldApellido1.getText(), textFieldApellido2.getText(), textFieldTelefono.getText(),
                            textFieldNombreUsuario.getText(), textFieldContrasena.getText(),
                            textFieldID.getText(), userName);

                    try {

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al editar oficial: " + ex.getMessage());
                    }
                    editando = false;
                    btnAgregar.setText("Guardar");
                    btnAgregar.setIcon(new ImageIcon("src/resources/icon_guardar.png"));
                    btnEditar.setText("Editar Oficial");
                    btnEditar.setEnabled(true);
                    vistaAdministrador.tablaOficiales.setEnabled(true);
                }
            } else {
                try {
                    vistaAdministrador.controlador.agregarOficial(
                            textFieldNombre1.getText(), textFieldNombre2.getText(),
                            textFieldApellido1.getText(), textFieldApellido2.getText(),
                            textFieldNombreUsuario.getText(), textFieldContrasena.getText(),
                            textFieldTelefono.getText(), textFieldID.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar oficial: " + ex.getMessage());
                }
            }
            textFieldNombre1.setText("");
            textFieldNombre2.setText("");
            textFieldApellido1.setText("");
            textFieldApellido2.setText("");
            textFieldID.setText("");
            textFieldTelefono.setText("");
            textFieldNombreUsuario.setText("");
            textFieldContrasena.setText("");
            try {
                vistaAdministrador.generarTablaOficiales();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        JScrollPane scrollPane = new JScrollPane(vistaAdministrador.tablaOficiales);
        scrollPane.setBounds(500, 100, 800, 400);
        vistaAdministrador.tablaOficiales.setBounds(0, 0, 800, 400);
        panelOficiales.add(scrollPane);

        btnEditar.setToolTipText("Haga clic para editar un oficial seleccionado");
        btnEditar.setIcon(new ImageIcon("src/resources/icon_editar.png")); 
        btnEditar.setBounds(900, 550, 150, 40);
        btnEditar.setBackground(new Color(0xFF054FBE));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBorderPainted(false);
        btnEditar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panelOficiales.add(btnEditar);
        btnEditar.addActionListener((e) -> {
            int filaSeleccionada = vistaAdministrador.tablaOficiales.getSelectedRow();
            if (filaSeleccionada != -1) {
                editando = true;

                String userName = vistaAdministrador.tablaOficiales.getValueAt(filaSeleccionada, 3).toString();
                String SQL = "SELECT * FROM usuarios WHERE nombreUsuario = '" + userName + "'";
                try {
                    ResultSet rs = vistaAdministrador.controlador.statement.executeQuery(SQL);
                    if (rs.next()) {
                        textFieldNombre1.setText(rs.getString("nombre1"));
                        textFieldNombre2.setText(rs.getString("nombre2"));
                        textFieldApellido1.setText(rs.getString("apellido1"));
                        textFieldApellido2.setText(rs.getString("apellido2"));
                        textFieldID.setText(rs.getString("cedula"));
                        textFieldTelefono.setText(rs.getString("numeroTelefono"));
                        textFieldNombreUsuario.setText(rs.getString("nombreUsuario"));
                        textFieldContrasena.setText(rs.getString("contraseña"));
                        btnAgregar.setText("Actualizar");
                        btnEditar.setText("Editando...");
                        btnEditar.setEnabled(false);
                        btnAgregar.setIcon(new ImageIcon("src/resources/icon_editar.png"));
                        vistaAdministrador.tablaOficiales.setEnabled(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el usuario.");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.");
            }
        });

        JLabel labelBuscar = new JLabel("Buscar Oficial:");
        labelBuscar.setBounds(500, 50, 500, 30);
        labelBuscar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panelOficiales.add(labelBuscar);

        JTextField textFieldBuscar = new JTextField();
        textFieldBuscar.setBounds(600, 50, 200, 30);
        panelOficiales.add(textFieldBuscar);
        textFieldBuscar.setToolTipText("Ingrese el nombre, apellido o ID del oficial");
        textFieldBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String busqueda = textFieldBuscar.getText();
                if (!busqueda.isEmpty()) {
                    consultarOficial(busqueda);
                } else {
                    try {
                        vistaAdministrador.generarTablaOficiales();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        
        JButton btnEliminar = new JButton("Eliminar ");
        btnEliminar.setToolTipText("Haga clic para eliminar un oficial seleccionado");
        btnEliminar.setIcon(new ImageIcon("src/resources/icon_eliminar.png"));
        btnEliminar.setBounds(1100, 550,125, 40);
        btnEliminar.setBackground(new Color(0xFFE0133C));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panelOficiales.add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = vistaAdministrador.tablaOficiales.getSelectedRow(); // Obtener la fila seleccionada
            if (filaSeleccionada != -1) { // Verificar si hay una fila seleccionada
                String userName = vistaAdministrador.tablaOficiales.getValueAt(filaSeleccionada, 3).toString();
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar al oficial " + userName + "?",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    vistaAdministrador.controlador.eliminarOficial(userName);
                    
                }
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

    public void consultarOficial(String busqueda) {
        String SQL = "SELECT * FROM usuarios WHERE tipoUsuario='Guarda' AND (nombre1 LIKE '%" + busqueda
                + "%' OR nombre2 LIKE '%" + busqueda + "%' OR apellido1 LIKE '%" + busqueda + "%' OR apellido2 LIKE '%"
                + busqueda + "%' OR cedula LIKE '%" + busqueda + "%')";
        try {
            ResultSet rs = vistaAdministrador.controlador.statement.executeQuery(SQL);
            actualizarTablaOficiales(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar oficiales: " + e.getMessage());
        }
    }

    public void actualizarTablaOficiales(ResultSet rs) {
        vistaAdministrador.modeloTablaOficiales.setRowCount(0);
        try {
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
                vistaAdministrador.modeloTablaOficiales.addRow(new Object[] {
                        nc, id, telefono, nombreUsuario, contrasena
                });
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
