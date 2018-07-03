package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.UsuarioHuella;


public interface IUsuarioHuellaLogica {
	
	public void crearUsuarioHuella(UsuarioHuella entity) throws Exception;
	public void modificarUsuarioHuella(UsuarioHuella entity) throws Exception;
	public void borrarUsuarioHuella(UsuarioHuella entity) throws Exception;
	public UsuarioHuella consultarPorIdUsuarioHuella(Long id) throws Exception;
	public List<UsuarioHuella> consultarTodosUsuarioHuella() throws Exception;

}
