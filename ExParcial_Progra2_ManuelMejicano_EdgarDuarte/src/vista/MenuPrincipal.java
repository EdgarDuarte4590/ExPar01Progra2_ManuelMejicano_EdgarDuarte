package vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MenuPrincipal extends JFrame{

    public MenuPrincipal(){
        setTitle("Menu Principal");
        setSize(1366, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        inicializarComponentes();
    }

    JTabbedPane tabbedPane; 
    PanelIngresos panelIngresos;

   public void inicializarComponentes(){
        setLayout(null);
        setResizable(false);  
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1366, 720); 
        add(tabbedPane);
        panelIngresos = new PanelIngresos();

        tabbedPane.addTab("Pestaña de Inicio", panelIngresos);
    } 
}

