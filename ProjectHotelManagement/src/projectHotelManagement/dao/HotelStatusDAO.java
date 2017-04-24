package projectHotelManagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Redundant comment I know :P
 * 
 * @author Nemanja
 *
 */
public interface HotelStatusDAO {

	/**
	 * Booking room for custumer to hotel.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @param serviceId
	 * @param roomId
	 * @throws SQLException
	 */
	public void customerRoomRegistration(int userId, int roomId) throws SQLException;

	/**
	 * Booking additional services for customer.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @param selectedServices
	 * @throws SQLException
	 */
	public void customerAdditionalServicesRegistration(int userId, ArrayList<Integer> selectedServices)
			throws SQLException;

	/**
	 * Returns ResultSet of all services that customer uses.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @return
	 */
	public ResultSet getUserAdditionalServices(int userId);

	/**
	 * Returns ResutlSet witch contains room where the customer is.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @return
	 */
	public ResultSet getUserRoom(int userId);

	/**
	 * Returns total amount for additional services that customer use. This
	 * method is used when customer wants to se how much it owes to hotel for
	 * additional services.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @return
	 */
	public int getCurrentTotalForServices(int userId) throws SQLException;

	/**
	 * Returns total amount for room that customer use. This method is used when
	 * customer wants to se how much it owes to hotel for room service.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int getCurrentTotalForRoom(int userId) throws SQLException;

	/**
	 * Changing the room.
	 * 
	 * @author Nemanja
	 * @param userId
	 */
	public void changeTheRoom(int userId, int roomId) throws SQLException;

	/**
	 * Returns records from hotelstatus table for rooms.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @return
	 */
	public ResultSet getHotelStatusRecordsForRoom(int userId);

	/**
	 * Returns records from hotelstatus table for additional services.
	 * 
	 * @param userId
	 * @return
	 */
	public ResultSet getHotelStatusRecordsForServices(int userId);

	/**
	 * Updating room end date in database.
	 * 
	 * @param userId
	 * @param lastRoomBooked
	 */
	public void updateRoomEndDate(int userId, int lastRoomBooked);

	/**
	 * Updating service end date in database.
	 * 
	 * @param userId
	 */
	public void updateServiceEndDate(int userId) throws SQLException;

}