package co.edu.usbcali.modelo;
// Generated 14/05/2017 01:16:03 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

	private long usuCedula;
	private Bitacora bitacora;
	private Fotografia fotografia;
	private TipoUsuario tipoUsuario;
	private String usuPrimernombre;
	private String usuSegundonombre;
	private String usuPrimerapellido;
	private String usuSegundoapellido;
	private String usuPassword;
	private Date usuFechnacimiento;
	private String direccion;
	private String ciudad;
	private String telefono;
	private Long huusId;
	private String estado;
	private String ingresoidentificacion;
	private String usuCreador;
	private String usuModificador;
	private Date fechaCreador;
	private Date fechaModificador;
	private Set<UsuarioHuella> usuarioHuellas = new HashSet<UsuarioHuella>(0);

	public Usuario() {
	}

	public Usuario(long usuCedula, String usuPrimernombre, String usuPrimerapellido, Date usuFechnacimiento,
			String usuCreador, Date fechaCreador) {
		this.usuCedula = usuCedula;
		this.usuPrimernombre = usuPrimernombre;
		this.usuPrimerapellido = usuPrimerapellido;
		this.usuFechnacimiento = usuFechnacimiento;
		this.usuCreador = usuCreador;
		this.fechaCreador = fechaCreador;
	}

	public Usuario(long usuCedula, Bitacora bitacora, Fotografia fotografia, TipoUsuario tipoUsuario,
			String usuPrimernombre, String usuSegundonombre, String usuPrimerapellido, String usuSegundoapellido,
			String usuPassword, Date usuFechnacimiento, String direccion, String ciudad, String telefono, Long huusId,
			String estado, String ingresoidentificacion, String usuCreador, String usuModificador, Date fechaCreador,
			Date fechaModificador, Set<UsuarioHuella> usuarioHuellas) {
		this.usuCedula = usuCedula;
		this.bitacora = bitacora;
		this.fotografia = fotografia;
		this.tipoUsuario = tipoUsuario;
		this.usuPrimernombre = usuPrimernombre;
		this.usuSegundonombre = usuSegundonombre;
		this.usuPrimerapellido = usuPrimerapellido;
		this.usuSegundoapellido = usuSegundoapellido;
		this.usuPassword = usuPassword;
		this.usuFechnacimiento = usuFechnacimiento;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.huusId = huusId;
		this.estado = estado;
		this.ingresoidentificacion = ingresoidentificacion;
		this.usuCreador = usuCreador;
		this.usuModificador = usuModificador;
		this.fechaCreador = fechaCreador;
		this.fechaModificador = fechaModificador;
		this.usuarioHuellas = usuarioHuellas;
	}

	public long getUsuCedula() {
		return this.usuCedula;
	}

	public void setUsuCedula(long usuCedula) {
		this.usuCedula = usuCedula;
	}

	public Bitacora getBitacora() {
		return this.bitacora;
	}

	public void setBitacora(Bitacora bitacora) {
		this.bitacora = bitacora;
	}

	public Fotografia getFotografia() {
		return this.fotografia;
	}

	public void setFotografia(Fotografia fotografia) {
		this.fotografia = fotografia;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsuPrimernombre() {
		return this.usuPrimernombre;
	}

	public void setUsuPrimernombre(String usuPrimernombre) {
		this.usuPrimernombre = usuPrimernombre;
	}

	public String getUsuSegundonombre() {
		return this.usuSegundonombre;
	}

	public void setUsuSegundonombre(String usuSegundonombre) {
		this.usuSegundonombre = usuSegundonombre;
	}

	public String getUsuPrimerapellido() {
		return this.usuPrimerapellido;
	}

	public void setUsuPrimerapellido(String usuPrimerapellido) {
		this.usuPrimerapellido = usuPrimerapellido;
	}

	public String getUsuSegundoapellido() {
		return this.usuSegundoapellido;
	}

	public void setUsuSegundoapellido(String usuSegundoapellido) {
		this.usuSegundoapellido = usuSegundoapellido;
	}

	public String getUsuPassword() {
		return this.usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}

	public Date getUsuFechnacimiento() {
		return this.usuFechnacimiento;
	}

	public void setUsuFechnacimiento(Date usuFechnacimiento) {
		this.usuFechnacimiento = usuFechnacimiento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getHuusId() {
		return this.huusId;
	}

	public void setHuusId(Long huusId) {
		this.huusId = huusId;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIngresoidentificacion() {
		return this.ingresoidentificacion;
	}

	public void setIngresoidentificacion(String ingresoidentificacion) {
		this.ingresoidentificacion = ingresoidentificacion;
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
