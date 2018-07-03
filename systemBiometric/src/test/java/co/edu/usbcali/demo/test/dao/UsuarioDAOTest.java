package co.edu.usbcali.demo.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.Usuario;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioDAOTest {
	
	public static final Logger log=LoggerFactory.getLogger(UsuarioDAOTest.class);
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	public static final Long id=100L;
	
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void aTestCrear() {
		Usuario usuario= new Usuario();
		
		usuario.setUsuCedula(id);
		usuario.setFechaCreador(new Date());
		usuario.setUsuPrimerapellido("usu");
		usuario.setUsuPrimernombre("usu");
		usuario.setUsuSegundoapellido("usu");
		usuario.setUsuSegundonombre("usu");
		usuario.setUsuFechnacimiento(new Date());
		usuario.setUsuCreador("creador");
		
		usuarioDAO.crearUsuario(usuario);
		
	}
	

	@Test
	@Transactional(readOnly=true)
	@Rollback(false)
	public void bTestConsultarPorId() {
		
		Usuario usuario = usuarioDAO.consultarPorIdUsuario(id);
			
		
		assertNotNull("No existe el usuario",usuario);
		
		log.info("id:"+usuario.getUsuCedula());
		log.info("Nombre:"+usuario.getUsuPrimernombre()+" "+ usuario.getUsuPrimerapellido());
		log.info("fecha nacimiento:"+usuario.getUsuFechnacimiento());
		//log.info("bita_id:"+usuario.getBitaId());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void cTestModificar() {
		
	
		Usuario usuario = usuarioDAO.consultarPorIdUsuario(id);
		assertNotNull("No existe el usuario",usuario);
		
		usuario.setUsuPrimerapellido("usuario modificado");
		usuarioDAO.modificarUsuario(usuario);

	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dTestConsultarTodos() {
		
		List<Usuario> losUsuaruis = usuarioDAO.consultarTodosUsuario();
		try {
		
			for (Usuario usuario : losUsuaruis) {
				log.info("id:"+usuario.getUsuCedula());
				log.info("Nombre:"+usuario.getUsuPrimernombre()+" "+ usuario.getUsuPrimerapellido());
				log.info("fecha nacimiento:"+usuario.getUsuFechnacimiento());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void eTestBorrar() {
		Usuario usuario = usuarioDAO.consultarPorIdUsuario(id);
		assertNotNull("No existe el usuario",usuario);
		
		usuarioDAO.borrarUsuario(usuario);
	}
	
	@Test
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void fTestConsultarUsuarioPorHuella() {
		
		List<Usuario> losUsuaruis = usuarioDAO.consultarUsuarioPorIdHuellaIzquierda(8L);
		try {
			log.info("id:"+losUsuaruis.get(0).getUsuCedula());
			for (Usuario usuario : losUsuaruis) {
				log.info("id:"+usuario.getUsuCedula());
				log.info("Nombre:"+usuario.getUsuPrimernombre()+" "+ usuario.getUsuPrimerapellido());
				log.info("fecha nacimiento:"+usuario.getUsuFechnacimiento());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	
	

}
