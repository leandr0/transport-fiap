package br.com.transport.ws.convert;

import br.com.transport.domain.Freight;
import br.com.transport.ws.vo.FreightWS;

public class TransportWSConvertFreight<T extends FreightWS, E extends Freight> implements TransportWSConvert<T, E> {

	@Override
	public E convertToEntity(T t){
		return null;
	}

	
	@Override
	public T convertToWS(E e){
		return null;
	}

}
