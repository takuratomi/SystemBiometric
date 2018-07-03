package co.edu.usbcali.demo.test.logica;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ConsecutivoFactoryDAO;
import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.ITipoUsuarioDAO;
import co.edu.usbcali.logica.ITipoUsuarioLogica;
import co.edu.usbcali.logica.IUsuarioLogica;
import co.edu.usbcali.modelo.TipoUsuario;
import co.edu.usbcali.modelo.Usuario;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioLogicaTest {
	
	public static final Logger log=LoggerFactory.getLogger(UsuarioLogicaTest.class);
	
	@Autowired
	private IUsuarioLogica usuarioLogica; 
	
	Long id_tipoUsuario = 10L;
	
	

	@Test
	public void bTestConsultarUsuarioPorHuella() {
		
		try {
			List<Usuario> lstUsuario = new ArrayList<>();
			List<Usuario> lstUsuario2 = new ArrayList<>();
			lstUsuario =usuarioLogica.consultarUsuarioPorIdHuellaDerecha(8L);
			lstUsuario2 =usuarioLogica.consultarUsuarioPorIdHuellaIzquierda(4L);
			Usuario usuario = usuarioLogica.consultarUsuarioPorIdHuellaIzquierda(4L).get(0);
			assertNotNull("La lista es nula",lstUsuario);
//			Usuario usuario = lstUsuario.get(0);
			log.info("cedula "+usuario.getUsuCedula());
			log.info("nombre " +usuario.getUsuPrimernombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
