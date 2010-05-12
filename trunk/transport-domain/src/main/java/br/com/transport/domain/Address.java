/**
 * 
 */
package br.com.transport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author leandro.goncalves
 *
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "STATE", nullable = false)
	private String state;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	
	@OneToOne(mappedBy = "departureAddress")
	private Freight freightDeparture;
	
	@OneToOne(mappedBy = "deliveryAddress")
	private Freight freightDelivery;
	
	/* (non-Javadoc)
	 * @see br.com.transport.domain.EntityBase#getId()
	 */
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see br.com.transport.domain.EntityBase#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Freight getFreightDeparture() {
		return freightDeparture;
	}

	public void setFreightDeparture(Freight freightDeparture) {
		this.freightDeparture = freightDeparture;
	}

	public Freight getFreightDelivery() {
		return freightDelivery;
	}

	public void setFreightDelivery(Freight freightDelivery) {
		this.freightDelivery = freightDelivery;
	}
}