package projectHotelManagement.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.HotelStatusDAOImpl;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.data.Messages;
import projectHotelManagement.data.User;
import projectHotelManagement.validations.HotelStatusValidation;

public class AdminApplication {

	public static Connection connection = DBConnection.getConnectionToDB();
	public static UserDAOImpl userDAOImpl = new UserDAOImpl(connection);
	public static HotelStatusDAOImpl hotelStatusDAOImpl = new HotelStatusDAOImpl(connection);
	static final int OFFLINE = 0;
	static final int ONLINE = 1;

	/**
	 * Updating specific user
	 * @author Nemanja
	 * @param input
	 * @return
	 */
	public static User updateCustomer(Scanner input) {
		int personalIdNumber = 0;
		String userName = "";
		String firstName = "";
		String lastName = "";
		String password = "";
		String gender = "";
		int age = 0;
		int isLogged = 0;
		boolean on = true;
		while (on) {
			try {
				System.out.print("Upisite ID korisnika kojeg zelite modifikovati: ");
				personalIdNumber = input.nextInt();
				boolean id = true;
				while (id) {
					if (HotelStatusValidation.isUserIdExists(personalIdNumber)) {
						userDAOImpl.getCustomerByID(personalIdNumber);
						System.out.println("Korisnicki podaci:\n" + userDAOImpl.toString(personalIdNumber));
						input.nextLine();

						System.out.println("Upisite novi naziv korisnickog racuna: ");
						boolean uN = true;
						while (uN) {
							userName = input.nextLine();

							if (HotelStatusValidation.isUserNameExists(userName)) {
								System.out.println("Naziv korisnickog racuna: " + userName
										+ " je vec zauzet, molimo upisite neki drugi: ");
							} else {
								uN = false;
							}
						}
						System.out.println("Upisite ime korisnika: ");
						firstName = input.nextLine();
						System.out.println("Upisite prezime korisnika: ");
						lastName = input.nextLine();
						System.out.println("Upisite spol korisnika: ");
						gender = input.nextLine();
						System.out.println("Upisite godine korisnika: ");
						age = input.nextInt();
						password = (int) (999 + Math.random() * 9000) + "";
						System.out.println("Password korisnickog racuna: " + userName + " je: " + password + ". ");
						on = false;
						id = false;
					} else {
						id = true;
					}
				}

			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}

		User customer = new User(personalIdNumber, userName, firstName, lastName, password, gender, age, isLogged);
		return customer;
	}

	/**
	 * Adding user in database with validation of ID and user name, password is
	 * generated automaticly if all data is input correctly.
	 * @author Nemanja
	 * @param input
	 * @return
	 */
	public static User addCustomer(Scanner input) {
		int personalIdNumber = 0;
		String userName = "";
		String firstName = "";
		String lastName = "";
		String password = "";
		String gender = "";
		int age = 0;
		int isLogged = 0;
		boolean on = true;
		while (on) {

			try {
				System.out.print("Upisite ID korisnika: ");
				boolean id = true;
				while (id) {
					personalIdNumber = input.nextInt();
					if (HotelStatusValidation.isUserIdExists(personalIdNumber)) {
						System.out.println(
								"Korisnicki ID: " + personalIdNumber + " je vec zauzet, molimo upisite neki drugi: ");
					} else {
						id = false;
					}
				}
				input.nextLine();
				System.out.println("Upisite naziv korisnickog racuna: ");
				boolean uN = true;
				while (uN) {
					userName = input.nextLine();
					if (HotelStatusValidation.isUserNameExists(userName)) {
						System.out.println("Naziv korisnickog racuna: " + userName
								+ " je vec zauzet, molimo upisite neki drugi: ");
					} else {
						uN = false;
					}
				}
				System.out.println("Upisite ime korisnika: ");
				firstName = input.nextLine();
				System.out.println("Upisite prezime korisnika: ");
				lastName = input.nextLine();
				System.out.println("Upisite spol korisnika: ");
				gender = input.nextLine();
				System.out.println("Upisite godine korisnika: ");
				age = input.nextInt();
				password = (int) (999 + Math.random() * 9000) + "";
				System.out.println("Password korisnickog racuna: " + userName + " je: " + password + ". ");

				System.out.println("Unesite ID sobe koju korisnik zeli: ");
				boolean roomFree = true;
				while (roomFree) {
					int roomId = input.nextInt();

					if (!HotelStatusValidation.isRoomFreeFromHotelStatus(roomId)) {
						System.out.println("Soba sa ID " + roomId + " je vec zauzeta. Unesite ID druge sobe: ");
					} else {
						hotelStatusDAOImpl.bookRoom(personalIdNumber, roomId);
						roomFree = false;
					}
				}
				on = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}
		User customer = new User(personalIdNumber, userName, firstName, lastName, password, gender, age, isLogged);
		return customer;
	}

	/**
	 * Changing user status from online to offline, it's possible to change user
	 * status to all customers.
	 * @author Nemanja
	 * @param input
	 */
	public static void changeCustomerStatus(Scanner input) {

		int option = 0;
		boolean running = true;
		while (running) {
			try {
				boolean on = true;
				while (on) {
					System.out.println("\n1. Lista svi online korisnika \n2. Odlogovati sve korisnike.\n3."
							+ " Odlogovati odredjenog korisnika.\n4. Izlaz.");
					System.out.println("Odaberite opciju:");
					option = input.nextInt();
					switch (option) {

					case 1:
						printAllLoggedUsers();
						break;
					case 2:
						userDAOImpl.loggedOffAllUsers();
						break;
					case 3:
						System.out.print("Upisite ID broj korisnika kojeg zelite odjaviti: ");
						int personalIdNumber = input.nextInt();
						userDAOImpl.updateColumnIsLogged(personalIdNumber, OFFLINE);
						break;
					case 4:
						on = false;
						break;
					default:
						System.out.println("Molimo unesite 1, 2 ili 3: ");
						input.nextLine();
						break;
					}
				}
				running = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/**
	 * Prints all cutomers that are online.
	 * @author Nemanja
	 * @throws SQLException
	 */
	public static void printAllLoggedUsers() throws SQLException {
		Set<User> customers = userDAOImpl.getAllCustomers();
		for (User customer : customers)
			if (customer.getIsLogged() == 1)
				System.out.println("Korisnik " + customer.getUserName() + " je online.");
	}

	/**
	 * Method for searching customer by customer name.
	 * @author Nemanja
	 * @param input
	 * @throws SQLException
	 */
	public static void searchCustomerByName(Scanner input) throws SQLException {
		input.nextLine();
		System.out.println("Upisite ime korisnika: ");
		String firstName = input.nextLine();

		if (!HotelStatusValidation.isUserFirstNameExists(firstName))
			System.out.println("Korisnik sa imenom " + firstName + " ne postoji.");
		else
			System.out.println("Users data:\n" + userDAOImpl.getCustomerByName(firstName).toString());
	}

	/**
	 * Method for searching customer by customer id.
	 * @author Nemanja
	 * @param input
	 * @throws SQLException
	 */
	public static void searchCustomerByID(Scanner input) throws SQLException {
		input.nextLine();
		System.out.println("Upisite ID korisnika: ");
		int id = input.nextInt();
		userDAOImpl.getCustomerByID(id);

		if (!HotelStatusValidation.isUserIdExists(id))
			System.out.println("Korisnik sa id " + id + " ne postoji.");
		else
			System.out.println("Korisnicki podaci:\n" + userDAOImpl.toString(id));
	}

	/**
	 * Method for searching customer by customer user name.
	 * @author Nemanja
	 * @param input
	 * @throws SQLException
	 */

	public static void searchCustomerByUserName(Scanner input) throws SQLException {
		Connection connection = DBConnection.getConnectionToDB();
		UserDAOImpl customerDAOImpl = new UserDAOImpl(connection);
		input.nextLine();
		System.out.println("Upisite naziv korisnickog racuna: ");
		String userName = input.nextLine();

		if (!HotelStatusValidation.isUserNameExists(userName))
			System.out.println("Korisnik " + userName + " ne postoji..");
		else
			System.out.println("Korisnicki podaci:\n" + customerDAOImpl.toString(userName));
	}

	/**
	 * Method for searchin customers.
	 * @author Nemanja
	 * @param input
	 * @throws SQLException
	 */
	public static void customerSearchEngine(Scanner input) throws SQLException {

		boolean running = true;
		int option = 0;
		while (running) {

			try {
				boolean on = true;
				while (on) {
					System.out.println(Messages.PRETRAGA_KORISNIKA);
					System.out.println("Upisite opciju:");
					option = input.nextInt();
					switch (option) {
					case 1:
						searchCustomerByUserName(input);
						break;
					case 2:
						searchCustomerByID(input);
						break;
					case 3:
						searchCustomerByName(input);
						break;
					case 4:
						on = false;
						break;
					default:
						break;
					}

				}
				running = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/**
	 * Displaying all users. 
	 * @author Nemanja
	 * @throws SQLException
	 */
	public static void displayAllCustomers() throws SQLException {
		Set<User> customers = userDAOImpl.getAllCustomers();
		System.out.println("Spisak svih korisnika:\n");
		for (User customer : customers) {
			System.out.println(customer.toString());
		}
	}

	public static void deleteCustomer(Scanner input) {
		boolean on = true;
		int id = 0;
		while (on) {
			try {
				System.out.println("Upisite ID korisnika: ");
				id = input.nextInt();
				userDAOImpl.deleteCustomer(id);
				on = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/**
	 * Printing bill to pay.
	 * @author Nemanja 
	 * @param input
	 */
	public static void bill(Scanner input) {
		int id = 0;
		boolean running = true;
		while (running) {
			try {
				boolean on = true;
				while (on) {
					System.out.print("Upisite ID korisnika za kojeg zelite racun: ");
					id = input.nextInt();
					if (HotelStatusValidation.isUserIdExists(id)) {
						on = false;
						hotelStatusDAOImpl.printBill(id);
					} else {
						System.out.println("Korisnik sa unesenim ID se ne nalazi u bazi, pokusajte ponovo.");
						input.nextLine();
					}
				}
				running = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/**
	 * Method for printing administrator functions menu.
	 * @author Nemanja
	 * @param input
	 * @throws SQLException
	 */

	public static void adminMenu(Scanner input) throws SQLException {
		boolean running = true;
		int option = 0;
		while (running) {
			try {
				boolean on = true;
				while (on) {
					System.out.println(Messages.ADMIN_MAIN_MENU);
					System.out.println("\nUpisite opciju: ");
					option = input.nextInt();
					switch (option) {
					case 1:
						userDAOImpl.addCustomer(addCustomer(input));
						break;
					case 2:
						userDAOImpl.updateCustomer(updateCustomer(input));
						break;
					case 3:
						changeCustomerStatus(input);
						break;
					case 4:
						displayAllCustomers();
						break;
					case 5:
						customerSearchEngine(input);
						break;
					case 6:
						deleteCustomer(input);
						break;
					case 7:
						bill(input);
						break;
					case 8:
						on = false;
						Application.runApp();
					case 9:
						System.out.println("APLIKACIJA JE ISKLJUCENA !!!");
						System.exit(0);
					default:
						System.out.println("Molimo izaberite opciju (1-7):");
						input.nextLine();
						break;
					}
				}
				running = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
			}
		}
	}
}
