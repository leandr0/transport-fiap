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
import org.apache.commons.lang.math.NumberUtils;
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
		if (carriers == null) 
			throw new IllegalArgumentException("List of Carrier is null");
		validateList(carriers);
		
		for (Carrier carrier : carriers)
			entityManager.persist(carrier);
	}




	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
	}


	public void findEmployee(Employee employee) {
		// TODO Auto-generated method stub
	}


	public void removeEmployee(Employee employee) {
		// TODO Auto-generated method stub
	}


	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
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




	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	

	
}
