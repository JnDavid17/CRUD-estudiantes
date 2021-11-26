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

public class modificar_materia extends JFrame implements ActionListener {
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 20);
	JLabel titulo, nombreTitulo, edadTitulo, materiaTitulo, calificacionTitulo,calificacionTitulo2,calificacionTitulo3;
	JTextField  nombre, edad, materia, calificacion, calificacion2, calificacion3;
	JButton regresar, ingresar;

	
	static  ArrayList<estudiantes> estudiantesLista = new ArrayList<estudiantes>();
	static  ArrayList<materias> materiasLista = new ArrayList<materias>();
	
	static String nombreAux;
	static int cedulaAux;
	static int cedulaVolver;
	
	static boolean redirigidoVolver = false;
	static String auxNombreMateria;
	static String archivo2_dat = "materias.dat";

	static materias copia = new materias();

	
	public static void main(String[] args) {
		modificar_materia frame = new modificar_materia(null);
	}

	public modificar_materia(materias materiaSeleccionada){
	
		setBounds(100, 100, 500, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);


		auxNombreMateria = materiaSeleccionada.getNombreMateria();
		cedulaAux = materiaSeleccionada.getIdEstudiante();

		cargar();

        this.getContentPane().setBackground(new Color(45,64,89));

        regresar = new JButton("Volver");
        regresar.setBounds(0,0,100,40);
        regresar.setFont(font2);
        regresar.setBackground(new Color(234, 84, 85));
        regresar.setForeground(Color.white);
        add(regresar);
        regresar.addActionListener(this);
        
		titulo = new JLabel("Modificar materia");		
		titulo.setBounds( 165, 30, 250, 60);
		titulo.setFont(font2);
		titulo.setForeground(new Color(255, 212, 96));
		add(titulo); 
		

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
		
		ingresar = new JButton("Modificar");
		ingresar.setBounds(0, 710, 500, 50);
		ingresar.setFont(font2);
		ingresar.setBackground(new Color(240, 123, 63));
		add(ingresar);
		ingresar.addActionListener(this);
	}
	

	public static void cargar() {
		try{
			FileInputStream archivo2 = new FileInputStream("data/" + archivo2_dat );
			ObjectInputStream entrada2 = new ObjectInputStream(archivo2);
			materiasLista = (ArrayList<materias>) entrada2.readObject();

			entrada2.close();
			archivo2.close();
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

					float aux1 = Float.parseFloat(calificacion.getText());
					float aux2 = Float.parseFloat(calificacion2.getText());
					float aux3 = Float.parseFloat(calificacion3.getText());
					float aux4 = (float) ((float) (aux1*0.30) + (aux2 * 0.30) + (aux3 * 0.40));

					if(aux1 > 5.0 || aux1 < 0.0 || aux2 > 5.0 || aux2 < 0.0 || aux3 > 5.0 || aux3 < 0.0 ){ 
							encontrado = true;
							JOptionPane.showMessageDialog(null, "Por favor escriba bien la nota");
							
					}

					
					for (int i = 0; i < materiasLista.size(); i++) {
						if (materiasLista.get(i).getNombreMateria().equalsIgnoreCase(materia.getText()) && cedulaAux == materiasLista.get(i).getIdEstudiante()) {
							encontrado = true;
							JOptionPane.showMessageDialog(null, "La materia "+materia.getText()+" ya está registrada para el estudiante "+cedulaAux+", por favor ingrese otra materia");
							break;
						}
					}

					if( encontrado == false){
						for (int i = 0; i < materiasLista.size(); i++) {
							if (materiasLista.get(i).getIdEstudiante() == cedulaAux && materiasLista.get(i).getNombreMateria().equalsIgnoreCase(auxNombreMateria)) {
								JOptionPane.showMessageDialog(null, "Datos de la materia antes de modificar: \nNombre: "+materiasLista.get(i).getNombreMateria()+"\nNota primer corte: "+materiasLista.get(i).getPrimerCorte()+"\nNota segundo corte: "+materiasLista.get(i).getSegundoCorte()+"\nNota tercer corte: "+materiasLista.get(i).getTercerCorte()+"\nDefinitiva semestre: "+materiasLista.get(i).getDefinitiva());
								materiasLista.get(i).setNombreMateria(materia.getText());
								materiasLista.get(i).setPrimerCorte(aux1);
								materiasLista.get(i).setSegundoCorte(aux2);
								materiasLista.get(i).setTercerCorte(aux3);
								materiasLista.get(i).setDefinitiva(aux4);
								
								copia.setIdEstudiante(materiasLista.get(i).getIdEstudiante());
								copia.setNombreMateria(materiasLista.get(i).getNombreMateria());
								copia.setPrimerCorte(materiasLista.get(i).getPrimerCorte());
								copia.setSegundoCorte(materiasLista.get(i).getSegundoCorte());
								copia.setTercerCorte(materiasLista.get(i).getTercerCorte());
								copia.setDefinitiva(materiasLista.get(i).getDefinitiva());
								
								JOptionPane.showMessageDialog(null, "Datos nuevos de la materia: \nNombre: "+materiasLista.get(i).getNombreMateria()+"\nNota primer corte: "+materiasLista.get(i).getPrimerCorte()+"\nNota segundo corte: "+materiasLista.get(i).getSegundoCorte()+"\nNota tercer corte: "+materiasLista.get(i).getTercerCorte()+"\nDefinitiva semestre: "+materiasLista.get(i).getDefinitiva());
							}
						}
						grabar();
					}
			}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Por favor digite correctamente en los campos correspondientes");
			}
			
		}
		
		if (e.getSource() == regresar) {
			eleccion_materias volverAtras =
					new eleccion_materias(copia, false);
			volverAtras.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public static void grabar() {
		try
		{
			
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
