package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Parametro;


public interface IParametroDAO {
	
	public void crearParametro(Parametro entity);
	public void modificarParametro(Parametro entity);
	public void borrarParametro(Parametro entity);
	public Parametro consultarPorIdParametro(int i);
	public List<Parametro> consultarTodosParametro();

}
