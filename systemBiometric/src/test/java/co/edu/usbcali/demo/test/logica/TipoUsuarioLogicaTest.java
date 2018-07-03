package co.edu.usbcali.demo.test.logica;

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

import co.edu.usbcali.dao.ConsecutivoFactoryDAO;
import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.ITipoUsuarioDAO;
import co.edu.usbcali.logica.ITipoUsuarioLogica;
import co.edu.usbcali.modelo.TipoUsuario;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TipoUsuarioLogicaTest {
	
	public static final Logger log=LoggerFactory.getLogger(TipoUsuarioLogicaTest.class);
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	Long id_tipoUsuario = 10L;
	
	@Test	
	public void aTestCrear() {
		TipoUsuario tipoUsuario = new TipoUsuario();
		
		tipoUsuario.setTusuId(id_tipoUsuario);
		tipoUsuario.setTusuNombre("USUARIO NUEVO");
		tipoUsuario.setUsuCreador("creador");
		tipoUsuario.setFechaCreador(new Date());
		
		try {
			tipoUsuarioLogica.crearTipoUsuario(tipoUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	

	@Test
	public void bTestConsultarPorId() {
		
		TipoUsuario tipoUsuario = new TipoUsuario();
		try {
			tipoUsuario = tipoUsuarioLogica.consultarPorIdTipoUsuario(id_tipoUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		assertNotNull("No existe el usuario",tipoUsuario);
		
		log.info("id:"+tipoUsuario.getTusuId());
		log.info("Nombre:"+tipoUsuario.getTusuNombre());
		
	}
	
	@Test
	public void cTestModificar() {
			
		TipoUsuario tipoUsuario = new TipoUsuario();
		try {
			tipoUsuario = tipoUsuarioLogica.consultarPorIdTipoUsuario(id_tipoUsuario);

			assertNotNull("No existe el usuario", tipoUsuario);

			tipoUsuario.setTusuNombre("usuario modificado");
			tipoUsuario.setUsuModificador("usuario modificado");
			tipoUsuario.setFechaModificador(new Date());

			tipoUsuarioLogica.modificarTipoUsuario(tipoUsuario);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void dTestConsultarTodos() {

		List<TipoUsuario> losTiposUsuarios;
		try {
			losTiposUsuarios = tipoUsuarioLogica.consultarTodosTipoUsuario();

			for (TipoUsuario tipoUsuario : losTiposUsuarios) {
				log.info("id:" + tipoUsuario.getTusuId());
				log.info("Nombre:" + tipoUsuario.getTusuNombre());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void eTestBorrar() {
		TipoUsuario tipoUsuario;
		try {
			tipoUsuario = tipoUsuarioLogica.consultarPorIdTipoUsuario(id_tipoUsuario);

			assertNotNull("No existe el usuario", tipoUsuario);

			tipoUsuarioLogica.borrarTipoUsuario(tipoUsuario);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
