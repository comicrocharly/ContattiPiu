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
	// Contatti da mostrare, anche in ricerca
	private ArrayList<Contatto> cList;

	private DatabaseConnect connessione;

	public ArrayList<Contatto> caricaRubrica(){
		
		ArrayList<Contatto> cList;
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
			
			PostAggregazioneDAO aggregazione = new PostAggregazioneDAO();
			
			ArrayList<Integer> aggregazioni = aggregazione.getAggregazioni(cont.getContID());
			
			for(Integer a: aggregazioni) {
				for(Gruppo g: gList) {
					if(a.intValue()== g.getGroupID()) {
						cont.addGruppo(g);
					}
				}
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

		setC(new Contatto(getC().getNome(), getC().getCognome(), getC().getIndFoto(), getC().getIndirizzo()));
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
