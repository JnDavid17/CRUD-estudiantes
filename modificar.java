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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class modificar extends JFrame implements ActionListener  {
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 20);
	JButton ingresar, volver;
	JLabel titulo, cedulaTitulo, nombreTitulo, edadTitulo;
	JTextField cedula, nombre, edad;
	static  ArrayList<estudiantes> estudiantesLista = new ArrayList<estudiantes>();
	static String archivo_dat = "students.dat";
	static estudiantes copia = new estudiantes();
	static boolean entro = false; 
	
	public static void main(String[] args) {
		modificar frame = new modificar(null);
	}

	public modificar(estudiantes estudiante){
		
		setBounds(100, 100, 500, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		Color color = new Color(45,64,89);
        this.getContentPane().setBackground(color);

        volver = new JButton("Volver");
        volver.setBounds(0,0,100,40);
        volver.setFont(font2);
        volver.setBackground(new Color(234, 84, 85));
        volver.setForeground(Color.white);
        add(volver);
        volver.addActionListener(this);
        
		titulo = new JLabel("Modificar estudiante");		
		titulo.setBounds( 145, 30, 250, 60);
		titulo.setFont(font2);
		titulo.setForeground(new Color(255, 212, 96));
		add(titulo); 
		
		cedulaTitulo = new JLabel("Cedula del estudiante");
		cedulaTitulo.setBounds( 55, 90, 250, 60);
		cedulaTitulo.setFont(font2);
		cedulaTitulo.setForeground(Color.white);
		add(cedulaTitulo); 
		
		cedula = new JTextField("");
		cedula.setBounds( 55, 140, 370, 40);
		cedula.setFont(font2);
		cedula.setEditable(false);
		add(cedula); 		
		cedula.setText(String.valueOf(estudiante.getCedula()));
		
		nombreTitulo = new JLabel("Ingrese el nombre");
		nombreTitulo.setBounds( 55, 190, 250, 60);
		nombreTitulo.setFont(font2);
		nombreTitulo.setForeground(Color.white);
		add(nombreTitulo); 
		
		nombre = new JTextField("");
		nombre.setBounds( 55, 240, 370, 40);
		nombre.setFont(font2);
		add(nombre); 		
		
		edadTitulo = new JLabel("Ingrese la edad");
		edadTitulo.setBounds( 55, 290, 250, 60);
		edadTitulo.setFont(font2);
		edadTitulo.setForeground(Color.white);
		add(edadTitulo); 
		
		edad= new JTextField("");
		edad.setBounds( 55, 340, 370, 40);
		edad.setFont(font2);
		add(edad); 		 
		
		ingresar = new JButton("Modificar estudiante");
		ingresar.setBounds(0,600,500,60);
		ingresar.setFont(font2);
		ingresar.setBackground(new Color(240, 123, 63));
		ingresar.addActionListener(this);

		add(ingresar);
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

					for (int i = 0; i < estudiantesLista.size(); i++) {
						if (estudiantesLista.get(i).getCedula() == Integer.parseInt(cedula.getText())) {
							JOptionPane.showMessageDialog(null, "Datos anteriores: \nNombre:"+estudiantesLista.get(i).getNombre()+"\nEdad:"+estudiantesLista.get(i).getEdad());
							estudiantesLista.get(i).setEdad(Integer.parseInt(edad.getText()));
							estudiantesLista.get(i).setNombre(nombre.getText());
							JOptionPane.showMessageDialog(null, "Datos actualizados: \nNombre:"+estudiantesLista.get(i).getNombre()+"\nEdad:"+estudiantesLista.get(i).getEdad());
							nombre.setText("");
							edad.setText("");
							
							copia.setCedula(estudiantesLista.get(i).getCedula());
							copia.setEdad(estudiantesLista.get(i).getEdad());
							copia.setNombre(estudiantesLista.get(i).getNombre());

							grabar();
							break;
							
						}
					}

			}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Por favor digite correctamente en los campos correspondientes");
			}
			
		}
		
		if (e.getSource() == volver) {
				eleccion_estudiante volverAtras = new eleccion_estudiante(copia, false);
				volverAtras.setVisible(true);
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
