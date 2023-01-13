package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.AlloggioDAO;
import database.DatabaseConnect;

// TODO: Auto-generated Javadoc
/**
 * The Class PostAlloggioDAO.
 */
public class PostAlloggioDAO implements AlloggioDAO {

	/** The link. */
	private Connection link = null;

	/**
	 * Instantiates a new post alloggio DAO.
	 */
	public PostAlloggioDAO() {
		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the alloggi.
	 *
	 * @param contID the cont ID
	 * @return the alloggi
	 */
	//La funzione restituisce un arraylist contenente gli ID degli indirizzi di contID
	public ArrayList<Integer> getAlloggi(int contID) {

		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Integer> alloggi=new ArrayList<Integer>();
		
		try {
			
			ps=link.prepareStatement("SELECT Addr_ID FROM Alloggio WHERE Cont_ID = '"+contID +"' ");
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				alloggi.add(rs.getInt(1));
				
			}
			
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return alloggi;
	}

	/**
	 * Sets the alloggio.
	 *
	 * @param contID the cont ID
	 * @param addrID the addr ID
	 * @throws SQLException 
	 */
	public void setAlloggio(int contID, int addrID) throws SQLException {

		PreparedStatement ps;

		
			ps = link.prepareStatement("INSERT INTO Alloggio " + "(Cont_ID, Addr_ID) " + 
					"VALUES ('"+contID+"', '"+addrID+"');");

			ps.executeUpdate();

		

	}

	/**
	 * Del alloggio.
	 *
	 * @param contID the cont ID
	 */
	public void delAlloggio(int contID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Alloggio " + "WHERE Cont_ID = '"+contID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Del alloggio.
	 *
	 * @param addrID the addr ID
	 * @param contID the cont ID
	 */
	public void delAlloggio(int addrID, int contID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Alloggio " + "WHERE Cont_ID = '"+contID+"' AND  Addr_ID = '"+addrID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int addrIDAlloggio = -1;
		try {
			ps = link.prepareStatement("SELECT Addr_ID FROM Alloggio WHERE Addr_ID = '"+addrID+"'");

			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				addrIDAlloggio = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(addrIDAlloggio==0) {

			try {
				ps = link.prepareStatement("DELETE FROM Indirizzo " + "WHERE Addr_ID = '"+addrID+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

}
