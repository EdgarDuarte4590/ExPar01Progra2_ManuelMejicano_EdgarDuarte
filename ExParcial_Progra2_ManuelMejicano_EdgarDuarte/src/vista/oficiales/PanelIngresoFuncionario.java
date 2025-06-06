package vista.oficiales;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
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
import modelo.Funcionario;
import modelo.IngresoFuncionario;

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
        btnBuscar.setIcon(new ImageIcon("src/resources/icon_busqueda.png"));
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
        btnAgregar.setBounds(450, 100, 100, 30);
        btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnAgregar.setBackground(new Color(0xFF2BA76B));
        btnAgregar.setForeground(java.awt.Color.white);
        panelIngreso.add(btnAgregar);

        // Llenar el JComboBox con los nombres de los funcionarios registrados
        if (vistaOficiales.controlador.getFuncionarios() != null) {

        } else {
            vistaOficiales.comboBoxFuncionarios.addItem("No hay funcionario registrados");
        }

        btnBuscar.addActionListener(e -> {
            // busca el funcionario por medio de la placa
            String placa = txtBusqueda.getText();
            for (int i = 0; i < vistaOficiales.controlador.getFuncionarios().size(); i++) {
                if (placa.equals(vistaOficiales.controlador.getFuncionarios().get(i).getVehiculo().getPlaca())) {
                    vistaOficiales.comboBoxFuncionarios.setSelectedIndex(i);
                    return;
                }

            }
            vistaOficiales.comboBoxFuncionarios.setSelectedIndex(-1);
            JOptionPane.showMessageDialog(null, "No se encontro el funcionario con ese número de placa: " + placa);
        });

        vistaOficiales.modeloTablaIngresoFuncionarios = new DefaultTableModel(new String[] {
                "ID Salida", "Nombre Funcionario", "Cédula", "Puesto",
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
                vistaOficiales.modeloTablaIngresoFuncionarios.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un ingreso para eliminar");
            }
        });

        return panelIngreso;
    }

}
