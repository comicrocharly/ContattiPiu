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


	private String getAttr(int contID, String attr) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement(
					"SELECT "+ attr +" " 
							+ "FROM contatto "
							+ "WHERE Cont_ID = '"+contID+"'");
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
	
	
	//Da aggiornare con return un oggetto arraylist<contatto>
	//get All contID
	public ArrayList<String> getContID() {

		PreparedStatement ps;
		ArrayList<String> contID = new ArrayList<String>();

		try {
			ps = link.prepareStatement(
					"SELECT Cont_ID " 
							+ "FROM contatto ");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				contID.add(rs.getString("Cont_ID"));

			}

			rs.close();
			return contID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String getNome(int contID) {

		String attr="nome";
		return getAttr(contID,attr);
	}

	public String getCognome(int contID) {

		String attr="cognome";
		return getAttr(contID,attr);
	}

	public String getIndFoto(int contID) {

		String attr="Ind_Foto";
		return getAttr(contID,attr);
	}

	public Integer getIndirizzoP(int contID) {

		String attr="Indirizzo_P";
		return Integer.valueOf(getAttr(contID,attr));
	}

	private void upAttr(int contID, String attr,String data) {

		PreparedStatement ps;
		int tmp = 0;

		if(attr == "Indirizzo_P") {
			tmp = Integer.valueOf(data);

			try {
				ps = link.prepareStatement(
						"UPDATE Contatto " 
								+ "SET "+attr+" = '"+tmp+"' "
								+ "WHERE cont_ID = '"+contID+"'");

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
								+ "SET "+attr+" = '"+data+"' "
								+ "WHERE cont_ID = '"+contID+"'");
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void  upNome(int contID, String data) {

		String attr="nome";
		upAttr(contID,attr,data);
	}

	public void  upCognome(int contID, String data) {

		String attr="cognome";
		upAttr(contID,attr,data);
	}

	public void  upIndFoto(int contID, String data) {

		String attr="Ind_Foto";
		upAttr(contID,attr,data);
	}

	public void upIndirizzoP(int contID, String data) {

		String attr="Indirizzo_P";
		upAttr(contID,attr,data);
	}

	public void setContatto(String nome, String cognome, String indFoto, int indirizzoP) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO contatto " 
							+ "(nome, cognome, ind_foto, indirizzo_p) "
							+ "VALUES ('"+nome+"', '"+cognome+"', '"+indFoto+"', '"+indirizzoP+"');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void setContatto(String nome, String cognome, int indirizzoP) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO contatto " 
							+ "(nome, cognome, indirizzo_p) "
							+ "VALUES ('"+nome+"', '"+cognome+"', '"+indirizzoP+"');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delContatto(int contID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Contatto "
							+ "WHERE Cont_ID = '"+contID+"'");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





}


