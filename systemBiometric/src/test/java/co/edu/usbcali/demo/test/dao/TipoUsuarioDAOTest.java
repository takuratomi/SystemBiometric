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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ITipoUsuarioDAO;
import co.edu.usbcali.modelo.TipoUsuario;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TipoUsuarioDAOTest {
	
	public static final Logger log=LoggerFactory.getLogger(TipoUsuarioDAOTest.class);
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	public static final Long id=100L;
	
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void aTestCrear() {
		TipoUsuario tipoUsuario = new TipoUsuario();
		
		tipoUsuario.setTusuId(id);
		tipoUsuario.setTusuNombre("USUARIO NUEVO");
		tipoUsuario.setUsuCreador("creador");
		tipoUsuario.setFechaCreador(new Date());
		
		tipoUsuarioDAO.crearTipoUsuario(tipoUsuario);
		
		
	}
	

	@Test
	@Transactional(readOnly=true)
	@Rollback(false)
	public void bTestConsultarPorId() {
		
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorIdTipoUsuario(id);
			
		
		assertNotNull("No existe el usuario",tipoUsuario);
		
		log.info("id:"+tipoUsuario.getTusuId());
		log.info("Nombre:"+tipoUsuario.getTusuNombre());
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void cTestModificar() {
		
	
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorIdTipoUsuario(id);
		assertNotNull("No existe el usuario",tipoUsuario);
		
		tipoUsuario.setTusuNombre("usuario modificado");
		tipoUsuarioDAO.modificarTipoUsuario(tipoUsuario);

	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dTestConsultarTodos() {
		
	
		List<TipoUsuario> losTiposUsuarios = tipoUsuarioDAO.consultarTodosTipoUsuario();
		
		for (TipoUsuario tipoUsuario : losTiposUsuarios) {
			log.info("id:"+tipoUsuario.getTusuId());
			log.info("Nombre:"+tipoUsuario.getTusuNombre());
		}

	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void eTestBorrar() {
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorIdTipoUsuario(id);
		assertNotNull("No existe el usuario",tipoUsuario);
		
		tipoUsuarioDAO.borrarTipoUsuario(tipoUsuario);
	}
	
	
	
	
	

}
