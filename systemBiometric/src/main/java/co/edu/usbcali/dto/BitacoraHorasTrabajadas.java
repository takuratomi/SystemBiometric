package co.edu.usbcali.dto;

import java.util.Date;

public class BitacoraHorasTrabajadas {

	private long bitaId;
	private long indaId;
	private long ingrId;
	private long saliId;
	private String observacion;
	private String fechaingreso;
	private String fechasalida;
	private String horaingreso;
	private String horasalida;
	private String horasTrabajadas;

	public BitacoraHorasTrabajadas() {
		super();
	}

	public BitacoraHorasTrabajadas(long bitaId, long indaId, long ingrId, long saliId, String observacion,
			String fechaingreso, String fechasalida, String horaingreso, String horasalida, String horasTrabajadas) {
		super();
		this.bitaId = bitaId;
		this.indaId = indaId;
		this.ingrId = ingrId;
		this.saliId = saliId;
		this.observacion = observacion;
		this.fechaingreso = fechaingreso;
		this.fechasalida = fechasalida;
		this.horaingreso = horaingreso;
		this.horasalida = horasalida;
		this.horasTrabajadas = horasTrabajadas;
	}

	public long getBitaId() {
		return bitaId;
	}

	public void setBitaId(long bitaId) {
		this.bitaId = bitaId;
	}

	public long getIndaId() {
		return indaId;
	}

	public void setIndaId(long indaId) {
		this.indaId = indaId;
	}

	public long getIngrId() {
		return ingrId;
	}

	public void setIngrId(long ingrId) {
		this.ingrId = ingrId;
	}

	public long getSaliId() {
		return saliId;
	}

	public void setSaliId(long saliId) {
		this.saliId = saliId;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(String fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public String getFechasalida() {
		return fechasalida;
	}

	public void setFechasalida(String fechasalida) {
		this.fechasalida = fechasalida;
	}

	public String getHoraingreso() {
		return horaingreso;
	}

	public void setHoraingreso(String horaingreso) {
		this.horaingreso = horaingreso;
	}

	public String getHorasalida() {
		return horasalida;
	}

	public void setHorasalida(String horasalida) {
		this.horasalida = horasalida;
	}

	public String getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(String horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

}
