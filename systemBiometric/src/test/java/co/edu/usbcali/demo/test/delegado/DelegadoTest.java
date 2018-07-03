package co.edu.usbcali.demo.test.delegado;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.Fotografia;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;
import co.edu.usbcali.modelo.TipoUsuario;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.UsuarioHuella;
import co.edu.usbcali.vista.IDelegadoDelNegocio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DelegadoTest {
	
	private static final Logger log = LoggerFactory.getLogger(DelegadoTest.class);
//	private CapturaBiometric cb;
	
	@Autowired
	private IDelegadoDelNegocio delegadoDelNegocio;
	
	@Test
	public void testA() {
		log.info("-------------------------------------");
		log.info("-- INIT BIOMETRIC 		     -----");
		log.info("-------------------------------------");
		log.info("-----######### DELEGADO  --------------");
		
		log.info(""+delegadoDelNegocio.getConsecutivoBita());
		log.info(""+delegadoDelNegocio.getConsecutivoBita());
		log.info(""+delegadoDelNegocio.getConsecutivoBita());
		
	}
	
	
	
//	@Test
//	public void testB()
//	{
//		Window w = new Window();
//		w.init();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	

}
