package co.edu.usbcali.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Scope("singleton")
@Repository("consecutivoFactory")
public class ConsecutivoFactoryDAO implements IConsecutivoFactory {

	@Autowired
	private SessionFactory sessionFactory;

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoBita() {
		String sqlName = "BITA_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoHuelIzq() {
		String sqlName = "HuelIzq_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoHuelDer() {
		String sqlName = "HuelDer_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoUsHu() {
		String sqlName = "UsHu_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoFotografia() {
		String sqlName = "Fotografia_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoInDa() {
		String sqlName = "InDa_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoSalida() {
		String sqlName = "Salida_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoIngreso() {
		String sqlName = "Ingreso_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

}
