package app.models;

public class TipoServicioVehiculo {
	protected Integer idTipSv;
	protected String nombre;
	protected Integer estado;
	public TipoServicioVehiculo() {
		
	}
	public TipoServicioVehiculo(Integer idTipSv, String nombre, Integer estado) {
//		super();
		this.idTipSv = idTipSv;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Integer getIdTipSv() {
		return idTipSv;
	}
	public void setIdTipSv(Integer idTipSv) {
		this.idTipSv = idTipSv;
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
		return "TipoServicioVehiculo [idTipSv=" + idTipSv + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
	
}
