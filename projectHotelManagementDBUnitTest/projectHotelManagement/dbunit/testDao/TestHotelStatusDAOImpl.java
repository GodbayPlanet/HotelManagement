package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.HotelStatusDAOImpl;

public class TestHotelStatusDAOImpl {

	private static final int SECOND_ROOM_ID = 1;
	private static final int SECOND_CUSTOMER_ID = 1;
	private static final int EXPECTED_AMOUNT_FOR_ROOM = 100;
	private static final int EXPECTED_AMOUNT_FOR_SERVICES = 0;
	private static final int SERVICE_ID = 1;
	private static final int FIRST_CUSTOMER_ID = 3;
	private static final int FIRST_ROOM_ID = 2;
	private static final int NUMER_OF_ROWS = 5;
	private static final String HOTEL_STATUS_TABLE_NAME = "hotelstatus";
	static TestDao testDao;
	HotelStatusDAOImpl hotelStatusDao;
	IDataSet loadedDataSet;

	@Before
	public void setUp() throws Exception {
		testDao = new TestDao("hotelstatus.xml");
		hotelStatusDao = new HotelStatusDAOImpl(DBConnection.getConnectionToDB());
		loadedDataSet = testDao.getDataSet();
	}

	@Test
	public void testBookRoom() throws SQLException {
		hotelStatusDao.bookRoom(FIRST_CUSTOMER_ID, FIRST_ROOM_ID);
		ResultSet hotelStatusRecords = hotelStatusDao.getHotelStatusRecordsForRoom(FIRST_CUSTOMER_ID);
		assertNotNull(hotelStatusRecords);
		hotelStatusRecords.next();
		assertEquals(FIRST_CUSTOMER_ID, hotelStatusRecords.getInt("UserID"));
	}

	@Test
	public void testBookAdditionalServices() throws SQLException {
		bookService();
		ResultSet usersAdditionalServices = hotelStatusDao.getUserAdditionalServices(FIRST_CUSTOMER_ID);
		assertNotNull(usersAdditionalServices);
		usersAdditionalServices.next();
		assertEquals(FIRST_CUSTOMER_ID, usersAdditionalServices.getInt("UserID"));
		assertEquals(20, usersAdditionalServices.getInt("Price"));

	}
	
	private void bookService() throws SQLException {
		hotelStatusDao.bookAdditionalServices(FIRST_CUSTOMER_ID, new ArrayList<Integer>() {
			private static final long serialVersionUID = 1L;
			{
				add(SERVICE_ID);
			}
		});
	}
	
	@Test
	public void testGetUserRoom() throws Exception {
		ResultSet userRoom = hotelStatusDao.getHotelStatusRecordsForRoom(SECOND_CUSTOMER_ID);
		assertNotNull(userRoom);
		userRoom.next();
		assertEquals(SECOND_ROOM_ID, userRoom.getInt("RoomID"));
	}
	
	@Test
	public void testLoadedData() throws DataSetException {
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(HOTEL_STATUS_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
	
	@Test
	public void testGetCurrentTotalForServices() throws SQLException {
		int totalForServices = hotelStatusDao.getCurrentTotalForServices(SECOND_CUSTOMER_ID);
		assertEquals(EXPECTED_AMOUNT_FOR_SERVICES, totalForServices);
	}
	
	@Test
	public void getCurrentTotalForRoom() throws SQLException {
		int totalForRoom = hotelStatusDao.getCurrentTotalForRoom(SECOND_CUSTOMER_ID);
		assertEquals(EXPECTED_AMOUNT_FOR_ROOM, totalForRoom);
	}
	
	@Test
	public void testUpdateRoomEndDate() throws SQLException {
		hotelStatusDao.updateRoomEndDate(FIRST_CUSTOMER_ID, FIRST_ROOM_ID);
		ResultSet hotelStatusRecords = hotelStatusDao.getUserRoom(FIRST_CUSTOMER_ID);
		assertNotNull(hotelStatusRecords);
		LocalDate today = LocalDate.now();
		hotelStatusRecords.next();
		Timestamp endDate = hotelStatusRecords.getTimestamp("EndDate");
		assertEquals(today, endDate.toLocalDateTime().toLocalDate());
	}
	
	/**
	 * Skontaj ovaj test bolje.
	 * @throws SQLException
	 */
	@Test
	public void testUpdateServiceEndDate() throws SQLException {
		hotelStatusDao.updateServiceEndDate(FIRST_CUSTOMER_ID);
		ResultSet result = hotelStatusDao.getHotelStatusRecordsForServices(FIRST_CUSTOMER_ID);
		assertNotNull(result);
		LocalDate today = LocalDate.now();
		result.next();
		Timestamp endDate = result.getTimestamp("EndDate");
		assertEquals(today, endDate.toLocalDateTime().toLocalDate());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		testDao.setUpDatabase();
	}
}
