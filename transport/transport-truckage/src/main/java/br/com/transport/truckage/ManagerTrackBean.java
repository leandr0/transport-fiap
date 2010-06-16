/**
 * 
 */
package br.com.transport.truckage;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.transport.domain.Freight;
import br.com.transport.domain.TrackHistory;

/**
 * @author robson
 *
 */
@Stateless(name = "track")
@Local(value = ManagerTrackLocal.class)
@Remote(value = ManagerTrackRemote.class)
public class ManagerTrackBean implements ManagerTrackLocal, ManagerTrackRemote {

	@PersistenceContext(unitName="persistence-truckage")
	private EntityManager entityManager;
	
	@Override
	public TrackHistory getTrackHistoryCurrent(Long numberFreight) {
		if(numberFreight == null)
			throw new IllegalArgumentException("number freight is null");
		
		Freight freight = entityManager.find(Freight.class, numberFreight);
		
		if( freight == null )
			return null;
		
		List<TrackHistory> trackHistories = freight.getListTrackHistory();
		
		if(trackHistories.size() == 1)
			return trackHistories.get(0);
		else
			return getLastTrackHistory(trackHistories);
	}

	
	
	private TrackHistory getLastTrackHistory(List<TrackHistory> trackHistories){
		
		TrackHistory lastTrackHistory = trackHistories.get(0);
		
		for (TrackHistory trackHistory : trackHistories)
				if(trackHistory.getDate().after(lastTrackHistory.getDate()))
					lastTrackHistory = trackHistory;
		
		return lastTrackHistory;
	}

	
	
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
