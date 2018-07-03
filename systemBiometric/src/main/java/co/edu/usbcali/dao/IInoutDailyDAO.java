package co.edu.usbcali.dao;

import java.util.Date;
import java.util.List;
import co.edu.usbcali.modelo.InoutDaily;


public interface IInoutDailyDAO {

	public void crearInoutDaily(InoutDaily inoutDaily);
	public void modificarInoutDaily(InoutDaily inoutDaily);
	public void borrarInoutDaily(InoutDaily inoutDaily);
	public InoutDaily consultarPorIdInoutDaily(Long id);
	public List<InoutDaily> consultarTodosInoutDaily();
	public List<InoutDaily> consultarPorFechaInoutDaily(Date fecha);
	public List<InoutDaily> consultarPorBitacoraInoutDaily(Long idBita);
}

