/**
 * 
 */
package br.com.transport.allocation.response;

import javax.ejb.EJBException;

import br.com.transport.domain.Freight;

/**
 * @author leandro.goncalves
 *
 */
public interface ResponseAllocation {

	/**
	 * 
	 * @param idMessage {@link String}
	 * @return {@link Freight}
	 * @throws EJBException
	 */
	public Freight responseAllocation(String idMessage) throws EJBException;
}
