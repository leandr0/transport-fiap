/**
 * 
 */
package br.com.tranport.cadastre;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import br.com.transport.domain.Carrier;
import br.com.transport.domain.Employee;
import br.com.transport.domain.Post;

/**
 * @author robson
 *
 */
public class ManagerCadastreBeanTest {

	
	ManagerCadastreBean  managerCadastreBeanTest;
	
	
	EntityManager entityManagerMock;
	
	@Before
	public void setUp() throws Exception {
			managerCadastreBeanTest = new ManagerCadastreBean();
			entityManagerMock = createMock(EntityManager.class); 
	}

	
	@After
	public void tearDown() throws Exception {
		managerCadastreBeanTest = null;
		reset(entityManagerMock);
	}


	@Test
	public void testAddCarrierFleetWithListNull() {
		Exception ex = null;		
		try {
			managerCadastreBeanTest.addCarrierFleet(null);
		} catch (Exception e) {
			ex = e;
		}		
		assertNotNull(ex);
	}
	
	
	@Test
	public void testAddCarrierFleetWithCarrierNull() {
		Exception ex = null;
		
		List<Carrier> carriers = new ArrayList<Carrier>();
		
		carriers.add(null);
		
		try {
			managerCadastreBeanTest.addCarrierFleet(carriers);
		} catch (Exception e) {
			ex = e;
		}		
		assertNotNull(ex);
	}	

	
	@Test
	public void testAddCarrierFleetWithCarrierInvalid1(){
		
		Exception ex = null;
		
		List<Carrier> carriers = new ArrayList<Carrier>();
		
		// todos os atributos obrigatorios estao null
		Carrier carrierInvalid = new Carrier();
		
		carriers.add(carrierInvalid);
		
		try {
			managerCadastreBeanTest.addCarrierFleet(carriers);
		} catch (Exception e) {
			ex = e;
		}		
		assertNotNull(ex);
	}
	
	
	@Test
	public void testAddCarrierFleetWithCarrierInvalid2(){
		
		Exception ex = null;
		
		List<Carrier> carriers = new ArrayList<Carrier>();
		
		//  lisencePlate valido demais invalidos
		Carrier carrierInvalid = new Carrier();		
		carrierInvalid.setLisencePlate("temp");
		
		carriers.add(carrierInvalid);
		
		try {
			managerCadastreBeanTest.addCarrierFleet(carriers);
		} catch (Exception e) {
			ex = e;
		}		
		assertNotNull(ex);
	}
	
	
	@Test
	public void testAddCarrierFleetWithCarrierInvalid3(){
		
		Exception ex = null;
		
		List<Carrier> carriers = new ArrayList<Carrier>();
		
		//  lisencePlate + model valido demais invalidos
		Carrier carrierInvalid = new Carrier();		
		carrierInvalid.setLisencePlate("licence");
		carrierInvalid.setModel("model");
		
		carriers.add(carrierInvalid);
		
		try {
			managerCadastreBeanTest.addCarrierFleet(carriers);
		} catch (Exception e) {
			ex = e;
		}		
		assertNotNull(ex);
	}
	
	
	@Test
	public void testAddCarrierFleetWithCarrierInvalid4(){
		
		Exception ex = null;
		
		List<Carrier> carriers = new ArrayList<Carrier>();
		
		//  lisencePlate + model + year valido demais invalidos
		Carrier carrierInvalid = new Carrier();		
		carrierInvalid.setLisencePlate("licence");
		carrierInvalid.setModel("model");
		carrierInvalid.setYear("year");
		
		carriers.add(carrierInvalid);
		
		try {
			managerCadastreBeanTest.addCarrierFleet(carriers);
		} catch (Exception e) {
			ex = e;
		}		
		assertNotNull(ex);
	}
	
	
	@Test
	public void testAddCarrierFleetCarrierInvalid5(){
		
		Exception ex = null;
		
		List<Carrier> carriers = new ArrayList<Carrier>();
		
		//  lisencePlate + model + year valido demais invalidos
		Carrier carrierInvalid = new Carrier();		
		carrierInvalid.setLisencePlate("licence");
		carrierInvalid.setModel("model");
		carrierInvalid.setYear("year");
		carrierInvalid.setCapacity(new Double(0.0));
		
		carriers.add(carrierInvalid);
		
		try {
			managerCadastreBeanTest.addCarrierFleet(carriers);
		} catch (Exception e) {
			ex = e;
		}		
		assertNotNull(ex);
	}

	
	
