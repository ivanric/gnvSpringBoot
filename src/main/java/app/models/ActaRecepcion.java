package app.models;

public class ActaRecepcion {
	protected Integer idrecep,almacenesSiNo,activosFijosSiNo,servGeneralesSiNo,idordserv;
	protected String numrecep,fechaActaRecepcion;
	protected OrdenServicio ordenServicio;
	protected RegistroKit registroKit;
	public ActaRecepcion() {

	}
	public ActaRecepcion(Integer idrecep, Integer almacenesSiNo, Integer activosFijosSiNo, Integer servGeneralesSiNo,
			Integer idordserv, String numrecep, String fechaActaRecepcion, OrdenServicio ordenServicio,
			RegistroKit registroKit) {
		super();
		this.idrecep = idrecep;
		this.almacenesSiNo = almacenesSiNo;
		this.activosFijosSiNo = activosFijosSiNo;
		this.servGeneralesSiNo = servGeneralesSiNo;
		this.idordserv = idordserv;
		this.numrecep = numrecep;
		this.fechaActaRecepcion = fechaActaRecepcion;
		this.ordenServicio = ordenServicio;
		this.registroKit = registroKit;
	}
	public Integer getIdrecep() {
		return idrecep;
	}
	public void setIdrecep(Integer idrecep) {
		this.idrecep = idrecep;
	}
	public Integer getAlmacenesSiNo() {
		return almacenesSiNo;
	}
	public void setAlmacenesSiNo(Integer almacenesSiNo) {
		this.almacenesSiNo = almacenesSiNo;
	}
	public Integer getActivosFijosSiNo() {
		return activosFijosSiNo;
	}
	public void setActivosFijosSiNo(Integer activosFijosSiNo) {
		this.activosFijosSiNo = activosFijosSiNo;
	}
	public Integer getServGeneralesSiNo() {
		return servGeneralesSiNo;
	}
	public void setServGeneralesSiNo(Integer servGeneralesSiNo) {
		this.servGeneralesSiNo = servGeneralesSiNo;
	}
	public Integer getIdordserv() {
		return idordserv;
	}
	public void setIdordserv(Integer idordserv) {
		this.idordserv = idordserv;
	}
	public String getNumrecep() {
		return numrecep;
	}
	public void setNumrecep(String numrecep) {
		this.numrecep = numrecep;
	}
	public String getFechaActaRecepcion() {
		return fechaActaRecepcion;
	}
	public void setFechaActaRecepcion(String fechaActaRecepcion) {
		this.fechaActaRecepcion = fechaActaRecepcion;
	}
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public RegistroKit getRegistroKit() {
		return registroKit;
	}
	public void setRegistroKit(RegistroKit registroKit) {
		this.registroKit = registroKit;
	}
	@Override
	public String toString() {
		return "ActaRecepcion [idrecep=" + idrecep + ", almacenesSiNo=" + almacenesSiNo + ", activosFijosSiNo="
				+ activosFijosSiNo + ", servGeneralesSiNo=" + servGeneralesSiNo + ", idordserv=" + idordserv
				+ ", numrecep=" + numrecep + ", fechaActaRecepcion=" + fechaActaRecepcion + ", ordenServicio="
				+ ordenServicio + ", registroKit=" + registroKit + ", getIdrecep()=" + getIdrecep()
				+ ", getAlmacenesSiNo()=" + getAlmacenesSiNo() + ", getActivosFijosSiNo()=" + getActivosFijosSiNo()
				+ ", getServGeneralesSiNo()=" + getServGeneralesSiNo() + ", getIdordserv()=" + getIdordserv()
				+ ", getNumrecep()=" + getNumrecep() + ", getFechaActaRecepcion()=" + getFechaActaRecepcion()
				+ ", getOrdenServicio()=" + getOrdenServicio() + ", getRegistroKit()=" + getRegistroKit()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
