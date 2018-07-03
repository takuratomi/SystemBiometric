package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ITipoUsuarioDAO;
import co.edu.usbcali.modelo.TipoUsuario;


@Service("tipoUsuarioLogica")
@Scope("singleton")
public class TipoUsuarioLogica implements ITipoUsuarioLogica{

	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	private TipoUsuario tipoUsuario_tmp;

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		
		if(tipoUsuario == null) throw new Exception("El tipo de usuario no puede ser nulo");
		
		if(tipoUsuario.getTusuId() == 0 ) throw new Exception("El id es obligatorio");
		
		if(tipoUsuario.getTusuId() <= 0 ) throw new Exception("El id  no puede ser negativo");
				
		if(tipoUsuario.getTusuNombre() == null || tipoUsuario.getTusuNombre().equals("")) throw new Exception("El nombre del tipo de usuario es obligatorio");
		
		tipoUsuario_tmp = tipoUsuarioDAO.consultarPorIdTipoUsuario(tipoUsuario.getTusuId());
		if(tipoUsuario_tmp != null) throw new Exception("El tipo de usuario ya existe");
			
		tipoUsuario_tmp = null;
		
		if(tipoUsuario.getUsuCreador() == null || tipoUsuario.getUsuCreador().equals("")) throw new Exception("Error: Usuario de creación fallido");
		
		if(tipoUsuario.getFechaCreador() == null || tipoUsuario.getFechaCreador().equals("")) throw new Exception("Error: Fecha de creación fallida");
				
		tipoUsuarioDAO.crearTipoUsuario(tipoUsuario);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) throws Exception{
		
		if (tipoUsuario == null) throw new Exception("El tipo de usuario no puede ser nulo");

		if (tipoUsuario.getTusuId() == 0) throw new Exception("El id es obligatorio");
		
		tipoUsuario_tmp = tipoUsuarioDAO.consultarPorIdTipoUsuario(tipoUsuario.getTusuId());
		if (tipoUsuario_tmp == null) throw new Exception("El tipo de usuario no existe");

		if (tipoUsuario.getTusuId() <= 0) throw new Exception("El id  no puede ser negativo");

		if (tipoUsuario.getTusuNombre() == null || tipoUsuario.getTusuNombre().equals("")) throw new Exception("El nombre del tipo de usuario es obligatorio");

		if (tipoUsuario.getUsuCreador() == null || tipoUsuario.getUsuCreador().equals("")) throw new Exception("Error: Usuario de creación fallido");

		if (tipoUsuario.getFechaCreador() == null || tipoUsuario.getFechaCreador().equals("")) throw new Exception("Error: Fecha de creación fallida");
		
		if (tipoUsuario.getUsuModificador() == null || tipoUsuario.getUsuModificador().equals("")) throw new Exception("Error: Usuario de modificacion fallido");

		if (tipoUsuario.getFechaModificador() == null || tipoUsuario.getFechaModificador().equals("")) throw new Exception("Error: Fecha de modificacion fallida");

		tipoUsuario_tmp.setTusuId(tipoUsuario.getTusuId());
		tipoUsuario_tmp.setTusuNombre(tipoUsuario.getTusuNombre());
		tipoUsuario_tmp.setUsuCreador(tipoUsuario.getUsuCreador());
		tipoUsuario_tmp.setFechaCreador(tipoUsuario.getFechaCreador());
		tipoUsuario_tmp.setUsuModificador(tipoUsuario.getUsuModificador());
		tipoUsuario_tmp.setFechaModificador(tipoUsuario.getFechaModificador());
		
		tipoUsuarioDAO.modificarTipoUsuario(tipoUsuario_tmp);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarTipoUsuario(TipoUsuario tipoUsuario) throws Exception{
		
		if (tipoUsuario == null) throw new Exception("El tipo de usuario no puede ser nulo");

		if (tipoUsuario.getTusuId() == 0) throw new Exception("El id es obligatorio");
		
		tipoUsuario_tmp = tipoUsuarioDAO.consultarPorIdTipoUsuario(tipoUsuario.getTusuId());
		if (tipoUsuario_tmp == null) throw new Exception("El tipo de usuario no existe");
		
		tipoUsuarioDAO.borrarTipoUsuario(tipoUsuario_tmp);
	
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public TipoUsuario consultarPorIdTipoUsuario(Long id) throws Exception{
		
		if(id == 0 || id ==null) throw new Exception("El id es obligatorio");
		
		return tipoUsuarioDAO.consultarPorIdTipoUsuario(id); 
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<TipoUsuario> consultarTodosTipoUsuario() throws Exception {
		
		List<TipoUsuario> losTiposUsuario = tipoUsuarioDAO.consultarTodosTipoUsuario();
		
		if( losTiposUsuario == null || losTiposUsuario.size()==0) throw new Exception("No hay tipo de usuarios");
		return losTiposUsuario;
	}	

}
