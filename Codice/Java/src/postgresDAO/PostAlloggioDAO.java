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

	public ArrayList<int[]> getAlloggi() {

		PreparedStatement ps;
		ResultSet rs;
		int [] contToInd;
		ArrayList<int[]> alloggi=new ArrayList<int[]>();
		
		try {
			
			ps=link.prepareStatement("SELECT * FROM ALLOGGIO");
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				contToInd=new int[2];
				contToInd[0]=rs.getInt(0);
				contToInd[1]=rs.getInt(1);
				alloggi.add(contToInd);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return alloggi;
	}

}
