package br.com.transport.ws.vo;

import javax.persistence.Column;

import br.com.transport.domain.Post;



/**
 * @author leandro
 * @author robson
 *
 */
public class EmployeeWS implements WSVO{
	
	private long id;
	
	private String name;
	
	private String post;

	public EmployeeWS() {}
	

	public EmployeeWS(long id, String name, String post) {
		super();
		this.id = id;
		this.name = name;
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
		
	
}
