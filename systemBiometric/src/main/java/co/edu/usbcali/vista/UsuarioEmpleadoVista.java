package co.edu.usbcali.vista;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import org.hamcrest.core.IsNull;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.Fotografia;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;
import co.edu.usbcali.modelo.TipoUsuario;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.UsuarioHuella;
import co.edu.usbcali.utilidad.Utilidad;

@ManagedBean
@ViewScoped
public class UsuarioEmpleadoVista {

	public final static Logger log = LoggerFactory.getLogger(UsuarioEmpleadoVista.class);

	@ManagedProperty(value = "#{delegadoDelNegocio}")
	private IDelegadoDelNegocio delegadoDelNegocio;

	/* load files */
	private UploadedFile loadDerImagen;
	private UploadedFile loadIzImagen;
	private UploadedFile loadFotografia;

	private InputText txtIdentificacion;
	private Date txtFechaNacimiento;

	private InputText txtPrimerNombre;
	private InputText txtSegundoNombre;
	private InputText txtPrimerApellido;
	private InputText txtSegundoApellido;	
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtCiudad;
	private Password txtPasword;
	private Password txtConfirmPasword;	
	
	private boolean estadoUsuario;
	private boolean validIdentificacion;

	private InputText txtTipoUsuario;
	private SelectOneMenu somTiposUsuario;
	private SelectBooleanCheckbox schckEstado;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	private CommandButton btnRegistrarHuella;
	
	private List<Usuario> losUsuario;
	private List<SelectItem> losTipoUsuarioItems;
	
	private static final String cod_admin = "1";
	private static final String cod_gestor_empleado = "3";
	private static final String identificacion_errro_caracteres = "La cedula no puede contener caracteres";
	private static final String activo = "A";
	private static final String inactivo = "I";
	

	/***
	 * ACTIONS
	 */
	public void txtIdListener() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("txtIdListener se activo");

		Long id = 0L;
		
		
			try {
				
				id = Long.parseLong(txtIdentificacion.getValue().toString().trim());
				
				Usuario usuario = delegadoDelNegocio.consultarPorIdUsuario(id);

				if (usuario == null) {
					btnCrear.setDisabled(false);
					btnModificar.setDisabled(true);
//					btnBorrar.setDisabled(true);
					cleanForm();

				} else {
					btnCrear.setDisabled(true);
					btnModificar.setDisabled(false);
//					btnBorrar.setDisabled(false);
					schckEstado.setDisabled(false);

					txtIdentificacion.setValue(usuario.getUsuCedula());
					txtPrimerNombre.setValue(usuario.getUsuPrimernombre());
					txtSegundoNombre.setValue(usuario.getUsuSegundonombre());
					txtPrimerApellido.setValue(usuario.getUsuPrimerapellido());
					txtSegundoApellido.setValue(usuario.getUsuSegundoapellido());
					txtDireccion.setValue(usuario.getDireccion());
					txtTelefono.setValue(usuario.getTelefono());
					txtCiudad.setValue(usuario.getCiudad());
					txtFechaNacimiento = usuario.getUsuFechnacimiento();
					
					// estado
					if(usuario.getEstado().toString().trim().equals(activo))
					{
						estadoUsuario = true;
					}
					else if(usuario.getEstado().toString().trim().equals(inactivo))
					{
						estadoUsuario = false;
					}
					
					// ingreso con identificacion
					if(usuario.getIngresoidentificacion().toString().trim().equals(activo))
					{
						validIdentificacion = true;
					}
					else if(usuario.getIngresoidentificacion().toString().trim().equals(inactivo))
					{
						validIdentificacion = false;
					}

					somTiposUsuario.setValue(usuario.getTipoUsuario().getTusuId());

				}		

			}
			catch(NumberFormatException number)
			{
				FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cedula no puede contener caracteres", ""));
			}		
			catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
				log.error(e.getMessage());

			}
