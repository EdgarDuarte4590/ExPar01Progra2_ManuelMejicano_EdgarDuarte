package vista.oficiales;

import controlador.Controlador;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Funcionario;
import modelo.VehiculoExterno;

public class VistaOficiales extends javax.swing.JFrame {

    JTabbedPane tabbedPane;
    DefaultTableModel modeloTablaFuncionarios;
    JTable tablaFuncionarios;
    Controlador controlador;
    JTable tablaIngresoFuncionarios;
    DefaultTableModel modeloTablaIngresoFuncionarios;
    JTable tablaIngresosExterno;
    DefaultTableModel modeloTablaIngresoExterno;
    DefaultTableModel modeloTablaVehiculoExterno;

    PanelSalidaEstudiante panelSalidaEstudiante;
    PanelFuncionarios panelFuncionarios;
    PanelIngresoFuncionario panelIngresoFuncionario;
    PanelIngresoExterno panelIngresoExterno;

    public VistaOficiales(Controlador controlador) throws SQLException {
        this.controlador = controlador;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mostrarDialogoCerrar();
            }
        });
    }

    private void initComponents() throws SQLException {
        setTitle("Vista Oficiales");
        setSize(1366, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1366, 720);
        super.add(tabbedPane);

        panelSalidaEstudiante = new PanelSalidaEstudiante(this);
        panelFuncionarios = new PanelFuncionarios(this);
        panelIngresoFuncionario = new PanelIngresoFuncionario(this);
        panelIngresoExterno = new PanelIngresoExterno(this);

        tabbedPane.addTab("Salida de Estudiantes", panelSalidaEstudiante.initComponents());
        tabbedPane.addTab("Funcionarios", panelFuncionarios.initComponents());
        tabbedPane.addTab("Ingreso Funcionario", panelIngresoFuncionario.initComponents());
        tabbedPane.addTab("Ingreso Externo", panelIngresoExterno.initComponents());

        panelFuncionarios.generarTablaFuncionarios();
        panelFuncionarios.GenerarComboFuncionarios();
        generarTablaIngresoFuncionarios();
        

    }

    public ResultSet ejecutarConsulta(String sql) throws SQLException {
        Statement stmt = controlador.connection.createStatement();
        return stmt.executeQuery(sql);
    }

  
    private void mostrarDialogoCerrar() {
        // Opciones personalizadas
        String[] opciones = {"Cerrar sesión", "Salir", "Cancelar"};
        int opcion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cerrar sesión?",
                "Confirmación de salida",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        switch (opcion) {
            case 0: // Cerrar sesión
                controlador.setSesionInciadaOficial(false);
                this.dispose();
                break;
            case 1: // Salir
                this.dispose(); // Cerrar la aplicación
                break;
            default: // Cancelar o cerrar diálogo
                // No hace nada, permanece en la aplicación
                break;
        }
    }

    public JComboBox<String> comboEstudiantes = new JComboBox<>();
    ;
    JTable tablaSalidasEstudiantes = new JTable();
    DefaultTableModel modeloTablaSalidasEstudiantes = new DefaultTableModel(new String[]{"Nombre Estudiante", "ID",
        "Motivo de salida", "Fecha de salida", "Hora de salida", "Nombre de oficial"}, 0);

    public void generarTablaSalidasEstudiantes() {
        // Limpia el modelo de la tabla
        modeloTablaSalidasEstudiantes.setRowCount(0);

        // SQL principal para obtener todas las salidas
        String sqlSalidas = "SELECT * FROM salidas_estudiantes";

        try (
                // Creamos un Statement nuevo para la consulta principal
                Statement stmtSalidas = controlador.connection.createStatement(); ResultSet rsSalidas = stmtSalidas.executeQuery(sqlSalidas); // Creamos un segundo Statement para las consultas de estudiantes
                 Statement stmtEstudiante = controlador.connection.createStatement();) {
            while (rsSalidas.next()) {
                String carnet = rsSalidas.getString("carnet");
                String fecha = rsSalidas.getString("fecha");
                String hora = rsSalidas.getString("hora");
                String motivo = rsSalidas.getString("motivo");
                String usuarioGuarda = rsSalidas.getString("nombre_usuario_guarda");
                String nombreGuarda=controlador.buscarGuardaPorUsuario(usuarioGuarda);

                // Definimos la consulta SQL para el estudiante usando el carnet obtenido
                String sqlEstudiante = ""
                        + "SELECT nombre1, nombre2, apellido1, apellido2, cedula "
                        + "FROM estudiantes "
                        + "WHERE carnet = '"
                        + carnet
                        + "'";

                // Ejecutamos la consulta del estudiante correspondiente
                try (ResultSet rsEst = stmtEstudiante.executeQuery(sqlEstudiante)) {
                    String nombreCompleto = "—";
                    String idCedula = "—";

                    if (rsEst.next()) {
                        String n1 = rsEst.getString("nombre1");
                        String n2 = rsEst.getString("nombre2");
                        String a1 = rsEst.getString("apellido1");
                        String a2 = rsEst.getString("apellido2");

                        nombreCompleto = (n1 != null ? n1 : "") + " "
                                + (n2 != null ? n2 : "") + " "
                                + (a1 != null ? a1 : "") + " "
                                + (a2 != null ? a2 : "");
                        idCedula = rsEst.getString("cedula");
                    }

                    // Añadimos la fila al modelo con: nombre completo, cédula, motivo, fecha, hora, usuario guardia
                    modeloTablaSalidasEstudiantes.addRow(new Object[]{
                        nombreCompleto.trim(),
                        idCedula,
                        motivo,
                        fecha,
                        hora,
                        nombreGuarda
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al cargar datos de salidas de estudiantes:\n" + ex.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    public void generarJComboEstudiantes2() {
        String SQL = "SELECT nombre1, nombre2, apellido1, apellido2 FROM estudiantes";
        try {
            ResultSet rs = controlador.statement.executeQuery(SQL);
            comboEstudiantes.removeAllItems();
            if (rs == null) {
                JOptionPane.showMessageDialog(this, "No se encontraron estudiantes.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                while (rs.next()) {

                    String nombre = rs.getString("nombre1") + " " + rs.getString("nombre2") + " "
                            + rs.getString("apellido1") + " " + rs.getString("apellido2");

                    comboEstudiantes.addItem(nombre);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generarJComboEstudiantes(ResultSet rs) {
        comboEstudiantes.removeAllItems();
        if (rs == null) {
            JOptionPane.showMessageDialog(this, "No se encontraron estudiantes.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            while (rs.next()) {
                String nombre = rs.getString("nombre1") + " " + rs.getString("nombre2") + " "
                        + rs.getString("apellido1") + " " + rs.getString("apellido2");
                comboEstudiantes.addItem(nombre);
            }
        } catch (SQLException e) {
            System.out.println("HOLA");
            e.printStackTrace();
        }
    }

    public void consultarEstudiantes(String busqueda) {
        if (busqueda == null || busqueda.isEmpty()) {
            generarJComboEstudiantes2();
            return;
        }

        try {
            String query = "SELECT nombre1, nombre2, apellido1, apellido2 FROM estudiantes WHERE nombre1 LIKE '%"
                    + busqueda + "%' OR nombre2 LIKE '%" + busqueda + "%' OR apellido1 LIKE '%" + busqueda
                    + "%' OR apellido2 LIKE '%" + busqueda + "%' OR carnet LIKE '%" + busqueda + "%'";

            ResultSet rs = controlador.statement.executeQuery(query);

            generarJComboEstudiantes(rs);
            ;

            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar estudiantes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    JComboBox<String> comboBoxFuncionarios = new JComboBox<>();



    public void generarTablaIngresoFuncionarios() {

        modeloTablaIngresoFuncionarios.setRowCount(0);

        String SQL = "SELECT * FROM ingresos";
        try {
            Statement stmt = controlador.connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String id = rs.getString("cedula");

               ResultSet rsFuncionario = controlador.consultarFuncionario(id);
                if (rsFuncionario == null || !rsFuncionario.next()) {
                    continue; // Si no se encuentra el funcionario, saltar a la siguiente iteración
                }

                String nombre1 = rsFuncionario.getString("nombre1");
                String nombre2 = rsFuncionario.getString("nombre2");
                String apellido1 = rsFuncionario.getString("apellido1");
                String apellido2 = rsFuncionario.getString("apellido2");
                String puesto = rsFuncionario.getString("ocupacion");

                String nombreCompleto = nombre1  + " " + (nombre2 != null ? nombre2 + " " : "")
                        + (apellido1 != null ? apellido1 + " " : "")
                        + (apellido2 != null ? apellido2 : "");
              
                String fechaIngreso = rs.getString("fecha");
                String horaIngreso = rs.getString("hora");
                String nombreGuarda = rs.getString(6);
                System.out.println("Nombre guarda: " + nombreGuarda);
                String tipoVehiculo = "";
                String placa = rsFuncionario.getString("placaVehiculo");
                if (placa != null) {
                   tipoVehiculo = controlador.getTipoVehiculo(placa);
                    
                }else{
                    placa = "No aplica";
                }
                
                String nombreGuardaCompleto = controlador.buscarGuardaPorUsuario(nombreGuarda);
                System.out.println("Nombre guarda completo: " + nombreGuardaCompleto);

                 modeloTablaIngresoFuncionarios.addRow(new Object[]{
                      nombreCompleto, id, puesto,tipoVehiculo ,placa, fechaIngreso, horaIngreso, nombreGuardaCompleto
                 });

            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar ingresos de funcionarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void GenerarTablaIngresoExterno() {

        modeloTablaIngresoExterno.setRowCount(0);
        String SQL ="SELECT * FROM ingresos  WHERE tipoIngreso='Externo'";
        try {
            Statement stmt = controlador.connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String id = rs.getString("cedula");
                Statement stmte= controlador.connection.createStatement();
                String SQLPersona = "SELECT nombre1, nombre2, apellido1, apellido2 FROM personas WHERE cedula = '" + id + "'";
                ResultSet rsPersona=stmte.executeQuery(SQLPersona);
                String nombreCompleto="";
                if (rsPersona.next()) {
                    String nombre1 = rsPersona.getString("nombre1");
                    String nombre2 = rsPersona.getString("nombre2");
                    String apellido1 = rsPersona.getString("apellido1");
                    String apellido2 = rsPersona.getString("apellido2");

                    nombreCompleto = (nombre1 != null ? nombre1 : "") + " "
                            + (nombre2 != null ? nombre2 : "") + " "
                            + (apellido1 != null ? apellido1 : "") + " "
                            + (apellido2 != null ? apellido2 : "");
                }
                String nombreUsuarioGuarda = rs.getString("nombre_usuario_guarda");
                String nombreGuarda = controlador.buscarGuardaPorUsuario(nombreUsuarioGuarda);
                String fechaIngreso = rs.getString("fecha");
                String horaIngreso = rs.getString("hora");
                String motivo = rs.getString("motivo");

                modeloTablaIngresoExterno.addRow(new Object[]{
                    nombreCompleto.trim(), id, motivo, fechaIngreso, horaIngreso, nombreGuarda
                });
                

              
              
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar ingresos externos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void GenerarTablaIngresoVehiculoExterno() {
        modeloTablaVehiculoExterno.setRowCount(0);
        for (VehiculoExterno ingreso : controlador.getIngresosVehiculoExterno()) {

            modeloTablaVehiculoExterno.addRow(new Object[]{
                ingreso.getVisitante().getNombre(), ingreso.getVisitante().getId(), ingreso.getMotivo(),
                ingreso.getFechaIngreso(), ingreso.getHoraIngreso().format(controlador.formato),
                ingreso.getNombreGuarda(),
                ingreso.getVehiculo().getPlaca(), ingreso.getVehiculo().getTipoVehiculo(),
                ingreso.getCantidadPasajeros(), ingreso.getCompania(),});

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
