package model;

public class Indirizzo {
	private int addrID;
	private String via;
	private String citta;
	private String cap;
	private String nazione;

	public Indirizzo(int addrID,String via, String citta, String cap, String nazione) {
		this.addrID = addrID;
		this.via = via;
		this.citta = citta;
		this.cap = cap;
		this.nazione = nazione;

	}
	
	public Indirizzo(String via, String citta, String cap, String nazione) {
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
	
	public void setAddrID(int addrID) {
		this.addrID = addrID;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
}
