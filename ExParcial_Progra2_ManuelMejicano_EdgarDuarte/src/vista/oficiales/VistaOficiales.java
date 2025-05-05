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
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import modelo.Funcionario;
import modelo.Guarda;
import modelo.Ingreso;
import modelo.Vehiculo;

public class VistaOficiales extends javax.swing.JFrame {

    JTabbedPane tabbedPane;

    private DefaultTableModel modeloTablaFuncionarios;
    private JTable tablaFuncionarios;
    private Controlador controlador;

    private JTable tablaIngresoFuncionarios;
    private DefaultTableModel modeloTablaIngresoFuncionarios;
    private JTable tablaIngresoExterno;
    private DefaultTableModel modeloTablaIngresoExterno;

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

        tabbedPane.addTab("Registro de Ingreso", panelIngresoRegistro());
        tabbedPane.addTab("Funcionarios", panelFuncionarios());
        tabbedPane.addTab("Ingreso Funcionario", panelIngresoFuncionario());
        tabbedPane.addTab("Ingreso Externo", IngresoPersonaExterna());
    }

    public JPanel panelIngresoRegistro() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null); // Establecer el diseño nulo para el panel de ingreso
        panelIngreso.setBounds(0, 0, 1366, 720);

        JLabel label = new JLabel("Registra los ingresos de los funcionarios del CTP UPALA");
        label.setBounds(25, 50, 550, 30);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        panelIngreso.add(label);

        return panelIngreso;

    }

    public JPanel panelFuncionarios() {
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

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Profesor (a)", "Cocinero", "Guarda", "Conserje", "Administrativo", "Otro"});
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

        JComboBox<String> vehiculoComboBox = new JComboBox<>(new String[]{"Automóvil", "Camioneta", "Motocicleta", "Bicicleta", "Bicimoto", "Otro"});
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

    public JPanel panelIngresoFuncionario() {
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
        panelIngreso.add(btnBuscar);

        JLabel labelNombre = new JLabel("Nombre del funcionario:");
        labelNombre.setBounds(25, 100, 200, 30); // Ajustar posición para alinearlo con los primeros componentes
        panelIngreso.add(labelNombre);

        JComboBox<String> comboBoxNombres = new JComboBox<>();
        comboBoxNombres.setBounds(230, 100, 200, 30); // Ajustar posición para alinearlo con los primeros componentes
        panelIngreso.add(comboBoxNombres);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(450, 100, 100, 30); // Ajustar posición para alinearlo con los primeros componentes
        panelIngreso.add(btnAgregar);

        // Llenar el JComboBox con los nombres de los funcionarios registrados
        if (controlador.getFuncionarios() != null) {
            for (Funcionario funcionario : controlador.getFuncionarios()) {
                comboBoxNombres.addItem(funcionario.getNombre());
            }
        } else {
            comboBoxNombres.addItem("No hay funcionario registrados");
        }

        btnBuscar.addActionListener(e -> {
            String placa = txtPlaca.getText();
            for (Funcionario funcionario : controlador.getFuncionarios()) {
                if (funcionario.getVehiculo() != null && placa.equals(funcionario.getVehiculo().getPlaca())) {
                    comboBoxNombres.setSelectedItem(funcionario.getNombre());
                    break;
                }
            }
        });

        modeloTablaIngresoFuncionarios= new DefaultTableModel(new String[]{"Nombre Funcionario","ID","Puesto","Tipo de Vehiculo","Placa","Nacionalidad","Edad","Fecha Ingreso","Hora ingreso","NombreGuarda",},0);

        tablaIngresoFuncionarios= new JTable(modeloTablaIngresoFuncionarios);
      
        JScrollPane scrollPane = new JScrollPane(tablaIngresoFuncionarios);
        scrollPane.setBounds(25, 150, 1300, 500);
        panelIngreso.add(scrollPane);

        btnAgregar.addActionListener((actionEvent) -> {
            Funcionario funcionario= buscarFuncionarioPorPlaca(txtPlaca.getText());
            String motivo= "Trabaja de: " +funcionario.getPuesto();
            LocalTime hora= LocalTime.now();
            Guarda guarda= buscarGuardaPorID(controlador.getIdOficialActual());
            String nombreGuarda= guarda.getNombre();

            LocalDate fecha= LocalDate.now();

            Ingreso ingreso= new Ingreso(fecha, motivo, hora, guarda, nombreGuarda);
            controlador.AgregarIngreso(ingreso);

            GenerarTablaIngresoFuncionarios(funcionario.getPuesto(), "No se", 25);

            

        });

        
        

        return panelIngreso;
    }

    public void GenerarTablaIngresoFuncionarios(String puesto,String nacionalidad,int edad ){

modeloTablaFuncionarios.setRowCount(0);
for (Object ingreso : controlador.getIngresos()) {
    Ingreso ingresoObj = (Ingreso) ingreso;
    modeloTablaIngresoFuncionarios.addRow(new Object[]{
        ingresoObj.getPersona().getNombre(),
        ingresoObj.getPersona().getId(),
        puesto,
        ingresoObj.getTipoVehiculo(),
        ingresoObj.getPlacaVehiculo(),
        nacionalidad,
        edad,
        ingresoObj.getFechaIngreso(),
        ingresoObj.getHoraIngreso(),
        ingresoObj.getNombreGuarda()
    });
    
}

    }

    public Funcionario buscarFuncionarioPorPlaca(String placa) {
        for (Funcionario funcionario : controlador.getFuncionarios()) {
            if (funcionario.getVehiculo() != null && funcionario.getVehiculo().getPlaca().equals(placa)) {
                return funcionario;
            }
        }
        return null; // Si no se encuentra el funcionario
    }

    public Guarda buscarGuardaPorID(String id){
        for (Guarda guarda : controlador.getOficiales()) {
            if (guarda.getIDAcceso().equals(id)) {
                return guarda;
            }
        }
        return null; // Si no se encuentra el guarda
    }

    

    public JPanel IngresoPersonaExterna() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel titulo = new JLabel("Ingreso Persona Externa");
        titulo.setBounds(25, 50, 550, 30);
        panelIngreso.add(titulo);

        JLabel labelNombre = new JLabel("Nombre Completo de la Persona Externa:");
        labelNombre.setBounds(25, 100, 550, 30);
        panelIngreso.add(labelNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(25, 130, 550, 30);
        panelIngreso.add(txtNombre);

        JLabel labelID = new JLabel("ID Persona Externa:");
        labelID.setBounds(25, 170, 550, 30);
        panelIngreso.add(labelID);

        JTextField txtID = new JTextField();
        txtID.setBounds(25, 200, 550, 30);
        panelIngreso.add(txtID);

        JLabel labelMotivo = new JLabel("Motivo de la Visita:");
        labelMotivo.setBounds(25, 240, 550, 30);
        panelIngreso.add(labelMotivo);

        JTextField txtMotivo = new JTextField();
        txtMotivo.setBounds(25, 270, 550, 30);
        panelIngreso.add(txtMotivo);

        return panelIngreso;
    }

    public JPanel panelIngresoVehiculo() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel titulo = new JLabel("Ingreso Vehiculo");
        titulo.setBounds(25, 50, 550, 30);
        panelIngreso.add(titulo);

        JLabel labelnombreChofer = new JLabel("Ingrese el nombre del Chofer: ");
        labelnombreChofer.setBounds(25, 100, 550, 30);
        panelIngreso.add(labelnombreChofer);

        JTextField txtnombreChofer = new JTextField();
        txtnombreChofer.setBounds(25, 130, 450, 30);
        panelIngreso.add(txtnombreChofer);

        JLabel labelPlaca = new JLabel("Ingrese la placa del Vehiculo:");
        labelPlaca.setBounds(25, 170, 550, 30);
        panelIngreso.add(labelPlaca);

        JTextField txtPlaca = new JTextField();
        txtPlaca.setBounds(25, 200, 450, 30);
        panelIngreso.add(txtPlaca);

        JLabel labelCantidadPasajeros = new JLabel("Ingrese la cantidad de pasajeros:");
        labelCantidadPasajeros.setBounds(25, 240, 550, 30);
        panelIngreso.add(labelCantidadPasajeros);

        JTextField txtCantidadPasajeros = new JTextField();
        txtCantidadPasajeros.setBounds(25, 270, 450, 30);
        panelIngreso.add(txtCantidadPasajeros);

        JLabel labelCompania = new JLabel("Ingrese la compañia:");
        labelCompania.setBounds(25, 310, 550, 30);
        panelIngreso.add(labelCompania);

        JTextField txtCompania = new JTextField();
        txtCompania.setBounds(25, 340, 450, 30);
        panelIngreso.add(txtCompania);

        JLabel labelMotivo = new JLabel("Motivo del ingreso:");
        labelMotivo.setBounds(25, 380, 550, 30);
        panelIngreso.add(labelMotivo);

        JTextField txtMotivo = new JTextField();
        txtMotivo.setBounds(25, 410, 450, 30);
        panelIngreso.add(txtMotivo);

        return panelIngreso;
    }

    public JPanel panelSalidaEstudiantes() {

        return null;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Controlador getControlador() {
        return controlador;
    }

}
