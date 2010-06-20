/**
 * 
 */
package br.com.transport.ws.vo;


/**
 * @author robson
 *
 */
public class FreightWS implements WSVO{

	private long id;
	
	
	private String status;
	
	
	private PaymentWs paymentWs;
	
	
	public FreightWS() {}

	
	

	public FreightWS(long id, String status, PaymentWs paymentWs) {
		super();
		this.id = id;
		this.status = status;
		this.paymentWs = paymentWs;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public PaymentWs getPaymentWs() {
		return paymentWs;
	}


	public void setPaymentWs(PaymentWs paymentWs) {
		this.paymentWs = paymentWs;
	}

	
}
