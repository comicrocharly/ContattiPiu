package postgresDAO;

import java.sql.*;
import database.DatabaseConnect;

public class ContattoDAO {

	private Connection link = null;

	public ContattoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}


	private String getAttr(int Cont_ID, String Attr) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement(
					"SELECT "+ Attr +" " 
							+ "FROM contatto "
							+ "WHERE cont_id = '"+Cont_ID+"'");
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
	public String getNome(int Cont_ID) {
		
		String Attr="nome";
		return getAttr(Cont_ID,Attr);
	}

	public String getCognome(int Cont_ID) {
		
		String Attr="cognome";
		return getAttr(Cont_ID,Attr);
	}

	public String getIndFoto(int Cont_ID) {

		String Attr="Ind_Foto";
		return getAttr(Cont_ID,Attr);
	}

	public Integer getIndirizzoP(int Cont_ID) {

		String Attr="Indirizzo_P";
		return Integer.valueOf(getAttr(Cont_ID,Attr));
	}

}


