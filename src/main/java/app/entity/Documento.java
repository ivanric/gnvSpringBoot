package app.entity;

public class Documento {
	protected Integer iddocb;
	protected String nombre;
	protected Integer estado;
	public Documento() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Documento(Integer iddocb, String nombre, Integer estado) {
		super();
		this.iddocb = iddocb;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Integer getIddocb() {
		return iddocb;
	}
	public void setIddocb(Integer iddocb) {
		this.iddocb = iddocb;
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
		return "Documento [iddocb=" + iddocb + ", nombre=" + nombre + ", estado=" + estado + "]";
	} 
	
}
