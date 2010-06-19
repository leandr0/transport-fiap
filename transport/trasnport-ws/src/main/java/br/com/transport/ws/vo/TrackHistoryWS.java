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
public class TrackHistoryWS implements WSVO{

	private String date;
	
	private String local;
	
	private FreightWS freight;

	public TrackHistoryWS() {}
	
	public TrackHistoryWS(String date, String local, FreightWS freight) {
		super();
		this.date = date;
		this.local = local;
		this.freight = freight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public FreightWS getFreight() {
		return freight;
	}

	public void setFreight(FreightWS freight) {
		this.freight = freight;
	}
	
}
