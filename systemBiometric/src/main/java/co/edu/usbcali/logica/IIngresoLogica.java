package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Ingreso;

public interface IIngresoLogica {
	
	public void crearIngreso(Ingreso ingreso) throws Exception;
	public void modificarIngreso(Ingreso ingreso) throws Exception;
	public void borrarIngreso(Ingreso ingreso) throws Exception;
	public Ingreso consultarPorIdIngreso(Long id) throws Exception;
	public List<Ingreso> consultarTodosIngreso() throws Exception;

}
