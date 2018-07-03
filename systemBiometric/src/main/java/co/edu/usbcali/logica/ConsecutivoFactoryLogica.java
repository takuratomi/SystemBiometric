package co.edu.usbcali.logica;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ConsecutivoFactoryDAO;
import co.edu.usbcali.dao.IConsecutivoFactory;

@Scope("singleton")
@Service("consecutivoFactoryLogica")
public class ConsecutivoFactoryLogica implements IConsecutivoFactoryLogica {

	@Autowired
	private IConsecutivoFactory consecutivoFactoryDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoBita() {
		return consecutivoFactoryDAO.getConsecutivoBita();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoHuelIzq() {
		return consecutivoFactoryDAO.getConsecutivoHuelIzq();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoHuelDer() {
		return consecutivoFactoryDAO.getConsecutivoHuelDer();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoUsHu() {
		return consecutivoFactoryDAO.getConsecutivoUsHu();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoFotografia() {
		return consecutivoFactoryDAO.getConsecutivoFotografia();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoInDa() {
		return consecutivoFactoryDAO.getConsecutivoInDa();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoSalida() {
		return consecutivoFactoryDAO.getConsecutivoSalida();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Rollback(false)
	@Override
	public synchronized Long getConsecutivoIngreso() {
		return consecutivoFactoryDAO.getConsecutivoIngreso();
	}

}
