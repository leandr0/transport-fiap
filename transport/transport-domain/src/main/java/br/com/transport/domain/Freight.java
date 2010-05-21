/**
 * 
 */
package br.com.transport.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Entity;

/**
 * @author leandro.goncalves
 *
 */
@Entity
@Table(name = "FREIGHT")
public class Freight implements EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "INVOICE")
	private String invoice;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@Column(name = "DEPARTURE_DATE")
	private Date departureDate;
	
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DEPARTURE_ADDRESS_ID",nullable = false)
	private Address departureAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DELIVERY_ADDRESS_ID", nullable = false)
	private Address deliveryAddress;
	
	@OneToMany(mappedBy = "freight")
	private List<TrackHistory> listTrackHistory;
	
	@ManyToOne
	@JoinColumn(name = "CARRIER_ID", nullable = false)
	private Carrier carrier;
	
	
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name="PAYMENT_ID", nullable = false)
	private Payment payment;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Address getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(Address departureAddress) {
		this.departureAddress = departureAddress;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<TrackHistory> getListTrackHistory() {
		return listTrackHistory;
	}

	public void setListTrackHistory(List<TrackHistory> listTrackHistory) {
		this.listTrackHistory = listTrackHistory;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}