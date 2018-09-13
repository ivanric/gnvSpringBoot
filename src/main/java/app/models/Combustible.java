package app.models;

public class Combustible {
	protected Integer idcomb;
	protected String nombre;
	protected String detalle;
	protected Integer estado;
	public Combustible() {
	
	}
	public Combustible(Integer idcomb, String nombre, String detalle, Integer estado) {
//		super();
		this.idcomb = idcomb;
		this.nombre = nombre;
		this.detalle = detalle;
		this.estado = estado;
	}
	public Integer getIdcomb() {
		return idcomb;
	}
	public void setIdcomb(Integer idcomb) {
		this.idcomb = idcomb;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Combustible [idcomb=" + idcomb + ", nombre=" + nombre + ", detalle=" + detalle + ", estado=" + estado
				+ "]";
	}
	
	
}
