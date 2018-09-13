package app.models;

public class ChipRom {
	protected Integer idRom,bloqueado;
	protected String nombreChip,fecha;
	public ChipRom() {
		//super();
	}
	public ChipRom(Integer idRom, Integer bloqueado, String nombreChip, String fecha) {
//		super();
		this.idRom = idRom;
		this.bloqueado = bloqueado;
		this.nombreChip = nombreChip;
		this.fecha = fecha;
	}
	public Integer getIdRom() {
		return idRom;
	}
	public void setIdRom(Integer idRom) {
		this.idRom = idRom;
	}
	public Integer getBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(Integer bloqueado) {
		this.bloqueado = bloqueado;
	}
	public String getNombreChip() {
		return nombreChip;
	}
	public void setNombreChip(String nombreChip) {
		this.nombreChip = nombreChip;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "ChipRom [idRom=" + idRom + ", bloqueado=" + bloqueado + ", nombreChip=" + nombreChip + ", fecha="
				+ fecha + "]";
	}
	
	
}
