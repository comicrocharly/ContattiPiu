package database;

import java.sql.*;

public class DatabaseConnect {
	
	private static DatabaseConnect instance;
	private Connection connection = null;
	private String nome = "postgres";
	private String password = "admin";
	private String url = "jdbc:postgresql://localhost:5432/ContattiPiu";
	private String driver = "org.postgresql.Driver";
	String failed = "DB Connection failed.";
	String success = "DB Connection succesful.";
	
	
	public DatabaseConnect() throws SQLException {
		try {
			Class.forName(getDriver());
			setConnection(DriverManager.getConnection(getUrl(), getNome(), password));

		} catch (ClassNotFoundException ex) {
			System.out.println("DB Connection failed: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
	public Connection getConnection() {
		return connection;
}

	public static DatabaseConnect getInstance() throws SQLException {
		if (instance == null) {
			instance = new DatabaseConnect();
		} else if (instance.getConnection().isClosed()) {
			instance = new DatabaseConnect();
		}
		return instance;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


}
