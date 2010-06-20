/**
 * 
 */
package br.com.transport.ws.vo;


/**
 * @author robson
 *
 */
public class PaymentWs implements WSVO{

	private String PaymentDate;
	
	private double value;

	private String paymentStatus;

	public PaymentWs() {
	}

	public PaymentWs(String paymentDate, double value, String paymentStatus) {
		super();
		PaymentDate = paymentDate;
		this.value = value;
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		PaymentDate = paymentDate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	
}
