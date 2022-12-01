package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.AlloggioDAO;
import database.DatabaseConnect;

public class PostAlloggioDAO implements AlloggioDAO {

	private Connection link = null;

	public PostAlloggioDAO() {
		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
	}

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

	public void setAlloggio(int contID, int addrID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement("INSERT INTO Alloggio " + "(Cont_ID, Addr_ID) " + 
					"VALUES ('"+contID+"', '"+addrID+"');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delAlloggio(int contID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Alloggio " + "WHERE Cont_ID = '" + contID + "'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delAlloggio(int addrID, int contID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Alloggio " + "WHERE Cont_ID = '" + contID + "' AND  Addr_ID = '"+addrID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
