package controller;

import java.sql.SQLException;

import database.DatabaseConnect;
import model.*;
import postgresDAO.*;

public class Controller {
	
	private Contatto c;
	private Indirizzo i;
	
	private DatabaseConnect connessione;
	
	//Inserisce un contatto in DB tramite DAO
	public void assignContatto() {
		
		setC(new Contatto(getC().getContID(), getC().getNome(), getC().getCognome(), getC().getIndFoto(), getC().getIndirizzo()));
		PostContattoDAO contattoDAO = new PostContattoDAO();
		contattoDAO.setContatto(getC());
		
	}
	
	public void assignIndirizzo() {
		
		setI(new Indirizzo(getI().getAddrID(), getI().getCap(), getI().getCognome(), getI().getIndFoto(), getI().getIndirizzo()));
		PostContattoDAO contattoDAO = new PostContattoDAO();
		contattoDAO.setContatto(getI());
		
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


}
