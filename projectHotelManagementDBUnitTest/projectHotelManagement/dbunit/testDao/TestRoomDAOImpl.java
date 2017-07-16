package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.*;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.RoomDAOImpl;

public class TestRoomDAOImpl {

	private static final int NUMER_OF_ROWS = 15;
	private static final String ROOM_TABLE_NAME = "room";
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
	public void testLoadedData() throws DataSetException {
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(ROOM_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
	
}
