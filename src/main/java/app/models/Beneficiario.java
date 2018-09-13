package app.models;

import java.util.List;

public class Beneficiario {
	protected  Integer idben;
	protected String institucion;
	protected String descripcion;
	protected Integer estado;
	protected Integer idper;
	protected List<Documento> documentos;
	public Beneficiario() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public Beneficiario(Integer idben, String institucion, String descripcion, Integer estado, Integer idper,
			List<Documento> documentos) {
		super();
		this.idben = idben;
		this.institucion = institucion;
		this.descripcion = descripcion;
		this.estado = estado;
		this.idper = idper;
		this.documentos = documentos;
	}
	public Integer getIdben() {
		return idben;
	}
	public void setIdben(Integer idben) {
		this.idben = idben;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Integer getIdper() {
		return idper;
	}
	public void setIdper(Integer idper) {
		this.idper = idper;
	}
	public List<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	@Override
	public String toString() {
		return "Beneficiario [idben=" + idben + ", institucion=" + institucion + ", descripcion=" + descripcion
				+ ", estado=" + estado + ", idper=" + idper + ", documentos=" + documentos + "]";
	}
	
	
	
}
