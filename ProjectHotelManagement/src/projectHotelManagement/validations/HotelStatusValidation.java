package projectHotelManagement.validations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.AdditionalServicesDAOImpl;
import projectHotelManagement.dao.HotelStatusDAOImpl;
import projectHotelManagement.dao.RoomDAOImpl;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.data.AdditionalServices;
import projectHotelManagement.data.Room;
import projectHotelManagement.data.User;

public class HotelStatusValidation {

	static Connection connection = DBConnection.getConnectionToDB();
	static UserDAOImpl cust = new UserDAOImpl(connection);
	static RoomDAOImpl roomImpl = new RoomDAOImpl(connection);
	static HotelStatusDAOImpl hotelStatusImpl = new HotelStatusDAOImpl(connection);
	static AdditionalServicesDAOImpl serviceImpl = new AdditionalServicesDAOImpl(connection);

	/**
	 * Returns true if userId exist in database.
	 * @author Nemanja
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public static boolean isUserIdExists(int userId) {
		User customer = cust.getCustomerByID(userId);
		return customer != null ? true : false;
	}

	/**
	 * Returns true if userName exist in database.
	 * @author Nemanja
	 * @param userId
	 * @return
	 * @throws SQLException
	 */

	public static boolean isUserNameExists(String userName) throws SQLException {
		User customer = cust.getCustomerByUserName(userName);
		return customer != null ? true : false;
	}
	
	/**
	 * Returns true if userName exist in database.
	 * @author Nemanja
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public static boolean isUserFirstNameExists(String userName) throws SQLException {
		User customer = cust.getCustomerByName(userName);
		return customer != null ? true : false;
	}

	/**
	 * Returns true is roomId exist in database.
	 * @author Nemanja
	 * @param roomId
	 * @return
	 */
	public static boolean isRoomExists(int roomId) {
		Room room = roomImpl.getRoomById(roomId);
		return room != null ? true : false;
	}

	/**
	 * Returns true if room is free and false if room is already ocupied.
	 * 
	 * @author Nemanja
	 * @param roomId
	 * @return
	 */
	public static boolean isRoomFree(int roomId) {
		Room room = roomImpl.getRoomById(roomId);
		return (room != null && room.isReserved() == 1) ? true : false;
	}
	
	/**
	 * Return true if room is not booked in hotel status table.
	 * @param roomId
	 * @return
	 * @throws SQLException
	 */
	public static boolean isRoomFreeFromHotelStatus(int roomId) throws SQLException {
		ResultSet result = hotelStatusImpl.getAllRooms();
		while(result.next()) {
			if(result.getInt("RoomID") == roomId)
				return false;
		}
		return true;
	}
	
	/**
	 * Returns true if there is no duplicate rows in table hotelstatus.
	 * @author Nemanja
	 * @param userId
	 * @throws SQLException
	 */
	public static boolean isFreeToBook(int userId) throws SQLException {
		ResultSet result = cust.getSpecificRow(userId);
		LocalDate today = LocalDate.now();
		
		while(result.next()) {
			int id = result.getInt("UserID"); 
			if(id == userId  && result.getDate("StartDate").toLocalDate().equals(today))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if additional service exists and false if not.
	 * @author Nemanja
	 * @param serviceId
	 * @return
	 */
	public static boolean isAdditionalServiceExist(int serviceId) {
		AdditionalServices additionalService = serviceImpl.findById(serviceId);
		return (additionalService != null) ? true : false;
	}
}
