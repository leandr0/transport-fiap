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

import br.com.transport.allocation.request.RequestAllocationRemote;
import br.com.transport.allocation.response.ResponseAllocationRemote;
import br.com.transport.domain.Freight;
import br.com.transport.domain.vo.ReportVO;
import br.com.transport.report.ReportRemote;

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
}