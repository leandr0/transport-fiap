/**
 * 
 */
package br.com.transport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author robson
 *
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee implements EntityBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	
	@Column(name="POST" , nullable = false)
	private Post post;

		
	public Employee() {}


	public Employee(String name, Post post) {
		super();
		this.name = name;
		this.post = post;
	}


	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}
}
