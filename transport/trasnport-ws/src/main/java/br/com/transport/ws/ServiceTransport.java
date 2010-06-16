/**
 * 
 */
package br.com.transport.ws;

import java.util.List;

import br.com.transport.domain.Carrier;
import br.com.transport.domain.Employee;
import br.com.transport.domain.Freight;
import br.com.transport.domain.TrackHistory;
import br.com.transport.domain.vo.ReportVO;

/**
 * @author leandro.goncalves
 *
 */
public interface ServiceTransport {

	public String requestAllocation(Freight freight);
	
	public Freight responseAllocation(String idMessage);
	
	public List<ReportVO> generateReport();
	
	public void addCarrierFleet(List<Carrier> carriers);
	
	public void addEmployee(Employee employee);

	public Employee findEmployee(Employee employee);
	
	public void removeEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);
	
	public Freight registerPaymentFreight(Long numberFreight, Double value);
	
	public TrackHistory getTrackHistoryCurrent(Long numberFreight);
}
