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
	
	public ArrayList<Integer> getAggregazioni(int contID){
		
		ArrayList<Integer> aggregazione = new  ArrayList<Integer>();
		
		PreparedStatement ps;
	

		try {
			
			ps = link.prepareStatement("SELECT GROUP_ID FROM AGGREGAZIONE WHERE CONT_ID = '"+contID+"' ");
			
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				aggregazione.add(rs.getInt(1));
			}
					
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return aggregazione; 
	}

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
	
}
