/**
 * 
 */
package br.com.transport.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author leandro.goncalves
 *
 */
@Entity
@Table(name = "CARRIER")
public class Carrier implements EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "MODEL", nullable = false)
	private String model;
	
	@Column(name = "YEAR", nullable = false)
	private String year;
	
	@Column(name = "LICENSE_PLATE", nullable = false)
	private String lisencePlate;
	
	@Column(name = "CAPACITY", nullable = false)
	private Double capacity;
	
	@OneToMany(mappedBy = "carrier")
	private List<Freight> listFreight;
	
	/* (non-Javadoc)
	 * @see br.com.transport.domain.EntityBase#getId()
	 */
	

	public Carrier() {
	}

	public Carrier(String model, String year, String lisencePlate,
			Double capacity) {
		super();
		this.model = model;
		this.year = year;
		this.lisencePlate = lisencePlate;
		this.capacity = capacity;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public List<Freight> getListFreight() {
		return listFreight;
	}

	public void setListFreight(List<Freight> listFreight) {
		this.listFreight = listFreight;
	}
}
