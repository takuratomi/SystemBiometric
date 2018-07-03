package co.edu.usbcali.logica;

import java.util.Date;
import java.util.List;

import co.edu.usbcali.modelo.Ingreso;
import co.edu.usbcali.modelo.InoutDaily;
import co.edu.usbcali.modelo.Salida;


public interface IInoutDailyLogica {

	public void crearInoutDaily(InoutDaily inoutDaily) throws Exception;
	public void crearInoutDailyIngreso(InoutDaily inoutDaily, Ingreso ingreso) throws Exception;
	public void modificarInoutDaily(InoutDaily inoutDaily) throws Exception;
	public void borrarInoutDaily(InoutDaily inoutDaily) throws Exception;
	public InoutDaily consultarPorIdInoutDaily(Long id) throws Exception;
	public List<InoutDaily> consultarTodosInoutDaily() throws Exception;
	public List<InoutDaily> consultarPorFechaInoutDaily(Date fecha) throws Exception;
	public List<InoutDaily> consultarPorBitacoraInoutDaily(Long idBita) throws Exception;
	public void crearInoutDailySalida(InoutDaily inoutDaily, Salida salida) throws Exception;
}
