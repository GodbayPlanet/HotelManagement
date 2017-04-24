package projectHotelManagement.dao;

import projectHotelManagement.data.Room;

/**
 * 
 * @author Nemanja
 *
 */
public interface RoomDAO {
	
	/**
	 * Returns Room object selected by roomId.
	 * @param roomId
	 * @return
	 */
	public Room getRoomById(int roomId);
	
	/**
	 * When new customer is booked in hotel room field ifFree needs to be change.
	 */
	public void updateIsFreeColumn(int roomId, int flag);
	
}