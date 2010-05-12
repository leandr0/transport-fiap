/**
 * 
 */
package br.com.transport.payment;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "payment")
@Local(PaymentLocal.class)
public class ManagerPayment implements PaymentLocal {

	@PersistenceContext(unitName = "persistence-payment")
	private EntityManager entityManager;

}
