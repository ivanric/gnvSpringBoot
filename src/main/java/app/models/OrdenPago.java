package app.models;

public class OrdenPago {
	protected int idOrdPago,idrecep;
	protected String numOrdPago,precio,fechaOrdPago,login;
	protected ActaRecepcion actaRecepcion;
	public OrdenPago() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public OrdenPago(int idOrdPago, int idrecep, String numOrdPago, String precio, String fechaOrdPago, String login,
			ActaRecepcion actaRecepcion) {
//		super();
		this.idOrdPago = idOrdPago;
		this.idrecep = idrecep;
		this.numOrdPago = numOrdPago;
		this.precio = precio;
		this.fechaOrdPago = fechaOrdPago;
		this.login = login;
		this.actaRecepcion = actaRecepcion;
	}
	public int getIdOrdPago() {
		return idOrdPago;
	}
	public void setIdOrdPago(int idOrdPago) {
		this.idOrdPago = idOrdPago;
	}
	public int getIdrecep() {
		return idrecep;
	}
	public void setIdrecep(int idrecep) {
		this.idrecep = idrecep;
	}
	public String getNumOrdPago() {
		return numOrdPago;
	}
	public void setNumOrdPago(String numOrdPago) {
		this.numOrdPago = numOrdPago;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getFechaOrdPago() {
		return fechaOrdPago;
	}
	public void setFechaOrdPago(String fechaOrdPago) {
		this.fechaOrdPago = fechaOrdPago;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public ActaRecepcion getActaRecepcion() {
		return actaRecepcion;
	}
	public void setActaRecepcion(ActaRecepcion actaRecepcion) {
		this.actaRecepcion = actaRecepcion;
	}
	@Override
	public String toString() {
		return "OrdenPago [idOrdPago=" + idOrdPago + ", idrecep=" + idrecep + ", numOrdPago=" + numOrdPago + ", precio="
				+ precio + ", fechaOrdPago=" + fechaOrdPago + ", login=" + login + ", actaRecepcion=" + actaRecepcion
				+ "]";
	}
	
}
