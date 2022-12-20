package model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Email.
 */
public class Email {
	
	/** The cont ID. */
	private Integer contID;
	
	/** The indirizzo. */
	private String indirizzo;
	
	/** The utilizzo. */
	private String utilizzo;
	

	/** The messaging pr. */
	private ArrayList<MessagingPr> messagingPr;
	
	/**
	 * Instantiates a new email.
	 *
	 * @param contID the cont ID
	 * @param indirizzo the indirizzo
	 * @param utilizzo the utilizzo
	 */
	public Email(Integer contID, String indirizzo, String utilizzo) {
		this.setContID(contID);
		this.setIndirizzo(indirizzo);;
		this.setUtilizzo(utilizzo);
	}
	
	/**
	 * Instantiates a new email.
	 *
	 * @param indirizzo the indirizzo
	 * @param utilizzo the utilizzo
	 */
	public Email(String indirizzo,String utilizzo) {
		this.indirizzo=indirizzo;
		this.utilizzo=utilizzo;
	}

	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return this.indirizzo;
	}

	/**
	 * Gets the utilizzo.
	 *
	 * @return the utilizzo
	 */
	public String getUtilizzo() {
		return this.utilizzo;
	}
	
	/**
	 * Adds the messaging pr.
	 *
	 * @param messagingPr the messaging pr
	 */
	public void addMessagingPr(MessagingPr messagingPr) {
		this.messagingPr.add(messagingPr);
		
	}
	
	/**
	 * Sets the messaging pr.
	 *
	 * @param messagingList the new messaging pr
	 */
	public void setMessagingPr(ArrayList<MessagingPr> messagingList) {
		this.messagingPr=messagingList;
	}
	
	/**
	 * Gets the messaging pr.
	 *
	 * @return the messaging pr
	 */
	public ArrayList<MessagingPr> getMessagingPr(){
		return this.messagingPr;
	}

	/**
	 * Gets the cont ID.
	 *
	 * @return the cont ID
	 */
	public Integer getContID() {
		return contID;
	}

	/**
	 * Sets the cont ID.
	 *
	 * @param contID the new cont ID
	 */
	public void setContID(Integer contID) {
		this.contID = contID;
	}
	
	/**
	 * Sets the indirizzo.
	 *
	 * @param indirizzo the new indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Sets the utilizzo.
	 *
	 * @param utilizzo the new utilizzo
	 */
	public void setUtilizzo(String utilizzo) {
		this.utilizzo = utilizzo;
	}
}
