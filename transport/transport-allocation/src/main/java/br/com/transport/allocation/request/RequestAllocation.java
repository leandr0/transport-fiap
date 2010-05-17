/**
 * 
 */
package br.com.transport.allocation.request;

import javax.ejb.EJBException;

import br.com.transport.domain.Freight;

/**
 * @author leandro.goncalves
 *
 */
public interface RequestAllocation {

	/**
	 * 
	 * @param freight {@link Freight}
	 * @return messageID
	 * @throws EJBException
	 */
	public String requestAllocation(Freight freight) throws EJBException;
	
}
