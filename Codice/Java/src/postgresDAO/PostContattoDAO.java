package postgresDAO;

import java.sql.*;
import java.util.ArrayList;
import DAO.ContattoDAO;
import database.DatabaseConnect;

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


	private String getAttr(int ContID, String Attr) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement(
					"SELECT "+ Attr +" " 
							+ "FROM contatto "
							+ "WHERE Cont_ID = '"+ContID+"'");
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

	//get All ContID
	public ArrayList<String> getContID() {

		PreparedStatement ps;
		ArrayList<String> ContID = new ArrayList<String>();

		try {
			ps = link.prepareStatement(
					"SELECT Cont_ID " 
							+ "FROM contatto ");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				ContID.add(rs.getString("Cont_ID"));

			}

			rs.close();
			return ContID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String getNome(int ContID) {

		String Attr="nome";
		return getAttr(ContID,Attr);
	}

	public String getCognome(int ContID) {

		String Attr="cognome";
		return getAttr(ContID,Attr);
	}

	public String getIndFoto(int ContID) {

		String Attr="Ind_Foto";
		return getAttr(ContID,Attr);
	}

	public Integer getIndirizzoP(int ContID) {

		String Attr="Indirizzo_P";
		return Integer.valueOf(getAttr(ContID,Attr));
	}

	private void upAttr(int ContID, String Attr,String data) {

		PreparedStatement ps;
		int tmp = 0;

		if(Attr == "Indirizzo_P") {
			tmp = Integer.valueOf(data);

			try {
				ps = link.prepareStatement(
						"UPDATE Contatto " 
								+ "SET "+Attr+" = '"+tmp+"' "
								+ "WHERE ContID = '"+ContID+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		else

			try {
				ps = link.prepareStatement(
						"UPDATE Contatto " 
								+ "SET "+Attr+" = '"+data+"' "
								+ "WHERE Cont_ID = '"+ContID+"'");
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void  upNome(int ContID, String data) {

		String Attr="nome";
		upAttr(ContID,Attr,data);
	}

	public void  upCognome(int ContID, String data) {

		String Attr="cognome";
		upAttr(ContID,Attr,data);
	}

	public void  upIndFoto(int ContID, String data) {

		String Attr="Ind_Foto";
		upAttr(ContID,Attr,data);
	}

	public void upIndirizzoP(int ContID, String data) {

		String Attr="Indirizzo_P";
		upAttr(ContID,Attr,data);
	}

	public void setContatto(String Nome, String Cognome, String IndFoto, int IndirizzoP) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO contatto " 
							+ "(nome, cognome, ind_foto, indirizzo_p) "
							+ "VALUES ('"+Nome+"', '"+Cognome+"', '"+IndFoto+"', '"+IndirizzoP+"');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void setContatto(String Nome, String Cognome, int IndirizzoP) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO contatto " 
							+ "(nome, cognome, indirizzo_p) "
							+ "VALUES ('"+Nome+"', '"+Cognome+"', '"+IndirizzoP+"');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delContatto(int ContID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Contatto "
							+ "WHERE Cont_ID = '"+ContID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





}


