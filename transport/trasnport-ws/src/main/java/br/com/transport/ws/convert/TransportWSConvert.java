/**
 * 
 */
package br.com.transport.ws.convert;

import java.io.Serializable;

import br.com.transport.ws.vo.WSVO;

/**
 * @author robson
 * @author leandro.goncalves
 *
 */
public interface TransportWSConvert<T extends WSVO, E extends Serializable> {

	public  T convertToWS(E e);
	
	public  E convertToEntity(T t);
}
