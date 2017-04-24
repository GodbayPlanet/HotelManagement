package projectHotelManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import projectHotelManagement.data.User;
import projectHotelManagement.validations.HotelStatusValidation;

public class HotelStatusDAOImpl implements HotelStatusDAO {

	Connection connection;
	PreparedStatement statement = null;
	RoomDAOImpl room;
	ResultSet result = null;
	String query = "";
	final int ROOM_RESERVED = 0;
	final int ROOM_RELEASED = 1;
	LocalDate today = LocalDate.now();
	java.sql.Date date = java.sql.Date.valueOf(today);

	public HotelStatusDAOImpl(Connection connection) {
		this.connection = connection;
		this.room = new RoomDAOImpl(connection);
	}

	/**
	 * Adding customer into database table hotelstatus.
	 * 
	 * @author Nemanja
	 */
	@Override
	public void customerRoomRegistration(int userId, int roomId) throws SQLException {
		if (!HotelStatusValidation.isUserIdExists(userId))
			System.out.println("Customer with id " + userId + " does not exists.");
		else if (!HotelStatusValidation.isRoomFree(roomId))
			System.out.println("Room with id " + roomId + " is already occupied.");
		else if (!HotelStatusValidation.isRoomExists(roomId))
			System.out.println("Room with id " + roomId + " does not exists.");
		else
			bookRoom(userId, roomId);
	}

	/**
	 * @author Nemanja
	 */
	@Override
	public void customerAdditionalServicesRegistration(int userId, ArrayList<Integer> selectedServices)
			throws SQLException {
		if (!HotelStatusValidation.isUserIdExists(userId))
			System.out.println("Customer with id " + userId + " does not exists.");
		else
			bookAdditionalServices(userId, selectedServices);
	}

