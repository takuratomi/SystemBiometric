package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.Ingreso;

@Repository("ingresoDAO")
@Scope("singleton")
public class IngresoDAO implements IIngresoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void crearIngreso(Ingreso ingreso) {
		sessionFactory.getCurrentSession().save(ingreso);
	}

	@Override
	public void modificarIngreso(Ingreso ingreso) {
		sessionFactory.getCurrentSession().update(ingreso);
	}

	@Override
	public void borrarIngreso(Ingreso ingreso) {
		sessionFactory.getCurrentSession().delete(ingreso);
	}

	@Override
	public Ingreso consultarPorIdIngreso(Long id) {
		return sessionFactory.getCurrentSession().get(Ingreso.class,id);
	}

	@Override
	public List<Ingreso> consultarTodosIngreso() {
		String hql = "SELECT ingr FROM Ingreso ingr ";

		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
