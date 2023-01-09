package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.EmailDAO;
import database.DatabaseConnect;
import model.Email;

// TODO: Auto-generated Javadoc
/**
 * The Class PostEmailDAO.
 */
public class PostEmailDAO implements EmailDAO{
	
		/** The link. */
		private Connection link = null;

		/**
		 * Instantiates a new post email DAO.
		 */
		public PostEmailDAO() {

			try {
				link = DatabaseConnect.getInstance().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Connection failed.");
				e.printStackTrace();
			}

		}
		
		/**
		 * Gets the email.
		 *
		 * @param contID the cont ID
		 * @return the email
		 */
		//Funzione che dato un ID Contatto ci restituisce sotto arraylist i suoi indirizzi email
		public ArrayList<Email> getEmail(int contID) {
			
			
			ArrayList<Email> eList=new ArrayList<Email>();
			ResultSet rs;
			PreparedStatement ps;
			
			try {
				
				ps=link.prepareStatement("SELECT * FROM EMAIL WHERE CONT_ID = ? ");
				ps.setInt(1, contID);
				
				rs=ps.executeQuery();
				
				while(rs.next()) {
					eList.add(new Email(rs.getString(1),rs.getString(2)));
				}
				
				rs.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
			
			return eList;
		}


		/**
		 * Gets the attr.
		 *
		 * @param indirizzo the indirizzo
		 * @param attr the attr
		 * @return the attr
		 */
		private String getAttr(String indirizzo, String attr) {

			PreparedStatement ps;
			String s = null;

			try {
				ps = link.prepareStatement(
						"SELECT "+ attr +" " 
								+ "FROM Email "
								+ "WHERE indirizzo = '"+indirizzo+"'");
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
		 * Gets the email.
		 *
		 * @return the email
		 */
		//get All indirizzo
		public ArrayList<String> getEmail() {

			PreparedStatement ps;
			ArrayList<String> indirizzo = new ArrayList<String>();

			try {
				ps = link.prepareStatement(
						"SELECT indirizzo " 
								+ "FROM Email ");
				ResultSet rs = ps.executeQuery();

				while(rs.next()) {

					indirizzo.add(rs.getString("indirizzo"));

				}

				rs.close();
				return indirizzo;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		/**
		 * Gets the indirizzo.
		 *
		 * @param indirizzo the indirizzo
		 * @return the indirizzo
		 */
		public String getIndirizzo(String indirizzo) {

			String attr="indirizzo";
			return getAttr(indirizzo,attr);
		}

		/**
		 * Gets the utilizzo.
		 *
		 * @param indirizzo the indirizzo
		 * @return the utilizzo
		 */
		public String getUtilizzo(String indirizzo) {

			String attr="utilizzo";
			return getAttr(indirizzo,attr);
		}


		/**
		 * Up attr.
		 *
		 * @param indirizzo the indirizzo
		 * @param attr the attr
		 * @param data the data
		 */
		private void upAttr(String indirizzo, String attr,String data) {

			PreparedStatement ps;


			try {
				ps = link.prepareStatement(
						"UPDATE Email " 
								+ "SET "+attr+" = '"+data+"' "
								+ "WHERE indirizzo = '"+indirizzo+"'");

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
		 * @param data the data
		 */
		public void  upIndirizzo(String indirizzo, String data) {

			String attr="indirizzo";
			upAttr(indirizzo,attr,data);
		}

		/**
		 * Up utilizzo.
		 *
		 * @param indirizzo the indirizzo
		 * @param data the data
		 */
		public void  upUtilizzo(String indirizzo, String data) {

			String attr="utilizzo";
			upAttr(indirizzo,attr,data);
		}

	
		/**
		 * Sets the email.
		 *
		 * @param contID the cont ID
		 * @param indirizzo the indirizzo
		 * @param utilizzo the utilizzo
		 */
		public void setEmail(Integer contID, String indirizzo, String utilizzo) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"INSERT INTO Email " 
								+ "(cont_id, indirizzo, utilizzo) "
								+ "VALUES ('"+contID+"','"+indirizzo+"', '"+utilizzo+"' );");

				ps.executeUpdate();
				
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		/**
		 * Sets the email.
		 *
		 * @param indirizzo the indirizzo
		 * @param utilizzo the utilizzo
		 */
		public void setEmail(String indirizzo, String utilizzo) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"INSERT INTO Email " 
								+ "(indirizzo, utilizzo) "
								+ "VALUES ('"+indirizzo+"', '"+utilizzo+"' );");

				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		/**
		 * Sets the email.
		 *
		 * @param indirizzo the new email
		 */
		public void setEmail(String indirizzo) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"INSERT INTO Email " 
								+ "(indirizzo ) "
								+ "VALUES ('"+indirizzo+"');");

				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		/**
		 * Del email.
		 *
		 * @param indirizzo the indirizzo
		 */
		public void delEmail(String indirizzo) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"DELETE FROM Email "
								+ "WHERE indirizzo = '"+indirizzo+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		/**
		 * Del email.
		 *
		 * @param contID the cont ID
		 */
		public void delEmail(int contID) {

			PreparedStatement ps;

			try {
				ps = link.prepareStatement(
						"DELETE FROM Email "
								+ "WHERE Cont_ID = '"+contID+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void upEmail(String indirizzo, String oldIndirizzo, String uso) {
			PreparedStatement ps;


			try {
				ps = link.prepareStatement(
						"UPDATE Email " 
								+ "SET Indirizzo = '"+indirizzo+"', Utilizzo = '"+uso+"'"
								+ "WHERE Indirizzo = '"+oldIndirizzo+"'");

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}





	}


