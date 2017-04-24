package projectHotelManagement.data;

/**
 * Class that describe User.
 * @author Nemanja
 *
 */
public class User {

	private int personalIdNumber;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String gender;
	private int age;
	private int isLogged;
	
	/**
	 * Construct the User object with specified name, lastName, gender, personalIdNumber, age, 
	 * userName and userPassword.
	 * @param name
	 * @param lastName
	 * @param gender
	 * @param personalIdNumber
	 * @param age
	 * @param userName
	 * @param userPassword
	 */
	public User(int personalIdNumber, String userName, String firstName, String lastName, String password, 
			String gender, int age, int isLogged) {
		this.personalIdNumber = personalIdNumber;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.isLogged = isLogged;
		
	}
	
	@Override
	public String toString() {
		return "Name and last name: " + firstName + " " + lastName
				+ "\npersonal ID number: " + personalIdNumber
				+ "\nUser name: " + userName;
	}

	public int getPersonalIdNumber() {
		return personalIdNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public int getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(int isLogged) {
		this.isLogged = isLogged;
	}
	
}
