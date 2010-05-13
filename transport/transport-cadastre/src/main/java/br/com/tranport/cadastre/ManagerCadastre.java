/**
 * 
 */
package br.com.tranport.cadastre;

import java.util.List;

import br.com.transport.domain.Carrier;
import br.com.transport.domain.Employee;

/**
 * @author leandro.goncalves
 *
 */
public interface ManagerCadastre{

	public void addCarrierFleet(List<Carrier> carriers);
	
	public void addEmployee(Employee employee);
	
	public void removeEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void findEmployee(Employee employee);
}
