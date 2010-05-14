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

	
	@Test
	public void testAddEmployee() {
		//fail("Not yet implemented");
	}


	@Test
	public void testFindEmployee() {
		//fail("Not yet implemented");
	}


	@Test
	public void testRemoveEmployee() {
		//fail("Not yet implemented");
	}


	@Test
	public void testUpdateEmployee() {
		//fail("Not yet implemented");
	}

}
