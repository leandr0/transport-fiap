/**
 * 
 */
package br.com.transport.payment;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.transport.domain.Freight;
import br.com.transport.domain.PaymentStatus;

/**
 * @author robson
 *
 */
@Stateless
@Local(value = ManagerPaymentLocal.class)
@Remote(value = ManagerPaymentRemote.class)
public class ManagerPaymentBean implements ManagerPaymentLocal, ManagerPaymentRemote {
	
	@PersistenceContext(unitName="persistence-payment")
	private EntityManager entityManager;
	
	
	@Override
	public Freight registerPaymentFreight(Long numberFreight, Double value) throws PaymentException{
		if (numberFreight == null || value == null) 
			throw new IllegalArgumentException("numberFreight or value is null");
		
		Freight freight = entityManager.find(Freight.class, numberFreight);
		
		if(freight == null )
			throw new PaymentException("Freigh not found");
		
		if(freight.getPayment().getValue().compareTo(value) != 0)
			throw new PaymentException("value less or greater which value of payment");
		
		freight.getPayment().setPaymentStatus(PaymentStatus.PAID);
		freight.getPayment().setPaymentDate(new Date());
		
		return entityManager.merge(freight);
	}

		
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
