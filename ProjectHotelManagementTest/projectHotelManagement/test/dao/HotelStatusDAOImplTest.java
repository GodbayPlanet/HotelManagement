package projectHotelManagement.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	
	@Mock
	private Connection connection;
	@Mock
	private HotelStatusDAOImpl hotelStatus;

	@Before
	public void setUp() {
		connection = DBConnection.getConnectionToDB();
		hotelStatus = new HotelStatusDAOImpl(connection);
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
		
		ResultSet rs = Mockito.mock(ResultSet.class);
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
	
	

	@After
	public void tearDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
