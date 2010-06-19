/**
 * 
 */
package br.com.transport.ws.convert;

import br.com.transport.domain.Carrier;
import br.com.transport.ws.vo.CarrierWS;

/**
 * @author robson
 *
 */
public class TransportWSConvertCarrier<T extends CarrierWS, E extends Carrier> implements TransportWSConvert<T, E> {

	
	@Override
	@SuppressWarnings("unchecked")
	public E convertToEntity(T t) {
		if(t == null)
			throw new IllegalArgumentException("T is null");
		return  (E) new Carrier(
					t.getModel(),
					t.getYear(),
					t.getLisencePlate(),
					t.getCapacity()
				);
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	public T convertToWS(E e){
		if( e == null)
			throw new IllegalArgumentException("E is null");
		return (T) new CarrierWS(
					e.getModel(),
					e.getYear(),
					e.getLisencePlate(),
					e.getCapacity()
				);
	}

}

