package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnect;
import model.*;
import postgresDAO.*;

public class Controller {

	private static Contatto c;
	private static Indirizzo i;
	private static Telefono t;

	// Lista di contatti caricata
	private static ArrayList<Contatto> cList;

	private DatabaseConnect connessione;

	public static void caricaRubrica() {

		ArrayList<Indirizzo> iList;
		ArrayList<Gruppo> gList;
		ArrayList<Telefono> tList;
		ArrayList<Recapito> rList;

		PostContattoDAO contattoDAO = new PostContattoDAO();

		cList = contattoDAO.getContatti();

		PostIndirizzoDAO indirizzoDAO = new PostIndirizzoDAO();

		iList = indirizzoDAO.getIndirizzi();

		PostTelefonoDAO telefonoDAO = new PostTelefonoDAO();

		tList = telefonoDAO.getListaNumeri();

		PostGruppoDAO gruppoDAO = new PostGruppoDAO();

		gList = gruppoDAO.getGruppi();

		// Ciclo in cui si andranno a caricare gli arraylist dei contatti
		// (Indirizzi,Email,Telefono,Gruppi)
		for (Contatto c : cList) {

			// Prima fase in cui sarà caricato l'arraylist degli indirizzi del contatto
			PostAlloggioDAO alloggioDAO = new PostAlloggioDAO();

			ArrayList<Integer> alloggi = alloggioDAO.getAlloggi(c.getContID());

			for (Integer alloggio : alloggi) {
				for (Indirizzo indirizzo : iList) {
					if (alloggio.intValue() == indirizzo.getAddrID()) {
						if (c.getIndirizzi() == null) {
							ArrayList<Indirizzo> i = new ArrayList<>();
							c.setIndirizzi(i);
						}

						c.addIndirizzo(indirizzo);
					}
				}
			}

			// Seconda fase in cui saranno caricati i gruppi nell'arraylist del contatto

			PostAggregazioneDAO aggregazioneDAO = new PostAggregazioneDAO();

			ArrayList<Integer> aggregazioni = aggregazioneDAO.getAggregazioni(c.getContID());

			for (Integer a : aggregazioni) {
				for (Gruppo g : gList) {
					if (a.intValue() == g.getGroupID()) {
						c.addGruppo(g);
					}
				}
			}

			// Terza fase dove saranno caricari i numeri di telefono del contatto nel suo
			// apposito arraylist

			PostRecapitoDAO recapitoDAO = new PostRecapitoDAO();
			rList = recapitoDAO.getRecapiti(tList, c.getContID());
			c.setRecapiti(rList);

			// Estrapoliamo le email del contatto dal DB passiamogliele sottoforma di
			// ArrayList
			PostEmailDAO emailDAO = new PostEmailDAO();
			c.setEmail(emailDAO.getEmail(c.getContID()));

			// Associamo ad ogni email del contatto i profili di Messaging
			PostMessagingPrDAO messagingPrDAO = new PostMessagingPrDAO();
			for (Email e : c.getEmail()) {
				e.setMessagingPr(messagingPrDAO.getMessagingPR(e.getIndirizzo()));
			}

		}

	}

	public ArrayList<Contatto> searchContact(String type, String data) {

		ArrayList<Contatto> r = null;

		Contatto cont = null;

		if (type.equals("Telefono")) {
			r = searchContactByTelephone(data);
		}
		if (type.equals("Nome")) {

		}
		if (type.equals("Email")) {

			r = new ArrayList<Contatto>();
			cont = searchContactByEmail(data);
			if (cont != null) {
				r.add(cont);
			}

		}
		if (type.equals("Social")) {

		}

		return r;
	}

	public ArrayList<Contatto> pullContacts() {
		ArrayList<Contatto> r = null;

		return cList = r;
	}

