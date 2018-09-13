package app.models;

public class TransferenciaBeneficiario {
	protected Integer idtrasl,idsolt,idbenActual,idbenNuevo;
	protected String fechaTraslado,motivoTraslado,login;
	protected Persona personaAnteriorBenf;
	protected Persona personaNuevoBenf;
	protected RegistroKit registroKit;
	public TransferenciaBeneficiario() {

	}
	public TransferenciaBeneficiario(Integer idtrasl, Integer idsolt, Integer idbenActual, Integer idbenNuevo,
			String fechaTraslado, String motivoTraslado, String login, Persona personaAnteriorBenf,
			Persona personaNuevoBenf, RegistroKit registroKit) {
		super();
		this.idtrasl = idtrasl;
		this.idsolt = idsolt;
		this.idbenActual = idbenActual;
		this.idbenNuevo = idbenNuevo;
		this.fechaTraslado = fechaTraslado;
		this.motivoTraslado = motivoTraslado;
		this.login = login;
		this.personaAnteriorBenf = personaAnteriorBenf;
		this.personaNuevoBenf = personaNuevoBenf;
		this.registroKit = registroKit;
	}
	public Integer getIdtrasl() {
		return idtrasl;
	}
	public void setIdtrasl(Integer idtrasl) {
		this.idtrasl = idtrasl;
	}
	public Integer getIdsolt() {
		return idsolt;
	}
	public void setIdsolt(Integer idsolt) {
		this.idsolt = idsolt;
	}
	public Integer getIdbenActual() {
		return idbenActual;
	}
	public void setIdbenActual(Integer idbenActual) {
		this.idbenActual = idbenActual;
	}
	public Integer getIdbenNuevo() {
		return idbenNuevo;
	}
	public void setIdbenNuevo(Integer idbenNuevo) {
		this.idbenNuevo = idbenNuevo;
	}
	public String getFechaTraslado() {
		return fechaTraslado;
	}
	public void setFechaTraslado(String fechaTraslado) {
		this.fechaTraslado = fechaTraslado;
	}
	public String getMotivoTraslado() {
		return motivoTraslado;
	}
	public void setMotivoTraslado(String motivoTraslado) {
		this.motivoTraslado = motivoTraslado;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Persona getPersonaAnteriorBenf() {
		return personaAnteriorBenf;
	}
	public void setPersonaAnteriorBenf(Persona personaAnteriorBenf) {
		this.personaAnteriorBenf = personaAnteriorBenf;
	}
	public Persona getPersonaNuevoBenf() {
		return personaNuevoBenf;
	}
	public void setPersonaNuevoBenf(Persona personaNuevoBenf) {
		this.personaNuevoBenf = personaNuevoBenf;
	}
	public RegistroKit getRegistroKit() {
		return registroKit;
	}
	public void setRegistroKit(RegistroKit registroKit) {
		this.registroKit = registroKit;
	}
	@Override
	public String toString() {
		return "TransferenciaBeneficiario [idtrasl=" + idtrasl + ", idsolt=" + idsolt + ", idbenActual=" + idbenActual
				+ ", idbenNuevo=" + idbenNuevo + ", fechaTraslado=" + fechaTraslado + ", motivoTraslado="
				+ motivoTraslado + ", login=" + login + ", personaAnteriorBenf=" + personaAnteriorBenf
				+ ", personaNuevoBenf=" + personaNuevoBenf + ", registroKit=" + registroKit + ", getIdtrasl()="
				+ getIdtrasl() + ", getIdsolt()=" + getIdsolt() + ", getIdbenActual()=" + getIdbenActual()
				+ ", getIdbenNuevo()=" + getIdbenNuevo() + ", getFechaTraslado()=" + getFechaTraslado()
				+ ", getMotivoTraslado()=" + getMotivoTraslado() + ", getLogin()=" + getLogin()
				+ ", getPersonaAnteriorBenf()=" + getPersonaAnteriorBenf() + ", getPersonaNuevoBenf()="
				+ getPersonaNuevoBenf() + ", getRegistroKit()=" + getRegistroKit() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
