package co.edu.usbcali.modelo;
// Generated 14/05/2017 01:16:03 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Salida generated by hbm2java
 */
public class Salida implements java.io.Serializable {

	private long saliId;
	private Date fechasalida;
	private Date horasalida;
	private String usuCreador;
	private String usuModificador;
	private Date fechaCreador;
	private Date fechaModificador;
	private Set<InoutDaily> inoutDailies = new HashSet<InoutDaily>(0);

	public Salida() {
	}

	public Salida(long saliId, String usuCreador, Date fechaCreador) {
		this.saliId = saliId;
		this.usuCreador = usuCreador;
		this.fechaCreador = fechaCreador;
	}

	public Salida(long saliId, Date fechasalida, Date horasalida, String usuCreador, String usuModificador,
			Date fechaCreador, Date fechaModificador, Set<InoutDaily> inoutDailies) {
		this.saliId = saliId;
		this.fechasalida = fechasalida;
		this.horasalida = horasalida;
		this.usuCreador = usuCreador;
		this.usuModificador = usuModificador;
		this.fechaCreador = fechaCreador;
		this.fechaModificador = fechaModificador;
		this.inoutDailies = inoutDailies;
	}

	public long getSaliId() {
		return this.saliId;
	}

	public void setSaliId(long saliId) {
		this.saliId = saliId;
	}

	public Date getFechasalida() {
		return this.fechasalida;
	}

	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}

	public Date getHorasalida() {
		return this.horasalida;
	}

	public void setHorasalida(Date horasalida) {
		this.horasalida = horasalida;
	}

	public String getUsuCreador() {
		return this.usuCreador;
	}

	public void setUsuCreador(String usuCreador) {
		this.usuCreador = usuCreador;
	}

	public String getUsuModificador() {
		return this.usuModificador;
	}

	public void setUsuModificador(String usuModificador) {
		this.usuModificador = usuModificador;
	}

	public Date getFechaCreador() {
		return this.fechaCreador;
	}

	public void setFechaCreador(Date fechaCreador) {
		this.fechaCreador = fechaCreador;
	}

	public Date getFechaModificador() {
		return this.fechaModificador;
	}

	public void setFechaModificador(Date fechaModificador) {
		this.fechaModificador = fechaModificador;
	}

	public Set<InoutDaily> getInoutDailies() {
		return this.inoutDailies;
	}

	public void setInoutDailies(Set<InoutDaily> inoutDailies) {
		this.inoutDailies = inoutDailies;
	}

}