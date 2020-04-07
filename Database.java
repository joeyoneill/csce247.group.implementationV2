import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Database.java: Used to manage backend of ticket purchase system using text files
 * @author cdcoffin
 */
public class Database {
	//Delimiting character for file
	public static final String DELIM = "\t"; 
	//There should be 8 fields in each line of the event file
	public static final int EVENT_FIELD_AMOUNT = 8; 
	//There should be 4 fields in each line of the user file
	public static final int USER_FIELD_AMOUNT = 4; 
	//There should be 4 fields in each line of the review file
	public static final int REVIEW_FIELD_AMOUNT = 4; 
	//There should be 7 fields in each line of the ticket file
	public static final int TICKET_FIELD_AMOUNT = 7; 
	
	/**
	 * Constructor: empty (only instantiated in order to call this file's methods in the driver)
	 */
	public Database() {
	}
	
	/**
	 * Loads events, users, reviews, and tickets into the driver
	 * @param events - an arraylist of events that the driver handles
	 * @param users - an arraylist of users that the driver handles
	 */
	public void load(ArrayList <Event> events, ArrayList <User> users) {
    	readEventDBFile("events.txt", events);
		readUserDBFile("users.txt", users);
		readReviewDBFile("reviews.txt", events);
		readTicketDBFile("tickets.txt", users, events);
    }
	
	/**
	 * Saves events, users, reviews, and tickets into the database  
	 * @param events - an arraylist of events that the driver handles
	 * @param users - an arraylist of users that the driver handles
	 */
	public void save(ArrayList <Event> events, ArrayList <User> users) {
		writeEventDBFile("events.txt", events);
		writeUserDBFile("users.txt", users);
		writeReviewDBFile("reviews.txt", events);
		writeTicketDBFile("tickets.txt", users);
	}
	
