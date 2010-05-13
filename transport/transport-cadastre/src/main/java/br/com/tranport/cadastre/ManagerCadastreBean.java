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

import br.com.transport.domain.Carrier;
import br.com.transport.domain.Employee;

/**
 * @author robson
 *
 */
@Stateless
@Local(ManagerCadastreLocal.class)
@Remote(ManagerCadastreRemote.class)
public class ManagerCadastreBean implements ManagerCadastreLocal,
		ManagerCadastreRemote {


	@PersistenceContext(unitName="persistence-cadastre")
	private EntityManager entityManager;
	
	
	public void addCarrierFleet(List<Carrier> carriers) {
		// TODO Auto-generated method stub
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

}
