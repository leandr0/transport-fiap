package br.com.transport.ws.convert;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.transport.domain.TrackHistory;
import br.com.transport.ws.vo.TrackHistoryWS;

public class TransportWSConvertTrackHistoryWSTest {

	private TransportWSConvert<TrackHistoryWS, TrackHistory> convert;
	
	@Before
	public void setUp() throws Exception {
		convert = new TransportWSConvertTrackHistoryWS<TrackHistoryWS, TrackHistory>();
	}

	@After
	public void tearDown() throws Exception{
		convert = null;
	}

	
/*	@Test(expected=IllegalArgumentException.class)
	public void testConvertToEntityWihtTrackNull() {
		convert.convertToEntity(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConvertToEntityWihtTrackValid() {		
		Date dateTest = new Date();		
		TrackHistory trackHistory = 
			convert.convertToEntity(new TrackHistoryWS(DateFormatUtils.format(dateTest, "dd/MM/yyyy"), "Rua do Teste 123"));
		assertNotNull(trackHistory);
		
	}	*/
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToWSWihtEntityNull() {
		convert.convertToWS(null);
	}
	
	
	@Test()
	public void testConvertToWSWihtEntityValid() {
		
		Date dateTest = new Date();
		
		TrackHistoryWS trackHistoryWS = convert.convertToWS(new TrackHistory(dateTest, "Rua do Teste 123"));
		assertNotNull(trackHistoryWS);
		
		assertEquals(DateFormatUtils.format(dateTest, "dd/MM/yyyy"), trackHistoryWS.getDate());
		assertEquals("Rua do Teste 123", trackHistoryWS.getLocal());
	}	

}
