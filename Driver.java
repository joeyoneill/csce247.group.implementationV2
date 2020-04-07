import java.util.Scanner;
import java.util.ArrayList;

public class Driver {
    private static final String WELCOME_MESSAGE = "************Welcome************";
    private String[] signInOptinos = { "Guest User", "Sign in" };
    private String[] menuOptions = { "Events", "View Tickets", "Logout" };
    private String[] employeeOptions = { "Create Event", "Logout" };
    private String[] venueOptions = { "" };
    private Scanner scanner;
    private ArrayList<User> users;
    private ArrayList<Venue> venues;

    Driver() {
        scanner = new Scanner(System.in);
        // read in users here
        // read in venue here
    }

    public void run() {
        System.out.println(WELCOME_MESSAGE);
    }
}