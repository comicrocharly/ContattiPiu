package controller;

import java.sql.SQLException;

import database.DatabaseConnect;
import model.*;
import postgresDAO.*;

public class Controller {
	
	private Contatto c;
	private Indirizzo i;
	private Telefono t;
	
	private DatabaseConnect connessione;
	
	//Inserisce un contatto in DB tramite DAO
	
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
		if(getConnessione().getConnection() == null) return false;

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
