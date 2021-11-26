import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class consultar_especifico  extends JFrame implements ActionListener {
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 18);
	JButton volver, ingresar, nextPage;
	static  ArrayList<estudiantes> estudiantesListaOriginal = new ArrayList<estudiantes>();
	static ArrayList<estudiantes> estudiantesListaCopia = new ArrayList<estudiantes>();

	static String archivo_dat = "students.dat";

	Font font3 = new Font("SAN_SERIF", Font.BOLD, 24);

	JTextField tituloIdentificacion, tituloNombre, tituloEdad, tituloMateria, busqueda;



	boolean creado = false;
	
	
    static String parametro;
    static String value;
    
    static String parametroRepetir;
    static String valorParaRepetir;
	JLabel tituloBuscar, titulo, listaGeneral;
	int xCedula = 90;
	int xNombre = 270;
	int xEdad = 590;
	int xMaterias = 690;
	
	int yGeneral = 350;
	
	int inicioVolver = 0;
	int paginaVolver = 0;
	int topeVolver = 0;
	
	int paginas = 0;
	int size;
	int sizeRecortado;
	int i = 0;
	int paginasTotales;
	public static void main(String[] args) {
		consultar_especifico frame = new consultar_especifico("", "",0,0,0,0);
	}

	public consultar_especifico(String parametroVariable, String valor, int inicio, int tope, int paginaActual, int totalPaginas){
		
		setBounds(100, 100, 1000, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		Color color = new Color(45,64,89);
		
		paginasTotales = totalPaginas;
        
        this.getContentPane().setBackground(color);
        
        parametro = parametroVariable;
        value = valor;
        
        
        
        if (inicio == 0) {
        	estudiantesListaCopia.removeAll(estudiantesListaCopia);
            cargar();
            copiaLista();
    	    
		}

    	size = estudiantesListaCopia.size();
    	JButton[] botonCedula = new JButton [size];
    	JTextField[] listarNombre = new JTextField [size];
    	JTextField [] listarEdad = new JTextField [size];
    	JButton [] botonMaterias = new JButton [size];
    	

        volver = new JButton("Volver");
        volver.setBounds(0,0,100,40);
        volver.setFont(font2);
        volver.setBackground(new Color(234, 84, 85));
        add(volver);
        volver.addActionListener(this);
    	

    	if (inicio == 0 && size >10) {
        	sizeRecortado = size;
    		while (sizeRecortado > 10) {
    			sizeRecortado -= 10;
    			paginas++;	
    		}
    		paginasTotales = (paginas+1);
		}
    	
    	if (size > 10) {
    		creado = true;
            nextPage = new JButton("Pagina "+paginaActual+"/"+paginasTotales);
            nextPage.setBounds(800,0,200,40);
            nextPage.setFont(font2);
            nextPage.setBackground(new Color(234, 84, 85));
            add(nextPage);
		}

    	if (size > 0) {
    		
            titulo = new JLabel ("Hay un total de "+size+ " estudiantes que coinciden con la busqueda de "+parametroVariable+" = "+valor);
            titulo.setBounds(20,180,1000,40);
            titulo.setFont(font3);
            titulo.setForeground(Color.white);
            add(titulo);
    		
            
			tituloIdentificacion = new JTextField("Identificacion");
			tituloIdentificacion.setBackground(new Color(45,64,89));
			tituloIdentificacion.setForeground(Color.white);
			tituloIdentificacion.setEditable(false);
			tituloIdentificacion.setHorizontalAlignment(JTextField.CENTER);
			tituloIdentificacion.setBounds(xCedula, 301, 180, 50);
			tituloIdentificacion.setFont(font2);
			add(tituloIdentificacion);
			
			tituloNombre = new JTextField("Nombre");
			tituloNombre.setBackground(new Color(45,64,89));
			tituloNombre.setForeground(Color.white);
			tituloNombre.setEditable(false);
			tituloNombre.setHorizontalAlignment(JTextField.CENTER);
			tituloNombre.setBounds(xNombre, 301, 320, 50);
			tituloNombre.setFont(font2);
			add(tituloNombre);
			
			tituloEdad = new JTextField("Edad");
			tituloEdad.setBackground(new Color(45,64,89));
			tituloEdad.setForeground(Color.white);
			tituloEdad.setEditable(false);
			tituloEdad.setHorizontalAlignment(JTextField.CENTER);
			tituloEdad.setBounds(xEdad, 301, 100, 50);
			tituloEdad.setFont(font2);
			add(tituloEdad);

			tituloMateria = new JTextField("Materias");
			tituloMateria.setBackground(new Color(45,64,89));
			tituloMateria.setForeground(Color.white);
			tituloMateria.setEditable(false);
			tituloMateria.setHorizontalAlignment(JTextField.CENTER);
			tituloMateria.setBounds(xMaterias, 301, 180, 50);
			tituloMateria.setFont(font2);
			add(tituloMateria);
            
            for (i = inicio; i < tope; i++) {
            	if (i < size) {
            		int aux = estudiantesListaCopia.get(i).getCedula();
                	int contador = i;
        			botonCedula[i] = new JButton(""+estudiantesListaCopia.get(i).getCedula());
        			botonCedula[i].setBackground(new Color(255, 212, 96));
        			botonCedula[i].setBounds(xCedula, yGeneral, 180, 50);
        			botonCedula[i].setFont(font2);
        			botonCedula[i].addActionListener(new ActionListener() {
        			    @Override
        			    public void actionPerformed(ActionEvent e) {
        					eleccion_estudiante eleccion = new eleccion_estudiante(estudiantesListaCopia.get(contador), false);
        					eleccion.setVisible(true);
        					setVisible(false);
        			    }
        			});
        			add(botonCedula[i]);
        			
        			listarNombre[i] = new JTextField(""+estudiantesListaCopia.get(i).getNombre());
        			listarNombre[i].setBackground(color);
        			listarNombre[i].setForeground(Color.white);
        			listarNombre[i].setEditable(false);
        			listarNombre[i].setFont(font2);
        			listarNombre[i].setHorizontalAlignment(JTextField.CENTER);
        			listarNombre[i].setBounds(xNombre, yGeneral, 320, 50);
        			add(listarNombre[i]);

        			listarEdad[i] = new JTextField(""+estudiantesListaCopia.get(i).getEdad());
        			listarEdad[i].setBackground(color);
        			listarEdad[i].setForeground(Color.white);
        			listarEdad[i].setEditable(false);
        			listarEdad[i].setFont(font2);
        			listarEdad[i].setHorizontalAlignment(JTextField.CENTER);
        			listarEdad[i].setBounds(xEdad, yGeneral, 100, 50);
        			add(listarEdad[i]);
        			
        			botonMaterias[i] = new JButton("Materias");
        			botonMaterias[i].setBackground(new Color(240, 123, 63));
        			botonMaterias[i].setBounds(xMaterias, yGeneral, 180, 50);
        			botonMaterias[i].setFont(font2);
        			botonMaterias[i].addActionListener(new ActionListener() {
        			    @Override
        			    public void actionPerformed(ActionEvent e) {
        					consultar_materia ver_materia = new consultar_materia(aux, estudiantesListaCopia.get(contador).getNombre(), 0,10,1,1);
        					ver_materia.setVisible(true); 
        					}
        			});			
        			add(botonMaterias[i]);
        			
        			if (creado == true) {
               			nextPage.addActionListener(new ActionListener() {
            			    @Override
            			    public void actionPerformed(ActionEvent e) {
            			    	if ((contador+1)%10 == 0 && paginaActual < paginasTotales) {
                					consultar_especifico again = new consultar_especifico(parametro, value, inicio+10, tope+10, paginaActual+1, paginasTotales);
                					again.setVisible(true);
                					setVisible(false);
        						}

            			    }
            			});		
					}
        			yGeneral += 50;
				}
    		}
		}else {
			titulo = new JLabel("No hay estudiantes para mostrar con "+parametroVariable+" de "+valor);
			titulo.setBounds( 165, 150, 800, 60);
			titulo.setFont(font3);
			titulo.setForeground(new Color(255, 212, 96));
			add(titulo); 
			
			ingresar = new JButton("Ingresar estudiantes");
			ingresar.setBounds(355, 250, 260, 50);
			ingresar.setFont(font2);
			ingresar.setBackground(new Color(240, 123, 63));
			add(ingresar);
			ingresar.addActionListener(this);
			
		}

        inicioVolver = inicio;
        paginaVolver = paginaActual;
        topeVolver = tope;
       
	}
	
	public static void copiaLista() {
		int valueInt;
		int contadorSplit = 0;
		int j = 0;
		for (int i = 0; i < estudiantesListaOriginal.size(); i++) {
			if (parametro.equalsIgnoreCase("Cedula")) {
				valueInt = Integer.parseInt(value);
				if (estudiantesListaOriginal.get(i).getCedula() == valueInt) {
					copiar(i);
				}
			}else if(parametro.equalsIgnoreCase("Nombre")) {
				String[] auxArrayEstudiante = estudiantesListaOriginal.get(i).getNombre().split("\\s+");
				String[] auxArrayValor = value.split("\\s+");

				while (contadorSplit < auxArrayValor.length) {
					if (auxArrayValor[contadorSplit].equalsIgnoreCase(auxArrayEstudiante[j])) {
						copiar(i);
						break;
					}
					if (j < auxArrayEstudiante.length) {
						j ++;
					}
					
					if (j == auxArrayEstudiante.length) {
							contadorSplit++;
							j = 0;
					}
				}
				
				contadorSplit = 0;

				
			}else if(parametro.equalsIgnoreCase("Edad")) {
				valueInt = Integer.parseInt(value);
				if (estudiantesListaOriginal.get(i).getEdad() == valueInt) {
					copiar(i);
				}
			}
		}
	}

	public static void copiar(int i) {
		estudiantes estudiante = new estudiantes();		
		estudiante.setCedula(estudiantesListaOriginal.get(i).getCedula());
		
		estudiante.setNombre(estudiantesListaOriginal.get(i).getNombre());
		
		estudiante.setEdad(estudiantesListaOriginal.get(i).getEdad());

		estudiantesListaCopia.add(estudiante);
		
	}
	
	public static void cargar() {
		try{
			FileInputStream archivo = new FileInputStream("data/" + archivo_dat );
			ObjectInputStream entrada = new ObjectInputStream(archivo);
			estudiantesListaOriginal = (ArrayList<estudiantes>) entrada.readObject();

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
		if (e.getSource() == volver) {
			if (inicioVolver >= 10) {
				consultar_especifico again = new consultar_especifico(parametro, value, inicioVolver-10, topeVolver-10, paginaVolver-1, paginasTotales);
				again.setVisible(true);
				this.setVisible(false);
			}else {
				consultar read = new consultar(0,10,1,1);
				read.setVisible(true);
				this.setVisible(false);
			}

		}
		
		if (e.getSource() == ingresar) {
			ingresar ingresarEstudiante = new ingresar(parametro, value);
			ingresarEstudiante.setVisible(true);
			this.setVisible(false);
		}
	}
	


}
