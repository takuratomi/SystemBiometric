package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ISalidaDAO;
import co.edu.usbcali.modelo.Salida;

@Repository("salidaLogica")
@Scope("singleton")
public class SalidaLogica implements ISalidaLogica {

	@Autowired
	private ISalidaDAO salidaDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void crearSalida(Salida salida) throws Exception {

		if (salida == null)
			throw new Exception("La salida no puede ser nula");
		if (salida.getSaliId() <= 0)
			throw new Exception("EL id de la salida es obligatorio");
		if (salida.getFechasalida() == null || salida.getFechasalida().toString().trim().equals(""))
			throw new Exception("La fecha de salida es obligatoria");
		if (salida.getHorasalida() == null || salida.getHorasalida().toString().trim().equals(""))
			throw new Exception("La hora de salida es obligatoria");
		if (salida.getUsuCreador() == null || salida.getUsuCreador().toString().trim().equals(""))
			throw new Exception("EL usuario creador es obligatorio");
		if (salida.getFechaCreador() == null || salida.getFechaCreador().toString().trim().equals(""))
			throw new Exception("La fecha de creacion es obligatoria");

		Salida temp = salidaDAO.consultarPorIdSalida(salida.getSaliId());
		if (temp != null)
			throw new Exception("La salida con id " + salida.getSaliId() + "  ya existe");

		salidaDAO.crearSalida(salida);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void modificarSalida(Salida salida) throws Exception {

		if (salida == null)
			throw new Exception("La salida no puede ser nula");
		if (salida.getSaliId() <= 0)
			throw new Exception("EL id de la salida es obligatorio");
		if (salida.getFechasalida() == null || salida.getFechasalida().toString().trim().equals(""))
			throw new Exception("La fecha de salida es obligatoria");
		if (salida.getHorasalida() == null || salida.getHorasalida().toString().trim().equals(""))
			throw new Exception("La hora de salida es obligatoria");
		if (salida.getUsuCreador() == null || salida.getUsuCreador().toString().trim().equals(""))
			throw new Exception("EL usuario creador es obligatorio");
		if (salida.getFechaCreador() == null || salida.getFechaCreador().toString().trim().equals(""))
			throw new Exception("La fecha de creacion es obligatoria");

		if (salida.getUsuModificador() == null || salida.getUsuModificador().toString().trim().equals(""))
			throw new Exception("EL usuario modificador es obligatorio");
		if (salida.getFechaModificador() == null || salida.getFechaModificador().toString().trim().equals(""))
			throw new Exception("La fecha de modificacion es obligatoria");

		salidaDAO.crearSalida(salida);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public void borrarSalida(Salida salida) throws Exception {

		if (salida == null)
			throw new Exception("La salida no puede ser nula");
		if (salida.getSaliId() <= 0)
			throw new Exception("EL id de la salida es obligatorio");

		Salida temp = salidaDAO.consultarPorIdSalida(salida.getSaliId());
		if (temp == null)
			throw new Exception("La salida con id " + salida.getSaliId() + "  ya no existe");

		salidaDAO.borrarSalida(temp);

	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public Salida consultarPorIdSalida(Long id) throws Exception {
		if (id <= 0)
			throw new Exception("El id es obligatorio");
		return salidaDAO.consultarPorIdSalida(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public List<Salida> consultarTodosSalida() throws Exception {

		return salidaDAO.consultarTodosSalida();
	}

}
