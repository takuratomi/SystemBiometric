package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Salida;


public interface ISalidaLogica {
	
	public void crearSalida(Salida salida) throws Exception;
	public void modificarSalida(Salida salida) throws Exception;
	public void borrarSalida(Salida salida) throws Exception;
	public Salida consultarPorIdSalida(Long id) throws Exception;
	public List<Salida> consultarTodosSalida() throws Exception;

}
