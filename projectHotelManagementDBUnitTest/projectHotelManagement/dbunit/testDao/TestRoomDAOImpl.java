package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.RoomDAOImpl;
import projectHotelManagement.data.Room;

public class TestRoomDAOImpl {

	private static final int ROOM_RESERVED = 0;
	private static final int ROOM_ID = 1;
	private static final int NUMER_OF_ROWS = 15;
	private static final String ROOM_TABLE_NAME = "rooms";
	TestDao testDao;
	RoomDAOImpl roomDao;
	IDataSet loadedDataSet;
	
	@Before
	public void setUp() throws Exception {
		testDao = new TestDao("rooms.xml");
		roomDao = new RoomDAOImpl(DBConnection.getConnectionToDB());
		loadedDataSet = testDao.getDataSet();
	}
	
	@Test
	public void testUpdateIsFreeColumn() {
		roomDao.updateIsFreeColumn(ROOM_ID, ROOM_RESERVED);
		assertEquals(ROOM_RESERVED, roomDao.getRoomById(ROOM_ID).isReserved());
	}
	
	@Test
	public void testGetRoomById() {
		Room room = roomDao.getRoomById(ROOM_ID);
		assertNotNull(room);
		assertEquals(ROOM_ID, room.getRoomNumber());
		assertEquals("Single Room", room.getRoomType());
	}
	
	@Test
	public void testLoadedData() throws DataSetException {
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(ROOM_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
	
	@After
	public void tearDown() throws Exception {
		testDao.setUpDatabase();
	}
	
}
