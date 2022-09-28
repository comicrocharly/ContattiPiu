package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnect;
import model.MessagingPr;

public class PostMessagingPrDAO {
	
	private Connection link = null;

	public PostMessagingPrDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}
	
	public ArrayList<MessagingPr> getMessagingPR(String indirizzo){

		ArrayList<MessagingPr> list = new  ArrayList<>();

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"SELECT * "
							+ "FROM MessagingPR "
							+ "WHERE Con	t_ID = '"+indirizzo+"'  ");

			ResultSet rs = ps.executeQuery();

			while(rs.next())
				list.add((MessagingPr) rs.getObject("email, fornitore, frase_Benvenuto, nickname, pr_ID"));

			rs.close();

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void setMessagingPR(String email, String fornitore, String fraseBenvenuto, String nickname) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO contatto " 
							+ "(email, fornitore, fraseBenvenuto, nickname) "
							+ "VALUES ('"+email+"', '"+fornitore+"', '"+fraseBenvenuto+"', '"+nickname+"' );");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
