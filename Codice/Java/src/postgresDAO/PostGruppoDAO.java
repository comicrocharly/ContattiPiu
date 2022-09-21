package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnect;
import model.Gruppo;
import model.Recapito;

public class PostGruppoDAO {

	private Connection link = null;

	public PostGruppoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}

	//metodo di aggregazione
	public ArrayList<Gruppo> getGruppi(int contID){

		ArrayList<Gruppo> list = new  ArrayList<>();

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"SELECT * "
							+ "FROM Gruppo "
							+ "NATURAL JOIN Aggregazione "
							+ "WHERE Cont_ID = '"+contID+"'  ");

			ResultSet rs = ps.executeQuery();

			while(rs.next())
				list.add((Gruppo) rs.getObject("Nome_G, Descrizione, Group_ID"));

			rs.close();

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setGruppo(String nomeG, String descrizione) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"INSERT INTO Gruppo "
							+ "(nome_G, descrizione) "
							+ "VALUES ('"+nomeG+"', '"+descrizione+"' );");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delGruppo(String groupID) {

		PreparedStatement ps;

		try {
			ps = link.prepareStatement(
					"DELETE FROM Gruppo "
							+ "WHERE (Group_ID = '"+groupID+"' );");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
