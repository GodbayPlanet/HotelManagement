package projectHotelManagement.dbunit.testDao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DatabaseTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import projectHotelManagement.connection.DBConnection;

public class TestDao extends DatabaseTestCase {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public String xmlFileName;
	private IDataSet loadedDataSet;

	public TestDao(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	
	@Before
	public void setUpDatabase() throws Exception {
		IDataSet dataSet = getDataSet();
		cleanlyInsertDataSet(dataSet);
	}

	private void cleanlyInsertDataSet(IDataSet dataSet) throws Exception {
		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, DBConnection.JDBC_URL, "root",
				"password1");
		databaseTester.setSetUpOperation(getSetUpOperation());
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		Connection jdbcConnection = DriverManager.getConnection(DBConnection.JDBC_URL, DBConnection.getProperties());
		return new DatabaseConnection(jdbcConnection);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream(xmlFileName));
		return loadedDataSet;
	}

	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

}
