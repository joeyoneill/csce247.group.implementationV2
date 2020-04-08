import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class DBDriver {
	public static void main(String[] args) { 
		
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<User> users = new ArrayList<User>();
		Database DB = new Database();
		//generate frozen 2
		Venue regalCinema = new Venue("Regal Cinema", 5, 5);
		LocalDate frozenDate = LocalDate.parse("2019-12-12");
		LocalTime frozenTime = LocalTime.parse("10:15:45");
		Movie frozenTwo = new Movie("Frozen 2", regalCinema, frozenDate, frozenTime, 9.95);
		events.add(frozenTwo);
		//generate parasite
		Venue littleTheater = new Venue("Little Theater", 6, 5);
		LocalDate parasiteTime = LocalDate.parse("2020-12-12");
		LocalTime parasiteDate = LocalTime.parse("12:15:45");
		Movie parasite = new Movie("Parasite", littleTheater, parasiteTime, parasiteDate, 8.95);
		events.add(parasite);
		
		//create user charlie
		User charlieCoffin = new User("cdcoffin", "password", UserType.REGULAR, "9000100020003000");
		users.add(charlieCoffin);
		//create user joey
		User joey = new User("JohnnyAppleseed", "monkey5", UserType.MILITARY, "1000100010001000");
		users.add(joey);
		
		//charlie buys ticket
		//public Ticket(String username, Event event, int seatRow, int seatCol, boolean isRefundable)
		Ticket charlieTicket = new Ticket("cdcoffin", frozenTwo, 3, 3, true);
		charlieCoffin.purchaseTicket(charlieTicket);
		//joey buys ticket
		Ticket joeyTicket = new Ticket("JohnnyAppleseed", frozenTwo, 0, 3, true);
		joey.purchaseTicket(joeyTicket);
		
		//charlie writes review
		//public Review(String eventName, String title, String description, int rating)
		Review charlieReview = new Review("Frozen 2", "Bullocks", "N/A", 0);
		frozenTwo.reviews.add(charlieReview);
		//joey writes review
		Review joeyReview = new Review("Frozen 2", "I loved it", "Sang along to every song!", 5);
		frozenTwo.reviews.add(joeyReview);
		
		//test write
		DB.writeEventDBFile("eventsInput.txt", events);
		DB.writeUserDBFile("userInput.txt", users);
		DB.writeReviewDBFile("reviewsInput.txt", events);
		DB.writeTicketDBFile("ticketInput.txt", users);
		
		//test read then write
		DB.readEventDBFile("eventsInput.txt", events);
		DB.readUserDBFile("userInput.txt", users);
		DB.readReviewDBFile("reviewsInput.txt", events);
		DB.readTicketDBFile("ticketInput.txt", users, events);
		
		DB.writeEventDBFile("eventsOutput.txt", events);
		DB.writeUserDBFile("userOutput.txt", users);
		DB.writeReviewDBFile("reviewsOutput.txt", events);
		DB.writeTicketDBFile("ticketOutput.txt", users);

		
		
	}
}
