/**
 * 
 */
package br.com.transport.report;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import br.com.transport.domain.vo.ReportVO;

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
	public List<ReportVO> executeReport() throws EJBException {

		List<ReportVO> 				resultList	= new LinkedList<ReportVO>(); 
		Map<Long, List<Calendar>> 	mapReport  	= new HashMap<Long, List<Calendar>>();
		
		List<ReportVO> handlerList = executeQuery(mapReport);
		
		removeDays(mapReport, handlerList);
		

		for(Long carrierId : mapReport.keySet()){

			for(ReportVO rVO : handlerList){
				if(rVO.getCarrierId().equals(carrierId)){
					resultList.add(new ReportVO(rVO.getDeliveryDate(),
							rVO.getDepartureDate(),
							rVO.getStatus(), 
							rVO.getCarrierId(),
							rVO.getCapacity(),
							rVO.getLicensePlate(),
							rVO.getModel(),
							getListDate(mapReport.get(carrierId))));
					break;
				}
			}
		}

		return resultList;
	}

	/**
	 * Retira da lista de dias da semana os dias utilizados no frete
	 * @param mapReport
	 * @param handlerList
	 */
	private void removeDays(Map<Long, List<Calendar>> mapReport , List<ReportVO> handlerList){
		
		Calendar init = getInitCalendar();

		Calendar end = getInitCalendar();

		for (ReportVO report : handlerList) {

			List<Calendar> daysList = new LinkedList<Calendar>( mapReport.get(report.getCarrierId()));

			init.setTime(report.getDepartureDate());
			end.setTime(report.getDeliveryDate());
			
			for(Calendar cld : daysList){				
				if(cld.compareTo(init) == 0 
						||( cld.after(init) 
								&& ( cld.before(end) ||  cld.compareTo(end) == 0 ))){
					mapReport.get(report.getCarrierId()).remove(cld);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param mapReport
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ReportVO> executeQuery(Map<Long, List<Calendar>> mapReport) {
		
		List<ReportVO> handlerList = new LinkedList<ReportVO>();
		
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

		query.setParameter("initialDate", dateFormat.format(getInitCalendar().getTime()));
		query.setParameter("lastDate", dateFormat.format(getLastCalendar().getTime()));

		List<Object[]> resultQuery = query.getResultList();

		/**
		 * departureDate	[0]
		 * deliveryDate  	[1]
		 * status        	[2]
		 * carrierId	 	[3]
		 * capacity		 	[4]
		 * licensePlate	 	[5]
		 * model		 	[6]
		 */

		DateTimeConverter timeConverter = new CalendarConverter();

		List<Calendar> calendars = createDayOfTheWeek();

		for(Object[] array : resultQuery){

			mapReport.put(new Long(array[3].toString()), new LinkedList<Calendar>(calendars));

			Calendar deliveryDate 	= (Calendar)timeConverter.convert(Calendar.class,array[1]); 
			Calendar departureDate 	= (Calendar)timeConverter.convert(Calendar.class,array[0]);

			handlerList.add(new ReportVO(departureDate.getTime(),
								deliveryDate.getTime(),
								array[2].toString(), 
								new Long(array[3].toString()),
								new Double(array[4].toString()),
								array[5].toString(),
								array[6].toString(),null));
		}
		
		return handlerList;
	}

	/**
	 * Retorna lista com os dias da semana
	 * @return {@link List}< {@link Calendar} >
	 */
	private List<Calendar> createDayOfTheWeek(){

		Calendar firstDay = GregorianCalendar.getInstance();
		firstDay.setFirstDayOfWeek(Calendar.SUNDAY);
		firstDay.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		firstDay.set(Calendar.HOUR_OF_DAY,0);
		firstDay.set(Calendar.MINUTE,0);
		firstDay.set(Calendar.SECOND,0);

		List<Calendar> daysOfTheWeek = new LinkedList<Calendar>();

		for(int count = 0; count < 7 ; count++){

			Calendar temp = (Calendar) firstDay.clone();
			temp.roll(Calendar.DAY_OF_WEEK, count);
			temp.setTime(temp.getTime());
			daysOfTheWeek.add(temp);  

		}

		return daysOfTheWeek;
	}


	/**
	 * Retorna o primeiro dia da semana
	 * @return
	 */
	private Calendar getInitCalendar(){

		Calendar init = GregorianCalendar.getInstance();
		init.setFirstDayOfWeek(Calendar.SUNDAY);
		init.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		init.set(Calendar.HOUR_OF_DAY,0);
		init.set(Calendar.MINUTE,0);
		init.set(Calendar.SECOND,0);
		init.setTime(init.getTime());

		return init;
	}

	/**
	 * Retorna o ultimo dia da semana
	 * @return
	 */
	private Calendar getLastCalendar(){

		Calendar end = GregorianCalendar.getInstance();
		end.setFirstDayOfWeek(Calendar.SUNDAY);
		end.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		end.set(Calendar.HOUR_OF_DAY,23);
		end.set(Calendar.MINUTE,59);
		end.set(Calendar.SECOND,59);
		end.setTime(end.getTime());
		
		return end;
	}

	/**
	 * 
	 * @param calendarList
	 * @return
	 */
	private List<Date> getListDate(List<Calendar> calendarList){

		List<Date> result = new LinkedList<Date>();

		for (Calendar calendar : calendarList)
			result.add(calendar.getTime());

		return result;
	}

	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}