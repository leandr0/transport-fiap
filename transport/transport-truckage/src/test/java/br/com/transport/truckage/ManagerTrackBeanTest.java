package br.com.transport.truckage;



import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.transport.domain.Freight;
import br.com.transport.domain.TrackHistory;

/**
 * @author robson
 *
 */
public class ManagerTrackBeanTest {

	private ManagerTrackBean beanTest;
	

	private EntityManager entityManagerMock;
	
	@Before
	public void setUp() throws Exception{
		beanTest = new ManagerTrackBean();
		entityManagerMock = createMock(EntityManager.class);
	}

	@After
	public void tearDown() throws Exception{
		beanTest = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetTrackHistoryCurrentWihtNumberNull(){
		beanTest.getTrackHistoryCurrent(null);
	}
	
	@Test()
	public void testGetTrackHistoryCurrentWithFreightNotFound(){
		
		Long number = new Long(0L);
		
		expect(entityManagerMock.find(Freight.class, number)).andReturn(null);
		replay(entityManagerMock);
		
		beanTest.setEntityManager(entityManagerMock);
		
		assertNull(beanTest.getTrackHistoryCurrent(number));
	}
	
	
	@Test()
	public void testGetTrackHistoryCurrentWithFreightFoundWithhOnceTrackHistory(){
		
		// datas for test
		Long number = new Long(0L);		
		Freight freight = new Freight();
		List<TrackHistory> trackHistories = new ArrayList<TrackHistory>();
		trackHistories.add(new TrackHistory(new Date(), "Rua do teste"));
		freight.setListTrackHistory(trackHistories);
		
		
		expect(entityManagerMock.find(Freight.class, number)).andReturn(freight);
		replay(entityManagerMock);
		
		beanTest.setEntityManager(entityManagerMock);
		
		assertNotNull(beanTest.getTrackHistoryCurrent(number));
	}
	
	
	@Test()
	public void testGetTrackHistoryCurrentWithFreightFoundWithMoreLessTrackHistory(){
		
		
		
		
		
		// datas for test
		Long number = new Long(0L);		
		Freight freight = new Freight();
		List<TrackHistory> trackHistories = new ArrayList<TrackHistory>();
		
		
		Calendar c = Calendar.getInstance();		
		c.set(Calendar.HOUR, 9);				
		trackHistories.add(new TrackHistory(c.getTime(), "Rua do teste"));
		c.set(Calendar.HOUR, 10);
		trackHistories.add(new TrackHistory(c.getTime(), "Rua do teste 2"));
		c.set(Calendar.HOUR, 10);
		trackHistories.add(new TrackHistory(c.getTime(), "Rua do teste 3"));
		freight.setListTrackHistory(trackHistories);
		
		
		expect(entityManagerMock.find(Freight.class, number)).andReturn(freight);
		replay(entityManagerMock);
		
		beanTest.setEntityManager(entityManagerMock);
		
		TrackHistory trackHistory = beanTest.getTrackHistoryCurrent(number);
		
		assertNotNull(trackHistory);
		assertEquals(trackHistory.getDate(), c.getTime()); // laste date
	}	

}
