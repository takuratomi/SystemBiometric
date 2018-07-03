package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.TipoUsuario;


@Repository("tipoUsuarioDAO")
@Scope("singleton")
public class TipoUsuarioDAO implements ITipoUsuarioDAO{

	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crearTipoUsuario(TipoUsuario tipoUsuario) {
		
		sessionFactory.getCurrentSession().save(tipoUsuario);
		
	}
	
	@Override
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) {
		
		sessionFactory.getCurrentSession().update(tipoUsuario);
	}

	@Override
	public void borrarTipoUsuario(TipoUsuario tipoUsuario) {
		
		sessionFactory.getCurrentSession().delete(tipoUsuario);
	}

	@Override
	public TipoUsuario consultarPorIdTipoUsuario(Long id) {
		
		return sessionFactory.getCurrentSession().get(TipoUsuario.class, id);
	}

	@Override
	public List<TipoUsuario> consultarTodosTipoUsuario() {
		
		String hql = "SELECT tipoUsuario FROM TipoUsuario tipoUsuario";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	

}
