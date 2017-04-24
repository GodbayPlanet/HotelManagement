package projectHotelManagement.dao;

import java.sql.SQLException;
import java.util.Set;

import projectHotelManagement.data.User;

/**
 * 
 * @author Nemanja
 *
 */
public interface UserDAO {
	
	/**
	 * Method for adding user in database.
	 * @param user
	 * @throws SQLException
	 */
	public void addCustomer(User customer) throws SQLException;

	/**
	 * Method that returns User from database.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User getCustomerByName(String firstName) throws SQLException;
	
	public User getCustomerByID(int id) throws SQLException;
	
	public User getCustomerByUserName(String userName) throws SQLException;

	/**
	 * Returns Set with all users.
	 * @return
	 * @throws SQLException
	 */
	public Set<User> getAllCustomers() throws SQLException;

	/**
	 * Update user in database.
	 * @param user
	 * @throws SQLException
	 */
	public void updateCustomer(User customer) throws SQLException;

	/**
	 * Delete user from database.
	 * @param id
	 * @throws SQLException
	 */
	public void deleteCustomer(int id) throws SQLException;
	
}
