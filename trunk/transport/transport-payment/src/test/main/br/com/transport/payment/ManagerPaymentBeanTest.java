/**
 * 
 */
package br.com.transport.payment;

import javax.persistence.EntityManager;



import org.junit.*;


import br.com.transport.domain.Freight;
import br.com.transport.domain.Payment;
import br.com.transport.domain.PaymentStatus;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * @author robson
 *
 */
public class ManagerPaymentBeanTest {

	
	private ManagerPaymentBean managerPaymentBeanTest;
	
	
	private EntityManager entityManagerMock;
	

	@Before
	public void setUp() throws Exception {
		entityManagerMock = createMock(EntityManager.class);
		managerPaymentBeanTest = new ManagerPaymentBean();
		managerPaymentBeanTest.setEntityManager(entityManagerMock);		
	}


	@After
	public void tearDown() throws Exception{
		managerPaymentBeanTest = null;
		reset(entityManagerMock);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRegisterPaymentFreightNumberFreigthNull() throws Exception {
		managerPaymentBeanTest.registerPaymentFreight(null, null);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterPaymentFreightValueNull() throws Exception {
		managerPaymentBeanTest.registerPaymentFreight(90L, null);
	}
	
	
	@Test(expected=PaymentException.class)
	public void testRegisterPaymentFreightNotFound() throws Exception{
		Long numberFreight = new Long(90L);		
		expect(entityManagerMock.find(Freight.class, numberFreight)).andReturn(null);
		replay(entityManagerMock);
		managerPaymentBeanTest.registerPaymentFreight(numberFreight, 100.56);
		verify(entityManagerMock);		
	}	
	
	
	
	@Test(expected=PaymentException.class)
	public void testRegisterPaymentFreightValueDiferent()throws Exception{
		
		Long numberFreight = new Long(90L);
				
		Freight freight = new Freight();
		freight.setPayment(new Payment());		
		freight.getPayment().setValue(100.555); // value != which value of argument
		
		
		expect(entityManagerMock.find(Freight.class, numberFreight)).andReturn(freight);
		replay(entityManagerMock);
		managerPaymentBeanTest.registerPaymentFreight(numberFreight, 100.56);
		verify(entityManagerMock);
		
		
	}
	
	
	@Test()
	public void testRegisterPaymentFreightValueLess()throws Exception{
		
		Long numberFreight = new Long(90L);
				
		Payment payment 
			= new Payment(
					null, 
					100.555, 
					PaymentStatus.TO_PAY
				);
		
		
		Freight freight = new Freight();
		freight.setPayment(payment);
		
		
		expect(entityManagerMock.find(Freight.class, numberFreight)).andReturn(freight);
		expect(entityManagerMock.merge(freight)).andReturn(freight);
		replay(entityManagerMock);
		freight = managerPaymentBeanTest.registerPaymentFreight(numberFreight, 100.555);
		verify(entityManagerMock);
		
		assertEquals(PaymentStatus.PAID, freight.getPayment().getPaymentStatus());
		assertNotNull(freight.getPayment().getPaymentDate());
		
	}	
	
	

}
