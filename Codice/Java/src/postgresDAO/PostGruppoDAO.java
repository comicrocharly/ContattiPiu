package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GruppoDAO;
import database.DatabaseConnect;
import model.Gruppo;


public class PostGruppoDAO implements GruppoDAO{

	private Connection link = null;

	public PostGruppoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}

	//Ritorna la lista dei gruppi presenti in memoria
	public ArrayList<Gruppo> getGruppi(){

		ArrayList<Gruppo> gList = new  ArrayList<Gruppo>();

		PreparedStatement ps;
		ResultSet rs;

		try {
			ps= link.prepareStatement("SELECT * FROM GRUPPO");
			rs= ps.executeQuery();
			
			while(rs.next()) {
				gList.add(new Gruppo(rs.getInt(0),rs.getString(1),rs.getString(2)));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return gList;
	}

	public void setGruppo(String nomeG, String descrizione) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO Gruppo "
							+ "(nome_G, descrizione) "
							+ "VALUES ('"+nomeG+"', '"+descrizione+"' );");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delGruppo(String groupID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Gruppo "
							+ "WHERE (Group_ID = '"+groupID+"' );");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
