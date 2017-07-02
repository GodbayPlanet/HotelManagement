package projectHotelManagement.test.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.AdminDAO;
import projectHotelManagement.dao.AdminDAOImpl;
import projectHotelManagement.data.Admin;

public class AdminDAOImplTest {
	
	@Mock private Connection connection;
	@Mock private AdminDAO admin;
	
	@Before
	public void setUp() {
		connection = DBConnection.getConnectionToDB();
		admin = new AdminDAOImpl(connection);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAdminByName() throws SQLException {
		Mockito.when(admin.getAdmin("Nemanja")).thenReturn(new Admin(1, "Nemanja", "password"));
		Admin administrator = admin.getAdmin("Nemanja");
		Assert.assertEquals("Nemanja", administrator.getAdminName());
		Assert.assertNotNull(administrator);
	}
	
}
