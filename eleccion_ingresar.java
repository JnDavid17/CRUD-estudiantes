import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class eleccion_ingresar extends JFrame implements ActionListener{
	JTextField listarCedula, listarNombre, listarEdad;  
	JLabel titulo;
	JButton estudiante, materia;
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 22);
	
	public static void main(String[] args) {
		eleccion_ingresar frame = new eleccion_ingresar();
	}

	eleccion_ingresar(){
		setBounds(100, 100, 700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		Color color = new Color(45,64,89);
        this.getContentPane().setBackground(color);
        
        titulo = new JLabel ("¿Que desea ingresar?");
		titulo.setBounds( 235, 30, 400, 60);
		titulo.setFont(font2);
		titulo.setForeground(new Color(255, 212, 96));
		add(titulo); 
		
		estudiante= new JButton("Estudiantes");		
		estudiante.setBounds( 85, 150, 250, 60);
		estudiante.setFont(font2);
		estudiante.setBackground(new Color(255, 212, 96));
		add(estudiante); 
		estudiante.addActionListener(this);

		materia= new JButton("Materias");		
		materia.setBounds( 345, 150, 250, 60);
		materia.setFont(font2);
		materia.setBackground(new Color(240, 123, 63));
		add(materia);
		materia.addActionListener(this);

//    	size = estudiantesLista.size();
//    	JButton[] botonCedula = new JButton [size];
//    	JTextField[] listarNombre = new JTextField [size];
//    	JTextField [] listarEdad = new JTextField [size];
//    	JButton [] botonMaterias = new JButton [size];
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == estudiante ) {
			ingresar create = new ingresar("","");
			create.setVisible(true);
			this.setVisible(false);
		}		
		
		if (e.getSource() == materia ) {
			ingresar_materias createMaterias = new ingresar_materias(false, 0, "");
			createMaterias.setVisible(true);
			this.setVisible(false);
		}		
	}
}
