/**
 * 
 */
package br.com.transport.ws.convert;

import br.com.transport.domain.EntityBase;
import br.com.transport.ws.vo.WSVO;

/**
 * @author robson
 *
 */
public interface TransportWSConvert<T extends WSVO, E extends EntityBase> {

	public  T convertToWS(E e);
	
	public  E convertToEntity(T t);
}
