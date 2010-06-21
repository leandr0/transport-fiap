/**
 * 
 */
package br.com.transport.ws.convert;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.transport.domain.Carrier;
import br.com.transport.ws.vo.CarrierWS;

/**
 * @author robson
 *
 */
public class TransporWSConvertCarrierTest {


	private TransportWSConvert<CarrierWS, Carrier> transporWSConvert;
	
	@Before
	public void setUp() throws Exception {
		transporWSConvert = new TransportWSConvertCarrier<CarrierWS, Carrier>();
	}


	@After
	public void tearDown() throws Exception {
		transporWSConvert = null;
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testConvertToWSWihtCarrierWSNull() {
		transporWSConvert.convertToWS(null);
	}
	
	@Test()
	public void testConvertToWSWihtCarrierWSValid() {
		CarrierWS carrierWS =  transporWSConvert.convertToWS(new Carrier(1L,"model","2010","100",2.0));
		assertNotNull(carrierWS);
		assertEquals("model", carrierWS.getModel());
		assertEquals("2010", carrierWS.getYear());
		assertEquals("100", carrierWS.getLisencePlate());
		assertEquals(2.0, carrierWS.getCapacity(), 0.0);
	}	

	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToEntityWithEntityNull() {
		transporWSConvert.convertToEntity(null);
	}
	
	
	@Test()
	public void testConvertToEntityWihtEntityValid() {
		Carrier carrier = transporWSConvert.convertToEntity(new CarrierWS(1L,"model","2010","100",2.0));
		assertNotNull(carrier);
		assertEquals("model", carrier.getModel());
		assertEquals("2010", carrier.getYear());
		assertEquals("100", carrier.getLisencePlate());
		assertEquals( 2.0, carrier.getCapacity(), 0.0);
	}

}
