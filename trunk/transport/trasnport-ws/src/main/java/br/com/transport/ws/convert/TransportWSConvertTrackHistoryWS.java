/**
 * 
 */
package br.com.transport.ws.convert;

import org.apache.commons.lang.time.DateFormatUtils;

import br.com.transport.domain.TrackHistory;
import br.com.transport.ws.vo.TrackHistoryWS;

/**
 * @author robson
 *
 */
public class TransportWSConvertTrackHistoryWS<T extends TrackHistoryWS , E extends TrackHistory> implements TransportWSConvert<T, E> {

	
	@Override
	public E convertToEntity(T t){
		return null;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public T convertToWS(E e) {
		if(e == null)
			throw new IllegalArgumentException("e is null");
		return (T) new TrackHistoryWS(
					DateFormatUtils.format(e.getDate(), "dd/MM/yyyy"),
					e.getLocal()
				);
	}

}
