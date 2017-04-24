package projectHotelManagement.application;

import java.sql.Connection;
import java.util.Scanner;

import projectHotelManagement.connection.DBConnection;
import projectHotelManagement.dao.UserDAOImpl;
import projectHotelManagement.data.Messages;
import projectHotelManagement.data.User;
import projectHotelManagement.validations.LoginValidation;

public class Application {

	public static Scanner input = new Scanner(System.in);
	public static Connection connection = DBConnection.getConnectionToDB();
	public static UserDAOImpl userDAOImpl = new UserDAOImpl(connection);
	
	/**
	 * Prijava pri pokretanju aplikacije i provjera validnosti unesenih podataka
	 * 
	 * @author amer
	 */
	public static void runApp() {

		Messages.displayWelcomeMessage();
		System.out.println("\nLanguage / Jezik / Sprache ");
		System.out.println("\n1. Bosanski\n2. Srpski\n3. Hrvatski");
		int options = 0;
		boolean running = true;
		while (running) {
			try {
				System.out.print("\nUpisite opciju: ");
				boolean on = true;
				while (on) {
					options = input.nextInt();
					if (options == 1 || options == 2 || options == 3) {
						on = false;
						System.out.println("\n--- Jezik uspjesno odabran ---");
						boolean login = true;
						while (login) {
							
							System.out.print("Upisite ime korisnickog racuna: ");
							String userName = input.next();
							System.out.print("Upisite sifru korisnickog racuna: ");
							String password = input.next();
							if (LoginValidation.isValidAdminLogin(userName, password)) {
								login = false;
								System.out.println("Uspjesno ste logovani kao administrator !");
								AdminApplication.adminMenu(input);
							} else if (LoginValidation.isValidUserLogin(userName, password)) {
								login = false;
								System.out.println("Uspjesno ste logovani kao korisnik !");
								User user = userDAOImpl.getCustomerByUserName(userName);
								userDAOImpl.updateColumnIsLogged(user.getPersonalIdNumber(), 1);
								UserApplication.userMenu(input, userName);
							} else {
								System.out.println("\nUneseni podaci nisu validni, pokusajte ponovo. ");
								input.nextLine();
							}
						}
					} else {
						System.out.println("Molimo da izaberete opciju 1, 2 ili 3: ");
					}
				}
				running = false;
			} catch (Exception e) {
				System.out.println(Messages.EXCEPTION);
				input.nextLine();
			}

		}
	}
}