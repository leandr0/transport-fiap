/**
 * 
 */
package br.com.transport.allocation;

import javax.ejb.EJBException;

import br.com.transport.domain.Freight;

/**
 * @author leandro.goncalves
 *
 */
public interface Allocation {

	/**
	 * 
	 * @param freight {@link Freight}
	 * @throws EJBException
	 */
	public void processAllocation(Freight freight,String idMessage)  throws EJBException;
}
