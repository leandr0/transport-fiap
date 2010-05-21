/**
 * 
 */
package br.com.transport.allocation.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.mockejb.MockContainer;
import org.mockejb.MockEjbContext;
import org.mockejb.SessionBeanDescriptor;
import org.mockejb.jndi.MockContextFactory;

import br.com.transport.allocation.AllocationLocal;
import br.com.transport.domain.Carrier;
import br.com.transport.domain.Freight;


/**
 * @author leandro.goncalves
 *
 */
public class ManagerAllocationBeanTest  {

	AllocationLocal allocationLocal = null;

	EntityManager entityManagerMock = null;

	ConnectionFactory factoryMock = null;	
	
	Queue queueMock = null;
	
	Session sessionMock = null;
	
	Query queryMock = null;
	
	/**MockEJB*/
	private Context context;
	
	private MockContainer mockContainer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
        
        
        /**EasyMock**/
		//allocationBean	 	= new ManagerAllocationBean();
		entityManagerMock	= EasyMock.createMock(EntityManager.class);
		factoryMock			= EasyMock.createMock(ConnectionFactory.class);
		queueMock			= EasyMock.createMock(Queue.class);
		sessionMock			= EasyMock.createMock(Session.class);
		queryMock			= EasyMock.createMock(Query.class);
	}

	/**
	 * Test method for {@link br.com.transport.allocation.ManagerAllocationBean#processAllocation(br.com.transport.domain.Freight, java.lang.String)}.
	 * @throws NamingException 
	 */
	@Test(expected = IllegalStateException.class)
	public void testProcessAllocation() throws NamingException {

		/**MockEJB**/
		MockContextFactory.setAsInitial();
        context = new InitialContext();		
        mockContainer = new MockContainer( context );
        
        SessionBeanDescriptor sampleBeanDescriptor = 
            new SessionBeanDescriptor( "allocation", 
            AllocationLocal.class, AllocationLocal.class, AllocationLocal.class );
        
        sampleBeanDescriptor.setStateful( false );
        
        mockContainer.deploy( sampleBeanDescriptor );

        allocationLocal = 
            (AllocationLocal)context.lookup( "allocation" );
		
		Freight freight = new Freight();
		freight.setDepartureDate(new Date());		
		
		Carrier carrier = new Carrier();
		carrier.setId(1L);
		
		freight.setCarrier(carrier);
		
		Exception exception = null;
		
		try{
			/*allocationBean.setEntityManager(entityManagerMock);
			allocationBean.setFactory(factoryMock);
			allocationBean.setQueue(queueMock);
			allocationBean.setSession(sessionMock);
			allocationBean.setQuery(queryMock);
			allocationBean.processAllocation(freight, "1");*/
			allocationLocal.processAllocation(freight, "1");
		}catch (Exception e) {
			System.err.println(e.getMessage());
			exception = e;
		}
		
		assertNotNull(exception);
	}
}
