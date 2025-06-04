package vista.oficiales;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
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

public class PanelIngresoFuncionario extends JPanel{
    VistaOficiales vistaOficiales;


    
    public PanelIngresoFuncionario(VistaOficiales vistaOficiales) {
        this.vistaOficiales = vistaOficiales;
        initComponents();
    }

    public JPanel initComponents() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel labelPlaca = new JLabel("Número de placa del vehículo:");
        labelPlaca.setBounds(25, 50, 200, 30);
        panelIngreso.add(labelPlaca);

        JTextField txtPlaca = new JTextField();
        txtPlaca.setBounds(230, 50, 200, 30);
        panelIngreso.add(txtPlaca);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(450, 50, 100, 30);
        btnBuscar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnBuscar.setBackground(new Color(0xFF054FBE));
        btnBuscar.setForeground(java.awt.Color.white);
        panelIngreso.add(btnBuscar);

        JLabel labelNombre = new JLabel("Nombre del funcionario:");
        labelNombre.setBounds(25, 100, 200, 30); 
        panelIngreso.add(labelNombre);

        vistaOficiales.comboBoxNombres.setBounds(230, 100, 200, 30); 
        panelIngreso.add(vistaOficiales.comboBoxNombres);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(450, 100, 100, 30); 
        btnAgregar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnAgregar.setBackground(new Color(0xFF2BA76B));
        btnAgregar.setForeground(java.awt.Color.white);
        panelIngreso.add(btnAgregar);

        // Llenar el JComboBox con los nombres de los funcionarios registrados
        if ( vistaOficiales.controlador.getFuncionarios() != null) {

        } else {
            vistaOficiales.comboBoxNombres.addItem("No hay funcionario registrados");
        }

        btnBuscar.addActionListener(e -> {
         //busca el funcionario por medio de la placa 
            String placa = txtPlaca.getText();
            for (int i = 0; i < vistaOficiales.controlador.getFuncionarios().size(); i++) {
                if (placa.equals(vistaOficiales.controlador.getFuncionarios().get(i).getVehiculo().getPlaca())) {
                    vistaOficiales.comboBoxNombres.setSelectedIndex(i);
                    return;
                }

            }
            vistaOficiales.comboBoxNombres.setSelectedIndex(-1);
            JOptionPane.showMessageDialog(null, "No se encontro el funcionario con ese número de placa: " + placa);
        });

        vistaOficiales.modeloTablaIngresoFuncionarios = new DefaultTableModel(new String[] {
                "Nombre Funcionario", "ID", "Puesto",
                "Tipo de Vehiculo", "Placa", "Fecha Ingreso", "Hora ingreso", "Nombre de oficial",
        }, 0);
        vistaOficiales.tablaIngresoFuncionarios = new JTable(vistaOficiales.modeloTablaIngresoFuncionarios);

        JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaIngresoFuncionarios);
        scrollPane.setBounds(25, 150, 1300, 500);
        panelIngreso.add(scrollPane);

        vistaOficiales.comboBoxNombres.addActionListener(e -> {
            if (vistaOficiales.comboBoxNombres.getSelectedIndex() != -1) {
                txtPlaca.setText(
                        vistaOficiales.controlador.getFuncionarios().get(vistaOficiales.comboBoxNombres.getSelectedIndex()).getVehiculo().getPlaca());
            } else {
                txtPlaca.setText("");
            }
        });

        btnAgregar.addActionListener((actionEvent) -> {
            Funcionario funcionario =  vistaOficiales.controlador.getFuncionarios().get(vistaOficiales.comboBoxNombres.getSelectedIndex());
            String motivo = "Trabaja de: " + funcionario.getPuesto();
            LocalTime hora = LocalTime.now();
            
            String nombreGuarda = vistaOficiales.controlador.buscarGuardaPorUsuario( vistaOficiales.controlador.getIdOficialActual());

            LocalDate fecha = LocalDate.now();

            IngresoFuncionario ingreso = new IngresoFuncionario(fecha, motivo, hora, nombreGuarda, funcionario);
            vistaOficiales.controlador.getIngresosFuncionarios().add(ingreso);
            vistaOficiales.GenerarTablaIngresoFuncionarios();

        });

        return panelIngreso;
    }

    
}
