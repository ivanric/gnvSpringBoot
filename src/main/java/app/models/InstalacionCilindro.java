package app.models;

public class InstalacionCilindro {
	protected int idcil,idregistroKit;
	protected String serie;
	protected Cilindro cilindro;
	public InstalacionCilindro() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public InstalacionCilindro(int idcil, int idregistroKit, String serie, Cilindro cilindro) {
//		super();
		this.idcil = idcil;
		this.idregistroKit = idregistroKit;
		this.serie = serie;
		this.cilindro = cilindro;
	}
	public int getIdcil() {
		return idcil;
	}
	public void setIdcil(int idcil) {
		this.idcil = idcil;
	}
	public int getIdregistroKit() {
		return idregistroKit;
	}
	public void setIdregistroKit(int idregistroKit) {
		this.idregistroKit = idregistroKit;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Cilindro getCilindro() {
		return cilindro;
	}
	public void setCilindro(Cilindro cilindro) {
		this.cilindro = cilindro;
	}
	@Override
	public String toString() {
		return "InstalacionCilindro [idcil=" + idcil + ", idregistroKit=" + idregistroKit + ", serie=" + serie
				+ ", cilindro=" + cilindro + "]";
	}
	
}
