/**
 * 
 */
package br.com.transport.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author robson
 *
 */
@Table(name="PAYMENT")
@Entity
public class Payment implements EntityBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4085746830145187839L;


	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;

	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="payment")	
	private Freight freight;
	
	
	@Column(name="PAYMENT_DATE")
	private Date PaymentDate;
	
	
	@Column(name="VALUE")
	private Double value;

	
	@Column(name="PAYMENT_STATUS")
	private PaymentStatus paymentStatus;


	public Payment() {
	}


	public Payment(Date paymentDate, Double value, PaymentStatus paymentStatus) {
		super();
		PaymentDate = paymentDate;
		this.value = value;
		this.paymentStatus = paymentStatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Freight getFreight() {
		return freight;
	}


	public void setFreight(Freight freight) {
		this.freight = freight;
	}


	public Date getPaymentDate() {
		return PaymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
