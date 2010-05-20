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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.transport.domain.Freight;

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

	private static final Log LOG = LogFactory.getLog(ManagerRequestAllocationBean.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.transport.allocation.RequestAllocation#requestAllocation(br.com.transport.domain.Freight)
	 */
	@Override
	public String requestAllocation(Freight freight)throws EJBException{

		Session session = null;

		try{
			LOG.info("Recebendo nova solicitacao de frete");
			freight.setStatus("NEW");
			persistFreight(freight);
			
		}catch (Exception e) {
			LOG.error("Erro ao persistir pedido de frete", e);
			throw new EJBException(e);
		}
		
		try{

			session = factory.createConnection().createSession(true, Session.AUTO_ACKNOWLEDGE);

			ObjectMessage objectMessage = session.createObjectMessage(freight);
			
			String idMessage = String.valueOf(freight.getId());

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

	private void persistFreight(Freight freight) throws EJBException{
		LOG.info("Persistindo solicitacao de frete");
		entityManager.persist(freight);
	}
}