//		}
//		else
//		{
//			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, identificacion_errro_caracteres , ""));
//			log.error(identificacion_errro_caracteres);
//		}
	}
	
	public void paswordListener() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("lsitener active password se activo");
		
		if(!btnCrear.isDisabled() && (somTiposUsuario.getValue().toString().trim().equals(cod_admin) || somTiposUsuario.getValue().toString().trim().equals(cod_gestor_empleado)))
		{
			log.info(" entro en el listerner activa passsword");
			txtPasword.setDisabled(false);
			txtConfirmPasword.setDisabled(false);
		}
		
		if(!btnModificar.isDisabled() && (somTiposUsuario.getValue().toString().trim().equals(cod_admin) || somTiposUsuario.getValue().toString().trim().equals(cod_gestor_empleado)))
		{
			log.info(" entro en el listerner activa passsword");
			txtPasword.setDisabled(false);
			txtConfirmPasword.setDisabled(false);
		}
		
		if(btnCrear.isDisabled() && btnModificar.isDisabled())
		{
			txtPasword.setDisabled(true);
			txtConfirmPasword.setDisabled(true);
		}
		
		if(!somTiposUsuario.getValue().toString().trim().equals(cod_admin) && !somTiposUsuario.getValue().toString().trim().equals(cod_gestor_empleado))
		{
			txtPasword.setDisabled(true);
			txtConfirmPasword.setDisabled(true);
		}
	}
	
	/* NO SE USA, PERO SE RESERVA EL CODIGO
	public void btHuellasListener() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("lsitener active btn huellas se activo");
		
		if( (btnCrear.isDisabled() && !validIdentificacion) || (btnModificar.isDisabled() && !validIdentificacion) )
		{
			btnRegistrarHuella.setDisabled(true);
		}
		if( (!btnCrear.isDisabled() && validIdentificacion) || (!btnModificar.isDisabled() && validIdentificacion)  )
		{
			btnRegistrarHuella.setDisabled(false);
		}
	}*/

	public String btnCrearAction() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("  btnCrear  ACTIVADO   ");

		Usuario usuario = new Usuario();
		Fotografia fotografia = new Fotografia();		
		TipoUsuario tipoUsuario = new TipoUsuario();
		UsuarioHuella usuarioHuella = new UsuarioHuella();
		Bitacora bitacora = new Bitacora();
		HuellaDerecha huellaDerecha = new HuellaDerecha();
		HuellaIzquierda huellaIzquierda = new HuellaIzquierda();
		
		Long id;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.getAuthentication();
		String usuCreador = securityContext.getAuthentication().getName();
		Date fechaCreacion = new Date();
		Date fechaNacimiento;
		Long idTipoUsuario;
