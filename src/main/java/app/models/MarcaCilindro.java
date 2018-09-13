package app.models;

public class MarcaCilindro {
	protected int idmarccil;
	protected String nombre;
	protected int estado;
	public MarcaCilindro() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public MarcaCilindro(int idmarccil, String nombre, int estado) {
//		super();
		this.idmarccil = idmarccil;
		this.nombre = nombre;
		this.estado = estado;
	}
	public int getIdmarccil() {
		return idmarccil;
	}
	public void setIdmarccil(int idmarccil) {
		this.idmarccil = idmarccil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "MarcaCilindro [idmarccil=" + idmarccil + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
}
