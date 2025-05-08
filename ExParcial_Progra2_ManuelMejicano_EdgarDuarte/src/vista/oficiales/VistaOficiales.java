package vista.oficiales;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Funcionario;
import modelo.IngresoExterno;
import modelo.IngresoFuncionario;
import modelo.Salida;
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

    public VistaOficiales(Controlador controlador) {
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

    private void initComponents() {
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

        

    }

       
    
    
    private void mostrarDialogoCerrar() {
        // Opciones personalizadas
        String[] opciones = { "Cerrar sesión", "Salir", "Cancelar" };
        int opcion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cerrar sesión?",
                "Confirmación de salida",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

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

    JComboBox<String> comboEstudiantes;
    JTable tablaSalidasEstudiantes = new JTable();
    DefaultTableModel modeloTablaSalidasEstudiantes = new DefaultTableModel(new String[] { "Nombre Estudiante", "ID",
            "Motivo de salida", "Fecha de salida", "Hora de salida", "Nombre de oficial" }, 0);

   
    public void generarTablaSalidasEstudiantes() {
        modeloTablaSalidasEstudiantes.setRowCount(0);
        for (Salida salida : controlador.getSalidasEstudiantes()) {
            modeloTablaSalidasEstudiantes.addRow(new Object[] {
                    salida.getPersona().getNombre(),
                    salida.getPersona().getId(),
                    salida.getMotivoSalida(),
                    salida.getFechaSalida(),
                    salida.getHoraSalida().format(controlador.formato),
                    salida.getGuarda().getNombre()
            });
        }
    }

    public void generarJComboEstudiantes() {
        comboEstudiantes.removeAllItems();
        for (int i = 0; i < controlador.getEstudiantes().size(); i++) {
            comboEstudiantes.addItem(controlador.getEstudiantes().get(i).getNombre());
        }
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

    JComboBox<String> comboBoxNombres = new JComboBox<>();

    public void GenerarComboFuncionarios() {
        comboBoxNombres.removeAllItems();
        for (int i = 0; i < controlador.getFuncionarios().size(); i++) {
            comboBoxNombres.addItem(controlador.getFuncionarios().get(i).getNombre());
        }
    }

    public void GenerarTablaIngresoFuncionarios() {

        modeloTablaIngresoFuncionarios.setRowCount(0);

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

   
    public void GenerarTablaIngresoExterno() {

        modeloTablaIngresoExterno.setRowCount(0);
        for (IngresoExterno ingreso : controlador.getIngresosExternos()) {
            modeloTablaIngresoExterno.addRow(new Object[] {
                    ingreso.getVisitante().getNombre(), ingreso.getVisitante().getId(), ingreso.getMotivo(),
                    ingreso.getFechaIngreso(), ingreso.getHoraIngreso().format(controlador.formato),
                    ingreso.getNombreGuarda(),

            });
        }
    }

    public void GenerarTablaIngresoVehiculoExterno() {
        modeloTablaVehiculoExterno.setRowCount(0);
        for (VehiculoExterno ingreso : controlador.getIngresosVehiculoExterno()) {

            modeloTablaVehiculoExterno.addRow(new Object[] {
                    ingreso.getVisitante().getNombre(), ingreso.getVisitante().getId(), ingreso.getMotivo(),
                    ingreso.getFechaIngreso(), ingreso.getHoraIngreso().format(controlador.formato),
                    ingreso.getNombreGuarda(),
                    ingreso.getVehiculo().getPlaca(), ingreso.getVehiculo().getTipoVehiculo(),
                    ingreso.getCantidadPasajeros(), ingreso.getCompania(),
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
