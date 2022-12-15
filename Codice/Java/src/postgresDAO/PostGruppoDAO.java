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
				gList.add(new Gruppo(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return gList;
	}

	public int setGruppo(String nomeG, String descrizione) {
		int groupID = -1;
		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO Gruppo "
							+ "(nome_G, descrizione) "
							+ "VALUES ('"+nomeG+"', '"+descrizione+"' )"
									+ "RETURNING Group_ID;");
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				groupID = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupID;
	}

	public void delGruppo(String groupID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Gruppo "
							+ "WHERE (Group_ID = '"+groupID+"' );");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
