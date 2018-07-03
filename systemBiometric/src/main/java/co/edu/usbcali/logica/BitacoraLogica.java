package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.BitacoraDAO;
import co.edu.usbcali.dao.IBitacoraDAO;
import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.HuellaDerecha;

@Service("bitacoraLogica")
@Scope("singleton")
public class BitacoraLogica implements IBitacoraLogica {

	@Autowired
	private IBitacoraDAO bitacoraDAO;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearBitacora(Bitacora entity) throws Exception {
				
		if(entity == null) throw new Exception("La bitacora no puede ser nula");
		
		if(entity.getBitaId() <=0 ) throw new Exception("El id de la bitacora es obligatoria");
		
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("")) throw new Exception("El usuario creador es obligatorio");
		
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creación es obligatoria");
		
		Bitacora bitacora = bitacoraDAO.consultarPorIdBitacora(entity.getBitaId());
		
		if(bitacora != null) throw new Exception("La bitacora con id "+entity.getBitaId()+" ya existe");
		
		bitacoraDAO.crearBitacora(entity);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarBitacora(Bitacora entity) throws Exception {
		
		if(entity == null) throw new Exception("La bitacora no puede ser nula");
		
		if(entity.getBitaId() <=0 ) throw new Exception("El id de la bitacora es obligatoria");
		
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("")) throw new Exception("El usuario creador es obligatorio");
		
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creación es obligatoria");
		
		if(entity.getUsuModificador() == null || entity.getUsuModificador().toString().trim().equals("")) throw new Exception("El usuario modificar es obligatorio");
		
		if(entity.getFechaModificador() == null || entity.getFechaModificador().toString().trim().equals("")) throw new Exception("La fecha de modificacion es obligatoria");
		
		bitacoraDAO.modificarBitacora(entity);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarBitacora(Bitacora entity) throws Exception {

		if(entity == null) throw new Exception("La bitacora no puede ser nula");
		
		if(entity.getBitaId() <=0 ) throw new Exception("El id de la bitacora es obligatoria");
		
		Bitacora bitacora = bitacoraDAO.consultarPorIdBitacora(entity.getBitaId());
		
		if(bitacora == null) throw new Exception("La bitacora con id "+entity.getBitaId()+" no existe");
		
		bitacoraDAO.borrarBitacora(entity);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public Bitacora consultarPorIdBitacora(Long id) throws Exception {
		
		if(id == null || id<=0) throw new Exception("El id es obligatorio");
		
		return bitacoraDAO.consultarPorIdBitacora(id);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<Bitacora> consultarTodosBitacora() throws Exception {
		
		List<Bitacora> lasBitacoras = bitacoraDAO.consultarTodosBitacora();
		
		if(lasBitacoras == null || lasBitacoras.size() == 0) throw new Exception("No existen Bitacoras");
		
		return lasBitacoras;
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<BitacoraHorasTrabajadas> consultarListaPorIdBitacora(Long id) throws Exception {
		// TODO Auto-generated method stub
		return bitacoraDAO.consultarListaPorIdBitacora(id);
	}

}
