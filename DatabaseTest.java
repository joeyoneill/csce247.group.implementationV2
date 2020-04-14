import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/*
 * JUnit tests for Database.java
 */
class DatabaseTest {

	@BeforeClass
	public static void oneTimeSetup() {
		
	}
	
	@AfterClass
	public static void oneTimeTearDown() {
		
	}
	
	@Before
	public static void setup() {
		//runs before each test
	}
	
	@After
	public static void tearDown() {
		//runs after each test
	}
	
	//assertEquals(val1,val2)
		//assertFalse(val)
		//assertTrue(val)
		//assertSame(val1,val2)
		//assertNotSame(val1,val2)
		//assertNull(val)
		//assertNotNull(val)
	
	/*
	 * This test makes sure that the database read/write for users works, by comparing a user to one that has been written, then read again from the file
	 */
	@Test
	public void testDatabaseUserReadWrite() {
		Database DB = new Database();
		User charlieCoffin = new User("cdcoffin", "password", UserType.REGULAR, "9000100020003000");
		ArrayList<User> users = new ArrayList<User>();
		users.add(charlieCoffin);
		DB.writeUserDBFile("DatabaseTestUser.txt", users);
		users.clear();
		DB.readUserDBFile("DatabaseTestUser.txt", users);
		User temp = users.get(0);
		assertEquals(temp.getName(), charlieCoffin.getName());
		assertEquals(temp.getPassword(), charlieCoffin.getPassword());
		assertEquals(temp.getUsertype(), charlieCoffin.getUsertype());
		assertEquals(temp.getCCNum(), charlieCoffin.getCCNum());
	}
	
	/*
	 * This test makes sure that the database read/write for events works, by comparing an event to one that has been written, then read again from the file
	 */
	@Test
	public void testDatabaseEventReadWrite() {
		Database DB = new Database();
		ArrayList <Event> events = new ArrayList<Event>();
		Venue regalCinema = new Venue("Regal Cinema", 5, 5);
		LocalDate frozenDate = LocalDate.parse("2019-12-12");
		LocalTime frozenTime = LocalTime.parse("10:15:45");
		Movie frozenTwo = new Movie("Frozen 2", regalCinema, frozenDate, frozenTime, 9.95);
		events.add(frozenTwo);
		DB.writeEventDBFile("DatabaseTestEvent.txt", events);
		events.clear();
		DB.readEventDBFile("DatabaseTestEvent.txt", events);
		Event temp = events.get(0);
		assertEquals(temp.name, frozenTwo.name);
		assertEquals(temp.venue.getName(), frozenTwo.venue.getName());
		assertEquals(temp.venue.getRows(), frozenTwo.venue.getRows());
		assertEquals(temp.venue.getColumns(), frozenTwo.venue.getColumns());
		assertEquals(temp.time.toString(), frozenTwo.time.toString());
		assertEquals(temp.date.toString(), frozenTwo.date.toString());
		assertEquals(temp.cost, frozenTwo.cost);
	}
	
