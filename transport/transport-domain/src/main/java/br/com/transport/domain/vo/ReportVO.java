/**
 * 
 */
package br.com.transport.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author leandro.goncalves
 *
 */
public class ReportVO implements Serializable {
	
	private final Date departureDate;
	
	private final Date deliveryDate;
	
	private final String status;
	
	private final Long carrierId;
	
	private final Double capacity;
	
	private final String licensePlate;
	
	private final String model;
	
	public ReportVO(Date departureDate,Date deliveryDate,String status,
			Long carrierId,Double capacity,String licensePlate,String model) {
	
		this.departureDate 	= departureDate;
		this.deliveryDate  	= deliveryDate;
		this.status        	= status;
		this.carrierId	   	= carrierId;
		this.capacity		= capacity;
		this.licensePlate	= licensePlate;
		this.model			= model;
		
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public Long getCarrierId() {
		return carrierId;
	}

	public Double getCapacity() {
		return capacity;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getModel() {
		return model;
	}
}