import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.*;

public class crud extends JFrame implements ActionListener {

	JButton ingresar, consultar,actualizar, eliminar ;
	
	static final String archivo_dat = "estudiante.dat";
	


    Font font2 = new Font("SAN_SERIF", Font.BOLD, 22);

	public static void main(String[] args) {
		crud frame = new crud();
	}

	public crud(){
		
		setBounds(100, 100, 800, 620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
	
			
	    
        this.getContentPane().setBackground(new Color(45,64,89));


		ingresar = new JButton("Ingresar");		
		ingresar.setBounds( 105, 240, 250, 60);
		ingresar.setFont(font2);
		ingresar.setBackground(new Color(255, 212, 96));
		add(ingresar); 
		ingresar.addActionListener(this);

		consultar = new JButton("Listar");		
		consultar.setBounds( 400, 240, 250, 60);
		consultar.setFont(font2);
		consultar.setBackground(new Color(240, 123, 63));
		add(consultar);
		consultar.addActionListener(this);

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ingresar ) {
			eleccion_ingresar choice = new eleccion_ingresar();
			choice.setVisible(true);
			this.setVisible(false);
		}		
		
		if (e.getSource() == consultar ) {
			consultar read = new consultar(0,10,1,1);
			read.setVisible(true);
			this.setVisible(false);
		}

	}
	
	


}
