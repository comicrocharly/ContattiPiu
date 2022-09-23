package controller;

import java.sql.SQLException;

import database.DatabaseConnect;
import model.*;
import postgresDAO.*;

public class Controller {

	private DatabaseConnect connessione;
	
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


}
