package br.com.transport.ws;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.transport.ws.vo.EmployeeWS;

/**
 * @author robson
 *
 */
public class ManagerServiceTransportBeanTest {

	private ManagerServiceTransportBean beanTest;
	
	@Before
	public void setUp() throws Exception {
		beanTest = new ManagerServiceTransportBean();
	}

	@After
	public void tearDown() throws Exception {
		beanTest = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddCarrierFleetWihtCarriersNull(){
		beanTest.addCarrierFleet(null);
	}

	
/*	@Test
	public void testAddEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenerateReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTrackHistoryCurrent() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegisterPaymentFreight() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestAllocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testResponseAllocation() {
		fail("Not yet implemented");
	} */

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateEmployeeWihtEmployeeWSNull() {
		beanTest.updateEmployee(null);
	}
	
}
