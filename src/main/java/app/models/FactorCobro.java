package app.models;

public class FactorCobro {
	protected int idfactCobro;
	protected String precio;
	protected int estado;
	public FactorCobro() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public FactorCobro(int idfactCobro, String precio, int estado) {
//		super();
		this.idfactCobro = idfactCobro;
		this.precio = precio;
		this.estado = estado;
	}
	public int getIdfactCobro() {
		return idfactCobro;
	}
	public void setIdfactCobro(int idfactCobro) {
		this.idfactCobro = idfactCobro;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "FactorCobro [idfactCobro=" + idfactCobro + ", precio=" + precio + ", estado=" + estado + "]";
	}
	
}
