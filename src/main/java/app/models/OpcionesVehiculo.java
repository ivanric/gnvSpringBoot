package app.models;

import java.util.List;

public class OpcionesVehiculo {
	protected Integer numSolt;
	protected List<TipoVehiculo> tipoVehiculo;
	protected List<MarcaVehiculo> marcaVehiculo;
	protected List<ModeloVehiculo> modeloVehiculo;
	protected List<TipoMotorVehiculo> tipoMotor;
	protected List<TipoServicioVehiculo> tipoServicio;
	protected List<CombustibleVehiculo> combustibles;
	public OpcionesVehiculo() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public OpcionesVehiculo(Integer numSolt, List<TipoVehiculo> tipoVehiculo, List<MarcaVehiculo> marcaVehiculo,
			List<ModeloVehiculo> modeloVehiculo, List<TipoMotorVehiculo> tipoMotor,
			List<TipoServicioVehiculo> tipoServicio, List<CombustibleVehiculo> combustibles) {
		super();
		this.numSolt = numSolt;
		this.tipoVehiculo = tipoVehiculo;
		this.marcaVehiculo = marcaVehiculo;
		this.modeloVehiculo = modeloVehiculo;
		this.tipoMotor = tipoMotor;
		this.tipoServicio = tipoServicio;
		this.combustibles = combustibles;
	}
	public Integer getNumSolt() {
		return numSolt;
	}
	public void setNumSolt(Integer numSolt) {
		this.numSolt = numSolt;
	}
	public List<TipoVehiculo> getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(List<TipoVehiculo> tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public List<MarcaVehiculo> getMarcaVehiculo() {
		return marcaVehiculo;
	}
	public void setMarcaVehiculo(List<MarcaVehiculo> marcaVehiculo) {
		this.marcaVehiculo = marcaVehiculo;
	}
	public List<ModeloVehiculo> getModeloVehiculo() {
		return modeloVehiculo;
	}
	public void setModeloVehiculo(List<ModeloVehiculo> modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}
	public List<TipoMotorVehiculo> getTipoMotor() {
		return tipoMotor;
	}
	public void setTipoMotor(List<TipoMotorVehiculo> tipoMotor) {
		this.tipoMotor = tipoMotor;
	}
	public List<TipoServicioVehiculo> getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(List<TipoServicioVehiculo> tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public List<CombustibleVehiculo> getCombustibles() {
		return combustibles;
	}
	public void setCombustibles(List<CombustibleVehiculo> combustibles) {
		this.combustibles = combustibles;
	}
	@Override
	public String toString() {
		return "OpcionesVehiculo [numSolt=" + numSolt + ", tipoVehiculo=" + tipoVehiculo + ", marcaVehiculo="
				+ marcaVehiculo + ", modeloVehiculo=" + modeloVehiculo + ", tipoMotor=" + tipoMotor + ", tipoServicio="
				+ tipoServicio + ", combustibles=" + combustibles + "]";
	}
	
	
}
