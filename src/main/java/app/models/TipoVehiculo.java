package app.models;

public class TipoVehiculo {
	protected Integer idtipv;
	protected String nombre;
	protected Integer estado;
	public TipoVehiculo() {
	}
	public TipoVehiculo(Integer idtipv, String nombre, Integer estado) {
//		super();
		this.idtipv = idtipv;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Integer getIdtipv() {
		return idtipv;
	}
	public void setIdtipv(Integer idtipv) {
		this.idtipv = idtipv;
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
		return "TipoVehiculo [idtipv=" + idtipv + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
	
	
}
