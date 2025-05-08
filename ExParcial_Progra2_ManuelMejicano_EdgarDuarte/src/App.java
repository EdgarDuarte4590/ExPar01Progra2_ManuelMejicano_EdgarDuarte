import java.util.ResourceBundle.Control;

import javax.swing.UIManager;


import com.formdev.flatlaf.themes.FlatMacLightLaf;

import controlador.Controlador;
import vista.MenuPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
           System.out.println("Algo salio mal");
        }
        Controlador controlador = new Controlador();
         
    }

    
}



