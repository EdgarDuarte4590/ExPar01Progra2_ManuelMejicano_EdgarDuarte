package vista.oficiales;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Guarda;
import modelo.IngresoExterno;
import modelo.Vehiculo;
import modelo.VehiculoExterno;

public class PanelIngresoExterno extends JPanel {
    VistaOficiales vistaOficiales; 

    public PanelIngresoExterno(VistaOficiales vistaOficiales) {
        this.vistaOficiales = vistaOficiales;
        initComponents();
    }
    public JPanel initComponents() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel opcion = new JLabel("Seleccione el tipo de ingreso:");
        opcion.setBounds(250, 10, 550, 50);
        panelIngreso.add(opcion);

        ButtonGroup btnGroup = new ButtonGroup();

        JRadioButton btnIngresoPersona = new JRadioButton("Ingreso Persona Externa");
        btnIngresoPersona.setBounds(150, 50, 250, 30);
        btnGroup.add(btnIngresoPersona);

        JRadioButton btnIngreso = new JRadioButton("Ingreso Vehiculo Externo");
        btnIngreso.setBounds(400, 50, 250, 30);
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
            String[] items = {
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
            JComboBox comboMotivo = new JComboBox(items);
            comboMotivo.setBounds(250, 100, 200, 30);
            panelIngreso.add(comboMotivo);

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setBounds(250, 150, 100, 30);
            panelIngreso.add(btnGuardar);

            btnGuardar.addActionListener(ev -> {
                String nombre = txtNombre.getText();
                int id = Integer.parseInt(txtId.getText());

                String motivo = (String) comboMotivo.getSelectedItem();

                LocalTime hora = LocalTime.now();
                LocalDate fecha = LocalDate.now();
                Guarda guarda = vistaOficiales.controlador.buscarGuardaPorID(vistaOficiales.controlador.getIdOficialActual());
                String nombreGuarda = guarda.getNombre();

                IngresoExterno ingreso = new IngresoExterno(fecha, motivo, hora, nombreGuarda, false, nombre, id);
                vistaOficiales.controlador.getIngresosExternos().add(ingreso);

                JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");

                vistaOficiales.GenerarTablaIngresoExterno();

            });

            vistaOficiales.modeloTablaIngresoExterno = new DefaultTableModel(
                    new String[] { "Nombre", "ID", "Motivo", "Fecha", "Hora", "Nombre de ofical" }, 0);
                    vistaOficiales.tablaIngresosExterno = new JTable(vistaOficiales.modeloTablaIngresoExterno);
            JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaIngresosExterno);
            scrollPane.setBounds(25, 200, 1300, 400);
            panelIngreso.add(scrollPane);

            vistaOficiales.GenerarTablaIngresoExterno();

            JButton Regresar = new JButton("Regresar");
            Regresar.setBounds(400, 150, 100, 30);
            panelIngreso.add(Regresar);
            Regresar.addActionListener(ev -> {
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
            String[] motivos = {
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
            JComboBox comboMotivo = new JComboBox(motivos);
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

            JLabel lblCantidadPasajeros = new JLabel("Cantidad de Pasajeros:");
            lblCantidadPasajeros.setBounds(700, 20, 150, 30);
            panelIngreso.add(lblCantidadPasajeros);

            JTextField txtCantidad = new JTextField();
            txtCantidad.setBounds(850, 20, 100, 30);
            panelIngreso.add(txtCantidad);

            JLabel lblCompania = new JLabel("Empresa Vehiculo:");
            lblCompania.setBounds(700, 60, 150, 30);
            panelIngreso.add(lblCompania);

            JTextField txtCompania = new JTextField();
            txtCompania.setBounds(850, 60, 100, 30);
            panelIngreso.add(txtCompania);

            JButton btnGuardarVehiculo = new JButton("Guardar");
            btnGuardarVehiculo.setBounds(590, 100, 100, 30);
            panelIngreso.add(btnGuardarVehiculo);

            btnGuardarVehiculo.addActionListener(ev -> {

                String nombre = txtNombre.getText();
                int id = Integer.parseInt(txtId.getText());

                String motivo = (String) comboMotivo.getSelectedItem();
                LocalTime hora = LocalTime.now();
                LocalDate fecha = LocalDate.now();
                Guarda guarda = vistaOficiales.controlador.buscarGuardaPorID(vistaOficiales.controlador.getIdOficialActual());
                String nombreGuarda = guarda.getNombre();

                int cantidadP = Integer.parseInt(txtCantidad.getText());
                String compani = txtCompania.getText();
                String placa = txtPlaca.getText();
                String tipoV = (String) vehiculoComboBox.getSelectedItem();

                Vehiculo vehiculo = new Vehiculo(placa, tipoV);

                VehiculoExterno ingreso = new VehiculoExterno(fecha, motivo, hora, nombreGuarda, false, nombre, id,
                        cantidadP, compani, vehiculo);
                        vistaOficiales.controlador.getIngresosVehiculoExterno().add(ingreso);

                JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");

                vistaOficiales.GenerarTablaIngresoVehiculoExterno();

            });

            vistaOficiales.modeloTablaVehiculoExterno = new DefaultTableModel(new String[] { "Nombre", "ID", "Motivo", "Fecha", "Hora",
                    "Nombre de ofical", "Placa Vehiculo", "Tipo de Vehiculo", "Cantidad Pasajeros", "Compañia" }, 0);
                    vistaOficiales.tablaIngresosExterno = new JTable(vistaOficiales.modeloTablaVehiculoExterno);
            JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaIngresosExterno);
            scrollPane.setBounds(25, 200, 1300, 400);
            panelIngreso.add(scrollPane);

            vistaOficiales.GenerarTablaIngresoVehiculoExterno();

            JButton Regresar = new JButton("Regresar");
            Regresar.setBounds(800, 100, 100, 30);
            panelIngreso.add(Regresar);
            Regresar.addActionListener(ev -> {
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


}
