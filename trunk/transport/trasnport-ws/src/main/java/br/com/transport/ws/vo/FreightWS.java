/**
 * 
 */
package br.com.transport.ws.vo;



/**
 * @author robson
 * @author leandro.goncalves
 */
public class FreightWS implements WSVO{

	private long id;
	
	private String status;
	
	private String departureDate;
	
	private String deliveryDate;
	
	private AddressWS departureAddress;

	private AddressWS deliveryAddress;
	
	private PaymentWs paymentWs;
	
	private CarrierWS carrier;
	
	public FreightWS() {}

	public FreightWS(long id, String status, PaymentWs paymentWs) {
		super();
		this.id = id;
		this.status = status;
		this.paymentWs = paymentWs;
	}

	public FreightWS(String departureDate,String deliveryDate,AddressWS departureAddress,AddressWS deliveryAddress,CarrierWS carrier){
		this.deliveryAddress 	= deliveryAddress;
		this.deliveryDate 		= deliveryDate;
		this.departureAddress 	= departureAddress;
		this.departureDate 		= departureDate;
		this.carrier			= carrier;
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

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public AddressWS getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(AddressWS departureAddress) {
		this.departureAddress = departureAddress;
	}

	public AddressWS getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressWS deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public CarrierWS getCarrier() {
		return carrier;
	}

	public void setCarrier(CarrierWS carrier) {
		this.carrier = carrier;
	}
}