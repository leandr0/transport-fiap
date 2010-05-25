/**
 * 
 */
package br.com.transport.allocation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.transport.domain.Freight;
import br.com.transport.domain.FreightStatus;



/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "allocation")
@Local(AllocationLocal.class)
@Remote(AllocationRemote.class)
public class ManagerAllocationBean implements AllocationLocal, AllocationRemote{

	@PersistenceContext(unitName = "persistence-allocation")
	private EntityManager entityManager;

	@Resource(mappedName = "ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(mappedName = "/queue/allocationResponse")	
	private Queue queue;

	private Session session = null;
	
	private Query query = null;
	
	private static final Log LOG = LogFactory.getLog(ManagerAllocationBean.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.transport.allocation.Allocation#processAllocation(br.com.transport.domain.Freight)
	 */
	@Override
	public void processAllocation(Freight freight,String idMessage) throws EJBException {
		
		try{
			
			Connection connection = factory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			
			LOG.info("Update Freigh");
			persistFreight(freight);
			LOG.info("Status Freigh : "+freight.getStatus());
			
			ObjectMessage objectMessage = session.createObjectMessage(freight);
			objectMessage.setStringProperty("MessageID",idMessage);
			
			LOG.info("Seng messade ID : "+idMessage);
			MessageProducer producer = session.createProducer(queue); 
			producer.send(objectMessage,DeliveryMode.NON_PERSISTENT,9,0);

			session.commit();
			producer.close();
			session.close();
			connection.close();
			
		}catch (Exception e) {
			throw new EJBException(e);
		}
	}

	/**
	 * 
	 * @param freight
	 * @throws EJBException
	 */
	private void persistFreight(Freight freight) throws EJBException{
		
		boolean approved = dateIsValid(freight.getCarrier().getId(), 
								freight.getDepartureDate(),
								freight.getDeliveryDate()); 
		
		if(approved){
			freight.setStatus(FreightStatus.ACCEPTED);
		}else
			freight.setStatus(FreightStatus.REJECTED);
		
		entityManager.merge(freight);
	}
	
	/**
	 * 
	 * @param carrierID
	 * @param departureDate
	 * @return
	 * @throws EJBException
	 */
	private boolean dateIsValid(Long carrierID, Date departureDate, Date deliveryDate )throws EJBException{
	
		if(query == null){
			query = entityManager.createNativeQuery("SELECT ID FROM FREIGHT "+
										"WHERE CARRIER_ID = :carrierID "+
										"AND ( " +
										"		:departureDate "+
										"			BETWEEN DEPARTURE_DATE "+
										"			AND DELIVERY_DATE "+
										"		OR "+
										"		:deliveryDate "+
										"			BETWEEN DEPARTURE_DATE "+
										"			AND DELIVERY_DATE "+
										"	 ) "+
										"AND STATUS <> 'REJECTED' "+
										"AND STATUS <> 'NEW'");
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		query.setParameter("carrierID"		, carrierID	);
		query.setParameter("departureDate"	, dateFormat.format(departureDate));
		query.setParameter("deliveryDate"	, dateFormat.format(deliveryDate));
		
		List<?> result =  query.getResultList();
		
		if(result != null && !result.isEmpty())
			return false;
		
		return true;
	}
}