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

	PostAlloggioDAO() {
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
			
			ps=link.prepareStatement("SELECT ADDR_ID FROM ALLOGGIO WHERE CONT_ID = ? ");
			ps.setInt(1, contID);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				alloggi.add(rs.getInt(0));
				
			}
			
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return alloggi;
	}
	

}
