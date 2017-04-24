package projectHotelManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import projectHotelManagement.data.AdditionalServices;

/**
 * 
 * @author Nemanja
 *
 */
public class AdditionalServicesDAOImpl implements AdditionalServicesDAO {

	Connection connection;
	PreparedStatement statement = null;
	ResultSet result = null;
	String query = "";

	public AdditionalServicesDAOImpl(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Returns AdditionalServices object if ResultSet is not empty, and returns null
	 * if ther is no additional service in database.
	 * @author Nemanja
	 */
	@Override
	public AdditionalServices findById(int servicesId) {
		try {
			query = "SELECT * FROM Services WHERE ID LIKE '" + servicesId + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();
			
			if(result.next())
				return new AdditionalServices();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
