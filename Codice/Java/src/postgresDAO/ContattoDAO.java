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
		
		PreparedStatement s;
		String n = null;
		
		try {
			s = link.prepareStatement(
					"SELECT nome " 
					+ "FROM contatto");
			
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				n += rs.getString("nome") + " ";
			}
			
			rs.close();
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}

