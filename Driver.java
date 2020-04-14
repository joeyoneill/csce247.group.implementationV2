import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Driver {
	public static final String WELCOME_MESSAGE = "************Welcome************";
	String[] signInOptions = { "Guest User", "Sign in", "Sign up", "Quit" };
	String[] menuOptions = { "View Events", "View Tickets", "Quit" };
	String[] employeeOptions = { "Create Event", "Quit" };
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

		// Initialize the driver arrays
		users = new ArrayList<User>();
		events = new ArrayList<Event>();

		// Read all database files on start up
		database.load(events, users);
	}

	public void run() {
		System.out.println(WELCOME_MESSAGE);
		login();

		int userCommand;
		while (true) {
			if (currentUser.getUsertype() == UserType.GUEST || currentUser.getUsertype() == UserType.REGULAR) {
				displayMenuOptions();
				userCommand = getUserInput(menuOptions.length + 1);
				/*
				 * if (userCommand == menuOptions.length - 1) {
				 * System.out.println("Going back"); break; }
				 */
				switch (userCommand) {
					case (1):
						// Events Command
						pickEvent();
						String eventString = "Enter the event number of the event you would like to select";
						userCommand = getUserInputPlusString(events.size() + 1, eventString);
						eventView(events.get(userCommand - 1));
						break;
					case (2):
						// View Tickets Command
						// If there are no purchased tickets
						if (currentUser.getTickets().isEmpty()) {
							System.out.println("Currently no tickets are owned");
							break;
						}
						// If there are tickets
						pickTicket();
						String ticketString = "Enter the ticket number of the ticket you would like to select";
						userCommand = getUserInputPlusString(currentUser.getTickets().size(), ticketString);
						// TODO ticket view is messed up. User command is getting overwritten
						ticketView(currentUser.getTickets().get(userCommand - 1));
						break;
					case (3):
						// Quit
						close();
						break;
				}
			} else if (currentUser.getUsertype() == UserType.EMPLOYEE) {
				displayEmployeeOptions();
				userCommand = getUserInput(employeeOptions.length + 1);
				/*
				 * if (userCommand == menuOptions.length - 1) {
				 * System.out.println("Logging out"); break; }
				 */
				switch (userCommand) {
					case (1):
						// create event
						createEvent();
						break;
					case (2):
						// Quit
						close();
						break;
				}
			}
		}
	}

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

					while (true) {
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
					while (true) {
						if (userTypeInput.equals("1")) {
							newUser = new User(newNameInput, newPasswordInput, UserType.REGULAR);
							break;
						} else if (userTypeInput.equals("2")) {
							newUser = new User(newNameInput, newPasswordInput, UserType.EMPLOYEE);
							break;
						} else {
							continue;
						}
					}
					users.add(newUser);
					System.out.println("Sign up completed!");
					break;
				case (4):
					// Exit
					close();
					break;
			}
			if (contLoginScreen == false)
				break;
		}
	}

	public void close() {
		System.out.println("Goodbye");
		updateDatabaseOnClose();
		System.exit(0);
	}

	private void pickEvent() {
		int i = 0;
		for (Event event : events) {
			i++;
			System.out.println(i + ". " + event.toString());
			System.out.println("********************");
		}
	}

	private void pickTicket() {
		int i = 0;
		for (Ticket ticket : currentUser.getTickets()) {
			i++;
			System.out.println(i + ". " + ticket.toString());
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
		if (command >= 0 && command <= numOfOptions) {
			return command;
		}
		return -1;
	}
	private int getUserInputPlusString(int numOfOptions, String string) {
		System.out.println(string);
		String input = scanner.nextLine();
		int command = Integer.parseInt(input); // -1 for array index
		if (command >= 0 && command <= numOfOptions) {
			return command;
		}
		return -1;
	}

	public boolean displayMenuOptions() {
		System.out.println("\n***** MENU OPTIONS *****");
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println((i + 1) + ". " + menuOptions[i]);
		}
		return true;
	}

	private void displayEmployeeOptions() {
		System.out.println("\n***** MENU OPTIONS *****");
		for (int i = 0; i < employeeOptions.length; i++) {
			System.out.println((i + 1) + ". " + employeeOptions[i]);
		}
	}

	/**
	 * View of Events From here you can buy event tickets, or leave the event a
	 * review
	 * 
	 * @param event
	 */
	public void eventView(Event event) {
		System.out.println(event.toString());
		System.out.println("********************");
		String viewInput;
		while (true) {
			System.out.println("Type 1 to buy ticket, 2 to leave a review, 3 to see reviews, or 4 to go back");
			viewInput = scanner.nextLine();

			// Buy tickets
			if (viewInput.equals("1")) {
				double price = event.cost;
				
				event.visualizeSeating();
				System.out.println("********************");

				System.out.println("How many tickets would you like to buy?");
				int ticketAmtInput;
				while (true) {
					ticketAmtInput = Integer.parseInt(scanner.nextLine());
					if (ticketAmtInput < 1 || ticketAmtInput > event.seatsRemaining()) {
						System.out.println("Input Error: Out of Bounds.");
						continue;
					}
					break;
				}

				for (int i = 0; i < ticketAmtInput; i++) {
					while (true) {
						System.out.println("Enter the row you wish to choose from.");
						int rowInput = Integer.parseInt(scanner.nextLine()) - 1;
						if (rowInput < 0 || rowInput > event.venue.getRows()) {
							System.out.println("Invalid Input");
							continue;
						} 
						else {
							System.out.println("Enter the seat you wish to choose.");
							int colInput = Integer.parseInt(scanner.nextLine()) - 1;
							if (colInput < 0 || colInput > event.venue.getColumns()) {
								System.out.println("Invalid Input284");
								continue;
							} 
							else {
								if (event.checkSeatAvailability(rowInput, colInput) == true) {
									while(currentUser.getCCNum() == null) {
										System.out.println("Enter credit card number (must be 16 digits)");
										String ccNum = scanner.nextLine();
										currentUser.setCreditCardNumber(ccNum);
										if(currentUser.getCCNum() == null) {
											System.out.println("Credit card number does not follow the correct format");
										}
									}
									String ccNum = currentUser.getCCNum();
									System.out.println("Charging $" + price + " to credit card " + "************" +
											ccNum.charAt(12) + ccNum.charAt(13) + ccNum.charAt(14) + ccNum.charAt(15));
									Ticket ticket = new Ticket(currentUser.getName(), event, rowInput, colInput, true);
									currentUser.purchaseTicket(ticket);
									System.out.println("Ticket purchased!");
									event.seats[rowInput][colInput] = true;
									break;
								}
							}
						}
					}
				}
				// continue;
			}
			// Ticket Review
			else if (viewInput.equals("2")) {
				// scanner.nextLine();
				System.out.println("Enter your review's title.");
				String reviewTitleInput = scanner.nextLine();
				System.out.println("Enter your review.");
				String reviewInput = scanner.nextLine();
				System.out.println("Enter your rating. (An integer value between 0 and 5)");
				int ratingInput;

				while (true) {
					String tmpInput = scanner.nextLine();
					ratingInput = Integer.parseInt(tmpInput);
					if (ratingInput < 0 || ratingInput > 5) {
						System.out.println("Input Error: Out of bounds.");
						continue;
					}
					break;
				}

				event.reviews.add(new Review(event.name, reviewTitleInput, reviewInput, ratingInput));
				break;
			} else if (viewInput.equals("3")) {
				eventReviews(event);
				break;
			} else {
				// System.out.println("Invalid Input.");
				break;
			}
		}
	}

	/**
	 * View of tickets From here you can view and print tickets
	 * 
	 * @param ticket
	 */
	public void ticketView(Ticket ticket) {
		System.out.println(ticket.toString());
		System.out.println("********************");
		while (true) {
			System.out.print("Type 1 to print ticket, 2 to return to the main menu");

			if (ticket.isRefundable == true)
				System.out.print(", or 3 to get a refund on your ticket.");
			System.out.print("\n");
			int ticketInput;

			while (true) {
				ticketInput = scanner.nextInt();
				if (ticket.isRefundable == false && (ticketInput < 1 || ticketInput > 2)) {
					System.out.println("Invalid Input: Out of Bounds.");
					continue;
				} else if (ticket.isRefundable == true && (ticketInput < 1 || ticketInput > 3)) {
					System.out.println("Invalid Input: Out of Bounds.");
					continue;
				}
				break;
			}

			if (ticketInput == 1) {
				ticket.printTicket();
				System.out.println("Ticket printed to file: " + ticket.event.name + "_ticket.txt");
				System.out.println("********************");
				continue;
			} else if (ticketInput == 2) {
				// TODO go back to home view
				scanner.nextLine();
				break;
			} else if (ticketInput == 3) {
				currentUser.removeTicket(ticket);
				ticket.event.seats[ticket.seatRow][ticket.seatCol] = false;
				System.out.println("Ticket refunded!");
				database.writeTicketDBFile("ticketInput.txt", users);
				scanner.nextLine();
				continue;
			}
		}
	}

	public void eventReviews(Event event) {
		for (int i = 0; i < event.reviews.size(); i++) {
			System.out.println("********************");
			System.out.println(event.reviews.get(i).toString());
			System.out.println("********************");
		}
	}

	// rewrites all db files with updated information
	public boolean updateDatabaseOnClose() {
		database.save(events, users);
		return true;
	}

	// allows employees to create events
	public void createEvent() {
		System.out.println("Which type of event would you like to create?");
		System.out.println("Type 1 for Concert, 2 for Movie, or 3 for Play.");
		int eventInput;

		while (true) {
			eventInput = Integer.parseInt(scanner.nextLine());
			if (eventInput < 1 || eventInput > 3) {
				System.out.println("Input Error: Out of Bounds.");
				continue;
			} 
			else {
				break;
			}
		}

		System.out.println("Enter the Event's name");
		String eventNameInput = scanner.nextLine();

		System.out.println("Enter the Event's date (format: YYYY-MM-DD)");
		String eventDateInput = scanner.nextLine();

		System.out.println("Enter the Event's time (format: HH:MM:SS)");
		String eventTimeInput = scanner.nextLine();
		
		System.out.println("Enter the venue name");
		String venueName = scanner.nextLine();
		
		System.out.println("Enter the number of rows of seats in the venue");
		int venueRows = -1;
		while(venueRows <= 0 || venueRows >= 1000) {
			String rows = scanner.nextLine();
			venueRows = Integer.parseInt(rows);
		}
		
		System.out.println("Enter the number of columns of seats in the venue");
		int venueColumns = -1;
		while(venueColumns <= 0 || venueColumns > 1000) {
			String columns = scanner.nextLine();
			venueColumns = Integer.parseInt(columns);
		}
		
		System.out.println("Enter ticket cost in XX.XX (double format)");
		double cost = -1.0;
		while(cost < 0) {
			cost = Double.parseDouble(scanner.nextLine());
		}
		
		venueRows--;
		venueColumns--;
		
		Venue venue = new Venue(venueName, venueRows, venueColumns);
		
		Event newEvent;
		if (eventInput == 1) {
			newEvent = new Concert(eventNameInput, venue, LocalDate.parse(eventDateInput),
					LocalTime.parse(eventTimeInput), cost);
		} else if (eventInput == 2) {
			newEvent = new Movie(eventNameInput, venue, LocalDate.parse(eventDateInput),
					LocalTime.parse(eventTimeInput), cost);
		} else {
			// else can only be eventInput == 3
			newEvent = new Play(eventNameInput, venue, LocalDate.parse(eventDateInput),
					LocalTime.parse(eventTimeInput), cost);
		}
		events.add(newEvent);
		System.out.println("Event " + newEvent.name + " added!");
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.run();
	}
}