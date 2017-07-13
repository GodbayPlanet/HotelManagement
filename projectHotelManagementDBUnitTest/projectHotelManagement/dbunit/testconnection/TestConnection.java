package projectHotelManagement.dbunit.testconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import projectHotelManagement.connection.DBConnection;

public class TestConnection extends DatabaseTestCase {
	
	public static final String ADMIN_TABLE_NAME = "admin";
	private IDataSet loadedDataSet;

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		Connection jdbcConnection = DriverManager.getConnection()
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
