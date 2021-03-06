package co.edu.usbcali.modelo;
// Generated 14/05/2017 01:16:03 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * HuellaDerecha generated by hbm2java
 */
public class HuellaDerecha implements java.io.Serializable {

	private long hudeId;
	private byte[] huelladerecha;
	private String usuCreador;
	private String usuModificador;
	private Date fechaCreador;
	private Date fechaModificador;
	private Set<UsuarioHuella> usuarioHuellas = new HashSet<UsuarioHuella>(0);

	public HuellaDerecha() {
	}

	public HuellaDerecha(long hudeId, String usuCreador, Date fechaCreador) {
		this.hudeId = hudeId;
		this.usuCreador = usuCreador;
		this.fechaCreador = fechaCreador;
	}

	public HuellaDerecha(long hudeId, byte[] huelladerecha, String usuCreador, String usuModificador, Date fechaCreador,
			Date fechaModificador, Set<UsuarioHuella> usuarioHuellas) {
		this.hudeId = hudeId;
		this.huelladerecha = huelladerecha;
		this.usuCreador = usuCreador;
		this.usuModificador = usuModificador;
		this.fechaCreador = fechaCreador;
		this.fechaModificador = fechaModificador;
		this.usuarioHuellas = usuarioHuellas;
	}

	public long getHudeId() {
		return this.hudeId;
	}

	public void setHudeId(long hudeId) {
		this.hudeId = hudeId;
	}

	public byte[] getHuelladerecha() {
		return this.huelladerecha;
	}

	public void setHuelladerecha(byte[] huelladerecha) {
		this.huelladerecha = huelladerecha;
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

	public Set<UsuarioHuella> getUsuarioHuellas() {
		return this.usuarioHuellas;
	}

	public void setUsuarioHuellas(Set<UsuarioHuella> usuarioHuellas) {
		this.usuarioHuellas = usuarioHuellas;
	}

}
