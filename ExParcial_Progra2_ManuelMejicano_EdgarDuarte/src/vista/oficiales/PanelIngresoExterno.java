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
import modelo.Vehiculo;
import modelo.VehiculoExterno;

public class PanelIngresoExterno extends JPanel {
    VistaOficiales vistaOficiales; 

    public PanelIngresoExterno(VistaOficiales vistaOficiales) {
        this.vistaOficiales = vistaOficiales;
        initComponents();
    }

    //inicialización del panel de ingreso externo
    public JPanel initComponents() {
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(null);

        JLabel opcion = new JLabel("Seleccione el tipo de ingreso:");
        opcion.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        opcion.setBounds(550, 10, 300, 50);
        panelIngreso.add(opcion);

        ButtonGroup btnGroup = new ButtonGroup();
        // Botón para registrar el ingreso de persona externa 
        JRadioButton btnIngresoPersona = new JRadioButton("Ingreso Persona Externa");
        btnIngresoPersona.setBounds(500, 50, 200, 30);
        btnGroup.add(btnIngresoPersona);
        // Botón para registrar el ingreso de vehículo externo
        JRadioButton btnIngreso = new JRadioButton("Ingreso Vehiculo Externo");
        btnIngreso.setBounds(700, 50, 250, 30);
        btnGroup.add(btnIngreso);

        panelIngreso.add(btnIngresoPersona);
        panelIngreso.add(btnIngreso);
        
        btnIngresoPersona.addActionListener(e -> {
            // Al seleccionar persona externa, se limpia y se crea el formulario con cuatro campos de nombre/apellidos
            panelIngreso.removeAll();

            // --- Nombres (verticalmente) ---
            JLabel lblNombre1 = new JLabel("Nombre 1:");
            lblNombre1.setBounds(450, 20, 80, 30);
            panelIngreso.add(lblNombre1);
            JTextField txtNombre1 = new JTextField();
            txtNombre1.setBounds(540, 20, 200, 30);
            panelIngreso.add(txtNombre1);

            JLabel lblNombre2 = new JLabel("Nombre 2:");
            lblNombre2.setBounds(450, 60, 80, 30);
            panelIngreso.add(lblNombre2);
            JTextField txtNombre2 = new JTextField();
            txtNombre2.setBounds(540, 60, 200, 30);
            panelIngreso.add(txtNombre2);

            // --- Apellidos (verticalmente, a la derecha de nombres) ---
            JLabel lblApellido1 = new JLabel("Apellido 1:");
            lblApellido1.setBounds(780, 20, 80, 30);
            panelIngreso.add(lblApellido1);
            JTextField txtApellido1 = new JTextField();
            txtApellido1.setBounds(870, 20, 200, 30);
            panelIngreso.add(txtApellido1);

            JLabel lblApellido2 = new JLabel("Apellido 2:");
            lblApellido2.setBounds(780, 60, 80, 30);
            panelIngreso.add(lblApellido2);
            JTextField txtApellido2 = new JTextField();
            txtApellido2.setBounds(870, 60, 200, 30);
            panelIngreso.add(txtApellido2);

            // --- ID ---
            JLabel lblId = new JLabel("ID:");
            lblId.setBounds(450, 100, 100, 30);
            panelIngreso.add(lblId);
            JTextField txtId = new JTextField();
            txtId.setBounds(540, 100, 200, 30);
            panelIngreso.add(txtId);

            // --- Motivo ---
            JLabel lblMotivo = new JLabel("Motivo:");
            lblMotivo.setBounds(780, 100, 100, 30);
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
            JComboBox<String> comboMotivo = new JComboBox<>(items);
            comboMotivo.setBounds(870, 100, 200, 30);
            panelIngreso.add(comboMotivo);

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setBounds(930, 150, 100, 30);
            btnGuardar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnGuardar.setBackground(new Color(0xFF2BA76B));
            btnGuardar.setForeground(Color.WHITE);
            btnGuardar.setBorderPainted(false);
            panelIngreso.add(btnGuardar);

            btnGuardar.addActionListener(ev -> {
                // lectura de los cuatro campos de nombre y apellidos
                String nombre1 = txtNombre1.getText().trim();
                String nombre2 = txtNombre2.getText().trim();
                String apellido1 = txtApellido1.getText().trim();
                String apellido2 = txtApellido2.getText().trim();
                String id = txtId.getText().trim();
                vistaOficiales.controlador.registrarPersonaExterna(id, nombre1, nombre2, apellido1, apellido2);

                String motivo = (String) comboMotivo.getSelectedItem();
                LocalTime hora = LocalTime.now();
                LocalDate fecha = LocalDate.now();
                String nombreUsuarioGuarda =vistaOficiales.controlador.getIdOficialActual();
                vistaOficiales.controlador.registarIngresoExterno(id, fecha, hora, motivo, nombreUsuarioGuarda);
               

                JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");
                // limpiar campos
                txtNombre1.setText("");
                txtNombre2.setText("");
                txtApellido1.setText("");
                txtApellido2.setText("");
                txtId.setText("");
                comboMotivo.setSelectedIndex(0);

                vistaOficiales.GenerarTablaIngresoExterno();
            });

            // == TABLA DE PERSONA EXTERNA (sólo “Nombre” como estaba originalmente) ==
            vistaOficiales.modeloTablaIngresoExterno = new DefaultTableModel(
                    new String[] { "Nombre", "ID", "Motivo", "Fecha", "Hora", "Nombre de oficial" }, 0);
            vistaOficiales.tablaIngresosExterno = new JTable(vistaOficiales.modeloTablaIngresoExterno);
            JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaIngresosExterno);
            scrollPane.setBounds(25, 200, 1300, 400);
            panelIngreso.add(scrollPane);

            vistaOficiales.GenerarTablaIngresoExterno();

            JButton Regresar = new JButton("Regresar");
            // Permite volver a la selección inicial con los dos radio buttons
            Regresar.setBounds(450, 150, 100, 30);
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
            // Al seleccionar vehículo externo, se limpia y se crea el formulario con cuatro campos de nombre/apellidos
            panelIngreso.removeAll();

            // --- Nombres (verticalmente) ---
            JLabel lblNombre1 = new JLabel("Nombre 1:");
            lblNombre1.setBounds(150, 20, 80, 30);
            panelIngreso.add(lblNombre1);
            JTextField txtNombre1 = new JTextField();
            txtNombre1.setBounds(240, 20, 200, 30);
            panelIngreso.add(txtNombre1);

            JLabel lblNombre2 = new JLabel("Nombre 2:");
            lblNombre2.setBounds(150, 60, 80, 30);
            panelIngreso.add(lblNombre2);
            JTextField txtNombre2 = new JTextField();
            txtNombre2.setBounds(240, 60, 200, 30);
            panelIngreso.add(txtNombre2);

            // --- Apellidos (verticalmente, a la derecha de nombres) ---
            JLabel lblApellido1 = new JLabel("Apellido 1:");
            lblApellido1.setBounds(480, 20, 80, 30);
            panelIngreso.add(lblApellido1);
            JTextField txtApellido1 = new JTextField();
            txtApellido1.setBounds(570, 20, 200, 30);
            panelIngreso.add(txtApellido1);

            JLabel lblApellido2 = new JLabel("Apellido 2:");
            lblApellido2.setBounds(480, 60, 80, 30);
            panelIngreso.add(lblApellido2);
            JTextField txtApellido2 = new JTextField();
            txtApellido2.setBounds(570, 60, 200, 30);
            panelIngreso.add(txtApellido2);

            // --- ID ---
            JLabel lblId = new JLabel("ID:");
            lblId.setBounds(150, 100, 100, 30);
            panelIngreso.add(lblId);
            JTextField txtId = new JTextField();
            txtId.setBounds(240, 100, 200, 30);
            panelIngreso.add(txtId);

            // --- Motivo ---
            JLabel lblMotivo = new JLabel("Motivo:");
            lblMotivo.setBounds(480, 100, 100, 30);
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
            JComboBox<String> comboMotivo = new JComboBox<>(motivos);
            comboMotivo.setBounds(570, 100, 200, 30);
            panelIngreso.add(comboMotivo);

            // --- Placa Vehículo ---
            JLabel lblPlaca = new JLabel("Placa Vehículo:");
            lblPlaca.setBounds(800, 20, 100, 30);
            panelIngreso.add(lblPlaca);
            JTextField txtPlaca = new JTextField();
            txtPlaca.setBounds(900, 20, 120, 30);
            panelIngreso.add(txtPlaca);

            // --- Tipo de Vehículo ---
            JLabel lblTipoVehiculo = new JLabel("Tipo de Vehículo:");
            lblTipoVehiculo.setBounds(800, 60, 100, 30);
            panelIngreso.add(lblTipoVehiculo);
            JComboBox<String> vehiculoComboBox = new JComboBox<>(
                    new String[] { "Automóvil", "Camioneta", "Motocicleta", "Bicicleta", "Bicimoto", "Otro" });
            vehiculoComboBox.setBounds(900, 60, 120, 30);
            panelIngreso.add(vehiculoComboBox);

            // --- Cantidad de Pasajeros ---
            JLabel lblCantidadPasajeros = new JLabel("Cantidad de Pasajeros:");
            lblCantidadPasajeros.setBounds(1030, 20, 150, 30);
            panelIngreso.add(lblCantidadPasajeros);
            JTextField txtCantidad = new JTextField();
            txtCantidad.setBounds(1180, 20, 100, 30);
            panelIngreso.add(txtCantidad);

            // --- Empresa Vehículo ---
            JLabel lblCompania = new JLabel("Empresa Vehículo:");
            lblCompania.setBounds(1030, 60, 150, 30);
            panelIngreso.add(lblCompania);
            JTextField txtCompania = new JTextField();
            txtCompania.setBounds(1180, 60, 100, 30);
            panelIngreso.add(txtCompania);

            JButton btnGuardarVehiculo = new JButton("Guardar");
            btnGuardarVehiculo.setBounds(1180, 100, 100, 30);
            btnGuardarVehiculo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnGuardarVehiculo.setBackground(new Color(0xFF2BA76B));
            btnGuardarVehiculo.setForeground(Color.WHITE);
            btnGuardarVehiculo.setBorderPainted(false);
            panelIngreso.add(btnGuardarVehiculo);

            btnGuardarVehiculo.addActionListener(ev -> {
                // lectura de los cuatro campos de nombre/apellidos
                String nombre1 = txtNombre1.getText().trim();
                String nombre2 = txtNombre2.getText().trim();
                String apellido1 = txtApellido1.getText().trim();
                String apellido2 = txtApellido2.getText().trim();
                int id = Integer.parseInt(txtId.getText().trim());
                String motivo = (String) comboMotivo.getSelectedItem();
                LocalTime hora = LocalTime.now();
                LocalDate fecha = LocalDate.now();
                String nombreGuarda = vistaOficiales.controlador
                        .buscarGuardaPorUsuario(vistaOficiales.controlador.getIdOficialActual());

                int cantidadP = Integer.parseInt(txtCantidad.getText().trim());
                String compani = txtCompania.getText().trim();
                String placa = txtPlaca.getText().trim();
                String tipoV = (String) vehiculoComboBox.getSelectedItem();

                Vehiculo vehiculo = new Vehiculo(placa, tipoV);
                // Concatenamos los cuatro componentes del nombre en un solo String para la tabla
                String nombreCompleto = nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
                VehiculoExterno ingreso = new VehiculoExterno(fecha, motivo, hora, nombreGuarda,
                        nombreCompleto, id, cantidadP, compani, vehiculo);
                vistaOficiales.controlador.getIngresosVehiculoExterno().add(ingreso);

                JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente");
                // limpiar campos
                txtNombre1.setText("");
                txtNombre2.setText("");
                txtApellido1.setText("");
                txtApellido2.setText("");
                txtId.setText("");
                txtPlaca.setText("");
                txtCantidad.setText("");
                txtCompania.setText("");
                comboMotivo.setSelectedIndex(0);
                vehiculoComboBox.setSelectedIndex(0);

                vistaOficiales.GenerarTablaIngresoVehiculoExterno();
            });

            
            vistaOficiales.modeloTablaVehiculoExterno = new DefaultTableModel(new String[] {
                    "Nombre", "ID", "Motivo", "Fecha", "Hora", "Nombre de oficial", "Placa Vehículo",
                    "Tipo de Vehículo", "Cantidad Pasajeros", "Compañía"
            }, 0);
            vistaOficiales.tablaIngresosExterno = new JTable(vistaOficiales.modeloTablaVehiculoExterno);
            JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaIngresosExterno);
            scrollPane.setBounds(25, 200, 1300, 400);
            panelIngreso.add(scrollPane);

            vistaOficiales.GenerarTablaIngresoVehiculoExterno();

            JButton Regresar = new JButton("Regresar");
            Regresar.setBounds(900, 100, 100, 30);
            Regresar.setBackground(new Color(0xFFE0133C));
            Regresar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            Regresar.setBorderPainted(false);
            Regresar.setForeground(Color.WHITE);
            panelIngreso.add(Regresar);
            Regresar.addActionListener(ev -> {
                // Regresar a la pantalla inicial con los dos radio buttons
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