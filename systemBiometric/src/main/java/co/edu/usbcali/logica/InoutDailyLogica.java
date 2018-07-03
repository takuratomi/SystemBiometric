package co.edu.usbcali.logica;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IInoutDailyDAO;
import co.edu.usbcali.dao.ISalidaDAO;
import co.edu.usbcali.modelo.Ingreso;
import co.edu.usbcali.modelo.InoutDaily;
import co.edu.usbcali.modelo.Salida;

@Repository("inoutDailyLogica")
@Scope("singleton")
public class InoutDailyLogica implements IInoutDailyLogica {

	@Autowired
	private IInoutDailyDAO inoutDailyDAO;  
	@Autowired
	private IIngresoLogica ingresoLogica;
	@Autowired
	private ISalidaLogica salidaLogica;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void crearInoutDaily(InoutDaily inoutDaily) throws Exception {
		
		if( inoutDaily == null ) throw new Exception("La entidad inoutDaily no puede ser nula");
		if( inoutDaily.getIndaId() <=0 ) throw new Exception("El id inoutDaily es obligatorio");
		if( inoutDaily.getBitacora() == null ) throw new Exception("La bitacora es obligatoria para inoutDaily");
		if( inoutDaily.getBitacora().getBitaId() <=0 ) throw new Exception("El id de la bitacor inoutDaily es obligatorio");
		if( inoutDaily.getUsuCreador() == null || inoutDaily.getUsuCreador().trim().equals("") ) throw new Exception("El usuario creador de inoutDaily es obligatorio");
		if( inoutDaily.getFechaCreador() == null  || inoutDaily.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creacion inoutDaily es obligatoria");
		
		InoutDaily temp = inoutDailyDAO.consultarPorIdInoutDaily(inoutDaily.getIndaId());
		if(temp != null) throw new Exception("inoutDaily ya existe con el id "+inoutDaily.getIndaId());
		
		inoutDailyDAO.crearInoutDaily(inoutDaily);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void modificarInoutDaily(InoutDaily inoutDaily) throws Exception {
		
		if( inoutDaily == null ) throw new Exception("La entidad inoutDaily no puede ser nula");
		if( inoutDaily.getIndaId() <=0 ) throw new Exception("El id inoutDaily es obligatorio");
		if( inoutDaily.getBitacora() == null ) throw new Exception("La bitacora es obligatoria para inoutDaily");
		if( inoutDaily.getBitacora().getBitaId() <=0 ) throw new Exception("El id de la bitacor inoutDaily es obligatorio");
		if( inoutDaily.getUsuCreador() == null || inoutDaily.getUsuCreador().trim().equals("") ) throw new Exception("El usuario creador de inoutDaily es obligatorio");
		if( inoutDaily.getFechaCreador() == null  || inoutDaily.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creacion inoutDaily es obligatoria");
		
		if( inoutDaily.getUsuModificador() == null || inoutDaily.getUsuModificador().trim().equals("") ) throw new Exception("El usuario modificar de inoutDaily es obligatorio");
		if( inoutDaily.getFechaModificador() == null  || inoutDaily.getFechaModificador().toString().trim().equals("")) throw new Exception("La fecha de modificacion inoutDaily es obligatoria");
		
		inoutDailyDAO.modificarInoutDaily(inoutDaily);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void borrarInoutDaily(InoutDaily inoutDaily) throws Exception {
		
		if( inoutDaily == null ) throw new Exception("La entidad inoutDaily no puede ser nula");
		if( inoutDaily.getIndaId() <=0 ) throw new Exception("El id inoutDaily es obligatorio");
		
		inoutDailyDAO.borrarInoutDaily(inoutDaily);

	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public InoutDaily consultarPorIdInoutDaily(Long id) throws Exception {
		if(id <=0) throw new Exception("El id es obligatorio");
		return inoutDailyDAO.consultarPorIdInoutDaily(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public List<InoutDaily> consultarTodosInoutDaily() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public List<InoutDaily> consultarPorFechaInoutDaily(Date fecha) throws Exception {
		if(fecha == null || fecha.toString().trim().equals("")) throw new Exception("La fecha es obligatoria");
		return inoutDailyDAO.consultarPorFechaInoutDaily(fecha);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public List<InoutDaily> consultarPorBitacoraInoutDaily(Long idBita) throws Exception {
		if(idBita <=0) throw new Exception("El id es obligatorio");
		return inoutDailyDAO.consultarPorBitacoraInoutDaily(idBita);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void crearInoutDailyIngreso(InoutDaily inoutDaily, Ingreso ingreso) throws Exception {
		
		inoutDailyDAO.crearInoutDaily(inoutDaily);
		ingresoLogica.crearIngreso(ingreso);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void crearInoutDailySalida(InoutDaily inoutDaily, Salida salida) throws Exception {
		
		inoutDailyDAO.modificarInoutDaily(inoutDaily);
		salidaLogica.crearSalida(salida);
		
	}

}
