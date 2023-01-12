package postgresDAO;

import java.sql.*;
import java.util.ArrayList;

import dao.ContattoDAO;
import database.DatabaseConnect;
import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class PostContattoDAO.
 */
public class PostContattoDAO implements ContattoDAO {

	/** The link. */
	private Connection link = null;

	/**
	 * Instantiates a new post contatto DAO.
	 */
	public PostContattoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the attr.
	 *
	 * @param contID the cont ID
	 * @param attr the attr
	 * @return the attr
	 */
	private String getAttr(int contID, String attr) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement("SELECT " + attr + " " + "FROM contatto " + "WHERE Cont_ID = '" + contID + "'");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (s == null)
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
	 * Gets the contatti.
	 *
	 * @return the contatti
	 */
	// Funzione che carica i contatti in memoria (da ultimare)
	public ArrayList<Contatto> getContatti() {

		ResultSet rs;
		PreparedStatement ps;
		ArrayList<Contatto> cList = new ArrayList<Contatto>();

		try {
			ps = link.prepareStatement("SELECT * FROM CONTATTO");
			rs = ps.executeQuery();

			while (rs.next()) {
				cList.add(new Contatto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5)));

			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cList;

	}

	/**
	 * Gets the nome.
	 *
	 * @param contID the cont ID
	 * @return the nome
	 */
	public String getNome(int contID) {

		String attr = "nome";
		return getAttr(contID, attr);
	}

	/**
	 * Gets the cognome.
	 *
	 * @param contID the cont ID
	 * @return the cognome
	 */
	public String getCognome(int contID) {

		String attr = "cognome";
		return getAttr(contID, attr);
	}

	/**
	 * Gets the ind foto.
	 *
	 * @param contID the cont ID
	 * @return the ind foto
	 */
	public String getIndFoto(int contID) {

		String attr = "Ind_Foto";
		return getAttr(contID, attr);
	}

	/**
	 * Gets the indirizzo P.
	 *
	 * @param contID the cont ID
	 * @return the indirizzo P
	 */
	public Integer getIndirizzoP(int contID) {

		String attr = "Indirizzo_P";
		return Integer.valueOf(getAttr(contID, attr));
	}

	/**
	 * Up attr.
	 *
	 * @param contID the cont ID
	 * @param attr the attr
	 * @param data the data
	 */
	private void upAttr(int contID, String attr, String data) {

		PreparedStatement ps;
		int tmp = 0;

		if (attr == "Indirizzo_P") {
			tmp = Integer.valueOf(data);

			try {
				ps = link.prepareStatement(
						"UPDATE Contatto " + "SET " + attr + " = '" + tmp + "' " + "WHERE cont_ID = '" + contID + "'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else

			try {
				ps = link.prepareStatement(
						"UPDATE Contatto " + "SET " + attr + " = '" + data + "' " + "WHERE cont_ID = '" + contID + "'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	/**
	 * Up nome.
	 *
	 * @param contID the cont ID
	 * @param data the data
	 */
	public void upNome(int contID, String data) {

		String attr = "nome";
		upAttr(contID, attr, data);
	}

	/**
	 * Up cognome.
	 *
	 * @param contID the cont ID
	 * @param data the data
	 */
	public void upCognome(int contID, String data) {

		String attr = "cognome";
		upAttr(contID, attr, data);
	}

	/**
	 * Up ind foto.
	 *
	 * @param contID the cont ID
	 * @param data the data
	 */
	public void upIndFoto(int contID, String data) {

		String attr = "Ind_Foto";
		upAttr(contID, attr, data);
	}

	/**
	 * Up indirizzo P.
	 *
	 * @param contID the cont ID
	 * @param addrID the addr ID
	 */
	public void upIndirizzoP(int contID, Integer addrID) {

		String attr = "Indirizzo_P";
		upAttr(contID, attr, addrID.toString());
	}

	/**
	 * Sets the contatto.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param indFoto the ind foto
	 * @param indirizzoP the indirizzo P
	 * @return the int
	 */
	public int setContatto(String nome, String cognome, String indFoto, int indirizzoP) {
		int contID = 0;
		PreparedStatement ps;

		try {
			ps = link.prepareStatement("INSERT INTO contatto " + "(nome, cognome, ind_foto, indirizzo_p) " + "VALUES ('"
					+ nome + "', '" + cognome + "', '" + indFoto + "', '" + indirizzoP + "')"
							+ "RETURNNING Cont_ID;");

			ResultSet rs = ps.executeQuery();
			contID = rs.getInt(0);
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return contID;

	}

	/**
	 * Sets the contatto.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param indirizzoP the indirizzo P
	 * @throws Exception 
	 */
	public void setContatto(String nome, String cognome, int indirizzoP) throws Exception {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement("INSERT INTO contatto " + "(nome, cognome, indirizzo_p) " + "VALUES ('" + nome
					+ "', '" + cognome + "', '" + indirizzoP + "');");

			ps.executeUpdate();

		} catch (SQLException e) {

			if(e.getMessage().contains("character varying")) {
				throw new Exception("Il campo Contatto deve essere lungo "+ e.getMessage().charAt(e.getMessage().length()-2));
			}
			else
				e.printStackTrace();
		}

	}

	/**
	 * Del contatto.
	 *
	 * @param contID the cont ID
	 */
	public void delContatto(int contID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement("DELETE FROM Contatto " + "WHERE Cont_ID = '" + contID + "'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sets the contatto.
	 *
	 * @param c the c
	 * @return the int
	 * @throws Exception 
	 */
	public int setContatto(Contatto c) throws Exception {
		// TODO Auto-generated method stub
		int contID = 0;
		String nome = c.getNome();
		String cognome = c.getCognome();
		int indirizzoP = c.getIndirizzoP();

		PreparedStatement ps;
		try {
			ps = link.prepareStatement("INSERT INTO contatto " + "(nome, cognome, indirizzo_p) " + "VALUES ('" + nome
					+ "', '" + cognome + "', '" + indirizzoP + "')"
							+ "RETURNING Cont_ID ;");

			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				contID = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			if(e.getMessage().contains("character varying")) {
				throw new Exception("Il campo Contatto deve essere lungo 50");
			}
			e.printStackTrace();
		}
		return contID;
	}

	/**
	 * Gets the cont ID.
	 *
	 * @return the cont ID
	 */
	@Override
	public ArrayList<String> getContID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateName(int id,String Nome,String Cognome) {
		
		PreparedStatement ps;
		
		try {
			ps=link.prepareStatement("update contatto set nome = '"+Nome+"', cognome = '"+Cognome+"' where cont_id = "+id);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
