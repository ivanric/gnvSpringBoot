package app.models;

public class Opcion {
	private Integer idopc;
	private String nombre;
	private String codigo;
	private Integer estado;
	public Opcion() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Opcion(Integer idopc, String nombre, String codigo, Integer estado) {
		super();
		this.idopc = idopc;
		this.nombre = nombre;
		this.codigo = codigo;
		this.estado = estado;
	}
	public Integer getIdopc() {
		return idopc;
	}
	public void setIdopc(Integer idopc) {
		this.idopc = idopc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Opcion [idopc=" + idopc + ", nombre=" + nombre + ", codigo=" + codigo + ", estado=" + estado + "]";
	}
		
	
}
