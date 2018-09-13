package app.models;

public class TipoMotorVehiculo {
	protected Integer idtipoMotorVeh;
	protected String nombre;
	protected Integer estado;
	public TipoMotorVehiculo() {
		
	}
	public TipoMotorVehiculo(Integer idtipoMotorVeh, String nombre, Integer estado) {
//		super();
		this.idtipoMotorVeh = idtipoMotorVeh;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Integer getIdtipoMotorVeh() {
		return idtipoMotorVeh;
	}
	public void setIdtipoMotorVeh(Integer idtipoMotorVeh) {
		this.idtipoMotorVeh = idtipoMotorVeh;
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
		return "TipoMotorVehiculo [idtipoMotorVeh=" + idtipoMotorVeh + ", nombre=" + nombre + ", estado=" + estado
				+ "]";
	}
	
	
}
