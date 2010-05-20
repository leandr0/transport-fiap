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
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.transport.domain.Freight;



/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "allocation")
@Local(AllocationLocal.class)
@Remote(AllocationRemote.class)
public class ManagerAllocationBean implements AllocationLocal, AllocationRemote {

	@PersistenceContext(unitName = "persistence-allocation")
	private EntityManager entityManager;

	@Resource(mappedName = "ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(mappedName = "/queue/allocationResponse")	
	private Queue queue;

	/*
	 * (non-Javadoc)
	 * @see br.com.transport.allocation.Allocation#processAllocation(br.com.transport.domain.Freight)
	 */
	@Override
	public void processAllocation(Freight freight,String idMessage) throws EJBException {
		Session session = null;

		try{
			
			session = factory.createConnection().createSession(true, Session.AUTO_ACKNOWLEDGE);
			
			persistFreight(freight);
			
			ObjectMessage objectMessage = session.createObjectMessage(freight);
			objectMessage.setStringProperty("MessageID",idMessage);
			session.createProducer(queue).send(objectMessage,DeliveryMode.NON_PERSISTENT,9,0);

			session.commit();
			session.close();
			
		}catch (Exception e) {

			if(session != null ){

				try{
					session.rollback();
				}catch (Exception ex) {
					ex.getCause();
				}
			}

			throw new EJBException(e);
		}
	}

	/**
	 * 
	 * @param freight
	 * @throws EJBException
	 */
	private void persistFreight(Freight freight) throws EJBException{
		
		if(dateIsValid(freight.getCarrier().getId(), freight.getDepartureDate())){
			freight.setStatus("ACCEPTED");
		}else
			freight.setStatus("REJECTED");
		
		entityManager.merge(freight);
	}
	
	/**
	 * 
	 * @param carrierID
	 * @param departureDate
	 * @return
	 * @throws EJBException
	 */
	private boolean dateIsValid(Long carrierID, Date departureDate )throws EJBException{
	
		Query query = entityManager.createNativeQuery("SELECT ID FROM FREIGHT "+
										"WHERE CARRIER_ID = :carrierID "+
										"AND :departureDate "+
										"BETWEEN DEPARTURE_DATE "+
										"AND DELIVERY_DATE "+
										"AND STATUS <> 'REJECTED' "+
										"AND STATUS <> 'NEW'");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		query.setParameter("carrierID"		, carrierID	);
		query.setParameter("departureDate"	, dateFormat.format(departureDate));
		
		List<?> result =  query.getResultList();
		
		if(result != null && !result.isEmpty())
			return false;
		
		return true;
	}
}