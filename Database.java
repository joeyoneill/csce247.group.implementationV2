import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Database {
	public static final String DELIM = "\t"; //Delimiting character for file
	public static final int FIELD_AMOUNT = 8; //There should be 7 fields in each line of the file
	
	public Database() {
	}
	
	public void writeEventDBFile(String aFileName, ArrayList<Event> events) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			
			 
			for(Event event : events) {
				 if(event == null)
					 break;
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
	
	public void readEventDBFile(String aFileName, ArrayList<Event> events) {
		try {
			Scanner fileScanner = new Scanner (new File(aFileName));
			while(fileScanner.hasNextLine()) {
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				if(splitStr.length != FIELD_AMOUNT) {
					//incorrect number of fields on line
					//return to top of while loop (don't bother with this line)
					continue;
				}
				String name = splitStr[0];
				String venueName = splitStr[1];
				int rows = Integer.parseInt(splitStr[2]);
				int columns = Integer.parseInt(splitStr[3]);
				String type = splitStr[4];
				LocalDate date = LocalDate.parse(splitStr[5]);
				LocalTime time = LocalTime.parse(splitStr[6]);
				boolean array[][] = stringToBoolArray(rows, columns, splitStr[7]);
				
				Venue venue = new Venue(venueName, rows, columns);
						
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
			fileScanner.close();//DON'T FORGET IT
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
	} 
	
	//write user database file
	public void writeUserDBFile(String aFileName, ArrayList<User> users) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			
			for(User user : users) {
				 if(user == null)
					 break;
				 
				 fileWriter.println(user.getUsername()+DELIM+
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
	
	//read user database file
	public void readUserDBFile(String aFileName, ArrayList<User> users) {
		try {
			Scanner fileScanner = new Scanner (new File(aFileName));
			while(fileScanner.hasNextLine()) {
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				if(splitStr.length != 4) {
					//incorrect number of fields on line
					//return to top of while loop (don't bother with this line)
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
	
	//write review database file
	public void writeReviewDBFile(String aFileName, ArrayList<Event> events) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			
			for(Event event : events) {
				 if(event == null)
					 break;
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
	
	//read review database file
	public void readReviewDBFile(String aFileName, ArrayList<Event> events) {
		try {
			ArrayList<Review> reviews = new ArrayList<Review>();
			Scanner fileScanner = new Scanner (new File(aFileName));
			while(fileScanner.hasNextLine()) {
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				if(splitStr.length != 4) {
					//incorrect number of fields on line
					//return to top of while loop (don't bother with this line)
					continue;
				}
				String eventName = splitStr[0];
				String title = splitStr[1];
				String description = splitStr[2];
				int rating = Integer.parseInt(splitStr[3]);
				
				Review review = new Review(eventName, title, description, rating);
				reviews.add(review);
			}
			//insert reviews into corresponding events
			for(Review review : reviews) {
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
	//public Ticket(String username, Event event, LocalTime purchaseTime, LocalDate purchaseDate, int seatRow, int seatCol, boolean isRefundable)
	public void writeTicketDBFile(String aFileName, ArrayList<User> users) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			
			for(User user : users) {
				 if(user == null)
					 break;
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
	
	public void readTicketDBFile(String aFileName, ArrayList<User> users, ArrayList<Event> events) {
		try {
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			Scanner fileScanner = new Scanner (new File(aFileName));
			while(fileScanner.hasNextLine()) {
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				if(splitStr.length != 7) {
					//incorrect number of fields on line
					//return to top of while loop (don't bother with this line)
					continue;
				}
				String username = splitStr[0];
				String eventName = splitStr[1];
				LocalTime purchaseTime = LocalTime.parse(splitStr[2]);
				LocalDate purchaseDate = LocalDate.parse(splitStr[3]);
				int seatRow = Integer.parseInt(splitStr[4]);
				int seatCol = Integer.parseInt(splitStr[5]);
				boolean isRefundable = Boolean.parseBoolean(splitStr[6]);
				
				//only insert ticket if event is found
				Event event = findEvent(eventName, events);
				if(event != null) {
					Ticket ticket = new Ticket(username, event, purchaseTime, purchaseDate, seatRow, seatCol, isRefundable);
					tickets.add(ticket);
				}
			}
			//insert ticket into corresponding user
			for(Ticket ticket : tickets) {
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
	
	public Event findEvent(String eventName, ArrayList<Event> events) {
		for(Event event : events) {
			if(event.name.equals(eventName)) {
				return event;
			}
		}
		return null;
	}
	
	public User findUser(String username, ArrayList<User> users) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	
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
