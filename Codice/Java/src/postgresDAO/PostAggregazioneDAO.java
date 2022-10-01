package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.AggregazioneDAO;
import database.DatabaseConnect;
import model.Gruppo;

public class PostAggregazioneDAO implements AggregazioneDAO{
	
	private Connection link = null;
	
	public PostAggregazioneDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}
	
	public ArrayList<Gruppo> getGruppi(int contID){
		
		ArrayList<Gruppo> list = new  ArrayList<>();
		
		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"SELECT * "
							+ "FROM Gruppo "
							+ "NATURAL JOIN Aggregazione "
							+ "WHERE Cont_ID = '"+contID+"'  ");
			
			ResultSet rs = ps.executeQuery();

			while(rs.next())
					list.add((Gruppo) rs.getObject("Nome_G, Descrizione, Group_ID"));

			rs.close();
			
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
