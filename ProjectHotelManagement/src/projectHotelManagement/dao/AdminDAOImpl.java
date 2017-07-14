package projectHotelManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projectHotelManagement.data.Admin;

/**
 * 
 * @author Nemanja
 *
 */
public class AdminDAOImpl implements AdminDAO {

	Connection connection;
	PreparedStatement statement = null;
	ResultSet result = null;
	String query = "";
	
	public AdminDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Returns administrator from database.
	 */
	@Override
	public Admin getAdmin(String adminName) throws SQLException {
		try {
			query = "select * from Admin where Name like '" + adminName + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();
			
			if(result.next()) {
				int id = result.getInt("ID");
				return new Admin(id, result.getString("Name"), result.getString("Password"));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.toString());
		}
		return null;
	}
	
}
