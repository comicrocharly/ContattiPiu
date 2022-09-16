import java.sql.SQLException;

import database.DatabaseConnect;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DatabaseConnect connect = null;
		try {
			connect = new DatabaseConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		System.out.println("Ciao " + connect.getUrl());
		
		
	}

}