	@Test
	public void testAddCarrierFleetWithListValid(){
		
		Exception ex = null;
		
		List<Carrier> carriers = new ArrayList<Carrier>();
		

		Carrier carrierValid = new Carrier();		
		carrierValid.setLisencePlate("licence");
		carrierValid.setModel("model");
		carrierValid.setYear("year");
		carrierValid.setCapacity(new Double(0.1));
		
		carriers.add(carrierValid);
		
		
		
		try {
			entityManagerMock.persist(carrierValid);						
			replay(entityManagerMock);
			managerCadastreBeanTest.setEntityManager(entityManagerMock);
			managerCadastreBeanTest.addCarrierFleet(carriers);			
			verify(entityManagerMock);
		} catch (Exception e) {
			ex = e;
		}		
		assertNull(ex);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testAddEmployeeNull() {
		managerCadastreBeanTest.addEmployee(null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddEmployeeInvalidName() {
		managerCadastreBeanTest.addEmployee(new Employee());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddEmployeeInvalidPost() {
		Employee employee = new Employee();
		employee.setName("robson");		
		managerCadastreBeanTest.addEmployee(employee);
	}
	
	
	@Test()
	public void testAddEmployeeValid(){
		Employee employee = new Employee();
		employee.setName("robson");
		employee.setPost(Post.DRIVER);		
		entityManagerMock.persist(employee);
		replay(entityManagerMock);
		managerCadastreBeanTest.setEntityManager(entityManagerMock);		
		managerCadastreBeanTest.addEmployee(employee);
		verify(entityManagerMock);
	}

	

	@Test(expected=IllegalArgumentException.class)
	public void testFindEmployeeNull(){
		managerCadastreBeanTest.findEmployee(null);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindEmployeeWihtIDnull(){
		managerCadastreBeanTest.findEmployee(new Employee());
	}

	
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindEmployeeWihtIDLessEqual0(){
		Employee employee = new Employee();
		employee.setId(0L);
		managerCadastreBeanTest.findEmployee(employee);
	}
	
	
	
	@Test()
	public void testFindEmployeeValid(){
		Employee employee = new Employee();
		employee.setId(1L);
		expect(entityManagerMock.find(Employee.class, employee.getId())).andReturn(new Employee("Robson", Post.DRIVER));
		replay(entityManagerMock);
		managerCadastreBeanTest.setEntityManager(entityManagerMock);
		employee = managerCadastreBeanTest.findEmployee(employee);
		verify(entityManagerMock);
		assertNotNull(employee);
	}


	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveEmployeeNull() {
		managerCadastreBeanTest.removeEmployee(null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveEmployeeWithIDNull() {
		managerCadastreBeanTest.removeEmployee(new Employee());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveEmployeeWihtIDLessEqual0() {
		Employee employee = new Employee();
		employee.setId(0L);
		managerCadastreBeanTest.removeEmployee(employee);
	}
	
	
	
	@Test()
	public void testRemoveEmployeeValid() {
		Employee employee = new Employee();
		employee.setId(1L);
		expect(entityManagerMock.find(Employee.class, employee.getId())).andReturn(employee);
		entityManagerMock.remove(employee);
		replay(entityManagerMock);
		managerCadastreBeanTest.setEntityManager(entityManagerMock);
		managerCadastreBeanTest.removeEmployee(employee);
		verify(entityManagerMock);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testUpdateEmployeeNotFound(){		
		Employee employee = new Employee();
		employee.setId(1L);		
		expect( entityManagerMock.find(Employee.class, employee.getId())).andReturn(null);
		replay(entityManagerMock);
		managerCadastreBeanTest.setEntityManager(entityManagerMock);
		managerCadastreBeanTest.updateEmployee(employee);
		verify(entityManagerMock);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateEmployeeFoundInvalid(){		
		Employee employee = new Employee();
		employee.setId(1L);		
		expect( entityManagerMock.find(Employee.class, employee.getId())).andReturn(new Employee());
		replay(entityManagerMock);
		managerCadastreBeanTest.setEntityManager(entityManagerMock);
		managerCadastreBeanTest.updateEmployee(employee);
		verify(entityManagerMock);
	}
	
	
	
	@Test()
	public void testUpdateEmployeeFoundValid(){		
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Robson");
		employee.setPost(Post.AUXILIARY);
		expect( entityManagerMock.find(Employee.class, employee.getId())).andReturn(new Employee());
		expect( entityManagerMock.merge(employee)).andReturn(employee);
		replay(entityManagerMock);
		managerCadastreBeanTest.setEntityManager(entityManagerMock);
		Employee employee2 = managerCadastreBeanTest.updateEmployee(employee);
		verify(entityManagerMock);
		assertNotNull(employee2);
	}

}
