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
	
	@ManyToOne
	@JoinColumn(name = "FREIGHT_ID", nullable = false)
	private Freight freight;
	
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
}