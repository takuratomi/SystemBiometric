package co.edu.usbcali.modelo;
// Generated 14/05/2017 01:16:03 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bitacora generated by hbm2java
 */
public class Bitacora implements java.io.Serializable {

	private long bitaId;
	private String usuCreador;
	private String usuModificador;
	private Date fechaCreador;
	private Date fechaModificador;
	private Set<InoutDaily> inoutDailies = new HashSet<InoutDaily>(0);
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);

	public Bitacora() {
	}

	public Bitacora(long bitaId, String usuCreador, Date fechaCreador) {
		this.bitaId = bitaId;
		this.usuCreador = usuCreador;
		this.fechaCreador = fechaCreador;
	}

	public Bitacora(long bitaId, String usuCreador, String usuModificador, Date fechaCreador, Date fechaModificador,
			Set<InoutDaily> inoutDailies, Set<Usuario> usuarios) {
		this.bitaId = bitaId;
		this.usuCreador = usuCreador;
		this.usuModificador = usuModificador;
		this.fechaCreador = fechaCreador;
		this.fechaModificador = fechaModificador;
		this.inoutDailies = inoutDailies;
		this.usuarios = usuarios;
	}

	public long getBitaId() {
		return this.bitaId;
	}

	public void setBitaId(long bitaId) {
		this.bitaId = bitaId;
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

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}