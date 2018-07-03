package co.edu.usbcali.demo.test.dao;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IBitacoraDAO;
import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.Usuario;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BitacoraDAOTest {
	
	public static final Logger log=LoggerFactory.getLogger(BitacoraDAOTest.class);
	
	@Autowired
	private IBitacoraDAO bitacoraDAO;
	
	public static final Long id=7L;
	
	
	@Test
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void aTestCrear() {
		
//	String time1 = "16:30:00";
//	String time2 = "19:15:00";
//	String n = calculateTime(time1,time2);
////	
//	log.info("time hour"+n);	
		
		
	 List<BitacoraHorasTrabajadas> t =	bitacoraDAO.consultarListaPorIdBitacora(id);
	 for (BitacoraHorasTrabajadas bitacoraHorasTrabajadas : t) {
		
		 System.out.println(""+bitacoraHorasTrabajadas.getBitaId());
		 System.out.println(""+bitacoraHorasTrabajadas.getIndaId());
		 System.out.println(""+bitacoraHorasTrabajadas.getIngrId());
		 System.out.println(""+bitacoraHorasTrabajadas.getSaliId());
		 System.out.println(""+bitacoraHorasTrabajadas.getObservacion());
		 System.out.println(""+bitacoraHorasTrabajadas.getFechaingreso());
		 System.out.println(""+bitacoraHorasTrabajadas.getFechasalida());
		 System.out.println(""+bitacoraHorasTrabajadas.getHoraingreso());
		 System.out.println(""+bitacoraHorasTrabajadas.getHorasalida());
		 System.out.println(""+bitacoraHorasTrabajadas.getHorasTrabajadas());
	}
	 
		
	}
	
	
	
	public double calculateTime(String time1, String time2)
	{
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1;
		Date date2;
		double diffHours = 0L;
		try {
			date1 = format.parse(time1);
			date2 = format.parse(time2);
			
			double difference = (double) date2.getTime() - (double) date1.getTime();			
		
			diffHours = difference / (60 * 60* 1000) % 24;
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return diffHours;
	}
	
	

}

