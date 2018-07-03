package co.edu.usbcali.vista;

import java.util.List;

import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.Fotografia;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;
import co.edu.usbcali.modelo.Parametro;
import co.edu.usbcali.modelo.TipoUsuario;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.UsuarioHuella;

public interface IDelegadoDelNegocio {
	
	// ---   TIPO  USUARIO --//
	public void crearTipoUsuario(TipoUsuario tipoUsuario) throws Exception;
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) throws Exception;
	public void borrarTipoUsuario(TipoUsuario tipoUsuario) throws Exception;
	public TipoUsuario consultarPorIdTipoUsuario(Long id) throws Exception;
	public List<TipoUsuario> consultarTodosTipoUsuario() throws Exception;
	
	// ---     USUARIO --//
	public void crearUsuarioSimpleLogica(Usuario entity ) throws Exception;
	
	public void crearUsuarioCompleto(Usuario usuario, Bitacora bitacora , Fotografia fotografia,UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda) throws Exception;
	
	public Usuario consultarPorIdUsuario(Long id) throws Exception;
	
	public List<Usuario> consultarTodosUsuario() throws Exception;
	
	public List<Usuario> consultarUsuarioPorIdHuellaIzquierda(Long idHuellaIzquierda) throws Exception;
	
	public List<Usuario> consultarUsuarioPorIdHuellaDerecha(Long idHuellaDerecha) throws Exception;
	
	public void modificarUsuarioCompleto(Usuario usuario, Bitacora bitacora , Fotografia fotografia,UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda) throws Exception;
	
	public void modificarUsuario(Usuario entity) throws Exception;
	
	public void borrarUsuario(Usuario entity) throws Exception;
	
	// ---  HUELLA IZQUIERDA  --//
	public void crearHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception;
	public void modificarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception;
	public void borrarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception;
	public HuellaIzquierda consultarPorIdHuellaIzquierda(Long id) throws Exception;
	public List<HuellaIzquierda> consultarTodosHuellaIzquierda() throws Exception;
	
	// ---  HUELLA DERECHA  --//
	public void crearHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception;
	public void modificarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception;
	public void borrarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception;
	public HuellaDerecha consultarPorIdHuellaDerecha(Long id) throws Exception;
	public List<HuellaDerecha> consultarTodosHuellaDerecha() throws Exception;
	
	// ---  BITACORA  --//
	public void crearBitacora(Bitacora entity) throws Exception;
	public void modificarBitacora(Bitacora entity) throws Exception;
	public void borrarBitacora(Bitacora entity) throws Exception;
	public Bitacora consultarPorIdBitacora(Long id) throws Exception;
	public List<BitacoraHorasTrabajadas> consultarListaPorIdBitacora(Long id) throws Exception;
	public List<Bitacora> consultarTodosBitacora() throws Exception;
	
	// ---  USUARIO HUELLA  --//
	public void crearUsuarioHuella(UsuarioHuella entity) throws Exception;
	public void modificarUsuarioHuella(UsuarioHuella entity) throws Exception;
	public void borrarUsuarioHuella(UsuarioHuella entity) throws Exception;
	public UsuarioHuella consultarPorIdUsuarioHuella(Long id) throws Exception;
	public List<UsuarioHuella> consultarTodosUsuarioHuella() throws Exception;
	
	// ---  FOTOGRAFIA  --//
	public void crearFotografia(Fotografia entity) throws Exception;
	public void modificarFotografia(Fotografia entity) throws Exception;
	public void borrarFotografia(Fotografia entity) throws Exception;
	public Fotografia consultarPorIdFotografia(Long id) throws Exception;
	public List<Fotografia> consultarTodosFotografia() throws Exception;	
	
	// ---  CONSECUTIVOS  --//
	public Long getConsecutivoBita();	
	public Long getConsecutivoHuelIzq();
	public Long getConsecutivoHuelDer();
	public Long getConsecutivoUsHu();
	public Long getConsecutivoFotografia();
	public Long getConsecutivoInDa();
	public Long getConsecutivoSalida();
	public Long getConsecutivoIngreso();
	
	// ---  PARAMETRO  --//
	public void crearParametro(Parametro entity) throws Exception;
	public void modificarParametro(Parametro entity) throws Exception;
	public void modificarListaParametro(List<Parametro> losParametros) throws Exception; 
	public void borrarParametro(Parametro entity) throws Exception;
	public Parametro consultarPorIdParametro(int id) throws Exception;
	public List<Parametro> consultarTodosParametro() throws Exception;
		
}
