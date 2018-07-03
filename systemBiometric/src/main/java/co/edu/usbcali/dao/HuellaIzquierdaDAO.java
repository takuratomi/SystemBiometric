package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.HuellaIzquierda;

@Repository("huellaIzquierdaDAO")
@Scope("singleton")
public class HuellaIzquierdaDAO implements IHuellaIzquierdaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crearHuellaIzquierda(HuellaIzquierda huellaIzquierda) {
		sessionFactory.getCurrentSession().save(huellaIzquierda);
	}

	@Override
	public void modificarHuellaIzquierda(HuellaIzquierda huellaIzquierda) {
		sessionFactory.getCurrentSession().update(huellaIzquierda);		
	}

	@Override
	public void borrarHuellaIzquierda(HuellaIzquierda huellaIzquierda) {
		sessionFactory.getCurrentSession().delete(huellaIzquierda);		
	}

	@Override
	public HuellaIzquierda consultarPorIdHuellaIzquierda(Long id) {

		return sessionFactory.getCurrentSession().get(HuellaIzquierda.class,id);
	}

	@Override
	public List<HuellaIzquierda> consultarTodosHuellaIzquierda() {
		
		String hql = "SELECT huIz FROM HuellaIzquierda huIz ";
		
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
}
