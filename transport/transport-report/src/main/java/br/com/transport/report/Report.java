/**
 * 
 */
package br.com.transport.report;

import java.util.List;

import javax.ejb.EJBException;

import br.com.transport.domain.vo.ReportVO;

/**
 * @author leandro.goncalves
 *
 */
public interface Report {

	/**
	 * 
	 * @return
	 * @throws EJBException
	 */
	public List<ReportVO> executeReport() throws EJBException;
}
