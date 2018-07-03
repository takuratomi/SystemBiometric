package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
import co.edu.usbcali.modelo.Bitacora;

public interface IBitacoraLogica {
	
	public void crearBitacora(Bitacora entity) throws Exception;
	public void modificarBitacora(Bitacora entity) throws Exception;
	public void borrarBitacora(Bitacora entity) throws Exception;
	public Bitacora consultarPorIdBitacora(Long id) throws Exception;
	public List<BitacoraHorasTrabajadas> consultarListaPorIdBitacora(Long id) throws Exception;
	public List<Bitacora> consultarTodosBitacora() throws Exception;
	
}
