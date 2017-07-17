package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.*;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAOImpl;

public class TestUserDAOImpl {

	private static final int NUMER_OF_ROWS = 12;
	private static final String USER_TABLE_NAME = "users";
	TestDao testDao;
	UserDAOImpl userDao;
	IDataSet loadedDataSet;

	@Before
	public void setUp() throws Exception {
		testDao = new TestDao("users.xml");
		userDao = new UserDAOImpl(DBConnection.getConnectionToDB());
		loadedDataSet = testDao.getDataSet();
	}
	
	
	
	@Test
	public void testLoadedData() throws DataSetException {
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(USER_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
}
