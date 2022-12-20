package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.AggregazioneDAO;
import database.DatabaseConnect;

// TODO: Auto-generated Javadoc
/**
 * The Class PostAggregazioneDAO.
 */
public class PostAggregazioneDAO implements AggregazioneDAO{
	
	/** The link. */
	private Connection link = null;
	
	/**
	 * Instantiates a new post aggregazione DAO.
	 */
	public PostAggregazioneDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the aggregazioni.
	 *
	 * @param contID the cont ID
	 * @return the aggregazioni
	 */
	public ArrayList<Integer> getAggregazioni(int contID){
		
		ArrayList<Integer> aggregazione = new  ArrayList<Integer>();
		
		PreparedStatement ps;
	

		try {
			
			ps = link.prepareStatement("SELECT GROUP_ID FROM AGGREGAZIONE WHERE CONT_ID = '"+contID+"' ");
			
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				aggregazione.add(rs.getInt(1));
			}
					
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return aggregazione; 
	}

	/**
	 * Del aggregazione.
	 *
	 * @param contID the cont ID
	 */
	public void delAggregazione(int contID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Aggregazione " + "WHERE Cont_ID = '" + contID + "'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Del aggregazione.
	 *
	 * @param groupID the group ID
	 * @param contID the cont ID
	 */
	public void delAggregazione(int groupID, int contID) {
		int gCont = 1;
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Aggregazione " + "WHERE Cont_ID = '" + contID + "' AND Group_ID = '"+groupID+"' ");

			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ps = link.prepareStatement("SELECT COUNT(*) FROM Aggregazione WHERE Group_ID = '"+groupID+"' ");

			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				gCont = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(gCont==0) {
			try {
				ps = link.prepareStatement("DELETE FROM Gruppo WHERE Group_ID = '"+groupID+"' ");

				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Sets the aggregazione.
	 *
	 * @param contID the cont ID
	 * @param groupID the group ID
	 * @throws Exception the exception
	 */
	public void setAggregazione(Integer contID, Integer groupID) throws Exception {
		
		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO Aggregazione "
							+ "(Cont_ID, Group_ID) "
							+ "VALUES ('"+contID+"', '"+groupID+"' );");
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new Exception("Exception message");
			
		}
		
	}
	
}
