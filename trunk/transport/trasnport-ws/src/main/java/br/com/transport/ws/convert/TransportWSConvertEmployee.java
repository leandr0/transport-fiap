/**
 * 
 */
package br.com.transport.ws.convert;

import br.com.transport.domain.Employee;
import br.com.transport.domain.Post;
import br.com.transport.ws.vo.EmployeeWS;

/**
 * @author robson
 *
 */
public class TransportWSConvertEmployee< T extends EmployeeWS , E extends Employee> implements TransportWSConvert<T, E> {

	@Override
	@SuppressWarnings("unchecked")
	public E convertToEntity(T t) {
		if(t == null)
			throw new IllegalArgumentException("T is null");
		
		Employee employee = new Employee();
		
		try {			 
			employee.setId(t.getId());
			employee.setName(t.getName());
			employee.setPost(Enum.valueOf(Post.class, t.getPost()));
		} catch (Exception e) {
			employee.setPost(Post.AUXILIARY);// default if  happen a error
		}
		
		return (E) employee;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public T convertToWS(E e) {
		if(e == null)
			throw new IllegalArgumentException("E is null");
		return (T) new EmployeeWS(
				e.getId(),
				e.getName(), 
				e.getPost().name()
			);
	}

	
}
