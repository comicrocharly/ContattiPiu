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
		ResultSet rs;

		try {
			
			ps=link.prepareStatement("SELECT GROUP_ID FROM AGGREGAZIONE WHERE CONT_ID = ? ");
			ps.setInt(1, contID);
			
			rs=ps.executeQuery();

			while(rs.next()) {
				aggregazione.add(rs.getInt(0));
			}
					
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return aggregazione; 
	}
	
}
