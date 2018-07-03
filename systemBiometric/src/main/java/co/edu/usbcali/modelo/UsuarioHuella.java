package co.edu.usbcali.modelo;
// Generated 14/05/2017 01:16:03 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

/**
 * UsuarioHuella generated by hbm2java
 */
public class UsuarioHuella implements java.io.Serializable {

	private long ushuId;
	private HuellaDerecha huellaDerecha;
	private HuellaIzquierda huellaIzquierda;
	private Usuario usuario;
	private String usuCreador;
	private String usuModificador;
	private Date fechaCreador;
	private Date fechaModificador;

	public UsuarioHuella() {
	}

	public UsuarioHuella(long ushuId, HuellaDerecha huellaDerecha, String usuCreador, Date fechaCreador) {
		this.ushuId = ushuId;
		this.huellaDerecha = huellaDerecha;
		this.usuCreador = usuCreador;
		this.fechaCreador = fechaCreador;
	}

	public UsuarioHuella(long ushuId, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda, Usuario usuario,
			String usuCreador, String usuModificador, Date fechaCreador, Date fechaModificador) {
		this.ushuId = ushuId;
		this.huellaDerecha = huellaDerecha;
		this.huellaIzquierda = huellaIzquierda;
		this.usuario = usuario;
		this.usuCreador = usuCreador;
		this.usuModificador = usuModificador;
		this.fechaCreador = fechaCreador;
		this.fechaModificador = fechaModificador;
	}

	public long getUshuId() {
		return this.ushuId;
	}

	public void setUshuId(long ushuId) {
		this.ushuId = ushuId;
	}

	public HuellaDerecha getHuellaDerecha() {
		return this.huellaDerecha;
	}

	public void setHuellaDerecha(HuellaDerecha huellaDerecha) {
		this.huellaDerecha = huellaDerecha;
	}

	public HuellaIzquierda getHuellaIzquierda() {
		return this.huellaIzquierda;
	}

	public void setHuellaIzquierda(HuellaIzquierda huellaIzquierda) {
		this.huellaIzquierda = huellaIzquierda;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

}
