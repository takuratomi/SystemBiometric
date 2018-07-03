package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.HuellaDerecha;

public interface IHuellaDerechaDAO {

	public void crearHuellaDerecha(HuellaDerecha huellaDerecha);
	public void modificarHuellaDerecha(HuellaDerecha huellaDerecha);
	public void borrarHuellaDerecha(HuellaDerecha huellaDerecha);
	public HuellaDerecha consultarPorIdHuellaDerecha(Long id);
	public List<HuellaDerecha> consultarTodosHuellaDerecha();
	
}
