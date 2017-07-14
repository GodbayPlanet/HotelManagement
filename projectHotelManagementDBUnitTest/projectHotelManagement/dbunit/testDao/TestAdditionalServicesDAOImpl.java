package projectHotelManagement.dbunit.testDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.AdditionalServicesDAOImpl;
import projectHotelManagement.data.AdditionalServices;


public class TestAdditionalServicesDAOImpl {

	private static final int RESTAURANT_SERVICE_ID = 1;
	private static final int NUMER_OF_ROWS = 5;
	private static final String ADDITIONAL_SERVICES_TABLE_NAME = "services";
	TestDao testDao;
	AdditionalServicesDAOImpl additionalServices;
	IDataSet loadedDataSet;
	
	@Before
	public void setUp() throws Exception {
		testDao = new TestDao("additionalServices.xml");
		additionalServices = new AdditionalServicesDAOImpl(DBConnection.getConnectionToDB());
		loadedDataSet = getDataSet();
	}
	
	@Test
	public void testFindById() {
		AdditionalServices service = additionalServices.findById(RESTAURANT_SERVICE_ID);
		assertNotNull(service);
		assertEquals(20, service.getRestaurantPrice());
	}
	
	@Test
	public void testLoadedDataSet() throws DataSetException {
		assertNotNull(loadedDataSet);
		int rowCount = loadedDataSet.getTable(ADDITIONAL_SERVICES_TABLE_NAME).getRowCount();
		assertEquals(NUMER_OF_ROWS, rowCount);
	}
	
	public IDataSet getDataSet() throws Exception {
		loadedDataSet = testDao.getDataSet();
		return loadedDataSet;
	}
}
