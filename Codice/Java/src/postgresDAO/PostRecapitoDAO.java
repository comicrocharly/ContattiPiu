package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import postgresDAO.PostTelefonoDAO;
import database.DatabaseConnect;
import java.util.ArrayList;

public class PostRecapitoDAO {
	
	private Connection link = null;

	public PostRecapitoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}


	public ArrayList<Recapito> getRecapiti(int contID) {
		
		ArrayList<Recapito> list = new  ArrayList<>();
		
		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"SELECT * "
							+ "FROM Recapito "
							+ "WHERE Cont_ID = '"+contID+"' ");
			ResultSet rs = ps.executeQuery();

			while(rs.next())
					list.add((Recapito) rs.getObject("Numero_In, Numero_Out, Rec_ID"));

			rs.close();
			
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean checkTelefono(String prefisso, String numero) {
		
		PreparedStatement ps;
		
		int tmp = 0;
		
		try {
			ps = link.prepareStatement(
					"SELECT COUNT(*)  AS C"
							+ "FROM Telefono "
							+ "WHERE numero = '"+numero+"' AND prefisso = '"+prefisso+"' ");
			
			ResultSet rs = ps.executeQuery();


			tmp = rs.getInt("C");
			
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
	


	//racchiudere in un try/catch per gestire l'eccezione generata dalla non esistenza del'oggetto telefono, magari con un propt di inserimento telefono
	public void setRecapito(int contID, String prefissoIn, String numeroIn, String prefissoOut, String numeroOut) {


		PreparedStatement ps;
		if(checkTelefono(prefissoIn, numeroIn) && checkTelefono(prefissoOut, numeroOut)) {
			try {
				ps = link.prepareStatement(
						"INSERT INTO Recapito " 
								+ "(prefisso_In, prefisso_out, numero_in, numero_out, Cont_ID) "
								+ "VALUES ('"+prefissoIn+"', '"+prefissoOut+"', '"+numeroIn+"', '"+numeroOut+"', '"+contID+"');");

				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}
	
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
}

	
		