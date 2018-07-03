package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Fotografia;


@Repository("fotografiaDAO")
@Scope("singleton")
public class FotografiaDAO implements IFotografiaDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void crearFotografia(Fotografia entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void modificarFotografia(Fotografia entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void borrarFotografia(Fotografia entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public Fotografia consultarPorIdFotografia(Long id) {
		// TODO Auto-generated method stub
		Fotografia fotografia = new Fotografia();
		fotografia = sessionFactory.getCurrentSession().get(Fotografia.class,id) ;
		return fotografia;
	}

	@Override
	public List<Fotografia> consultarTodosFotografia() {
		// TODO Auto-generated method stub
		
		String hql = "SELECT bita FROM Fotografia bita ";

		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
