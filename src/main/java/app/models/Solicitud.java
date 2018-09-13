package app.models;

import java.util.List;

public class Solicitud {
	protected Integer idsolt;
	protected String numSolt;
	protected String fecha;
	protected String observaciones;
//	protected String placa;
	protected Integer aprobadoSiNo;
	protected String login;
	protected Integer estado;
	
	protected Vehiculo vehiculo;
	protected Persona persona;
	
	private List<Aprobacion> Aprobaciones;

	public Solicitud() {
//		super();
		// TODO Auto-generated constructor stub
	}

	public Solicitud(Integer idsolt, String numSolt, String fecha, String observaciones, Integer aprobadoSiNo,
			String login, Integer estado, Vehiculo vehiculo, Persona persona, List<Aprobacion> aprobaciones) {
//		super();
		this.idsolt = idsolt;
		this.numSolt = numSolt;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.aprobadoSiNo = aprobadoSiNo;
		this.login = login;
		this.estado = estado;
		this.vehiculo = vehiculo;
		this.persona = persona;
		Aprobaciones = aprobaciones;
	}

	public Integer getIdsolt() {
		return idsolt;
	}

	public void setIdsolt(Integer idsolt) {
		this.idsolt = idsolt;
	}

	public String getNumSolt() {
		return numSolt;
	}

	public void setNumSolt(String numSolt) {
		this.numSolt = numSolt;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getAprobadoSiNo() {
		return aprobadoSiNo;
	}

	public void setAprobadoSiNo(Integer aprobadoSiNo) {
		this.aprobadoSiNo = aprobadoSiNo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Aprobacion> getAprobaciones() {
		return Aprobaciones;
	}

	public void setAprobaciones(List<Aprobacion> aprobaciones) {
		Aprobaciones = aprobaciones;
	}

	@Override
	public String toString() {
		return "Solicitud [idsolt=" + idsolt + ", numSolt=" + numSolt + ", fecha=" + fecha + ", observaciones="
				+ observaciones + ", aprobadoSiNo=" + aprobadoSiNo + ", login=" + login + ", estado=" + estado
				+ ", vehiculo=" + vehiculo + ", persona=" + persona + ", Aprobaciones=" + Aprobaciones + "]";
	}

}
