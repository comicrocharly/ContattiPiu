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
	
	
	//Funzione che carica i contatti in memoria (da ultimare)
	public ArrayList<Contatto> getContatti() {

		PreparedStatement ps;
		ArrayList<Contatto> cList = new ArrayList<Contatto>();
		Contatto c = null;
		
		
		try {
			ps = link.prepareStatement(
					"SELECT * " 
							+ "FROM contatto ");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
			c= new Contatto(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3));
			cList.add(c);
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cList;
		
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

	private void upAttr(int contID, String attr, String data) {

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


	public void setContatto(Contatto c) {
		// TODO Auto-generated method stub
		
		String nome = c.getNome();
		String cognome = c.getCognome();
		int indirizzoP = c.getIndirizzo().getAddrID();
		
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


	@Override
	public ArrayList<String> getContID() {
		// TODO Auto-generated method stub
		return null;
	}





}


