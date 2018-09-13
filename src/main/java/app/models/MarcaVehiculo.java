package app.models;

public class MarcaVehiculo {
	protected Integer idmarcv;
	protected String nombre;
	protected Integer estado;
	public MarcaVehiculo() {
	}
	public MarcaVehiculo(Integer idmarcv, String nombre, Integer estado) {
//		super();
		this.idmarcv = idmarcv;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Integer getIdmarcv() {
		return idmarcv;
	}
	public void setIdmarcv(Integer idmarcv) {
		this.idmarcv = idmarcv;
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
		return "MarcaVehiculo [idmarcv=" + idmarcv + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
	
}
