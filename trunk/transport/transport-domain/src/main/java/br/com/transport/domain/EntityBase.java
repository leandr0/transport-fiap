/**
 * 
 */
package br.com.transport.domain;

import java.io.Serializable;

/**
 * @author leandro.goncalves
 *
 */
public interface EntityBase extends Serializable {

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id);
	
	/**
	 * 
	 * @return
	 */
	public Long getId();
}
