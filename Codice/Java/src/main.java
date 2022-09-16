import java.sql.SQLException;

import database.DatabaseConnect;
import postgresDAO.*;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DatabaseConnect connect = null;
		ContattoDAO contatto = null;
		
		try {
			connect = new DatabaseConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contatto = new ContattoDAO();
		
		
		System.out.println("Ciao " + connect.getUrl());
		
			System.out.println(contatto.getNome());
		
		
		
		
	}

}
