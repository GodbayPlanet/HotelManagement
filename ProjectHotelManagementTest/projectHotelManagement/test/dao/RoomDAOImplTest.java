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
import projectHotelManagement.dao.RoomDAO;
import projectHotelManagement.dao.RoomDAOImpl;
import projectHotelManagement.data.Room;

public class RoomDAOImplTest {
	
	private static final int ROOM_ID = 1;
	private static final int ROOM_PRICE = 20;
	@Mock
	private Connection mockConnection;
	@Mock
	private RoomDAO mockRoomDAO;
	
	@Before
	public void setUp() {
		mockConnection = DBConnection.getConnectionToDB();
		mockRoomDAO = Mockito.mock(RoomDAOImpl.class);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetRoomById() {
		Mockito.when(mockRoomDAO.getRoomById(ROOM_ID)).thenReturn(createRoom());
		Room room = mockRoomDAO.getRoomById(ROOM_ID);
		Assert.assertEquals(ROOM_ID, room.getRoomNumber());
	}
	
	private Room createRoom() {
		return new Room(ROOM_ID, "SingleRoom", ROOM_PRICE);
	}
	
	@After
	public void tearDown() {
		try {
			mockConnection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
