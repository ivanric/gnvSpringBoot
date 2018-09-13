package app.models;

import java.util.Date;

public class Aprobacion {
	protected Integer idsolt;
	protected Integer idTipoAp;
	protected Date fechaAprob;
	protected String login;
	
	protected TipoAprobador tipoAprobador;
	
	public Aprobacion() {
//		super();
		// TODO Auto-generated constructor stub
	}

	public Aprobacion(Integer idsolt, Integer idTipoAp, Integer pausaAp, Date fechaAprob, String login,
			TipoAprobador tipoAprobador) {
//		super();
		this.idsolt = idsolt;
		this.idTipoAp = idTipoAp;
		this.fechaAprob = fechaAprob;
		this.login = login;
		this.tipoAprobador = tipoAprobador;
	}

	public Integer getIdsolt() {
		return idsolt;
	}

	public void setIdsolt(Integer idsolt) {
		this.idsolt = idsolt;
	}

	public Integer getIdTipoAp() {
		return idTipoAp;
	}

	public void setIdTipoAp(Integer idTipoAp) {
		this.idTipoAp = idTipoAp;
	}


	public Date getFechaAprob() {
		return fechaAprob;
	}

	public void setFechaAprob(Date fechaAprob) {
		this.fechaAprob = fechaAprob;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public TipoAprobador getTipoAprobador() {
		return tipoAprobador;
	}

	public void setTipoAprobador(TipoAprobador tipoAprobador) {
		this.tipoAprobador = tipoAprobador;
	}

	@Override
	public String toString() {
		return "Aprobacion [idsolt=" + idsolt + ", idTipoAp=" + idTipoAp + ", fechaAprob=" + fechaAprob + ", login="
				+ login + ", tipoAprobador=" + tipoAprobador + "]";
	}


}
