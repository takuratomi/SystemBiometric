package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.Fotografia;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.UsuarioHuella;

public interface IUsuarioLogica {
		
	public void crearUsuarioSimpleLogica(Usuario entity ) throws Exception;
	
	public void crearUsuarioCompletoLogica(Usuario usuario, Bitacora bitacora , Fotografia fotografia,UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda) throws Exception;
	
	public void modificarUsuarioCompletoLogica(Usuario usuario, Bitacora bitacora , Fotografia fotografia,UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda) throws Exception;
	
	public void modificarUsuario(Usuario entity) throws Exception;
	
	public void borrarUsuario(Usuario entity) throws Exception;
	
	public Usuario consultarPorIdUsuario(Long id) throws Exception;
	
	public List<Usuario> consultarUsuarioPorIdHuellaIzquierda(Long idHuellaIzquierda) throws Exception;
	public List<Usuario> consultarUsuarioPorIdHuellaDerecha(Long idHuellaDerecha) throws Exception;
	
	public Usuario consultarPorIdUsuarioParaCrear(Long id) throws Exception;
	
	public List<Usuario> consultarTodosUsuario() throws Exception;
	
	

}
