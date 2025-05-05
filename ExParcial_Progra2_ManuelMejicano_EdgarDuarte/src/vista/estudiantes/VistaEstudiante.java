package vista.estudiantes;

import javax.swing.JFrame;
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
    }

    public void mostrar() {
        setVisible(true);
    }


}
