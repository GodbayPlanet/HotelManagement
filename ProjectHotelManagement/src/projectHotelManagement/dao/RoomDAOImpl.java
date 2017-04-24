package projectHotelManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projectHotelManagement.data.Room;

/**
 * This class is implementation of RoomDAO interface. I know that maybe these comments are redundant,
 * but hey... maybe somebody doesn't know :P
 * @author Nemanja
 *
 */
public class RoomDAOImpl implements RoomDAO {

	Connection connection;
	PreparedStatement statement = null;
	ResultSet result = null;
	String query = "";
	
	public RoomDAOImpl(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Returns specific room from database selected by roomId and returns null if there is no room 
	 * with specified roodId..
	 * @author Nemanja
	 */
	@Override
	public Room getRoomById(int roomId) {
		try {
			query = "select * from Rooms where RoomNumber like '" + roomId + "'";
			statement = (PreparedStatement) connection.prepareStatement(query);
			result = statement.executeQuery();
			
			return getRoom(result);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns Room object from ResultSet, or null if ResultSet is empty.
	 * @author Nemanja
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public Room getRoom(ResultSet result) throws SQLException {
		if(result.next()) 
			return new Room(result.getInt("RoomNumber"), result.getString("RoomType"), 
					result.getInt("RoomPrice"), result.getInt("IsFree"));
		return null;
	}

	/**
	 * Method for updating column IsFree in table Rooms.
	 * @author Nemanja
	 */
	@Override
	public void updateIsFreeColumn(int roomId, int flag) {
		try {
			query = "update Rooms set IsFree = '" + flag + "' where RoomNumber like '" + roomId + "'"; 
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.executeUpdate(query);
			System.out.println("Column updated.");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
