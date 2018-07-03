package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Fotografia;


public interface IFotografiaLogica {
	
	public void crearFotografia(Fotografia entity) throws Exception;
	public void modificarFotografia(Fotografia entity) throws Exception;
	public void borrarFotografia(Fotografia entity) throws Exception;
	public Fotografia consultarPorIdFotografia(Long id) throws Exception;
	public List<Fotografia> consultarTodosFotografia() throws Exception;

}
