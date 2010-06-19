package br.com.transport.ws.convert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.transport.domain.Employee;
import br.com.transport.domain.Post;
import br.com.transport.ws.vo.EmployeeWS;

public class TransportWSConvertEmployeeTest {

	
	private TransportWSConvert<EmployeeWS, Employee> transportWSConvert;
	
	@Before
	public void setUp() throws Exception {
		transportWSConvert = new TransportWSConvertEmployee<EmployeeWS, Employee>();
	}

	
	@After
	public void tearDown() throws Exception {
		transportWSConvert = null;
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testConvertToEntityWihtEntityNull() {
		transportWSConvert.convertToEntity(null);
	}
	
	
	@Test()
	public void testConvertToEntityWihtEntityValid() {
		Employee employee = transportWSConvert.convertToEntity(new EmployeeWS(1L,"Robson","AUXILIARY"));
		assertNotNull(employee);
		assertEquals(new Long(1L), employee.getId());
		assertEquals("Robson", employee.getName());
		assertEquals(Post.AUXILIARY, employee.getPost());
	}
	
	
	@Test()
	public void testConvertToEntityWihtInvalidPost() {
		Employee employee = transportWSConvert.convertToEntity(new EmployeeWS(1L,"Robson","skksksk"));
		assertNotNull(employee);
		assertEquals(new Long(1L), employee.getId());
		assertEquals("Robson", employee.getName());
		assertEquals(Post.AUXILIARY, employee.getPost());
	}	

	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToWSWihtEmployeeWSNull() {
		transportWSConvert.convertToWS(null);
	}
	
	
	@Test()
	public void testConvertToWSWihtEmployeeWSValid() {
		Employee employee = new Employee("Robson", Post.AUXILIARY);
		employee.setId(1L);
		
		EmployeeWS employeeWS = transportWSConvert.convertToWS(employee);		
		assertNotNull(employeeWS);
		assertEquals(1L, employeeWS.getId());
		assertEquals("Robson", employeeWS.getName());
		assertEquals("AUXILIARY", employeeWS.getPost());	
	}	

}
