package projectHotelManagement.data;

/**
 * @author Nemanja
 *
 */
public class Messages {

	public static void displayWelcomeMessage() {
		System.out.println("      #       #  #####  #       ####   ####   #   #  #####     ");
		System.out.println("      #   #   #  #      #      #      #    #  ## ##  #      ");
		System.out.println("***   #   #   #  ###    #      #      #    #  # # #  ###    ***");
		System.out.println("       # # # #   #      #      #      #    #  #   #  #     ");
		System.out.println("        #   #    #####  #####   ####   ####   #   #  #####  ");
	}

	public static final String ADMIN_MAIN_MENU = "PRIJAVLJENI STE KAO ADMINISTRATOR"
			+ "\n\n1. Registracija korisnika. " + "\n2. Azuriranje informacije o korisniku.\n"
			+ "3. Provjera i mijenjanje statusa logovanih korisnika.\n4. Lista svih korisnika.\n5. Pretraga korisnika."
			+ "\n6. Brisanje korisnika.\n7. Odjava korisnika (arhiviranje) i izdavanje racuna.\n8. Pocetni meni \n9. Izlaz !";
	
	public static final String PRETRAGA_KORISNIKA = "MOLIMO IZABERITE NACIN PRETRAGE"
			+ "\n\n1. Pretraga po korisnickom imenu."
			+ "\n2. Pretraga po broju licne karte\n3. Pretraga po imenu.\n4. Izlaz. ";

	public static final String USER_MAIN_MENU = "PRIJAVLJENI STE KAO KORISNIK"
			+ "\n\n1. Provjera trenutnog iznosa racuna. "
			+ "\n2. Narucivanje nove usluge.\n3. Promjena sobe.\n4. Odjava iz hotela.\n5. Izlaz !";

	public static final String ADD_SERVICES_LIST = "--- Dodatne usluge ---\n\n1. Restoran --> 20KM\n2. Sauna --> 10KM"
			+ "\n3. Bazen --> 10KM\n4. Teretana --> 10KM\n5. Kino --> 10KM";

	public static final String EXCEPTION = "Unos nije validan ! Pokusajte ponovo: ";
	
	public static final String LOGIN_SCREEN = "1.Prijavi se.\n2.Registruj se.";
}
