package app.models;

public class Telefono {
	protected  int idper;
	protected String numero;
	public Telefono() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Telefono(int idper, String numero) {
//		super();
		this.idper = idper;
		this.numero = numero;
	}
	public int getIdper() {
		return idper;
	}
	public void setIdper(int idper) {
		this.idper = idper;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Telefono [idper=" + idper + ", numero=" + numero + "]";
	}
	
}