//		byte [] temporal = new byte[]{0,0,0};
		try {
			
			id = Long.parseLong(txtIdentificacion.getValue().toString().trim());				
			fechaNacimiento = txtFechaNacimiento;
			
			/* FOTOGRAFIA */
			
			fotografia.setFotoId(delegadoDelNegocio.getConsecutivoFotografia());			
			fotografia.setUsuCreador(usuCreador);
			fotografia.setFechaCreador(fechaCreacion);
			
			if(loadFotografia.getSize() <=0 )
			{
				String path = "C:\\workspaces\\workspace_sts\\systemBiometric\\imagenes\\persona_default.png";
				
				File file = new File(path);
				byte[] bFile = new byte[(int) file.length()];
				FileInputStream fileInputStream;
				try {
					 fileInputStream = new FileInputStream(file);
					 fileInputStream.read(bFile);
					 fileInputStream.close();
					 fotografia.setImagenbin(bFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			else
			{
				fotografia.setImagenbin(loadFotografia.getContents());
			}
			
			
			
			/* BITACORA */
			bitacora.setBitaId(delegadoDelNegocio.getConsecutivoBita());
			bitacora.setUsuCreador(usuCreador);
			bitacora.setFechaCreador(fechaCreacion);
			
			usuario.setUsuCedula(id);
			usuario.setUsuPrimernombre(txtPrimerNombre.getValue().toString().trim());
			usuario.setUsuSegundonombre(txtSegundoNombre.getValue().toString().trim());
			usuario.setUsuPrimerapellido(txtPrimerApellido.getValue().toString().trim());
			usuario.setUsuSegundoapellido(txtSegundoApellido.getValue().toString().trim());
			usuario.setCiudad(txtCiudad.getValue().toString().trim());
			usuario.setTelefono(txtTelefono.getValue().toString().trim());
			usuario.setDireccion(txtDireccion.getValue().toString().trim());
			usuario.setEstado(activo);  // por default cuando se crea va en activo;
			
			// validacion de creacion del usuario con uso ingreso con identificacion
			if(validIdentificacion)
			{
				usuario.setIngresoidentificacion(activo);
			}
			else
			{
				usuario.setIngresoidentificacion(inactivo);
			}
			
			if(!somTiposUsuario.getValue().toString().trim().equals("2"))
			{
				if(validationPassword(txtPasword.getValue().toString().trim(),txtConfirmPasword.getValue().toString().trim()).equals("Password coinciden"))
				{
					usuario.setUsuPassword(txtPasword.getValue().toString().trim());
				}
			}			
			
			usuario.setUsuCreador(usuCreador);
			usuario.setFechaCreador(fechaCreacion);
			usuario.setUsuFechnacimiento(fechaNacimiento);
			
			idTipoUsuario = Long.parseLong(somTiposUsuario.getValue().toString().trim());
			tipoUsuario = delegadoDelNegocio.consultarPorIdTipoUsuario(idTipoUsuario);
			
			usuario.setTipoUsuario(tipoUsuario);
			usuario.setFotografia(fotografia);
			usuario.setBitacora(bitacora);
			
			huellaDerecha.setHudeId(delegadoDelNegocio.getConsecutivoHuelDer());			
			huellaDerecha.setHuelladerecha(loadDerImagen.getContents());
			huellaDerecha.setUsuCreador(usuCreador);
			huellaDerecha.setFechaCreador(fechaCreacion);
			
			huellaIzquierda.setHullId2(delegadoDelNegocio.getConsecutivoHuelIzq());
			huellaIzquierda.setHuellaizquierda(loadIzImagen.getContents());
			huellaIzquierda.setUsuCreador(usuCreador);
			huellaIzquierda.setFechaCreador(fechaCreacion);
			
			usuarioHuella.setUshuId(delegadoDelNegocio.getConsecutivoUsHu());
			usuario.setHuusId(usuarioHuella.getUshuId());
			usuarioHuella.setUsuario(usuario);
			usuarioHuella.setHuellaDerecha(huellaDerecha);
			usuarioHuella.setHuellaIzquierda(huellaIzquierda);
			usuarioHuella.setUsuCreador(usuCreador);
			usuarioHuella.setFechaCreador(fechaCreacion);
			
			delegadoDelNegocio.crearUsuarioCompleto(usuario, bitacora, fotografia, usuarioHuella, huellaDerecha, huellaIzquierda);
			
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se creo el usuario", ""));
			log.info("SE CREO EL USUARIO");
			
		}catch(NumberFormatException number)
		{
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo con formato inadecuado", ""));
			log.error(number.getMessage());
		} 
		catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), ""));
			log.error(e1.getMessage());
		}
		return "";
	}

	public void cleanForm() {
		txtPrimerNombre.resetValue();
		txtSegundoNombre.resetValue();
		txtPrimerApellido.resetValue();
		txtSegundoApellido.resetValue();
		txtDireccion.resetValue();
		txtTelefono.resetValue();
		txtCiudad.resetValue();
		somTiposUsuario.setValue("-1");
		txtFechaNacimiento = null;

	}

	
	public void cleanFormAll() {
		
		txtIdentificacion.resetValue();
		txtPrimerNombre.resetValue();
		txtSegundoNombre.resetValue();
		txtPrimerApellido.resetValue();
		txtSegundoApellido.resetValue();
		txtDireccion.resetValue();
		txtTelefono.resetValue();
		txtCiudad.resetValue();
		somTiposUsuario.setValue("-1");
		validIdentificacion=false;
		estadoUsuario=false;				
		txtFechaNacimiento = null;
		txtPasword.resetValue();
		txtConfirmPasword.resetValue();
//		loadDerImagen = null;
//		loadIzImagen = null;
	}

	// TODO
	public String btnModificarAction()
	 {
		
		log.info("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("btnModificar ACTIVADO");
		
		Usuario usuario = new Usuario();
		Usuario usuariOriginal;
		Fotografia fotografia = new Fotografia();		
		TipoUsuario tipoUsuario = new TipoUsuario();
		UsuarioHuella usuarioHuella = new UsuarioHuella();
		Bitacora bitacora = new Bitacora();
		HuellaDerecha huellaDerecha = new HuellaDerecha();
		HuellaIzquierda huellaIzquierda = new HuellaIzquierda();
		
		Long id;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.getAuthentication();
		String usuModificar = securityContext.getAuthentication().getName();		
		Date fechaModificar = new Date();
		Date fechaNacimiento;
		Long idTipoUsuario;
		Long usuHuID = 0L;
		
		int indicador_modificacion = 0;
		int indicador_primeraDerecha = 0;
		int indicador_primeraIzquierda = 0;
		
		try {
			// NUEVO PROCESO DE MODIFICACION
			// DESCRIPCION: SE TOMAN TODOS LOS DATOS PARA DESPUES EMPEZAR A HACER LA MODIFICACION EN EL METODO DE MODIFICAR LOGICA
			// CON LA DIFERENCIA DE LAS FECHAS Y USUARIOS DE CREACION
			id = Long.parseLong(txtIdentificacion.getValue().toString().trim());				
			fechaNacimiento = txtFechaNacimiento;
			
			/* FOTOGRAFIA */						
			fotografia.setUsuModificador(usuModificar);
			fotografia.setFechaModificador(fechaModificar);
			
//			if(loadFotografia.getSize() <=0 )
//			{
//				String path = "C:\\workspaces\\workspace_sts\\systemBiometric\\imagenes\\persona_default.png";
//				
//				File file = new File(path);
//				byte[] bFile = new byte[(int) file.length()];
//				FileInputStream fileInputStream;
//				try {
//					 fileInputStream = new FileInputStream(file);
//					 fileInputStream.read(bFile);
//					 fileInputStream.close();
//					 fotografia.setImagenbin(bFile);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//			else
//			{
				fotografia.setImagenbin(loadFotografia.getContents());
//			}
			
			usuario.setUsuCedula(id);
			usuario.setUsuPrimernombre(txtPrimerNombre.getValue().toString().trim());
			usuario.setUsuSegundonombre(txtSegundoNombre.getValue().toString().trim());
			usuario.setUsuPrimerapellido(txtPrimerApellido.getValue().toString().trim());
			usuario.setUsuSegundoapellido(txtSegundoApellido.getValue().toString().trim());
			usuario.setCiudad(txtCiudad.getValue().toString().trim());
			usuario.setTelefono(txtTelefono.getValue().toString().trim());
			usuario.setDireccion(txtDireccion.getValue().toString().trim());
			// validacion del estado del usuario
			if(estadoUsuario)
			{
				usuario.setEstado(activo);
			}
			else
			{
				usuario.setEstado(inactivo);
			}	
			
			// validacion de creacion del usuario con uso ingreso con identificacion
			if(validIdentificacion)
			{
				usuario.setIngresoidentificacion(activo);
			}
			else
			{
				usuario.setIngresoidentificacion(inactivo);
			}
			
			if(!somTiposUsuario.getValue().toString().trim().equals("2"))
			{
				if(validationPassword(txtPasword.getValue().toString().trim(),txtConfirmPasword.getValue().toString().trim()).equals("Password coinciden"))
				{
					usuario.setUsuPassword(txtPasword.getValue().toString().trim());
				}
			}			
			
			usuario.setUsuModificador(usuModificar);
			usuario.setFechaModificador(fechaModificar);
			usuario.setUsuFechnacimiento(fechaNacimiento);
			
			idTipoUsuario = Long.parseLong(somTiposUsuario.getValue().toString().trim());
			tipoUsuario = delegadoDelNegocio.consultarPorIdTipoUsuario(idTipoUsuario);
			
			usuario.setTipoUsuario(tipoUsuario);
			usuario.setFotografia(fotografia);
			usuario.setBitacora(bitacora);
		
			huellaDerecha.setHuelladerecha(loadDerImagen.getContents());
			huellaDerecha.setUsuModificador(usuModificar);
			huellaDerecha.setFechaModificador(fechaModificar);

			huellaIzquierda.setHuellaizquierda(loadIzImagen.getContents());
			huellaIzquierda.setUsuModificador(usuModificar);
			huellaIzquierda.setFechaModificador(fechaModificar);

			usuario.setHuusId(usuarioHuella.getUshuId());
			usuarioHuella.setUsuario(usuario);
			usuarioHuella.setHuellaDerecha(huellaDerecha);
			usuarioHuella.setHuellaIzquierda(huellaIzquierda);
			usuarioHuella.setUsuModificador(usuModificar);
			usuarioHuella.setFechaModificador(fechaModificar);
			
			delegadoDelNegocio.modificarUsuarioCompleto(usuario, bitacora, fotografia, usuarioHuella, huellaDerecha, huellaIzquierda);
			
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico el usuario", ""));
			log.info("SE MODIFICO EL USUARIO");
			
		}catch(NumberFormatException number)
		{
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo con formato inadecuado", ""));
			log.error(number.getMessage());
		} 
		catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), ""));
			log.error(e1.getMessage());
		}
		
		 return "";
	 }

	public String btnBorrarAction() {
		log.info("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("btnBorrar ACTIVADO ");
		
		Long id = 0L;
		Usuario usuario;
		boolean is_numerico = false; 
		Utilidad utilidad = new Utilidad();
		
		is_numerico = utilidad.isNumeric(txtIdentificacion.getValue().toString().trim());
		
		if(utilidad.isNumeric(txtIdentificacion.getValue().toString().trim()))
		{
			id = Long.parseLong(txtIdentificacion.getValue().toString().trim());
			try {
				
				usuario = delegadoDelNegocio.consultarPorIdUsuario(id);
				
				if(usuario != null){
//					delegadoDelNegocio
					log.info("USUARIO BORRRADO");
				}				
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), ""));
				log.error(e.getMessage());
			}						
		}
		else{
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, identificacion_errro_caracteres , ""));
			log.error(identificacion_errro_caracteres);
			
		}
		
		return "";
	}

	public String limpiarAction() {
		log.info("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("btnLimpiar  ACTIVADO");

		cleanFormAll();
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
//		btnBorrar.setDisabled(true);
		btnLimpiar.setDisabled(false);
//		btnRegistrarHuella.setDisabled(true);
//		validIdentificacion = false;
		txtPasword.setDisabled(true);
		txtConfirmPasword.setDisabled(true);
		schckEstado.setDisabled(true);

		return "";
	}

	public String validationPassword(String password, String confirmPassword) throws Exception
	{
		String ok = "Password coinciden";
		String fallo = "Password no coinciden";
		String msjMuyCortos = "El password debe tener más de cuatro caracteres";
		String msjObligatorio = "EL password es obligatorio para este tipo de usuario";
		String msjFaltoLLenar= "Falto un password";
		String respuesta = "";
		
		if(password == null || password.toString().trim().equals("")) throw new Exception(msjObligatorio);
		
		if(confirmPassword == null || confirmPassword.toString().trim().equals("")) throw new Exception(msjObligatorio);
		
		if(password.toString().trim().length() <= 4) throw new Exception(msjMuyCortos);
		
		if(confirmPassword.toString().trim().length() <= 4) throw new Exception(msjMuyCortos);
		
		if( !password.toString().trim().equals(confirmPassword.toString().trim()) ) throw new Exception(fallo);
			
		respuesta=ok;
		
		return respuesta;
	}

	/** GETTERS & SETTERS */

	// public void setTxtTipoUsuario(InputText txtTipoUsuario) {
	// this.txtTipoUsuario = txtTipoUsuario;
	// }

	public IDelegadoDelNegocio getDelegadoDelNegocio() {
		return delegadoDelNegocio;
	}

	public void setDelegadoDelNegocio(IDelegadoDelNegocio delegadoDelNegocio) {
		this.delegadoDelNegocio = delegadoDelNegocio;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public UploadedFile getLoadFotografia() {
		return loadFotografia;
	}

	public void setLoadFotografia(UploadedFile loadFotografia) {
		this.loadFotografia = loadFotografia;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public Date getTxtFechaNacimiento() {
		return txtFechaNacimiento;
	}

	public Password getTxtPasword() {
		return txtPasword;
	}

	public boolean isValidIdentificacion() {
		return validIdentificacion;
	}

	public void setValidIdentificacion(boolean validIdentificacion) {
		this.validIdentificacion = validIdentificacion;
	}

	public Password getTxtConfirmPasword() {
		return txtConfirmPasword;
	}

	public void setTxtPasword(Password txtPasword) {
		this.txtPasword = txtPasword;
	}

	public void setTxtConfirmPasword(Password txtConfirmPasword) {
		this.txtConfirmPasword = txtConfirmPasword;
	}

	public void setTxtFechaNacimiento(Date txtFechaNacimiento) {
		this.txtFechaNacimiento = txtFechaNacimiento;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public InputText getTxtTipoUsuario() {
		return txtTipoUsuario;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public List<Usuario> getLosUsuario() {

		if (losUsuario == null) {
			try {
				losUsuario = delegadoDelNegocio.consultarTodosUsuario();

			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return losUsuario;
	}

	public void setLosUsuario(List<Usuario> losUsuario) {
		this.losUsuario = losUsuario;
	}

	public SelectOneMenu getSomTiposUsuario() {
		return somTiposUsuario;
	}

	public List<SelectItem> getLosTipoUsuarioItems() {
		try {
			if (losTipoUsuarioItems == null) {
				List<TipoUsuario> losTiposUsuarios = delegadoDelNegocio.consultarTodosTipoUsuario();
				losTipoUsuarioItems = new ArrayList<SelectItem>();
				for (TipoUsuario tiposUsuario : losTiposUsuarios) {
					SelectItem selectItem = new SelectItem(tiposUsuario.getTusuId(), tiposUsuario.getTusuNombre());
					losTipoUsuarioItems.add(selectItem);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return losTipoUsuarioItems;
	}

	public void setSomTiposUsuario(SelectOneMenu somTiposUsuario) {
		this.somTiposUsuario = somTiposUsuario;
	}

	public void setLosTipoUsuarioItems(List<SelectItem> losTipoUsuarioItems) {
		this.losTipoUsuarioItems = losTipoUsuarioItems;
	}

	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public InputText getTxtPrimerNombre() {
		return txtPrimerNombre;
	}

	public InputText getTxtSegundoNombre() {
		return txtSegundoNombre;
	}

	public InputText getTxtPrimerApellido() {
		return txtPrimerApellido;
	}

	public InputText getTxtSegundoApellido() {
		return txtSegundoApellido;
	}

	public InputText getTxtDireccion() {
		return txtDireccion;
	}

	public InputText getTxtTelefono() {
		return txtTelefono;
	}

	public InputText getTxtCiudad() {
		return txtCiudad;
	}

	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public void setTxtPrimerNombre(InputText txtPrimerNombre) {
		this.txtPrimerNombre = txtPrimerNombre;
	}

	public void setTxtSegundoNombre(InputText txtSegundoNombre) {
		this.txtSegundoNombre = txtSegundoNombre;
	}

	public void setTxtPrimerApellido(InputText txtPrimerApellido) {
		this.txtPrimerApellido = txtPrimerApellido;
	}

	public void setTxtSegundoApellido(InputText txtSegundoApellido) {
		this.txtSegundoApellido = txtSegundoApellido;
	}

	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public void setTxtCiudad(InputText txtCiudad) {
		this.txtCiudad = txtCiudad;
	}

	public void setTxtTipoUsuario(InputText txtTipoUsuario) {
		this.txtTipoUsuario = txtTipoUsuario;
	}

	public UploadedFile getLoadDerImagen() {
		return loadDerImagen;
	}

	public UploadedFile getLoadIzImagen() {
		return loadIzImagen;
	}

	public void setLoadDerImagen(UploadedFile loadDerImagen) {
		this.loadDerImagen = loadDerImagen;
	}

	public void setLoadIzImagen(UploadedFile loadIzImagen) {
		this.loadIzImagen = loadIzImagen;
	}

	public CommandButton getBtnRegistrarHuella() {
		return btnRegistrarHuella;
	}

	public void setBtnRegistrarHuella(CommandButton btnRegistrarHuella) {
		this.btnRegistrarHuella = btnRegistrarHuella;
	}

	public boolean isEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public SelectBooleanCheckbox getSchckEstado() {
		return schckEstado;
	}

	public void setSchckEstado(SelectBooleanCheckbox schckEstado) {
		this.schckEstado = schckEstado;
	}

	

	/*
	 * public InputText getTxtCalendar() { return txtCalendar; }
	 * 
	 * public void setTxtCalendar(InputText txtCalendar) { this.txtCalendar =
	 * txtCalendar; }
	 */

}
