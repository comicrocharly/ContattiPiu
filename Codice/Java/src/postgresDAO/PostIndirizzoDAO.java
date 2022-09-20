package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnect;

public class PostIndirizzoDAO {

	private Connection link = null;

	public PostIndirizzoDAO() {
		// TODO Auto-generated constructor stub


		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
	}
	
	private String getAttr(int AddrID, String Attr) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement(
					"SELECT "+ Attr +" " 
							+ "FROM Indirizzo "
							+ "WHERE addr_id = '"+AddrID+"'");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				if(s==null)
					s = rs.getString(Attr);
			}

			rs.close();
			return s;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	//get All ADD
	public ArrayList<String> getAddrID() {

		PreparedStatement ps;
		ArrayList<String> AddrID = new ArrayList<String>();

		try {
			ps = link.prepareStatement(
					"SELECT Addr_ID " 
							+ "FROM Indirizzo ");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				AddrID.add(rs.getString("Addr_ID"));

			}

			rs.close();
			return AddrID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String getCap(int AddrID) {

		String Attr="cap";
		return getAttr(AddrID,Attr);
	}

	public String getCitta(int AddrID) {

		String Attr="citta";
		return getAttr(AddrID,Attr);
	}

	public String getNazione(int AddrID) {

		String Attr="nazione";
		return getAttr(AddrID,Attr);
	}

	public String getVia(int AddrID) {

		String Attr="via";
		return getAttr(AddrID,Attr);
	}
	
	public void delIndirizzo(int AddrID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Indirizzo "
							+ "WHERE Addr_ID = '"+AddrID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setContatto(String via, String citta, String cap, String nazione) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO indirizzo " 
							+ "(via, citta, cap, nazione) "
							+ "VALUES ('"+via+"', '"+citta+"', '"+cap+"', '"+nazione+"');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void upAttr(int AddrID, String Attr,String data) {

		PreparedStatement ps;
			
			try {
				ps = link.prepareStatement(
						"UPDATE Contatto " 
								+ "SET "+Attr+" = '"+data+"' "
								+ "WHERE Addr_ID = '"+AddrID+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void  upCap(int AddrID, String data) {

		String Attr="cap";
		upAttr(AddrID,Attr,data);
	}

	public void  upCitta(int AddrID, String data) {

		String Attr="citta";
		upAttr(AddrID,Attr,data);
	}

	public void  upNazione(int AddrID, String data) {

		String Attr="nazione";
		upAttr(AddrID,Attr,data);
	}

	public void upVia(int AddrID, String data) {

		String Attr="via";
		upAttr(AddrID,Attr,data);
	}

}
