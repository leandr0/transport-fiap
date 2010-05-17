/**
 * 
 */
package br.com.transport.allocation.request;

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

import br.com.transport.domain.Freight;

/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "requestAllocation")
@Local(RequestAllocationLocal.class)
@Remote(RequestAllocationRemote.class)
public class ManagerRequestAllocationBean implements RequestAllocationLocal, RequestAllocationRemote{

	@Resource(mappedName = "ConnectionFactory")
	private ConnectionFactory factory;
	
	@Resource(mappedName = "/queue/allocationRequest")	
	private Queue queue;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.transport.allocation.RequestAllocation#requestAllocation(br.com.transport.domain.Freight)
	 */
	@Override
	public String requestAllocation(Freight freight)throws EJBException{
		 
		 Session session = null;
		 
		 try{
			 
			session = factory.createConnection().createSession(true, Session.AUTO_ACKNOWLEDGE);

			ObjectMessage objectMessage = session.createObjectMessage(freight);
			
			String idMessage = String.valueOf(objectMessage.getJMSTimestamp());
			
			//Atributo que servira de parametro para o filtro de mensagens
			objectMessage.setStringProperty("MessageID", idMessage);
			
			session.createProducer(queue).send(objectMessage,DeliveryMode.NON_PERSISTENT,9,0);
			
			session.commit();
			session.close();
			
			return idMessage;
			
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
}