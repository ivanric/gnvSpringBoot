package app.models;

public class Reductor {
	protected int idreduc;
	protected String serie;
	protected String tipoTecnologia;
	protected int idmarcred;
	protected int estado;
	protected MarcaReductor marcaReductor;
	public Reductor() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Reductor(int idreduc, String serie, String tipoTecnologia, int idmarcred, int estado,
			MarcaReductor marcaReductor) {
//		super();
		this.idreduc = idreduc;
		this.serie = serie;
		this.tipoTecnologia = tipoTecnologia;
		this.idmarcred = idmarcred;
		this.estado = estado;
		this.marcaReductor = marcaReductor;
	}
	public int getIdreduc() {
		return idreduc;
	}
	public void setIdreduc(int idreduc) {
		this.idreduc = idreduc;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getTipoTecnologia() {
		return tipoTecnologia;
	}
	public void setTipoTecnologia(String tipoTecnologia) {
		this.tipoTecnologia = tipoTecnologia;
	}
	public int getIdmarcred() {
		return idmarcred;
	}
	public void setIdmarcred(int idmarcred) {
		this.idmarcred = idmarcred;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public MarcaReductor getMarcaReductor() {
		return marcaReductor;
	}
	public void setMarcaReductor(MarcaReductor marcaReductor) {
		this.marcaReductor = marcaReductor;
	}
	@Override
	public String toString() {
		return "Reductor [idreduc=" + idreduc + ", serie=" + serie + ", tipoTecnologia=" + tipoTecnologia
				+ ", idmarcred=" + idmarcred + ", estado=" + estado + ", marcaReductor=" + marcaReductor + "]";
	}
	
	
}
