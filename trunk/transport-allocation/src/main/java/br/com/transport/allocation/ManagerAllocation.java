/**
 * 
 */
package br.com.transport.allocation;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.transport.domain.Carrier;



/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "allocation")
@Local(AllocationLocal.class)
@Remote(AllocationRemote.class)
public class ManagerAllocation implements AllocationLocal {

	@PersistenceContext(unitName = "persistence-allocation")
	private EntityManager entityManager;

	public void teste() throws EJBException {
		
		Carrier carrier = new Carrier();
		
		carrier.setCapacity(1.2);
		carrier.setLisencePlate("ABC 2154");
		carrier.setModel("Mercedez");
		carrier.setYear("1990");
		
		entityManager.persist(carrier);
		
	}
	
}
