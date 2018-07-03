package co.edu.usbcali.dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.Usuario;

@Repository("usuarioDAO")
@Scope("singleton")
public class UsuarioDAO implements IUsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crearUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);		
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().delete(usuario);		
	}

	@Override
	public Usuario consultarPorIdUsuario(Long id) {

		return sessionFactory.getCurrentSession().get(Usuario.class,id);
	}

	@Override
	public List<Usuario> consultarTodosUsuario() {
		
		String hql = "SELECT usu FROM Usuario usu";
		
		List<Usuario> temporal = sessionFactory.getCurrentSession().createQuery(hql).list();
		
		if(temporal != null || temporal.size()>0)
		{
			for (Usuario usuario : temporal) {
				usuario.getTipoUsuario();
				usuario.getTipoUsuario().getTusuNombre();
			}
		}
//		return sessionFactory.getCurrentSession().createQuery(hql).list();
		return temporal;
	}

	@Override
	public Usuario consultarPorIdUsuarioParaCrear(Long id) {
		Usuario usuario = sessionFactory.getCurrentSession().get(Usuario.class,id);
		usuario.getBitacora().getBitaId();
		usuario.getBitacora().getUsuCreador();
		usuario.getTipoUsuario().getTusuId();
		usuario.getFotografia().getFotoId();
		usuario.getFotografia().getImagenbin();
		
		return usuario;
	}

	@Override
	public List<Usuario> consultarUsuarioPorIdHuellaIzquierda(Long idHuellaIzquierda) {
				
		return sessionFactory.getCurrentSession().getNamedQuery("consultarUsuarioPorHuellaIzquierda").setParameter("idHuellaIzquierda",idHuellaIzquierda).list();
	}

	@Override
	public List<Usuario> consultarUsuarioPorIdHuellaDerecha(Long idHuellaDerecha) {
		
		return sessionFactory.getCurrentSession().getNamedQuery("consultarUsuarioPorHuellaDerecha").setParameter("idHuellaDerecha",idHuellaDerecha).list();
	}
	
}
