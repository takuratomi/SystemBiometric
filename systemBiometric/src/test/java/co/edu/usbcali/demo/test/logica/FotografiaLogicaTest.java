package co.edu.usbcali.demo.test.logica;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

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
import co.edu.usbcali.logica.IFotografiaLogica;
import co.edu.usbcali.logica.ITipoUsuarioLogica;
import co.edu.usbcali.modelo.Fotografia;
import co.edu.usbcali.modelo.TipoUsuario;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FotografiaLogicaTest {
	
	public static final Logger log=LoggerFactory.getLogger(FotografiaLogicaTest.class);
	
	@Autowired
	private IFotografiaLogica fotografiaLogica; 
	
	Long fotoId = 10L;
	
	/**
	 * GUARDAR FOTOGRAFI
	 */
	@Test	
	public void aTestCrear() {
		String path = "C:\\workspaces\\workspace_sts\\systemBiometric\\imagenes\\persona_default.png";
		
		File file = new File(path);
		
		if(file.exists())
		{
			log.info("Existe la imagen");
			byte[] bFile = new byte[(int) file.length()];
			FileInputStream fileInputStream;
			try {
				 fileInputStream = new FileInputStream(file);
				 fileInputStream.read(bFile);
				 fileInputStream.close();
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Fotografia fotografia = new Fotografia();
			
			fotografia.setFotoId(fotoId);
			fotografia.setUsuCreador("USUARIO");
			fotografia.setFechaCreador(new Date());
			fotografia.setImagenbin(bFile);
			try
			{
				fotografiaLogica.crearFotografia(fotografia);
				log.info("Se guardo la imagen");
			}catch(Exception e)
			{
				log.info("FALLO E guardo la imagen");
				e.printStackTrace();
			}
			
		}
		else
		{
			log.info("NO Existe la imagen");
			
		}	
		
	}	

	/**
	 *  DESCARGAR FOTOGRAFIA
	 */
	@Test
	public void bTestConsultarPorId() {
		
		Fotografia fotografia;
		
		try {
			
			fotografia = fotografiaLogica.consultarPorIdFotografia(2L);
			
			File file = new File("C:\\Users\\Desarrollo4\\Downloads\\defaultDownload2.png");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(fotografia.getImagenbin());
			
			fileOutputStream.close();
			file = null;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		
	}
	
	/*
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
	
	*/
	
	
	

}
