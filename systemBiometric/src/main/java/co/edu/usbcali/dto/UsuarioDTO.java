package co.edu.usbcali.dto;

import java.util.Date;

public class UsuarioDTO {

	private long usuCedula;
	private String usuPrimernombre;
	private String usuSegundonombre;
	private String usuPrimerapellido;
	private String usuSegundoapellido;
	private String usuLogin;
	private Date usuFechnacimiento;
	private long tusuId;
	private String tusuNombre;
	
	
	public long getUsuCedula() {
		return usuCedula;
	}
	public String getUsuPrimernombre() {
		return usuPrimernombre;
	}
	public String getUsuSegundonombre() {
		return usuSegundonombre;
	}
	public String getUsuPrimerapellido() {
		return usuPrimerapellido;
	}
	public String getUsuSegundoapellido() {
		return usuSegundoapellido;
	}
	public String getUsuLogin() {
		return usuLogin;
	}
	public Date getUsuFechnacimiento() {
		return usuFechnacimiento;
	}
	public long getTusuId() {
		return tusuId;
	}
	public String getTusuNombre() {
		return tusuNombre;
	}
	public void setUsuCedula(long usuCedula) {
		this.usuCedula = usuCedula;
	}
	public void setUsuPrimernombre(String usuPrimernombre) {
		this.usuPrimernombre = usuPrimernombre;
	}
	public void setUsuSegundonombre(String usuSegundonombre) {
		this.usuSegundonombre = usuSegundonombre;
	}
	public void setUsuPrimerapellido(String usuPrimerapellido) {
		this.usuPrimerapellido = usuPrimerapellido;
	}
	public void setUsuSegundoapellido(String usuSegundoapellido) {
		this.usuSegundoapellido = usuSegundoapellido;
	}
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}
	public void setUsuFechnacimiento(Date usuFechnacimiento) {
		this.usuFechnacimiento = usuFechnacimiento;
	}
	public void setTusuId(long tusuId) {
		this.tusuId = tusuId;
	}
	public void setTusuNombre(String tusuNombre) {
		this.tusuNombre = tusuNombre;
	}	
	
}
