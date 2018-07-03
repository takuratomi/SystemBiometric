package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.IHuellaDerechaDAO;
import co.edu.usbcali.modelo.HuellaDerecha;

@Repository("huellaDerechaLogica")
@Scope("singleton")
public class HuellaDerechaLogica implements IHuellaDerechaLogica {

	@Autowired
	private IHuellaDerechaDAO huellaDerechaDAO;
		
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception{
				
		if(huellaDerecha == null) throw new Exception("La huella derecha no puede ser nula");
		
		HuellaDerecha huellaDerecha_temp = huellaDerechaDAO.consultarPorIdHuellaDerecha(huellaDerecha.getHudeId());
		
		if(huellaDerecha_temp != null) throw new Exception("La huella derecha con id "+huellaDerecha.getHudeId()+ "ya existe");
		
		if(huellaDerecha.getHudeId() == 0) throw new Exception("El id es obligatorio");
		
		if(huellaDerecha.getHudeId() <= 0) throw new Exception("El id no puede ser negativo");
		
		if(huellaDerecha.getHuelladerecha() == null || huellaDerecha.getHuelladerecha().length == 0 ) throw new Exception("La huella derecha es obligatoria");
		
		if(huellaDerecha.getUsuCreador() == null || huellaDerecha.getUsuCreador().equals("")) throw new Exception("Error: Usuario de creación fallido");
		
		if(huellaDerecha.getFechaCreador() == null) throw new Exception("Error: Fecha de creación fallida");
				
		huellaDerechaDAO.crearHuellaDerecha(huellaDerecha);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception{
		
		if(huellaDerecha == null) throw new Exception("La huella derecha no puede ser nula");
		
		HuellaDerecha huellaDerecha_temp = huellaDerechaDAO.consultarPorIdHuellaDerecha(huellaDerecha.getHudeId());
		
		if(huellaDerecha_temp == null) throw new Exception("La huella derecha con id "+huellaDerecha.getHudeId()+ "no existe");
		
		if(huellaDerecha.getHudeId() == 0) throw new Exception("El id es obligatorio");
		
		if(huellaDerecha.getHudeId() <= 0) throw new Exception("El id no puede ser negativo");
		
		if(huellaDerecha.getHuelladerecha() == null || huellaDerecha.getHuelladerecha().length == 0 ) throw new Exception("La huella derecha es obligatoria");
		
		if(huellaDerecha.getUsuCreador() == null || huellaDerecha.getUsuCreador().equals("")) throw new Exception("Error: Usuario de creación fallido");
		
		if(huellaDerecha.getFechaCreador() == null) throw new Exception("Error: Fecha de creación fallida");
		
		if(huellaDerecha.getUsuModificador() == null || huellaDerecha.getUsuModificador().equals("")) throw new Exception("Error: Usuario de creación fallido");
		
		if(huellaDerecha.getFechaModificador() == null) throw new Exception("Error: Fecha de creación fallida");
				
		huellaDerecha_temp.setHudeId(huellaDerecha.getHudeId());
		huellaDerecha_temp.setHuelladerecha(huellaDerecha.getHuelladerecha());
		huellaDerecha_temp.setUsuCreador(huellaDerecha.getUsuCreador());
		huellaDerecha_temp.setFechaCreador(huellaDerecha.getFechaCreador());
		huellaDerecha_temp.setUsuModificador(huellaDerecha.getUsuModificador());
		huellaDerecha_temp.setFechaModificador(huellaDerecha.getFechaModificador());
			
		huellaDerechaDAO.modificarHuellaDerecha(huellaDerecha_temp);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception{
		
		if(huellaDerecha == null) throw new Exception("La huella derecha no puede ser nula");
		
		HuellaDerecha huellaDerecha_temp = huellaDerechaDAO.consultarPorIdHuellaDerecha(huellaDerecha.getHudeId());
		
		if(huellaDerecha_temp == null) throw new Exception("La huella derecha con id "+huellaDerecha.getHudeId()+ "no existe");
		
		if(huellaDerecha.getHudeId() == 0) throw new Exception("El id es obligatorio");
		
		if(huellaDerecha.getHudeId() <= 0) throw new Exception("El id no puede ser negativo");
				
		huellaDerechaDAO.borrarHuellaDerecha(huellaDerecha);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public HuellaDerecha consultarPorIdHuellaDerecha(Long id) throws Exception{

		if(id == 0 || id ==null) throw new Exception("El id es obligatorio");
		
		 return huellaDerechaDAO.consultarPorIdHuellaDerecha(id);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<HuellaDerecha> consultarTodosHuellaDerecha() throws Exception{
		
		List<HuellaDerecha> lasHuellasDerehas = huellaDerechaDAO.consultarTodosHuellaDerecha();
		
		if(lasHuellasDerehas == null || lasHuellasDerehas.size() == 0) throw new Exception("No existen huellas");
		
		return lasHuellasDerehas;
	}

}
