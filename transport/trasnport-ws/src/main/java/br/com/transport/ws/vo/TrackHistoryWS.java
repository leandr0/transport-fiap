/**
 * 
 */
package br.com.transport.ws.vo;


/**
 * @author leandro
 * @author robson
 */
public class TrackHistoryWS implements WSVO{

	private String date;
	
	private String local;

	public TrackHistoryWS() {}
	
	public TrackHistoryWS(String date, String local) {
		super();
		this.date = date;
		this.local = local;
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
}
