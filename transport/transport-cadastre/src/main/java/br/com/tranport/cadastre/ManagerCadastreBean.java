/**
 * 
 */
package br.com.tranport.cadastre;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.transport.domain.Carrier;
import br.com.transport.domain.Employee;

/**
 * @author robson
 *
 */
@Stateless
@Local(ManagerCadastreLocal.class)
@Remote(ManagerCadastreRemote.class)
public class ManagerCadastreBean implements ManagerCadastreLocal, ManagerCadastreRemote {


	private static final Log LOG = LogFactory.getLog(ManagerCadastreBean.class);
	
	
	@PersistenceContext(unitName="persistence-cadastre")
	private EntityManager entityManager;
	
	
	
	public void addCarrierFleet(List<Carrier> carriers){
		LOG.info("init add carries");
		if (carriers == null) 
			throw new IllegalArgumentException("List of Carrier is null");
		validateList(carriers);
		
		for (Carrier carrier : carriers)
			entityManager.persist(carrier);
		LOG.info("carries add with sucess");
	}




	public void addEmployee(Employee employee) {
		validEmployee(employee);
		entityManager.persist(employee);
	}



	public Employee findEmployee(Employee employee){
		if(employee == null)
			throw new IllegalArgumentException("Employee is null");
		if(employee.getId() == null )
			throw new IllegalArgumentException("Employee with id null or <= 0");		
		return entityManager.find(Employee.class, employee.getId());
	}	


	
	public void removeEmployee(Employee employee) {
		entityManager.remove(findEmployee(employee));
	}

	

	public Employee updateEmployee(Employee employee){
		if( findEmployee(employee) == null)
			throw new IllegalArgumentException("employee not found");
		validEmployee(employee);
		return entityManager.merge(employee);
	}
	

	
	private void validateList(List<Carrier> carriers) {
		for (Carrier carrier : carriers) {
			if (carrier == null) 
				throw new IllegalArgumentException("List of Carrier contains carrier null ");
			if( StringUtils.isBlank(carrier.getLisencePlate()))
				throw new IllegalArgumentException("List of Carrier contains carrier with invalid LisencePlate");
			if( StringUtils.isBlank(carrier.getModel()))
				throw new IllegalArgumentException("List of Carrier contains carrier with invalid Model");
			if( StringUtils.isBlank(carrier.getYear()))
				throw new IllegalArgumentException("List of Carrier contains carrier with invalid Year");
			if( carrier.getCapacity() == null || carrier.getCapacity() <= 0 )
				throw new IllegalArgumentException("List of Carrier contains carrier with invalid Capacity");			
		}		
	}

	
	private void validEmployee(Employee employee) {
		if (employee == null) 
			throw new IllegalArgumentException("Employee is null");
		if( StringUtils.isBlank(employee.getName()))
			throw new IllegalArgumentException("Employee with invalid name");
		if( employee.getPost() == null)
			throw new IllegalArgumentException("Employee with invalid post");		
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	

	
}
