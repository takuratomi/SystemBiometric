package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.Param;

import co.edu.usbcali.dao.IParametroDAO;
import co.edu.usbcali.modelo.Parametro;

@Service("parametroLogica")
@Scope("singleton")
public class ParametroLogica implements IParametroLogica {

	@Autowired
	private IParametroDAO parametroDAO;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearParametro(Parametro entity) throws Exception {
				
		if(entity == null ) throw new Exception("El parametro no puede ser nulo");
		if(entity.getParaId() <= 0) throw new Exception("EL id es obligatorio");
		if(entity.getDescripcion() == null || entity.getDescripcion().toString().trim().equals("")) throw new Exception("La descripcion es obligatoria");
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("")) throw new Exception("El usuario creador es obligatorio");
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("") ) throw new Exception("La fecha de creacion es obligatoria");
		
		Parametro tmp = parametroDAO.consultarPorIdParametro(entity.getParaId());
		if(tmp != null) throw new Exception("EL parametro con id " +entity.getParaId()+" ya existe");
				
		parametroDAO.crearParametro(entity);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarParametro(Parametro entity) throws Exception {
		
		if(entity == null ) throw new Exception("El parametro no puede ser nulo");
		if(entity.getParaId() <= 0) throw new Exception("EL id es obligatorio");
		if(entity.getDescripcion() == null || entity.getDescripcion().toString().trim().equals("")) throw new Exception("La descripcion es obligatoria");
		if(entity.getValor() == null || entity.getValor().toString().trim().equals("")) throw new Exception("Los campos no pueden ser vacios ");
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("")) throw new Exception("El usuario creador es obligatorio");
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("") ) throw new Exception("La fecha de creacion es obligatoria");
		
		if(entity.getUsuModificador() == null || entity.getUsuModificador().toString().trim().equals("")) throw new Exception("El usuario modificador es obligatorio");
		if(entity.getFechaModificador() == null || entity.getFechaModificador().toString().trim().equals("") ) throw new Exception("La fecha de modificacion es obligatoria");
						
		parametroDAO.modificarParametro(entity);
		
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarParametro(Parametro entity) throws Exception {
		
		if(entity == null ) throw new Exception("El parametro no puede ser nulo");
		if(entity.getParaId() <= 0) throw new Exception("EL id es obligatorio");
		
		Parametro tmp = parametroDAO.consultarPorIdParametro(entity.getParaId());
		if(tmp == null) throw new Exception("EL parametro con id " +entity.getParaId()+" no existe");
				
		parametroDAO.borrarParametro(entity);

		
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public Parametro consultarPorIdParametro(int id) throws Exception {
		
		if(id<=0) throw new Exception("El id es obligatorio");
		
		return parametroDAO.consultarPorIdParametro(id);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<Parametro> consultarTodosParametro() throws Exception {
		
		return parametroDAO.consultarTodosParametro();
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarListaParametro(List<Parametro> losParametros) throws Exception {
				
		for (Parametro parametro : losParametros) {					
			modificarParametro(parametro);
			//parametroDAO.modificarParametro(parametro);
		}		
	}

}
