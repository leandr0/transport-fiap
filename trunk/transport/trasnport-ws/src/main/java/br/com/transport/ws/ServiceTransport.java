/**
 * 
 */
package br.com.transport.ws;

import br.com.transport.domain.Freight;

/**
 * @author leandro.goncalves
 *
 */
public interface ServiceTransport {

	public String requestAllocation(Freight freight);
	
	public Freight responseAllocation(String idMessage);
}
