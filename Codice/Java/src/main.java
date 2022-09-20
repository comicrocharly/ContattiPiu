import java.sql.SQLException;

import database.DatabaseConnect;
import postgresDAO.*;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatabaseConnect connect = null;
		PostContattoDAO contatto = null;

		try {
			connect = new DatabaseConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contatto = new PostContattoDAO();


		System.out.println("Ciao " + connect.getUrl());

		System.out.println(contatto.getNome(2));
		System.out.println(contatto.getContID());

		System.out.println(contatto.getNome(39));
		contatto.upNome(40, "Lino");
		System.out.println(contatto.getNome(40));





	}

}
