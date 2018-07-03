package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.IUsuarioHuellaDAO;
import co.edu.usbcali.dao.UsuarioDAO;
import co.edu.usbcali.modelo.TipoUsuario;
import co.edu.usbcali.modelo.UsuarioHuella;

@Repository("UsuarioHuellaHuellaDAO")
@Scope("singleton")
public class UsuarioHuellaLogica implements IUsuarioHuellaLogica {
	
	@Autowired
	private IUsuarioHuellaDAO usuarioHuellaDAO;
	@Autowired
	private IHuellaDerechaLogica huellaDerechaLogica;
	@Autowired
	private IHuellaIzquierdaLogica huellaIzquierdaLogica;
	

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearUsuarioHuella(UsuarioHuella entity) throws Exception {
		
		if(entity == null) throw new Exception("usuario huella no puede ser nula");
		
		if(entity.getUshuId() <= 0) throw new Exception("El id de usuarioHuella es obligatorio");
		
		if(entity.getUsuario() == null) throw new Exception("El usuario no puede ser nulo");
		
		if(entity.getUsuario().getUsuCedula() <=0 ) throw new Exception("La cedula del usuario es obligatoria");
		
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("") ) throw new Exception("El usuario creador es obligatorio");
		
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("") ) throw new Exception("La fecha creador es obligatorio");
		
		UsuarioHuella usuarioHuella = usuarioHuellaDAO.consultarPorIdUsuarioHuella(entity.getUshuId());
		
		if(usuarioHuella != null) throw new Exception("El usuarioHuella ya existe");
		
		usuarioHuellaDAO.crearUsuarioHuella(entity);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarUsuarioHuella(UsuarioHuella entity) throws Exception {
	
		if(entity == null) throw new Exception("usuario huella no puede ser nula");
		
		if(entity.getUshuId() <= 0) throw new Exception("El id de usuarioHuella es obligatorio");
		
		if(entity.getUsuario() == null) throw new Exception("El usuario no puede ser nulo");
		
		if(entity.getUsuario().getUsuCedula() <=0 ) throw new Exception("La cedula del usuario es obligatoria");
		
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("") ) throw new Exception("El usuario creador es obligatorio");
		
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("") ) throw new Exception("La fecha creador es obligatorio");
		
		if(entity.getUsuModificador() == null || entity.getUsuModificador().toString().trim().equals("") ) throw new Exception("El usuario modificador es obligatorio");
		
		if(entity.getFechaModificador() == null || entity.getFechaModificador().toString().trim().equals("") ) throw new Exception("La fecha modificador es obligatorio");
		
		UsuarioHuella usuarioHuella = usuarioHuellaDAO.consultarPorIdUsuarioHuella(entity.getUshuId());
		
		if(usuarioHuella == null) throw new Exception("El usuarioHuella con id "+entity.getUshuId()+" no existe");		
		
		usuarioHuella.setFechaModificador(entity.getFechaModificador());
		usuarioHuella.setUsuModificador(entity.getUsuModificador());		
		usuarioHuellaDAO.modificarUsuarioHuella(usuarioHuella);
	
	}
		
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarUsuarioHuella(UsuarioHuella entity) throws Exception {
		
		if(entity == null) throw new Exception("usuario huella no puede ser nula");
		
		if(entity.getUshuId() <= 0) throw new Exception("El id de usuarioHuella es obligatorio");
				
		UsuarioHuella usuarioHuella = usuarioHuellaDAO.consultarPorIdUsuarioHuella(entity.getUshuId());
		
		if(usuarioHuella == null) throw new Exception("El usuarioHuella con id "+entity.getUshuId()+" no existe");
		
		usuarioHuellaDAO.borrarUsuarioHuella(entity);	
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public UsuarioHuella consultarPorIdUsuarioHuella(Long id) throws Exception {

		if(id == 0 || id ==null) throw new Exception("El id es obligatorio");
		
		return usuarioHuellaDAO.consultarPorIdUsuarioHuella(id);
	}
	
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<UsuarioHuella> consultarTodosUsuarioHuella() throws Exception {
		
		List<UsuarioHuella> losUsuarioHuella = usuarioHuellaDAO.consultarTodosUsuarioHuella();
		
		if( losUsuarioHuella == null || losUsuarioHuella.size()==0) throw new Exception("No hay usuarioHuella");
		
		return losUsuarioHuella;
		
	}
	
}