	/*
	 * This test makes sure that the database read/write for tickets works, by comparing a ticket to one that has been written, then read again from the file
	 */
	@Test
	public void testDatabaseTicketReadWrite() {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<User> users = new ArrayList<User>();
		Database DB = new Database();
		
		Venue regalCinema = new Venue("Regal Cinema", 5, 5);
		LocalDate frozenDate = LocalDate.parse("2019-12-12");
		LocalTime frozenTime = LocalTime.parse("10:15:45");
		Movie frozenTwo = new Movie("Frozen 2", regalCinema, frozenDate, frozenTime, 9.95);
		events.add(frozenTwo);
		
		//create user charlie
		User charlieCoffin = new User("cdcoffin", "password", UserType.REGULAR, "9000100020003000");
		users.add(charlieCoffin);
		
		//charlie buys ticket
		//public Ticket(String username, Event event, int seatRow, int seatCol, boolean isRefundable)
		Ticket charlieTicket = new Ticket("cdcoffin", frozenTwo, 3, 3, true);
		charlieCoffin.purchaseTicket(charlieTicket);
		
		DB.writeEventDBFile("DatabaseTestEvent.txt", events);
		DB.writeUserDBFile("DatabaseTestUser.txt", users);
		DB.writeTicketDBFile("DatabaseTestTicket.txt", users);
		events.clear();
		users.clear();
		DB.readEventDBFile("DatabaseTestEvent.txt", events);
		DB.readUserDBFile("DatabaseTestUser.txt", users);
		DB.readTicketDBFile("DatabaseTestTicket.txt", users, events);
		
		Ticket temp = users.get(0).getTickets().get(0);
		
		//test
		assertEquals(temp.username, charlieTicket.username);
		assertEquals(temp.event.name, charlieTicket.event.name);
		assertEquals(temp.event.date.toString(), charlieTicket.event.date.toString());
		assertEquals(temp.event.time.toString(), charlieTicket.event.time.toString());
		assertEquals(temp.event.venue.getName(), charlieTicket.event.venue.getName());
		assertEquals(temp.event.venue.getColumns(), charlieTicket.event.venue.getColumns());
		assertEquals(temp.event.venue.getRows(), charlieTicket.event.venue.getRows());
		assertEquals(temp.event.type, charlieTicket.event.type);
		assertEquals(temp.event.cost, charlieTicket.event.cost);
		assertEquals(temp.seatRow, charlieTicket.seatRow);
		assertEquals(temp.seatCol, charlieTicket.seatCol);
		assertEquals(temp.purchaseDate, charlieTicket.purchaseDate);
		assertEquals(temp.purchaseTime, charlieTicket.purchaseTime);
		assertEquals(temp.isRefundable, charlieTicket.isRefundable);
	}
	
	/*
	 * This test makes sure that the database read/write for review works, by comparing a review to one that has been written, then read again from the file
	 */
	@Test
	public void testDatabaseReviewReadWrite() {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<User> users = new ArrayList<User>();
		Database DB = new Database();
		
		Venue regalCinema = new Venue("Regal Cinema", 5, 5);
		LocalDate frozenDate = LocalDate.parse("2019-12-12");
		LocalTime frozenTime = LocalTime.parse("10:15:45");
		Movie frozenTwo = new Movie("Frozen 2", regalCinema, frozenDate, frozenTime, 9.95);
		events.add(frozenTwo);
		
		//create user charlie
		User charlieCoffin = new User("cdcoffin", "password", UserType.REGULAR, "9000100020003000");
		users.add(charlieCoffin);
		
		//charlie buys ticket
		//public Ticket(String username, Event event, int seatRow, int seatCol, boolean isRefundable)
		Ticket charlieTicket = new Ticket("cdcoffin", frozenTwo, 3, 3, true);
		charlieCoffin.purchaseTicket(charlieTicket);
		
		Review charlieReview = new Review("Frozen 2", "Bullocks", "N/A", 0);
		frozenTwo.reviews.add(charlieReview);
		
		DB.writeEventDBFile("DatabaseTestEvent.txt", events);
		DB.writeUserDBFile("DatabaseTestUser.txt", users);
		DB.writeTicketDBFile("DatabaseTestTicket.txt", users);
		DB.writeReviewDBFile("DatebaseTestReview.txt", events);
		
		events.clear();
		users.clear();
		
		DB.readEventDBFile("DatabaseTestEvent.txt", events);
		DB.readUserDBFile("DatabaseTestUser.txt", users);
		DB.readTicketDBFile("DatabaseTestTicket.txt", users, events);
		DB.readReviewDBFile("DatebaseTestReview.txt", events);
		
		Review temp = events.get(0).reviews.get(0);
		
		assertEquals(temp.eventName, charlieReview.eventName);
		assertEquals(temp.description, charlieReview.description);
		assertEquals(temp.title, charlieReview.title);
		assertEquals(temp.rating, charlieReview.rating);
	}
	
	//Note that I didn't test load() or save() because those methods simply call all the methods that have been tested here with prefilled parameters (to make the driver simpler for other members to write)
}
