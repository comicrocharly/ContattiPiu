package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.IndirizzoDAO;
import model.Indirizzo;
import database.DatabaseConnect;

// TODO: Auto-generated Javadoc
/**
 * The Class PostIndirizzoDAO.
 */
public class PostIndirizzoDAO implements IndirizzoDAO {

	/** The link. */
	private Connection link = null;

	/**
	 * Instantiates a new post indirizzo DAO.
	 */
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
	
	/**
	 * Gets the indirizzi from database.
	 *
	 * @return an ArrayList<Indirizzo>
	 */
	
	public ArrayList<Indirizzo> getIndirizzi(){
		
		ArrayList<Indirizzo> indList = new ArrayList<Indirizzo>();
		
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps=link.prepareStatement("SELECT * FROM INDIRIZZO");
			rs=ps.executeQuery();
			
			while(rs.next()) {
				indList.add(new Indirizzo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			rs.close();
		}
		catch(SQLException e) {
		e.printStackTrace();
		return null;
		}
		
		return indList; 
	}
	
	/**
	 * Gets the indirizzo.
	 *
	 * @param addrID the addr ID
	 * @return the indirizzo
	 */
	public Indirizzo getIndirizzo(int addrID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"SELECT * "
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

	/**
	 * Gets the attr.
	 *
	 * @param addrID the addr ID
	 * @param attr the attr
	 * @return the attr
	 */
	private String getAttr(int addrID, String attr) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement(
					"SELECT "+ attr +" " 
							+ "FROM Indirizzo "
							+ "WHERE Addr_ID = '"+addrID+"'");
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

	/**
	 * Gets the addr ID.
	 *
	 * @return the addr ID
	 */
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

	/**
	 * Gets the cap.
	 *
	 * @param addrID the addr ID
	 * @return the cap
	 */
	public String getCap(int addrID) {

		String attr="cap";
		return getAttr(addrID,attr);
	}

	/**
	 * Gets the citta.
	 *
	 * @param addrID the addr ID
	 * @return the citta
	 */
	public String getCitta(int addrID) {

		String attr="citta";
		return getAttr(addrID,attr);
	}

	/**
	 * Gets the nazione.
	 *
	 * @param addrID the addr ID
	 * @return the nazione
	 */
	public String getNazione(int addrID) {

		String attr="nazione";
		return getAttr(addrID,attr);
	}

	/**
	 * Gets the via.
	 *
	 * @param addrID the addr ID
	 * @return the via
	 */
	public String getVia(int addrID) {

		String attr="via";
		return getAttr(addrID,attr);
	}

	/**
	 * Del indirizzo.
	 *
	 * @param addrID the addr ID
	 */
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

	/**
	 * Sets the indirizzo.
	 *
	 * @param i the i
	 * @return the int
	 */
	public int setIndirizzo(Indirizzo i) {
		int addrID = 0;
		String via = i.getVia();
		String citta = i.getCitta();
		String cap = i.getCap();
		String nazione = i.getNazione();
		

		PreparedStatement ps;
		Boolean exist = false;
		try {
			ps = link.prepareStatement(
					"SELECT Addr_ID FROM Indirizzo "
					+ "WHERE Via = '"+via+"' AND Citta = '"+citta+"' AND Cap = '"+cap+"' AND Nazione = '"+nazione+"'");

			ResultSet rs = ps.executeQuery();
			if(rs.next())
				if(rs.getInt(1)>0) {
					addrID = rs.getInt(1);
					exist = true;
				}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!exist) {
			try {
				ps = link.prepareStatement(
						"INSERT INTO indirizzo " 
								+ "(via, citta, cap, nazione) "
								+ "VALUES ('"+via+"', '"+citta+"', '"+cap+"', '"+nazione+"')"
								+ "RETURNING Addr_ID ;");

				ResultSet rs = ps.executeQuery();
				if(rs.next())
					addrID = rs.getInt(1);
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return addrID;

	}

	/**
	 * Sets the indirizzo.
	 *
	 * @param addrID the addr ID
	 * @param attr the attr
	 * @param data the data
	 * @return the int
	 */
	
	
	

	/**
	 * Up attr.
	 *
	 * @param addrID the addr ID
	 * @param attr the attr
	 * @param data the data
	 */
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
	
	/**
	 * Up indirizzo.
	 *
	 * @param indirizzo the indirizzo
	 * @param addrID the addr ID
	 */
	public void upIndirizzo(Indirizzo indirizzo, int addrID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"UPDATE Indirizzo " 
							+ "SET Nazione = '"+indirizzo.getNazione()+"',"
									+ "Citta = '"+indirizzo.getCitta()+"',"
											+ "Via = '"+indirizzo.getVia()+"',"
													+ "Cap = '"+indirizzo.getCap()+"'"
							+ "WHERE Addr_ID = '"+addrID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * Up cap.
	 *
	 * @param addrID the addr ID
	 * @param data the data
	 */
	public void  upCap(int addrID, String data) {

		String attr="cap";
		upAttr(addrID,attr,data);
	}

	/**
	 * Up citta.
	 *
	 * @param addrID the addr ID
	 * @param data the data
	 */
	public void  upCitta(int addrID, String data) {

		String attr="citta";
		upAttr(addrID,attr,data);
	}

	/**
	 * Up nazione.
	 *
	 * @param addrID the addr ID
	 * @param data the data
	 */
	public void  upNazione(int addrID, String data) {

		String attr="nazione";
		upAttr(addrID,attr,data);
	}

	/**
	 * Up via.
	 *
	 * @param addrID the addr ID
	 * @param data the data
	 */
	public void upVia(int addrID, String data) {

		String attr="via";
		upAttr(addrID,attr,data);
	}

	
}
