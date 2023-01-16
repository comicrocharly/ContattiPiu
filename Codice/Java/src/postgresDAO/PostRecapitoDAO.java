package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import database.DatabaseConnect;
import java.util.ArrayList;

import dao.RecapitoDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostRecapitoDAO.
 */
public class PostRecapitoDAO implements RecapitoDAO {
	
	/** The link. */
	private Connection link = null;

	/**
	 * Instantiates a new post recapito DAO.
	 */
	public PostRecapitoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}
	
	/**
	 * Match tel.
	 *
	 * @param tList the t list
	 * @param prefisso the prefisso
	 * @param numero the numero
	 * @return the telefono
	 */
	public Telefono matchTel(ArrayList<Telefono> tList,String prefisso,String numero) {
		boolean x = false, y = false;
		Telefono metchedTel = null;
		
		for(Telefono tel: tList) {
			
			x = tel.getNumero().trim().equals(numero.trim());
			y = tel.getPrefisso().trim().equals(prefisso.trim());
			
			if(x && y) {
				metchedTel=tel;
				break;
			}
		}
		
		return metchedTel;
	}


	/**
	 * Gets the recapiti.
	 *
	 * @param tList the t list
	 * @param contID the cont ID
	 * @return the recapiti
	 */
	public ArrayList<Recapito> getRecapiti(ArrayList<Telefono> tList,int contID) {
		
		ArrayList<Recapito> rList = new  ArrayList<Recapito>();
		
		PreparedStatement ps;
		

		try {
			
			ps = link.prepareStatement("SELECT * FROM Recapito WHERE Cont_ID = '"+contID+"' ");
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rList.add(new Recapito(rs.getInt(1),matchTel(tList,rs.getString(4),rs.getString(3)),matchTel(tList,rs.getString(6),rs.getString(5))));

			}
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return rList;

	}
	
	/**
	 * Gets the recapiti.
	 *
	 * @param tList the t list
	 * @return the recapiti
	 */
	public ArrayList<Recapito> getRecapiti(ArrayList<Telefono> tList) {
		
		ArrayList<Recapito> rList = new  ArrayList<Recapito>();
		
		PreparedStatement ps;
		

		try {
			
			ps = link.prepareStatement("SELECT * FROM Recapito ");
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rList.add(new Recapito(rs.getInt(1),matchTel(tList,rs.getString(4),rs.getString(3)),matchTel(tList,rs.getString(6),rs.getString(5))));

			}
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return rList;

	}
	
	/**
	 * Check telefono.
	 *
	 * @param prefisso the prefisso
	 * @param numero the numero
	 * @return true, if successful
	 */
	public boolean checkTelefono(String prefisso, String numero) {
		
		PreparedStatement ps;
		
		int tmp = 0;
		
		try {
			ps = link.prepareStatement(
					"SELECT COUNT(*) "
							+ "FROM Telefono "
							+ "WHERE numero = '"+numero+"' AND prefisso = '"+prefisso+"' ");
			
			ResultSet rs = ps.executeQuery();

			
			if(rs.next())
				tmp = rs.getInt(1);
			
			rs.close();

			if(tmp>1)
				return true;
			else 
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	


	
	/**
	 * Sets the recapito.
	 *
	 * @param contID the cont ID
	 * @param prefissoIn the prefisso in
	 * @param numeroIn the numero in
	 * @param prefissoOut the prefisso out
	 * @param numeroOut the numero out
	 * @return the int
	 * @throws Exception the exception
	 */
	public int setRecapito(int contID, String prefissoIn, String numeroIn, String prefissoOut, String numeroOut) throws Exception {

		PreparedStatement ps;
		ResultSet rs;
		
		int recID = -1;

		try {
			ps = link.prepareStatement(
					"INSERT INTO Recapito (prefisso, prefisso_out, numero, numero_out, Cont_ID) "
							+ "VALUES ('"+prefissoIn+"', '"+prefissoOut+"', '"+numeroIn+"', '"+numeroOut+"', '"+contID+"') returning rec_id;");

			rs = ps.executeQuery();
			rs.next();
			recID = rs.getInt(1);


		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return recID;
	}


	/**
	 * Up tin recapito.
	 *
	 * @param tIn the t in
	 * @param r the r
	 */
	public void upTinRecapito(Telefono tIn, Recapito r) {
		PreparedStatement ps;
		
		try {
			ps = link.prepareStatement(" UPDATE Recapito "
					+ "SET Numero = '"+tIn.getNumero()+"', Prefisso = '"+tIn.getPrefisso()+"'"
							+ "WHERE Rec_ID = '"+r.getRecID()+"'");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Up tout recapito.
	 *
	 * @param tOut the t out
	 * @param r the r
	 */
	public void upToutRecapito(Telefono tOut, Recapito r) {
		PreparedStatement ps;
		
		try {
			ps = link.prepareStatement(" UPDATE Recapito "
					+ "SET Numero_Out = '"+tOut.getNumero()+"', Prefisso_Out = '"+tOut.getPrefisso()+"'"
							+ "WHERE Rec_ID = '"+r.getRecID()+"'");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * Del recapito.
	 *
	 * @param contID the cont ID
	 * @param prefissoIn the prefisso in
	 * @param prefissoOut the prefisso out
	 * @param numeroIn the numero in
	 * @param numeroOut the numero out
	 */
	public void delRecapito(int contID, String prefissoIn, String prefissoOut, String numeroIn, String numeroOut ) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Contatto "
							+ "WHERE Cont_ID = '"+contID+"' AND prefisso_In '"+prefissoIn+"'AND prefisso_out '"+prefissoOut+"'AND numero_in '"+numeroIn+"' AND numero_out'"+numeroOut+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Del recapito.
	 *
	 * @param contID the cont ID
	 */
	public void delRecapito(int contID) {
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Recapito " + "WHERE Cont_ID = '" + contID + "'");

			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Del recapito.
	 *
	 * @param recID the rec ID
	 * @param contID the cont ID
	 * @throws SQLException the SQL exception
	 */
	public void delRecapito(int recID, int contID) throws SQLException {
		
		PreparedStatement ps;

		
			ps = link.prepareStatement("DELETE FROM Recapito WHERE Cont_ID = '" + contID + "' AND Rec_ID = '"+recID+"' ");

			ps.executeUpdate();
		
		
	}
	
	/**
	 * Switch trigger.
	 *
	 * @param mode the mode
	 */
	public void switchTrigger(String mode) {
		
		PreparedStatement ps;
		try {
		ps = link.prepareStatement("ALTER TABLE RECAPITO " + mode + " TRIGGER ALL");
		
		ps.executeUpdate();
		ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


}



	
		