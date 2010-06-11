/**
 * 
 */
package br.com.transport.allocation.test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJBException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import br.com.transport.allocation.ManagerAllocationBean;
import br.com.transport.domain.Carrier;
import br.com.transport.domain.Freight;


/**
 * @author leandro.goncalves
 *
 */
public class ManagerAllocationBeanTest  {

	ManagerAllocationBean allocationBeanMock = null;

	EntityManager entityManagerMock = null;

	ConnectionFactory factoryMock = null;	
	
	Queue queueMock = null;
	
	Session sessionMock = null;
	
	Query queryMock = null;
	
	Connection connectionMock = null;
	
	ObjectMessage objectMessageMock = null;

	MessageProducer producerMock = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		allocationBeanMock = new ManagerAllocationBean();
		
		entityManagerMock	= createMock(EntityManager.class);
		factoryMock			= createMock(ConnectionFactory.class);
		queueMock			= createMock(Queue.class);
		sessionMock			= createMock(Session.class);
		queryMock			= createMock(Query.class);
		connectionMock		= createMock(Connection.class);
		objectMessageMock	= createMock(ObjectMessage.class);
		producerMock		= createMock(MessageProducer.class);
	}

	/**
	 * Test method for {@link br.com.transport.allocation.ManagerAllocationBean#processAllocation(br.com.transport.domain.Freight, java.lang.String)}.
	 * @throws JMSException 
	 * @throws NamingException 
	 */
	@Test
	public void testProcessAllocation() throws JMSException{
		
		Calendar departureDate = Calendar.getInstance();
		Calendar deliveryDate  = Calendar.getInstance();
		
		departureDate.set(2010, 5, 06);
		deliveryDate.set(2010, 5, 10);

		Long idFreight = 1L;
		
		String messageID = "555";
		
		Freight freight = new Freight();
		freight.setDepartureDate(departureDate.getTime());		
		freight.setDeliveryDate(deliveryDate.getTime());
		
		Carrier carrier = new Carrier();
		carrier.setId(idFreight);
		
		freight.setCarrier(carrier);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		allocationBeanMock.setEntityManager(entityManagerMock);
		allocationBeanMock.setFactory(factoryMock);
		allocationBeanMock.setQueue(queueMock);
		
		expect(queryMock.setParameter("carrierID"		, idFreight	)).andReturn(queryMock);
		expect(queryMock.setParameter("departureDate"	, dateFormat.format(freight.getDepartureDate()) )).andReturn(queryMock);
		expect(queryMock.setParameter("deliveryDate"	, dateFormat.format(freight.getDeliveryDate()) )).andReturn(queryMock);
		expect(queryMock.getResultList()).andReturn(new LinkedList<Object>());
		
		expect(sessionMock.createObjectMessage(freight)).andReturn(objectMessageMock);
		expect(sessionMock.createProducer(queueMock)).andReturn(producerMock);
		
		expect(connectionMock.createSession(true, Session.AUTO_ACKNOWLEDGE)).andReturn(sessionMock);
		
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		
		expect(entityManagerMock.createNativeQuery("SELECT ID FROM FREIGHT "+
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
				"AND STATUS <> 'NEW'")).andReturn(queryMock);
		expect(entityManagerMock.merge(freight)).andReturn(freight);
		
		sessionMock.commit();
		sessionMock.close();
		connectionMock.close();
		
		replay(factoryMock);
		replay(entityManagerMock);
		replay(queryMock);
		replay(sessionMock);
		replay(connectionMock);
		
		allocationBeanMock.processAllocation(freight, messageID);
	}
	
	@Test
	public void testProcessAllocationListNotEmpty() throws JMSException{
		
		Calendar departureDate = Calendar.getInstance();
		Calendar deliveryDate  = Calendar.getInstance();
		
		departureDate.set(2010, 5, 06);
		deliveryDate.set(2010, 5, 10);

		Long idFreight = 1L;
		
		String messageID = "555";
		
		Freight freight = new Freight();
		freight.setDepartureDate(departureDate.getTime());		
		freight.setDeliveryDate(deliveryDate.getTime());
		
		Carrier carrier = new Carrier();
		carrier.setId(idFreight);
		
		freight.setCarrier(carrier);
		
		List<Object> list = new LinkedList<Object>();
		list.add("Teste");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		allocationBeanMock.setEntityManager(entityManagerMock);
		allocationBeanMock.setFactory(factoryMock);
		allocationBeanMock.setQueue(queueMock);
		
		expect(queryMock.setParameter("carrierID"		, idFreight	)).andReturn(queryMock);
		expect(queryMock.setParameter("departureDate"	, dateFormat.format(freight.getDepartureDate()) )).andReturn(queryMock);
		expect(queryMock.setParameter("deliveryDate"	, dateFormat.format(freight.getDeliveryDate()) )).andReturn(queryMock);
		expect(queryMock.getResultList()).andReturn(list);
		
		expect(sessionMock.createObjectMessage(freight)).andReturn(objectMessageMock);
		expect(sessionMock.createProducer(queueMock)).andReturn(producerMock);
		
		expect(connectionMock.createSession(true, Session.AUTO_ACKNOWLEDGE)).andReturn(sessionMock);
		
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		
		expect(entityManagerMock.createNativeQuery("SELECT ID FROM FREIGHT "+
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
				"AND STATUS <> 'NEW'")).andReturn(queryMock);
		expect(entityManagerMock.merge(freight)).andReturn(freight);
		
		sessionMock.commit();
		sessionMock.close();
		connectionMock.close();
		
		replay(factoryMock);
		replay(entityManagerMock);
		replay(queryMock);
		replay(sessionMock);
		replay(connectionMock);
		
		allocationBeanMock.processAllocation(freight, messageID);
	}
	
	@Test( expected = EJBException.class )
	public void testProcessAllocationFreightNull() throws JMSException{
		
		Calendar departureDate = Calendar.getInstance();
		Calendar deliveryDate  = Calendar.getInstance();
		
		departureDate.set(2010, 5, 06);
		deliveryDate.set(2010, 5, 10);

		Long idFreight = 1L;
		
		String messageID = "555";
		
		Freight freight = new Freight();
		freight.setDepartureDate(departureDate.getTime());		
		freight.setDeliveryDate(deliveryDate.getTime());
		
		Carrier carrier = new Carrier();
		carrier.setId(idFreight);
		
		freight.setCarrier(carrier);
		
		List<Object> list = new LinkedList<Object>();
		list.add("Teste");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		allocationBeanMock.setEntityManager(entityManagerMock);
		allocationBeanMock.setFactory(factoryMock);
		allocationBeanMock.setQueue(queueMock);
		
		expect(queryMock.setParameter("carrierID"		, idFreight	)).andReturn(queryMock);
		expect(queryMock.setParameter("departureDate"	, dateFormat.format(freight.getDepartureDate()) )).andReturn(queryMock);
		expect(queryMock.setParameter("deliveryDate"	, dateFormat.format(freight.getDeliveryDate()) )).andReturn(queryMock);
		expect(queryMock.getResultList()).andReturn(list);
		
		expect(sessionMock.createObjectMessage(freight)).andReturn(objectMessageMock);
		expect(sessionMock.createProducer(queueMock)).andReturn(producerMock);
		
		expect(connectionMock.createSession(true, Session.AUTO_ACKNOWLEDGE)).andReturn(sessionMock);
		
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		
		expect(entityManagerMock.createNativeQuery("SELECT ID FROM FREIGHT "+
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
				"AND STATUS <> 'NEW'")).andReturn(queryMock);
		expect(entityManagerMock.merge(freight)).andReturn(freight);
		
		sessionMock.commit();
		sessionMock.close();
		connectionMock.close();
		
		replay(factoryMock);
		replay(entityManagerMock);
		replay(queryMock);
		replay(sessionMock);
		replay(connectionMock);
		
		allocationBeanMock.processAllocation(null, messageID);
	}
	
	@Test( expected = EJBException.class )
	public void testProcessAllocationIdMessageNull() throws JMSException{
		
		Calendar departureDate = Calendar.getInstance();
		Calendar deliveryDate  = Calendar.getInstance();
		
		departureDate.set(2010, 5, 06);
		deliveryDate.set(2010, 5, 10);

		Long idFreight = 1L;
		
		Freight freight = new Freight();
		freight.setDepartureDate(departureDate.getTime());		
		freight.setDeliveryDate(deliveryDate.getTime());
		
		Carrier carrier = new Carrier();
		carrier.setId(idFreight);
		
		freight.setCarrier(carrier);
		
		List<Object> list = new LinkedList<Object>();
		list.add("Teste");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		allocationBeanMock.setEntityManager(entityManagerMock);
		allocationBeanMock.setFactory(factoryMock);
		allocationBeanMock.setQueue(queueMock);
		
		expect(queryMock.setParameter("carrierID"		, idFreight	)).andReturn(queryMock);
		expect(queryMock.setParameter("departureDate"	, dateFormat.format(freight.getDepartureDate()) )).andReturn(queryMock);
		expect(queryMock.setParameter("deliveryDate"	, dateFormat.format(freight.getDeliveryDate()) )).andReturn(queryMock);
		expect(queryMock.getResultList()).andReturn(list);
		
		expect(sessionMock.createObjectMessage(freight)).andReturn(objectMessageMock);
		expect(sessionMock.createProducer(queueMock)).andReturn(producerMock);
		
		expect(connectionMock.createSession(true, Session.AUTO_ACKNOWLEDGE)).andReturn(sessionMock);
		
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		expect(factoryMock.createConnection()).andReturn(connectionMock);
		
		expect(entityManagerMock.createNativeQuery("SELECT ID FROM FREIGHT "+
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
				"AND STATUS <> 'NEW'")).andReturn(queryMock);
		expect(entityManagerMock.merge(freight)).andReturn(freight);
		
		sessionMock.commit();
		sessionMock.close();
		connectionMock.close();
		
		replay(factoryMock);
		replay(entityManagerMock);
		replay(queryMock);
		replay(sessionMock);
		replay(connectionMock);
		
		allocationBeanMock.processAllocation(freight, null);
	}
}
