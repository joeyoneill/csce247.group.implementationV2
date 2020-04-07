import java.util.Scanner;
import java.util.ArrayList;

public class Driver {
    private static final String WELCOME_MESSAGE = "************Welcome************";
    private String[] signInOptions = { "Guest User", "Sign in" };
    private String[] menuOptions = { "Events", "View Tickets", "Logout" };
    private String[] employeeOptions = { "Create Event", "Logout" };
    private String[] venueOptions = { "" };
    private Scanner scanner;
    private ArrayList<User> users;
    private User currentUser;
    private ArrayList<Venue> venues;

    // Venues
    Venue playVenue = new Venue("playVenue", 5, 10);
    Venue concertVenue = new Venue("concertVenue", 20, 20);
    Venue movieVenue = new Venue("movieVenue", 5, 10);

    Driver() {
        scanner = new Scanner(System.in);
        // read in users here
        // read in venue here
    }

    public void run() {
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            displayLoginMenu();
            int userCommand = getUserInput(signInOptions.length);
            if (userCommand == -1) {
                System.out.println("Not valid command");
                continue;
            }
            if (userCommand == signInOptions.length - 1) {
                System.out.println("Goodbye");
                break;
            }
            switch (userCommand) {
                case (0):
                    // guest user
                    break;
                case (1):
                    // sign in
                    break;
            }
            if (currentUser.getUsertype() == UserType.GUEST || currentUser.getUsertype() == UserType.REGULAR) {
                displayMenuOptions();
            }

        }
    }

    }

    private void displayLoginMenu() {
        System.out.println("\n***** LOGIN MENU *****");
        for (int i = 0; i < signInOptions.length; i++) {
            System.out.println((i + 1) + ". " + signInOptions[i]);
        }
    }

    private int getUserInput(int numOfOptions) {

        System.out.println("Please choose a menu option: (Number input)");
        String input = scanner.nextLine();
        int command = Integer.parseInt(input); // -1 for array index
        if (command >= 0 && command < numOfOptions) {
            return command;
        }
        return -1;
    }
}