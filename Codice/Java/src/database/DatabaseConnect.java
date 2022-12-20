package database;

import java.sql.*;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseConnect.
 */
public class DatabaseConnect {
	
	/** The instance. */
	private static DatabaseConnect instance;
	
	/** The connection. */
	private Connection connection = null;
	
	/** The nome. */
	private String nome = "postgres";
	
	/** The password. */
	private String password = "admin";
	
	/** The url. */
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	
	/** The driver. */
	private String driver = "org.postgresql.Driver";
	
	/** The failed. */
	String failed = "DB Connection failed";
	
	/** The success. */
	String success = "DB Connection succesful";
	
	
	/**
	 * Instantiates a new database connect.
	 *
	 * @throws SQLException the SQL exception
	 */
	public DatabaseConnect() throws SQLException {
		try {
			Class.forName(getDriver());
			setConnection(DriverManager.getConnection(getUrl(), getNome(), password));
			
			if(connection!=null)
				System.out.println(success);

		} catch (ClassNotFoundException ex) {
			System.out.println("DB Connection failed: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
}

	/**
	 * Gets the single instance of DatabaseConnect.
	 *
	 * @return single instance of DatabaseConnect
	 * @throws SQLException the SQL exception
	 */
	public static DatabaseConnect getInstance() throws SQLException {
		if (instance == null) {
			instance = new DatabaseConnect();
		} else if (instance.getConnection().isClosed()) {
			instance = new DatabaseConnect();
		}
		return instance;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver the new driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Sets the connection.
	 *
	 * @param connection the new connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


}
