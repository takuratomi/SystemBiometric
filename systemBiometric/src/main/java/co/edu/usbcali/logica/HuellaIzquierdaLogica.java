package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.IHuellaIzquierdaDAO;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;

@Repository("huellaIzquierdaLogica")
@Scope("singleton")
public class HuellaIzquierdaLogica implements IHuellaIzquierdaLogica {
	
	@Autowired
	private IHuellaIzquierdaDAO huellaIzquierdaDAO;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception{

		if (huellaIzquierda == null) throw new Exception("La huella Izquierda no puede ser nula");

		HuellaIzquierda huellaIzquierda_temp = huellaIzquierdaDAO.consultarPorIdHuellaIzquierda(huellaIzquierda.getHullId2());

		if (huellaIzquierda_temp != null) throw new Exception("La huella Izquierda con id " + huellaIzquierda.getHullId2() + "ya existe");

		if (huellaIzquierda.getHullId2() == 0) throw new Exception("El id es obligatorio");

		if (huellaIzquierda.getHullId2() <= 0) throw new Exception("El id no puede ser negativo");

		if (huellaIzquierda.getHuellaizquierda() == null || huellaIzquierda.getHuellaizquierda().length == 0) throw new Exception("La huella Izquierda es obligatoria");

		if (huellaIzquierda.getUsuCreador() == null || huellaIzquierda.getUsuCreador().equals("")) throw new Exception("Error: Usuario de creación fallido");

		if (huellaIzquierda.getFechaCreador() == null) throw new Exception("Error: Fecha de creación fallida");

		huellaIzquierdaDAO.crearHuellaIzquierda(huellaIzquierda);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception{

		if (huellaIzquierda == null)
			throw new Exception("La huella izquierda no puede ser nula");

		HuellaIzquierda huellaIzquierda_temp = huellaIzquierdaDAO
				.consultarPorIdHuellaIzquierda(huellaIzquierda.getHullId2());

		if (huellaIzquierda_temp == null)
			throw new Exception("La huella izquierda con id " + huellaIzquierda.getHullId2() + "no existe");

		if (huellaIzquierda.getHullId2() == 0)
			throw new Exception("El id es obligatorio");

		if (huellaIzquierda.getHullId2() <= 0)
			throw new Exception("El id no puede ser negativo");

		if (huellaIzquierda.getHuellaizquierda() == null || huellaIzquierda.getHuellaizquierda().length == 0)
			throw new Exception("La huella izquierda es obligatoria");

		if (huellaIzquierda.getUsuCreador() == null || huellaIzquierda.getUsuCreador().equals(""))
			throw new Exception("Error: Usuario de creación fallido");

		if (huellaIzquierda.getFechaCreador() == null)
			throw new Exception("Error: Fecha de creación fallida");

		if (huellaIzquierda.getUsuModificador() == null || huellaIzquierda.getUsuModificador().equals(""))
			throw new Exception("Error: Usuario de creación fallido");

		if (huellaIzquierda.getFechaModificador() == null)
			throw new Exception("Error: Fecha de creación fallida");

		huellaIzquierda_temp.setHullId2(huellaIzquierda.getHullId2());
		huellaIzquierda_temp.setHuellaizquierda(huellaIzquierda.getHuellaizquierda());
		huellaIzquierda_temp.setUsuCreador(huellaIzquierda.getUsuCreador());
		huellaIzquierda_temp.setFechaCreador(huellaIzquierda.getFechaCreador());
		huellaIzquierda_temp.setUsuModificador(huellaIzquierda.getUsuModificador());
		huellaIzquierda_temp.setFechaModificador(huellaIzquierda.getFechaModificador());

		huellaIzquierdaDAO.modificarHuellaIzquierda(huellaIzquierda_temp);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception {
		
		if (huellaIzquierda == null)
			throw new Exception("La huella izquierda no puede ser nula");

		HuellaIzquierda huellaIzquierda_temp = huellaIzquierdaDAO.consultarPorIdHuellaIzquierda(huellaIzquierda.getHullId2());

		if (huellaIzquierda_temp == null)
			throw new Exception("La huella izquierda con id " + huellaIzquierda.getHullId2() + "no existe");

		if (huellaIzquierda.getHullId2() == 0)
			throw new Exception("El id es obligatorio");

		if (huellaIzquierda.getHullId2() <= 0)
			throw new Exception("El id no puede ser negativo");

		huellaIzquierdaDAO.borrarHuellaIzquierda(huellaIzquierda_temp);
	}
	
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public HuellaIzquierda consultarPorIdHuellaIzquierda(Long id) throws Exception{
		
		if(id == 0 || id == null) throw new Exception("El id es obligatorio");
		
		return huellaIzquierdaDAO.consultarPorIdHuellaIzquierda(id); 
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<HuellaIzquierda> consultarTodosHuellaIzquierda() throws Exception{
		
		List<HuellaIzquierda> lasHuellasIzquierdas = huellaIzquierdaDAO.consultarTodosHuellaIzquierda();
		
		if(lasHuellasIzquierdas == null || lasHuellasIzquierdas.size() == 0) throw new Exception("No hay huellas izqierda");
		
		return lasHuellasIzquierdas; 
	}
	
}
