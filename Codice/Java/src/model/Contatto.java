package model;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Contatto.
 */
public class Contatto {
	
	/** The cont ID. */
	private int contID;
	
	/** The nome. */
	private String nome;
	
	/** The cognome. */
	private String cognome;
	
	/** The ind foto. */
	private String indFoto;
	
	/** The indirizzo P. */
	private int indirizzoP;
	
	/** The recapiti. */
	private ArrayList<Recapito> recapiti;
	
	/** The gruppi. */
	private ArrayList<Gruppo> gruppi;
	
	/** The email. */
	private ArrayList<Email> email;
	
	/** The indirizzi. */
	private ArrayList<Indirizzo> indirizzi;
	

	/**
	 * Instantiates a new contatto.
	 *
	 * @param contID the cont ID
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param indFoto the ind foto
	 * @param indirizzoP the indirizzo P
	 */
	public Contatto(int contID, String nome, String cognome, String indFoto, int indirizzoP) {

		this.contID = contID;
		this.nome = nome;
		this.cognome = cognome;
		this.indFoto = indFoto;
		this.indirizzoP = indirizzoP;

	}
	
	/**
	 * Instantiates a new contatto.
	 *
	 * @param contID the cont ID
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param indFoto the ind foto
	 */
	//Costruttore utile al caricamento dei contatti nell'ArrayList
	public Contatto(int contID, String nome, String cognome, String indFoto) {

		this.contID = contID;
		this.nome = nome;
		this.cognome = cognome;
		this.indFoto = indFoto;

	}
	
	/**
	 * Instantiates a new contatto.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param indirizzoP the indirizzo P
	 */
	public Contatto(String nome, String cognome, int indirizzoP) {

		this.nome = nome;
		this.cognome = cognome;
		this.indirizzoP = indirizzoP;

	}

	/**
	 * Gets the cont ID.
	 *
	 * @return the cont ID
	 */
	public int getContID() {
		return this.contID;
	}
	
	/**
	 * Sets the cont ID.
	 *
	 * @param contID the new cont ID
	 */
	public void setContID(int contID) {
		this.contID = contID;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Gets the cognome.
	 *
	 * @return the cognome
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * Gets the ind foto.
	 *
	 * @return the ind foto
	 */
	public String getIndFoto() {
		return this.indFoto;
	}

	/**
	 * Gets the gruppi.
	 *
	 * @return the gruppi
	 */
	public ArrayList<Gruppo> getGruppi(){
		return this.gruppi;
	}
	
	/**
	 * Adds the gruppo.
	 *
	 * @param gruppo the gruppo
	 */
	public void addGruppo(Gruppo gruppo) {
		this.gruppi.add(gruppo);
	}
	
	/**
	 * Adds the email.
	 *
	 * @param email the email
	 */
	public void addEmail(Email email) {
		this.email.add(email);
	}
	
	/**
	 * Adds the indirizzo.
	 *
	 * @param indirizzo the indirizzo
	 */
	public void addIndirizzo(Indirizzo indirizzo) {
		this.indirizzi.add(indirizzo);
	}
	
	/**
	 * Adds the recapito.
	 *
	 * @param recapito the recapito
	 */
	public void addRecapito(Recapito recapito) {
		this.recapiti.add(recapito);
	}
	
	/**
	 * Sets the recapiti.
	 *
	 * @param rList the new recapiti
	 */
	public void setRecapiti(ArrayList<Recapito> rList) {
		this.recapiti=rList;
	}
	
	/**
	 * Gets the indirizzo P.
	 *
	 * @return the indirizzo P
	 */
	public int getIndirizzoP() {
		return indirizzoP;
	}

	/**
	 * Sets the indirizzo P.
	 *
	 * @param indirizzoP the new indirizzo P
	 */
	public void setIndirizzoP(int indirizzoP) {
		this.indirizzoP = indirizzoP;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Sets the cognome.
	 *
	 * @param cognome the new cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Sets the ind foto.
	 *
	 * @param indFoto the new ind foto
	 */
	public void setIndFoto(String indFoto) {
		this.indFoto = indFoto;
	}

	/**
	 * Sets the email.
	 *
	 * @param eList the new email
	 */
	public void setEmail(ArrayList<Email> eList) {
		this.email=eList;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public ArrayList<Email> getEmail(){
		return this.email;
	}

	/**
	 * Gets the indirizzi.
	 *
	 * @return the indirizzi
	 */
	public ArrayList<Indirizzo> getIndirizzi() {
		return indirizzi;
	}
	
	/**
	 * Sets the indirizzi.
	 *
	 * @param indirizzi the new indirizzi
	 */
	public void setIndirizzi(ArrayList<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}
	
	/**
	 * Gets the recapiti.
	 *
	 * @return the recapiti
	 */
	public ArrayList<Recapito> getRecapiti() {
		return recapiti;
	}
	
	/**
	 * Sets the gruppi.
	 *
	 * @param gruppi the new gruppi
	 */
	public void setGruppi(ArrayList<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}
}
