package vista.oficiales;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
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
        comboBox.setToolTipText("Seleccione el puesto del funcionario");
        panelFuncionarios.add(comboBox);

        JLabel label3a = new JLabel("Primer nombre del funcionario:");
        label3a.setBounds(50, 170, 200, 30);
        panelFuncionarios.add(label3a);

        JTextField primerNombreField = new JTextField();
        primerNombreField.setBounds(50, 200, 200, 30);
        primerNombreField.setToolTipText("Digite el primer nombre del funcionario (ej. Juan)");
        panelFuncionarios.add(primerNombreField);

        JLabel label3b = new JLabel("Segundo nombre del funcionario:");
        label3b.setBounds(50, 230, 200, 30);
        panelFuncionarios.add(label3b);

        JTextField segundoNombreField = new JTextField();
        segundoNombreField.setBounds(50, 260, 200, 30);
        segundoNombreField.setToolTipText("Digite el segundo nombre del funcionario (ej. Pablo), si aplica");
        panelFuncionarios.add(segundoNombreField);

        JLabel label3c = new JLabel("Primer apellido del funcionario:");
        label3c.setBounds(50, 290, 200, 30);
        panelFuncionarios.add(label3c);

        JTextField primerApellidoField = new JTextField();
        primerApellidoField.setBounds(50, 320, 200, 30);
        primerApellidoField.setToolTipText("Digite el primer apellido del funcionario (ej. Rodríguez)");
        panelFuncionarios.add(primerApellidoField);

        JLabel label3d = new JLabel("Segundo apellido del funcionario:");
        label3d.setBounds(50, 350, 200, 30);
        panelFuncionarios.add(label3d);

        JTextField segundoApellidoField = new JTextField();
        segundoApellidoField.setBounds(50, 380, 200, 30);
        segundoApellidoField.setToolTipText("Digite el segundo apellido del funcionario (ej. Gómez)");
        panelFuncionarios.add(segundoApellidoField);

        JLabel label4 = new JLabel("Número de identificación:");
        label4.setBounds(50, 410, 200, 30);
        panelFuncionarios.add(label4);

        JTextField idField = new JTextField();
        idField.setBounds(50, 440, 200, 30);
        idField.setToolTipText("Digite la cédula del funcionario (ej. 1-2345-6789)");
        panelFuncionarios.add(idField);

        JLabel label5 = new JLabel("Tipo de Vehículo:");
        label5.setBounds(50, 470, 200, 30);
        panelFuncionarios.add(label5);

        JComboBox<String> vehiculoComboBox = new JComboBox<>(
                new String[] { "Automóvil", "Camioneta", "Motocicleta", "Bicicleta", "Bicimoto", "Otro", "No Aplica" });
        vehiculoComboBox.setBounds(50, 500, 200, 30);
        vehiculoComboBox.setToolTipText("Seleccione el tipo de vehículo del funcionario");
        panelFuncionarios.add(vehiculoComboBox);

        JLabel label6 = new JLabel("Número de placa:");
        label6.setBounds(50, 530, 200, 30);
        panelFuncionarios.add(label6);

        JTextField placaField = new JTextField();
        placaField.setBounds(50, 560, 200, 30);
        placaField.setToolTipText("Digite el número de placa del vehículo (ej. ABC-123), si aplica");
        panelFuncionarios.add(placaField);

        // Disable placaField if "No Aplica" is selected
        vehiculoComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selected = (String) vehiculoComboBox.getSelectedItem();
                    if ("No Aplica".equals(selected)) {
                        placaField.setEnabled(false);
                        placaField.setText("");
                    } else {
                        placaField.setEnabled(true);
                    }
                }
            }
        });

        JButton btnRegistrar = new JButton("Registrar funcionario");
        btnRegistrar.setIcon(new ImageIcon("src/resources/icon_create.png"));
        btnRegistrar.setToolTipText("Registrar un nuevo funcionario, llene todos los campos requeridos");
        btnRegistrar.setHorizontalTextPosition(JButton.LEFT);
        btnRegistrar.setIconTextGap(15);
        btnRegistrar.setBounds(50, 610, 200, 30);
        btnRegistrar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnRegistrar.setBackground(new java.awt.Color(0xFF0A1419));
        btnRegistrar.setForeground(Color.WHITE);
        panelFuncionarios.add(btnRegistrar);

        JButton btnEditar = new JButton("Editar funcionario");
        btnEditar.setIcon(new ImageIcon("src/resources/icon_editar.png"));
        btnEditar.setToolTipText("Editar un funcionario existente, seleccione el funcionario a editar");
        btnEditar.setHorizontalTextPosition(JButton.LEFT);
        btnEditar.setIconTextGap(15);
        btnEditar.setBounds(1100, 610, 200, 30);
        btnEditar.setBackground(new Color(0xFF054FBE));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBorderPainted(false);
        btnEditar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panelFuncionarios.add(btnEditar);

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
            String primerNombre = primerNombreField.getText();
            String segundoNombre = segundoNombreField.getText();
            String primerApellido = primerApellidoField.getText();
            String segundoApellido = segundoApellidoField.getText();
            String nombreCompleto = primerNombre + " " + (segundoNombre.isEmpty() ? "" : segundoNombre + " ") + primerApellido + " " + segundoApellido;
            String id = idField.getText();
            String vehiculo = (String) vehiculoComboBox.getSelectedItem();
            String placa = placaField.getText();

            Funcionario funcionario = new Funcionario(nombreCompleto, Integer.parseInt(id), rol);
            Vehiculo vehiculoObj = new Vehiculo(placa, vehiculo);
            funcionario.setVehiculo(vehiculoObj);

            comboBox.setSelectedIndex(0);
            primerNombreField.setText("");
            segundoNombreField.setText("");
            primerApellidoField.setText("");
            segundoApellidoField.setText("");
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