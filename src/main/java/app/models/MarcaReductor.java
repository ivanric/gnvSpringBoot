package app.models;

public class MarcaReductor {
	protected int idmarcred;
	protected String nombre;
	protected int estado;
	public MarcaReductor() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public MarcaReductor(int idmarcred, String nombre, int estado) {
//		super();
		this.idmarcred = idmarcred;
		this.nombre = nombre;
		this.estado = estado;
	}
	public int getIdmarcred() {
		return idmarcred;
	}
	public void setIdmarcred(int idmarcred) {
		this.idmarcred = idmarcred;
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
		return "MarcaReductor [idmarcred=" + idmarcred + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
}
