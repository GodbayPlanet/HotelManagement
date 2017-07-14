package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.AdminDAOImpl;
import projectHotelManagement.data.Admin;

public class TestAdminDaoImpl {
	
	private static final int NUMER_OF_ROWS = 1;
	private static final String ADMIN_TABLE_NAME = "admin";
	TestDao testDao;
	AdminDAOImpl adminDao;
	IDataSet loadedDataSet;
	
	@Before
	public void setUp() {
		adminDao = new AdminDAOImpl(DBConnection.getConnectionToDB());
		testDao = new TestDao("admin.xml");
	}
	
	@Test
	public void testGetAdminByName() throws SQLException {
		Admin admin = adminDao.getAdmin("Admin");
		assertNotNull(admin);
	}
	
	@Test
	public void testLoadedDataSet() throws Exception {
		loadedDataSet = getDataSet();
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(ADMIN_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
	
	public IDataSet getDataSet() throws Exception {
		loadedDataSet = testDao.getDataSet();
		return loadedDataSet;
	}
}