	/**
	 * This method is used when customer wants to book room.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @param roomId
	 * @throws SQLException
	 */
	public void bookRoom(int userId, int roomId) throws SQLException {
		try {
			query = "insert into hotelstatus (UserID, ServiceID, StartDate, RoomID)" + " values ( ?, ?, ?, ? )";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setInt(1, userId);
			statement.setInt(2, 0);
			statement.setDate(3, date);
			statement.setInt(4, roomId);

			try {
				statement.executeUpdate();
				System.out.println("Data inserted into database.");
			}
			catch(MySQLIntegrityConstraintViolationException ex) {
				System.out.println("Ne mozete mijenjati vise soba u jednom danu. Bookiracemo vas sutra.");
			}

			room.updateIsFreeColumn(roomId, ROOM_RESERVED);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally {
			statement.close();
		}
	}

	/**
	 * When customer wants to book additional services he can chek more that one
	 * additional service, this method calls another helper method that insert
	 * data to database.
	 * @author Nemanja
	 * @param userId
	 * @param selectedServices
	 * @throws SQLException
	 */
	public void bookAdditionalServices(int userId, ArrayList<Integer> selectedServices) throws SQLException {
		for (int i = 0; i < selectedServices.size(); i++) {
			bookAdditionalServices(userId, selectedServices.get(i));
		}
	}

	/**
	 * Method for inserting data to hotelstatus table in database.
	 * @author Nemanja
	 * @param userId
	 * @param serviceId
	 * @throws SQLException
	 */
	private void bookAdditionalServices(int userId, int serviceId) throws SQLException {
		try {
			query = "insert into hotelstatus (UserID, ServiceID, StartDate, RoomID)" + " values ( ?, ?, ?, ? )";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setInt(1, userId);
			statement.setInt(2, serviceId);
			statement.setDate(3, date);
			statement.setNull(4, Types.INTEGER);

			statement.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			statement.close();
		}
	}

	/**
	 * Returns ResultSet of all additional services that customer uses.
	 * @author Nemanja
	 */
	@Override
	public ResultSet getUserAdditionalServices(int userId) {
		try {
			query = "SELECT hotelstatus.UserID, hotelstatus.StartDate, hotelstatus.EndDate, services.Name, "
					+ " services.Price FROM services " + " JOIN hotelstatus ON services.ID = hotelstatus.ServiceID "
					+ " WHERE hotelstatus.UserID LIKE '" + userId + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns ResultSet with room that custumer is currently in, and with other
	 * rooms that custuser was in.
	 * 
	 * @author Nemanja
	 */
	@Override
	public ResultSet getUserRoom(int userId) {
		try {
			query = "SELECT rooms.RoomNumber, rooms.RoomType, rooms.RoomPrice, hotelstatus.StartDate, "
					+ "hotelstatus.EndDate, hotelstatus.RoomID " + " FROM rooms"
					+ " JOIN hotelstatus ON rooms.RoomNumber = hotelstatus.RoomID " + " WHERE hotelstatus.UserID LIKE '"
					+ userId + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Overriden method that returns total amount for additional services that
	 * customer use.
	 */
	@Override
	public int getCurrentTotalForServices(int userId) throws SQLException {
		int price = 0;
		int daysStayed = 0;
		java.sql.Date dateCheckedIn = null;
		java.sql.Date dateCheckedOut = null;
		LocalDate today = LocalDate.now();
		int total = 0;
		result = getUserAdditionalServices(userId);

		while (result.next()) {
			price = result.getInt("Price");
			dateCheckedIn = result.getDate("StartDate");
			dateCheckedOut = result.getDate("EndDate");

			if (dateCheckedOut == null) {
				// If customer has used additional service form only one day.
				if (today.equals(dateCheckedIn.toLocalDate())) {
					total += price;
				} else { // If customer used additional services for more than
					// one day.
					daysStayed = today.compareTo(dateCheckedIn.toLocalDate());
					total += daysStayed * price;
				}
			} else {
				if (today.equals(dateCheckedIn.toLocalDate())) {
					total += price;
				}
				else {
					daysStayed = dateCheckedOut.toLocalDate().compareTo(dateCheckedIn.toLocalDate());
					total += daysStayed * price;
				}
			}
		}
		return total;
	}

	/**
	 * Returns total amount for room services.
	 * 
	 * @author Nemanja
	 */
	@Override
	public int getCurrentTotalForRoom(int userId) throws SQLException {
		int price = 0;
		int daysStayed = 0;
		java.sql.Date dateCheckedIn = null;
		java.sql.Date dateCheckedOut = null;
		result = getUserRoom(userId);
		LocalDate today = LocalDate.now();
		int total = 0;

		while (result.next()) {

			price = result.getInt("RoomPrice");
			dateCheckedIn = result.getDate("StartDate");
			dateCheckedOut = result.getDate("EndDate");

			if (dateCheckedOut == null) {
				// If customer has stayed only one day in particular room.
				if (today.equals(dateCheckedIn.toLocalDate())) {
					total += price;
				} else { // If customer has stayed more than one day in
					// particular room.
					daysStayed = today.compareTo(dateCheckedIn.toLocalDate());
					total += daysStayed * price;
				}
			} else {
				if (today.equals(dateCheckedIn.toLocalDate())) {
					total += price;
				}
				else {
					daysStayed = dateCheckedOut.toLocalDate().compareTo(dateCheckedIn.toLocalDate());
					total += daysStayed * price;
				}
			}
		}
		return total;
	}

	/**
	 * @author Nemanja
	 * @throws SQLException
	 */
	@Override
	public void changeTheRoom(int userId, int roomId) throws SQLException {
		ResultSet hotelStatusRecords = getHotelStatusRecordsForRoom(userId);
		int lastRoomBooked = getLastRoomBooked(hotelStatusRecords);

		bookRoom(userId, roomId);
		updateRoomEndDate(userId, lastRoomBooked);
	}

	/**
	 * Updating EndDate column in database for room.
	 * 
	 * @author Nemanja
	 * @param userId
	 * @param lastRoomBooked
	 */
	@Override
	public void updateRoomEndDate(int userId, int lastRoomBooked) {
		try {
			query = "UPDATE hotelstatus SET EndDate = '" + today + "' WHERE UserID LIKE '" + userId
					+ "' AND RoomID LIKE '" + lastRoomBooked + "' AND EndDate IS null";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.executeUpdate(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @author Nemanja
	 * @throws SQLException
	 */
	@Override
	public void updateServiceEndDate(int userId) throws SQLException {
		result = getHotelStatusRecordsForServices(userId);

		while (result.next()) {
			query = "UPDATE hotelstatus SET EndDate = '" + today + "' WHERE EndDate IS null AND " + " UserID LIKE '"
					+ userId + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.executeUpdate(query);
		}
	}

	/**
	 * Returns RoomID of last room that specific user is booked.
	 * @author Nemanja
	 * @param results
	 * @return
	 * @throws SQLException
	 */
	public int getLastRoomBooked(ResultSet results) throws SQLException {
		int lastRoomId = 0;

		boolean available = false;
		available = results.next();
		lastRoomId = result.getInt("RoomId");

		while (available) {

			if (result.next())
				lastRoomId = result.getInt("RoomId");
			else
				available = false;
		}
		return lastRoomId;
	}

	/**
	 * @author Nemanja
	 */
	@Override
	public ResultSet getHotelStatusRecordsForRoom(int userId) {
		try {
			query = "SELECT UserID, StartDate, RoomID, EndDate FROM hotelstatus " + "WHERE ServiceId = '" + 0
					+ "' AND UserId LIKE '" + userId + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Nemanja
	 */
	@Override
	public ResultSet getHotelStatusRecordsForServices(int userId) {
		try {
			query = "SELECT UserID, StartDate, ServiceID, EndDate FROM hotelstatus " + "WHERE ServiceId != '" + 0
					+ "' AND UserId LIKE '" + userId + "'";
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
	 * Returns all room from table hotelstatus.
	 * @author Nemanja
	 * @return
	 */
	public ResultSet getAllRooms() {
		try {
			query = "SELECT RoomID FROM hotelstatus WHERE RoomID IS NOT NULL";
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
	 * Prints all services and amounts that customer use and total amount to
	 * pay.
	 * @author Nemanja
	 * @param userId
	 * @throws SQLException
	 */
	public void printCurrentAmountToPay(int userId) throws SQLException {
		UserDAOImpl userDAOImpl = new UserDAOImpl(connection);
		User customer = userDAOImpl.getCustomerByID(userId);
		ResultSet services = getUserAdditionalServices(userId);

		printCurrentAmountToPay(customer, services);
		System.out.println("\nTotal amount to pay is: "
				+ (getCurrentTotalForRoom(userId) + getCurrentTotalForServices(userId)) + " KM");
	}

	/**
	 * This method is used when user ask for bill. Method prints the bill for
	 * the hotel services. Before printing the bill, it updates columns EndDate
	 * in table hotelstatus so we can know how many days customer is stayed in
	 * room or how many days is used additional services. Also updates the
	 * column isFree in table rooms to set the room free.
	 * @author Nemanja
	 * @param userId
	 * @throws SQLException
	 */
	public void printBill(int userId) throws SQLException {
		ResultSet hotelStatusRecords = getHotelStatusRecordsForRoom(userId);
		result = getUserRoom(userId);

		int roomId = 0;

		if (result.next()) {
			roomId = result.getInt("RoomNumber");
			int lastRoomBooked = getLastRoomBooked(hotelStatusRecords);
			updateRoomEndDate(userId, lastRoomBooked);
		}
		room.updateIsFreeColumn(roomId, ROOM_RELEASED);
		updateServiceEndDate(userId);

		printCurrentAmountToPay(userId);
		LocalTime timePayent = LocalTime.now();
		System.out.println("\nPayment Date and time: " + today + " -- " + timePayent);
	}

	/**
	 * Helper method for printCurrentAmountToPay(int userId).
	 * @author Nemanja
	 * @param customer
	 * @param services
	 * @throws SQLException
	 */
	public void printCurrentAmountToPay(User customer, ResultSet services) throws SQLException {
		System.out.println("Bill for customer " + customer.getUserName());
		String endDate = null;

		while (services.next()) {
			endDate = getEndDateToPrint(services);

			System.out.printf("%-18s%10s%15s%18s%22s%18d KM Per day\n", services.getString("Name"), "Reservation Date",
					services.getDate("StartDate"), " --  ", endDate, services.getInt("Price"));
		}

		System.out.println();
		services = getUserRoom(customer.getPersonalIdNumber());
		System.out.println("You room/s cost is: ");

		while (services.next()) {
			endDate = getEndDateToPrint(services);

			System.out.printf("Room Number %-10d%-15s%10s%10s%10s%22s%8d KM Per day\n", services.getInt("RoomNumber"),
					services.getString("RoomType"), "Reservation Date    ", services.getDate("StartDate"), " --  ",
					endDate, services.getInt("RoomPrice"));
		}
	}

	/**
	 * Returns String that represent current information on endDate.
	 * @author Nemanja
	 * @param services
	 * @return
	 * @throws SQLException
	 */
	private String getEndDateToPrint(ResultSet services) throws SQLException {
		if (services.getDate("EndDate") == null)
			return "Still use the service";
		else
			return services.getDate("EndDate").toString();
	}

}