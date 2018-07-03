package co.edu.usbcali.dao;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.postgresql.core.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.dto.BitacoraHorasTrabajadas;
import co.edu.usbcali.modelo.Bitacora;


@Repository("bitacoraDAO")
@Scope("singleton")
public class BitacoraDAO implements IBitacoraDAO {

	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public void crearBitacora(Bitacora bitacora) {
		sessionFactory.getCurrentSession().save(bitacora);
	}

	@Override
	public void modificarBitacora(Bitacora bitacora) {
		sessionFactory.getCurrentSession().update(bitacora);
	}

	@Override
	public void borrarBitacora(Bitacora bitacora) {
		sessionFactory.getCurrentSession().delete(bitacora);
	}

	@Override
	public Bitacora consultarPorIdBitacora(Long id) {
		return sessionFactory.getCurrentSession().get(Bitacora.class,id);
	}

	@Override
	public List<Bitacora> consultarTodosBitacora() {
		
		String hql = "SELECT bita FROM Bitacora bita ";

		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<BitacoraHorasTrabajadas> consultarListaPorIdBitacora(Long id) {
		List<BitacoraHorasTrabajadas> listaHorasTrabajadas = new ArrayList<>();
		String sql = "select b.bita_id, id.inda_id, id.ingr_id, id.sali_id, id.observacion,i.fechaingreso, s.fechasalida,i.horaingreso,s.horasalida from bitacora b, inout_daily id, ingreso i, salida s where b.bita_id=* and b.bita_id=id.bita_id and id.ingr_id=i.ingr_id and id.sali_id=s.sali_id order by (i.fechaingreso) desc ";
		
		sql = sql.replace("*", ""+id);
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);		
		List<Object[]> listObj = query.list();
		if(listObj != null )
		{
			listaHorasTrabajadas = new ArrayList<>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm:ss");
			
			for (Object[] objects : listObj) {
				String dateString = "";
				Date dateTmp1 = new Date();
				Date dateTmp2 = new Date();
				BitacoraHorasTrabajadas bitacoraHorasTrabajadas = new BitacoraHorasTrabajadas();
				bitacoraHorasTrabajadas.setBitaId(Long.parseLong(objects[0].toString().trim()));
				bitacoraHorasTrabajadas.setIndaId(Long.parseLong(objects[1].toString().trim()));
				bitacoraHorasTrabajadas.setIngrId(Long.parseLong(objects[2].toString().trim()));
				bitacoraHorasTrabajadas.setSaliId(Long.parseLong(objects[3].toString().trim()));
				if(objects[4] == null)
				{
					bitacoraHorasTrabajadas.setObservacion(null);
				}
				else
				{
					bitacoraHorasTrabajadas.setObservacion((String)objects[4].toString().trim());
				}
						
//				dateString = "";
//				dateString = simpleDateFormat.format(objects[5]);								
				bitacoraHorasTrabajadas.setFechaingreso(simpleDateFormat.format(objects[5]));
				
//				dateString = "";
//				dateString = simpleDateFormat.format(objects[6]);				
				bitacoraHorasTrabajadas.setFechasalida(simpleDateFormat.format(objects[6]));
				
//				dateString = "";
//				dateString = objects[7].toString();
				try {
									
					dateTmp1= simpleTimeFormat.parse(objects[7].toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				bitacoraHorasTrabajadas.setHoraingreso(objects[7].toString());
				
//				dateString = "";
//				dateString = objects[8].toString();
				try {					
					dateTmp2= simpleTimeFormat.parse(objects[8].toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bitacoraHorasTrabajadas.setHorasalida(objects[8].toString());
				
				bitacoraHorasTrabajadas.setHorasTrabajadas(calculateTime(simpleTimeFormat.format(dateTmp1), simpleTimeFormat.format(dateTmp2)));
				
				listaHorasTrabajadas.add(bitacoraHorasTrabajadas);
			}
		}
		
		return listaHorasTrabajadas;		
		
	}

	
	public String calculateTime(String time1, String time2)
	{
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
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
		
		return  decimalFormat.format(diffHours);
	}
	
}
