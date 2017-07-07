package projectHotelManagement.test.validations;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.data.Room;
import projectHotelManagement.data.User;
import projectHotelManagement.validations.HotelStatusValidation;

public class HotelStatusValidationTest {

	private static final int OFF_LINE = 0;
	private static final int USER_AGE = 26;
	private static final int ROOM_PRICE = 50;
	private static final int ROOM_ID = 1;
	private static final int USER_ID = 1;
	@Mock
	private Connection mockConnection;
	@Mock
	private UserDAOImpl mockCustomer;
	private User user = new User(USER_ID, "Perica", "Pero", "Peric", "pass", "M", USER_AGE, OFF_LINE);
	private Room room = new Room(ROOM_ID, "SingleRoom", ROOM_PRICE);

	@Before
	public void setUp() {
		mockConnection = DBConnection.getConnectionToDB();
		mockCustomer = Mockito.mock(UserDAOImpl.class);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIsUserIdExists() {
		assertTrue(HotelStatusValidation.isUserIdExists(USER_ID));
	}

	@Test
	public void testIsUserNameExists() throws SQLException {
		assertTrue(HotelStatusValidation.isUserNameExists(user.getUserName()));
	}
	
	@Test
	public void testIsUserFirstNameExists() throws SQLException {
		assertTrue(HotelStatusValidation.isUserFirstNameExists(user.getFirstName()));
	}
	
	@Test
	public void testIsRoomExists() {
		assertTrue(HotelStatusValidation.isRoomExists(room.getRoomNumber()));
	}
	
	@Test
	public void testIsAdditionalServiceExist() {
		
	}
	
	@After
	public void tearDown() {
		try {
			mockConnection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
