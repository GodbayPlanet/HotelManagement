package projectHotelManagement.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.dao.HotelStatusDAOImpl;
import projectHotelManagement.data.User;
import projectHotelManagement.validations.HotelStatusValidation;
import projectHotelManagement.data.Messages;

/**
 * Korisnicka aplikacija
 * 
 * @author amer
 *
 */

public class UserApplication {

	public static Connection connection = DBConnection.getConnectionToDB();
	public static UserDAOImpl customerDAOImpl = new UserDAOImpl(connection);
	public static HotelStatusDAOImpl hotelStatusDAOImpl = new HotelStatusDAOImpl(connection);

	/**
	 * Narucivanje novih servisa od strane korisnika
	 * @author amer
	 * @param input
	 * @throws SQLException
	 */
	public static void orderNewServices(Scanner input, String userName) throws SQLException {
		User customer = customerDAOImpl.getCustomerByUserName(userName);
		ArrayList<Integer> listOfServices = new ArrayList<>();
		input.nextLine();
		while (true) {
			
			System.out.println(Messages.ADD_SERVICES_LIST);
			System.out.print("Izaberite usluge koje zelite (Mozete izabrati vise usluga odjednom,"
					+ " u tom slucaju odvojite unos sa razmakom npr. 1 2 3): ");

			String userInput = input.nextLine();

			if (isInputValid(userInput)) {
				String[] arrayOfServices = userInput.split(" ");
				listOfServices = getListOfServices(arrayOfServices);
				hotelStatusDAOImpl.bookAdditionalServices(customer.getPersonalIdNumber(), listOfServices);
				break;
			} else {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/**
	 * Returns array list with additional services that user choosed.
	 * @param arrayOfServices
	 * @return
	 */
	private static ArrayList<Integer> getListOfServices(String[] arrayOfServices) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < arrayOfServices.length; i++) 
			list.add(Integer.parseInt(arrayOfServices[i]));
		return list;
	}

	/**
	 * @author Nemanja
	 * @param userInput
	 * @return
	 */
	private static boolean isInputValid(String userInput) {
		for (int i = 0; i < userInput.length(); i++)
			if (Character.isAlphabetic(userInput.charAt(i))) {
				System.out.println("NESTO NE VALJA");
				return false;
			}
		return true;
	}

	/**
	 * Promjena sobe
	 * @author amer
	 * @param userName
	 * @throws SQLException
	 */
	public static void roomChange(Scanner input, String userName) throws SQLException {

		int roomId = 0;
		boolean on = true;
		while (on) {

			try {
				System.out.print("Upisite ID sobe koju zelite: ");
				roomId = input.nextInt();
				on = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}
		}
		User user = customerDAOImpl.getCustomerByUserName(userName);
		
		if(HotelStatusValidation.isFreeToBook(user.getPersonalIdNumber()))
			hotelStatusDAOImpl.changeTheRoom(getCustomerID(userName), roomId);
		else
			System.out.println("Zao nam je ne mozete rezervisati vise soba za isti datum. Dodjite sutra"
					+ " za novu rezervaciju.");

	}

	/**
	 * Metoda vraca ID korisnika kada upise svoj userName radi lakse primjene u
	 * drugim metodama
	 * @author amer
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	public static int getCustomerID(String userName) throws SQLException {
		int id = 0;
		User customer = customerDAOImpl.getCustomerByUserName(userName);
		id = customer.getPersonalIdNumber();
		return id;
	}

	/**
	 * Funkcije korisnickog menija
	 * @author amer
	 * @param input
	 * @param userName
	 * @throws SQLException
	 */
	public static void userMenu(Scanner input, String userName) throws SQLException {
		boolean running = true;
		int option = 0;
		while (running) {
			try {
				boolean on = true;
				while (on) {
					System.out.println("\n" + Messages.USER_MAIN_MENU);
					System.out.print("\nUpisite opciju: ");
					option = input.nextInt();
					switch (option) {
					case 1:
						hotelStatusDAOImpl.printCurrentAmountToPay(getCustomerID(userName));
						break;
					case 2:
						orderNewServices(input, userName);
						break;
					case 3:
						roomChange(input, userName);
						break;
					case 4:
						hotelStatusDAOImpl.printBill(getCustomerID(userName));
						on = false;
						break;
					case 5:
						on = false;
						System.out.println("\n\n");
						Application.runApp();
						break;
					default:
						break;
					}
				}
				running = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				e.printStackTrace();
			}

		}

	}

}
