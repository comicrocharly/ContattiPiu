package model;

import java.util.ArrayList;

public class Email {
	private Integer contID;
	private String indirizzo;
	private String utilizzo;
	

	private ArrayList<MessagingPr> messagingPr;
	
	public Email(Integer contID, String indirizzo, String utilizzo) {
		this.setContID(contID);
		this.setIndirizzo(indirizzo);;
		this.setUtilizzo(utilizzo);
	}
	
	public Email(String indirizzo,String utilizzo) {
		this.indirizzo=indirizzo;
		this.utilizzo=utilizzo;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public String getUtilizzo() {
		return this.utilizzo;
	}
	
	public void addMessagingPr(MessagingPr messagingPr) {
		this.messagingPr.add(messagingPr);
		
	}
	
	public void setMessagingPr(ArrayList<MessagingPr> messagingList) {
		this.messagingPr=messagingList;
	}
	
	public ArrayList<MessagingPr> getMessagingPr(){
		return this.messagingPr;
	}

	public Integer getContID() {
		return contID;
	}

	public void setContID(Integer contID) {
		this.contID = contID;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public void setUtilizzo(String utilizzo) {
		this.utilizzo = utilizzo;
	}
}
