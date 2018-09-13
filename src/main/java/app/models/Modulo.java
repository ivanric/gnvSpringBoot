package app.models;

import java.util.ArrayList;
import java.util.List;


public class Modulo {
	private Integer idmod;
	private String nombre;
	private String icono;
	private Integer estado;
	private List<Proceso> procesos = new ArrayList<Proceso>() ; 
	
	public Modulo() {
//		super();
		// TODO Auto-generated constructor stub
	}

	public Modulo(Integer idmod, String nombre, String icono, Integer estado, List<Proceso> procesos) {
//		super();
		this.idmod = idmod;
		this.nombre = nombre;
		this.icono = icono;
		this.estado = estado;
		this.procesos = procesos;
	}

	public Integer getIdmod() {
		return idmod;
	}

	public void setIdmod(Integer idmod) {
		this.idmod = idmod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	@Override
	public String toString() {
		return "Modulo [idmod=" + idmod + ", nombre=" + nombre + ", icono=" + icono + ", estado=" + estado
				+ ", procesos=" + procesos + "]";
	}
	
}
