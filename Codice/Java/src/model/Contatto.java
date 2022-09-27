package model;
import java.util.ArrayList;

public class Contatto {
	private int contID;
	private String nome;
	private String cognome;
	private String indFoto;
	private Indirizzo indirizzoP;
	private ArrayList<Gruppo> gruppi;

	public Contatto(int contID, String nome, String cognome, String indFoto, Indirizzo indirizzoP) {

		this.contID = contID;
		this.nome = nome;
		this.cognome = cognome;
		this.indFoto = indFoto;
		this.indirizzoP = indirizzoP;

	}
	
	public Contatto(String nome, String cognome, String indFoto, Indirizzo indirizzoP) {

		this.nome = nome;
		this.cognome = cognome;
		this.indFoto = indFoto;
		this.indirizzoP = indirizzoP;

	}

	public int getContID() {
		return this.contID;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public String getIndFoto() {
		return this.indFoto;
	}

	public Indirizzo getIndirizzo() {
		return this.indirizzoP;
	}
	
	public ArrayList<Gruppo> getGruppi(){
		return this.gruppi;
	}
	
	public void addGruppo(Gruppo gruppo) {
		this.gruppi.add(gruppo);
	}
}
