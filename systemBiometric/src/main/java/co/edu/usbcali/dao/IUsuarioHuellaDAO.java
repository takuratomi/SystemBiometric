package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.InoutDaily;
import co.edu.usbcali.modelo.UsuarioHuella;


public interface IUsuarioHuellaDAO {
	
	public void crearUsuarioHuella(UsuarioHuella entity);
	public void modificarUsuarioHuella(UsuarioHuella entity);
	public void borrarUsuarioHuella(UsuarioHuella entity);
	public UsuarioHuella consultarPorIdUsuarioHuella(Long id);
	public List<UsuarioHuella> consultarTodosUsuarioHuella();
	public List<InoutDaily> consultarPorFechaInoutDaily();

}
