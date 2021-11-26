
public class materias implements java.io.Serializable {
	public int idEstudiante;
	public String nombreMateria;
	public float primerCorte;
	public float segundoCorte;
	public float tercerCorte;
	public float definitiva;

	
	public int getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	public String getNombreMateria() {
		return nombreMateria;
	}
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	
	
	public float getPrimerCorte() {
		return primerCorte;
	}
	public void setPrimerCorte(float primerCorte) {
		this.primerCorte = primerCorte;
	}
	public float getSegundoCorte() {
		return segundoCorte;
	}
	public void setSegundoCorte(float segundoCorte) {
		this.segundoCorte = segundoCorte;
	}
	public float getTercerCorte() {
		return tercerCorte;
	}
	public void setTercerCorte(float tercerCorte) {
		this.tercerCorte = tercerCorte;
	}
	public float getDefinitiva() {
		return definitiva;
	}
	public void setDefinitiva(float definitiva) {
		this.definitiva = definitiva;
	}
	
	
}
