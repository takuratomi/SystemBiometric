package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.TipoUsuario;

public interface ITipoUsuarioDAO {
	
	public void crearTipoUsuario(TipoUsuario tipoUsuario);
	public void modificarTipoUsuario(TipoUsuario tipoUsuario);
	public void borrarTipoUsuario(TipoUsuario tipoUsuario);
	public TipoUsuario consultarPorIdTipoUsuario(Long id);
	public List<TipoUsuario> consultarTodosTipoUsuario();
	

}
