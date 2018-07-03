package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Usuario;

public interface IUsuarioDAO {

	public void crearUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public void borrarUsuario(Usuario usuario);
	public Usuario consultarPorIdUsuario(Long id);
	public List<Usuario> consultarUsuarioPorIdHuellaIzquierda(Long idHuellaIzquierda);
	public List<Usuario> consultarUsuarioPorIdHuellaDerecha(Long idHuellaDerecha);
	public Usuario consultarPorIdUsuarioParaCrear(Long id);
	public List<Usuario> consultarTodosUsuario();
	
}
