package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Salida;


public interface ISalidaDAO {
	
	public void crearSalida(Salida salida);
	public void modificarSalida(Salida salida);
	public void borrarSalida(Salida salida);
	public Salida consultarPorIdSalida(Long id);
	public List<Salida> consultarTodosSalida();

}
