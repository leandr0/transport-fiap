package br.com.transport.ws.convert;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.transport.domain.Freight;
import br.com.transport.ws.vo.FreightWS;

public class TransportWSConvertFreightTest {

	
	private TransportWSConvert<FreightWS, Freight> convert;
	
	@Before
	public void setUp() throws Exception {
		convert = new TransportWSConvertFreight<FreightWS, Freight>();
	}

	@After
	public void tearDown() throws Exception {
		convert = null;
	}

	@Test
	public void testConvertToEntity() {
		/*fail("Not yet implemented");*/
	}

	
	@Test
	public void testConvertToWS() {
		fail("Not yet implemented");
	}

}
