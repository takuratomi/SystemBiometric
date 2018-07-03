package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Fotografia;


public interface IFotografiaDAO {
	
	public void crearFotografia(Fotografia entity);
	public void modificarFotografia(Fotografia entity);
	public void borrarFotografia(Fotografia entity);
	public Fotografia consultarPorIdFotografia(Long id);
	public List<Fotografia> consultarTodosFotografia();

}
