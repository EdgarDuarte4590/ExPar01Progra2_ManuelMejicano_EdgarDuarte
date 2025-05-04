package vista.oficiales;

import java.awt.Color;
import java.awt.ScrollPane;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.ui.FlatListCellBorder.Default;

import controlador.Controlador;
import modelo.Funcionario;
import modelo.Vehiculo;

public class VistaOficiales extends javax.swing.JFrame {
    JTabbedPane tabbedPane;

    private DefaultTableModel  modeloTablaFuncionarios;
    private JTable tablaFuncionarios;
    private Controlador controlador; 

    public VistaOficiales(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
        setLocationRelativeTo(null); 
        setResizable(false); 
    }

    private void initComponents() {
        setTitle("Vista Oficiales");
        setSize(1366, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1366, 720);
        super.add(tabbedPane);

        tabbedPane.addTab("Registro de Ingreso",panelIngresoRegistro());
        tabbedPane.addTab("Funcionarios", panelFuncionarios());
    }

    public JPanel panelIngresoRegistro(){
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null); // Establecer el diseño nulo para el panel de ingreso
        panelIngreso.setBounds(0, 0, 1366, 720);

        JLabel label = new JLabel("Registra los ingresos de los funcionarios del CTP UPALA");
        label.setBounds(25, 50, 550, 30); 
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15)); 
        panelIngreso.add(label); 

        return panelIngreso; 

    }

    public JPanel panelFuncionarios(){
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

        JComboBox<String> comboBox = new JComboBox<>(new String[] {"Profesor (a)", "Cocinero", "Guarda", "Conserje", "Administrativo", "Otro"});
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

        JComboBox<String> vehiculoComboBox = new JComboBox<>(new String[] {"Automóvil", "Camioneta", "Motocicleta", "Bicicleta","Bicimoto", "Otro"});
        vehiculoComboBox.setBounds(50, 340, 200, 30);
        panelFuncionarios.add(vehiculoComboBox);

        JLabel label6 = new JLabel("Número de placa:");
        label6.setBounds(50, 380, 200, 30);
        panelFuncionarios.add(label6);

        JTextField placaField = new JTextField();
        placaField.setBounds(50, 410, 200, 30);
        panelFuncionarios.add(placaField);
        placaField.setToolTipText("Escriba el número de placa del vehículo, si aplica");

        JButton btnRegistrar = new JButton("Registrar Funcionario");
        
        btnRegistrar.setBounds(50, 450, 200, 30);
        btnRegistrar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnRegistrar.setBackground(new java.awt.Color(0xFF0A1419));
        btnRegistrar.setForeground(Color.WHITE);
        panelFuncionarios.add(btnRegistrar);

        tablaFuncionarios = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaFuncionarios);
        scrollPane.setBounds(300, 100, 1000, 500);
        panelFuncionarios.add(scrollPane);
        tablaFuncionarios.setBounds(0, 0, 100, 500);


        modeloTablaFuncionarios = new DefaultTableModel(new String[]{"Puesto", "Nombre", "ID", "Vehículo", "Placa"}, 0);
        tablaFuncionarios.setModel(modeloTablaFuncionarios);

        btnRegistrar.addActionListener(e -> {
            String rol = (String) comboBox.getSelectedItem();
            String nombre = nombreField.getText();
            String id = idField.getText();
            String vehiculo = (String) vehiculoComboBox.getSelectedItem();
            String placa = placaField.getText();

            Funcionario funcionario = new Funcionario(nombre, Integer.parseInt(id), rol);
            Vehiculo vehiculoObj = new Vehiculo(vehiculo, placa);
            funcionario.setVehiculo(vehiculoObj);


            // Limpiar los campos después de registrar
            comboBox.setSelectedIndex(0);
            nombreField.setText("");
            idField.setText("");
            vehiculoComboBox.setSelectedIndex(0);
            placaField.setText("");

            controlador.getFuncionarios().add(funcionario);
            generarTablaFuncionarios();
        });
        return panelFuncionarios;
    }

    public void generarTablaFuncionarios() {
        modeloTablaFuncionarios.setRowCount(0);
        for (Funcionario funcionario : controlador.getFuncionarios()) {
            modeloTablaFuncionarios.addRow(new Object[]{
                funcionario.getPuesto(),
                funcionario.getNombre(),
                funcionario.getId(),
                funcionario.getVehiculo().getTipoVehiculo(),
                funcionario.getVehiculo().getPlaca()
            });
        }
    }
    public void mostrar() {
        setVisible(true);
    }
    public void ocultar() {
        setVisible(false);
    }

}
