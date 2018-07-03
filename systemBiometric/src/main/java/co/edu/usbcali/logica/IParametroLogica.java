package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Parametro;


public interface IParametroLogica {
	
	public void crearParametro(Parametro entity) throws Exception;
	public void modificarParametro(Parametro entity) throws Exception;
	public void modificarListaParametro(List<Parametro> losParametros) throws Exception;
	public void borrarParametro(Parametro entity) throws Exception;
	public Parametro consultarPorIdParametro(int id) throws Exception;
	public List<Parametro> consultarTodosParametro() throws Exception;

}
