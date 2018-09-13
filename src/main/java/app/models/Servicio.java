package app.models;

public class Servicio {
	protected int idserv;
	protected int idreduc,idcil,idcomb,idtipoMotorVeh,idfactCobro;
	protected Cilindro cilindro;
	protected Reductor reductor;
	protected Combustible combustible;
	protected String factorCobro;
	protected TipoMotorVehiculo tipoMotorVehiculo;
	protected Persona persona;
	protected int numPistones;
	protected String precioDolares;
	protected String precioBolivianos;
	protected String precioTotal;
	protected String fecha;
	protected int estado;
	public Servicio() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Servicio(int idserv, int idreduc, int idcil, int idcomb, int idtipoMotorVeh, int idfactCobro,
			Cilindro cilindro, Reductor reductor, Combustible combustible, String factorCobro,
			TipoMotorVehiculo tipoMotorVehiculo, Persona persona, int numPistones, String precioDolares,
			String precioBolivianos, String precioTotal, String fecha, int estado) {
		super();
		this.idserv = idserv;
		this.idreduc = idreduc;
		this.idcil = idcil;
		this.idcomb = idcomb;
		this.idtipoMotorVeh = idtipoMotorVeh;
		this.idfactCobro = idfactCobro;
		this.cilindro = cilindro;
		this.reductor = reductor;
		this.combustible = combustible;
		this.factorCobro = factorCobro;
		this.tipoMotorVehiculo = tipoMotorVehiculo;
		this.persona = persona;
		this.numPistones = numPistones;
		this.precioDolares = precioDolares;
		this.precioBolivianos = precioBolivianos;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
		this.estado = estado;
	}
	public int getIdserv() {
		return idserv;
	}
	public void setIdserv(int idserv) {
		this.idserv = idserv;
	}
	public int getIdreduc() {
		return idreduc;
	}
	public void setIdreduc(int idreduc) {
		this.idreduc = idreduc;
	}
	public int getIdcil() {
		return idcil;
	}
	public void setIdcil(int idcil) {
		this.idcil = idcil;
	}
	public int getIdcomb() {
		return idcomb;
	}
	public void setIdcomb(int idcomb) {
		this.idcomb = idcomb;
	}
	public int getIdtipoMotorVeh() {
		return idtipoMotorVeh;
	}
	public void setIdtipoMotorVeh(int idtipoMotorVeh) {
		this.idtipoMotorVeh = idtipoMotorVeh;
	}
	public int getIdfactCobro() {
		return idfactCobro;
	}
	public void setIdfactCobro(int idfactCobro) {
		this.idfactCobro = idfactCobro;
	}
	public Cilindro getCilindro() {
		return cilindro;
	}
	public void setCilindro(Cilindro cilindro) {
		this.cilindro = cilindro;
	}
	public Reductor getReductor() {
		return reductor;
	}
	public void setReductor(Reductor reductor) {
		this.reductor = reductor;
	}
	public Combustible getCombustible() {
		return combustible;
	}
	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}
	public String getFactorCobro() {
		return factorCobro;
	}
	public void setFactorCobro(String factorCobro) {
		this.factorCobro = factorCobro;
	}
	public TipoMotorVehiculo getTipoMotorVehiculo() {
		return tipoMotorVehiculo;
	}
	public void setTipoMotorVehiculo(TipoMotorVehiculo tipoMotorVehiculo) {
		this.tipoMotorVehiculo = tipoMotorVehiculo;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public int getNumPistones() {
		return numPistones;
	}
	public void setNumPistones(int numPistones) {
		this.numPistones = numPistones;
	}
	public String getPrecioDolares() {
		return precioDolares;
	}
	public void setPrecioDolares(String precioDolares) {
		this.precioDolares = precioDolares;
	}
	public String getPrecioBolivianos() {
		return precioBolivianos;
	}
	public void setPrecioBolivianos(String precioBolivianos) {
		this.precioBolivianos = precioBolivianos;
	}
	public String getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(String precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Servicio [idserv=" + idserv + ", idreduc=" + idreduc + ", idcil=" + idcil + ", idcomb=" + idcomb
				+ ", idtipoMotorVeh=" + idtipoMotorVeh + ", idfactCobro=" + idfactCobro + ", cilindro=" + cilindro
				+ ", reductor=" + reductor + ", combustible=" + combustible + ", factorCobro=" + factorCobro
				+ ", tipoMotorVehiculo=" + tipoMotorVehiculo + ", persona=" + persona + ", numPistones=" + numPistones
				+ ", precioDolares=" + precioDolares + ", precioBolivianos=" + precioBolivianos + ", precioTotal="
				+ precioTotal + ", fecha=" + fecha + ", estado=" + estado + "]";
	}
	
	
	
}
