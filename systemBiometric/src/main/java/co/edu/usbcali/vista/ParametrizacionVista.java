package co.edu.usbcali.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import co.edu.usbcali.modelo.Parametro;
import javassist.compiler.ast.CondExpr;

@ManagedBean
@ViewScoped
public class ParametrizacionVista {

	public final static Logger log = LoggerFactory.getLogger(ParametrizacionVista.class);

	@ManagedProperty(value = "#{delegadoDelNegocio}")
	private IDelegadoDelNegocio delegadoDelNegocio;

	private InputText txtHInicio;
	private InputText txtHAlmInicio;
	private InputText txtHAlmFin;
	private InputText txtHFin;
	private InputText txtToleHras;
	
	private CommandButton btnGuardar;
	
	
	private static boolean load_first_time = true;
	private static int contador = 1;
	
	private List<Parametro> losParametros;
	private HashMap<String, Parametro> indexParametros;

	/***
	 * ACTIONS
	 */	
	public String btnCrearAction() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("  btnGuardar  ACTIVADO   ");
		
		try
		{
			String usuModificador = "ADMIN2";
			
			getLosParametros();
			indexParametros.get("1").setValor(txtHAlmInicio.getValue().toString().trim());
			indexParametros.get("1").setUsuModificador(usuModificador);
			indexParametros.get("1").setFechaModificador(new Date());

			indexParametros.get("2").setValor(txtHAlmFin.getValue().toString().trim());
			indexParametros.get("2").setUsuModificador(usuModificador);
			indexParametros.get("2").setFechaModificador(new Date());

			indexParametros.get("3").setValor(txtHInicio.getValue().toString().trim());
			indexParametros.get("3").setUsuModificador(usuModificador);
			indexParametros.get("3").setFechaModificador(new Date());

			indexParametros.get("4").setValor(txtHFin.getValue().toString().trim());
			indexParametros.get("4").setUsuModificador(usuModificador);
			indexParametros.get("4").setFechaModificador(new Date());

			indexParametros.get("5").setValor(txtToleHras.getValue().toString().trim());
			indexParametros.get("5").setUsuModificador(usuModificador);
			indexParametros.get("5").setFechaModificador(new Date());
			
			losParametros = hastMapToList(indexParametros);
			
			delegadoDelNegocio.modificarListaParametro(losParametros);
			contador=1;

			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo la modificación", ""));
			log.info("SE GUARDO LA MODIFICACION");
			
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

	

	public List<Parametro> hastMapToList(HashMap<String, Parametro> hasMapParametros)
	{
		List<Parametro> lstParametros = new ArrayList<Parametro>();
		for (Parametro parametro : hasMapParametros.values()) {
			lstParametros.add(parametro);
		}
		return lstParametros ;
	}
		

	/** GETTERS & SETTERS */

	public IDelegadoDelNegocio getDelegadoDelNegocio() {
		return delegadoDelNegocio;
	}

	public void setDelegadoDelNegocio(IDelegadoDelNegocio delegadoDelNegocio) {
		this.delegadoDelNegocio = delegadoDelNegocio;
	}



	public InputText getTxtHInicio() {
		return txtHInicio;
	}



	public void setTxtHInicio(InputText txtHInicio) {
		this.txtHInicio = txtHInicio;
		if(contador<=5)
		{
			getLosParametros();		
			txtHInicio.setValue(indexParametros.get("3").getValor().trim());
			contador++;
		}			
		else {
			contador++;
		}
	}



	public InputText getTxtHAlmInicio() {
//		if( losParametros == null )
//		{
//			getLosParametros();
//			txtHAlmInicio = new InputText();
//			txtHAlmInicio.setValue(indexParametros.get("1").getValor().trim());
//		}
//		else
//		{
//			txtHAlmInicio = new InputText();
//			txtHAlmInicio.setValue(indexParametros.get("1").getValor().trim());
//		}
//		if(txtHAlmInicio == null)
//		{
//			if(load_first_time)
//			{
//				getLosParametros();
//				load_first_time = false;
//			}
//			
//			txtHAlmInicio = new InputText();
//			txtHAlmInicio.setValue(indexParametros.get("1").getValor().trim());
//			
//		}
		
		return txtHAlmInicio;
	}



	public void setTxtHAlmInicio(InputText txtHAlmInicio) {
		this.txtHAlmInicio = txtHAlmInicio;
		if(contador<=5)
		{
//			getLosParametros();		
			txtHAlmInicio.setValue(indexParametros.get("1").getValor().trim());
			contador++;
		}			
		else {
			contador++;
		}
	}



	public InputText getTxtHAlmFin() {
//		if(txtHAlmFin == null)
//		{
//			if(load_first_time)
//			{
//				getLosParametros();
//				load_first_time = false;
//			}
//			
//			txtHAlmFin = new InputText();
//			txtHAlmFin.setValue(indexParametros.get("2").getValor().trim());
//			
//		}
		return txtHAlmFin;
	}



	public void setTxtHAlmFin(InputText txtHAlmFin) {
		this.txtHAlmFin = txtHAlmFin;
		if(contador<=5)
		{
//			getLosParametros();		
			txtHAlmFin.setValue(indexParametros.get("2").getValor().trim());
			contador++;
		}			
		else {
			contador++;
		}
	}



	public InputText getTxtHFin() {
//		if(txtHFin == null)
//		{
//			if(load_first_time)
//			{
//				getLosParametros();
//				load_first_time = false;
//			}
//			
//			txtHFin = new InputText();
//			txtHFin.setValue(indexParametros.get("4").getValor().trim());
//			
//		}

		return txtHFin;
	}



	public void setTxtHFin(InputText txtHFin) {
		this.txtHFin = txtHFin;
		if(contador<=5)
		{
//			getLosParametros();		
			txtHFin.setValue(indexParametros.get("4").getValor().trim());
			contador++;
		}			
		else {
			contador++;
		}
		
	}



	public InputText getTxtToleHras() {
//		if(txtToleHras == null)
//		{
//			if(load_first_time)
//			{
//				getLosParametros();
//				load_first_time = false;
//			}
//			
//			txtToleHras = new InputText();
//			txtToleHras.setValue(indexParametros.get("5").getValor().trim());
//			
//		}
		
		return txtToleHras;
	}



	public void setTxtToleHras(InputText txtToleHras) {
		this.txtToleHras = txtToleHras;
		if(contador<=5)
		{
//			getLosParametros();		
			txtToleHras.setValue(indexParametros.get("5").getValor().trim());
			contador++;
		}			
		else {
			contador++;
		}
	}



	public List<Parametro> getLosParametros() {
		
		if(losParametros == null)
		{
			try {
				
				losParametros = delegadoDelNegocio.consultarTodosParametro();
				indexParametros = new HashMap<>();
				for (Parametro parametro : losParametros) {
					
					indexParametros.put(""+parametro.getParaId(), parametro);
					
				}
				
			} catch (Exception e) {
			}
		}
		
		return losParametros;
	}



	public void setLosParametros(List<Parametro> losParametros) {
		this.losParametros = losParametros;
	}



	public CommandButton getBtnGuardar() {
		return btnGuardar;
	}



	public void setBtnGuardar(CommandButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

}
