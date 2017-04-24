package projectHotelManagement.data;

import java.util.Date;

public class HotelStatus {
	
	private int userId;
	private int serviceId;
	private int roomId;
	private Date checkIn;
	
	/**
	 * Construct the hotel status object with specified userId, roomId and checkIn to hotel.
	 * @param userId
	 * @param serviceId
	 * @param roomId
	 */
	public HotelStatus(int userId, int serviceId, int roomId, Date checkIn) {
		this.userId = userId;
		this.serviceId = serviceId;
		this.roomId = roomId;
		this.checkIn = checkIn;
	}
	
	/**
	 * Construct the hotel status object with specified userId, roomId and checkIn to hotel.
	 * @param userId
	 * @param serviceId
	 * @param roomId
	 */
	public HotelStatus(int userId, int roomId, Date checkIn) {
		this.userId = userId;
		this.roomId = roomId;
		this.checkIn = checkIn;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	
}