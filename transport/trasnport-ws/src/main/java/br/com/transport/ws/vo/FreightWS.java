/**
 * 
 */
package br.com.transport.ws.vo;


/**
 * @author robson
 *
 */
public class FreightWS implements WSVO{

	private Long id;
	
	
	private String status;
	
	
	public FreightWS() {}
	
	
	public FreightWS(Long id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
