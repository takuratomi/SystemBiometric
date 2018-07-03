package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.HuellaDerecha;

public interface IHuellaDerechaLogica {

	public void crearHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception;
	public void modificarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception;
	public void borrarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception;
	public HuellaDerecha consultarPorIdHuellaDerecha(Long id) throws Exception;
	public List<HuellaDerecha> consultarTodosHuellaDerecha() throws Exception;
	
}
