/**
 * 
 */
package br.com.transport.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.tranport.cadastre.ManagerCadastreRemote;
import br.com.transport.allocation.request.RequestAllocationRemote;
import br.com.transport.allocation.response.ResponseAllocationRemote;
import br.com.transport.domain.Carrier;
import br.com.transport.domain.Employee;
import br.com.transport.domain.Freight;
import br.com.transport.domain.TrackHistory;
import br.com.transport.domain.vo.ReportVO;
import br.com.transport.payment.ManagerPaymentBean;
import br.com.transport.report.ReportRemote;
import br.com.transport.truckage.ManagerTrackBean;

/**
 * @author leandro.goncalves
 *
 */
@Stateless(name = "serviceTransport")
@Remote(ServiceTransportRemote.class)
@WebService
public class ManagerServiceTranportBean implements ServiceTransportLocal, ServiceTransportRemote {	
	
	@EJB(mappedName="requestAllocation/remote") 
	private RequestAllocationRemote requestAllocation;
	
	@EJB(mappedName="responseAllocation/remote") 
	private ResponseAllocationRemote responseAllocation;
	
	@EJB(mappedName="report/remote") 
	private ReportRemote report;
	
	@EJB(mappedName="cadastre/remote")
	private ManagerCadastreRemote cadastre;
	
	@EJB(mappedName="payment/remote")
	private ManagerPaymentBean payment;
	
	@EJB(mappedName="track/remote")
	private ManagerTrackBean track;
	
	@Override
	@WebMethod
	public String requestAllocation(Freight freight) {
		return requestAllocation.requestAllocation(freight);
	}

	
	@Override
	@WebMethod
	public Freight responseAllocation(String idMessage) {
		return responseAllocation.responseAllocation(idMessage);
	}


	@Override
	@WebMethod
	public List<ReportVO> generateReport() {
		return report.executeReport();
	}
	
	@Override
	@WebMethod
	public void addCarrierFleet(List<Carrier> carriers){
		cadastre.addCarrierFleet(carriers);
	}
	
	@Override
	@WebMethod
	public void addEmployee(Employee employee) {
		cadastre.addEmployee(employee);
	}

	@Override
	@WebMethod
	public Employee findEmployee(Employee employee){
		return cadastre.findEmployee(employee);
	}	

	@Override
	@WebMethod
	public void removeEmployee(Employee employee) {
		cadastre.removeEmployee(employee);
	}

	@Override
	@WebMethod
	public Employee updateEmployee(Employee employee){
		return cadastre.updateEmployee(employee);
	}

//TODO arrumar esta muito porco
	@Override
	@WebMethod
	public Freight registerPaymentFreight(Long numberFreight, Double value) {
		try {
			return payment.registerPaymentFreight(numberFreight, value);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	@WebMethod
	public TrackHistory getTrackHistoryCurrent(Long numberFreight) {
		return track.getTrackHistoryCurrent(numberFreight);
	}
}