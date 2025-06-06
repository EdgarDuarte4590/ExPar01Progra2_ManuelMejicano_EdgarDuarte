package vista.oficiales;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelIngresoFuncionario extends JPanel {
    VistaOficiales vistaOficiales;

    public PanelIngresoFuncionario(VistaOficiales vistaOficiales) {
        this.vistaOficiales = vistaOficiales;
        initComponents();
    }

    public JPanel initComponents() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel labelPlaca = new JLabel("Busca un funcionario:");
        labelPlaca.setBounds(25, 50, 200, 30);
        panelIngreso.add(labelPlaca);

        JTextField txtBusqueda = new JTextField();
        txtBusqueda.setBounds(230, 50, 200, 30);
        panelIngreso.add(txtBusqueda);
        txtBusqueda.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        String busqueda = txtBusqueda.getText();
                        if (!busqueda.isEmpty()) {
                            vistaOficiales.buscarFuncionario(busqueda);
                        } else {
                            vistaOficiales.panelFuncionarios.generarComboFuncionarios();

                        }
                    }

                });

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(new ImageIcon("src/resources/icon_busqueda_24.png"));
        btnBuscar.setBounds(450, 50, 125, 30);
        btnBuscar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnBuscar.setBackground(new Color(0xFF054FBE));
        btnBuscar.setForeground(java.awt.Color.white);
        panelIngreso.add(btnBuscar);

        JLabel labelNombre = new JLabel("Cédula del funcionario:");
        labelNombre.setBounds(25, 100, 200, 30);
        panelIngreso.add(labelNombre);

        vistaOficiales.comboBoxFuncionarios.setBounds(230, 100, 200, 30);
        panelIngreso.add(vistaOficiales.comboBoxFuncionarios);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setIcon(new ImageIcon("src/resources/icon_create.png"));
        btnAgregar.setBounds(450, 100, 125, 30);
        btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnAgregar.setBackground(new Color(0xFF2BA76B));
        btnAgregar.setForeground(java.awt.Color.white);
        panelIngreso.add(btnAgregar);

       
 



        vistaOficiales.modeloTablaIngresoFuncionarios = new DefaultTableModel(new String[] {
                "ID Ingreso", "Nombre Funcionario", "Cédula", "Puesto",
                "Tipo de Vehiculo", "Placa", "Fecha Ingreso", "Hora ingreso", "Nombre de oficial",
        }, 0);
        vistaOficiales.tablaIngresoFuncionarios = new JTable(vistaOficiales.modeloTablaIngresoFuncionarios);

        JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaIngresoFuncionarios);
        scrollPane.setBounds(25, 150, 1300, 500);
        panelIngreso.add(scrollPane);

        vistaOficiales.comboBoxFuncionarios.addActionListener(e -> {

        });

        btnAgregar.addActionListener((actionEvent) -> {
            String idFuncionario = vistaOficiales.comboBoxFuncionarios.getSelectedItem().toString();
            if (idFuncionario != null && !idFuncionario.isEmpty()) {
                LocalDate fechaIngreso = LocalDate.now();
                LocalTime horaIngreso = LocalTime.now();

                String userGuarda = vistaOficiales.controlador.getIdOficialActual();
                System.out.println("Nombre de usuario guarda: " + userGuarda);

                vistaOficiales.controlador.agregarIngresoFuncionario(idFuncionario, fechaIngreso, horaIngreso,
                        userGuarda);

                vistaOficiales.generarTablaIngresoFuncionarios();

            } else {
                JOptionPane.showMessageDialog(null, "Funcionario no encontrado");
            }
        });

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(600, 50, 125, 30);
        btnRefrescar.setIcon(new ImageIcon("src/resources/icon_recargar.png"));
        btnRefrescar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnRefrescar.setBackground(new Color(0xFFFFA83E));
        btnRefrescar.setForeground(java.awt.Color.white);
        panelIngreso.add(btnRefrescar);
        btnRefrescar.addActionListener(e -> {
            vistaOficiales.generarTablaIngresoFuncionarios();
            txtBusqueda.setText("");
            vistaOficiales.panelFuncionarios.generarComboFuncionarios();
        });
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(600, 100, 125, 30);
        btnEliminar.setIcon(new ImageIcon("src/resources/icon_eliminar.png"));
        btnEliminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnEliminar.setBackground(new Color(0xFFB00020));
        btnEliminar.setForeground(java.awt.Color.white);
        panelIngreso.add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            int selectedRow = vistaOficiales.tablaIngresoFuncionarios.getSelectedRow();
            if (selectedRow != -1) {
                String idIngreso = vistaOficiales.modeloTablaIngresoFuncionarios.getValueAt(selectedRow, 0).toString();
                vistaOficiales.eliminarIngresoFuncionario(idIngreso);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un ingreso para eliminar");
            }
        });

        return panelIngreso;
    }

    

}
