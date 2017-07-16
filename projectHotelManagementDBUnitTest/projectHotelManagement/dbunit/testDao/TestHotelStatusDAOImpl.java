package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.HotelStatusDAOImpl;

public class TestHotelStatusDAOImpl {

	private static final int EXPECTED_AMOUNT_FOR_ROOM = 40;
	private static final int EXPECTED_AMOUNT_FOR_SERVICES = 20;
	private static final int SERVICE_ID = 1;
	private static final int CUSTOMER_ID = 3;
	private static final int ROOM_ID = 2;
	private static final int NUMER_OF_ROWS = 5;
	private static final String HOTEL_STATUS_TABLE_NAME = "hotelstatus";
	TestDao testDao;
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
		hotelStatusDao.bookRoom(CUSTOMER_ID, ROOM_ID);
		ResultSet hotelStatusRecords = hotelStatusDao.getHotelStatusRecordsForRoom(CUSTOMER_ID);
		assertNotNull(hotelStatusRecords);
		hotelStatusRecords.next();
		assertEquals(CUSTOMER_ID, hotelStatusRecords.getInt("UserID"));
	}

	@Test
	public void testBookAdditionalServices() throws SQLException {
		hotelStatusDao.bookAdditionalServices(CUSTOMER_ID, new ArrayList<Integer>() {
			private static final long serialVersionUID = 1L;
			{
				add(SERVICE_ID);
			}
		});
		
		ResultSet usersAdditionalServices = hotelStatusDao.getUserAdditionalServices(CUSTOMER_ID);
		assertNotNull(usersAdditionalServices);
		usersAdditionalServices.next();
		assertEquals(CUSTOMER_ID, usersAdditionalServices.getInt("UserID"));
		assertEquals(20, usersAdditionalServices.getInt("Price"));

	}
	
	@Test
	public void testGetUserRoom() throws SQLException {
		ResultSet userRoom = hotelStatusDao.getUserRoom(CUSTOMER_ID);
		assertNotNull(userRoom);
		userRoom.next();
		assertEquals(ROOM_ID, userRoom.getInt("RoomNumber"));
	}
	
	@Test
	public void testLoadedData() throws DataSetException {
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(HOTEL_STATUS_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
	
	@Test
	public void testGetCurrentTotalForServices() throws SQLException {
		int totalForServices = hotelStatusDao.getCurrentTotalForServices(CUSTOMER_ID);
		assertEquals(EXPECTED_AMOUNT_FOR_SERVICES, totalForServices);
	}
	
	@Test
	public void getCurrentTotalForRoom() throws SQLException {
		int totalForRoom = hotelStatusDao.getCurrentTotalForRoom(CUSTOMER_ID);
		assertEquals(EXPECTED_AMOUNT_FOR_ROOM, totalForRoom);
	}
	
	
//	@After
//	public void tearDown() throws Exception {
//		testDao.setUpDatabase();
//	}
}
