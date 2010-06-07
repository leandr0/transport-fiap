/**
 * 
 */
package br.com.transport.allocation.response;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.transport.domain.Freight;

/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "responseAllocation")
@Local(ResponseAllocationLocal.class)
@Remote(ResponseAllocationRemote.class)
public class ManagerResponseAllocationBean implements ResponseAllocationLocal , ResponseAllocationRemote{


	@Resource(mappedName = "ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(mappedName = "/queue/allocationResponse")	
	private Queue queue;

	private static final Log LOG = LogFactory.getLog(ManagerResponseAllocationBean.class);
	
	/* 
	 * (non-Javadoc)
	 * @see br.com.transport.allocation.response.ResponseAllocation#responseAllocation(java.lang.String)
	 */
	@Override
	public Freight responseAllocation(String idMessage) throws EJBException {
		
		Session 	session 	= null;
		Connection 	connection 	= null;
		
		try{
			
			String querySelector = "MessageID = '"+idMessage+"'";
			
			connection = factory.createConnection();
			connection.start();
			
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			LOG.info("Consumer by : "+querySelector);
			MessageConsumer consumer = session.createConsumer(queue,querySelector);
			
			ObjectMessage objectMessage = (ObjectMessage) consumer.receive(1000);
			
			consumer.close();
			session.commit();
			session.close();
			connection.close();
			
			if(objectMessage != null){
				LOG.info("Return Freight by messageID : "+idMessage);
				return ((Freight)objectMessage.getObject());
			}
			LOG.info("Freight by messageID : "+idMessage+" not found");
			return null;

		}catch (Exception e) {
			throw new EJBException(e);
		}
	}
}