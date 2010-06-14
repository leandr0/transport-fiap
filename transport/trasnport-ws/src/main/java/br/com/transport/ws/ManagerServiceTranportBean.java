/**
 * 
 */
package br.com.transport.ws;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.transport.domain.Freight;

/**
 * @author leandro.goncalves
 *
 */
@Stateless
@Remote(ServiceTransportRemote.class)
@WebService(serviceName="Counter", portName="CounterPort")
public class ManagerServiceTranportBean implements ServiceTransportLocal,
		ServiceTransportRemote {

	
	@Override
	@WebMethod
	public String requestAllocation(Freight freight) {
		// TODO Auto-generated method stub
		return null;
	}

}
