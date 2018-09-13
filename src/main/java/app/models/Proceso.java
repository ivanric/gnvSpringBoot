package app.models;

import java.util.ArrayList;
import java.util.List;

public class Proceso {
	private Integer idproc;
	private String nombre;
	private String enlace;
	private String icono;
	private Integer estado;
	private List<Opcion> opciones=new ArrayList<Opcion>(); 
	public Proceso() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Proceso(Integer idproc, String nombre, String enlace, String icono, Integer estado, List<Opcion> opciones) {
		super();
		this.idproc = idproc;
		this.nombre = nombre;
		this.enlace = enlace;
		this.icono = icono;
		this.estado = estado;
		this.opciones = opciones;
	}
	public Integer getIdproc() {
		return idproc;
	}
	public void setIdproc(Integer idproc) {
		this.idproc = idproc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
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
	public List<Opcion> getOpciones() {
		return opciones;
	}
	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}
	@Override
	public String toString() {
		return "Proceso [idproc=" + idproc + ", nombre=" + nombre + ", enlace=" + enlace + ", icono=" + icono
				+ ", estado=" + estado + ", opciones=" + opciones + "]";
	}
	
	
}
