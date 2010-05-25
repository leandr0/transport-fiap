/**
 * 
 */
package br.com.transport.allocation.request;

import java.util.Calendar;
import java.util.Random;

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

import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.transport.domain.Freight;
import br.com.transport.domain.FreightStatus;
import br.com.transport.domain.Payment;
import br.com.transport.domain.PaymentStatus;

/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "requestAllocation")
@Local(RequestAllocationLocal.class)
@Remote(RequestAllocationRemote.class)
public class ManagerRequestAllocationBean implements RequestAllocationLocal, RequestAllocationRemote{

	@PersistenceContext(unitName = "persistence-allocation")
	private EntityManager entityManager;

	@Resource(mappedName = "ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(mappedName = "/queue/allocationRequest")	
	private Queue queue;

	private Session session;
	
	private MessageProducer producer;
	
	private static final Log LOG = LogFactory.getLog(ManagerRequestAllocationBean.class);

	/*
	 * (non-Javadoc)
	 * @see br.com.transport.allocation.RequestAllocation#requestAllocation(br.com.transport.domain.Freight)
	 */
	@Override
	public String requestAllocation(Freight freight)throws EJBException{

		try{
			LOG.info("Recebendo nova solicitacao de frete");
			freight.setStatus(FreightStatus.NEW);
			persistFreight(freight);

		}catch (Exception e) {
			LOG.error("Erro ao persistir pedido de frete", e);
			throw new EJBException(e);
		}

		try{
			Connection connection = factory.createConnection(); 
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			ObjectMessage objectMessage = session.createObjectMessage(freight);

			String idMessage = String.valueOf(freight.getId());

			//Atributo que servira de parametro para o filtro de mensagens
			objectMessage.setStringProperty("MessageID", idMessage);
			LOG.info("Send MessageID : "+idMessage);
			producer = session.createProducer(queue);
			producer.send(objectMessage,DeliveryMode.NON_PERSISTENT,9,0);
			
			session.commit();
			producer.close();
			session.close();
			connection.close();
			return idMessage;

		}catch (Exception e) {
			throw new EJBException(e);
		} 
	}	

	private void persistFreight(Freight freight) throws EJBException{
		LOG.info("Persistindo solicitacao de frete");
		Payment payment = new Payment();
		payment.setPaymentStatus(PaymentStatus.TO_PAY);
		payment.setValue(randomValue());
		freight.setPayment(payment);
		resetDate(freight);
		entityManager.persist(freight);
	}

	private Double randomValue(){

		Random random = new Random();

		Integer result = random.nextInt(3000);

		if(result <= 100)
			randomValue();

		return result.doubleValue();

	}

	private void resetDate(Freight freight){
		
		DateTimeConverter timeConverter = new CalendarConverter();

		Calendar departure = (Calendar) timeConverter.convert(Calendar.class, freight.getDepartureDate());
		departure.set(Calendar.HOUR_OF_DAY,0);
		departure.set(Calendar.MINUTE,0);
		departure.set(Calendar.SECOND,0);
		
		Calendar delivery = (Calendar) timeConverter.convert(Calendar.class, freight.getDeliveryDate());
		delivery.set(Calendar.HOUR_OF_DAY,23);
		delivery.set(Calendar.MINUTE,59);
		delivery.set(Calendar.SECOND,59);
		
		freight.setDepartureDate(departure.getTime());
		freight.setDeliveryDate(delivery.getTime());
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setFactory(ConnectionFactory factory) {
		this.factory = factory;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}
}