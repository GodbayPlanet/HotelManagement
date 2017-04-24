package projectHotelManagement.validations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projectHotelManagement.connection.DBConnection;

public class LoginValidation {

	public static Connection connection = DBConnection.getConnectionToDB();

	/**
	 * Validacija korisnickog imena i pasvorda
	 * 
	 * @author amer
	 * @param userName
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static boolean isValidUserLogin(String userName, String password) throws SQLException {
		
		//Case sensitive
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Users WHERE BINARY Username = '" + userName + "'" + "AND BINARY Password ='" + password + "'");

		ResultSet result = statement.executeQuery();
		if (!result.isBeforeFirst()) {
			return false;
		}
		return true;
	}

	/**
	 * Validacija admin imena i pasvorda
	 * 
	 * @author amer
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static boolean isValidAdminLogin(String name, String password) throws SQLException {
		//Case sensitive
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Admin WHERE BINARY Name = '" + name + "'" + "AND BINARY Password ='" + password + "'");

		ResultSet result = statement.executeQuery();
		if (!result.isBeforeFirst()) {
			return false;
		}
		return true;
	}

}
