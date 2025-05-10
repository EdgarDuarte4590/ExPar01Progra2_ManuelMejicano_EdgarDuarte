package vista.oficiales;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Funcionario;
import modelo.Vehiculo;

public class PanelFuncionarios extends JPanel {
    VistaOficiales vistaOficiales;



    public PanelFuncionarios(VistaOficiales vistaOficiales) {
        this.vistaOficiales = vistaOficiales;
        initComponents();
    }

    public JPanel initComponents() {
        JPanel panelFuncionarios = new JPanel();
        panelFuncionarios.setLayout(null);
        panelFuncionarios.setBounds(0, 0, 1366, 720);

        JLabel label = new JLabel("Registra los funcionarios del CTP UPALA");
        label.setBounds(25, 50, 550, 30);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        panelFuncionarios.add(label);

        JLabel label2 = new JLabel("Seleccione el rol del funcionario:");
        label2.setBounds(50, 100, 500, 30);
        panelFuncionarios.add(label2);

        JComboBox<String> comboBox = new JComboBox<>(
                new String[] { "Profesor (a)", "Cocinero", "Guarda", "Conserje", "Administrativo", "Otro" });
        comboBox.setBounds(50, 130, 200, 30);
        panelFuncionarios.add(comboBox);

        JLabel label3 = new JLabel("Nombre del funcionario:");
        label3.setBounds(50, 170, 200, 30);
        panelFuncionarios.add(label3);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(50, 200, 200, 30);
        panelFuncionarios.add(nombreField);

        JLabel label4 = new JLabel("Número de identificación:");
        label4.setBounds(50, 240, 200, 30);
        panelFuncionarios.add(label4);

        JTextField idField = new JTextField();
        idField.setBounds(50, 270, 200, 30);
        panelFuncionarios.add(idField);

        JLabel label5 = new JLabel("Tipo de Vehículo:");
        label5.setBounds(50, 310, 200, 30);
        panelFuncionarios.add(label5);

        JComboBox<String> vehiculoComboBox = new JComboBox<>(
                new String[] { "Automóvil", "Camioneta", "Motocicleta", "Bicicleta", "Bicimoto", "Otro" });
        vehiculoComboBox.setBounds(50, 340, 200, 30);
        panelFuncionarios.add(vehiculoComboBox);

        JLabel label6 = new JLabel("Número de placa:");
        label6.setBounds(50, 380, 200, 30);
        panelFuncionarios.add(label6);

        JTextField placaField = new JTextField();
        placaField.setBounds(50, 410, 200, 30);
        panelFuncionarios.add(placaField);
        placaField.setToolTipText("Escriba el número de placa del vehículo, si aplica");

        JButton btnRegistrar = new JButton("Registrar funcionario");

        btnRegistrar.setBounds(50, 450, 200, 30);
        btnRegistrar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnRegistrar.setBackground(new java.awt.Color(0xFF0A1419));
        btnRegistrar.setForeground(Color.WHITE);
        panelFuncionarios.add(btnRegistrar);

        vistaOficiales.tablaFuncionarios = new JTable();
        JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaFuncionarios);
        scrollPane.setBounds(300, 100, 1000, 500);
        panelFuncionarios.add(scrollPane);
        vistaOficiales.tablaFuncionarios.setBounds(0, 0, 100, 500);

        vistaOficiales.modeloTablaFuncionarios = new DefaultTableModel(new String[] { "Puesto", "Nombre", "ID", "Vehículo", "Placa" },
                0);
                vistaOficiales.tablaFuncionarios.setModel(vistaOficiales.modeloTablaFuncionarios);

        btnRegistrar.addActionListener(e -> {
            String rol = (String) comboBox.getSelectedItem();
            String nombre = nombreField.getText();
            String id = idField.getText();
            String vehiculo = (String) vehiculoComboBox.getSelectedItem();
            String placa = placaField.getText();

            Funcionario funcionario = new Funcionario(nombre, Integer.parseInt(id), rol);
            Vehiculo vehiculoObj = new Vehiculo(placa, vehiculo);
            funcionario.setVehiculo(vehiculoObj);

            // Limpiar los campos después de registrar
            comboBox.setSelectedIndex(0);
            nombreField.setText("");
            idField.setText("");
            vehiculoComboBox.setSelectedIndex(0);
            placaField.setText("");

            vistaOficiales.controlador.getFuncionarios().add(funcionario);
            generarTablaFuncionarios();
            GenerarComboFuncionarios();
        });
        return panelFuncionarios;
    }

    public void GenerarComboFuncionarios() {
        vistaOficiales.comboBoxNombres.removeAllItems();
        for (int i = 0; i < vistaOficiales.controlador.getFuncionarios().size(); i++) {
            vistaOficiales.comboBoxNombres.addItem(vistaOficiales.controlador.getFuncionarios().get(i).getNombre());
        }
    }

    public void generarTablaFuncionarios() {
        vistaOficiales.modeloTablaFuncionarios.setRowCount(0);
        for (Funcionario funcionario : vistaOficiales.controlador.getFuncionarios()) {
            vistaOficiales.modeloTablaFuncionarios.addRow(new Object[] {
                    funcionario.getPuesto(),
                    funcionario.getNombre(),
                    funcionario.getId(),
                    funcionario.getVehiculo().getTipoVehiculo(),
                    funcionario.getVehiculo().getPlaca()
            });
        }
    }

}
