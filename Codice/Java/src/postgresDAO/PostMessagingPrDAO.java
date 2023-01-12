package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MessagingPrDAO;
import database.DatabaseConnect;
import model.MessagingPr;

// TODO: Auto-generated Javadoc
/**
 * The Class PostMessagingPrDAO.
 */
public class PostMessagingPrDAO implements MessagingPrDAO {
	
	/** The link. */
	private Connection link = null;

	/**
	 * Instantiates a new post messaging pr DAO.
	 */
	public PostMessagingPrDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the messaging PR.
	 *
	 * @param indirizzo the indirizzo
	 * @return the messaging PR
	 */
	public ArrayList<MessagingPr> getMessagingPR(String indirizzo){

		ArrayList<MessagingPr> mList = new  ArrayList<MessagingPr>();

		PreparedStatement ps;

		try {
			ps = link.prepareStatement("SELECT * FROM MESSAGINGPR WHERE INDIRIZZO = '"+indirizzo+"' ");
			

			ResultSet rs = ps.executeQuery();

			while(rs.next())
				mList.add(new MessagingPr(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));

			rs.close();

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return mList;
	}
	
	/**
	 * Sets the messaging PR.
	 *
	 * @param email the email
	 * @param fornitore the fornitore
	 * @param fraseBenvenuto the frase benvenuto
	 * @param nickname the nickname
	 */
	public int setMessagingPR(String email, String fornitore, String fraseBenvenuto, String nickname) {

		PreparedStatement ps;
		ResultSet rs;
		
		int pr_id=-1;

		try {
			ps = link.prepareStatement(
					"INSERT INTO MessagingPR " 
							+ "(indirizzo, fornitore, frase_benvenuto, nickname) "
							+ "VALUES ('"+email+"', '"+fornitore+"', '"+fraseBenvenuto+"', '"+nickname+"' ) RETURNING pr_id;");

			rs=ps.executeQuery();
			rs.next();
			pr_id=rs.getInt(1);
			System.out.println(pr_id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pr_id;

	}

	/**
	 * Del messaging pr.
	 *
	 * @param indirizzo the indirizzo
	 */
	public void delMessagingPr(String indirizzo) {
		
			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"DELETE FROM MessagingPr "
								+ "WHERE indirizzo = '"+indirizzo+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}

	/**
	 * Del messaging pr.
	 *
	 * @param prID the pr ID
	 */
	public void delMessagingPr(int prID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM MessagingPr "
							+ "WHERE Pr_ID = '"+prID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
