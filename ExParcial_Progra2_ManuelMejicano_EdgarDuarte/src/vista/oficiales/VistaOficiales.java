package vista.oficiales;

import java.awt.Color;
import java.awt.ScrollPane;
import java.util.ResourceBundle.Control;

import javax.swing.ButtonGroup;
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

import javax.swing.JRadioButton;
import modelo.Funcionario;
import modelo.Guarda;
import modelo.Ingreso;
import modelo.IngresoExterno;
import modelo.IngresoFuncionario;
import modelo.Persona;
import modelo.Vehiculo;
import modelo.VehiculoExterno;

public class VistaOficiales extends javax.swing.JFrame {

    JTabbedPane tabbedPane;

    private DefaultTableModel modeloTablaFuncionarios;
    private JTable tablaFuncionarios;
    private Controlador controlador;

    private JTable tablaIngresoFuncionarios;
    private DefaultTableModel modeloTablaIngresoFuncionarios;
    private JTable tablaIngresosExterno;
    private DefaultTableModel modeloTablaIngresoExterno;
    private DefaultTableModel modeloTablaVehiculoExterno;

    

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

        tabbedPane.addTab("Salida de Estudiantes", panelEstudiantes());
        tabbedPane.addTab("Funcionarios", panelFuncionarios());
        tabbedPane.addTab("Ingreso Funcionario", panelIngresoFuncionario());
        tabbedPane.addTab("Ingreso Externo", IngresoExterno());
    }

    public JPanel panelEstudiantes() {
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

        tablaFuncionarios = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaFuncionarios);
        scrollPane.setBounds(300, 100, 1000, 500);
        panelFuncionarios.add(scrollPane);
        tablaFuncionarios.setBounds(0, 0, 100, 500);

        modeloTablaFuncionarios = new DefaultTableModel(new String[] { "Puesto", "Nombre", "ID", "Vehículo", "Placa" },
                0);
        tablaFuncionarios.setModel(modeloTablaFuncionarios);

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

            controlador.getFuncionarios().add(funcionario);
            generarTablaFuncionarios();
        });
        return panelFuncionarios;
    }

    public void generarTablaFuncionarios() {
        modeloTablaFuncionarios.setRowCount(0);
        for (Funcionario funcionario : controlador.getFuncionarios()) {
            modeloTablaFuncionarios.addRow(new Object[] {
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

        } else {
            comboBoxNombres.addItem("No hay funcionario registrados");
        }

        btnBuscar.addActionListener(e -> {
            for (Funcionario funcionario : controlador.getFuncionarios()) {
                comboBoxNombres.addItem(funcionario.getNombre());
            }
            String placa = txtPlaca.getText();
            for (Funcionario funcionario : controlador.getFuncionarios()) {
                if (funcionario.getVehiculo() != null && placa.equals(funcionario.getVehiculo().getPlaca())) {
                    comboBoxNombres.setSelectedItem(funcionario.getNombre());
                    break;
                }
            }
        });

        modeloTablaIngresoFuncionarios = new DefaultTableModel(new String[] { "Nombre Funcionario", "ID", "Puesto",
                "Tipo de Vehiculo", "Placa", "Fecha Ingreso", "Hora ingreso", "Nombre de oficial", }, 0);

        tablaIngresoFuncionarios = new JTable(modeloTablaIngresoFuncionarios);

        JScrollPane scrollPane = new JScrollPane(tablaIngresoFuncionarios);
        scrollPane.setBounds(25, 150, 1300, 500);
        panelIngreso.add(scrollPane);

        btnAgregar.addActionListener((actionEvent) -> {
            Funcionario funcionario = controlador.buscarFuncionarioPorPlaca(txtPlaca.getText());
            String motivo = "Trabaja de: " + funcionario.getPuesto();
            LocalTime hora = LocalTime.now();
            Guarda guarda = controlador.buscarGuardaPorID(controlador.getIdOficialActual());
            String nombreGuarda = guarda.getNombre();

            LocalDate fecha = LocalDate.now();

            IngresoFuncionario ingreso = new IngresoFuncionario(fecha, motivo, hora, nombreGuarda, funcionario);
            controlador.getIngresosFuncionarios().add(ingreso);
            GenerarTablaIngresoFuncionarios();

        });

        return panelIngreso;
    }

    public void GenerarTablaIngresoFuncionarios() {

        modeloTablaFuncionarios.setRowCount(0);
    
        for (IngresoFuncionario ingreso : controlador.getIngresosFuncionarios()) {
     
            modeloTablaIngresoFuncionarios.addRow(new Object[] {
                    ingreso.getFuncionario().getNombre(),
                    ingreso.getFuncionario().getId(),
                    ingreso.getFuncionario().getPuesto(),
                    ingreso.getFuncionario().getVehiculo().getTipoVehiculo(),
                    ingreso.getFuncionario().getVehiculo().getPlaca(),
                    ingreso.getFechaIngreso(),
                    ingreso.getHoraIngreso().format(controlador.formato),
                    ingreso.getNombreGuarda()
            });

        }

    }

  

    public JPanel IngresoExterno() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel opcion= new JLabel("Seleccione el tipo de ingreso:");
        opcion.setBounds(250, 10, 550, 50);
        panelIngreso.add(opcion);

        ButtonGroup btnGroup= new ButtonGroup();
        
        JRadioButton btnIngresoPersona= new JRadioButton("Ingreso Persona Externa");
        btnIngresoPersona.setBounds(150,50,250,30);
        btnGroup.add(btnIngresoPersona);


        JRadioButton btnIngreso = new JRadioButton("Ingreso Vehiculo Externo");
        btnIngreso.setBounds(400,50,250,30);
        btnGroup.add(btnIngreso);


        panelIngreso.add(btnIngresoPersona);
        panelIngreso.add(btnIngreso);

        btnIngresoPersona.addActionListener(e -> {
            
            panelIngreso.removeAll();

            JLabel lblNombre = new JLabel("Nombre:");
            lblNombre.setBounds(150, 20, 100, 30);
            panelIngreso.add(lblNombre);

            JTextField txtNombre = new JTextField();
            txtNombre.setBounds(250, 20, 200, 30);
            panelIngreso.add(txtNombre);

            JLabel lblId = new JLabel("ID:");
            lblId.setBounds(150, 60, 100, 30);
            panelIngreso.add(lblId);

            JTextField txtId = new JTextField();
            txtId.setBounds(250, 60, 200, 30);
            panelIngreso.add(txtId);

            JLabel lblMotivo = new JLabel("Motivo:");
            lblMotivo.setBounds(150, 100, 100, 30);
            panelIngreso.add(lblMotivo);
            String[] items= {
                "Reunión con el director",
                "Entrega de documentos",
                "Reunión de padres de familia",
                "Mantenimiento o reparación",
                "Entrega de suministros",
                "Visita guiada",
                "Capacitación o charla",
                "Evento cultural o deportivo",
                "Inspección o auditoría",
                "Otro"
            };
            JComboBox comboMotivo= new JComboBox(items);
            comboMotivo.setBounds(250, 100, 200, 30);
            panelIngreso.add(comboMotivo);

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setBounds(250, 150, 100, 30);
            panelIngreso.add(btnGuardar);

            btnGuardar.addActionListener(ev -> {
                String nombre = txtNombre.getText();
                int id= Integer.parseInt(txtId.getText());

                Persona visitante= new Persona(nombre, id);
                String motivo = (String) comboMotivo.getSelectedItem();

                LocalTime hora= LocalTime.now();
                LocalDate fecha= LocalDate.now();
                Guarda guarda= controlador.buscarGuardaPorID(controlador.getIdOficialActual());
                String nombreGuarda= guarda.getNombre();

               IngresoExterno ingreso= new IngresoExterno(fecha, motivo, hora, nombreGuarda, visitante, false);
               controlador.getIngresosExternos().add(ingreso);

               JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");


                GenerarTablaIngresoExterno();


            });

            modeloTablaIngresoExterno= new DefaultTableModel(new String[]{"Nombre","ID","Motivo","Fecha","Hora","Nombre de ofical"},0);
            tablaIngresosExterno= new JTable(modeloTablaIngresoExterno);
            JScrollPane scrollPane= new JScrollPane(tablaIngresosExterno);
            scrollPane.setBounds(25,200,1300,400);
            panelIngreso.add(scrollPane);

            GenerarTablaIngresoExterno();

            JButton Regresar= new JButton("Regresar");
            Regresar.setBounds(400,150,100,30);
            panelIngreso.add(Regresar);
            Regresar.addActionListener(ev ->{
                panelIngreso.removeAll();
                panelIngreso.add(opcion);
                panelIngreso.add(btnIngresoPersona);
                panelIngreso.add(btnIngreso);
                panelIngreso.revalidate();
                panelIngreso.repaint();


            });

            panelIngreso.revalidate();
            panelIngreso.repaint();
        });

        btnIngreso.addActionListener(e -> {


            panelIngreso.removeAll();

            JLabel lblNombre = new JLabel("Nombre:");
            lblNombre.setBounds(150, 20, 100, 30);
            panelIngreso.add(lblNombre);

            JTextField txtNombre = new JTextField();
            txtNombre.setBounds(250, 20, 200, 30);
            panelIngreso.add(txtNombre);

            JLabel lblId = new JLabel("ID:");
            lblId.setBounds(150, 60, 100, 30);
            panelIngreso.add(lblId);

            JTextField txtId = new JTextField();
            txtId.setBounds(250, 60, 200, 30);
            panelIngreso.add(txtId);

            JLabel lblMotivo = new JLabel("Motivo:");
            lblMotivo.setBounds(150, 100, 100, 30);
            panelIngreso.add(lblMotivo);
            String[] motivos= {
                "Entrega de suministros",
                "Mantenimiento o reparación",
                "Evento cultural o deportivo",
                "Transporte de estudiantes",
                "Transporte de personal",
                "Visita oficial",
                "Inspección o auditoría",
                "Capacitación o charla",
                "Entrega de equipo",
                "Otro"
            };
            JComboBox comboMotivo= new JComboBox(motivos);
            comboMotivo.setBounds(250, 100, 200, 30);
            panelIngreso.add(comboMotivo);

            JLabel lblPlaca = new JLabel("Placa Vehiculo:");
            lblPlaca.setBounds(470, 20, 100, 30);
            panelIngreso.add(lblPlaca);

            JTextField txtPlaca = new JTextField();
            txtPlaca.setBounds(570, 20, 100, 30);
            panelIngreso.add(txtPlaca);

            JLabel lblTipoVehiculo = new JLabel("Tipo de Vehículo:");
            lblTipoVehiculo.setBounds(470, 60, 100, 30);
            panelIngreso.add(lblTipoVehiculo);

            JComboBox<String> vehiculoComboBox = new JComboBox<>(
                new String[] { "Automóvil", "Camioneta", "Motocicleta", "Bicicleta", "Bicimoto", "Otro" });
            vehiculoComboBox.setBounds(570, 60, 120, 30);
            panelIngreso.add(vehiculoComboBox);


            JLabel lblCantidadPasajeros= new JLabel("Cantidad de Pasajeros:");
            lblCantidadPasajeros.setBounds(700,20,150,30);
            panelIngreso.add(lblCantidadPasajeros);

            JTextField txtCantidad = new JTextField();
            txtCantidad.setBounds(850, 20, 100, 30);
            panelIngreso.add(txtCantidad);

            JLabel lblCompania= new JLabel("Empresa Vehiculo:");
            lblCompania.setBounds(700,60,150,30);
            panelIngreso.add(lblCompania);

            JTextField txtCompania = new JTextField();
            txtCompania.setBounds(850, 60, 100, 30);
            panelIngreso.add(txtCompania);



            JButton btnGuardarVehiculo = new JButton("Guardar");
            btnGuardarVehiculo.setBounds(590, 100, 100, 30);
            panelIngreso.add(btnGuardarVehiculo);

            btnGuardarVehiculo.addActionListener(ev -> {
              
                String nombre = txtNombre.getText();
                int id= Integer.parseInt(txtId.getText());
                Persona visitante= new Persona(nombre, id);

                String motivo = (String) comboMotivo.getSelectedItem();
                LocalTime hora= LocalTime.now();
                LocalDate fecha= LocalDate.now();
                Guarda guarda= controlador.buscarGuardaPorID(controlador.getIdOficialActual());
                String nombreGuarda= guarda.getNombre();

                int cantidadP= Integer.parseInt(txtCantidad.getText());
                String compani= txtCompania.getText();
                String placa= txtPlaca.getText();
                String tipoV= (String) vehiculoComboBox.getSelectedItem();

                Vehiculo vehiculo= new Vehiculo(placa, tipoV);

                VehiculoExterno ingreso= new VehiculoExterno(fecha, motivo, hora, nombreGuarda, visitante, false, cantidadP, compani, vehiculo);
                controlador.getIngresosVehiculoExterno().add(ingreso);

                JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");

                GenerarTablaIngresoVehiculoExterno();
                

            });

            modeloTablaVehiculoExterno= new DefaultTableModel(new String[]{"Nombre","ID","Motivo","Fecha","Hora","Nombre de ofical","Placa Vehiculo","Tipo de Vehiculo","Cantidad Pasajeros","Compañia"},0);
            tablaIngresosExterno= new JTable(modeloTablaVehiculoExterno);
            JScrollPane scrollPane= new JScrollPane(tablaIngresosExterno);
            scrollPane.setBounds(25,200,1300,400);
            panelIngreso.add(scrollPane);

            GenerarTablaIngresoVehiculoExterno();


            JButton Regresar= new JButton("Regresar");
            Regresar.setBounds(800,100,100,30);
            panelIngreso.add(Regresar);
            Regresar.addActionListener(ev ->{
                panelIngreso.removeAll();
                panelIngreso.add(opcion);
                panelIngreso.add(btnIngresoPersona);
                panelIngreso.add(btnIngreso);
                panelIngreso.revalidate();
                panelIngreso.repaint();


            });

            panelIngreso.revalidate();
            panelIngreso.repaint();
        });

        return panelIngreso;
    }

    public void GenerarTablaIngresoExterno(){

        modeloTablaIngresoExterno.setRowCount(0);
        for(IngresoExterno ingreso: controlador.getIngresosExternos()){
            modeloTablaIngresoExterno.addRow(new Object[]{
                ingreso.getVisitante().getNombre(),  ingreso.getVisitante().getId(),ingreso.getMotivo(),
                ingreso.getFechaIngreso(), ingreso.getHoraIngreso().format(controlador.formato),ingreso.getNombreGuarda(),

            });
        }
    }

    public void GenerarTablaIngresoVehiculoExterno(){
        modeloTablaVehiculoExterno.setRowCount(0);
        for (VehiculoExterno ingreso : controlador.getIngresosVehiculoExterno()) {
         
                modeloTablaVehiculoExterno.addRow(new Object[] {
                   ingreso.getVisitante().getNombre() , ingreso.getVisitante().getId(),ingreso.getMotivo(),
                   ingreso.getFechaIngreso(),ingreso.getHoraIngreso().format(controlador.formato),ingreso.getNombreGuarda(),
                   ingreso.getVehiculo().getPlaca(), ingreso.getVehiculo().getTipoVehiculo(),ingreso.getCantidadPasajeros(),ingreso.getCompania(),
                });
            
        }
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
