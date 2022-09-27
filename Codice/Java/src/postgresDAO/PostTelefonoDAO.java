package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Telefono;
import database.DatabaseConnect;

public class PostTelefonoDAO {
	private Connection link = null;

	public PostTelefonoDAO() {

		try {
			link = DatabaseConnect.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed.");
			e.printStackTrace();
		}

	}


	public String getTipo(String numero, String prefisso) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement(
					"SELECT tipo "
							+ "FROM Telefono "
							+ "WHERE numero = '"+numero+"' AND prefisso = '"+prefisso+"' ");
			ResultSet rs = ps.executeQuery();


			s = rs.getString("tipo");


			rs.close();

			return s;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public void setTelefono(Telefono telefono) {

		String prefisso = telefono.getPrefisso();
		String numero = telefono.getNumero();
		String tipo = telefono.getTipo();

		try {
			link.prepareStatement(
					"INSERT INTO TELEFONO "
							+ "(numero, prefisso, tipo) "
							+ "VALUES ('"+numero+"', '"+prefisso+"', '"+tipo+"' );");

		} catch (SQLException ignore) {

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


}
