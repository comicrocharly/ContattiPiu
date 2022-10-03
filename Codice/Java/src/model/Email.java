package model;

import java.util.ArrayList;

public class Email {
	private String indirizzo;
	private String utilizzo;
	private ArrayList<MessagingPr> messagingPr;
	
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

}
