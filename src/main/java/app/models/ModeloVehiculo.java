package app.models;

public class ModeloVehiculo {
	protected Integer idmodv;
	protected String nombre;
	protected Integer estado;
	public ModeloVehiculo() {
		
	}
	public ModeloVehiculo(Integer idmodv, String nombre, Integer estado) {
//		super();
		this.idmodv = idmodv;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Integer getIdmodv() {
		return idmodv;
	}
	public void setIdmodv(Integer idmodv) {
		this.idmodv = idmodv;
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
		return "ModeloVehiculo [idmodv=" + idmodv + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
}
