package projectHotelManagement.dao;

import java.sql.SQLException;

import projectHotelManagement.data.Admin;

/**
 * 
 * @author Nemanja
 *
 */
public interface AdminDAO {

	/**
	 * Get admin by adminName.
	 * @param adminName
	 * @return
	 * @throws SQLException
	 */
	public Admin getAdmin(String adminName) throws SQLException;
}
