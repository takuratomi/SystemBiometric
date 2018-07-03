package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.HuellaDerecha;

@Repository("huellaDerechaDAO")
@Scope("singleton")
public class HuellaDerechaDAO implements IHuellaDerechaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crearHuellaDerecha(HuellaDerecha huellaDerecha) {
		sessionFactory.getCurrentSession().save(huellaDerecha);
	}

	@Override
	public void modificarHuellaDerecha(HuellaDerecha huellaDerecha) {
		sessionFactory.getCurrentSession().update(huellaDerecha);
	}

	@Override
	public void borrarHuellaDerecha(HuellaDerecha huellaDerecha) {
		sessionFactory.getCurrentSession().delete(huellaDerecha);
	}

	@Override
	public HuellaDerecha consultarPorIdHuellaDerecha(Long id) {

		return sessionFactory.getCurrentSession().get(HuellaDerecha.class, id);
	}

	@Override
	public List<HuellaDerecha> consultarTodosHuellaDerecha() {
		String hql = "SELECT huDe FROM HuellaDerecha huDe ";

		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
