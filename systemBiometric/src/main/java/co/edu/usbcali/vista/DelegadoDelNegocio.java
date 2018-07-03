package co.edu.usbcali.vista;

import java.util.List;

import org.hibernate.engine.query.spi.ParamLocationRecognizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
import co.edu.usbcali.logica.CaptureForm;
import co.edu.usbcali.logica.ConsecutivoFactoryLogica;
import co.edu.usbcali.logica.HuellaIzquierdaLogica;
import co.edu.usbcali.logica.IBitacoraLogica;
import co.edu.usbcali.logica.IConsecutivoFactoryLogica;
import co.edu.usbcali.logica.IFotografiaLogica;
import co.edu.usbcali.logica.IHuellaDerechaLogica;
import co.edu.usbcali.logica.IHuellaIzquierdaLogica;
import co.edu.usbcali.logica.IParametroLogica;
import co.edu.usbcali.logica.ITipoUsuarioLogica;
import co.edu.usbcali.logica.IUsuarioHuellaLogica;
import co.edu.usbcali.logica.IUsuarioLogica;
import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.Fotografia;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;
import co.edu.usbcali.modelo.Parametro;
import co.edu.usbcali.modelo.TipoUsuario;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.UsuarioHuella;


@Scope("singleton")
@Component("delegadoDelNegocio")
public class DelegadoDelNegocio implements IDelegadoDelNegocio {

	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private IFotografiaLogica fotografiaLogica;
	
	@Autowired
	private IBitacoraLogica bitacoraLogica;
	
	@Autowired
	private IUsuarioHuellaLogica usuarioHuellaLogica;
	
	@Autowired
	private IHuellaDerechaLogica  huellaDerechaLogica;
	
	@Autowired
	private IHuellaIzquierdaLogica huellaIzquierdaLogica;
		
	@Autowired
	private IConsecutivoFactoryLogica consecutivoFactoryLogica;
	
	@Autowired
	private IParametroLogica parametroLogica;
	
