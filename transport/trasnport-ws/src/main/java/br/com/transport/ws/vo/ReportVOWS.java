package br.com.transport.ws.vo;


/**
 * @author leandro
 * @author robson
 *
 */
public class ReportVOWS implements WSVO{

	private String departureDate;
	
	private String deliveryDate;
	
	private String status;
	
	private long carrierId;
	
	private double capacity;
	
	private String licensePlate;
	
	private String model;
	
	private String[] freeDays = null;

	
	public ReportVOWS() {}
	
	public ReportVOWS(String departureDate,
						String deliveryDate,
						String status,
						long carrierId,
						double capacity,
						String licensePlate,
						String model,
						String[] freeDays) {
		
		this.departureDate	= departureDate;
		this.deliveryDate	= deliveryDate;
		this.status			= status;
		this.carrierId		= carrierId;
		this.capacity		= capacity;
		this.licensePlate	= licensePlate;
		this.model			= model;
		this.freeDays		= freeDays;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(long carrierId) {
		this.carrierId = carrierId;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String[] getFreeDays() {
		return freeDays;
	}
}