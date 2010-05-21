/**
 * 
 */
package br.com.transport.payment;

import br.com.transport.domain.Freight;

/**
 * @author robson
 *
 */
public interface ManagerPayment{
	
	public Freight registerPaymentFreight(Long numberFreight, Double value) throws PaymentException;
}
