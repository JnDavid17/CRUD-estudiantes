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

public class eleccion_materias extends JFrame implements ActionListener {

	JTextField listarMateria, listarNota1, listarNota2, listarNota3, listarNota4,tituloMateria, tituloCalificacion1, tituloCalificacion2, tituloCalificacion3, tituloDefinitiva;
;  
	JLabel titulo;
	JButton eliminar, modificar, volver;
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 18);
	Font font3 = new Font("SAN_SERIF", Font.BOLD, 32);

	static  ArrayList<estudiantes> estudiantesLista = new ArrayList<estudiantes>();
	static  ArrayList<materias> materiasLista = new ArrayList<materias>();
	
	static String nombreMateriaAux;
	
	static String archivo_dat = "students.dat";
	static String archivo2_dat = "materias.dat";

	static materias materiaCopia = new materias();

	int cedula;
	int y = 150;
	public static void main(String[] args) {
		eleccion_materias frame = new eleccion_materias	(null, false);
	}

	eleccion_materias(materias materia, boolean eliminado){
		

		setBounds(100, 100, 1200, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		Color color = new Color(45,64,89);
        this.getContentPane().setBackground(color);
        
        cargar();
        
        volver = new JButton("Volver");
        volver.setBounds(0,0,100,40);
        volver.setFont(font2);
        volver.setBackground(new Color(234, 84, 85));
        add(volver);
        volver.addActionListener(this);
        
        if (eliminado == false) {
    		materiaCopia = materia;
    		nombreMateriaAux = materia.getNombreMateria();
    		tituloMateria = new JTextField("Materia");
    		tituloMateria.setBackground(new Color(45,64,89));
    		tituloMateria.setForeground(Color.white);
    		tituloMateria.setEditable(false);
    		tituloMateria.setHorizontalAlignment(JTextField.CENTER);
    		tituloMateria.setBounds(40, 100, 380, 50);
    		tituloMateria.setFont(font2);
    		add(tituloMateria);
    		
    		tituloCalificacion1 = new JTextField("Primer corte");
    		tituloCalificacion1.setBackground(new Color(45,64,89));
    		tituloCalificacion1.setForeground(Color.white);
    		tituloCalificacion1.setEditable(false);
    		tituloCalificacion1.setHorizontalAlignment(JTextField.CENTER);
    		tituloCalificacion1.setBounds(420, 100, 180, 50);
    		tituloCalificacion1.setFont(font2);
    		add(tituloCalificacion1);
    		
    		tituloCalificacion2 = new JTextField("Segundo corte");
    		tituloCalificacion2.setBackground(new Color(45,64,89));
    		tituloCalificacion2.setForeground(Color.white);
    		tituloCalificacion2.setEditable(false);
    		tituloCalificacion2.setHorizontalAlignment(JTextField.CENTER);
    		tituloCalificacion2.setBounds(600, 100, 180, 50);
    		tituloCalificacion2.setFont(font2);
    		add(tituloCalificacion2);
    		
    		tituloCalificacion3 = new JTextField("Tercer corte");
    		tituloCalificacion3.setBackground(new Color(45,64,89));
    		tituloCalificacion3.setForeground(Color.white);
    		tituloCalificacion3.setEditable(false);
    		tituloCalificacion3.setHorizontalAlignment(JTextField.CENTER);
    		tituloCalificacion3.setBounds(780, 100, 180, 50);
    		tituloCalificacion3.setFont(font2);
    		add(tituloCalificacion3);

    		
    		tituloDefinitiva = new JTextField("Definitiva");
    		tituloDefinitiva.setBackground(new Color(45,64,89));
    		tituloDefinitiva.setForeground(Color.white);
    		tituloDefinitiva.setEditable(false);
    		tituloDefinitiva.setHorizontalAlignment(JTextField.CENTER);
    		tituloDefinitiva.setBounds(960, 100, 180, 50);
    		tituloDefinitiva.setFont(font2);
    		add(tituloDefinitiva);
            
            cedula = materia.getIdEstudiante();
            titulo = new JLabel ("La materia seleccionada del estudiante con cedula "+cedula+" es:");
    		titulo.setBounds( 365, 30, 800, 60);
    		titulo.setFont(font2);
    		titulo.setForeground(new Color(255, 212, 96));
    		add(titulo); 
    		
    		listarMateria = new JTextField(""+materia.getNombreMateria());
    		listarMateria.setBackground(new Color(45,64,89));
    		listarMateria.setForeground(Color.white);
    		listarMateria.setEditable(false);
    		listarMateria.setHorizontalAlignment(JTextField.CENTER);
    		listarMateria.setBounds(40, y, 380, 50);
    		listarMateria.setFont(font2);
    		add(listarMateria);
    		
    		
    		listarNota1 = new JTextField(""+materia.getPrimerCorte());
    		listarNota1.setBackground(new Color(45,64,89));
    		listarNota1.setForeground(Color.white);
    		listarNota1.setEditable(false);
    		listarNota1.setHorizontalAlignment(JTextField.CENTER);
    		listarNota1.setBounds(420, y, 180, 50);
    		listarNota1.setFont(font2);

    		add(listarNota1);
    		
    		
    		listarNota2 = new JTextField(""+materia.getSegundoCorte());
    		listarNota2.setBackground(new Color(45,64,89));
    		listarNota2.setForeground(Color.white);
    		listarNota2.setEditable(false);
    		listarNota2.setHorizontalAlignment(JTextField.CENTER);
    		listarNota2.setBounds(600, y, 180, 50);
    		listarNota2.setFont(font2);
    		add(listarNota2);
    		
    		listarNota3 = new JTextField(""+materia.getTercerCorte());
    		listarNota3.setBackground(new Color(45,64,89));
    		listarNota3.setForeground(Color.white);
    		listarNota3.setEditable(false);
    		listarNota3.setHorizontalAlignment(JTextField.CENTER);
    		listarNota3.setBounds(780, y, 180, 50);
    		listarNota3.setFont(font2);
    		add(listarNota3);
    		
    		listarNota4 = new JTextField(""+materia.getDefinitiva());
    		listarNota4.setBackground(new Color(45,64,89));
    		listarNota4.setForeground(Color.white);
    		listarNota4.setEditable(false);
    		listarNota4.setHorizontalAlignment(JTextField.CENTER);
    		listarNota4.setBounds(960, y, 180, 50);
    		listarNota4.setFont(font2);
    		add(listarNota4);
    		
    		eliminar = new JButton("Eliminar materia");		
    		eliminar.setBounds( 155, 270, 250, 60);
    		eliminar.setFont(font2);
    		eliminar.setBackground(new Color(255, 212, 96));
    		add(eliminar); 
    		eliminar.addActionListener(this);

    		modificar = new JButton("Modificar materia");		
    		modificar.setBounds( 415, 270, 250, 60);
    		modificar.setFont(font2);
    		modificar.setBackground(new Color(240, 123, 63));
    		add(modificar);
    		modificar.addActionListener(this);
    
		}else {
			titulo = new JLabel("La materia fue eliminada");
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
		int contador = 0;
		if (e.getSource() == eliminar ) {
			JOptionPane.showMessageDialog(null, "Se eliminará la materia del estudiante con cedula "+cedula);
			for (int i = 0; i < materiasLista.size(); i++) {
				if (materiasLista.get(i).getIdEstudiante() == cedula && materiasLista.get(i).getNombreMateria().equalsIgnoreCase(nombreMateriaAux)) {
					materiasLista.remove(i);
					materiaEncontrada = true;
					break;
				}
			}
			
			grabar();
			
			if (materiaEncontrada == true) {
				eleccion_materias again = new eleccion_materias(null, true);
				again.setVisible(true);
				this.setVisible(false);
			}
			
		}		
		
		if (e.getSource() == modificar ) {
			modificar_materia update = new modificar_materia(materiaCopia);
			update.setVisible(true);
			this.setVisible(false);
		}
		
		if (e.getSource() == volver) {
			consultar_materia volverAtras = new consultar_materia(materiaCopia.getIdEstudiante(),materiaCopia.getNombreMateria(),0,10,1,1);
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
