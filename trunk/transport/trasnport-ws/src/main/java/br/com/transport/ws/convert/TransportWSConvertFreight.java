package br.com.transport.ws.convert;

import org.apache.commons.lang.time.DateFormatUtils;

import br.com.transport.domain.Freight;
import br.com.transport.ws.vo.FreightWS;
import br.com.transport.ws.vo.PaymentWs;

public class TransportWSConvertFreight<T extends FreightWS, E extends Freight> implements TransportWSConvert<T, E> {

	@Override
	public E convertToEntity(T t){
		return null;
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	public T convertToWS(E e){
		if(e == null)
			throw new IllegalArgumentException("e is null");
		return (T) new FreightWS(
					e.getId(),
					e.getStatus().name(),
					new PaymentWs(
						DateFormatUtils.format(e.getPayment().getPaymentDate(), "dd/MM/yyyy"), 
						e.getPayment().getValue(), 
						e.getPayment().getPaymentStatus().name()
					)
				);
	}

}
