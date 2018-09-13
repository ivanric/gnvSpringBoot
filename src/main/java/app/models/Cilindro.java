package app.models;

public class Cilindro {
	protected int idcil;
	protected String capacidad;
	protected String serie;
	protected int idmarccil;
	protected int estado;
	protected MarcaCilindro marcaCilindro;
	public Cilindro() {

	}
	public Cilindro(int idcil, String capacidad, String serie, int idmarccil, int estado, MarcaCilindro marcaCilindro) {
//		super();
		this.idcil = idcil;
		this.capacidad = capacidad;
		this.serie = serie;
		this.idmarccil = idmarccil;
		this.estado = estado;
		this.marcaCilindro = marcaCilindro;
	}
	public int getIdcil() {
		return idcil;
	}
	public void setIdcil(int idcil) {
		this.idcil = idcil;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public int getIdmarccil() {
		return idmarccil;
	}
	public void setIdmarccil(int idmarccil) {
		this.idmarccil = idmarccil;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public MarcaCilindro getMarcaCilindro() {
		return marcaCilindro;
	}
	public void setMarcaCilindro(MarcaCilindro marcaCilindro) {
		this.marcaCilindro = marcaCilindro;
	}
	@Override
	public String toString() {
		return "Cilindro [idcil=" + idcil + ", capacidad=" + capacidad + ", serie=" + serie + ", idmarccil=" + idmarccil
				+ ", estado=" + estado + ", marcaCilindro=" + marcaCilindro + "]";
	}
	
}
