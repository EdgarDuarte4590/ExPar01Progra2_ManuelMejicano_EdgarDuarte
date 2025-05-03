package vista.oficiales;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.Controlador;

public class Login extends JFrame{
    Controlador controlador; // Instancia del controlador

    Container container;

        public Login(Controlador controlador) {
            this.controlador = controlador; // Inicializar el controlador
            setTitle("Inicio de Sesión de Oficiales");
            setSize(500, 400);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null); 
            setLayout(null); 
            inicializarComponentes();
            this.setContentPane(container);
        }

        public void inicializarComponentes(){
            container = new Container();
            container.setLayout(null);
            container.setBounds(0, 0, 500, 400); 

            JLabel label = new JLabel("Inicio de Sesión de Oficiales");
            label.setBounds(150, 50, 200, 30);
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); 
            container.add(label);  

            JLabel label2 = new JLabel("Escriba el ID de Acceso:");
            label2.setBounds(180, 100, 200, 30);
            container.add(label2); 

            JTextField idAcceso = new JTextField();
            idAcceso.setBounds(150, 130, 200, 30);
            container.add(idAcceso);
            idAcceso.setToolTipText("Escriba el ID de Acceso"); 


            JLabel label3 = new JLabel("Escriba la Contraseña:");
            label3.setBounds(180, 170, 200, 30);
            container.add(label3);

            JTextField contrasena = new JTextField();
            contrasena.setBounds(150, 200, 200, 30);
            container.add(contrasena);
            contrasena.setToolTipText("Escriba la Contraseña");

            JButton btnIniciar = new JButton("Iniciar Sesión");
            btnIniciar.setBounds(180, 250, 150, 30);
            btnIniciar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnIniciar.setBackground(new Color(0xFF020123));
            btnIniciar.setForeground(Color.WHITE);
            container.add(btnIniciar);
        }
    
        
}
