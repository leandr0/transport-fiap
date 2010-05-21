/**
 * 
 */
package br.com.transport.payment;

/**
 * @author robson
 *
 */
public class PaymentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5466979306785648617L;

	/**
	 * 
	 */
	public PaymentException() {
	}

	
	/**
	 * @param arg0
	 */
	public PaymentException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public PaymentException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PaymentException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
