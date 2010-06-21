package br.com.transport.ws.convert;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import br.com.transport.domain.Address;
import br.com.transport.domain.Carrier;
import br.com.transport.domain.Freight;
import br.com.transport.ws.vo.FreightWS;
import br.com.transport.ws.vo.PaymentWs;

/**
 * 
 * @author leandro.goncalves
 * 
 */
public class TransportWSConvertFreight<T extends FreightWS, E extends Freight> implements TransportWSConvert<T, E> {

	@SuppressWarnings("unchecked")
	@Override
	public E convertToEntity(T t){
		if(t == null)
			throw new IllegalArgumentException("Freight is null");
		
		Freight freight = new Freight();
		
		Carrier carrier = new Carrier();
		carrier.setCapacity(t.getCarrier().getCapacity());
		carrier.setLisencePlate(t.getCarrier().getLisencePlate());
		carrier.setModel(t.getCarrier().getModel());
		carrier.setYear(t.getCarrier().getYear());
		carrier.setId(t.getCarrier().getId());
		
		Address deliveryAddress = new Address();
		deliveryAddress.setState(t.getDeliveryAddress().getState());
		deliveryAddress.setCity(t.getDeliveryAddress().getCity());
		deliveryAddress.setAddress(t.getDeliveryAddress().getAddress());
		
		Address departureAddress = new Address();
		departureAddress.setState(t.getDepartureAddress().getState());
		departureAddress.setCity(t.getDepartureAddress().getCity()); 
		departureAddress.setAddress(t.getDepartureAddress().getAddress());
		
		
		freight.setCarrier(carrier);
		freight.setDeliveryAddress(deliveryAddress);
		freight.setDepartureAddress(departureAddress);
		
		String[] pattern = {"yyyy-MM-dd"};
		
		try {
			freight.setDeliveryDate(DateUtils.parseDate(t.getDeliveryDate(),pattern ));
			freight.setDepartureDate(DateUtils.parseDate(t.getDepartureDate(),pattern ));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid Date");
		}
		
		return (E) freight;
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
						getPaymentDate(e.getPayment().getPaymentDate()), 
						e.getPayment().getValue(), 
						e.getPayment().getPaymentStatus().name()
					)
				);
	}

	private String getPaymentDate(Date paymentDate){
		String date = null;
		
		if(paymentDate != null)
			date = DateFormatUtils.format(paymentDate, "dd/MM/yyyy");
		
		return date;
	}
}
