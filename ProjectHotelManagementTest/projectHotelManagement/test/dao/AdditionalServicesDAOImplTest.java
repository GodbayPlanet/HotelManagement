package projectHotelManagement.test.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.AdditionalServicesDAO;
import projectHotelManagement.dao.AdditionalServicesDAOImpl;
import projectHotelManagement.data.AdditionalServices;

public class AdditionalServicesDAOImplTest {
	
	private static final int SERVICES_ID = 1;
	@Mock
	private AdditionalServicesDAO additionalServices;
	@Mock
	Connection connection;
	
	@Before
	public void setUp() {
		connection = DBConnection.getConnectionToDB();
		additionalServices = new AdditionalServicesDAOImpl(connection);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindByIdDao() {
		additionalServices.findById(SERVICES_ID);
		Mockito.verify(additionalServices).findById(SERVICES_ID);
	}
	
	@Test
	public void testFindByIdDaoImpl() {
		Mockito.when(additionalServices.findById(SERVICES_ID)).thenReturn(createAdditionalServices());
		AdditionalServices gym = additionalServices.findById(SERVICES_ID);
		Assert.assertEquals(10, gym.getGymPrice());
	}

	private AdditionalServices createAdditionalServices() {
		AdditionalServices additionalService = new AdditionalServices();
		additionalService.setServicesId(SERVICES_ID);
		return additionalService;
	}
	
	@After
	public void tearDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
