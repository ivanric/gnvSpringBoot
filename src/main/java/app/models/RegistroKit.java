package app.models;

import java.util.ArrayList;
import java.util.List;

public class RegistroKit{
	protected Integer idregistroKit,desinstaladoKitGlpSiNo,certificadoDesintalacionSiNo,idordserv,idreduc;
	protected String fechaInstalacion,fechaConversion,numSerieRegulador,certificadoKit,numSerieMotor,login;
	protected OrdenServicio ordenServicio;
	protected Reductor reductor;
	protected List<InstalacionCilindro> Cilindros = new ArrayList<InstalacionCilindro>() ;
	public RegistroKit() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public RegistroKit(Integer idregistroKit, Integer desinstaladoKitGlpSiNo, Integer certificadoDesintalacionSiNo,
			Integer idordserv, Integer idreduc, String fechaInstalacion, String fechaConversion,
			String numSerieRegulador, String certificadoKit, String numSerieMotor, String login,
			OrdenServicio ordenServicio, Reductor reductor, List<InstalacionCilindro> cilindros) {
		super();
		this.idregistroKit = idregistroKit;
		this.desinstaladoKitGlpSiNo = desinstaladoKitGlpSiNo;
		this.certificadoDesintalacionSiNo = certificadoDesintalacionSiNo;
		this.idordserv = idordserv;
		this.idreduc = idreduc;
		this.fechaInstalacion = fechaInstalacion;
		this.fechaConversion = fechaConversion;
		this.numSerieRegulador = numSerieRegulador;
		this.certificadoKit = certificadoKit;
		this.numSerieMotor = numSerieMotor;
		this.login = login;
		this.ordenServicio = ordenServicio;
		this.reductor = reductor;
		Cilindros = cilindros;
	}
	public Integer getIdregistroKit() {
		return idregistroKit;
	}
	public void setIdregistroKit(Integer idregistroKit) {
		this.idregistroKit = idregistroKit;
	}
	public Integer getDesinstaladoKitGlpSiNo() {
		return desinstaladoKitGlpSiNo;
	}
	public void setDesinstaladoKitGlpSiNo(Integer desinstaladoKitGlpSiNo) {
		this.desinstaladoKitGlpSiNo = desinstaladoKitGlpSiNo;
	}
	public Integer getCertificadoDesintalacionSiNo() {
		return certificadoDesintalacionSiNo;
	}
	public void setCertificadoDesintalacionSiNo(Integer certificadoDesintalacionSiNo) {
		this.certificadoDesintalacionSiNo = certificadoDesintalacionSiNo;
	}
	public Integer getIdordserv() {
		return idordserv;
	}
	public void setIdordserv(Integer idordserv) {
		this.idordserv = idordserv;
	}
	public Integer getIdreduc() {
		return idreduc;
	}
	public void setIdreduc(Integer idreduc) {
		this.idreduc = idreduc;
	}
	public String getFechaInstalacion() {
		return fechaInstalacion;
	}
	public void setFechaInstalacion(String fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}
	public String getFechaConversion() {
		return fechaConversion;
	}
	public void setFechaConversion(String fechaConversion) {
		this.fechaConversion = fechaConversion;
	}
	public String getNumSerieRegulador() {
		return numSerieRegulador;
	}
	public void setNumSerieRegulador(String numSerieRegulador) {
		this.numSerieRegulador = numSerieRegulador;
	}
	public String getCertificadoKit() {
		return certificadoKit;
	}
	public void setCertificadoKit(String certificadoKit) {
		this.certificadoKit = certificadoKit;
	}
	public String getNumSerieMotor() {
		return numSerieMotor;
	}
	public void setNumSerieMotor(String numSerieMotor) {
		this.numSerieMotor = numSerieMotor;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public Reductor getReductor() {
		return reductor;
	}
	public void setReductor(Reductor reductor) {
		this.reductor = reductor;
	}
	public List<InstalacionCilindro> getCilindros() {
		return Cilindros;
	}
	public void setCilindros(List<InstalacionCilindro> cilindros) {
		Cilindros = cilindros;
	}
	@Override
	public String toString() {
		return "RegistroKit [idregistroKit=" + idregistroKit + ", desinstaladoKitGlpSiNo=" + desinstaladoKitGlpSiNo
				+ ", certificadoDesintalacionSiNo=" + certificadoDesintalacionSiNo + ", idordserv=" + idordserv
				+ ", idreduc=" + idreduc + ", fechaInstalacion=" + fechaInstalacion + ", fechaConversion="
				+ fechaConversion + ", numSerieRegulador=" + numSerieRegulador + ", certificadoKit=" + certificadoKit
				+ ", numSerieMotor=" + numSerieMotor + ", login=" + login + ", ordenServicio=" + ordenServicio
				+ ", reductor=" + reductor + ", Cilindros=" + Cilindros + "]";
	}
	
	
}
