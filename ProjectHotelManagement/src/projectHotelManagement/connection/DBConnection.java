package projectHotelManagement.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

	/**
	 * Returns connection to data base.
	 * @return
	 */
	public static Connection getConnectionToDB() {
		Properties properties = getProperties();

		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagamenttest",
					//Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagamentTest",
					properties);
			return myConnection;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns properties defined for data base.
	 * @return
	 */
	public static Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "password1");
		//properties.setProperty("password", "ameramer");
		properties.setProperty("useSSL", "false");

		return properties;
	}
}
