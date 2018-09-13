package app.models;

public class OrdenServicio {
	protected Integer idordserv;
	protected Integer idsolt;
	protected Integer numords;
	protected String fechaords;
	protected Solicitud solicitud;
	protected Taller taller;
	protected Servicio servicio;
	protected Integer instaladoSiNo;
	protected Persona persona;
	protected ChipRom chipRom;
	public OrdenServicio() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public OrdenServicio(Integer idordserv, Integer idsolt, Integer numords, String fechaords, Solicitud solicitud,
			Taller taller, Servicio servicio, Integer instaladoSiNo, Persona persona, ChipRom chipRom) {
//		super();
		this.idordserv = idordserv;
		this.idsolt = idsolt;
		this.numords = numords;
		this.fechaords = fechaords;
		this.solicitud = solicitud;
		this.taller = taller;
		this.servicio = servicio;
		this.instaladoSiNo = instaladoSiNo;
		this.persona = persona;
		this.chipRom = chipRom;
	}
	public Integer getIdordserv() {
		return idordserv;
	}
	public void setIdordserv(Integer idordserv) {
		this.idordserv = idordserv;
	}
	public Integer getIdsolt() {
		return idsolt;
	}
	public void setIdsolt(Integer idsolt) {
		this.idsolt = idsolt;
	}
	public Integer getNumords() {
		return numords;
	}
	public void setNumords(Integer numords) {
		this.numords = numords;
	}
	public String getFechaords() {
		return fechaords;
	}
	public void setFechaords(String fechaords) {
		this.fechaords = fechaords;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public Taller getTaller() {
		return taller;
	}
	public void setTaller(Taller taller) {
		this.taller = taller;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public Integer getInstaladoSiNo() {
		return instaladoSiNo;
	}
	public void setInstaladoSiNo(Integer instaladoSiNo) {
		this.instaladoSiNo = instaladoSiNo;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public ChipRom getChipRom() {
		return chipRom;
	}
	public void setChipRom(ChipRom chipRom) {
		this.chipRom = chipRom;
	}
	@Override
	public String toString() {
		return "OrdenServicio [idordserv=" + idordserv + ", idsolt=" + idsolt + ", numords=" + numords + ", fechaords="
				+ fechaords + ", solicitud=" + solicitud + ", taller=" + taller + ", servicio=" + servicio
				+ ", instaladoSiNo=" + instaladoSiNo + ", persona=" + persona + ", chipRom=" + chipRom + "]";
	}
	
}
