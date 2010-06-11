/**
 * 
 */
package br.com.transport.truckage;

import br.com.transport.domain.TrackHistory;

/**
 * @author robson
 *
 */
public interface ManagerTrack {

	public TrackHistory getTrackHistoryCurrent( Long numberFreight );
}
