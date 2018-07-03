package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Ingreso;

public interface IIngresoDAO {
	
	public void crearIngreso(Ingreso ingreso);
	public void modificarIngreso(Ingreso ingreso);
	public void borrarIngreso(Ingreso ingreso);
	public Ingreso consultarPorIdIngreso(Long id);
	public List<Ingreso> consultarTodosIngreso();

}
