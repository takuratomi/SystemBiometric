package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.InoutDaily;
import co.edu.usbcali.modelo.UsuarioHuella;

@Repository("usuarioHuellaDAO")
@Scope("singleton")
public class UsuarioHuellaDAO implements IUsuarioHuellaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crearUsuarioHuella(UsuarioHuella entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void modificarUsuarioHuella(UsuarioHuella entity) {
		sessionFactory.getCurrentSession().update(entity);		
	}

	@Override
	public void borrarUsuarioHuella(UsuarioHuella entity) {
		sessionFactory.getCurrentSession().delete(entity);		
	}

	@Override
	public UsuarioHuella consultarPorIdUsuarioHuella(Long id) {

		return sessionFactory.getCurrentSession().get(UsuarioHuella.class,id);
	}

	@Override
	public List<UsuarioHuella> consultarTodosUsuarioHuella() {
		
		String hql = "SELECT usu FROM UsuarioHuella usu";
		
		return sessionFactory.getCurrentSession().createQuery(hql).list();
		
	}

	@Override
	public List<InoutDaily> consultarPorFechaInoutDaily() {
		
		String hql = "SELECT usu FROM UsuarioHuella usu";
		
		return null;
	}
	
}
