package app.models;

public class Rol {
	protected Integer idrol;
	protected String nombre;
	protected Integer estado;
	public Rol() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Rol(Integer idrol, String nombre, Integer estado) {
//		super();
		this.idrol = idrol;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Integer getIdrol() {
		return idrol;
	}
	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Rol [idrol=" + idrol + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
}