	@Override
	public void crearTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.crearTipoUsuario(tipoUsuario);

	}

	@Override
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.modificarTipoUsuario(tipoUsuario);

	}

	@Override
	public void borrarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.borrarTipoUsuario(tipoUsuario);

	}

	@Override
	public TipoUsuario consultarPorIdTipoUsuario(Long id) throws Exception {
		return tipoUsuarioLogica.consultarPorIdTipoUsuario(id);
	}

	@Override
	public List<TipoUsuario> consultarTodosTipoUsuario() throws Exception {
		
		return tipoUsuarioLogica.consultarTodosTipoUsuario();
	}

	@Override
	public List<Usuario> consultarTodosUsuario() throws Exception {
		
		return usuarioLogica.consultarTodosUsuario();
	}

	@Override
	public Usuario consultarPorIdUsuario(Long id) throws Exception {
		if(id <= 0 ) throw new Exception("La cedula no puede ser nula");
		return usuarioLogica.consultarPorIdUsuario(id);
	}

	@Override
	public void crearUsuarioSimpleLogica(Usuario entity) throws Exception {
		usuarioLogica.crearUsuarioSimpleLogica(entity);
		
	}

	@Override
	public void crearUsuarioCompleto(Usuario usuario, Bitacora bitacora , Fotografia fotografia,UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda) throws Exception 
	{
		usuarioLogica.crearUsuarioCompletoLogica(usuario,  bitacora , fotografia,usuarioHuella, huellaDerecha, huellaIzquierda);			
	}

	@Override
	public Long getConsecutivoBita() {
		return consecutivoFactoryLogica.getConsecutivoBita() ;
	}

	@Override
	public Long getConsecutivoHuelIzq() {
		return consecutivoFactoryLogica.getConsecutivoHuelIzq() ;
	}

	@Override
	public Long getConsecutivoHuelDer() {
		return consecutivoFactoryLogica.getConsecutivoHuelDer() ;
	}

	@Override
	public Long getConsecutivoUsHu() {
		return consecutivoFactoryLogica.getConsecutivoUsHu() ;
	}

	@Override
	public Long getConsecutivoFotografia() {
		return consecutivoFactoryLogica.getConsecutivoFotografia() ;
	}

	@Override
	public Long getConsecutivoInDa() {
		return consecutivoFactoryLogica.getConsecutivoInDa() ;
	}

	@Override
	public Long getConsecutivoSalida() {
		return consecutivoFactoryLogica.getConsecutivoSalida() ;
	}

	@Override
	public Long getConsecutivoIngreso() {
		return consecutivoFactoryLogica.getConsecutivoIngreso() ;
	}

	@Override
	public void modificarUsuario(Usuario entity) throws Exception {
		usuarioLogica.modificarUsuario(entity);
		
	}

	@Override
	public void borrarUsuario(Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarUsuarioCompleto(Usuario usuario, Bitacora bitacora, Fotografia fotografia,	UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda)	throws Exception {
		
		usuarioLogica.modificarUsuarioCompletoLogica(usuario,  bitacora , fotografia,usuarioHuella, huellaDerecha, huellaIzquierda);
	}

	@Override
	public void crearHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarHuellaIzquierda(HuellaIzquierda huellaIzquierda) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HuellaIzquierda consultarPorIdHuellaIzquierda(Long id) throws Exception {
		
		return huellaIzquierdaLogica.consultarPorIdHuellaIzquierda(id);
	}

	@Override
	public List<HuellaIzquierda> consultarTodosHuellaIzquierda() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarHuellaDerecha(HuellaDerecha huellaDerecha) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HuellaDerecha consultarPorIdHuellaDerecha(Long id) throws Exception {
		// TODO Auto-generated method stub
		return huellaDerechaLogica.consultarPorIdHuellaDerecha(id);
	}

	@Override
	public List<HuellaDerecha> consultarTodosHuellaDerecha() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearBitacora(Bitacora entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarBitacora(Bitacora entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarBitacora(Bitacora entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bitacora consultarPorIdBitacora(Long id) throws Exception {
		// TODO Auto-generated method stub
		return bitacoraLogica.consultarPorIdBitacora(id);
	}

	@Override
	public List<Bitacora> consultarTodosBitacora() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearUsuarioHuella(UsuarioHuella entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarUsuarioHuella(UsuarioHuella entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarUsuarioHuella(UsuarioHuella entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioHuella consultarPorIdUsuarioHuella(Long id) throws Exception {
		// TODO Auto-generated method stub
		return usuarioHuellaLogica.consultarPorIdUsuarioHuella(id);
	}

	@Override
	public List<UsuarioHuella> consultarTodosUsuarioHuella() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearFotografia(Fotografia entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarFotografia(Fotografia entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarFotografia(Fotografia entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fotografia consultarPorIdFotografia(Long id) throws Exception {
		// TODO Auto-generated method stub
		return fotografiaLogica.consultarPorIdFotografia(id);
	}

	@Override
	public List<Fotografia> consultarTodosFotografia() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearParametro(Parametro entity) throws Exception {
		parametroLogica.crearParametro(entity);
		
	}

	@Override
	public void modificarParametro(Parametro entity) throws Exception {
		parametroLogica.modificarParametro(entity);
	}

	@Override
	public void borrarParametro(Parametro entity) throws Exception {
		parametroLogica.borrarParametro(entity);
	}

	@Override
	public Parametro consultarPorIdParametro(int id) throws Exception {
		
		return parametroLogica.consultarPorIdParametro(id);
	}

	@Override
	public List<Parametro> consultarTodosParametro() throws Exception {
		// TODO Auto-generated method stub
		return parametroLogica.consultarTodosParametro();
	}

	@Override
	public void modificarListaParametro(List<Parametro> losParametros) throws Exception {
		parametroLogica.modificarListaParametro(losParametros);
		
	}

	@Override
	public List<Usuario> consultarUsuarioPorIdHuellaIzquierda(Long idHuellaIzquierda) throws Exception {
		
		return usuarioLogica.consultarUsuarioPorIdHuellaIzquierda(idHuellaIzquierda);
	}

	@Override
	public List<Usuario> consultarUsuarioPorIdHuellaDerecha(Long idHuellaDerecha) throws Exception {
		// TODO Auto-generated method stub
		return usuarioLogica.consultarUsuarioPorIdHuellaDerecha(idHuellaDerecha);
	}

	@Override
	public List<BitacoraHorasTrabajadas> consultarListaPorIdBitacora(Long id) throws Exception {
		// TODO Auto-generated method stub
		return bitacoraLogica.consultarListaPorIdBitacora(id);
	}

}
