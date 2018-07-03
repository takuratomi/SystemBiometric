package co.edu.usbcali.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.modelo.InoutDaily;

@Repository("inoutDailyDAO")
@Scope("singleton")
public class InoutDailyDAO implements IInoutDailyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void crearInoutDaily(InoutDaily inoutDaily) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(inoutDaily);
	}

	@Override
	public void modificarInoutDaily(InoutDaily inoutDaily) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(inoutDaily);
	}

	@Override
	public void borrarInoutDaily(InoutDaily inoutDaily) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(inoutDaily);
	}

	@Override
	public InoutDaily consultarPorIdInoutDaily(Long id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(InoutDaily.class,id);
	}

	@Override
	public List<InoutDaily> consultarTodosInoutDaily() {
		String hql = "SELECT inDa FROM InoutDaily inDa";

		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<InoutDaily> consultarPorFechaInoutDaily(Date fecha) {
		
		String patterDate = "yyyy-mm-dd";		
		String hql = "SELECT id FROM InoutDaily id where id.fechaCreador=:date";
		/*
		SimpleDateFormat sdf = new SimpleDateFormat(patterDate);
		String fechaStr = sdf.format(fecha);*/
		return sessionFactory.getCurrentSession().createQuery(hql).setParameter("date", fecha).list();
	}

	@Override
	public List<InoutDaily> consultarPorBitacoraInoutDaily(Long idBita) {
		
		String hql = "SELECT id FROM InoutDaily where id.bitacora.bitaId = :idBita";
		   
		return sessionFactory.getCurrentSession().createQuery(hql).setParameter("idBita", idBita).list();
	}

}
