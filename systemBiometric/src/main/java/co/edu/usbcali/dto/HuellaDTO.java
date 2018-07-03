package co.edu.usbcali.dto;

import java.util.Date;

/***
 * 
 * @author Toshiro Alejandro Kuratomi
 * fecha_creacion: 07-05-17
 *
 * codretorno: 
 * 0: nulo
 * 10: ok
 * 20: falla
 */


public class HuellaDTO {
	
	private long cedula;
	private byte[] huella;
	private String usuario;
	private Date fecha;
	private String desRetorno;
	private int codRetorno;
	
	
	public long getCedula() {
		return cedula;
	}
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}
	public byte[] getHuella() {
		return huella;
	}
	public void setHuella(byte[] huella) {
		this.huella = huella;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDesRetorno() {
		return desRetorno;
	}
	public void setDesRetorno(String desRetorno) {
		this.desRetorno = desRetorno;
	}
	public int getCodRetorno() {
		return codRetorno;
	}
	public void setCodRetorno(int codRetorno) {
		this.codRetorno = codRetorno;
	}
	
			
}
