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

public class eleccion_estudiante  extends JFrame implements ActionListener {

	JTextField listarCedula, listarNombre, listarEdad;  
	JLabel titulo;
	JButton eliminar, modificar, volver;
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 18);
	Font font3 = new Font("SAN_SERIF", Font.BOLD, 32);

	static  ArrayList<estudiantes> estudiantesLista = new ArrayList<estudiantes>();
	static  ArrayList<materias> materiasLista = new ArrayList<materias>();

	static String archivo_dat = "students.dat";
	static String archivo2_dat = "materias.dat";
	
	static estudiantes copia = new estudiantes();
	
	int cedula;
	int y = 150;
	public static void main(String[] args) {
		eleccion_estudiante frame = new eleccion_estudiante(null, false);
	}

	eleccion_estudiante(estudiantes estudiantes, boolean eliminado){
		setBounds(100, 100, 800, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		copia = estudiantes;
		Color color = new Color(45,64,89);
        this.getContentPane().setBackground(color);
        
        volver = new JButton("Volver");
        volver.setBounds(0,0,100,40);
        volver.setFont(font2);
        volver.setBackground(new Color(234, 84, 85));
        volver.setForeground(Color.white);
        add(volver);
        volver.addActionListener(this);
        
        if (eliminado == false) {
        	cargar();
            
            cedula = estudiantes.getCedula();
            titulo = new JLabel ("El estudiante seleccionado es:");
    		titulo.setBounds( 265, 30, 400, 60);
    		titulo.setFont(font2);
    		titulo.setForeground(new Color(255, 212, 96));
    		add(titulo); 
    		
    		listarCedula = new JTextField(""+estudiantes.getCedula());
    		listarCedula.setBackground(new Color(45,64,89));
    		listarCedula.setForeground(Color.white);
    		listarCedula.setEditable(false);
    		listarCedula.setHorizontalAlignment(JTextField.CENTER);
    		listarCedula.setBounds(140, y, 180, 50);
    		listarCedula.setFont(font2);
    		add(listarCedula);
    		
    		
    		listarNombre = new JTextField(""+estudiantes.getNombre());
    		listarNombre.setBackground(new Color(45,64,89));
    		listarNombre.setForeground(Color.white);
    		listarNombre.setEditable(false);
    		listarNombre.setHorizontalAlignment(JTextField.CENTER);
    		listarNombre.setBounds(320, y, 180, 50);
    		listarNombre.setFont(font2);

    		add(listarNombre);
    		
    		
    		listarEdad = new JTextField(""+estudiantes.getEdad());
    		listarEdad.setBackground(new Color(45,64,89));
    		listarEdad.setForeground(Color.white);
    		listarEdad.setEditable(false);
    		listarEdad.setHorizontalAlignment(JTextField.CENTER);
    		listarEdad.setBounds(500, y, 180, 50);
    		listarEdad.setFont(font2);
    		add(listarEdad);
    		
    		eliminar = new JButton("Eliminar estudiante");		
    		eliminar.setBounds( 155, 270, 250, 60);
    		eliminar.setFont(font2);
    		eliminar.setBackground(new Color(255, 212, 96));
    		add(eliminar); 
    		eliminar.addActionListener(this);

    		modificar = new JButton("Modificar estudiante");		
    		modificar.setBounds( 415, 270, 250, 60);
    		modificar.setFont(font2);
    		modificar.setBackground(new Color(240, 123, 63));
    		add(modificar);
    		modificar.addActionListener(this);
		}else {
			titulo = new JLabel("El estudiante fue eliminado");
			titulo.setBounds( 195, 150, 800, 60);
			titulo.setFont(font3);
			titulo.setForeground(new Color(255, 212, 96));
			add(titulo); 
		}
        

	}

	public static void cargar() {
		try{
			FileInputStream archivo = new FileInputStream("data/" + archivo_dat );
			ObjectInputStream entrada = new ObjectInputStream(archivo);
			estudiantesLista = (ArrayList<estudiantes>) entrada.readObject();

			FileInputStream archivo2 = new FileInputStream("data/" + archivo2_dat );
			ObjectInputStream entrada2 = new ObjectInputStream(archivo2);
			materiasLista = (ArrayList<materias>) entrada2.readObject();

			entrada.close();
			archivo.close();
		}
		catch(Exception e){
			//e.printStackTrace();
			System.out.println( "No se pudo abrir el archivo " + e   );
			return;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean materiaEncontrada = false;
		int j = 0;
		boolean estudianteEncontrado = true;
		int contador = 0;
		if (e.getSource() == eliminar ) {
			JOptionPane.showMessageDialog(null, "Se eliminará al estudiante con la cedula "+cedula);
			for (int i = 0; i < estudiantesLista.size(); i++) {
				if (estudiantesLista.get(i).getCedula() == cedula) {
					estudiantesLista.remove(i);
					estudianteEncontrado = true;
					break;
				}
			}
			
			while (j < materiasLista.size()) {
				if (materiasLista.get(j).getIdEstudiante() == cedula) {
					contador++;
					materiaEncontrada = true;
					materiasLista.remove(j);
					j = 0;
				}else {
					j++;
				}
			}
			

			
			if (materiaEncontrada == true && contador > 1) {
				JOptionPane.showMessageDialog(null, "El estudiante cursaba un total de "+contador+" las cuales también se eliminarán junto a su registro");
			}else if (materiaEncontrada == true && contador == 1) {
				JOptionPane.showMessageDialog(null, "El estudiante cursaba solo una materia, la cuál será eliminada junto a su registro");
			}
			grabar();
			
			if (estudianteEncontrado == true) {
				eleccion_estudiante again = new eleccion_estudiante(null, true);
				again.setVisible(true);
				this.setVisible(false);
			}

		}
		
		if (e.getSource() == modificar ) {
			modificar update = new modificar(copia);
			update.setVisible(true);
			this.setVisible(false);
		}
		
		if (e.getSource() == volver) {
			consultar volverAtras = new consultar(0,10,1,1);
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
			
			FileOutputStream archivo2 = new FileOutputStream( "data/" + archivo2_dat );
			ObjectOutputStream salida2 = new ObjectOutputStream(archivo2);
			salida2.writeObject( materiasLista );

			salida2.close();
			archivo2.close();
		}catch (IOException i){
			// i.printStackTrace();
			System.out.println("No se pudo guardar en " + archivo2_dat );
		}
	}

}
