package co.edu.usbcali.vista;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.modelo.TipoUsuario;

@ManagedBean
@ViewScoped
public class TipoUsuarioVista {

	public final static Logger log = LoggerFactory.getLogger(TipoUsuarioVista.class);

	@ManagedProperty(value ="#{delegadoDelNegocio}")
	private IDelegadoDelNegocio delegadoDelNegocio;
	
	private InputText txtId;
	private InputText txtTipoUsuario;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;

	private List<TipoUsuario> losTiposUsuario;

	
	
	/***
	 *    ACTIONS
	 */
	public void txtIdListener(){
		log.info("txtIdListener");
				
		try {
			Long id=Long.parseLong(txtId.getValue().toString().trim());
			
			TipoUsuario tipoUsuario = delegadoDelNegocio.consultarPorIdTipoUsuario(id);
						
			if(tipoUsuario==null){
				btnCrear.setDisabled(false);
				btnModificar.setDisabled(true);
				btnBorrar.setDisabled(true);				
				txtTipoUsuario.resetValue();
				
								
			}else{
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);
				btnBorrar.setDisabled(false);
				btnLimpiar.setDisabled(false);
				
				txtId.setValue(tipoUsuario.getTusuId());
				txtTipoUsuario.setValue(tipoUsuario.getTusuNombre());
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public String btnCrearAction()
	{
		log.info("btnCrear");
		
		
		try {
			Long id=Long.parseLong(txtId.getValue().toString().trim());
			
			String nombreTipoUsuario = txtTipoUsuario.getValue().toString().trim().toUpperCase();
			
			String usuCreador = "ADMIN";
			
			TipoUsuario tipoUsuario = new TipoUsuario();

			tipoUsuario.setTusuId(id);
			tipoUsuario.setTusuNombre(nombreTipoUsuario);
			tipoUsuario.setFechaCreador(new Date());
			tipoUsuario.setUsuCreador(usuCreador);

			delegadoDelNegocio.crearTipoUsuario(tipoUsuario);


			txtId.resetValue();
			txtTipoUsuario.resetValue();
			
			losTiposUsuario = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se creo el tipo de usuario", ""));
			btnCrear.setDisabled(true);
			
		}catch(NumberFormatException ne)
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se ingreso caracteres en el id del tipo de usuario", ""));
			log.error(ne.getMessage());
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			log.error(e.getMessage());
		}	
		
		return "";
	}
	
	public String btnModificarAction()
	{
		log.info("btnModificar");
		
		
		try {
			Long id=Long.parseLong(txtId.getValue().toString().trim());
			String nombreTipoUsuario = txtTipoUsuario.getValue().toString().trim().toUpperCase();
			String usuModificar = "ADMIN2";
			
			TipoUsuario tipoUsuario = delegadoDelNegocio.consultarPorIdTipoUsuario(id);
			tipoUsuario.setTusuId(id);
			tipoUsuario.setTusuNombre(nombreTipoUsuario);
			tipoUsuario.setFechaModificador(new Date());
			tipoUsuario.setUsuModificador(usuModificar);
			
			delegadoDelNegocio.modificarTipoUsuario(tipoUsuario);

			txtId.resetValue();
			txtTipoUsuario.resetValue();
			
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);
			btnBorrar.setDisabled(true);
			
			losTiposUsuario = null;
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico el tipo de usuario", ""));		

		}catch(NumberFormatException ne)
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se ingreso caracteres en el id del tipo de usuario", ""));
			log.error(ne.getMessage());
		}		
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),""));
			log.error(e.getMessage());
		}
			
		return "";
	}
	
	public String btnBorrarAction()
	{
		log.info("btnBorrar");
		
		
		
		try {
			Long id=Long.parseLong(txtId.getValue().toString().trim());
			
			TipoUsuario tipoUsuario = delegadoDelNegocio.consultarPorIdTipoUsuario(id);
			
			delegadoDelNegocio.borrarTipoUsuario(tipoUsuario);			

			txtId.resetValue();
			txtTipoUsuario.resetValue();
			
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(true);
			btnBorrar.setDisabled(true);
			
			losTiposUsuario = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se borro el tipo de usuario", ""));		

		}catch(NumberFormatException ne)
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se ingreso caracteres en el id del tipo de usuario", ""));
			log.error(ne.getMessage());
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), "" ));
			log.error(e.getMessage());
		}
		
		return "";
	}
		
	public String limpiarAction() {

		log.info("btnLimpiar");
		
		txtId.resetValue();
		txtTipoUsuario.resetValue();
		
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		btnBorrar.setDisabled(true);

		return "";
	}
	
	
	/***
	 *   GETTERS & SETTERS
	 * @param txtId
	 */
	public void setTxtId(InputText txtId) {
		this.txtId = txtId;
	}

	public void setTxtTipoUsuario(InputText txtTipoUsuario) {
		this.txtTipoUsuario = txtTipoUsuario;
	}

	public IDelegadoDelNegocio getDelegadoDelNegocio() {
		return delegadoDelNegocio;
	}

	public void setDelegadoDelNegocio(IDelegadoDelNegocio delegadoDelNegocio) {
		this.delegadoDelNegocio = delegadoDelNegocio;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
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

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public InputText getTxtId() {
		return txtId;
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

	public List<TipoUsuario> getLosTiposUsuario() {

		if (losTiposUsuario == null) {
			try {
				losTiposUsuario = delegadoDelNegocio.consultarTodosTipoUsuario();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			}
		}

		return losTiposUsuario;
	}

	public void setLosTiposUsuario(List<TipoUsuario> losTiposUsuario) {
		this.losTiposUsuario = losTiposUsuario;
	}

}
