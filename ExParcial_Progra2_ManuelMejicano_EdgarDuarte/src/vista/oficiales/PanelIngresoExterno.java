package vista.oficiales;

import java.awt.Color;
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
import modelo.IngresoExterno;
import modelo.Vehiculo;
import modelo.VehiculoExterno;

public class PanelIngresoExterno extends JPanel {
    VistaOficiales vistaOficiales; 

    public PanelIngresoExterno(VistaOficiales vistaOficiales) {
        this.vistaOficiales = vistaOficiales;
        initComponents();
    }

    //inicializacion del panel de ingreso externo
    public JPanel initComponents() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel opcion = new JLabel("Seleccione el tipo de ingreso:");
        opcion.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        opcion.setBounds(550, 10, 300, 50);
        panelIngreso.add(opcion);

        ButtonGroup btnGroup = new ButtonGroup();
        //boton que sirve para registrar el ingreso de perosna externa 
        JRadioButton btnIngresoPersona = new JRadioButton("Ingreso Persona Externa");
        btnIngresoPersona.setBounds(500, 50, 200, 30);
        btnGroup.add(btnIngresoPersona);
        //boton que sirve para registrar el ingreso de vehiculo externo
        JRadioButton btnIngreso = new JRadioButton("Ingreso Vehiculo Externo");
        btnIngreso.setBounds(700, 50, 250, 30);
        btnGroup.add(btnIngreso);

        panelIngreso.add(btnIngresoPersona);
        panelIngreso.add(btnIngreso);
        
        btnIngresoPersona.addActionListener(e -> {
            // sin el radio button de persona es seleccionado se eliminan los componentes iniciales del panel y se colo el formulario de ingreso de perosna externa
            panelIngreso.removeAll();

            JLabel lblNombre = new JLabel("Nombre:");
            lblNombre.setBounds(500, 20, 100, 30);
            panelIngreso.add(lblNombre);

            JTextField txtNombre = new JTextField();
            txtNombre.setBounds(600, 20, 200, 30);
            panelIngreso.add(txtNombre);

            JLabel lblId = new JLabel("ID:");
            lblId.setBounds(500, 60, 100, 30);
            panelIngreso.add(lblId);

            JTextField txtId = new JTextField();
            txtId.setBounds(600, 60, 200, 30);
            panelIngreso.add(txtId);

            JLabel lblMotivo = new JLabel("Motivo:");
            lblMotivo.setBounds(500, 100, 100, 30);
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
            comboMotivo.setBounds(600, 100, 200, 30);
            panelIngreso.add(comboMotivo);

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setBounds(700, 150, 100, 30);
            btnGuardar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnGuardar.setBackground(new Color(0xFF2BA76B));
            btnGuardar.setForeground(Color.WHITE);
            btnGuardar.setBorderPainted(false);
            panelIngreso.add(btnGuardar);

            btnGuardar.addActionListener(ev -> {
                       // sin el radio button de persona es seleccionado se eliminan los componentes iniciales del panel y se colo el formulario de ingreso de vehiculo externo
                String nombre = txtNombre.getText();
                int id = Integer.parseInt(txtId.getText());

                String motivo = (String) comboMotivo.getSelectedItem();

                LocalTime hora = LocalTime.now();
                LocalDate fecha = LocalDate.now();
               
                String nombreGuarda = vistaOficiales.controlador.buscarGuardaPorUsuario(vistaOficiales.controlador.getIdOficialActual());
                // se cre el ingreso externo, y luefo se pasa al arraylist
                IngresoExterno ingreso = new IngresoExterno(fecha, motivo, hora, nombreGuarda, nombre, id);
                vistaOficiales.controlador.getIngresosExternos().add(ingreso);

                JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");
                txtNombre.setText("");
                txtId.setText("");
                comboMotivo.setSelectedIndex(0);

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
            //permite al guarda regresar y poder seleccionar si quiere agergar un vehiculo externo, se le muestran los 2 radio button
            Regresar.setBounds(500, 150, 100, 30);
            Regresar.setBackground(new Color(0xFFE0133C));
            Regresar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            Regresar.setBorderPainted(false);
            Regresar.setForeground(Color.WHITE);
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
            txtPlaca.setBounds(570, 20, 120, 30);
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
            btnGuardarVehiculo.setBounds(850, 100, 100, 30);
            btnGuardarVehiculo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnGuardarVehiculo.setBackground(new Color(0xFF2BA76B));
            btnGuardarVehiculo.setForeground(Color.WHITE);
            btnGuardarVehiculo.setBorderPainted(false);
            panelIngreso.add(btnGuardarVehiculo);

            btnGuardarVehiculo.addActionListener(ev -> {
// se cre el ingreso externo, y luego se pasa al arraylist
                String nombre = txtNombre.getText();
                int id = Integer.parseInt(txtId.getText());

                String motivo = (String) comboMotivo.getSelectedItem();
                LocalTime hora = LocalTime.now();
                LocalDate fecha = LocalDate.now();
                 
                String nombreGuarda = vistaOficiales.controlador.buscarGuardaPorUsuario(vistaOficiales.controlador.getIdOficialActual());

                int cantidadP = Integer.parseInt(txtCantidad.getText());
                String compani = txtCompania.getText();
                String placa = txtPlaca.getText();
                String tipoV = (String) vehiculoComboBox.getSelectedItem();

                Vehiculo vehiculo = new Vehiculo(placa, tipoV);

                VehiculoExterno ingreso = new VehiculoExterno(fecha, motivo, hora, nombreGuarda, nombre, id,
                        cantidadP, compani, vehiculo);
                        vistaOficiales.controlador.getIngresosVehiculoExterno().add(ingreso);

                JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");
                txtNombre.setText("");
                txtId.setText("");
                txtPlaca.setText("");
                txtCantidad.setText("");
                txtCompania.setText("");
                comboMotivo.setSelectedIndex(0);
                vehiculoComboBox.setSelectedIndex(0);
                

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

            Regresar.setBounds(590, 100, 100, 30);
            Regresar.setBackground(new Color(0xFFE0133C));
            Regresar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            Regresar.setBorderPainted(false);
            Regresar.setForeground(Color.WHITE);
            panelIngreso.add(Regresar);
            Regresar.addActionListener(ev -> {
                //permite al guarda regresar y poder seleccionar si quiere agergar una perona externa, se le muestran los 2 radio button
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
