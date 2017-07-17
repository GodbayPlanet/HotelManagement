package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Set;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.data.User;

public class TestUserDAOImpl {

	private static final int OFFLINE = 0;
	private static final int ONLINE = 1;
	private static final int CUSTOMER_AGE = 55;
	private static final int CUSTOMER_DEFFTONES_ID = 13;
	private static final int CUSTOMER_JAMES_ID = 1;
	private static final int NUMER_OF_ROWS = 12;
	private static final String USER_TABLE_NAME = "users";
	static TestDao testDao;
	UserDAOImpl userDao;
	IDataSet loadedDataSet;

	@Before
	public void setUp() throws Exception {
		testDao = new TestDao("users.xml");
		userDao = new UserDAOImpl(DBConnection.getConnectionToDB());
		loadedDataSet = testDao.getDataSet();
	}
	
	@Test
	public void testAddCustomer() throws SQLException {
		userDao.addCustomer(
				new User(CUSTOMER_DEFFTONES_ID, "Defftones", "Defti", "Deftoo", "2364", "M", CUSTOMER_AGE, ONLINE));
		User user = userDao.getCustomerByID(13);
		assertNotNull(user);
		assertEquals("Defftones", user.getUserName());
	}
	
	@Test
	public void testGetCustomerByName() throws SQLException {
		User user = userDao.getCustomerByName("James");
		assertNotNull(user);
		assertEquals("James", user.getFirstName());
	}
	
	@Test
	public void testGetCustomerById() {
		User user = userDao.getCustomerByID(CUSTOMER_JAMES_ID);
		assertNotNull(user);
		assertEquals(CUSTOMER_JAMES_ID, user.getPersonalIdNumber());
	}
	
	@Test
	public void testGetCustomerByUserName() throws SQLException {
		User user = userDao.getCustomerByUserName("Metallica");
		assertNotNull(user);
		assertEquals("Metallica", user.getUserName());
	}
	
	@Test
	public void testGetAllCustomers() throws SQLException {
		Set<User> users = userDao.getAllCustomers();
		assertNotNull(users);
		assertEquals(NUMER_OF_ROWS, users.size());
	}
	
	@Test
	public void testLoadedData() throws DataSetException {
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(USER_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
	
	@Test
	public void testUpdateCustomer() throws SQLException {
		User user = userDao.getCustomerByID(CUSTOMER_JAMES_ID);
		user.setIsLogged(OFFLINE);
		userDao.updateCustomer(user);
		assertEquals(OFFLINE, user.getIsLogged());
	}
	
	@Test
	public void testDeleteCustomer() throws SQLException {
		userDao.deleteCustomer(CUSTOMER_JAMES_ID);
		assertEquals(null, userDao.getCustomerByID(CUSTOMER_JAMES_ID));
	}
	
	@Test
	public void testUpdateColumnIsLogged() {
		userDao.updateColumnIsLogged(CUSTOMER_JAMES_ID, OFFLINE);
		assertEquals(OFFLINE, userDao.getCustomerByID(CUSTOMER_JAMES_ID).getIsLogged());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		testDao.setUpDatabase();
	}
}
