package projectHotelManagement.data;

/**
 * 
 * @author Nemanja
 *
 */
public class Room {
	
	private int roomNumber;
	private String roomType;
	private int price;
	private int isReserved;
	
	/**
	 * Construct Room object with specified roomNumber and roomType.
	 * @param roomNumber
	 * @param roomType
	 */
	public Room(int roomNumber, String roomType, int price, int isReserved) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
		this.isReserved = isReserved;
	}
	
	
	/**
	 * Construct Room object with specified roomNumber, roomType and price.
	 * @param roomNumber
	 * @param roomType
	 * @param price
	 */
	public Room(int roomNumber, String roomType, int price) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Room Number: " + roomNumber + "\n Room Type: " + roomType + "\n Is reserved: " + isReserved;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int isReserved() {
		return isReserved;
	}

	public void setReserved(int isReserved) {
		this.isReserved = isReserved;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
