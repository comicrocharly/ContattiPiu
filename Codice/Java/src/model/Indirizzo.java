package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Indirizzo.
 */
public class Indirizzo {
	
	/** The addr ID. */
	private int addrID;
	
	/** The via. */
	private String via;
	
	/** The citta. */
	private String citta;
	
	/** The cap. */
	private String cap;
	
	/** The nazione. */
	private String nazione;

	/**
	 * Instantiates a new indirizzo.
	 *
	 * @param addrID the addr ID
	 * @param via the via
	 * @param citta the citta
	 * @param cap the cap
	 * @param nazione the nazione
	 */
	public Indirizzo(int addrID,String via, String citta, String cap, String nazione) {
		this.addrID = addrID;
		this.via = via;
		this.citta = citta;
		this.cap = cap;
		this.nazione = nazione;

	}
	
	/**
	 * Instantiates a new indirizzo.
	 *
	 * @param via the via
	 * @param citta the citta
	 * @param cap the cap
	 * @param nazione the nazione
	 */
	public Indirizzo(String via, String citta, String cap, String nazione) {
		this.via = via;
		this.citta = citta;
		this.cap = cap;
		this.nazione = nazione;
	}

	/**
	 * Gets the addr ID.
	 *
	 * @return the addr ID
	 */
	public int getAddrID() {
		return this.addrID;
	}
	
	/**
	 * Gets the via.
	 *
	 * @return the via
	 */
	public String getVia() {
		return this.via;
	}
	
	/**
	 * Gets the citta.
	 *
	 * @return the citta
	 */
	public String getCitta() {
		return this.citta;
	}
	
	/**
	 * Gets the cap.
	 *
	 * @return the cap
	 */
	public String getCap() {
		return this.cap;
	}
	
	/**
	 * Gets the nazione.
	 *
	 * @return the nazione
	 */
	public String getNazione() {
		return this.nazione;
	}
	
	/**
	 * Sets the addr ID.
	 *
	 * @param addrID the new addr ID
	 */
	public void setAddrID(int addrID) {
		this.addrID = addrID;
	}

	/**
	 * Sets the via.
	 *
	 * @param via the new via
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/**
	 * Sets the citta.
	 *
	 * @param citta the new citta
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * Sets the cap.
	 *
	 * @param cap the new cap
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Sets the nazione.
	 *
	 * @param nazione the new nazione
	 */
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
}
