package projectHotelManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import projectHotelManagement.data.User;

/**
 * 
 * @author Nemanja
 *
 */
public class UserDAOImpl implements UserDAO {

	Connection connection;
	PreparedStatement statement = null;
	ResultSet result = null;
	String query = "";
	final int OFFLINE = 0;
	final int ONLINE = 1;
	
	
	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Method adds Customer in database 
	 * @author Nemanja
	 */
	@Override
	public void addCustomer(User customer) throws SQLException {
		try {
			statement = (PreparedStatement) connection.prepareStatement("insert into Users"
					+ "(ID, UserName, FirstName, LastName, Password, Gender, Age, IsLogged)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setInt(1, customer.getPersonalIdNumber());
			statement.setString(2, customer.getUserName());
			statement.setString(3, customer.getFirstName());
			statement.setString(4, customer.getLastName());
			statement.setString(5, customer.getUserPassword());
			statement.setString(6, customer.getGender());
			statement.setInt(7, customer.getAge());
			statement.setInt(8, OFFLINE);

			statement.executeUpdate();
			System.out.println("Data inserted into database.");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			close(statement);
		}
	}

	/**
	 * Returns customer from database selected by First Name. 
	 * @author Nemanja
	 */
	@Override
	public User getCustomerByName(String firstName) throws SQLException {
		try {
			query = "select * from Users where FirstName like '" + firstName + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();

			return getCustomer(result);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Nemanja
	 */
	@Override
	public User getCustomerByID(int id) {
		try {
			query = "select * from Users where ID like '" + id + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();

			return getCustomer(result);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Find customer by userName and returns customer.
	 * @author Nemanja
	 */
	@Override
	public User getCustomerByUserName(String userName) throws SQLException {
		try {
			query = "select * from Users where UserName like '" + userName + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();

			return getCustomer(result);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Method returns Customer object from ResultSet, or returns null if ResultSet is empty. 
	 * @author Nemanja
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public User getCustomer(ResultSet result) throws SQLException {
		if(result.next())
			return new User(result.getInt("ID"), result.getString("UserName"), result.getString("FirstName"),
					result.getString("LastName"), result.getString("Password"), 
					result.getString("Gender"), result.getInt("Age"), result.getInt("IsLogged"));
		return null;
	}

	/**
	 * Returns all customers from database.
	 * @author Nemanja
	 */
	@Override
	public Set<User> getAllCustomers() throws SQLException {
		Set<User> customers = new LinkedHashSet<>();
		try {
			query = "select * from Users";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();

			while(result.next()) {
				customers.add(new User(result.getInt("ID"), result.getString("UserName"), result.getString("FirstName"),
						result.getString("LastName"), result.getString("Password"), 
						result.getString("Gender"), result.getInt("Age"), result.getInt("IsLogged")));
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return customers;
	}
	
	/**
	 * Returns specific rows from hotelstatus table. 
	 * @author Nemanja
	 * @param userId
	 * @return
	 */
	public ResultSet getSpecificRow(int userId) {
		try {
			query = "SELECT * FROM hotelstatus WHERE UserID LIKE '" + userId + "'  and RoomID is not null";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();
			
			return result;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Updating customer.
	 * @author Nemanja
	 */
	@Override
	public void updateCustomer(User customer) throws SQLException {
		try {
			query = "update Users set UserName = '" + customer.getUserName() + "',FirstName = '" + customer.getFirstName() +
					"',LastName = '" + customer.getLastName() + "', Password = '" + customer.getUserPassword() +
					"', Gender = '" + customer.getGender() + "', Age = '" + customer.getAge() +
					"', IsLogged = '" + customer.getIsLogged() + "' where ID = '" + customer.getPersonalIdNumber() + "'";

			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.executeUpdate(query);
			System.out.println("Customer updated.");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Deleting customer from database.
	 * @author Nemanja
	 */
	@Override
	public void deleteCustomer(int id) throws SQLException {
		try {
			query = "delete from Users where ID = '" + id + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.executeUpdate(query);
			System.out.println("Customer with id " + id + " deleted.");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method loogOff all users.
	 * @author Nemanja
	 * @throws SQLException
	 */
	public void loggedOffAllUsers() throws SQLException {
		Set<User> customers = getAllCustomers();
		customers.forEach(customer -> {
			if(customer.getIsLogged() == 1)
				updateColumnIsLogged(customer.getPersonalIdNumber(), OFFLINE);
		});
	}

	/**
	 * Set the column IsLogged to 0 witch means false, for specified customer.
	 * @author Nemanja
	 * @param personalIdNumber
	 */
	public void updateColumnIsLogged(int personalIdNumber, int flag) {
		try {
			query = "update Users set IsLogged = '" + flag + "' where ID like '" + personalIdNumber + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.executeUpdate(query);
			User customer = getCustomerByID(personalIdNumber);
			if(flag == OFFLINE)
				System.out.println("Customer " + customer.getUserName() + " is now offLine.");
			else
				System.out.println("Customer " + customer.getUserName() + " is now onLine.");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Close the Statement.
	 * @author Nemanja
	 * @param stmt
	 * @param result
	 * @throws SQLException
	 */
	private void close(Statement stmt) throws SQLException {
		stmt.close();
	}

	public String toString(int id){
		return getCustomerByID(id).toString();
	}

	public String toString(String userName) throws SQLException {
		return getCustomerByUserName(userName).toString();
	}
}
