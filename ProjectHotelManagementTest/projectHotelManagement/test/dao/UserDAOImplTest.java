package projectHotelManagement.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.data.User;

public class UserDAOImplTest {

	private static final int USER_ID = 1;
	private static final int USER_AGE = 23;
	private static final int IS_LOGGED = 1;

	@Mock
	private Connection mockConnection;
	@Mock
	private UserDAOImpl mockUserDAOImpl;
	@Mock
	ResultSet rs;

	@Before
	public void setUp() throws SQLException {
		mockConnection = DBConnection.getConnectionToDB();
		mockUserDAOImpl = new UserDAOImpl(mockConnection);
		rs = Mockito.mock(ResultSet.class);
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
		Mockito.when(mockUserDAOImpl.getCustomerByName("Pero")).thenReturn(createUser());
		mockUserDAOImpl.getCustomerByName("Pero");
		Mockito.verify(mockUserDAOImpl).getCustomerByName("Pero");
	}
	
	@Test
	public void testGetCustomerById() throws SQLException {
		Mockito.when(mockUserDAOImpl.getCustomerByID(USER_ID)).thenReturn(createUser());
		mockUserDAOImpl.getCustomerByID(USER_ID);
		Mockito.verify(mockUserDAOImpl).getCustomerByID(USER_ID);
	}
	
	@Test
	public void testGetCustomerByUserName() throws SQLException {
		Mockito.when(mockUserDAOImpl.getCustomerByUserName("Perica")).thenReturn(createUser());
		mockUserDAOImpl.getCustomerByUserName("Perica");
		Mockito.verify(mockUserDAOImpl).getCustomerByUserName("Perica");
	}
	
	@Test
	public void testGetCustomer() throws SQLException {
		Mockito.when(mockUserDAOImpl.getCustomer(rs)).thenReturn(createUser());
	}
	
	@Test
	public void testGetAllCustomers() throws SQLException {
		Set<User> customers = new LinkedHashSet<>();
		customers.add(createUser());
		Mockito.when(mockUserDAOImpl.getAllCustomers()).thenReturn(customers);
		assertNotNull(customers);
		assertEquals(1, customers.size());
	}
	
	@Test
	public void testGetSpecificRow() {
		Mockito.when(mockUserDAOImpl.getSpecificRow(USER_ID)).thenReturn(rs);
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
