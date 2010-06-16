/**
 * 
 */
package br.com.transport.ws;

import java.util.List;

import br.com.transport.domain.Freight;
import br.com.transport.domain.vo.ReportVO;

/**
 * @author leandro.goncalves
 *
 */
public interface ServiceTransport {

	public String requestAllocation(Freight freight);
	
	public Freight responseAllocation(String idMessage);
	
	public List<ReportVO> generateReport();
}
