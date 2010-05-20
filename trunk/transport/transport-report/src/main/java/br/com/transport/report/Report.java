/**
 * 
 */
package br.com.transport.report;

import java.util.List;

import javax.ejb.EJBException;

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
	public List<?> executeReport() throws EJBException;
}
