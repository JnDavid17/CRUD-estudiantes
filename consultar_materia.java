import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class consultar_materia extends JFrame implements ActionListener {
	JLabel titulo;
	Font font2 = new Font("SAN_SERIF", Font.BOLD, 18);
	Font font3 = new Font("SAN_SERIF", Font.BOLD, 24);
	static  ArrayList<materias> materiasLista = new ArrayList<materias>();
	static String archivo_dat = "materias.dat";

	JTextField tituloMateria, tituloCalificacion1, tituloCalificacion2, tituloCalificacion3, tituloDefinitiva;
	JButton ingresar, volver, nextPage;
	int size;
	int y = 150;
	int cedulaAux;
	boolean redirigido;
	static String nombreAux;
	boolean encontrado = false;
	int sizeRecortado;
	int paginasTotales;
	int paginas = 0;
	int contadorMaterias = 0;
	boolean creado = false;
	int contador;
    int inicioVolver;
    int paginaVolver;
    int topeVolver;
	public static void main(String[] args) {
		consultar_materia frame = new consultar_materia(0, "", 0,0,0,0);
	}

	consultar_materia(int cedula, String nombre, int inicio, int tope, int paginaActual, int totalPaginas){
		
		setBounds(100, 100, 1200, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		Color color = new Color(45,64,89);
        this.getContentPane().setBackground(color);
        
        nombreAux = nombre;
		cargar();
        size = materiasLista.size();
    	JButton[] listarMateria = new JButton [size];
    	JTextField[] listarNota1 = new JTextField [size];
    	JTextField [] listarNota2 = new JTextField [size];
       	JTextField [] listarNota3 = new JTextField [size];
       	JTextField [] listarNota4 = new JTextField [size];


       	paginasTotales = totalPaginas; 
       	
        volver = new JButton("Volver");
        volver.setBounds(0,0,100,40);
        volver.setFont(font2);
        volver.setBackground(new Color(234, 84, 85));
        add(volver);
        volver.addActionListener(this);
       	
       	cedulaAux = cedula;
	
    	if (inicio == 0 && size >10) {
        	sizeRecortado = size;
    		while (sizeRecortado > 10) {
    			sizeRecortado -= 10;
    			paginas++;	
    		}
    		paginasTotales = (paginas+1);
		}
    	
    	ArrayList<materias> materiasListaCopia = new ArrayList<materias>();

    	
    	for (int i = 0; i < materiasLista.size(); i++) {
			if (materiasLista.get(i).getIdEstudiante() == cedulaAux) {
				materias asignaturas = new materias();
				contadorMaterias++;

				asignaturas.setIdEstudiante(materiasLista.get(i).getIdEstudiante());
				asignaturas.setNombreMateria(materiasLista.get(i).getNombreMateria());
				
				encontrado = true;

				
				float aux1 = materiasLista.get(i).getPrimerCorte();
				float aux2 = materiasLista.get(i).getSegundoCorte();
				float aux3 = materiasLista.get(i).getTercerCorte();
				
				float aux4 = (float) ((float) (aux1*0.30) + (aux2 * 0.30) + (aux3 * 0.40));
				
				asignaturas.setPrimerCorte(aux1);
				asignaturas.setSegundoCorte(aux2);
				asignaturas.setTercerCorte(aux3);
				asignaturas.setDefinitiva(aux4);
				materiasListaCopia.add(asignaturas);
				}
		}
       	
		for (int i = inicio; i < tope; i++) {
			if (i < materiasListaCopia.size()) {
                	contador = i;
                	int pasarPosicion = i;
                	int aux = materiasListaCopia.get(i).getIdEstudiante();
					listarMateria[i] = new JButton(""+materiasListaCopia.get(i).getNombreMateria());
					listarMateria[i].setBackground(color);
					listarMateria[i].setForeground(Color.white);
					listarMateria[i].setHorizontalAlignment(JTextField.CENTER);
					listarMateria[i].setBounds(40, y, 380, 50);
					listarMateria[i].setFont(font2);
					listarMateria[i].addActionListener(new ActionListener() {
					    @Override
					    public void actionPerformed(ActionEvent e) {
					    	eleccion_materias modificarOEliminar = new eleccion_materias(materiasListaCopia.get(pasarPosicion), false);
        					modificarOEliminar.setVisible(true);
        					setVisible(false);
					    }
					});
					add(listarMateria[i]);
					
					
					listarNota1[i] = new JTextField(""+materiasListaCopia.get(i).getPrimerCorte());
					listarNota1[i].setBackground(new Color(45,64,89));
					listarNota1[i].setForeground(Color.white);
					listarNota1[i].setEditable(false);
					listarNota1[i].setHorizontalAlignment(JTextField.CENTER);
					listarNota1[i].setBounds(420, y, 180, 50);
					listarNota1[i].setFont(font2);

					add(listarNota1[i]);
					
					
					listarNota2[i] = new JTextField(""+materiasListaCopia.get(i).getSegundoCorte());
					listarNota2[i].setBackground(new Color(45,64,89));
					listarNota2[i].setForeground(Color.white);
					listarNota2[i].setEditable(false);
					listarNota2[i].setHorizontalAlignment(JTextField.CENTER);
					listarNota2[i].setBounds(600, y, 180, 50);
					listarNota2[i].setFont(font2);
					add(listarNota2[i]);
					
					listarNota3[i] = new JTextField(""+materiasListaCopia.get(i).getTercerCorte());
					listarNota3[i].setBackground(new Color(45,64,89));
					listarNota3[i].setForeground(Color.white);
					listarNota3[i].setEditable(false);
					listarNota3[i].setHorizontalAlignment(JTextField.CENTER);
					listarNota3[i].setBounds(780, y, 180, 50);
					listarNota3[i].setFont(font2);
					add(listarNota3[i]);
					
					listarNota4[i] = new JTextField(""+materiasListaCopia.get(i).getDefinitiva());
					listarNota4[i].setBackground(new Color(45,64,89));
					listarNota4[i].setForeground(Color.white);
					listarNota4[i].setEditable(false);
					listarNota4[i].setHorizontalAlignment(JTextField.CENTER);
					listarNota4[i].setBounds(960, y, 180, 50);
					listarNota4[i].setFont(font2);
					add(listarNota4[i]);	

					y += 50;

				}
			}
		
		if (encontrado == false) {
			titulo = new JLabel("No hay materias para mostrar, por favor ingrese alguna");
			titulo.setBounds( 265, 200, 800, 60);
			titulo.setFont(font3);
			titulo.setForeground(new Color(255, 212, 96));
			add(titulo); 
			
			ingresar = new JButton("Ingresar materias");
			ingresar.setBounds(455, 300, 200, 50);
			ingresar.setFont(font2);
			ingresar.setBackground(new Color(240, 123, 63));
			add(ingresar);
			ingresar.addActionListener(this);
			
			redirigido = true;
		}else {
	        titulo = new JLabel ("Hay un total de "+contadorMaterias+" materias de el/la estudiante "+nombre);
			titulo.setBounds( 315, 30, 700, 60);
			titulo.setFont(font2);
			titulo.setForeground(new Color(255, 212, 96));
			add(titulo); 
			
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
		}

    	if (contadorMaterias > 10) {
    		creado = true;
            nextPage = new JButton("Pagina "+paginaActual+"/"+paginasTotales);
            nextPage.setBounds(1000,0,200,40);
            nextPage.setFont(font2);
            nextPage.setBackground(new Color(234, 84, 85));
            add(nextPage);
		}
    	
		if (creado == true) {
			nextPage.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	if ((contador+1)%10 == 0 && paginaActual < paginasTotales) {
    					consultar_materia again = new consultar_materia(cedulaAux, nombreAux, inicio+10, tope+10, paginaActual+1, paginasTotales);
    					again.setVisible(true);
    					setVisible(false);
					}

			    }
			});	
		}

        inicioVolver = inicio;
        paginaVolver = paginaActual;
        topeVolver = tope;


	}

	
	public static void cargar() {
		try{
			FileInputStream archivo = new FileInputStream("data/" + archivo_dat );
			ObjectInputStream entrada = new ObjectInputStream(archivo);
			materiasLista = (ArrayList<materias>) entrada.readObject();

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
		if (e.getSource() == ingresar ) {
			ingresar_materias create = new ingresar_materias(redirigido, cedulaAux, nombreAux);
			create.setVisible(true);
			this.setVisible(false);
		}		
		
		if (e.getSource() == volver) {
			if (inicioVolver >= 10) {
				consultar_materia again = new consultar_materia(cedulaAux, nombreAux,inicioVolver-10, topeVolver-10, paginaVolver-1, paginasTotales);
				again.setVisible(true);
				this.setVisible(false);
			}else {
				
				consultar volver = new consultar(0,10,1,1);
				volver.setVisible(true);			
				this.setVisible(false);
			}
		}
	}
}
