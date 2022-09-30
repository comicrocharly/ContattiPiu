package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Indirizzo;
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
	
	public Indirizzo getIndirizzo(int addrID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"SELECT *"
							+ "FROM indirizzo " 
							+ "WHERE Addr_ID = '"+addrID+"'");

			ResultSet rs = ps.executeQuery();


			Indirizzo i = new Indirizzo(rs.getInt(addrID), rs.getString("via"), rs.getString("citta"), rs.getString("cap"), rs.getString("nazione"));

			return i;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;

	}

	private String getAttr(int addrID, String attr) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement(
					"SELECT "+ attr +" " 
							+ "FROM Indirizzo "
							+ "WHERE addr_id = '"+addrID+"'");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				if(s==null)
					s = rs.getString(attr);
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
		ArrayList<String> addrID = new ArrayList<String>();

		try {
			ps = link.prepareStatement(
					"SELECT Addr_ID " 
							+ "FROM Indirizzo ");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				addrID.add(rs.getString("Addr_ID"));

			}

			rs.close();
			return addrID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String getCap(int addrID) {

		String attr="cap";
		return getAttr(addrID,attr);
	}

	public String getCitta(int addrID) {

		String attr="citta";
		return getAttr(addrID,attr);
	}

	public String getNazione(int addrID) {

		String attr="nazione";
		return getAttr(addrID,attr);
	}

	public String getVia(int addrID) {

		String attr="via";
		return getAttr(addrID,attr);
	}

	public void delIndirizzo(int addrID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Indirizzo "
							+ "WHERE Addr_ID = '"+addrID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setIndirizzo(Indirizzo i) {
		
		String via = i.getVia();
		String citta = i.getCitta();
		String cap = i.getCap();
		String nazione = i.getNazione();
		

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

	private void upAttr(int addrID, String attr,String data) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"UPDATE Contatto " 
							+ "SET "+attr+" = '"+data+"' "
							+ "WHERE Addr_ID = '"+addrID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void  upCap(int addrID, String data) {

		String attr="cap";
		upAttr(addrID,attr,data);
	}

	public void  upCitta(int addrID, String data) {

		String attr="citta";
		upAttr(addrID,attr,data);
	}

	public void  upNazione(int addrID, String data) {

		String attr="nazione";
		upAttr(addrID,attr,data);
	}

	public void upVia(int addrID, String data) {

		String attr="via";
		upAttr(addrID,attr,data);
	}

}
