/**
 * 
 */
package br.com.transport.report;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "report")
@Local(ReportLocal.class)
@Remote(ReportRemote.class)
public class ManagerReportBean implements ReportLocal, ReportRemote {

	@PersistenceContext(unitName = "persistence-report")
	private EntityManager entityManager;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/*
	 * (non-Javadoc)
	 * @see br.com.transport.report.Report#executeReport()
	 */
	@SuppressWarnings("unchecked")
	public List<?> executeReport() throws EJBException {



		Query query = entityManager
						.createNativeQuery("SELECT F.DEPARTURE_DATE,F.DELIVERY_DATE,F.STATUS, "+
								"C.ID,C.CAPACITY,C.LICENSE_PLATE, C.MODEL "+
								"FROM CARRIER C "+
								"INNER JOIN FREIGHT F "+
								"ON C.ID = F.CARRIER_ID "+
								"WHERE F.STATUS IN ('ACCEPTED' ,'IN_PROGRESS') "+
								"AND F.DEPARTURE_DATE "+
								"BETWEEN :initialDate "+
								"AND :lastDate "+
								"ORDER BY C.ID ASC");

		query.setParameter("initialDate", getInitialDate());
		query.setParameter("lastDate", getLastDate());

		List<Object[]> a = query.getResultList();

		/**
		 * departureDate	[0]
		 * deliveryDate  	[1]
		 * status        	[2]
		 * carrierId	 	[3]
		 * capacity		 	[4]
		 * licensePlate	 	[5]
		 * model		 	[6]
		 */

		BigInteger idcarrier = null;
		Calendar deliveryDate = null;
		
		DateTimeConverter timeConverter = new CalendarConverter();
		
		for(Object[] array : a){

			if(idcarrier == null){
				idcarrier = (BigInteger) array[3];
				deliveryDate = (Calendar) timeConverter.convert(Calendar.class, array[1]); 
					//(Calendar) array[1];
				deliveryDate.roll(Calendar.DAY_OF_MONTH, 1);

			}else if(idcarrier.equals((BigInteger) array[3])){
				
				Calendar cal = (Calendar)timeConverter.convert(Calendar.class,array[0]); 
				cal.roll(Calendar.DAY_OF_MONTH, -1);
				
				cal.set(Calendar.HOUR_OF_DAY,0);
				cal.set(Calendar.MINUTE,0);
				cal.set(Calendar.SECOND,0);

				deliveryDate.set(Calendar.HOUR_OF_DAY,0);
				deliveryDate.set(Calendar.MINUTE,0);
				deliveryDate.set(Calendar.SECOND,0);

				if(deliveryDate.compareTo(cal) <= 0){
					
					System.out.print("Truck : ");
					System.out.print(array[3]);
					System.out.print(" Capacity : ");
					System.out.print(array[4]);
					System.out.print(" License Plate : ");
					System.out.print(array[5]);
					System.out.print(" Model : ");
					System.out.print(array[6]);
					System.out.println("");
					System.out.print("Initial Date : ");
					System.out.print(deliveryDate.getTime());
					System.out.print("Initial Date : ");
					System.out.println(cal.getTime());
					
					deliveryDate = cal;
				}
				
			}else{
				idcarrier = (BigInteger) array[3];
				deliveryDate = (Calendar) timeConverter.convert(Calendar.class,array[1]);
				deliveryDate.roll(Calendar.DAY_OF_MONTH, 1);
			}


		}

		return null;
	}

	private void query(){

	}

	private String getInitialDate(){

		Calendar init = GregorianCalendar.getInstance();
		init.setFirstDayOfWeek(Calendar.SUNDAY);
		init.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		init.set(Calendar.HOUR_OF_DAY,0);
		init.set(Calendar.MINUTE,0);
		init.set(Calendar.SECOND,0);


		return dateFormat.format(init.getTime());
	}

	private String getLastDate(){

		Calendar end = GregorianCalendar.getInstance();
		end.setFirstDayOfWeek(Calendar.SUNDAY);
		end.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		end.set(Calendar.HOUR_OF_DAY,23);
		end.set(Calendar.MINUTE,59);
		end.set(Calendar.SECOND,59);

		return dateFormat.format(end.getTime());
	}
}
