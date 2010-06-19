/**
 * 
 */
package br.com.transport.ws;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import br.com.tranport.cadastre.ManagerCadastreRemote;
import br.com.transport.allocation.request.RequestAllocationRemote;
import br.com.transport.allocation.response.ResponseAllocationRemote;
import br.com.transport.payment.ManagerPayment;
import br.com.transport.payment.PaymentException;
import br.com.transport.report.ReportRemote;
import br.com.transport.truckage.ManagerTrack;
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
@Stateless(name = "serviceTransport" )
@Remote(ServiceTransportRemote.class)
@WebService(name="transportws" )
public class ManagerServiceTranportBean implements ServiceTransportLocal, ServiceTransportRemote {	
	
	@EJB 
	private RequestAllocationRemote requestAllocation;
	
	@EJB 
	private ResponseAllocationRemote responseAllocation;
	
	@EJB 
	private ReportRemote report;
	
	@EJB
	private ManagerCadastreRemote cadastre;
	
	@EJB
	private ManagerPayment payment;
	
	@EJB
	private ManagerTrack track;

	@Override
	public void addCarrierFleet(CarrierWS[] carriers) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addEmployee(EmployeeWS employee) {
		// TODO Auto-generated method stub
	}

	@Override
	public EmployeeWS findEmployee(EmployeeWS employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReportVOWS[] generateReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrackHistoryWS getTrackHistoryCurrent(Long numberFreight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FreightWS registerPaymentFreight(Long numberFreight, Double value)
			throws PaymentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEmployee(EmployeeWS employee) {
		// TODO Auto-generated method stub	
	}

	@Override
	public String requestAllocation(FreightWS freight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FreightWS responseAllocation(String idMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeWS updateEmployee(EmployeeWS employee) {
		// TODO Auto-generated method stub
		return null;
	}	
}