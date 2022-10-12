package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import postgresDAO.PostTelefonoDAO;
import database.DatabaseConnect;
import java.util.ArrayList;

import dao.RecapitoDAO;

public class PostRecapitoDAO implements RecapitoDAO {
	
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
	


	
	public void setRecapito(int contID, String prefissoIn, String numeroIn, String prefissoOut, String numeroOut) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO Recapito (prefisso, prefisso_out, numero, numero_out, Cont_ID) "
							+ "VALUES ('"+prefissoIn+"', '"+prefissoOut+"', '"+numeroIn+"', '"+numeroOut+"', '"+contID+"');");

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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



	
		