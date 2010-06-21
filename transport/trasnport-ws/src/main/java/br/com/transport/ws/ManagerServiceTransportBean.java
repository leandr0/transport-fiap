/**
 * 
 */
package br.com.transport.ws;

import java.util.ArrayList;
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
import br.com.transport.payment.ManagerPaymentRemote;
import br.com.transport.payment.PaymentException;
import br.com.transport.report.ReportRemote;
import br.com.transport.truckage.ManagerTrackRemote;
import br.com.transport.ws.convert.TransportWSConvert;
import br.com.transport.ws.convert.TransportWSConvertCarrier;
import br.com.transport.ws.convert.TransportWSConvertEmployee;
import br.com.transport.ws.convert.TransportWSConvertFreight;
import br.com.transport.ws.convert.TransportWSConvertReport;
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

	@WebMethod
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

	@WebMethod
	@Override
	public void addEmployee(EmployeeWS employee) {
		TransportWSConvert<EmployeeWS, Employee> convert = new TransportWSConvertEmployee<EmployeeWS, Employee>();
		cadastre.addEmployee(convert.convertToEntity(employee));
	}

	@WebMethod
	@Override
	public EmployeeWS findEmployee(EmployeeWS employeeWS) {
		TransportWSConvert<EmployeeWS, Employee> convert = new TransportWSConvertEmployee<EmployeeWS, Employee>();
		Employee employee = cadastre.findEmployee(convert.convertToEntity(employeeWS));

		if(employee == null)
			return null;

		return convert.convertToWS(employee);
	}

	@WebMethod
	@Override
	public ReportVOWS[] generateReport() {

		TransportWSConvert<ReportVOWS, ReportVO> convert =
			new TransportWSConvertReport<ReportVOWS, ReportVO>();

		List<ReportVO> reports = report.executeReport();

		if(reports == null || reports.isEmpty())
			return null;

		ReportVOWS[] result 		= new ReportVOWS[reports.size()];  

		int i = 0;
		for(ReportVO vo : reports){
			result[i] = convert.convertToWS(vo);
			i++;
		}


		return result;
	}

	@WebMethod
	@Override
	public TrackHistoryWS getTrackHistoryCurrent(Long numberFreight){

		TransportWSConvert<TrackHistoryWS, TrackHistory> convert =
			new TransportWSConvertTrackHistoryWS<TrackHistoryWS, TrackHistory>();

		TrackHistory history = track.getTrackHistoryCurrent(numberFreight);

		if( history == null)
			return null;

		return convert.convertToWS(history);
	}

	@WebMethod
	@Override
	public FreightWS registerPaymentFreight(Long numberFreight, Double value)throws PaymentException {
		TransportWSConvert<FreightWS, Freight> convert = 
			new TransportWSConvertFreight<FreightWS, Freight>();

		return convert.convertToWS(payment.registerPaymentFreight(numberFreight, value));
	}

	@WebMethod
	@Override
	public void removeEmployee(EmployeeWS employee) {
		TransportWSConvert<EmployeeWS, Employee> convert = new TransportWSConvertEmployee<EmployeeWS, Employee>();
		cadastre.removeEmployee(convert.convertToEntity(employee));	
	}

	@WebMethod
	@Override
	public String requestAllocation(FreightWS freight) {
		TransportWSConvert<FreightWS, Freight> convert = 
			new TransportWSConvertFreight<FreightWS, Freight>();

		return requestAllocation.requestAllocation(convert.convertToEntity(freight));
	}

	@WebMethod
	@Override
	public FreightWS responseAllocation(String idMessage) {
		TransportWSConvert<FreightWS, Freight> convert = 
			new TransportWSConvertFreight<FreightWS, Freight>();

		FreightWS result = null;

		try{
			result = convert.convertToWS(responseAllocation.responseAllocation(idMessage));
		}catch (IllegalArgumentException e) {}

		return result; 
	}

	@WebMethod
	@Override
	public EmployeeWS updateEmployee(EmployeeWS employeeWS){		
		TransportWSConvert<EmployeeWS, Employee> convert = new TransportWSConvertEmployee<EmployeeWS, Employee>();
		return convert.convertToWS(cadastre.updateEmployee(convert.convertToEntity(employeeWS)));
	}	
}