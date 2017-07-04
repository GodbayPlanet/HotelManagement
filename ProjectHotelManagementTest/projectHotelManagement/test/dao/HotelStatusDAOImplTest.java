package projectHotelManagement.test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.HotelStatusDAOImpl;
import projectHotelManagement.data.AdditionalServices;
import projectHotelManagement.data.HotelStatus;
import projectHotelManagement.data.Room;
import projectHotelManagement.data.User;

public class HotelStatusDAOImplTest {

	private static final int SERVICE_ID = 0;
	private static final int ROOM_PRICE = 10;
	private static final int ROOM_ID = 1;
	private static final int IS_LOGGED = 1;
	private static final int USER_AGE = 52;
	private static final int USER_ID = 1;
	private static final double DELTA = 1e-15;

	@Mock
	private Connection connection;
	@Mock
	private HotelStatusDAOImpl hotelStatus;

	@Mock
	ResultSet rs;
	
	@Before
	public void setUp() {
		connection = DBConnection.getConnectionToDB();
		hotelStatus = new HotelStatusDAOImpl(connection);
		rs = Mockito.mock(ResultSet.class);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCustomerRoomRegistration() throws SQLException {

		User user = new User(USER_ID, "Stagod", "Pero", "Peric", "sss", "M", USER_AGE, IS_LOGGED);
		Room room = new Room(ROOM_ID, "Jednosobna", ROOM_PRICE);
		AdditionalServices additionalService = new AdditionalServices();
		additionalService.setServicesId(SERVICE_ID);

		HotelStatus hotel = new HotelStatus(user.getPersonalIdNumber(), additionalService.getServicesId(),
				room.getRoomNumber(), new Date());

		assertNotNull(user);
		assertNotNull(room);
		assertNotNull(additionalService);
		assertNotNull(hotel);
		assertEquals("Pero", user.getFirstName());
		assertEquals("Jednosobna", room.getRoomType());

		Mockito.when(rs.getInt(USER_ID)).thenReturn(hotel.getUserId());
		Mockito.when(rs.getInt(SERVICE_ID)).thenReturn(hotel.getServiceId());
		Mockito.when(rs.getInt(ROOM_ID)).thenReturn(hotel.getRoomId());
		Mockito.when(rs.getDate("checkIn")).thenReturn(new java.sql.Date(hotel.getCheckIn().getTime()));
	}

	@Test
	public void testBookAdditionalServices() throws SQLException {
		ArrayList<Integer> selectedServices = new ArrayList<>();
		selectedServices.add(SERVICE_ID);

		assertNotNull(selectedServices);
		assertEquals(1, selectedServices.size());

		hotelStatus.bookAdditionalServices(USER_ID, selectedServices);
		Mockito.verify(hotelStatus, Mockito.times(1)).bookAdditionalServices(USER_ID, selectedServices);
	}

	@Test
	public void testGetCurrentTotalForServices() {
		AdditionalServices additionalServiceGym = new AdditionalServices();
		additionalServiceGym.setServicesId(SERVICE_ID);
		int daysStayed = 2;
		int totalToPay = 0;

		totalToPay = daysStayed * additionalServiceGym.getGymPrice();
		assertNotNull(additionalServiceGym);
		assertEquals(SERVICE_ID, additionalServiceGym.getServicesId());
		assertEquals(20, totalToPay);
	}

	@Test
	public void testGetCurrentTotalForRoom() {
		Room apartment = new Room(ROOM_ID, "Apartment", 80);
		int daysStayed = 2;
		double totalForRoomServices = 0;

		totalForRoomServices = daysStayed * apartment.getPrice();
		assertEquals(160, totalForRoomServices, DELTA);
		assertNotNull(apartment);
		assertEquals(ROOM_ID, apartment.getRoomNumber());
	}
	
	@Test
	public void testGetLastRoomBooked() throws SQLException {
		Mockito.when(hotelStatus.getLastRoomBooked(rs)).thenReturn(ROOM_ID);
	}

	@After
	public void tearDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
