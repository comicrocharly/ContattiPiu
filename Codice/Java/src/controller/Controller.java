package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnect;
import model.*;
import postgresDAO.*;

public class Controller {

	private Contatto c;
	private Indirizzo i;
	private Telefono t;
	// Lista di contatti caricata
	private ArrayList<Contatto> cList;

	private DatabaseConnect connessione;

	public ArrayList<Contatto> caricaRubrica(){


		ArrayList<Indirizzo> iList;
		ArrayList<Gruppo> gList;
		ArrayList<Telefono> tList;

		PostContattoDAO contattoDAO = new PostContattoDAO();

		cList = contattoDAO.getContatti();

		PostIndirizzoDAO indirizzoDAO = new PostIndirizzoDAO();

		iList = indirizzoDAO.getIndirizzi();

		PostTelefonoDAO telefonoDAO = new PostTelefonoDAO();

		tList = telefonoDAO.getListaNumeri();

		PostGruppoDAO gruppoDAO = new PostGruppoDAO();

		gList = gruppoDAO.getGruppi();

		//Ciclo in cui si andranno a caricare gli arraylist dei contatti (Indirizzi,Email,Telefono,Gruppi)
		for(Contatto cont: cList) {

			//Prima fase in cui sarà caricato l'arraylist degli indirizzi del contatto
			PostAlloggioDAO alloggioDAO = new PostAlloggioDAO();

			ArrayList<Integer> alloggi = alloggioDAO.getAlloggi(cont.getContID());

			for(Integer alloggio: alloggi) {
				for(Indirizzo indirizzo: iList) {
					if(alloggio.intValue()==indirizzo.getAddrID()) {
						cont.addIndirizzo(indirizzo);
					}
				}
			}

			//Seconda fase in cui saranno caricati i gruppi nell'arraylist del contatto

			PostAggregazioneDAO aggregazioneDAO = new PostAggregazioneDAO();

			ArrayList<Integer> aggregazioni = aggregazioneDAO.getAggregazioni(cont.getContID());

			for(Integer a: aggregazioni) {
				for(Gruppo g: gList) {
					if(a.intValue()== g.getGroupID()) {
						cont.addGruppo(g);
					}
				}
			}

			//Terza fase dove saranno caricari i numeri di telefono del contatto nel suo apposito arraylist

			PostRecapitoDAO recapitoDAO = new PostRecapitoDAO();



			//Estrapoliamo le email del contatto dal DB passiamogliele sottoforma di ArrayList
			PostEmailDAO emailDAO = new PostEmailDAO();
			c.setEmail(emailDAO.getEmail(c.getContID()));

			//Associamo ad ogni email del contatto i profili di Messaging
			PostMessagingPrDAO messagingPrDAO = new PostMessagingPrDAO();
			for(Email e: c.getEmail()) {
				e.setMessagingPr(messagingPrDAO.getMessagingPR(e.getIndirizzo()));
			}

		}

		return cList;

	}

	public ArrayList<Contatto> searchContact(String type, String data) {

		ArrayList<Contatto> r = null;

		r = pullContacts();

		if (type.equals("Telefono")) {

		}
		if (type.equals("Nome")) {

		}
		if (type.equals("Email")) {

		}
		if (type.equals("Social")) {

		}

		return this.cList = r;
	}

	public ArrayList<Contatto> pullContacts() {
		ArrayList<Contatto> r = null;

		return this.cList = r;
	}

	// Inserisce un contatto in DB tramite DAO
	public void assignContatto() {

		setC(new Contatto(getC().getNome(), getC().getCognome(), getC().getIndFoto(), getC().getIndirizzoP()));
		PostContattoDAO contattoDAO = new PostContattoDAO();
		contattoDAO.setContatto(getC());

	}

	public void assignIndirizzo() {

		setI(new Indirizzo(getI().getCap(), getI().getCitta(), getI().getNazione(), getI().getVia()));
		PostIndirizzoDAO indirizzoDAO = new PostIndirizzoDAO();
		indirizzoDAO.setIndirizzo(getI());

	}

	public void assignTelefono() {

		setT(new Telefono(getT().getPrefisso(), getT().getNumero(), getT().getTipo()));
		PostTelefonoDAO telefonoDAO = new PostTelefonoDAO();
		telefonoDAO.setTelefono(getT());

	}

	public boolean checkConnection() throws SQLException {

		setConnessione(new DatabaseConnect());
		try {
			getConnessione().setConnection(DatabaseConnect.getInstance().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (getConnessione().getConnection() == null)
			return false;

		return true;

	}
	
	public static void insertDataRow(String data[]) {
		Contatto c;
		Indirizzo i;
		Telefono tIn, tOut;
		
		String nome, cognome, prefissoIn, numeroIn, prefissoOut, numeroOut, nazione, citta, via, cap;
		int addrID, contID;
		
		nome = data[0];
		cognome = data[1];
		prefissoIn = data[2];
		numeroIn = data[3];
		prefissoOut = data[4];
		numeroOut = data[5];
		nazione = data[6];
		citta = data[7];
		via = data[8];
		cap = data[9];
		
		i = new Indirizzo(via, citta, cap, nazione);
		PostIndirizzoDAO iDao = new PostIndirizzoDAO();
		addrID = iDao.setIndirizzo(i);
		
		//Il primo telefono deve essere fisso
		tIn = new Telefono(numeroIn, prefissoIn, "Fisso");
		tOut = new Telefono(numeroOut, prefissoOut, "Mobile");
		
		
		//-------------WorkInProgress--------------
		
		
		
		
	}

	private void setConnessione(DatabaseConnect connessione) {
		// TODO Auto-generated method stub
		this.connessione = connessione;

	}

	private DatabaseConnect getConnessione() {
		// TODO Auto-generated method stub

		return connessione;
	}

	public Contatto getC() {
		return c;
	}

	public void setC(Contatto c) {
		this.c = c;
	}

	public Indirizzo getI() {
		return i;
	}

	public void setI(Indirizzo i) {
		this.i = i;
	}

	public Telefono getT() {
		return t;
	}

	public void setT(Telefono t) {
		this.t = t;
	}

}
