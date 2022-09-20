package model;

public class Indirizzo {
	private int addrID;
	private String via;
	private String citta;
	private String cap;
	private String nazione;

	public Indirizzo(int addrID, String via, String citta, String cap, String nazione) {
		this.addrID = addrID;
		this.via = via;
		this.citta = citta;
		this.cap = cap;
		this.nazione = nazione;

	}
	
	public int getAddrID() {
		return this.addrID;
	}
	
	public String getVia() {
		return this.via;
	}
	
	public String getCitta() {
		return this.citta;
	}
	
	public String getCap() {
		return this.cap;
	}
	
	public String getNazione() {
		return this.nazione;
	}
}
