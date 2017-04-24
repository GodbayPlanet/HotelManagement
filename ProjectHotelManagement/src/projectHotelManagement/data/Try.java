package projectHotelManagement.data;

import java.sql.SQLException;

public class Try {

	public static void main(String[] args) throws SQLException {

		//		SingleBedRoom singleRoom = new SingleBedRoom(0, true);
		//		DoubleBedRoom doubleRoom = new DoubleBedRoom(1, true);
		//		
		//		System.out.println(singleRoom.getRoomPrice());
		//		System.out.println(doubleRoom.getRoomPrice());
		//		System.out.println(singleRoom.getRoomType());
		//		
		//		Customer user = new Customer("Nemanja", "Vasic", "male", 123, 24, "neco", "111");
		//		Booking booking = new Booking(user, singleRoom, new Date(), 000, 3);
		//		
		//		UserAccount userAccount = new UserAccount(user, booking, 1);
		//		
		//		System.out.println(userAccount.calculateRoomPayment());
		//		
		//		int[] services = {1, 3, 4};
		//		
		//		System.out.println(userAccount.calculateTotalPayment(services));
		//		
		//		System.out.println(booking.toString());

		//Connection connection = DBConnection.getConnectionToDB();

		//Customer customer = new Customer(9, "Perica", "444", "Pero", "Peric", "M", 56, 1);
		//CustomerDAOImpl user = new CustomerDAOImpl(connection);

		//		Customer c = null;
		//		try {
		//			c = user.getCustomerByUserName("Mentalica");
		//		} 
		//		catch(SQLException e) {
		//			e.printStackTrace();
		//		}
		//		System.out.println(c.toString());

		//		Set<Customer> customers = user.getAllCustomers();
		//		
		//		for(Customer c : customers) {
		//			System.out.println(c.toString());
		//		}

		//		Customer customer = new Customer(9, "Perica", "Pero", "Peric", "Peric", "M", 56, 1);
		//		try {
		//			user.updateCustomer(customer);
		//		}
		//		catch(SQLException e) {
		//			e.printStackTrace();
		//		}

		//		user.deleteCustomer(9);

		//		HotelStatusDAOImpl hotel = new HotelStatusDAOImpl(connection);
		//
		//		HotelStatus hs = new HotelStatus(9, -1, 6, new Date());
		//
		//		hotel.userRegistration(hs.getUserId(), hs.getServiceId(), hs.getRoomId());

		//CustomerDAOImpl user = new CustomerDAOImpl(connection);
		//		user.loggedOffAllUsers();

		//		HotelStatusDAOImpl hotel = new HotelStatusDAOImpl(connection);

		//		ResultSet userServices = hotel.getUserAdditionalServices(7);
		//		Customer customer = user.getCustomerByID(7);
		//
		//		System.out.println("Customer " + customer.getUserName() + " currently use: ");
		//		while(userServices.next()) {
		//			System.out.println(userServices.getString("Name") + " Price: " + userServices.getInt("Price"));
		//		}

		//		ResultSet userServices = hotel.getUserRoom(1);
		//		Customer customer = user.getCustomerByID(1);
		//
		//		System.out.println("Customer " + customer.getUserName() + " is currently in: ");
		//		while(userServices.next()) {
		//			System.out.println(userServices.getInt("room_number") + " " + userServices.getString("room_type") + 
		//			ResultSet r = hotel.getHotelStatusRecords(2);
//		System.out.println(hotel.getLastRoomBooked(r));
//		System.out.println();
//		
//		hotel.changeTheRoom(2, 14);		" Price: " + userServices.getInt("room_price"));
		//		}
		//
		//		System.out.println();
		//		System.out.println("Total of additional services for customer " + customer.getUserName() + " is: "
		//				+ hotel.calculateAdditionalServices(7));
		//
		//		System.out.println();

		//		LocalDate today = LocalDate.now().plusDays(1);
		//		
		//		java.sql.Date dateCheckedIn = hotel.getDateCheckedIn(1);
		//		
		//		System.out.println(today + "   " + dateCheckedIn);
		//		
		//		
		//		int daysStayed = today.compareTo(dateCheckedIn.toLocalDate());
		//		System.out.println(daysStayed);

		//		System.out.println("Total form customer " + customer.getUserName() + " on room service is " + 
		//				hotel.calculateAmountForRoom(1));

		//		Customer c = user.getCustomerByUserName("Dejo");
		//		System.out.println(c.toString());
		//		
		//		HotelStatusDAOImpl hotel = new HotelStatusDAOImpl(connection);
		//		
		//		hotel.userRegistration(2, 6);

		//		LocalDate l = LocalDate.parse("2017-04-22");
		//		System.out.println(l);

		//		HotelStatusDAOImpl hotel = new HotelStatusDAOImpl(connection);
		//		Customer c = user.getCustomerByID(2);
		//
		//		int[] addSer = {1, 2, 3};
		//		
		//		hotel.bookAdditionalServices(c.getPersonalIdNumber(), addSer);
		
		//HotelStatusDAOImpl hotel = new HotelStatusDAOImpl(connection);
//		Customer c = user.getCustomerByID(2);
//		ResultSet services = hotel.getUserAdditionalServices(2);
//		
//		System.out.println("Customer " + c.getUserName() + " currently use: ");
//		while(services.next()) {
//			System.out.printf("%-15s%20d\n", services.getString("Name"), services.getInt("Price"));
//		}
//		System.out.println();
//		services = hotel.getUserRoom(2);
//		System.out.println("You room/s cost is: ");
//		while(services.next()) {
//			System.out.printf("Room Number %-15d%10s%10d\n", services.getInt("RoomNumber"), services.getString("RoomType"),
//					services.getInt("RoomPrice"));
//		}
//		
//		System.out.println(hotel.getCurrentTotalForRoom(2));
		
		//hotel.printCurrentAmountToPay(2);
		
//		ResultSet r = hotel.getHotelStatusRecords(2);
//		System.out.println(hotel.getLastRoomBooked(r));
//		System.out.println();
//		
//		hotel.changeTheRoom(2, 14);
	}
}
