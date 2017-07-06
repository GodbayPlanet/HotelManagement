package projectHotelManagement.test.validations;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.validations.HotelStatusValidation;
import static org.junit.Assert.*;

public class HotelStatusValidationTest {
	
	private static final int USER_ID = 1;
	@Mock
	private Connection mockConnection;
	@Mock
	private UserDAOImpl mockCustomer;
	
	@Before
	public void setUp() {
		mockConnection = DBConnection.getConnectionToDB();
		mockCustomer = Mockito.mock(UserDAOImpl.class);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIsUserIdExists() {
		final HotelStatusValidation validation = Mockito.spy(new HotelStatusValidation());
		Mockito.verify(validation);
		HotelStatusValidation.isUserIdExists(USER_ID);
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
