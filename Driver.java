import java.util.Scanner;
import java.util.ArrayList;

public class Driver {
    public static final String WELCOME_MESSAGE = "************Welcome************";
    String[] signInOptions = { "Guest User", "Sign in", "Sign up", "Quit"};
    String[] menuOptions = { "Events", "View Tickets", "Back" };
    String[] employeeOptions = { "Create Event", "Logout" };
    String[] venueOptions = { "" };
    Scanner scanner;
    ArrayList<User> users;
    User currentUser;
    // private ArrayList<Venue> venues;
    Database database;
    ArrayList<Event> events;

    // Venues
    Venue playVenue = new Venue("playVenue", 5, 10);
    Venue concertVenue = new Venue("concertVenue", 20, 20);
    Venue movieVenue = new Venue("movieVenue", 5, 10);

    public Driver() {
        scanner = new Scanner(System.in);
        database = new Database();
        //database.readEventDBFile("eventsInput.txt", events);
        //database.readUserDBFile("userInput.txt", users);
        users = new ArrayList<User>();
        users.add(new User("name","pass",UserType.REGULAR));
        events = new ArrayList<Event>();

    }

    /*
    public void run() {
        System.out.println(WELCOME_MESSAGE);
        
         // this will be end of first while loop
            
            if (currentUser.getUsertype() == UserType.GUEST || currentUser.getUsertype() == UserType.REGULAR) {
                displayMenuOptions();
                userCommand = getUserInput(menuOptions.length);
                if (userCommand == menuOptions.length - 1) {
                    System.out.println("Going back");
                    break;
                }
                switch (userCommand) {
                    case (0):
                        pickEvent();
                        userCommand = getUserInput(events.size());
                        events.get(userCommand);
                        break;
                    case (1):
                        //
                        break;
                }

            }
            if (currentUser.getUsertype() == UserType.EMPLOYEE) {
                displayEmployeeOptions();
                if (userCommand == menuOptions.length - 1) {
                    System.out.println("Logging out");
                    break;
                }
                switch (userCommand) {
                    case (0):
                        // create event
                        break;
                }
            }

        }
    }*/

	public void login() {
		boolean contLoginScreen = true;
		while (true) {
			displayLoginMenu();
            int userCommand = getUserInput(signInOptions.length + 1);
            if (userCommand == -1) {
                System.out.println("Not valid command");
                continue;
            }
            switch (userCommand) {
                case (1):
                    // guest user
                    currentUser = new User();
                	contLoginScreen = false;
                    break;
                case (2):
                    // sign in
                    System.out.println("Enter your username: ");
                    String userNameInput = scanner.nextLine();
                    System.out.println("Enter your password: ");
                    String passwordInput = scanner.nextLine();
                    
                    // Checks if user exists
                    boolean userExists = false;
                    for (int i = 0; i < users.size(); i++) {
                    	if (users.get(i).getName().equalsIgnoreCase(userNameInput)) {
                    		if (!passwordInput.equals(users.get(i).getPassword())) {
                    			System.out.println("Invalid Password");
                    			break;
                    		}
                    		userExists = true;
                    		currentUser = users.get(i);
                    		contLoginScreen = false;
                    		break;
                    	}
                    }
                    if (userExists == false) {
                    	System.out.println("User/Password Combination does not exist.");
                    }
                    break;
                case (3):
                    // sign up
                	User newUser;
                    String newNameInput;
                    String userTypeInput;
                	System.out.println("Are you a regular user or an employee?");
                    System.out.println("Enter 1 for regular or 2 for employee.");
                    userTypeInput = scanner.nextLine();
                    
                    while(true) {
                    	System.out.println("Enter a username: ");
                    	newNameInput = scanner.nextLine();
                    	
                    	boolean nameExists = false;
                    	for (int i = 0; i < users.size(); i++) {
                        	if (users.get(i).getName() == newNameInput) {
                        		System.out.println("Username already exists.");
                        		nameExists = true;
                        		break;
                        	}
                    	}
                    	if (nameExists == false) {
                    		break;
                    	}
                    }
                    
                    System.out.println("Enter a password: ");
                    String newPasswordInput = scanner.nextLine();
                    
                    // Create new user
                    while(true) {
                    	if (userTypeInput.equals("1")) {
                        	newUser = new User(newNameInput, newPasswordInput, UserType.REGULAR);
                        	break;
                        }
                        else if (userTypeInput.equals("2")) {
                        	newUser = new User(newNameInput, newPasswordInput, UserType.EMPLOYEE);
                        	break;
                        }
                        else {
                        	continue;
                        }
                    }
                    users.add(newUser);
                    System.out.println("Sign up completed!");
                    break;
                case (4):
                    // Exit
                    System.out.println("Goodbye!");
                	System.exit(0);
                    break;
            }
            if (contLoginScreen == false)
            	break;
		}
	}
	
    private void pickEvent() {
        int i = 0;
        for (Event event : events) {
            i++;
            System.out.println(i + ". " + event.toString());
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

    private void displayMenuOptions() {
        System.out.println("\n***** MENU OPTIONS *****");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }
    }

    private void displayEmployeeOptions() {
        System.out.println("\n***** MENU OPTIONS *****");
        for (int i = 0; i < employeeOptions.length; i++) {
            System.out.println((i + 1) + ". " + employeeOptions[i]);
        }
    }
    
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.login();      
    }
}