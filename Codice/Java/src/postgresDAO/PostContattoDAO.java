package postgresDAO;

import java.sql.*;
import java.util.ArrayList;

import dao.ContattoDAO;
import database.DatabaseConnect;
import model.*;

public class PostContattoDAO implements ContattoDAO {

	private Connection link = null;

	public PostContattoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}
	
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

	public String getNome(int contID) {

		String attr = "nome";
		return getAttr(contID, attr);
	}

	public String getCognome(int contID) {

		String attr = "cognome";
		return getAttr(contID, attr);
	}

	public String getIndFoto(int contID) {

		String attr = "Ind_Foto";
		return getAttr(contID, attr);
	}

	public Integer getIndirizzoP(int contID) {

		String attr = "Indirizzo_P";
		return Integer.valueOf(getAttr(contID, attr));
	}

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

	public void upNome(int contID, String data) {

		String attr = "nome";
		upAttr(contID, attr, data);
	}

	public void upCognome(int contID, String data) {

		String attr = "cognome";
		upAttr(contID, attr, data);
	}

	public void upIndFoto(int contID, String data) {

		String attr = "Ind_Foto";
		upAttr(contID, attr, data);
	}

	public void upIndirizzoP(int contID, Integer addrID) {

		String attr = "Indirizzo_P";
		upAttr(contID, attr, addrID.toString());
	}

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

	public void setContatto(String nome, String cognome, int indirizzoP) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement("INSERT INTO contatto " + "(nome, cognome, indirizzo_p) " + "VALUES ('" + nome
					+ "', '" + cognome + "', '" + indirizzoP + "');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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

	public int setContatto(Contatto c) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contID;
	}

	@Override
	public ArrayList<String> getContID() {
		// TODO Auto-generated method stub
		return null;
	}

}
