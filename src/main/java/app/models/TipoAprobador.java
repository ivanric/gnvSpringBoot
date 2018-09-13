package app.models;

public class TipoAprobador {
	protected Integer idTipoAp;
	protected String nombre;
	protected String codigo;
	protected Integer jerarquia;
	protected Integer estado;
	public TipoAprobador() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public TipoAprobador(Integer idTipoAp, String nombre, String codigo, Integer jerarquia, Integer estado) {
//		super();
		this.idTipoAp = idTipoAp;
		this.nombre = nombre;
		this.codigo = codigo;
		this.jerarquia = jerarquia;
		this.estado = estado;
	}
	public Integer getIdTipoAp() {
		return idTipoAp;
	}
	public void setIdTipoAp(Integer idTipoAp) {
		this.idTipoAp = idTipoAp;
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
	public Integer getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(Integer jerarquia) {
		this.jerarquia = jerarquia;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "TipoAprobador [idTipoAp=" + idTipoAp + ", nombre=" + nombre + ", codigo=" + codigo + ", jerarquia="
				+ jerarquia + ", estado=" + estado + "]";
	}
	
}
