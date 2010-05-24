/**
 * 
 */
package br.com.transport.allocation.request;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.transport.allocation.AllocationLocal;
import br.com.transport.domain.Freight;


/**
 * @author leandro.goncalves
 *
 */
@MessageDriven(
		name = "requesAllocationMDB",
		activationConfig ={
				@ActivationConfigProperty(
						propertyName = "destinationType",
						propertyValue = "javax.jms.Queue"
				),
				@ActivationConfigProperty(
						propertyName = "destination",
						propertyValue = "/queue/allocationRequest"
				)		
		}
)
public class RequestAllocationMDB implements MessageListener{

	@Resource
	private MessageDrivenContext context;
	
	@EJB
	private AllocationLocal allocationLocal;
	
	private static final Log LOG = LogFactory.getLog(RequestAllocationMDB.class);
	
	@Override
	public void onMessage(Message message) {
		
		try{
		
			ObjectMessage objectMessage =  (ObjectMessage) message;
			
			String messageId = objectMessage.getStringProperty("MessageID");
			
			LOG.info("Message Processing ID : "+messageId);
			allocationLocal.processAllocation((Freight) objectMessage.getObject(),messageId);
			
			Thread.sleep(3000);
			
		}catch (Exception e) {
			context.getRollbackOnly();
		}
		
	}

}
