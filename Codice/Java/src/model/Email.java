package model;

public class Email {
	private String indirizzo;
	private String utilizzo;
	
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

}
