package vista.oficiales;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelSalidaEstudiante extends JPanel {
    VistaOficiales vistaOficiales;

    

    public PanelSalidaEstudiante(VistaOficiales vistaOficiales) throws SQLException {
        this.vistaOficiales = vistaOficiales;
        
            initComponents();
      
    }

public JPanel initComponents() throws SQLException  {
    JPanel panelEstSalidas = new JPanel();
    panelEstSalidas.setLayout(null); // Establecer el diseño nulo para el panel de ingreso
    panelEstSalidas.setBounds(0, 0, 1366, 720);

    JLabel label = new JLabel("Registra las salidas de los estudiantes del Colegio Técnico Profesional de Upala");
    label.setBounds(25, 50, 700, 30);
    label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
    panelEstSalidas.add(label);

    JLabel label7 = new JLabel("Búsqueda por carnet :");
    label7.setBounds(50, 100, 200, 30);
    panelEstSalidas.add(label7);

    JTextField txtBusqueda = new JTextField();
    txtBusqueda.setBounds(50, 130, 200, 30);
    panelEstSalidas.add(txtBusqueda);
    txtBusqueda.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String busqueda = txtBusqueda.getText();
            if (!busqueda.isEmpty()) {
                vistaOficiales.consultarEstudiantes(busqueda);
            } else {
                vistaOficiales.generarJComboEstudiantes2();
            }
        }
    });

    JButton btnBuscar = new JButton("Buscar");
    btnBuscar.setIcon(new ImageIcon("src/resources/icon_busqueda_24.png"));
    btnBuscar.setHorizontalTextPosition(SwingConstants.LEFT);
    btnBuscar.setIconTextGap(15);
    btnBuscar.setToolTipText("Buscar estudiante por número de carnet");
    btnBuscar.setBounds(260, 130, 130, 30);
    btnBuscar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
    btnBuscar.setBackground(new java.awt.Color(0xFF054FBE));
    btnBuscar.setForeground(Color.WHITE);
    btnBuscar.addActionListener(e -> {
        String carnet = txtBusqueda.getText();
        for (int i = 0; i < vistaOficiales.controlador.getEstudiantes().size(); i++) {
            if (carnet.equals(vistaOficiales.controlador.getEstudiantes().get(i).getCarnet())) {
                vistaOficiales.comboEstudiantes.setSelectedIndex(i);
                return;
            }
        }
        vistaOficiales.comboEstudiantes.setSelectedIndex(-1);
        JOptionPane.showMessageDialog(null, "No se encontro el estudiante con ese número de carnet: " + carnet);
    });
    panelEstSalidas.add(btnBuscar);

    JLabel label2 = new JLabel("Seleccione el estudiante:");
    label2.setBounds(400, 100, 500, 30);
    panelEstSalidas.add(label2);

    vistaOficiales.comboEstudiantes.setBounds(400, 130, 280, 30);
    panelEstSalidas.add(vistaOficiales.comboEstudiantes);

    vistaOficiales.comboEstudiantes.addActionListener(e->{     
        // acción cuando cambie selección (si se requiere)
    });

    JLabel label3 = new JLabel("Motivo de salida:");
    label3.setBounds(700, 100, 200, 30);
    panelEstSalidas.add(label3);

    JComboBox<String> comboBoxMotivo = new JComboBox<>(
            new String[] { "Salida por cita médica o dental", "Salida por problemas de salud",
                    "Salida por llamado de padres o encargados", "Fallecimiento de familiar o allegado",
                    "Salida de almuerzo",
                    "Salida por tarde libre", "Salida por ausencia de profesor", "Salida por otro motivo" });
    comboBoxMotivo.setBounds(700, 130, 280, 30);
    panelEstSalidas.add(comboBoxMotivo);

    JButton btnRegistrar = new JButton("Aprobar salida");
    btnRegistrar.setIcon(new ImageIcon("src/resources/icon_check.png"));
    btnRegistrar.setBounds(1020, 130, 200, 30);
    btnRegistrar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
    btnRegistrar.setBackground(new java.awt.Color(0xFF2BA76B));
    btnRegistrar.setForeground(Color.WHITE);
    btnRegistrar.addActionListener(e -> {

        ResultSet rs = vistaOficiales.controlador.consultarEstudiante(vistaOficiales.comboEstudiantes.getSelectedItem().toString());
        if (rs == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el estudiante seleccionado.");
            return;
        }

        String motivoSalida = (String) comboBoxMotivo.getSelectedItem();
        LocalTime horaSalida = LocalTime.now();
        LocalDate fechaSalida = LocalDate.now();
        String nombreGuarda = vistaOficiales.controlador.getIdOficialActual();
        try {
            if (rs.next()) {
                String carnetEstudiante = rs.getString("carnet");
                vistaOficiales.controlador.registrarSalidaEstudiante(carnetEstudiante, fechaSalida, horaSalida, motivoSalida, nombreGuarda);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró información del estudiante seleccionado.");
                return;
            }
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, "Error al obtener el carnet del estudiante.");
            e1.printStackTrace();
            return;
        }

        vistaOficiales.generarTablaSalidasEstudiantes();
        vistaOficiales.generarJComboEstudiantes2();

        // Limpiar los campos después de registrar
        vistaOficiales.comboEstudiantes.setSelectedIndex(0);
        comboBoxMotivo.setSelectedIndex(0);
    });
    panelEstSalidas.add(btnRegistrar);

    
    JButton btnEliminar = new JButton("Eliminar Salida");
     btnEliminar.setToolTipText("Haga clic para eliminar un oficial seleccionado");
        btnEliminar.setIcon(new ImageIcon("src/resources/icon_eliminar.png"));
    btnEliminar.setBounds(1020, 170, 200, 30); // Justo debajo de "Aprobar salida"
    btnEliminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
    btnEliminar.setBackground(Color.RED);
    btnEliminar.setForeground(Color.WHITE);
    btnEliminar.addActionListener(e -> {
        
        
    });
    panelEstSalidas.add(btnEliminar);
    

    JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaSalidasEstudiantes);
    panelEstSalidas.add(scrollPane);
    vistaOficiales.tablaSalidasEstudiantes.setModel(vistaOficiales.modeloTablaSalidasEstudiantes);
    scrollPane.setBounds(25, 200, 1300, 400);
    vistaOficiales.tablaSalidasEstudiantes.setBounds(0, 0, 100, 500);

    vistaOficiales.generarJComboEstudiantes2();
    vistaOficiales.generarTablaSalidasEstudiantes();

    return panelEstSalidas;
}


}
