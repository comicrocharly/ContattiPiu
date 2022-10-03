package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MessagingPrDAO;
import database.DatabaseConnect;
import model.MessagingPr;

public class PostMessagingPrDAO implements MessagingPrDAO {
	
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

		ArrayList<MessagingPr> mList = new  ArrayList<MessagingPr>();

		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = link.prepareStatement("SELECT * FROM MESSAGINGPR WHERE INDIRIZZO = ? ");
			ps.setString(1, indirizzo);

			rs = ps.executeQuery();

			while(rs.next())
				mList.add(new MessagingPr(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3)));

			rs.close();

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return mList;
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
