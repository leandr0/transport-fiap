/**
 * 
 */
package br.com.transport.ws.vo;


/**
 * 
 * @author leandro
 * @author robson
 *
 */
public class CarrierWS implements WSVO{
	
	private String model;
		
	private String year;
	
	private String lisencePlate;
	
	private double capacity;
	
	public CarrierWS() {}

	
	public CarrierWS(String model, String year, String lisencePlate,
			double capacity) {
		super();
		this.model = model;
		this.year = year;
		this.lisencePlate = lisencePlate;
		this.capacity = capacity;
	}


	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLisencePlate() {
		return lisencePlate;
	}

	public void setLisencePlate(String lisencePlate) {
		this.lisencePlate = lisencePlate;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}	
}
