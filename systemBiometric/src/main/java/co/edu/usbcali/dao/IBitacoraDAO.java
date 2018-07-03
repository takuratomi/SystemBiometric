package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
import co.edu.usbcali.modelo.Bitacora;


public interface IBitacoraDAO {
	
	public void crearBitacora(Bitacora bitacora);
	public void modificarBitacora(Bitacora bitacora);
	public void borrarBitacora(Bitacora bitacora);
	public Bitacora consultarPorIdBitacora(Long id);
	public List<BitacoraHorasTrabajadas> consultarListaPorIdBitacora(Long id);
	public List<Bitacora> consultarTodosBitacora();
	

}
