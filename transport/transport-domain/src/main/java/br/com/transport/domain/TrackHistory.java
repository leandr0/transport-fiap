/**
 * 
 */
package br.com.transport.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author leandro.goncalves
 *
 */
@Entity
@Table(name = "TRACK_HISTORY")
public class TrackHistory implements EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DATE", nullable = false)
	private Date date;
	
	@Column(name = "LOCAL", nullable = false)
	private String local;
	
	@ManyToOne
	@JoinColumn(name = "FREIGHT_ID", nullable = false)
	private Freight freight;

	
	public TrackHistory() {}

	
	public TrackHistory(Date date, String local) {
		super();
		this.date = date;
		this.local = local;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Freight getFreight() {
		return freight;
	}

	public void setFreight(Freight freight) {
		this.freight = freight;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	
}