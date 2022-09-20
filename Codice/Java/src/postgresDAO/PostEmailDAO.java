package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.EmailDAO;
import database.DatabaseConnect;

public class PostEmailDAO {
	
		private Connection link = null;

		public PostEmailDAO() {

			try {
				link = DatabaseConnect.getInstance().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Connection failed.");
				e.printStackTrace();
			}

		}


		private String getAttr(String Indirizzo, String Attr) {

			PreparedStatement ps;
			String s = null;

			try {
				ps = link.prepareStatement(
						"SELECT "+ Attr +" " 
								+ "FROM Email "
								+ "WHERE Indirizzo = '"+Indirizzo+"'");
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

		//get All Indirizzo
		public ArrayList<String> getEmail() {

			PreparedStatement ps;
			ArrayList<String> Indirizzo = new ArrayList<String>();

			try {
				ps = link.prepareStatement(
						"SELECT Indirizzo " 
								+ "FROM Email ");
				ResultSet rs = ps.executeQuery();

				while(rs.next()) {

					Indirizzo.add(rs.getString("Indirizzo"));

				}

				rs.close();
				return Indirizzo;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		public String getIndirizzo(String Indirizzo) {

			String Attr="Indirizzo";
			return getAttr(Indirizzo,Attr);
		}

		public String getUtilizzo(String Indirizzo) {

			String Attr="utilizzo";
			return getAttr(Indirizzo,Attr);
		}


		private void upAttr(String Indirizzo, String Attr,String data) {

			PreparedStatement ps;


			try {
				ps = link.prepareStatement(
						"UPDATE Email " 
								+ "SET "+Attr+" = '"+data+"' "
								+ "WHERE Indirizzo = '"+Indirizzo+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




		}

		public void  upIndirizzo(String Indirizzo, String data) {

			String Attr="indirizzo";
			upAttr(Indirizzo,Attr,data);
		}

		public void  unUtilizzo(String Indirizzo, String data) {

			String Attr="utilizzo";
			upAttr(Indirizzo,Attr,data);
		}

	

		public void setEmail(String Indirizzo, String Utilizzo) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"INSERT INTO Email " 
								+ "(Indirizzo, Utilizzo) "
								+ "VALUES ('"+Indirizzo+"', '"+Utilizzo+"' );");

				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void setEmail(String Indirizzo) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"INSERT INTO Email " 
								+ "(indirizzo ) "
								+ "VALUES ('"+Indirizzo+"');");

				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void delEmail(String Indirizzo) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"DELETE FROM Email "
								+ "WHERE Indirizzo = '"+Indirizzo+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}





	}


