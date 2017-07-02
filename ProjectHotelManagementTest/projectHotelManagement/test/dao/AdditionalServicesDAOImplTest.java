package projectHotelManagement.test.dao;

import java.sql.Connection;

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
		additionalServices.findById(1);
		Mockito.verify(additionalServices).findById(1);
	}
	
	@Test
	public void testFindByIdDaoImpl() {
		Mockito.when(additionalServices.findById(1)).thenReturn(createAdditionalServices());
		AdditionalServices gym = additionalServices.findById(1);
		Assert.assertEquals(10, gym.getGymPrice());
	}

	private AdditionalServices createAdditionalServices() {
		AdditionalServices additionalService = new AdditionalServices();
		additionalService.setServicesId(1);
		return additionalService;
	}
}
