package co.edu.usbcali.logica;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IIngresoDAO;
import co.edu.usbcali.dao.IngresoDAO;
import co.edu.usbcali.modelo.Ingreso;

@Repository("ingresoLogica")
@Scope("singleton")
public class IngresoLogica implements IIngresoLogica {

	@Autowired
	private IIngresoDAO ingresoDAO;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void crearIngreso(Ingreso ingreso) throws Exception {
		
		if(ingreso == null) throw new Exception("EL ingreso no puede ser nulo.");
		if(ingreso.getIngrId() <=0 ) throw new Exception("EL id del ingreso es obligatorio.");
		if(ingreso.getHoraingreso() == null || ingreso.getHoraingreso().toString().trim().equals("")) throw new Exception("La hora de ingreso es oligatoria");
		if(ingreso.getFechaingreso() == null || ingreso.getFechaingreso().toString().trim().equals("")) throw new Exception("La fecha es obligatoria");
		if(ingreso.getUsuCreador() == null || ingreso.getUsuCreador().toString().trim().equals("")) throw new Exception("El usuario de creacion es obligatorio");
		if(ingreso.getFechaCreador() == null || ingreso.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creacion es obligatoria");
		
		Ingreso temp = ingresoDAO.consultarPorIdIngreso(ingreso.getIngrId());
		if(temp != null) throw new Exception("EL ingreso con id "+ingreso.getIngrId()+" ya existe");

		ingresoDAO.crearIngreso(ingreso);
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void modificarIngreso(Ingreso ingreso) throws Exception {
		
		if(ingreso == null) throw new Exception("EL ingreso no puede ser nulo.");
		if(ingreso.getIngrId() <=0 ) throw new Exception("EL id del ingreso es obligatorio.");
		if(ingreso.getHoraingreso() == null || ingreso.getHoraingreso().toString().trim().equals("")) throw new Exception("La hora de ingreso es oligatoria");
		if(ingreso.getFechaingreso() == null || ingreso.getFechaingreso().toString().trim().equals("")) throw new Exception("La fecha es obligatoria");
		if(ingreso.getUsuCreador() == null || ingreso.getUsuCreador().toString().trim().equals("")) throw new Exception("El usuario de creacion es obligatorio");
		if(ingreso.getFechaCreador() == null || ingreso.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creacion es obligatoria");
		
		if(ingreso.getUsuModificador() == null || ingreso.getUsuModificador().toString().trim().equals("")) throw new Exception("El usuario de modificacion es obligatorio");
		if(ingreso.getFechaModificador() == null || ingreso.getFechaModificador().toString().trim().equals("")) throw new Exception("La fecha de modificacion es obligatoria");
		
//		Ingreso temp = ingresoDAO.consultarPorIdIngreso(ingreso.getIngrId());
//		if(temp != null) throw new Exception("EL ingreso con id "+ingreso.getIngrId()+" ya existe");

		ingresoDAO.modificarIngreso(ingreso);
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void borrarIngreso(Ingreso ingreso) throws Exception {
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public Ingreso consultarPorIdIngreso(Long id) throws Exception {
		
		if(id <=0 ) throw new Exception("EL id es obligatorio");
		return ingresoDAO.consultarPorIdIngreso(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public List<Ingreso> consultarTodosIngreso() throws Exception {

		return ingresoDAO.consultarTodosIngreso();
	}

}
