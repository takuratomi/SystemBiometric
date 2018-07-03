package co.edu.usbcali.logica;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IBitacoraDAO;
import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.IHuellaDerechaDAO;
import co.edu.usbcali.dao.IHuellaIzquierdaDAO;
import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.dao.IUsuarioHuellaDAO;
import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.Fotografia;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.UsuarioHuella;

@Service("usuarioLogica")
@Scope("singleton")
public class UsuarioLogica implements IUsuarioLogica {

	@Autowired
	private IUsuarioDAO usuarioDAO;
	@Autowired
	private IUsuarioHuellaLogica usuarioHuellaLogica;
	@Autowired
	private IBitacoraLogica bitacoraLogica;
	@Autowired
	private IHuellaIzquierdaLogica huellaIzquierdaLogica;
	@Autowired
	private IHuellaDerechaLogica huellaDerechaLogica;
	@Autowired
	private IConsecutivoFactory consecutivoFactoryDAO;
	@Autowired
	private IFotografiaLogica fotografiaLogica;
	
	
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public Usuario consultarPorIdUsuario(Long id) throws Exception{
		
		Usuario us = new Usuario();
		
		if(id < 0 || id ==null) throw new Exception("La cedula no puede ser nula");
			
		us = usuarioDAO.consultarPorIdUsuario(id);
		if(us != null)
			us.getTipoUsuario().getTusuNombre();
		
		return us;
	}
	
	
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<Usuario> consultarTodosUsuario() throws Exception {
		
		List<Usuario> tmpUsuario = usuarioDAO.consultarTodosUsuario();
		
		if( tmpUsuario == null)
			throw new Exception("No se encontraron registros");
		
		return tmpUsuario ;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearUsuarioSimpleLogica(Usuario entity) throws Exception {
		
		Usuario usuarioValidacion;
				
		if(entity == null) throw new Exception("EL entity no puede ser nulo");
		
		if(entity.getUsuCedula() <=0) throw new Exception("La cedula es obligatoria");
		
		if(entity.getUsuPrimernombre() == null || entity.getUsuPrimernombre().toString().trim().equals("")) throw new Exception("El primer nombre del entity es obligatorio");
				
		if(entity.getUsuPrimerapellido() == null || entity.getUsuPrimerapellido().toString().trim().equals("")) throw new Exception("El segundo nombre del entity es obligatorio");
		
		if(entity.getUsuSegundoapellido() == null || entity.getUsuSegundoapellido().toString().trim().equals("")) throw new Exception("El segundo apellido del entity es obligatorio");
		
		if(entity.getDireccion() == null || entity.getDireccion().toString().trim().equals("")) throw new Exception("La direccion es obligatoria");
			
		if(entity.getTelefono() == null || entity.getTelefono().toString().trim().equals("")) throw new Exception("El Telefono es obligatoria");
		
		if(entity.getCiudad() == null || entity.getCiudad().toString().trim().equals("")) throw new Exception("La ciudad es obligatoria");
		
		if(entity.getEstado() == null || entity.getEstado().toString().trim().equals("")) throw new Exception("El estado es obligatorio");
		
		if(entity.getIngresoidentificacion() == null || entity.getIngresoidentificacion().toString().trim().equals("") ) throw new Exception("EL ingreso con identificación es obligatorio");
		
		if(entity.getBitacora() == null) throw new Exception("La bitacora no puede ser nula");
		
		if(entity.getBitacora().getBitaId() <=0 ) throw new Exception("El codigo de la bitacora es obligatoria");
		
		if(entity.getFotografia() == null) throw new Exception("La fotografia no puede ser nula");
		
		if(entity.getFotografia().getFotoId() <=0 ) throw new Exception("El codigo de la fotografia es obligatoria");
				
		if(entity.getTipoUsuario() == null) throw new Exception("EL tipo de usuario no puede ser nulo");
		
		if(entity.getTipoUsuario().getTusuId() <= 0) throw new Exception("EL codigo del tipo de usuario es obligatorio");
				
		if(entity.getUsuFechnacimiento() == null || entity.getUsuFechnacimiento().toString().trim().equals("")) throw new Exception("La fecha de nacimiento es obligatoria");
		
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("")) throw new Exception("El entity creador es obligatoria");
		
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creación es obligatoria");
		
		usuarioValidacion = usuarioDAO.consultarPorIdUsuario(entity.getUsuCedula());
		
		if(usuarioValidacion != null) throw new Exception("EL usuario con cedula "+entity.getUsuCedula()+" ya existe");
 
		usuarioDAO.crearUsuario(entity);
		
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void crearUsuarioCompletoLogica(Usuario usuario, Bitacora bitacora , Fotografia fotografia,UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda ) throws Exception {
		
		int se_guarda_usuarioHuella = 0;
		
		fotografiaLogica.crearFotografia(fotografia);
		bitacoraLogica.crearBitacora(bitacora);
		crearUsuarioSimpleLogica(usuario);		
		
		if(usuarioHuella.getHuellaDerecha().getHuelladerecha() != null && usuarioHuella.getHuellaDerecha().getHuelladerecha().length > 0 )
		{
			huellaDerechaLogica.crearHuellaDerecha(huellaDerecha);
			se_guarda_usuarioHuella++;
		}
			
		if(usuarioHuella.getHuellaIzquierda().getHuellaizquierda() != null && usuarioHuella.getHuellaIzquierda().getHuellaizquierda().length > 0)
		{
			huellaIzquierdaLogica.crearHuellaIzquierda(huellaIzquierda);
			se_guarda_usuarioHuella++;
		}	
		
		if(se_guarda_usuarioHuella > 0)
			usuarioHuellaLogica.crearUsuarioHuella(usuarioHuella);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarUsuario(Usuario entity) throws Exception {
		
		if(entity == null) throw new Exception("EL entity no puede ser nulo");
		
		if(entity.getUsuCedula() <=0) throw new Exception("La cedula es obligatoria");
		
		if(entity.getUsuPrimernombre() == null || entity.getUsuPrimernombre().toString().trim().equals("")) throw new Exception("El primer nombre del entity es obligatorio");
				
		if(entity.getUsuPrimerapellido() == null || entity.getUsuPrimerapellido().toString().trim().equals("")) throw new Exception("El segundo nombre del entity es obligatorio");
		
		if(entity.getUsuSegundoapellido() == null || entity.getUsuSegundoapellido().toString().trim().equals("")) throw new Exception("El segundo apellido del entity es obligatorio");
		
		if(entity.getDireccion() == null || entity.getDireccion().toString().trim().equals("")) throw new Exception("La direccion es obligatoria");
			
		if(entity.getTelefono() == null || entity.getTelefono().toString().trim().equals("")) throw new Exception("El Telefono es obligatoria");
		
		if(entity.getCiudad() == null || entity.getCiudad().toString().trim().equals("")) throw new Exception("La ciudad es obligatoria");
		
		if(entity.getEstado() == null || entity.getEstado().toString().trim().equals("")) throw new Exception("El estado es obligatorio");
		
		if(entity.getIngresoidentificacion() == null || entity.getIngresoidentificacion().toString().trim().equals("") ) throw new Exception("EL ingreso con identificación es obligatorio");
		
		if(entity.getBitacora() == null) throw new Exception("La bitacora no puede ser nula");
		
		if(entity.getBitacora().getBitaId() <=0 ) throw new Exception("El codigo de la bitacora es obligatoria");
		
		if(entity.getFotografia() == null) throw new Exception("La fotografia no puede ser nula");
		
		if(entity.getFotografia().getFotoId() <=0 ) throw new Exception("El codigo de la fotografia es obligatoria");
				
		if(entity.getTipoUsuario() == null) throw new Exception("EL tipo de usuario no puede ser nulo");
		
		if(entity.getTipoUsuario().getTusuId() <= 0) throw new Exception("EL codigo del tipo de usuario es obligatorio");
				
		if(entity.getUsuFechnacimiento() == null || entity.getUsuFechnacimiento().toString().trim().equals("")) throw new Exception("La fecha de nacimiento es obligatoria");
		
		if(entity.getUsuCreador() == null || entity.getUsuCreador().toString().trim().equals("")) throw new Exception("El usuario creador es obligatoria");
		
		if(entity.getFechaCreador() == null || entity.getFechaCreador().toString().trim().equals("")) throw new Exception("La fecha de creación es obligatoria");
		
		if(entity.getUsuModificador() == null || entity.getUsuModificador().toString().trim().equals("")) throw new Exception("El usuario modificador es obligatoria");
		
		if(entity.getFechaModificador() == null || entity.getFechaModificador().toString().trim().equals("")) throw new Exception("La fecha de modificacion es obligatoria");
		
		Usuario usuario = usuarioDAO.consultarPorIdUsuario(entity.getUsuCedula());
		if ( usuario == null ) throw new Exception("EL usuario no existe ");
		 
		usuarioDAO.modificarUsuario(entity);
		
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void borrarUsuario(Usuario entity) throws Exception {
		
		if(entity == null) throw new Exception("EL entity no puede ser nulo");
		
		if(entity.getUsuCedula() <=0) throw new Exception("La cedula es obligatoria");
		 
		usuarioDAO.borrarUsuario(entity);		
		
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void modificarUsuarioCompletoLogica(Usuario usuario, Bitacora bitacora, Fotografia fotografia,UsuarioHuella usuarioHuella, HuellaDerecha huellaDerecha, HuellaIzquierda huellaIzquierda)	throws Exception 
	{
		
		int se_modifica_usuarioHuella = 0;
		int se_crea_primera_ves = 0;
		Long idFoto = 0L;
		
		Usuario userMod = usuarioDAO.consultarPorIdUsuarioParaCrear(usuario.getUsuCedula());
		idFoto = userMod.getFotografia().getFotoId();
		Fotografia fotoMod = fotografiaLogica.consultarPorIdFotografia(idFoto);
		UsuarioHuella ushuMod = usuarioHuellaLogica.consultarPorIdUsuarioHuella(userMod.getHuusId());
				
		userMod.setUsuPrimernombre(usuario.getUsuPrimernombre());
		userMod.setUsuPrimerapellido(usuario.getUsuPrimerapellido());
		userMod.setUsuSegundoapellido(usuario.getUsuSegundoapellido());
		userMod.setUsuSegundonombre(usuario.getUsuSegundonombre());
		userMod.setCiudad(usuario.getCiudad());
		userMod.setDireccion(usuario.getDireccion());
		userMod.setEstado(usuario.getEstado());
		userMod.setFechaModificador(usuario.getFechaModificador());
		userMod.setIngresoidentificacion(usuario.getIngresoidentificacion());
		userMod.setTelefono(usuario.getTelefono());
		userMod.setTipoUsuario(usuario.getTipoUsuario());
		userMod.setUsuFechnacimiento(usuario.getUsuFechnacimiento());
		userMod.setUsuModificador(usuario.getUsuModificador());
		
		// se valida si el password cambia si no cambia se deja el mismo o no se crea 
		if(usuario.getUsuPassword() == null || usuario.getUsuPassword().toString().trim().equals(""))
		{
			
		}
		else
		{
			userMod.setUsuPassword(usuario.getUsuPassword());
		}
		
		// no tiene huella y se quiere crear las dos
		if(ushuMod == null && ((huellaIzquierda.getHuellaizquierda().length > 0 && huellaDerecha.getHuelladerecha().length > 0)))
		{
			// huella derecha
			
			huellaDerecha.setHudeId(consecutivoFactoryDAO.getConsecutivoHuelDer());
			huellaDerecha.setUsuCreador(huellaDerecha.getUsuModificador());
			huellaDerecha.setFechaCreador(huellaDerecha.getFechaModificador());
			huellaDerecha.setUsuModificador(null);
			huellaDerecha.setFechaModificador(null);
			
			// huella izquierda
			huellaIzquierda.setHullId2(consecutivoFactoryDAO.getConsecutivoHuelIzq());
			huellaIzquierda.setUsuCreador(huellaIzquierda.getUsuModificador());
			huellaIzquierda.setFechaCreador(huellaIzquierda.getFechaModificador());
			huellaIzquierda.setUsuModificador(null);
			huellaIzquierda.setFechaModificador(null);
			usuarioHuella = new UsuarioHuella();
			usuarioHuella.setUshuId(consecutivoFactoryDAO.getConsecutivoUsHu());
			usuarioHuella.setHuellaIzquierda(huellaIzquierda);
			usuarioHuella.setHuellaDerecha(huellaDerecha);
			usuarioHuella.setUsuCreador(usuarioHuella.getUsuModificador());
			usuarioHuella.setUsuModificador(null);
			usuarioHuella.setFechaCreador(usuarioHuella.getFechaModificador());
			usuarioHuella.setFechaModificador(null);
			usuarioHuella.setUsuario(userMod);
			userMod.setHuusId(usuarioHuella.getUshuId());
						
			huellaDerechaLogica.crearHuellaDerecha(huellaDerecha);
			huellaIzquierdaLogica.crearHuellaIzquierda(huellaIzquierda);
			usuarioHuellaLogica.crearUsuarioHuella(usuarioHuella);
			
		}
		// no tiene huella y se quiere crear huellaIzquierda
		else if(ushuMod == null && ((huellaIzquierda.getHuellaizquierda().length > 0 && huellaDerecha.getHuelladerecha().length <= 0)))
		{
						
			// huella izquierda
			huellaIzquierda.setHullId2(consecutivoFactoryDAO.getConsecutivoHuelIzq());
			huellaIzquierda.setUsuCreador(huellaIzquierda.getUsuModificador());
			huellaIzquierda.setFechaCreador(huellaIzquierda.getFechaModificador());
			huellaIzquierda.setUsuModificador(null);
			huellaIzquierda.setFechaModificador(null);
			usuarioHuella = new UsuarioHuella();
			usuarioHuella.setUshuId(consecutivoFactoryDAO.getConsecutivoUsHu());
			usuarioHuella.setHuellaIzquierda(huellaIzquierda);
			usuarioHuella.setUsuCreador(usuarioHuella.getUsuModificador());
			usuarioHuella.setUsuModificador(null);
			usuarioHuella.setFechaCreador(usuarioHuella.getFechaModificador());
			usuarioHuella.setFechaModificador(null);
			usuarioHuella.setUsuario(userMod);
			userMod.setHuusId(usuarioHuella.getUshuId());
			
			huellaIzquierdaLogica.crearHuellaIzquierda(huellaIzquierda);
			usuarioHuellaLogica.crearUsuarioHuella(usuarioHuella);			
			
		}
		// no tiene huella y se quiere crear huellaDerecha
		else if(ushuMod == null && ((huellaIzquierda.getHuellaizquierda().length <= 0 && huellaDerecha.getHuelladerecha().length > 0)))
		{
			// huella derecha
			huellaDerecha.setHudeId(consecutivoFactoryDAO.getConsecutivoHuelDer());
			huellaDerecha.setUsuCreador(huellaDerecha.getUsuModificador());
			huellaDerecha.setFechaCreador(huellaDerecha.getFechaModificador());
			huellaDerecha.setUsuModificador(null);
			huellaDerecha.setFechaModificador(null);
			
			usuarioHuella = new UsuarioHuella();
			usuarioHuella.setUshuId(consecutivoFactoryDAO.getConsecutivoUsHu());
			usuarioHuella.setHuellaDerecha(huellaDerecha);
			usuarioHuella.setUsuCreador(usuarioHuella.getUsuModificador());
			usuarioHuella.setUsuModificador(null);
			usuarioHuella.setFechaCreador(usuarioHuella.getFechaModificador());
			usuarioHuella.setFechaModificador(null);
			usuarioHuella.setUsuario(userMod);
			userMod.setHuusId(usuarioHuella.getUshuId());
			
			huellaDerechaLogica.crearHuellaDerecha(huellaDerecha);
			usuarioHuellaLogica.crearUsuarioHuella(usuarioHuella);
			
		}
		
//		usuarioDAO.modificarUsuario(userMod);
		
		// si tiene huella y se van actualizar las dos
		if(ushuMod != null && ((huellaIzquierda.getHuellaizquierda().length > 0 && huellaDerecha.getHuelladerecha().length > 0)))
		{
			Long idHuIz = ushuMod.getHuellaIzquierda().getHullId2();
			Long idHuDer = ushuMod.getHuellaDerecha().getHudeId();
			
			HuellaIzquierda huIzMod = huellaIzquierdaLogica.consultarPorIdHuellaIzquierda(idHuIz);
			HuellaDerecha huDereMod = huellaDerechaLogica.consultarPorIdHuellaDerecha(idHuDer);
			
			// huella derecha	
			//huDereMod.setHudeId(idHuDer);
			huDereMod.setHuelladerecha(huellaDerecha.getHuelladerecha());
			huDereMod.setUsuModificador(huellaDerecha.getUsuModificador());
			huDereMod.setFechaModificador(huellaDerecha.getFechaModificador());
			
			// huella izquierda
			//huIzMod.setHullId2(idHuIz);
			huIzMod.setHuellaizquierda(huellaIzquierda.getHuellaizquierda());
			huIzMod.setUsuModificador(huellaIzquierda.getUsuModificador());
			huIzMod.setFechaModificador(huellaIzquierda.getFechaModificador());
			
			ushuMod.setHuellaIzquierda(huIzMod);
			ushuMod.setHuellaDerecha(huDereMod);
			ushuMod.setFechaModificador(usuarioHuella.getFechaModificador());
			ushuMod.setUsuModificador(usuarioHuella.getUsuModificador());
						
			huellaDerechaLogica.modificarHuellaDerecha(huDereMod);
			huellaIzquierdaLogica.modificarHuellaIzquierda(huIzMod);
//			usuarioHuellaLogica.modificarUsuarioHuella(ushuMod);
		}
		// si tiene huella y se actualiza solo la huellaIzquierda
		else if(ushuMod != null && ((huellaIzquierda.getHuellaizquierda().length > 0 && huellaDerecha.getHuelladerecha().length <= 0)))
		{
			Long idHuIz = ushuMod.getHuellaIzquierda().getHullId2();
			HuellaIzquierda huIzMod = huellaIzquierdaLogica.consultarPorIdHuellaIzquierda(idHuIz);
			
			// huella izquierda
			huIzMod.setHuellaizquierda(huellaIzquierda.getHuellaizquierda());
			huIzMod.setUsuModificador(huellaIzquierda.getUsuModificador());
			huIzMod.setFechaModificador(huellaIzquierda.getFechaModificador());
			
			ushuMod.setHuellaIzquierda(huellaIzquierda);
			ushuMod.setFechaModificador(usuarioHuella.getFechaModificador());
			ushuMod.setUsuModificador(usuarioHuella.getUsuModificador());
						
			huellaIzquierdaLogica.modificarHuellaIzquierda(huIzMod);
			usuarioHuellaLogica.modificarUsuarioHuella(ushuMod);
		}
		// si tiene huella y se actualiza solo la huellaDerecha
		else if(ushuMod != null && ((huellaIzquierda.getHuellaizquierda().length <= 0 && huellaDerecha.getHuelladerecha().length > 0)))
		{
			Long idHuDer = ushuMod.getHuellaDerecha().getHudeId();
			HuellaDerecha huDereMod = huellaDerechaLogica.consultarPorIdHuellaDerecha(idHuDer);
			
			// huella derecha			
			huDereMod.setHuelladerecha(huellaDerecha.getHuelladerecha());
			huDereMod.setUsuModificador(huellaDerecha.getUsuModificador());
			huDereMod.setFechaModificador(huellaDerecha.getFechaModificador());
			
			ushuMod.setHuellaDerecha(huellaDerecha);
			ushuMod.setFechaModificador(usuarioHuella.getFechaModificador());
			ushuMod.setUsuModificador(usuarioHuella.getUsuModificador());
						
			huellaDerechaLogica.modificarHuellaDerecha(huDereMod);			
//			usuarioHuellaLogica.modificarUsuarioHuella(ushuMod);
		}
		
		
		if(fotografia.getImagenbin().length > 0)
		{
			fotoMod.setFechaModificador(fotografia.getFechaModificador());
			fotoMod.setUsuModificador(fotografia.getUsuModificador());
			fotoMod.setImagenbin(fotografia.getImagenbin());
			fotografiaLogica.modificarFotografia(fotografia);
		}
		
		
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public Usuario consultarPorIdUsuarioParaCrear(Long id) throws Exception {
		if(id < 0 || id ==null) throw new Exception("La cedula no puede ser nula");
		return usuarioDAO.consultarPorIdUsuarioParaCrear(id);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<Usuario> consultarUsuarioPorIdHuellaIzquierda(Long idHuellaIzquierda) throws Exception {
		if(idHuellaIzquierda < 0 || idHuellaIzquierda ==null) throw new Exception("La cedula no puede ser nula");
		return usuarioDAO.consultarUsuarioPorIdHuellaIzquierda(idHuellaIzquierda);
	}

	@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public List<Usuario> consultarUsuarioPorIdHuellaDerecha(Long idHuellaDerecha) throws Exception {
		if(idHuellaDerecha < 0 || idHuellaDerecha ==null) throw new Exception("La cedula no puede ser nula");
		return usuarioDAO.consultarUsuarioPorIdHuellaDerecha(idHuellaDerecha);
	}

}

