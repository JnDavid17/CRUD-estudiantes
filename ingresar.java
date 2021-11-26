import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

public class ingresar  extends JFrame implements ActionListener  {
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 20);
	JLabel titulo, cedulaTitulo, nombreTitulo, edadTitulo, materiaTitulo, calificacionTitulo,calificacionTitulo2,calificacionTitulo3;
	JTextField cedula, nombre, edad, materia, calificacion, calificacion2, calificacion3;
	JButton volver, ingresar;
	
	static  ArrayList<estudiantes> estudiantesLista = new ArrayList<estudiantes>();
	static  ArrayList<materias> materiasLista = new ArrayList<materias>();
	
	static String archivo_dat = "students.dat";

	public static void main(String[] args) {
		ingresar frame = new ingresar("","");
	}

	public ingresar(String parametro, String valor){
	
		setBounds(100, 100, 500, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);



        this.getContentPane().setBackground(new Color(45,64,89));

		
        volver = new JButton("Volver");
        volver.setBounds(0,0,100,40);
        volver.setFont(font2);
        volver.setBackground(new Color(234, 84, 85));
        volver.setForeground(Color.white);
        add(volver);
        volver.addActionListener(this);
        
		titulo = new JLabel("Añadir estudiante");		
		titulo.setBounds( 165, 30, 250, 60);
		titulo.setFont(font2);
		titulo.setForeground(new Color(255, 212, 96));
		add(titulo); 
		

		cedulaTitulo = new JLabel("Ingrese la cedula");
		cedulaTitulo.setBounds( 55, 90, 250, 60);
		cedulaTitulo.setFont(font2);
		cedulaTitulo.setForeground(Color.white);
		add(cedulaTitulo); 
		

		cedula = new JTextField("");
		cedula.setBounds( 55, 140, 370, 40);
		cedula.setFont(font2);
		add(cedula); 		
		if (parametro.equalsIgnoreCase("Cedula")) {
			cedula.setText(valor);
			cedula.setEditable(false);
		}
		
		nombreTitulo = new JLabel("Ingrese el nombre");
		nombreTitulo.setBounds( 55, 190, 250, 60);
		nombreTitulo.setFont(font2);
		nombreTitulo.setForeground(Color.white);
		add(nombreTitulo); 
		
		nombre = new JTextField("");
		nombre.setBounds( 55, 240, 370, 40);
		nombre.setFont(font2);
		add(nombre); 		
		if (parametro.equalsIgnoreCase("Nombre")) {
			nombre.setText(valor);
			nombre.setEditable(false);
		}
		
		edadTitulo = new JLabel("Ingrese la edad");
		edadTitulo.setBounds( 55, 290, 250, 60);
		edadTitulo.setFont(font2);
		edadTitulo.setForeground(Color.white);
		add(edadTitulo); 
		
		edad= new JTextField("");
		edad.setBounds( 55, 340, 370, 40);
		edad.setFont(font2);
		add(edad); 		 
		if (parametro.equalsIgnoreCase("Edad")) {
			edad.setText(valor);
			edad.setEditable(false);
		}
		
		
		ingresar = new JButton("Ingresar");
		ingresar.setBounds(0, 600, 500, 50);
		ingresar.setFont(font2);
		ingresar.setBackground(new Color(240, 123, 63));
		add(ingresar);
		ingresar.addActionListener(this);
		cargar();
	}
	
	public static void cargar() {
		try{
			FileInputStream archivo = new FileInputStream("data/" + archivo_dat );
			ObjectInputStream entrada = new ObjectInputStream(archivo);
			estudiantesLista = (ArrayList<estudiantes>) entrada.readObject();
			entrada.close();
			archivo.close();
		}
		catch(Exception e){
			//e.printStackTrace();
			System.out.println( "No se pudo abrir el archivo " + archivo_dat  );
			return;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ingresar) {
			try {
				boolean encontrado=false;
				boolean avanzar = true;
				if (cedula.getText().equalsIgnoreCase("")) {
					cedulaTitulo.setForeground(Color.red);
					avanzar = false;
				}
				if(nombre.getText().equalsIgnoreCase("")) {
					nombreTitulo.setForeground(Color.red);
					avanzar = false;
				}
				if(edad.getText().equalsIgnoreCase("")) {
					edadTitulo.setForeground(Color.red);
					avanzar = false;
				}

				
				if (avanzar == true) {

					estudiantes estudiante = new estudiantes();		
					estudiante.setCedula(Integer.parseInt(cedula.getText()));
					
					estudiante.setNombre(nombre.getText());
					
					estudiante.setEdad(Integer.parseInt(edad.getText()));

					
					for(int i = 0; i<estudiantesLista.size(); i++ ){
						if( estudiantesLista.get(i).getCedula() == estudiante.getCedula()){ 
							encontrado = true;
							JOptionPane.showMessageDialog(null, "El estudiante con la cedula "+estudiante.getCedula()+" ya está registrado, por favor ingrese otra cedula");
							break;
						}
					}
					

					if( encontrado == false){
						JOptionPane.showMessageDialog(null, "Estudiante agregado con cedula "+estudiante.getCedula());
						estudiantesLista.add(estudiante);				
						cedula.setText("");
						nombre.setText("");
						edad.setText("");
						grabar();
					}
			}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Por favor digite correctamente en los campos correspondientes");
			}
			
			}
		
		if (e.getSource() == volver) {
			crud inicio = new crud();
			inicio.setVisible(true);
			this.setVisible(false);
		}
		}
	
	public static void grabar() {
		try
		{
			FileOutputStream archivo = new FileOutputStream( "data/" + archivo_dat );
			ObjectOutputStream salida = new ObjectOutputStream(archivo);
			salida.writeObject( estudiantesLista );
			salida.close();
			archivo.close();
		}catch (IOException i){
			// i.printStackTrace();
			System.out.println("No se pudo guardar en " + i );
		}
	}
}
