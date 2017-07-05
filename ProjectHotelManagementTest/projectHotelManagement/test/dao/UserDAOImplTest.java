package projectHotelManagement.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAO;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.data.User;

public class UserDAOImplTest {

	private static final int USER_ID = 1;
	private static final int USER_AGE = 23;
	private static final int IS_LOGGED = 1;

	@Mock
	private Connection mockConnection;
	@Mock
	private UserDAO mockUserDAO;

	@Before
	public void setUp() throws SQLException {
		mockConnection = DBConnection.getConnectionToDB();
		mockUserDAO = new UserDAOImpl(mockConnection);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddCustomer() throws SQLException {
		User user = createUser();
		assertNotNull(user);
		assertEquals(USER_ID, user.getPersonalIdNumber());
		assertEquals("Perica", user.getUserName());
		assertEquals(IS_LOGGED, user.getIsLogged());
	}

	@Test
	public void testGetCustomerByName() throws SQLException {
		Mockito.when(mockUserDAO.getCustomerByName("Pero")).thenReturn(createUser());
		mockUserDAO.getCustomerByName("Pero");
		Mockito.verify(mockUserDAO).getCustomerByName("Pero");
	}
	
	@Test
	public void testGetCustomerById() throws SQLException {
		Mockito.when(mockUserDAO.getCustomerByID(USER_ID)).thenReturn(createUser());
		mockUserDAO.getCustomerByID(USER_ID);
		Mockito.verify(mockUserDAO).getCustomerByID(USER_ID);
	}
	
	private User createUser() {
		return new User(USER_ID, "Perica", "Pero", "Peric", "pass", "M", USER_AGE, IS_LOGGED);
	}
	
	@After
	public void tearDown() {
		try {
			mockConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
