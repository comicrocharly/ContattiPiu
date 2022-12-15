package controller;

import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.postgresql.util.PSQLException;

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
	
	
	public static void caricaRubrica(){


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
			
		//Ciclo in cui si andranno a caricare gli arraylist dei contatti (Indirizzi,Email,Telefono,Gruppi)
		for(Contatto c: cList) {
			
			//Prima fase in cui sarà caricato l'arraylist degli indirizzi del contatto
			PostAlloggioDAO alloggioDAO = new PostAlloggioDAO();

			ArrayList<Integer> alloggi = alloggioDAO.getAlloggi(c.getContID());
			
			for(Integer alloggio: alloggi) {
				for(Indirizzo indirizzo: iList) {
					if(alloggio.intValue()==indirizzo.getAddrID()) {
						if(c.getIndirizzi()==null) {
							ArrayList<Indirizzo> i = new ArrayList<>();
							c.setIndirizzi(i);
						}
						c.addIndirizzo(indirizzo);
						
					}
				}
			}
			
		
			//Seconda fase in cui saranno caricati i gruppi nell'arraylist del contatto

			PostAggregazioneDAO aggregazioneDAO = new PostAggregazioneDAO();

			ArrayList<Integer> aggregazioni = aggregazioneDAO.getAggregazioni(c.getContID());
			
			for(Integer a: aggregazioni) {
				for(Gruppo gruppo : gList) {
					if(a.intValue() == gruppo.getGroupID()) {
						if(c.getGruppi() == null) {
							ArrayList<Gruppo> g = new ArrayList<>();
							c.setGruppi(g);
						}
						
						c.addGruppo(gruppo);
						
					}
						
				}
			}

			//Terza fase dove saranno caricari i numeri di telefono del contatto nel suo apposito arraylist

			PostRecapitoDAO recapitoDAO = new PostRecapitoDAO();
			rList = recapitoDAO.getRecapiti(tList, c.getContID());
			c.setRecapiti(rList);
		

			//Estrapoliamo le email del contatto dal DB passiamogliele sottoforma di ArrayList
			PostEmailDAO emailDAO = new PostEmailDAO();
			c.setEmail(emailDAO.getEmail(c.getContID()));

			//Associamo ad ogni email del contatto i profili di Messaging
			PostMessagingPrDAO messagingPrDAO = new PostMessagingPrDAO();
			for(Email e: c.getEmail()) {
				e.setMessagingPr(messagingPrDAO.getMessagingPR(e.getIndirizzo()));
			}
			
		}

	}
	
	public static ArrayList<Gruppo> caricaGruppi(){
		ArrayList<Gruppo> gList = null;
		
		PostGruppoDAO gDao = new PostGruppoDAO();
		gList = gDao.getGruppi();
		
		
		return gList;
		
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
	
	public static void insertGruppo(String data[], Contatto c) throws Exception {
		
		Gruppo g;
		String nomeG, descrizione;
		Integer contID=c.getContID();
		Integer groupID;
		
		//NomeG, Descrizione é l'ordine dei parametri contenuti nell'array
		
		nomeG = data[0];
		descrizione = data[1];
		
		g = new Gruppo(nomeG, descrizione);
		PostGruppoDAO gDao = new PostGruppoDAO();
		groupID = gDao.setGruppo(nomeG, descrizione);
		
		PostAggregazioneDAO agDAO = new PostAggregazioneDAO();
		agDAO.setAggregazione(contID, groupID);
		
		for (Contatto tmp: cList) {
			if(contID.equals(tmp.getContID()))
				c = tmp;
		}
		
		if(c.getGruppi()==null) {
			ArrayList<Gruppo> gList = new ArrayList<>();
			c.setGruppi(gList);
		}
		
		c.addGruppo(g);
		
		
		
	}
	
	public static void insertAggregazione(Integer groupID, Integer contID) throws Exception {
		PostAggregazioneDAO agDao = new PostAggregazioneDAO();
		agDao.setAggregazione(contID, groupID);
		
	}
	
	public static void insertRecapito(String data[], Contatto c) throws Exception {
		Telefono tIn,tOut;
		String prefissoIn,prefissoOut,numeroIn,numeroOut,tipoIn,tipoOut;
		Integer contID=c.getContID();
		
		//Prefisso,numero,tipo é l'ordine dei parametri contenuti nell'array per i due telefoni in e out
		
		prefissoIn = data[0];
		numeroIn = data[1];
		tipoIn = data[2];
		prefissoOut = data[3];
		numeroOut= data[4];
		tipoOut= data[5];
		
		tIn = new Telefono(numeroIn, prefissoIn, tipoIn);
		PostTelefonoDAO tInDao = new PostTelefonoDAO();
		tInDao.setTelefono(tIn);
		
		tOut = new Telefono(numeroOut, prefissoOut, tipoOut);
		PostTelefonoDAO tOutDao = new PostTelefonoDAO();
		tOutDao.setTelefono(tOut);
		
		for (Contatto tmp: cList) {
			if(contID.equals(tmp.getContID()))
				c = tmp;
		}
		
		if(c.getRecapiti()==null) {
			ArrayList<Recapito> rList = new ArrayList<>();
			c.setRecapiti(rList);
		}
		
		Recapito r = new Recapito(tIn, tOut);
		PostRecapitoDAO rDao = new PostRecapitoDAO();
		rDao.setRecapito(contID, prefissoIn, numeroIn, prefissoOut, numeroOut);
		
		c.addRecapito(r);
		
	} 
	
	public static void insertEmail(String data[], Contatto c) {
		Email e;
		String indirizzo, uso;
		Integer contID=c.getContID();
		
		//Indirizzo, Uso é l'ordine dei parametri contenuti nell'array
		
		indirizzo = data[0];
		uso = data[1];
		
		e = new Email(contID, indirizzo, uso);
		PostEmailDAO eDao = new PostEmailDAO();
		eDao.setEmail(contID, indirizzo, uso);
		
		
		if(c.getEmail()==null) {
			ArrayList<Email> eList = new ArrayList<>();
			c.setEmail(eList);
		}
		
		c.addEmail(e);
		
	} 
	
	public static void insertAlloggio(String[] data, Contatto c) {
		Indirizzo i;
		String nazione, citta, cap, via;
		Integer contID = c.getContID();
		
		nazione = data[0];
		citta = data[1];
		cap = data[2];
		via = data[3];
		
		i = new Indirizzo(via, citta, cap, nazione);
		PostIndirizzoDAO iDao = new PostIndirizzoDAO();
		int addrID = iDao.setIndirizzo(i, contID);
		i.setAddrID(addrID);
		c.addIndirizzo(i);
		
	}
	
	public static void insertSocial(String[] data, Contatto c, Email email) {
		
		String nickname, provider, fraseBenvenuto;
		
		nickname = data[0];
		provider = data[1];
		fraseBenvenuto = data[2];
		
		MessagingPr mP = new MessagingPr(nickname, fraseBenvenuto, provider);
		PostMessagingPrDAO mpDao = new PostMessagingPrDAO();
		mpDao.setMessagingPR(email.getIndirizzo(), provider, fraseBenvenuto, nickname);
		
		if(email.getMessagingPr()==null) {
			ArrayList<MessagingPr> mpList = new ArrayList<>();
			email.setMessagingPr(mpList);
		}
		
		email.addMessagingPr(mP);
		
		
	}
	
	public static void insertContatto(String data[]) throws Exception {
		Contatto c;
		Indirizzo i;
		Telefono tFisso, tMobile;
		Recapito r;
		
		ArrayList<Indirizzo> iList = null;
		ArrayList<Recapito> rList = null;
		
		String nome, cognome, prefissoFisso, numeroFisso, prefissoMobile, 
		numeroMobile, nazione, citta, via, cap;
		
		int addrID, contID;
		
		nome = data[0];
		cognome = data[1];
		prefissoFisso = data[2];
		numeroFisso = data[3];
		prefissoMobile= data[4];
		numeroMobile = data[5];
		nazione = data[6];
		citta = data[7];
		via = data[8];
		cap = data[9];
		
		//Caricamento dati nel DB
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
		//Fine Caricamento DB

		//Sincronizzazione con variabili locali

		if(c.getIndirizzi()==null) {
			iList = new ArrayList<>();
			iList.add(i);
			c.setIndirizzi(iList);
		}
		else
			c.addIndirizzo(i);

		if(c.getRecapiti()==null) {
			rList = new ArrayList<>();
			rList.add(r);
			c.setRecapiti(rList);
		}
		else
			c.addRecapito(r);
		
		if(cList==null) {
			ArrayList<Contatto> cList = new ArrayList<>();
			cList.add(c);
			
		}
		else
			cList.add(c);



	}
	
	public static Contatto searchContactByEmail(String email) {

		for (Contatto cont : cList) {
			for (Email em : cont.getEmail()) {
				String indirizzo = em.getIndirizzo().toLowerCase().trim();
				String input = email.toLowerCase().trim();
				if (indirizzo.contains(input)) {
					return cont;
				}
			}
		}
		
		return null;

	}

	public static ArrayList<Contatto> searchContactByTelephone(String numero) {

		ArrayList<Contatto> matched = new ArrayList<Contatto>();
		String num;

		for (Contatto cont : cList) {
			for (Recapito rec : cont.getRecapiti()) {
				num = rec.getTelefonoIn().getPrefisso().trim() + rec.getTelefonoIn().getNumero().trim();
				num = num.trim();
				if (num.contains(numero)) {
					matched.add(cont);
					break;
				}
			}
		}
		return matched;
	}

	public static ArrayList<Contatto> searchContactByNickname(String nickname) {

		ArrayList<Contatto> matched = new ArrayList<Contatto>();

		boolean trovato;

		for (Contatto cont : cList) {
			trovato = false;
			for (Email ema : cont.getEmail()) {
				for (MessagingPr mpr : ema.getMessagingPr()) {
					String nick = mpr.getNickname().toLowerCase().trim();
					String input = nickname.toLowerCase().trim();
					if (nick.contains(input)) {
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

	public static ArrayList<Contatto> searchContactByName(String nome) {

		ArrayList<Contatto> matched = new ArrayList<Contatto>();

		ArrayList<String> splittedName = new ArrayList<String>();
		
		String[] splittedTmp = nome.split(" ");

		String nomeContatto;
		
		int match = 0;
		
		//Vado ad aggiungere a splittedName solo i nomi validi da analizzare, dato il difetto di .split() di non trattare più sequenze di spazzi
		for (String k : splittedTmp)
			if (k.length() != 0)
				splittedName.add(k.trim());

		for (Contatto cont : cList) {
			nomeContatto = new String(cont.getNome() + cont.getCognome()).toLowerCase().trim();
			match = 0;
			for (String s : splittedName) {
				if (nomeContatto.contains(s.toLowerCase()))
					match++;
			}
			if(match==splittedName.size()) matched.add(cont);
		}

		return matched;

	}
	
	public static void upIndirizzoP(Contatto c, Indirizzo i) {
		Integer addrID = i.getAddrID();
		Integer contID = c.getContID();
		PostContattoDAO cDao = new PostContattoDAO();
		cDao.upIndirizzoP(contID, addrID);
		
		c.setIndirizzoP(i.getAddrID());
	}

	public static void upContattoFoto(Contatto c, String indFoto) {
		int contID = c.getContID();

		PostContattoDAO cDao = new PostContattoDAO();
		cDao.upIndFoto(contID, indFoto);
		
		c.setIndFoto(indFoto);
	}

	public static void delAlloggio(Contatto c, Indirizzo i) throws Exception {
		int contID = c.getContID();
		int addrID = i.getAddrID();

		if(addrID!=c.getIndirizzoP()) {
			if(c.getIndirizzi().size() > 1 ) {
				PostAlloggioDAO aDao = new PostAlloggioDAO();
				aDao.delAlloggio(addrID, contID);

				c.getIndirizzi().remove(i);

			}
			else {
				throw new Exception("Il contatto deve avere almeno un Alloggio");
			}

		}
		else {
			throw new Exception("L'indirizzo è primario");
		}
	}

	public static void delGruppo(Contatto c, int i){
		int contID = c.getContID();
		int groupID = c.getGruppi().get(i).getGroupID();

		
		PostAggregazioneDAO aggDao = new PostAggregazioneDAO();
		aggDao.delAggregazione(groupID, contID);


	}

	public static void delEmail(Email e, Contatto c) throws Exception {

		if(e.getMessagingPr()==null) {
			PostEmailDAO eDao = new PostEmailDAO();
			eDao.delEmail(e.getIndirizzo());
			c.getEmail().remove(e);
		} 
		else if(e.getMessagingPr().isEmpty()) {
				PostEmailDAO eDao = new PostEmailDAO();
				eDao.delEmail(e.getIndirizzo());
				c.getEmail().remove(e);
			}
		else throw new Exception("Email è legata a dei MessagingPr");
	}
	
	public static void delSocial(MessagingPr mp, Email e) throws Exception{
		
		PostMessagingPrDAO mpDao = new PostMessagingPrDAO();
		mpDao.delMessagingPr(mp.getPrID());
		
		e.getMessagingPr().remove(mp);
		
		
	}
	
	public static void delRecapito(Contatto c, Recapito re) throws Exception {
		int contID = c.getContID();
		int recID = re.getRecID();

		if(c.getRecapiti().size() > 1) {
			PostRecapitoDAO rDao = new PostRecapitoDAO();
			rDao.delRecapito(recID, contID);
			c.getRecapiti().remove(re);
		}
		else {
			throw new Exception("Il contatto deve avere almeno un Recapito");
		}
	}
	

	public static void delAggregazione(Gruppo g, Contatto c) {
		
		PostAggregazioneDAO agDao = new PostAggregazioneDAO();
		agDao.delAggregazione(g.getGroupID(), c.getContID());
		
		c.getGruppi().remove(g);
		
	}

	

	public static void deleteContact(int selectedRowCount) {
		Contatto c = cList.get(selectedRowCount);
		int contID = c.getContID();

		PostContattoDAO cDao = new PostContattoDAO();
		cDao.delContatto(contID);

		PostAlloggioDAO aDao = new PostAlloggioDAO();
		aDao.delAlloggio(contID);

		PostRecapitoDAO rDao = new PostRecapitoDAO();
		rDao.delRecapito(contID);

		if(!(c.getGruppi()==null) && !(c.getGruppi().isEmpty())) {
			PostAggregazioneDAO gDao = new PostAggregazioneDAO();
			gDao.delAggregazione(contID);
		}
		
		if(!(c.getEmail()==null) && !(c.getEmail().isEmpty())) {
			for(Email e:c.getEmail()) {
				PostMessagingPrDAO mPDao = new PostMessagingPrDAO();
				mPDao.delMessagingPr(e.getIndirizzo());
			}

			PostEmailDAO eDao = new PostEmailDAO();
			eDao.delEmail(contID);
		}


		cList.remove(c);

	}

	private void setConnessione(DatabaseConnect connessione) {
		// TODO Auto-generated method stub
		this.connessione = connessione;

	}

	private DatabaseConnect getConnessione() {
		// TODO Auto-generated method stub

		return connessione;
	}

	public static Contatto getC() {
		return c;
	}

	public static void setC(Contatto c) {
		Controller.c = c;
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

	

	


	

	
	

	

	

}
