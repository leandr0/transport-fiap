/**
 * 
 */
package br.com.transport.ws;

import br.com.transport.payment.PaymentException;
import br.com.transport.ws.vo.CarrierWS;
import br.com.transport.ws.vo.EmployeeWS;
import br.com.transport.ws.vo.FreightWS;
import br.com.transport.ws.vo.ReportVOWS;
import br.com.transport.ws.vo.TrackHistoryWS;

/**
 * @author leandro.goncalves
 * @author robson
 *
 */
public interface ServiceTransport {

	public String requestAllocation(FreightWS freight);
	
	public FreightWS responseAllocation(String idMessage);
	
	public ReportVOWS[] generateReport();
	
	public void addCarrierFleet(CarrierWS[] carriers);
	
	public void addEmployee(EmployeeWS employee);

	public EmployeeWS findEmployee(EmployeeWS employee);
	
	public void removeEmployee(EmployeeWS employee);

	public EmployeeWS updateEmployee(EmployeeWS employee);
	
	public FreightWS registerPaymentFreight(Long numberFreight, Double value) throws PaymentException;
	
	public TrackHistoryWS getTrackHistoryCurrent(Long numberFreight);
}