	/**
	 * writes all events to database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param events - arraylist of events handled by the driver
	 */
	public void writeEventDBFile(String aFileName, ArrayList<Event> events) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			
			//Iterate through events: store each event as a line in the text file
			for(Event event : events) {
				 if(event == null)
					 break;
				 //Store the boolean array of seats as a string so that it can fit within one line
				 String boolString = boolArrayToString(event.venue.getRows(), event.venue.getColumns(), event.seats);
				 fileWriter.println(event.name+DELIM+
						event.venue.getName()+DELIM+
						event.venue.getRows()+DELIM+
						event.venue.getColumns()+DELIM+
						event.getType()+DELIM+
						event.date.toString()+DELIM+
						event.time.toString()+DELIM+
				 		boolString);
				 		
			 }
			 fileWriter.close();//DON'T FORGET TO CLOSE STREAMS
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
	}
	

	/**
	 * Read all events from database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param events - arraylist of events handled by the driver
	 */
	public void readEventDBFile(String aFileName, ArrayList<Event> events) {
		try {
			Scanner fileScanner = new Scanner (new File(aFileName));
			//Iterate through lines of fileScanner
			while(fileScanner.hasNextLine()) {
				//Store each line as string, then split with DELIM character
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				//If there are too many fields for this event on the line, skip to next line with continue (don't save this one)
				if(splitStr.length != EVENT_FIELD_AMOUNT) {
					continue;
				}
				//Store split strings (parse to correct type when necessary)
				String name = splitStr[0];
				String venueName = splitStr[1];
				int rows = Integer.parseInt(splitStr[2]);
				int columns = Integer.parseInt(splitStr[3]);
				String type = splitStr[4];
				LocalDate date = LocalDate.parse(splitStr[5]);
				LocalTime time = LocalTime.parse(splitStr[6]);
				//Convert stored string back to boolean array
				boolean array[][] = stringToBoolArray(rows, columns, splitStr[7]);
				
				//Generate the venue from stored data
				Venue venue = new Venue(venueName, rows, columns);
				
				//Determine what type of event to create. then do it and store. If none of these apply, don't create event
				if(type.equalsIgnoreCase("Movie")) {
					Event event = new Movie(name, venue, date, time, array);
					events.add(event);
				}
				else if(type.equalsIgnoreCase("Play")) {
					Event event = new Play(name, venue, date, time, array);
					events.add(event);
				}
				else if(type.equalsIgnoreCase("Concert")) {
					Event event = new Concert(name, venue, date, time, array);
					events.add(event);
				}
			}
			fileScanner.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
	} 
	
	/**
	 * Writes all users to database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param users - arraylist of users handled by the driver
	 */
	public void writeUserDBFile(String aFileName, ArrayList<User> users) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			
			//iterate through users, store each user as line within file
			for(User user : users) {
				 if(user == null)
					 break;
				 
				 fileWriter.println(user.getName()+DELIM+
						user.getPassword()+DELIM+
						user.getUsertype()+DELIM+
						user.getCCNum());
				 		
			 }
			 fileWriter.close();//DON'T FORGET TO CLOSE STREAMS
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
	}
	
	/**
	 * Read all users from database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param users - arraylist of users handled by the driver
	 */
	public void readUserDBFile(String aFileName, ArrayList<User> users) {
		try {
			//Iterate through lines of text file
			Scanner fileScanner = new Scanner (new File(aFileName));
			while(fileScanner.hasNextLine()) {
				//Take line as String and split with DELIM character
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				//If there are too many fields for a user on this line, skip to next line
				if(splitStr.length != USER_FIELD_AMOUNT) {
					continue;
				}
				String username = splitStr[0];
				String password = splitStr[1];
				String type = splitStr[2];
				String ccNum = splitStr[3];
				
				//determine userType ENUM
				UserType userType;
				switch(type) {
					case "REGULAR":
						userType = UserType.REGULAR;
						break;
					case "GUEST":
						userType = UserType.GUEST;
						break;
					case "MILITARY":
						userType = UserType.MILITARY;
						break;
					case "TEACHER":
						userType = UserType.TEACHER;
						break;
					case "CHILD":
						userType = UserType.CHILD;
						break;
					case "SENIOR":
						userType = UserType.SENIOR;
						break;
					case "EMPLOYEE":
						userType = UserType.EMPLOYEE;
						break;
					default:
						userType = UserType.REGULAR;
				}
				
				//Create user and store it within arrayList
				User user = new User(username, password, userType, ccNum);
				users.add(user);
			}
			fileScanner.close();//DON'T FORGET IT
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
	}
	
	/**
	 * Writes all reviews to database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param events - arraylist of events handled by the driver
	 */
	public void writeReviewDBFile(String aFileName, ArrayList<Event> events) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			//Iterate through events
			for(Event event : events) {
				 if(event == null)
					 break;
				 //Iterate through each review stored within the reviews arraylist of each event
				 for(Review review : event.reviews) {
					 if(review == null)
						 break;
					 fileWriter.println(review.getEventName()+DELIM+
								review.getTitle()+DELIM+
								review.getDescription()+DELIM+
								review.getRating());
				 }	 		
			 }
			 fileWriter.close();//DON'T FORGET TO CLOSE STREAMS
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
	}
	
	/**
	 * Read all reviews from database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param events - arraylist of events handled by the driver
	 */
	public void readReviewDBFile(String aFileName, ArrayList<Event> events) {
		try {
			//Generate arraylist of reviews to temporarily store them in
			ArrayList<Review> reviews = new ArrayList<Review>();
			Scanner fileScanner = new Scanner (new File(aFileName));
			//Iterate through each line of text file, storing each field and then delimiting
			while(fileScanner.hasNextLine()) {
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				//If there are two many fields for a review, skip this line
				if(splitStr.length != REVIEW_FIELD_AMOUNT) {
					continue;
				}
				//Store split fields, parsing as needed
				String eventName = splitStr[0];
				String title = splitStr[1];
				String description = splitStr[2];
				int rating = Integer.parseInt(splitStr[3]);
				
				//Create and store review
				Review review = new Review(eventName, title, description, rating);
				reviews.add(review);
			}
			//Insert reviews into corresponding events
			for(Review review : reviews) {
				//Search for an event using the eventName of the review. If a match is found, add to that event's arraylist of reviews
				Event event = findEvent(review.getEventName(), events);
				if(event != null) {
					event.reviews.add(review);
				}
			}
			fileScanner.close();//DON'T FORGET IT
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
	}
	
	/**
	 * Writes all tickets to database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param users - arraylist of users handled by the driver
	 */
	public void writeTicketDBFile(String aFileName, ArrayList<User> users) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			//Iterate through users
			for(User user : users) {
				 if(user == null)
					 break;
				 //Iterate through arraylist of tickets within each user
				 for(Ticket ticket : user.getTickets()) {
					 if(ticket == null)
						 break;
					 fileWriter.println(ticket.username+DELIM+
							 	ticket.event.name+DELIM+
							 	ticket.purchaseTime.toString()+DELIM+
							 	ticket.purchaseDate.toString()+DELIM+
							 	ticket.seatRow+DELIM+
							 	ticket.seatCol+DELIM+
							 	ticket.isRefundable);
				 }	 		
			 }
			 fileWriter.close();//DON'T FORGET TO CLOSE STREAMS
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
	}
	
	/**
	 * Read all tickets from database file using fileIO
	 * @param aFileName - string used to determine the name of the database file to create/write to
	 * @param users - arraylist of users handled by the driver
	 * @param events - arraylist of events handled by the driver
	 */
	public void readTicketDBFile(String aFileName, ArrayList<User> users, ArrayList<Event> events) {
		try {
			//Create arraylist to store tickets in
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			Scanner fileScanner = new Scanner (new File(aFileName));
			//Iterate through each line of fileScanner, storing each line and then splitting with DELIM character to store each field
			while(fileScanner.hasNextLine()) {
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				//If there are too many fields for a ticket, skip this line
				if(splitStr.length != TICKET_FIELD_AMOUNT) {
					continue;
				}
				//Store variables, parsing from String as needed
				String username = splitStr[0];
				String eventName = splitStr[1];
				LocalTime purchaseTime = LocalTime.parse(splitStr[2]);
				LocalDate purchaseDate = LocalDate.parse(splitStr[3]);
				int seatRow = Integer.parseInt(splitStr[4]);
				int seatCol = Integer.parseInt(splitStr[5]);
				boolean isRefundable = Boolean.parseBoolean(splitStr[6]);
				
				//Only insert ticket if event is found
				//Search for correct event using the eventName
				Event event = findEvent(eventName, events);
				if(event != null) {
					Ticket ticket = new Ticket(username, event, purchaseTime, purchaseDate, seatRow, seatCol, isRefundable);
					tickets.add(ticket);
				}
			}
			//Iterate through tickets, searching for the corresponding user, then storing in their ticket arraylist
			for(Ticket ticket : tickets) {
				//Search for correct user using the username stored within a ticket
				User user = findUser(ticket.username, users);
				if(user != null) {
					user.getTickets().add(ticket);
				}
			}
			fileScanner.close();//DON'T FORGET IT
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
	}
	
	/**
	 * Search for event using an eventName
	 * @param eventName - String of an eventName
	 * @param events - arraylist of events handled by driver
	 * @return event if found, else null
	 */
	public Event findEvent(String eventName, ArrayList<Event> events) {
		//Iterate and compare eventNames - return event if true
		for(Event event : events) {
			if(event.name.equals(eventName)) {
				return event;
			}
		}
		return null;
	}
	
	/**
	 * Search for user using an username
	 * @param username - String of an username
	 * @param users - arraylist of users handled by driver
	 * @return user if found, else null
	 */
	public User findUser(String username, ArrayList<User> users) {
		//Iterate and compare usernames - return user if true
		for(User user : users) {
			if(user.getName().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Convert boolean array to string, so that it can be stored within a line of a text file
	 * @param rows - integer value of rows of array
	 * @param columns - integer value of columns of array
	 * @param array - a 2d boolean array
	 * @return a String of 1s and 0s, representing true and false values at corresponding indexes
	 */
	public String boolArrayToString(int rows, int columns, boolean array[][]) {
		String ret = "";
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(array[i][j] == true) {
					ret = ret + '1';
				}
				else {
					ret = ret + '0';
				}
			}
		}
		return ret;
	}
	
	/**
	 * Convert string to boolean array, so that it can be stored within a line of a text file
	 * @param rows - integer value of rows of array
	 * @param columns - integer value of columns of array
	 * @param arrayString - a String of 1s and 0s, representing true and false values at corresponding indexes
	 * @return array - a 2d boolean array generated by the method
	 */
	public boolean[][] stringToBoolArray(int rows, int columns, String arrayString) {
		boolean[][] array = new boolean[rows][columns];
		int count = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(arrayString.charAt(count) == '1') {
					array[i][j] = true;
				}
				else {
					array[i][j] = false;
				}
				count++;
			}
		}
		return array;
	}
	
}
