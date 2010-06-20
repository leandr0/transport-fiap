package br.com.transport.ws.convert;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.transport.domain.Freight;
import br.com.transport.domain.FreightStatus;
import br.com.transport.domain.Payment;
import br.com.transport.domain.PaymentStatus;
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

	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToWSWihtEntityNull() {
		convert.convertToWS(null);
	}
	
	@Test()
	public void testConvertToWSWihtEntityValid() {
		
		Date dateTest = new Date();
		
		Freight freight = new Freight();
		freight.setId(1L);
		freight.setStatus(FreightStatus.COMPLETED);
		freight.setPayment( new Payment(dateTest, 2.0, PaymentStatus.PAID));
		
		FreightWS freightWS = convert.convertToWS(freight);
		
		assertNotNull(freightWS);
		assertEquals(1L, freightWS.getId());
		assertEquals("COMPLETED", freightWS.getStatus());
		assertEquals(DateFormatUtils.format(dateTest, "dd/MM/yyyy"), freightWS.getPaymentWs().getPaymentDate());
		assertEquals(2.0, freightWS.getPaymentWs().getValue(), 0.0);
		assertEquals("PAID", freightWS.getPaymentWs().getPaymentStatus());
		
	}	

}
