package postgresDAO;

import java.sql.*;
import model.*;
import database.DatabaseConnect;

public class ContattoDAO {
	
	private Connection link;
	private Contatto c;

	public ContattoDAO() {
		
		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
		
	}
	
	public String getNome() {
		
		PreparedStatement ps;
		String s = null;
		
		try {
			ps = link.prepareStatement(
					"SELECT nome " 
					+ "FROM contatto");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(s==null)
					s = rs.getString("nome") + " ";
				s += rs.getString("nome") + " ";
			}
			
			rs.close();
			return s;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getCognome() {

		PreparedStatement ps;
		String s = null;
		try {
			ps = link.prepareStatement(
					"SELECT cognome " 
					+ "FROM contatto");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(s==null)
					s = rs.getString("cognome") + " ";
				s += rs.getString("cognome") + " ";
			}

			rs.close();
			return s;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public String getIndFoto() {


		PreparedStatement ps;
		String s = null;
		try {
			ps = link.prepareStatement(
					"SELECT Ind_Foto " 
					+ "FROM contatto");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(s==null)
					s = rs.getString("Ind_Foto") + " ";
				s += rs.getString("Ind_Foto") + " ";
			}

			rs.close();
			return s;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	
	}
	
	public String getIndirizzoP() {


		PreparedStatement ps;
		String s = null;
		try {
			ps = link.prepareStatement(
					"SELECT Ind_Foto " 
					+ "FROM contatto");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(s==null)
					s = rs.getString("Indirizzo_P") + " ";
				s += rs.getString("Indirizzo_P") + " ";
			}

			rs.close();
			return s;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	
	}
}