	// Inserisce un contatto in DB tramite DAO
	public void assignContatto() {

		setC(new Contatto(getC().getNome(), getC().getCognome(), getC().getIndirizzoP()));
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
		Telefono tFisso, tMobile;
		Recapito r;

		ArrayList<Indirizzo> iList = null;
		ArrayList<Recapito> rList = null;

		String nome, cognome, prefissoFisso, numeroFisso, prefissoMobile, numeroMobile, nazione, citta, via, cap;

		int addrID, contID;

		nome = data[0];
		cognome = data[1];
		prefissoFisso = data[2];
		numeroFisso = data[3];
		prefissoMobile = data[4];
		numeroMobile = data[5];
		nazione = data[6];
		citta = data[7];
		via = data[8];
		cap = data[9];

		// Caricamento dati nel DB
		i = new Indirizzo(via, citta, cap, nazione);
		PostIndirizzoDAO iDao = new PostIndirizzoDAO();
		addrID = iDao.setIndirizzo(i);

		c = new Contatto(nome, cognome, addrID);
		PostContattoDAO cDao = new PostContattoDAO();
		contID = cDao.setContatto(c);

		PostAlloggioDAO pDao = new PostAlloggioDAO();
		pDao.setAlloggio(contID, addrID);

		tFisso = new Telefono(numeroFisso, prefissoFisso, "Fisso");
		tMobile = new Telefono(numeroMobile, prefissoMobile, "Mobile");

		PostTelefonoDAO tFissoDao = new PostTelefonoDAO();
		tFissoDao.setTelefono(tFisso);
		PostTelefonoDAO tMobileDao = new PostTelefonoDAO();
		tMobileDao.setTelefono(tMobile);

		r = new Recapito(tFisso, tMobile);
		r.setContID(contID);
		PostRecapitoDAO rDao = new PostRecapitoDAO();
		rDao.setRecapito(contID, prefissoFisso, numeroFisso, prefissoMobile, numeroMobile);
		// Fine Caricamento DB

		// Sincronizzazione con variabili locali

		if (c.getIndirizzi() == null) {
			iList = new ArrayList<>();
			iList.add(i);
			c.setIndirizzi(iList);
		} else
			c.addIndirizzo(i);

		if (c.getRecapiti() == null) {
			rList = new ArrayList<>();
			rList.add(r);
			c.setRecapiti(rList);
		} else
			c.addRecapito(r);

		if (cList == null) {
			ArrayList<Contatto> cList = new ArrayList<>();
			cList.add(c);

		} else
			cList.add(c);

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

	public static ArrayList<Contatto> getcList() {
		return cList;
	}

	public void setcList(ArrayList<Contatto> cList) {
		this.cList = cList;
	}

	public Contatto searchContactByEmail(String email) {

		Contatto matched = null;

		for (Contatto cont : cList) {
			for (Email em : cont.getEmail()) {
				if (em.getIndirizzo().equalsIgnoreCase(email)) {
					matched = cont;
					break;
				}
			}
		}

		return matched;

	}

	public ArrayList<Contatto> searchContactByTelephone(String numero) {

		ArrayList<Contatto> matched = new ArrayList<Contatto>();
		String num;

		for (Contatto cont : cList) {
			for (Recapito rec : cont.getRecapiti()) {
				num = rec.getTelefonoIn().getPrefisso().trim() + rec.getTelefonoIn().getNumero().trim();
				if (num.contains(numero)) {
					matched.add(cont);
					break;
				}
			}
		}
		return matched;
	}

	public ArrayList<Contatto> searchContactByNickname(String nickname) {

		ArrayList<Contatto> matched = new ArrayList<Contatto>();

		boolean trovato;

		for (Contatto cont : cList) {
			trovato = false;
			for (Email ema : cont.getEmail()) {
				for (MessagingPr mpr : ema.getMessagingPr()) {
					if (mpr.getNickname().equals(nickname)) {
						matched.add(cont);
						trovato = true;
						break;
					}
				}
				if (trovato)
					break;
			}
		}

		return matched;
	}

	public ArrayList<Contatto> searchContactByName(String nome) {

		ArrayList<Contatto> matched = new ArrayList<Contatto>();

		ArrayList<String> splittedName = new ArrayList<String>();
		
		String[] splittedTmp = nome.split(" ");

		String nomeContatto;
		
		int match = 0;
		
		//Vado ad aggiungere a splittedName solo i nomi validi da analizzare, dato il difetto di .split() di non trattare più sequenze di spazi
		for (String k : splittedTmp)
			if (k.length() != 0)
				splittedName.add(k.trim());

		for (Contatto cont : cList) {
			nomeContatto = new String(cont.getNome() + cont.getCognome());
			match = 0;
			for (String s : splittedName) {
				if (nomeContatto.contains(s))
					match++;
			}
			if(match==splittedName.size()) matched.add(cont);
		}

		return matched;

	}
}
