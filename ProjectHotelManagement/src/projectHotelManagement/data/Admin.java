package projectHotelManagement.data;

/**
 * Class that describe Administrator.
 * @author Nemanja
 *
 */
public class Admin {

	private int id;
	private String adminName;
	private String adminPassword;
	
	public Admin(int id, String adminName, String adminPassword) {
		this.id = id;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	public int getId() {
		return id;
	}

	public String getAdminName() {
		return adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}
	
	
	
}
