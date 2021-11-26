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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ingresar_materias extends JFrame implements ActionListener {
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 20);
	JLabel titulo, cedulaTitulo, nombreTitulo, edadTitulo, materiaTitulo, calificacionTitulo,calificacionTitulo2,calificacionTitulo3;
	JTextField  nombre, edad, materia, calificacion, calificacion2, calificacion3;
	JButton volver, ingresar;
	
	JComboBox cedula = new JComboBox();
	
	static  ArrayList<estudiantes> estudiantesLista = new ArrayList<estudiantes>();
	static  ArrayList<materias> materiasLista = new ArrayList<materias>();
	
	static String nombreAux;
	static int cedulaVolver;
	
	static boolean redirigidoVolver = false;
	static int auxCedula;
	static String archivo_dat = "students.dat";
	static String archivo2_dat = "materias.dat";

	public static void main(String[] args) {
		ingresar_materias frame = new ingresar_materias(false, 0, "");
	}

	public ingresar_materias(boolean redirigido, int cedulaAux, String nombre){
	
		setBounds(100, 100, 500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);


		redirigidoVolver = redirigido;
		cedulaVolver = cedulaAux;
		nombreAux = nombre;
		cargar();

        this.getContentPane().setBackground(new Color(45,64,89));

        volver = new JButton("Volver");
        volver.setBounds(0,0,100,40);
        volver.setFont(font2);
        volver.setBackground(new Color(234, 84, 85));
        volver.setForeground(Color.white);
        add(volver);
        volver.addActionListener(this);
        
		titulo = new JLabel("Añadir materia");		
		titulo.setBounds( 165, 30, 250, 60);
		titulo.setFont(font2);
		titulo.setForeground(new Color(255, 212, 96));
		add(titulo); 
		
		cedulaTitulo = new JLabel("Ingrese la cedula");
		cedulaTitulo.setBounds( 55, 90, 250, 60);
		cedulaTitulo.setFont(font2);
		cedulaTitulo.setForeground(Color.white);
		add(cedulaTitulo); 
		
		
		cedula = new JComboBox();
		if (redirigido == true) {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			cedula.addItem(cedulaAux);
		}else {
			cedula.addItem("");
			for (int i = 0; i < estudiantesLista.size(); i++) {
				int aux = estudiantesLista.get(i).getCedula();
				cedula.addItem(aux);
			}
		}

		cedula.setBounds( 55, 140, 370, 40);
		cedula.setFont(font2);
		add(cedula); 		
		
		materiaTitulo = new JLabel("Ingrese la materia");
		materiaTitulo.setBounds( 55, 190, 250, 60);
		materiaTitulo.setFont(font2);
		materiaTitulo.setForeground(Color.white);
		add(materiaTitulo); 
		
		materia = new JTextField("");
		materia.setBounds( 55, 240, 370, 40);
		materia.setFont(font2);
		add(materia); 		
				
		
		calificacionTitulo = new JLabel("Ingrese la calificacion del primer corte");
		calificacionTitulo.setBounds( 55, 290, 550, 60);
		calificacionTitulo.setFont(font2);
		calificacionTitulo.setForeground(Color.white);
		add(calificacionTitulo); 
		
		calificacion= new JTextField("");
		calificacion.setBounds( 55, 340, 370, 40);
		calificacion.setFont(font2);
		add(calificacion); 		 
		
		
		calificacionTitulo2 = new JLabel("Ingrese la calificacion del segundo corte");
		calificacionTitulo2.setBounds( 55, 390, 550, 60);
		calificacionTitulo2.setFont(font2);
		calificacionTitulo2.setForeground(Color.white);
		add(calificacionTitulo2); 
		
		calificacion2= new JTextField("");
		calificacion2.setBounds( 55, 440, 370, 40);
		calificacion2.setFont(font2);
		add(calificacion2); 		 
		
		
		calificacionTitulo3 =  new JLabel("Ingrese la calificacion del tercer corte");
		calificacionTitulo3.setBounds( 55, 490, 550, 60);
		calificacionTitulo3.setFont(font2);
		calificacionTitulo3.setForeground(Color.white);
		add(calificacionTitulo3); 
		
		calificacion3= new JTextField("");
		calificacion3.setBounds( 55, 540, 370, 40);
		calificacion3.setFont(font2);
		add(calificacion3); 		 
		
		ingresar = new JButton("Ingresar");
		ingresar.setBounds(0, 710, 500, 50);
		ingresar.setFont(font2);
		ingresar.setBackground(new Color(240, 123, 63));
		add(ingresar);
		ingresar.addActionListener(this);
	}
	
	public static void cargar() {
		try{
			FileInputStream archivo = new FileInputStream("data/" + archivo_dat );
			ObjectInputStream entrada = new ObjectInputStream(archivo);
			estudiantesLista = (ArrayList<estudiantes>) entrada.readObject();
			System.out.println( "Se ha cargado el archivo " + archivo_dat );

			FileInputStream archivo2 = new FileInputStream("data/" + archivo2_dat );
			ObjectInputStream entrada2 = new ObjectInputStream(archivo2);
			materiasLista = (ArrayList<materias>) entrada2.readObject();
			System.out.println( "Se ha cargado el archivo " + archivo2_dat );

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
		if (e.getSource() == ingresar) {
			try {
				boolean encontrado=false;
				boolean avanzar = true;
				String cedulaAux = cedula.getSelectedItem().toString();
				if (cedulaAux.equalsIgnoreCase("")) {
					cedulaTitulo.setForeground(Color.red);
					avanzar = false;
				}
				if(materia.getText().equalsIgnoreCase("")) {
					materiaTitulo.setForeground(Color.red);
					avanzar = false;
				}
				if(calificacion.getText().equalsIgnoreCase("")) {
					calificacionTitulo.setForeground(Color.red);
					avanzar = false;
				}
				if(calificacion2.getText().equalsIgnoreCase("")) {
					calificacionTitulo2.setForeground(Color.red);
					avanzar = false;
				}
				if(calificacion3.getText().equalsIgnoreCase("")) {
					calificacionTitulo3.setForeground(Color.red);
					avanzar = false;
				}
				
				if (avanzar == true) {

					
					materias asignaturas = new materias();
					
					asignaturas.setIdEstudiante(Integer.parseInt(cedula.getSelectedItem().toString()));
					asignaturas.setNombreMateria(materia.getText());
					
			
					
					float aux1 = Float.parseFloat(calificacion.getText());
					float aux2 = Float.parseFloat(calificacion2.getText());
					float aux3 = Float.parseFloat(calificacion3.getText());
					
					float aux4 = (float) ((float) (aux1*0.30) + (aux2 * 0.30) + (aux3 * 0.40));
					
					asignaturas.setPrimerCorte(aux1);
					asignaturas.setSegundoCorte(aux2);
					asignaturas.setTercerCorte(aux3);
					asignaturas.setDefinitiva(aux4);
					
					
					if(aux1 > 5.0 || aux1 < 0.0 || aux2 > 5.0 || aux2 < 0.0 || aux3 > 5.0 || aux3 < 0.0 ){ 
							encontrado = true;
							JOptionPane.showMessageDialog(null, "Por favor escriba bien la nota");
					}
					
					
					for (int i = 0; i < materiasLista.size(); i++) {
						if (materiasLista.get(i).getIdEstudiante() == Integer.parseInt(cedulaAux)) {
							for (int j = 0; j < materiasLista.size(); j++) {
								if (materiasLista.get(i).getNombreMateria().equalsIgnoreCase(materia.getText())) {
									encontrado = true;
									JOptionPane.showMessageDialog(null, "La materia "+materia.getText()+" ya está registrada para el estudiante "+cedulaAux+", por favor ingrese otra materia");
									break;
								}
							}
						}
					}

					if( encontrado == false){
						JOptionPane.showMessageDialog(null, "Materia "+asignaturas.getNombreMateria()+" asignada al estudiante "+cedula.getSelectedItem().toString());
						materiasLista.add(asignaturas);
						materia.setText("");
						calificacion.setText("");
						calificacion2.setText("");
						calificacion3.setText("");
						grabar();
					}
			}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Por favor digite correctamente en los campos correspondientes");
			}
			
			}
		
		if (e.getSource() == volver) {
			if (redirigidoVolver == true) {
				consultar_materia volverMaterias = new consultar_materia(cedulaVolver, nombreAux,0,10,1,1);
				volverMaterias.setVisible(true);
				this.setVisible(false);
				
			}else {
				crud inicio = new crud();
				inicio.setVisible(true);
				this.setVisible(false);
			}
		}
		}
	
	public static void grabar() {
		try
		{
			FileOutputStream archivo = new FileOutputStream( "data/" + archivo_dat );
			ObjectOutputStream salida = new ObjectOutputStream(archivo);
			salida.writeObject( estudiantesLista );
			System.out.println("Datos guardados en " + archivo_dat );
			salida.close();
			archivo.close();
			
			FileOutputStream archivo2 = new FileOutputStream( "data/" + archivo2_dat );
			ObjectOutputStream salida2 = new ObjectOutputStream(archivo2);
			salida2.writeObject( materiasLista );

			salida2.close();
			archivo2.close();
			System.out.println("Datos guardados en " + archivo2_dat );
		}catch (IOException i){
			// i.printStackTrace();
			System.out.println("No se pudo guardar en " + archivo2_dat );
		}
	}
}
