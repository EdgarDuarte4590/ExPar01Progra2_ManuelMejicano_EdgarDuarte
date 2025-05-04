package vista.oficiales;

import controlador.Controlador;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class VistaOficiales extends javax.swing.JFrame {

    Controlador controlador; // Instancia del controlador

    public VistaOficiales(Controlador controlador) {
        this.controlador = controlador; // Inicializar el controlador
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        setTitle("Vista Oficiales");
        setSize(1366, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);// Centrar la ventana en la pantalla
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1366, 720); // Establecer el tamaño del JTabbedPane
        tabbedPane.addTab("Funcionario", panelIngresoFuncionario());
        tabbedPane.addTab("Persona extena", IngresoPersonaExterna());
        tabbedPane.addTab("Vehiculo Externo", panelIngresoVehiculo());

        add(tabbedPane);

    }

    public void mostrar() {
        setVisible(true);
    }

    public void ocultar() {
        setVisible(false);
    }

    public JPanel panelOficiales() {
        JPanel panelOficiales = new JPanel();
        panelOficiales.setLayout(null); // Establecer el diseño nulo para el panel principal
        panelOficiales.setBounds(0, 0, 1366, 720); // Establecer el tamaño del panel principal
        JLabel label = new JLabel("Bienvendios Oficiales");
        label.setBounds(25, 50, 550, 30);
        panelOficiales.add(label);

        return panelOficiales;
    }

    public JPanel panelIngresoFuncionario() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel titulo = new JLabel("Ingreso Funcionario");
        titulo.setBounds(25, 50, 550, 30);
        panelIngreso.add(titulo);

        JLabel labelNombre = new JLabel("Nombre Completo del Funcionario:");
        labelNombre.setBounds(25, 100, 550, 30);
        panelIngreso.add(labelNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(25, 130, 550, 30);
        panelIngreso.add(txtNombre);

        JLabel labelID = new JLabel("ID Funcionario:");
        labelID.setBounds(25, 170, 550, 30);
        panelIngreso.add(labelID);

        JTextField txtID = new JTextField();
        txtID.setBounds(25, 200, 550, 30);
        panelIngreso.add(txtID);

        JLabel labelMotivo = new JLabel("Motivo de Ingreso:");
        labelMotivo.setBounds(25, 240, 550, 30);
        panelIngreso.add(labelMotivo);

        JTextField txtMotivo= new JTextField();
        txtMotivo.setBounds(25, 270, 550, 30);
        panelIngreso.add(txtMotivo);

        JLabel labelHora = new JLabel("Hora de Ingreso:");
        labelHora.setBounds(25, 310, 550, 30);
        panelIngreso.add(labelHora);

        

        return panelIngreso;
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

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Controlador getControlador() {
        return controlador;
    }

}
