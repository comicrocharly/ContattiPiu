package postgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import dao.TelefonoDAO;
import model.Telefono;
import database.DatabaseConnect;

public class PostTelefonoDAO implements TelefonoDAO{
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

	public ArrayList<Telefono> getListaNumeri() {

		ArrayList<Telefono> listaNumeri = new ArrayList<Telefono>();
		
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps= link.prepareStatement("SELECT * FROM TELEFONO");
			rs = ps.executeQuery();
			while(rs.next()) {
				listaNumeri.add(new Telefono(rs.getString(1),rs.getString(2),rs.getString(3)));
				
			}
			
			rs.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return listaNumeri;
	}

	public String getTipo(String numero, String prefisso) {

		PreparedStatement ps;
		String s = null;

		try {
			ps = link.prepareStatement("SELECT tipo " + "FROM Telefono " + "WHERE numero = '" + numero
					+ "' AND prefisso = '" + prefisso + "' ");
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
		PreparedStatement ps;
		
		String prefisso = telefono.getPrefisso();
		String numero = telefono.getNumero();
		String tipo = telefono.getTipo();

		try {
			ps = link.prepareStatement("INSERT INTO Telefono ( numero, prefisso, tipo) "
					+ "VALUES ('"+numero+"', '"+prefisso+"', '"+tipo+"' ); ");
			
			try {
				ps.executeUpdate();
			} catch (PSQLException pe) {
				// TODO Auto-generated catch block
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkTelefono(String prefisso, String numero) {

		PreparedStatement ps;

		int tmp = 0;

		try {
			ps = link.prepareStatement("SELECT COUNT(*)  AS C " 
					+ "FROM Telefono " 
					+ "WHERE numero = '" + numero+ "' AND prefisso = '" + prefisso + "' ");

			ResultSet rs = ps.executeQuery();

			tmp = rs.getInt("C");

			rs.close();

			if (tmp > 1)
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
