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
    }
}