package model;
import java.util.ArrayList;

public class Contatto {
	private int contID;
	private String nome;
	private String cognome;
	private String indFoto;
	private int indirizzoP;
	
	private ArrayList<Recapito> recapiti;
	private ArrayList<Gruppo> gruppi;
	private ArrayList<Email> email;
	private ArrayList<Indirizzo> indirizzi;
	

	public Contatto(int contID, String nome, String cognome, String indFoto, int indirizzoP) {

		this.contID = contID;
		this.nome = nome;
		this.cognome = cognome;
		this.indFoto = indFoto;
		this.indirizzoP = indirizzoP;

	}
	//Costruttore utile al caricamento dei contatti nell'ArrayList
	public Contatto(int contID, String nome, String cognome, String indFoto) {

		this.contID = contID;
		this.nome = nome;
		this.cognome = cognome;
		this.indFoto = indFoto;

	}
	
	public Contatto(String nome, String cognome, int indirizzoP) {

		this.nome = nome;
		this.cognome = cognome;
		this.indirizzoP = indirizzoP;

	}

	public int getContID() {
		return this.contID;
	}
	
	public void setContID(int contID) {
		this.contID = contID;
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

	
	public ArrayList<Gruppo> getGruppi(){
		return this.gruppi;
	}
	
	public void addGruppo(Gruppo gruppo) {
		this.gruppi.add(gruppo);
	}
	
	public void addEmail(Email email) {
		this.email.add(email);
	}
	
	public void addIndirizzo(Indirizzo indirizzo) {
		this.indirizzi.add(indirizzo);
	}
	
	public void addRecapito(Recapito recapito) {
		this.recapiti.add(recapito);
	}
	
	public void setRecapiti(ArrayList<Recapito> rList) {
		this.recapiti=rList;
	}
	
	public int getIndirizzoP() {
		return indirizzoP;
	}

	public void setIndirizzoP(int indirizzoP) {
		this.indirizzoP = indirizzoP;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setIndFoto(String indFoto) {
		this.indFoto = indFoto;
	}

	public void setEmail(ArrayList<Email> eList) {
		this.email=eList;
	}
	
	public ArrayList<Email> getEmail(){
		return this.email;
	}

	public ArrayList<Indirizzo> getIndirizzi() {
		return indirizzi;
	}
	public void setIndirizzi(ArrayList<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}
	public ArrayList<Recapito> getRecapiti() {
		return recapiti;
	}
}
