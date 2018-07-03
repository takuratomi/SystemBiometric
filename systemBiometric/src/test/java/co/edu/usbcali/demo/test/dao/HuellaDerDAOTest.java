package co.edu.usbcali.demo.test.dao;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsecutivoFactory;
import co.edu.usbcali.dao.IHuellaDerechaDAO;
import co.edu.usbcali.modelo.HuellaDerecha;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HuellaDerDAOTest {
	
	public static final Logger log=LoggerFactory.getLogger(HuellaDerDAOTest.class);
	
	@Autowired
	private IHuellaDerechaDAO huellaDerechaDAO;
	
	@Autowired
	private IConsecutivoFactory consecutivoFactory;
	
	public static final Long id=100L;
	
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void aTestCrear() {
		HuellaDerecha huellaDerecha = new HuellaDerecha();
		byte b [] = new byte[2];
		for (int i = 0; i < 2; i++) {
			b[i] = new Byte("000"+i);
		}
		
		Long consecutivo = consecutivoFactory.getConsecutivoHuelDer();
		huellaDerecha.setHudeId(consecutivo);
		huellaDerecha.setUsuCreador("TOSHIRO");
		huellaDerecha.setFechaCreador(new Date());
		huellaDerecha.setHuelladerecha(b);		
		
		huellaDerechaDAO.crearHuellaDerecha(huellaDerecha);
		
		
	}
	
	
	
	
	

}
