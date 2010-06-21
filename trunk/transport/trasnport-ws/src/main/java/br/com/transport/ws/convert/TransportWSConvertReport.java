/**
 * 
 */
package br.com.transport.ws.convert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.transport.domain.vo.ReportVO;
import br.com.transport.ws.vo.ReportVOWS;

/**
 * @author leandro.goncalves
 *
 */
public class TransportWSConvertReport<T extends ReportVOWS, E extends ReportVO> 
									implements TransportWSConvert<T, E> {

	@Override
	public E convertToEntity(T t) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T convertToWS(E e) {
		if(e == null){
			
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return (T) new ReportVOWS(sdf.format(e.getDepartureDate()),
				sdf.format(e.getDeliveryDate()),
				e.getStatus(),
				e.getCarrierId(),
				e.getCapacity(),
				e.getLicensePlate(),
				e.getModel(),
				convertFreeDays( e.getFreeDays()));
		
	}

	/**
	 * 
	 * @param dates
	 * @return
	 */
	private String[] convertFreeDays(List<Date> dates){
		
		if(dates == null || dates.isEmpty())
			return null;
		
		String[]	result	= new String[dates.size()];
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		int i = 0;
		for (Date dt : dates ){
			 result[i] = sdf.format(dt);
			 i++;
		}
		
		return result;
	}	
}