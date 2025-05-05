package vista.estudiantes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.Controlador;


public class VistaEstudiante  extends JFrame{
    Controlador controlador;

    JTabbedPane tabbedPane;
    public VistaEstudiante(Controlador controlador) {
        setTitle("Vista Estudiante");
        setSize(1366, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(null); 
        this.controlador = controlador; 
    }

    public void inicializarComponentes(){
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0,0, 1366, 720);
        super.add(tabbedPane);


    }

    public JPanel panelSolicitarSalida(){
        JPanel panel = new JPanel();

        return panel;
    }
    public void mostrar() {
        setVisible(true);
    }


}
