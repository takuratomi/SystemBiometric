package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.Salida;

@Repository("salidaDAO")
@Scope("singleton")
public class SalidaDAO implements ISalidaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crearSalida(Salida salida) {
		sessionFactory.getCurrentSession().save(salida);
	}

	@Override
	public void modificarSalida(Salida salida) {
		sessionFactory.getCurrentSession().update(salida);
	}

	@Override
	public void borrarSalida(Salida salida) {
		sessionFactory.getCurrentSession().delete(salida);
	}

	@Override
	public Salida consultarPorIdSalida(Long id) {
		return sessionFactory.getCurrentSession().get(Salida.class, id);
	}

	@Override
	public List<Salida> consultarTodosSalida() {
		
		String hql = "SELECT sali FROM Salida sali ";

		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
