package co.edu.usbcali.vista;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.sun.faces.taglib.html_basic.OutputTextTag;

import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
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
public class BitacoraVista {

	public final static Logger log = LoggerFactory.getLogger(BitacoraVista.class);

	@ManagedProperty(value = "#{delegadoDelNegocio}")
	private IDelegadoDelNegocio delegadoDelNegocio;

	private String totalH;
	private InputText txt;
	private CommandButton btnCalcularHoras;
	
	public String getTotalH() {
		return totalH;
	}

	public void setTotalH(String totalH) {
		this.totalH = totalH;
	}

	private OutputLabel totalHoras;
	private OutputTextTag t ;
	public OutputTextTag getT() {
		return t;
	}

	public void setT(OutputTextTag t) {
		this.t = t;
	}

	private Usuario selectedUsuario;
	private List<Usuario> losUsuario;
	private List<BitacoraHorasTrabajadas> lasHorasTrabajadas;
	private List<BitacoraHorasTrabajadas> lasHorasTrabajadasDias;
	
	
	/***
	 * ACTIONS
	 */
	public void txtIdListener() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("txtIdListener se activo");

	}
	
	public void paswordListener() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("lsitener active password se activo");
	
	}
	
	
	public String btnCrearAction() {
		log.info(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("  btnCrear  ACTIVADO   ");

		return "";
	}

	// TODO
	public String btnModificarAction()
	 {
		
		log.info("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("btnModificar ACTIVADO");
	
		 return "";
	 }

	public String btnBorrarAction() {
		log.info("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("btnBorrar ACTIVADO ");
		
		return "";
	}
	
	public String btnCalcularHorasTrabajadasAction() {
		log.info("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   ");
		log.info("btnCalcular ACTIVADO ");
		
		calcularTotalHorasTrabajadas();
		
		return "";
	}

	/** GETTERS & SETTERS */


	public IDelegadoDelNegocio getDelegadoDelNegocio() {
		return delegadoDelNegocio;
	}

	public void setDelegadoDelNegocio(IDelegadoDelNegocio delegadoDelNegocio) {
		this.delegadoDelNegocio = delegadoDelNegocio;
	}

	public List<Usuario> getLosUsuario() {
		if(losUsuario == null)
		{
			try {
				losUsuario = delegadoDelNegocio.consultarTodosUsuario();
				
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}		
		
		return losUsuario;
	}

	public void setLosUsuario(List<Usuario> losUsuario) {
		this.losUsuario = losUsuario;
	}

	public Usuario getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public List<BitacoraHorasTrabajadas> getLasHorasTrabajadas() {
		
		if(lasHorasTrabajadas == null && selectedUsuario !=null)
		{
			try {
				lasHorasTrabajadas = new ArrayList<>();
				lasHorasTrabajadas = delegadoDelNegocio.consultarListaPorIdBitacora(selectedUsuario.getBitacora().getBitaId());
				
				calcularTotalHorasTrabajadas();
				getTotalH();
				calcularHorasTrabajadasDia();
						
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return lasHorasTrabajadas;
	}

	public void setLasHorasTrabajadas(List<BitacoraHorasTrabajadas> lasHorasTrabajadas) {
		this.lasHorasTrabajadas = lasHorasTrabajadas;
	}

	public List<BitacoraHorasTrabajadas> getLasHorasTrabajadasDias() {	
		
		return lasHorasTrabajadasDias;
	}

	public void setLasHorasTrabajadasDias(List<BitacoraHorasTrabajadas> lasHorasTrabajadasDias) {
		this.lasHorasTrabajadasDias = lasHorasTrabajadasDias;
	}
	
	
	public void calcularHorasTrabajadasDia()
	{
		DecimalFormat df = new DecimalFormat("#.##");

		lasHorasTrabajadasDias = new ArrayList<>();
		if (lasHorasTrabajadas != null) {
			for (int i = 0; i < lasHorasTrabajadas.size(); i++) {

				BitacoraHorasTrabajadas tmp = lasHorasTrabajadas.get(i);
				for (int h = 0; h < lasHorasTrabajadas.size(); h++) {
					if (tmp.getFechaingreso().equals(lasHorasTrabajadas.get(h).getFechaingreso())) {

						try {
							double numero1 = (double) df.parse(tmp.getHorasTrabajadas());
							double numero2 = (double) df.parse(lasHorasTrabajadas.get(h).getHorasTrabajadas());
							double total = numero1 + numero2;
							tmp.setHorasTrabajadas(df.format(total));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						i = h;
						break;
					}
				}
				lasHorasTrabajadasDias.add(tmp);
			}

		}
	}
	
//	
	public String calcularTotalHorasTrabajadas() 
	{
		DecimalFormat df = new DecimalFormat("#.##");
		double total = 0;
		
		if (lasHorasTrabajadas != null) {
			for (int i = 0; i < lasHorasTrabajadas.size(); i++) {

				try {
					BitacoraHorasTrabajadas tmp = lasHorasTrabajadas.get(i);				
					double numero1 = (double) df.parse(tmp.getHorasTrabajadas());				
					total = total+ numero1;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		totalH = "Total horas trabajadas: ";
		totalH = totalH + df.format(total);
		txt.setValue(totalH);
		return "";
//		totalHoras.setValue(totalH);
	}
	

	public OutputLabel getTotalHoras() {
		if(lasHorasTrabajadas != null)
		{
			calcularTotalHorasTrabajadas();
		}
		
		
		return totalHoras;
	}

	public void setTotalHoras(OutputLabel totalHoras) {
		this.totalHoras = totalHoras;
	}

	public CommandButton getBtnCalcularHoras() {
		return btnCalcularHoras;
	}

	public void setBtnCalcularHoras(CommandButton btnCalcularHoras) {
		this.btnCalcularHoras = btnCalcularHoras;
	}

	public InputText getTxt() {
		return txt;
	}

	public void setTxt(InputText txt) {
		this.txt = txt;
	}
			
	
	
	

}
