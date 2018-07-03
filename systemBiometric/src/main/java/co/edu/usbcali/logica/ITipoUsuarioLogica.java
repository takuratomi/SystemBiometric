package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.TipoUsuario;

public interface ITipoUsuarioLogica {
	
	public void crearTipoUsuario(TipoUsuario tipoUsuario) throws Exception;
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) throws Exception;
	public void borrarTipoUsuario(TipoUsuario tipoUsuario) throws Exception;
	public TipoUsuario consultarPorIdTipoUsuario(Long id) throws Exception;
	public List<TipoUsuario> consultarTodosTipoUsuario() throws Exception;
	

}
