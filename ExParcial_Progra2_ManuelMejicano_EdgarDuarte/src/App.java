import java.util.ResourceBundle.Control;

import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import controlador.Controlador;
import vista.MenuPrincipal;

// Clase principal que inicializa la aplicación y configura el tema visual.
public class App {
    

    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
           System.out.println("Algo salio mal");
        }

        //instancia de la clase controlador quien es el que controla la apliacion
        
        Controlador controlador = new Controlador();
         
    }

    
}



