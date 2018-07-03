package co.edu.usbcali.logica;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.IFotografiaDAO;
import co.edu.usbcali.modelo.Fotografia;


@Service("fotografiaLogica")
@Scope("singleton")
public class FotografiaLogica implements IFotografiaLogica {

	@Autowired
	private IFotografiaDAO fotografiaDAO;
		
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearFotografia(Fotografia entity) throws Exception {
				
		if(entity == null) throw new Exception("La fotografia no puede ser nula");
		
		if(entity.getFotoId() <=0 ) throw new Exception("EL id de la fotografia es obligatoria");
		
		if(entity.getImagenbin() == null || entity.getImagenbin().length <=0) throw new Exception("La fotografia es obligatoria");
		
		if(entity.getUsuCreador().toString().trim().equals("") || entity.getUsuCreador() == null) throw new Exception("El usuario creador de la fotografia es obligatori");
		
		if(entity.getFechaCreador() == null ) throw new Exception("La fecha de creacion de la fotografia es obligatoria");
		
		Fotografia ftTemporal = fotografiaDAO.consultarPorIdFotografia(entity.getFotoId());
		
		if(ftTemporal != null) throw new Exception("La fotografia con id "+ entity.getFotoId() + " ya existe");
		
		fotografiaDAO.crearFotografia(entity);		
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarFotografia(Fotografia entity) throws Exception {
		
		if(entity == null) throw new Exception("La fotografia no puede ser nula");
		
		if(entity.getFotoId() <=0 ) throw new Exception("EL id de la fotografia es obligatoria");
		
		if(entity.getImagenbin() == null || entity.getImagenbin().length <=0) throw new Exception("La fotografia es obligatoria");
		
		if(entity.getUsuCreador().toString().trim().equals("") || entity.getUsuCreador() == null) throw new Exception("El usuario creador de la fotografia es obligatori");
		
		if(entity.getFechaCreador() == null ) throw new Exception("La fecha de creacion de la fotografia es obligatoria");
		
		if(entity.getUsuModificador().toString().trim().equals("") || entity.getUsuModificador() == null) throw new Exception("El usuario modificador de la fotografia es obligatori");
		
		if(entity.getFechaModificador() == null ) throw new Exception("La fecha de modificacion de la fotografia es obligatoria");
				
		fotografiaDAO.modificarFotografia(entity);	
		
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarFotografia(Fotografia entity) throws Exception {
		
		Fotografia fValidacion;
		
		if(entity == null) throw new Exception("La fotografia no puede ser nula");
		
		if(entity.getFotoId() <=0 ) throw new Exception("EL id de la fotografia es obligatoria");
		
		fValidacion = fotografiaDAO.consultarPorIdFotografia(entity.getFotoId());
		
		if(fValidacion == null) throw new Exception("La fotografia con id "+entity.getFotoId()+" ya no existe"); 
		
		fotografiaDAO.borrarFotografia(entity);
		
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public Fotografia consultarPorIdFotografia(Long id) throws Exception {
		
		if(id <=0 ) throw new Exception("El di de la fotografia es obligatorio");
		
		return fotografiaDAO.consultarPorIdFotografia(id);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<Fotografia> consultarTodosFotografia() throws Exception {
		
		return fotografiaDAO.consultarTodosFotografia();
	}

}

