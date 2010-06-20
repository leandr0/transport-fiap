/**
 * 
 */
package br.com.transport.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import br.com.tranport.cadastre.ManagerCadastreRemote;
import br.com.transport.allocation.request.RequestAllocationRemote;
import br.com.transport.allocation.response.ResponseAllocationRemote;
import br.com.transport.domain.Carrier;
import br.com.transport.domain.Employee;
import br.com.transport.domain.Freight;
import br.com.transport.domain.TrackHistory;
import br.com.transport.payment.ManagerPayment;
import br.com.transport.payment.ManagerPaymentRemote;
import br.com.transport.payment.PaymentException;
import br.com.transport.report.ReportRemote;
import br.com.transport.truckage.ManagerTrack;
import br.com.transport.truckage.ManagerTrackRemote;
import br.com.transport.ws.convert.TransportWSConvert;
import br.com.transport.ws.convert.TransportWSConvertCarrier;
import br.com.transport.ws.convert.TransportWSConvertEmployee;
import br.com.transport.ws.convert.TransportWSConvertFreight;
import br.com.transport.ws.convert.TransportWSConvertTrackHistoryWS;
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
public class ManagerServiceTransportBean implements ServiceTransportLocal, ServiceTransportRemote {	
	
	@EJB 
	private RequestAllocationRemote requestAllocation;
	
	@EJB 
	private ResponseAllocationRemote responseAllocation;
	
	@EJB 
	private ReportRemote report;
	
	@EJB
	private ManagerCadastreRemote cadastre;
	
	@EJB
	private ManagerPaymentRemote payment;
	
	@EJB
	private ManagerTrackRemote track;
 
	
	TransportWSConvert<EmployeeWS, Employee> convert = new TransportWSConvertEmployee<EmployeeWS, Employee>();	
	
	@Override
	public void addCarrierFleet(CarrierWS[] carriers){
		if(carriers == null)
			throw new IllegalArgumentException("carriers is null");
		
		TransportWSConvert<CarrierWS, Carrier> transporWSConvert = 
			new TransportWSConvertCarrier<CarrierWS, Carrier>();
		
		List<Carrier> carriersList = new ArrayList<Carrier>();
		
		for (CarrierWS carrierWS : carriers) 
			carriersList.add(transporWSConvert.convertToEntity(carrierWS));
		
		cadastre.addCarrierFleet(carriersList);
	}

	
	@Override
	public void addEmployee(EmployeeWS employee) {
		cadastre.addEmployee(convert.convertToEntity(employee));
	}

	
	@Override
	public EmployeeWS findEmployee(EmployeeWS employeeWS) {
		Employee employee = cadastre.findEmployee(convert.convertToEntity(employeeWS));
		
		if(employee == null)
			return null;
		
		return convert.convertToWS(employee);
	}

	
	@Override
	public ReportVOWS[] generateReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrackHistoryWS getTrackHistoryCurrent(Long numberFreight){
		
		TransportWSConvert<TrackHistoryWS, TrackHistory> convert =
				new TransportWSConvertTrackHistoryWS<TrackHistoryWS, TrackHistory>();

		TrackHistory history = track.getTrackHistoryCurrent(numberFreight);
		
		if( history == null)
			return null;
		
		return convert.convertToWS(history);
	}

	
	@Override
	public FreightWS registerPaymentFreight(Long numberFreight, Double value)throws PaymentException {
		TransportWSConvert<FreightWS, Freight> convert = 
			new TransportWSConvertFreight<FreightWS, Freight>();
		
		return convert.convertToWS(payment.registerPaymentFreight(numberFreight, value));
	}

	@Override
	public void removeEmployee(EmployeeWS employee) {
		cadastre.removeEmployee(convert.convertToEntity(employee));	
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
	public EmployeeWS updateEmployee(EmployeeWS employeeWS){		
		return convert.convertToWS(cadastre.updateEmployee(convert.convertToEntity(employeeWS)));
	}
	
}