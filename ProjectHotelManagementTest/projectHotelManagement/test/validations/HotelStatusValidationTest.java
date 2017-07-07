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
import projectHotelManagement.data.User;
import projectHotelManagement.validations.HotelStatusValidation;

public class HotelStatusValidationTest {

	private static final int USER_ID = 1;
	@Mock
	private Connection mockConnection;
	@Mock
	private UserDAOImpl mockCustomer;
	private HotelStatusValidation validation;
	private User user = new User(USER_ID, "Perica", "Pero", "Peric", "pass", "M", 26, 0);

	@Before
	public void setUp() {
		mockConnection = DBConnection.getConnectionToDB();
		mockCustomer = Mockito.mock(UserDAOImpl.class);
		validation = Mockito.spy(new HotelStatusValidation());
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIsUserIdExists() {
		assertTrue(HotelStatusValidation.isUserIdExists(USER_ID));
	}

	@Test
	public void testIsUserNameExists() throws SQLException {
		assertTrue(HotelStatusValidation.isUserNameExists("Perica"));
	}
	
	@Test
	public void testIsUserFirstNameExists() throws SQLException {
		assertTrue(HotelStatusValidation.isUserFirstNameExists("Pero"));
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
