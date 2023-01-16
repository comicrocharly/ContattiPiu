package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GruppoDAO;
import database.DatabaseConnect;
import model.Gruppo;


// TODO: Auto-generated Javadoc
/**
 * The Class PostGruppoDAO.
 */
public class PostGruppoDAO implements GruppoDAO{

	/** The link. */
	private Connection link = null;

	/**
	 * Instantiates a new post gruppo DAO.
	 */
	public PostGruppoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}

	/**
	 * Gets the gruppi.
	 *
	 * @return the gruppi
	 */
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

	/**
	 * Sets the gruppo.
	 *
	 * @param nomeG the nome G
	 * @param descrizione the descrizione
	 * @return the int
	 */
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

	/**
	 * Del gruppo.
	 *
	 * @param groupID the group ID
	 */
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

	/**
	 * Up gruppo.
	 *
	 * @param nomeG the nome G
	 * @param descrizione the descrizione
	 * @param groupID the group ID
	 */
	public void upGruppo(String nomeG, String descrizione, int groupID) {
		PreparedStatement ps;


		try {
			ps = link.prepareStatement(
					"UPDATE Gruppo " 
							+ "SET Nome_G = '"+nomeG+"', Descrizione = '"+descrizione+"'"
							+ "WHERE Group_ID = '"+groupID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
