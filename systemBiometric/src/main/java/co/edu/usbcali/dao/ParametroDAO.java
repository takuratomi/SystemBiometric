package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Parametro;


@Repository("parametroDAO")
@Scope("singleton")
public class ParametroDAO implements IParametroDAO {

	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public void crearParametro(Parametro entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void modificarParametro(Parametro entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void borrarParametro(Parametro entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public Parametro consultarPorIdParametro(int id) {
		return sessionFactory.getCurrentSession().get(Parametro.class,id);
	}

	@Override
	public List<Parametro> consultarTodosParametro() {
		
		String hql = "SELECT param FROM Parametro param ";

		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
